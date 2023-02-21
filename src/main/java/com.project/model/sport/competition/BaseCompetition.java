package com.project.model.sport.competition;

import lombok.Data;
import lombok.NoArgsConstructor;
import com.project.model.sport.type.SportType;
import com.project.model.user.Organizer;
import com.project.model.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class BaseCompetition {
    protected UUID id;
    protected String title;
    protected LocalDateTime start;
    protected LocalDateTime end;
    protected SportType type;
    protected Organizer organizer;
    protected List<User> players = new ArrayList<>();

    public BaseCompetition(UUID id, String title, LocalDateTime start, LocalDateTime end, SportType type) {
        this.id = id;
        this.title = title;
        this.start = start;
        this.end = end;
        this.type = type;
    }

    public void addPlayer(User user){
        players.add(user);
    }

}
