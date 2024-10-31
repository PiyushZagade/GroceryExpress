package com.example.GroceryExpress.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.GroceryExpress.Entity.User;



@Repository
public interface MyRepository extends JpaRepository<User,Integer>{

    @Query("select u from User u where u.email=:email")
    public User getUserByName(@Param("email") String email);
} 
