package com.project.model.user;

import lombok.Data;
import com.project.model.Publication;
import com.project.model.Role;
import com.project.model.sport.competition.BaseCompetition;
import com.project.model.sport.result.BaseSportResult;
import com.project.model.sport.type.BaseSport;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor
@Entity
@Table(name = "student")
public class User extends BaseUser {
    @ManyToMany
    @JoinTable(
            name = "sport_participants",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "sport_id")}
    )
    private List<BaseSport> sports = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private List<BaseSportResult> sportResults  = new ArrayList<>();
    @ManyToMany
    @JoinTable(
            name = "user_competitions",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "competition_id")}
    )
    private List<BaseCompetition> competitions = new ArrayList<>();
    @ManyToOne
    private Trainer trainer;


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
                "   sportResults=" + sportResults.stream().map((r) -> r.getSport().getTitle() + ": place - " + r.getPoint()).collect(Collectors.toList()) +",\n"+
                '}'+",\n";
    }
}
