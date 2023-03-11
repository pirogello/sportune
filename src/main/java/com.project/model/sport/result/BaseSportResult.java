package com.project.model.sport.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.model.sport.competition.BaseCompetition;
import lombok.Data;
import com.project.model.sport.type.BaseSport;
import com.project.model.user.User;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "sport_result")
public class BaseSportResult {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @ManyToOne
    @JsonIgnoreProperties({"users", "following", "followers", "publications", "likedPublication", "jointPublication", "competitions"})
    private User user;
    @ManyToOne
    @JsonIgnoreProperties({"players"})
    private BaseCompetition competition;
    private int point;

    public BaseSportResult(User user, BaseCompetition competition, int point) {
        this.user = user;
        this.competition = competition;
        this.point = point;

        user.addSportResults(this);
    }
}
