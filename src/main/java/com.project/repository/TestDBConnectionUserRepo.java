package com.project.repository;


import com.project.model.user.TestDBConnectionUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestDBConnectionUserRepo extends JpaRepository<TestDBConnectionUser, Integer> {

    public TestDBConnectionUser findByUsername(String username);
}
