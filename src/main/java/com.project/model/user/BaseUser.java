package com.project.model.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.model.Publication;
import com.project.model.Role;
import com.project.model.Status;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "usr")
@Inheritance(
        strategy = InheritanceType.JOINED
)
public abstract class BaseUser {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
    protected UUID id;
    //@Column(unique = true, nullable = false)
    @CreatedDate
    private Date created;
    @LastModifiedDate
    private Date updated;
    private String firstName;
    private String lastName;
    private String email;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(nullable = false, unique = true)
    protected String username;
    protected String info;
    @Column(nullable = false)
    protected String password;
    @Enumerated(EnumType.STRING)
    protected Role role;
    @ManyToMany
    @JsonIgnoreProperties({"followers", "following", "publications", "likedPublication", "jointPublication", "trainee"})
    @JoinTable(name = "user_followers", joinColumns = {@JoinColumn(name = "follower_id")}, inverseJoinColumns = {@JoinColumn(name = "user_id")})
    List<BaseUser> following = new ArrayList<>();

    @ManyToMany
    @JsonIgnoreProperties({"followers", "following", "publications", "likedPublication", "jointPublication"})
    @JoinTable(
            name = "user_followers",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "follower_id")}
    )
    List<BaseUser> followers = new ArrayList<>();
    @ManyToMany
    @JoinTable(
            name = "user_publication",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "publication_id")}
    )
    List<Publication> publications = new ArrayList<>();
    @ManyToMany
    @JoinTable(
            name = "user_liked_publication",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "publication_id")}
    )
    List<Publication> likedPublication = new ArrayList<>();
    @ManyToMany
    @JoinTable(
            name = "user_joint_publication",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "publication_id")}
    )
    List<Publication> jointPublication = new ArrayList<>();



    protected BaseUser(String username, String info, String password) {
        this.username = username;
        this.info = info;
        this.password = password;
    }

    public void addPublication(Publication publication) {
        this.publications.add(publication);
    }

    public void likePublication(Publication publication) {
        this.likedPublication.add(publication);
    }

    public void proposeJointPublication(Publication publication, BaseUser user) {
        if (this.publications.contains(publication))
            user.jointPublication.add(publication);
    }

    public void joinToPublication(Publication publication) {
        if (this.jointPublication.contains(publication)) {
            this.jointPublication.remove(publication);
            this.publications.add(publication);
        }
    }

    public void followToUser(BaseUser user) {
        this.following.add(user);
    }

}
