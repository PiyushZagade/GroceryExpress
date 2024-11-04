package com.example.GroceryExpress.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.GroceryExpress.Entity.User;
import com.example.GroceryExpress.Repository.MyRepository;



@Service
public class userservice {
    
    @Autowired
    MyRepository repo;

    public void add(User user){
        // System.out.println("user" + user);
        repo.save(user);
    }

    public boolean isEmailRegistered(String email) {
        return repo.findByEmail(email).isPresent();
    }

    public boolean isPhonenoRegistered(String phone){
        List<User>  users = repo.findByPhoneno(phone);
        return !users.isEmpty();
    }
}
