package com.example.demo.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name="Prolist")
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
