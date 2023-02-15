package model.sport.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import model.sport.type.BaseSport;
import model.user.BaseUser;
import model.user.User;

@Data
public class BaseSportResult {
    private User user;
    private BaseSport sport;
    private int point;

    public BaseSportResult(User user, BaseSport sport, int point) {
        this.user = user;
        this.sport = sport;
        this.point = point;

        user.addSportResults(this);
    }
}
