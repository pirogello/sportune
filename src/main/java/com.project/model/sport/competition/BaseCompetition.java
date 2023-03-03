package com.project.model.sport.competition;

import com.project.model.sport.type.BaseSport;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.project.model.sport.type.SportType;
import com.project.model.user.Organizer;
import com.project.model.user.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "sport_competition")
public class BaseCompetition {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
    protected UUID id;
    protected String title;
    //@Column(name = "local_date_time", columnDefinition = "TIMESTAMP")
    protected LocalDateTime startCompetition;
   // @Column(name = "local_date_time", columnDefinition = "TIMESTAMP")
    protected LocalDateTime endCompetition;
    @ManyToOne
    protected BaseSport sport;
    @ManyToOne
    protected Organizer organizer;
    @ManyToMany(mappedBy = "competitions")
    protected List<User> players = new ArrayList<>();

    public BaseCompetition(String title, LocalDateTime start, LocalDateTime end, BaseSport sport, Organizer organizer) {
        this.title = title;
        this.startCompetition = start;
        this.endCompetition = end;
        this.sport = sport;
        this.organizer = organizer;
    }

    public void addPlayer(User user){
        players.add(user);
        user.addCompetition(this);
    }

}
