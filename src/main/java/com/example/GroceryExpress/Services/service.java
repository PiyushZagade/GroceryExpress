package com.example.GroceryExpress.Services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.GroceryExpress.Entity.User;
import com.example.GroceryExpress.Repository.MyRepository;



@Service
public class service {
    
    @Autowired
    MyRepository repo;

    public void add(User user){
        // System.out.println("user" + user);
        repo.save(user);
    }
}
