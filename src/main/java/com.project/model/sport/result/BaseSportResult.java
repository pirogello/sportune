package com.project.model.sport.result;

import lombok.Data;
import com.project.model.sport.type.BaseSport;
import com.project.model.user.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "sport_result")
public class BaseSportResult {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private UUID id;
    @ManyToOne
    private User user;
    @ManyToOne
    private BaseSport sport;
    private int point;

    public BaseSportResult(User user, BaseSport sport, int point) {
        this.user = user;
        this.sport = sport;
        this.point = point;

        user.addSportResults(this);
    }
}
