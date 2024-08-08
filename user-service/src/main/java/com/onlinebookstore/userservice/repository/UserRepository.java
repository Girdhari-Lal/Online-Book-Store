package com.onlinebookstore.userservice.repository;

import com.onlinebookstore.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Modifying
    @Transactional
    @Query("UPDATE User u set u.active = false where u.username = :username and u.password = :password")
    void deactivateUser(@Param("username") String username, @Param("password") String password);

    List<User> findByActiveTrue();
}