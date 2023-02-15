package model.user;

import lombok.Data;
import model.Role;
import model.sport.type.SportType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
public class Trainer extends BaseUser{

    private SportType sport;
    private List<User> trainee = new ArrayList<>();
    private String qualification;
    private String workExperience;

    public Trainer(UUID id, String username, String info, String password, String qualification, String workExperience, SportType type) {
        super(id, username, info, password);
        this.role = Role.TRAINER;
        this.qualification = qualification;
        this.workExperience = workExperience;
        this.sport = type;
    }

    public void addTrainee(User user){
        this.trainee.add(user);
        user.setTrainer(this);
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
