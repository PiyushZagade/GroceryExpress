package com.example.demo.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entities.ProList;
import com.example.demo.Entities.User;

@Repository
public interface ProListRepo extends JpaRepository<ProList,Integer>{
    List<ProList> findByUser(User user); 
}
