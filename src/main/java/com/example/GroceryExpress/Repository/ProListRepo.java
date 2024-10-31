package com.example.GroceryExpress.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.GroceryExpress.Entity.ProList;
import com.example.GroceryExpress.Entity.User;



@Repository
public interface ProListRepo extends JpaRepository<ProList,Integer>{
    List<ProList> findByUser(User user); 
}
