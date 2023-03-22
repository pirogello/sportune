package com.project.controller;

import com.project.model.Publication;
import com.project.model.Status;
import com.project.model.sport.Train;
import com.project.model.sport.competition.BaseCompetition;
import com.project.model.sport.result.BaseSportResult;
import com.project.model.sport.type.BaseSport;
import com.project.model.sport.type.SportType;
import com.project.model.user.*;
import com.project.repository.PublicationRepo;
import com.project.repository.TestDBConnectionUserRepo;
import com.project.repository.sportRepos.BaseCompetitionRepo;
import com.project.repository.sportRepos.BaseSportRepo;
import com.project.repository.sportRepos.BaseSportResultRepo;
import com.project.repository.sportRepos.TrainRepo;
import com.project.repository.userRepos.OrganizerRepo;
import com.project.repository.userRepos.TrainerRepo;
import com.project.repository.userRepos.UserRepo;
import com.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping(value = "/test/api")
public class TestController {

    @Autowired
    private TestDBConnectionUserRepo repo;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private TrainerRepo trainerRepo;
    @Autowired
    private TrainRepo trainRepo;
    @Autowired
    private OrganizerRepo organizerRepo;
    @Autowired
    private PublicationRepo publicationRepo;
    @Autowired
    private BaseCompetitionRepo competitionRepo;
    @Autowired
    private BaseSportRepo sportRepo;
    @Autowired
    private BaseSportResultRepo sportResultRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @GetMapping("/get/{id}")
    public Optional<TestDBConnectionUser> getUser(@PathVariable Integer id){
        return repo.findById(id);
    }

    @GetMapping("/fillDB")
    public void fillDatabase(){
        User user1 = new User("user1", "info about user1", "passUser1");
        User user2 = new User("user2", "info about user2", "passUser2");
        User user3 = new User("user3", "info about user3", "passUser3");

        user1.setStatus(Status.ACTIVE);
        user2.setStatus(Status.ACTIVE);
        user3.setStatus(Status.ACTIVE);

        userService.register(user1);
        userService.register(user2);
        userService.register(user3);

        BaseSport sport1 = new BaseSport("football1", SportType.FOOTBALL);
        BaseSport sport2 = new BaseSport("football2", SportType.FOOTBALL);
        BaseSport sport3 = new BaseSport("badminton", SportType.BADMINTON);
        sportRepo.save(sport1);
        sportRepo.save(sport2);
        sportRepo.save(sport3);

        sport1.addUser(user1);
        sport1.addUser(user2);
        sport2.addUser(user2);
        sport3.addUser(user3);


        Trainer trainer1 = new Trainer("trainer1", "info about trainer1", "passTrainer1","3 years" ,"professional player", SportType.BADMINTON);
        Trainer trainer2 = new Trainer("trainer2", "info about trainer2", "passTrainer2","1 years","trainer", SportType.FOOTBALL);
        trainer1.setStatus(Status.ACTIVE);
        trainer2.setStatus(Status.ACTIVE);

        userService.register(trainer1);
        userService.register(trainer2);

        trainer1.addTrainee(user2);
        trainer1.addTrainee(user3);
        trainer2.addTrainee(user1);

        Organizer organizer1 = new Organizer("organizer1", "info about organizer1", "passOrganizer1");
        organizer1.setStatus(Status.ACTIVE);
        userService.register(organizer1);

        user2.followToUser(user1);
        user1.followToUser(user3);


        List<BaseUser> publications1authors = new ArrayList<>(Arrays.asList(user1,user2));
        List<BaseUser> publications2authors = new ArrayList<>(Collections.singletonList(user1));
        List<BaseUser> publications3authors = new ArrayList<>(Arrays.asList(user2,user3));
        List<BaseUser> publications4authors = new ArrayList<>(Collections.singletonList(trainer1));
        List<BaseUser> publications5authors = new ArrayList<>(Collections.singletonList(trainer2));
        List<BaseUser> publications6authors = new ArrayList<>(Arrays.asList(trainer1,trainer2));
        List<BaseUser> publications7authors = new ArrayList<>(Arrays.asList(organizer1,trainer1, trainer2));
        List<BaseUser> publications8authors = new ArrayList<>(Collections.singletonList(organizer1));
        Publication publication1 = new Publication("publication1");
        Publication publication2 = new Publication("publication2");
        Publication publication3 = new Publication("publication3");
        Publication publication4 = new Publication("publication4");
        Publication publication5 = new Publication("publication5");
        Publication publication6 = new Publication("publication6");
        Publication publication7 = new Publication("publication7");
        Publication publication8 = new Publication("publication8");
        publicationRepo.save(publication1);
        publicationRepo.save(publication2);
        publicationRepo.save(publication3);
        publicationRepo.save(publication4);
        publicationRepo.save(publication5);
        publicationRepo.save(publication6);
        publicationRepo.save(publication7);
        publicationRepo.save(publication8);

        publication1.addAuthors(publications1authors);
        publication2.addAuthors(publications2authors);
        publication3.addAuthors(publications3authors);
        publication4.addAuthors(publications4authors);
        publication5.addAuthors(publications5authors);
        publication6.addAuthors(publications6authors);
        publication7.addAuthors(publications7authors);
        publication8.addAuthors(publications8authors);

        user1.proposeJointPublication(publication2, trainer1);
       // trainer1.joinToPublication(publication2);
        publication1.like(user1);
        publication1.like(user2);
        publication2.like(user1);
        publication3.like(trainer1);

        BaseCompetition competition1 = new BaseCompetition("competition1", LocalDateTime.now(), LocalDateTime.of(2023, 10, 1,10,0,0), sport1, organizer1);
        competitionRepo.save(competition1);
        competition1.addPlayer(user1);
        competition1.addPlayer(user2);


        BaseSportResult result1 = new BaseSportResult(user1, competition1, 1);
        BaseSportResult result2 = new BaseSportResult(user2, competition1, 2);
        sportResultRepo.save(result1);
        sportResultRepo.save(result2);

        Train train1 = new Train("train1", LocalDateTime.now(), LocalDateTime.of(2023, 10, 1,10,0,0));
        trainRepo.save(train1);
        train1.addTrainer(trainer1);
        train1.addUser(user1);
        train1.addUser(user2);

        trainerRepo.save(trainer1);
        trainerRepo.save(trainer2);
        userRepo.save(user1);
        userRepo.save(user2);
        userRepo.save(user3);
        organizerRepo.save(organizer1);
        publicationRepo.save(publication1);
        publicationRepo.save(publication2);
        publicationRepo.save(publication3);
        publicationRepo.save(publication4);
        publicationRepo.save(publication5);
        publicationRepo.save(publication6);
        publicationRepo.save(publication7);
        publicationRepo.save(publication8);
        sportRepo.save(sport1);
        sportRepo.save(sport2);
        sportRepo.save(sport3);
        sportResultRepo.save(result1);
        sportResultRepo.save(result2);
        competitionRepo.save(competition1);

        System.out.println("база заполнена");
    }

    @GetMapping("/{user}")
    public TestDBConnectionUser getUser(@PathVariable TestDBConnectionUser user){
        return user;
    }
}
