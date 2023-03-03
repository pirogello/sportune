package com.project.model.sport.type.football;

import com.project.model.sport.type.SportType;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.project.model.sport.type.BaseSport;
import com.project.model.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class Football extends BaseSport {
    private List<User> team = new ArrayList<>();

    public Football(String title) {
        super(title, SportType.FOOTBALL);
    }

    public void addToTeam(User user){
        this.team.add(user);
        user.addSport(this);
    }
    @Override
    public String toString() {
        return "\n      Football{" + ",\n"+
                "           id=" + id + ",\n"+
                "           title='" + title + ",\n" +
                "           team='" +  this.team.stream().map((a) -> a.getUsername()).collect(Collectors.toList()) + ",\n" +
                "       }"+ '\n';
    }
}
