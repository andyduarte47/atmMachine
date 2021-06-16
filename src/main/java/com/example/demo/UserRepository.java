package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends     JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.userID = ?1")
    public User findByuserID(String userID);

}

