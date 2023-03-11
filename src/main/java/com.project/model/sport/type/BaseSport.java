package com.project.model.sport.type;

import com.project.model.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Entity
@Table(name = "sport")
public class BaseSport {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
    protected UUID id;
    @Column(nullable = false)
    protected String title;
    @Enumerated(EnumType.ORDINAL)
    private SportType sportType;
    @ManyToMany(mappedBy = "sports")
    protected List<User> users = new ArrayList<>();

    public BaseSport(String title, SportType type) {
        this.title = title;
        this.sportType = type;
    }

    public void addUser(User user){
        users.add(user);
        user.addSport(this);
    }

    @Override
    public String toString() {
        return "\n      Football{" + ",\n"+
                "           id=" + id + ",\n"+
                "           title='" + title + ",\n" +
                "           sportType='" + sportType + ",\n" +
                "           team='" +  this.users.stream().map((a) -> a.getUsername()).collect(Collectors.toList()) + ",\n" +
                "       }"+ '\n';

    }
}
