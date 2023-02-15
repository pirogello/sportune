package model;

import lombok.Getter;
import model.user.BaseUser;
import model.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
public class Publication {
    private UUID id;
    private String title;
    private List<BaseUser> authors;
    private List<BaseUser> likes = new ArrayList<>();

    public Publication(UUID id, String title, List<BaseUser> authors) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        for (BaseUser author : authors) {
            author.addPublication(this);
        }
    }

    public void addAuthor(BaseUser user){
        this.authors.add(user);
    }
    public void like(BaseUser user){
        this.likes.add(user);
        user.likePublication(this);
    }
    @Override
    public String toString() {
        return  "\n         Publication{"+ ",\n" +
                "           id=" + id + ",\n" +
                "           title=" + title + ",\n" +
                "           authors=" +  this.authors.stream().map((a) -> a.getUsername()).collect(Collectors.toList()) + ",\n" +
                "           likes=" +  this.likes.stream().map((a) -> a.getUsername()).collect(Collectors.toList()) + ",\n" +
                "         }";
    }
}
