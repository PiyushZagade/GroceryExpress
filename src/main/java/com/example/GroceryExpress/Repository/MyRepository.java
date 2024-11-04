package com.example.GroceryExpress.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.GroceryExpress.Entity.User;



@Repository
public interface MyRepository extends JpaRepository<User,Integer>{

    @Query("select u from User u where u.email=:email")
    public User getUserByName(@Param("email") String email);

    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.phoneno = :phoneno")
    List<User> findByPhoneno(@Param("phoneno") String phoneno);
} 
