package com.project;

import com.project.model.Publication;
import com.project.model.sport.result.BaseSportResult;
import com.project.model.sport.type.SportType;
import com.project.model.sport.type.badminton.Badminton;
import com.project.model.sport.type.badminton.BadmintonType;
import com.project.model.sport.type.football.Football;
import com.project.model.user.BaseUser;
import com.project.model.user.Organizer;
import com.project.model.user.Trainer;
import com.project.model.user.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
//        User user1 = new User(UUID.randomUUID(), "user1", "info about user1", "passUser1");
//        User user2 = new User(UUID.randomUUID(), "user2", "info about user2", "passUser2");
//        User user3 = new User(UUID.randomUUID(), "user3", "info about user3", "passUser3");
//
//        Football sport1 = new Football(UUID.randomUUID(), "football1", user2);
//        Football sport2 = new Football(UUID.randomUUID(), "football2", user3);
//        Badminton sport3 = new Badminton(UUID.randomUUID(), "badminton", user1, BadmintonType.SOLO);
//        sport1.addToTeam(user3);
//        sport2.addToTeam(user2);
//
//        BaseSportResult result1 = new BaseSportResult(user1, sport3, 1);
//        BaseSportResult result2 = new BaseSportResult(user2, sport2, 2);
//
//        Trainer trainer1 = new Trainer(UUID.randomUUID(), "trainer1", "info about trainer1", "passTrainer1","3 years" ,"professional player", SportType.BADMINTON);
//        Trainer trainer2 = new Trainer(UUID.randomUUID(), "trainer2", "info about trainer2", "passTrainer2","1 years","trainer", SportType.FOOTBALL);
//
//        trainer1.addTrainee(user2);
//        trainer1.addTrainee(user3);
//        trainer2.addTrainee(user1);
//
//        Organizer organizer1 = new Organizer(UUID.randomUUID(), "organizer1", "info about organizer1", "passOrganizer1");
//
//        List<BaseUser> publications1authors = new ArrayList<>(Arrays.asList(user1,user2));
//        List<BaseUser> publications2authors = new ArrayList<>(Collections.singletonList(user1));
//        List<BaseUser> publications3authors = new ArrayList<>(Arrays.asList(user2,user3));
//        List<BaseUser> publications4authors = new ArrayList<>(Collections.singletonList(trainer1));
//        List<BaseUser> publications5authors = new ArrayList<>(Collections.singletonList(trainer2));
//        List<BaseUser> publications6authors = new ArrayList<>(Arrays.asList(trainer1,trainer2));
//        List<BaseUser> publications7authors = new ArrayList<>(Arrays.asList(organizer1,trainer1, trainer2));
//        List<BaseUser> publications8authors = new ArrayList<>(Collections.singletonList(organizer1));
//
//        Publication publication1 = new Publication(UUID.randomUUID(), "publication1", publications1authors);
//        Publication publication2 = new Publication(UUID.randomUUID(), "publication2", publications2authors);
//        Publication publication3 = new Publication(UUID.randomUUID(), "publication3", publications3authors);
//        Publication publication4 = new Publication(UUID.randomUUID(), "publication4", publications4authors);
//        Publication publication5 = new Publication(UUID.randomUUID(), "publication5", publications5authors);
//        Publication publication6 = new Publication(UUID.randomUUID(), "publication6", publications6authors);
//        Publication publication7 = new Publication(UUID.randomUUID(), "publication7", publications7authors);
//        Publication publication8 = new Publication(UUID.randomUUID(), "publication8", publications8authors);
//
//        user1.proposeJointPublication(publication2, trainer1);
//        trainer1.joinToPublication(publication2);
//
//        publication1.like(user1);
//        publication1.like(user2);
//        publication2.like(user1);
//        publication3.like(trainer1);
//
//        user1.followToUser(trainer1);
//        user2.followToUser(trainer1);
//        user1.followToUser(trainer2);
//
//        System.out.println(user1);
//        System.out.println(user2);
//        System.out.println(user3);
//
//        System.out.println(trainer1);
//        System.out.println(trainer2);
//        System.out.println(organizer1);
    }
}
