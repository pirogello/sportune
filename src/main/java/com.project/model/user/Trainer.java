package com.project.model.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.model.Role;
import com.project.model.sport.Train;
import com.project.model.sport.type.SportType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@NoArgsConstructor
@Table(name = "trainer")
public class Trainer extends BaseUser{
    private String qualification;
    private String workExperience;
    @Enumerated(EnumType.STRING)
    private SportType sport;
    @OneToMany(mappedBy = "trainer")
    @JsonIgnoreProperties({"likedPublication", "jointPublication", "publications", "followers", "following", "trainer"})
    private List<User> trainee = new ArrayList<>();
    @OneToMany(mappedBy = "trainer")
    @JsonIgnoreProperties({"trainer"})
    private List<Train> trains = new ArrayList<>();


    public Trainer(String username, String info, String password, String qualification, String workExperience, SportType type) {
        super(username, info, password);
        this.role = Role.TRAINER;
        this.qualification = qualification;
        this.workExperience = workExperience;
        this.sport = type;
    }

    public void addTrainee(User user){
        user.setTrainer(this);
    }

    public void addTrain(Train train){
        train.setTrainer(this);
    }

    @Override
    public String toString() {
        return  "Trainer{" +",\n" +
                "   id=" + id +",\n"+
                "   username='" + username +",\n" +
                "   info='" + info +",\n" +
                "   password='" + password +",\n" +
                "   role=" + role +",\n"+
                "   followers=" +  this.followers.stream().map(BaseUser::getUsername).collect(Collectors.toList()) + ",\n" +
                "   following=" +  this.following.stream().map(BaseUser::getUsername).collect(Collectors.toList()) + ",\n" +
                "   likedPublication=" +  this.likedPublication.stream().map((a) -> a.getTitle()).collect(Collectors.toList()) + ",\n" +
                "   jointPublication=" +  this.jointPublication.stream().map((a) -> a.getTitle()).collect(Collectors.toList()) + ",\n" +
                "   publications=" + publications +",\n"+
                "   sport=" + sport +",\n"+
                "   trainee=" + this.trainee.stream().map((a) -> a.getUsername()).collect(Collectors.toList()) + ",\n" +
                "   qualification='" + qualification +",\n" +
                "   workExperience='" + workExperience +",\n" +
                "}"+",\n";
    }
}
