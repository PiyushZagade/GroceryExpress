package com.example.GroceryExpress.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.GroceryExpress.Entity.Product;



@Repository
public interface Productrepo extends JpaRepository<Product,Integer> {

    @Query("from Product as c where c.user.id=:userId")
    public  List<Product> findProductsByUser(@Param("userId") int userId);


    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    public List<Product> findByNameContaining(@Param("name") String name);
    
} 
