package com.example.GroceryExpress.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.GroceryExpress.Entity.Product;
import com.example.GroceryExpress.Repository.Productrepo;



@Service
public class Prodservice {
    @Autowired
    private Productrepo productrepo;

    public Product getProductById(int id){
        return productrepo.findById(id).get();
    }

    public List<Product> getAllProducts(){
        return productrepo.findAll();
    }

    public Optional<Product> getById(int id){
        return productrepo.findById(id);
    }

    public void deleteproductId(int id){
        Product product = productrepo.findById(id).get();
        product.setUser(null);
        productrepo.delete(product);
    }

    public Product updateProd(int id,Product prod){
        Product product = productrepo.findById(id).get();
        product.setName(prod.getName());
        product.setType(prod.getType());
        product.setWeight(prod.getWeight());
        product.setPrice(prod.getPrice());
        product.setImage(prod.getImage());
        return productrepo.save(product);
    }
}
