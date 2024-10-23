package com.example.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.demo.Entities.User;
import com.example.demo.Repository.MyRepository;

@Service
public class service {
    
    @Autowired
    MyRepository repo;

    public void add(User user){
        // System.out.println("user" + user);
        repo.save(user);
    }
}
