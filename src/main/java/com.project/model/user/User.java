package com.project.model.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.model.Publication;
import com.project.model.Role;
import com.project.model.sport.Train;
import com.project.model.sport.competition.BaseCompetition;
import com.project.model.sport.result.BaseSportResult;
import com.project.model.sport.type.BaseSport;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor
@Entity
@Table(name = "student")
public class User extends BaseUser {
    @ManyToMany
    @JsonIgnoreProperties({"users"})
    @JoinTable(name = "sport_participants", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "sport_id")})
    private List<BaseSport> sports = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties({"user"})
    private List<BaseSportResult> sportResults  = new ArrayList<>();
    @ManyToMany
    @JsonIgnoreProperties({"players"})
    @JoinTable(name = "user_competitions",joinColumns = {@JoinColumn(name = "user_id")},inverseJoinColumns = {@JoinColumn(name = "competition_id")})
    private List<BaseCompetition> competitions = new ArrayList<>();
    @ManyToOne
    @JsonIgnoreProperties({"likedPublication", "jointPublication", "publications", "followers", "following","trainee"})
    private Trainer trainer;
    @ManyToMany
    @JsonIgnoreProperties({"users"})
    @JoinTable(name = "user_trains", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "train_id")})
    private List<Train> trains = new ArrayList<>();





    public User(String username, String info, String password){
        super(username,info, password);
        this.role = Role.USER;
    }

    public void addSport(BaseSport sport){
        this.sports.add(sport);
    }
    public void addSportResults(BaseSportResult result){
        this.sportResults.add(result);
    }

    public void addCompetition(BaseCompetition competition){
        this.competitions.add(competition);
    }
    public void addTrain(Train train){
        this.trains.add(train);
    }

    @Override
    public String toString() {
        return "User{" + ",\n" +
                "   id=" + id +",\n"+
                "   username='" + username +",\n" +
                "   info='" + info +",\n" +
                "   password='" + password +",\n" +
                "   role=" + role +",\n"+
                "   trainer="+this.trainer.getUsername()+",\n"+
                "   competitions=" +  this.competitions.stream().map(BaseCompetition::getTitle).collect(Collectors.toList()) + ",\n" +
                "   followers=" +  this.followers.stream().map(BaseUser::getUsername).collect(Collectors.toList()) + ",\n" +
                "   following=" +  this.following.stream().map(BaseUser::getUsername).collect(Collectors.toList()) + ",\n" +
                "   likedPublication=" +  this.likedPublication.stream().map(Publication::getTitle).collect(Collectors.toList()) + ",\n" +
                "   jointPublication=" +  this.jointPublication.stream().map(Publication::getTitle).collect(Collectors.toList()) + ",\n" +
                "   publications=" + publications +",\n"+
                "   sports=" + sports +",\n"+
                "   sportResults=" + sportResults.stream().map((r) -> r.getCompetition().getTitle() + ": place - " + r.getPoint()).collect(Collectors.toList()) +",\n"+
                '}'+",\n";
    }
}
