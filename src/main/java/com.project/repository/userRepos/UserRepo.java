package com.project.repository.userRepos;

import com.project.model.user.User;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserRepo extends AbstractBaseUserRepo<User> {

}
