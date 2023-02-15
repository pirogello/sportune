package model.sport.type.badminton;

import lombok.Data;
import model.sport.type.BaseSport;
import model.user.BaseUser;
import model.user.Trainer;
import model.user.User;

import java.util.UUID;


@Data
public class Badminton extends BaseSport {
    private BadmintonType type;
    private User team;

    public Badminton(UUID id, String title, User user, BadmintonType type) {
        super(id, title, user);
        this.type = type;
        user.addSport(this);
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
