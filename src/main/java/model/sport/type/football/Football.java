package model.sport.type.football;

import lombok.Data;
import lombok.NoArgsConstructor;
import model.sport.type.BaseSport;
import model.user.BaseUser;
import model.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class Football extends BaseSport {
    private List<User> team = new ArrayList<>();

    public Football(UUID id, String title, User user) {
        super(id, title, user);
        user.addSport(this);
        team.add(user);
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
