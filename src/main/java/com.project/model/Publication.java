package com.project.model;

import lombok.Data;
import lombok.Getter;
import com.project.model.user.BaseUser;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@Data
@Table(name = "publication")
public class Publication {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    private String title;
    @ManyToMany(mappedBy = "publications")
    private List<BaseUser> authors = new ArrayList<>();
    @ManyToMany(mappedBy = "likedPublication")
    private List<BaseUser> likes = new ArrayList<>();
    @ManyToMany(mappedBy = "jointPublication")
    private List<BaseUser> joint = new ArrayList<>();

//    public Publication(String title, List<BaseUser> authors) {
//        this.title = title;
//        this.authors = authors;
//        for (BaseUser author : authors) {
//            author.addPublication(this);
//        }
//    }
    public Publication(String title) {
        this.title = title;

    }
    public void addAuthor(BaseUser user){
        this.authors.add(user);
    }
    public void addAuthors(List<BaseUser> authors){
        this.authors = authors;
        for (BaseUser author : authors) {
            author.addPublication(this);
        }
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
