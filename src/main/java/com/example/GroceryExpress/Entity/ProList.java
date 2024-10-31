package com.example.GroceryExpress.Entity;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.RequiredArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name="DataProlist")
public class ProList {
    
     @Id
    private int id;
    private String name;
    private String type;
    private String weight;
    private String price;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
}
