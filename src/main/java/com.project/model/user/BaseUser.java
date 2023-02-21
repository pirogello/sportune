package com.project.model.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import com.project.model.Publication;
import com.project.model.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public abstract class BaseUser {
    protected UUID id;
   protected String username;
   protected String info;
   protected String password;
   protected Role role;
   List<BaseUser> followers = new ArrayList<>();
   List<BaseUser> following = new ArrayList<>();
   List<Publication> publications = new ArrayList<>();
   List<Publication> likedPublication = new ArrayList<>();
   List<Publication> jointPublication = new ArrayList<>();

   protected BaseUser(UUID id, String username, String info, String password){
       this.id = id;
       this.username = username;
       this.info = info;
       this.password = password;
   }

   public void addPublication(Publication publication){
       this.publications.add(publication);
   }
    public void likePublication(Publication publication){
        this.likedPublication.add(publication);
    }

    public void proposeJointPublication(Publication publication, BaseUser user){
       if(this.publications.contains(publication))
           user.jointPublication.add(publication);
    }

    public void joinToPublication(Publication publication){
        if(this.jointPublication.contains(publication)){
            this.jointPublication.remove(publication);
            this.publications.add(publication);
            publication.addAuthor(this);
        }
    }

    public void followToUser(BaseUser user){
       this.following.add(user);
       user.getFollowers().add(this);
    }
}
