package com.project.model.user;

import com.project.model.Publication;
import com.project.model.Role;
import com.project.model.sport.competition.BaseCompetition;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Organizer extends BaseUser {

    private List<BaseCompetition> competitions = new ArrayList<>();

    public Organizer(UUID id, String username, String info, String password) {
        super(id, username, info, password);
        this.role = Role.ORGANIZER;
    }

    public void addCompetition(BaseCompetition competition){
        this.competitions.add(competition);
        competition.setOrganizer(this);
    }

    @Override
    public String toString() {
        return "Organizer{" + ",\n"+
                "   competitions=" +  this.competitions.stream().map(BaseCompetition::getTitle).collect(Collectors.toList()) + ",\n" +
                "   id=" + id + ",\n"+
                "   username='" + username + ",\n" +
                "   info='" + info + ",\n" +
                "   password='" + password + ",\n" +
                "   role=" + role + ",\n" +
                "   followers=" +  this.followers.stream().map(BaseUser::getUsername).collect(Collectors.toList()) + ",\n" +
                "   following=" +  this.following.stream().map(BaseUser::getUsername).collect(Collectors.toList()) + ",\n" +
                "   publications=" + publications + ",\n" +
                "   likedPublication=" +  this.likedPublication.stream().map(Publication::getTitle).collect(Collectors.toList()) + ",\n" +
                "   jointPublication=" +  this.jointPublication.stream().map(Publication::getTitle).collect(Collectors.toList()) + ",\n" +
                '}';
    }
}

