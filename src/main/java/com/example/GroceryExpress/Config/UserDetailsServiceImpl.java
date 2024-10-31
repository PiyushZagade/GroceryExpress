package com.example.GroceryExpress.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.GroceryExpress.Entity.User;
import com.example.GroceryExpress.Repository.MyRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private MyRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =repo.getUserByName(username);
        if(user==null){
            throw new UsernameNotFoundException("Could not found : " +username);
        }

        CustomUserDetails customUserDetails=new CustomUserDetails(user);
        return customUserDetails;
    }
    
}
