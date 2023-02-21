package com.project.model.sport.result;

import lombok.Data;
import com.project.model.sport.type.BaseSport;
import com.project.model.user.User;

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
