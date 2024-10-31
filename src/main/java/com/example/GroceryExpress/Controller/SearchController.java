package com.example.GroceryExpress.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.GroceryExpress.Entity.Product;
import com.example.GroceryExpress.Repository.Productrepo;



@RestController
public class SearchController {
    @Autowired
    Productrepo productrepo;

     @GetMapping("/search/{query}")
    public ResponseEntity<?> getsearch(@PathVariable("query") String query) {
        List<Product> products = productrepo.findByNameContaining(query);
        return ResponseEntity.ok(products);
    }
    
}
