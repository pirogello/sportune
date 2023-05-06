package com.project.model.sport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.model.user.Trainer;
import com.project.model.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Train {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    private String location;
    @Column(columnDefinition = "TIMESTAMP")
    protected LocalDateTime startTrain;
    @Column(columnDefinition = "TIMESTAMP")
    protected LocalDateTime endTrain;
    @ManyToOne
    @JsonIgnoreProperties({"likedPublication", "jointPublication", "publications", "followers", "following", "trains", "trainee"})
    private Trainer trainer;
    @ManyToMany(mappedBy = "trains", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"likedPublication", "jointPublication", "publications","competitions","sportResults", "followers", "following", "trains", "trainer"})
    private List<User> users = new ArrayList<>();




    public Train(String location, LocalDateTime startTrain, LocalDateTime endTrain) {
        this.location = location;
        this.startTrain = startTrain;
        this.endTrain = endTrain;
    }

    public void addUser(User user){
        user.addTrain(this);
    }
    public void deleteUser(User user){
        user.deleteTrain(this);
    }
    public void addTrainer(Trainer trainer){
        trainer.addTrain(this);
    }

    @Override
    public String toString() {
        return "Train{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", startTrain=" + startTrain +
                ", endTrain=" + endTrain +
                ", trainer=" + trainer +
                ", users=" + users +
                '}';
    }
}
