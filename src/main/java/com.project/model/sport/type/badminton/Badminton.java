package com.project.model.sport.type.badminton;

import com.project.model.sport.type.SportType;
import lombok.Data;
import com.project.model.sport.type.BaseSport;
import com.project.model.user.User;

import java.util.UUID;


@Data
public class Badminton extends BaseSport {
    private BadmintonType type;
    private User team;

    public Badminton(String title, BadmintonType type) {
        super(title, SportType.BADMINTON);
        this.type = type;
    }

    @Override
    public String toString() {
        return "\n      Badminton{" + ",\n"+
                "           id=" + id + ",\n"+
                "           title='" + title + ",\n" +
                "           type=" + type + ",\n"+
                "       }"+ '\n';
    }
}
