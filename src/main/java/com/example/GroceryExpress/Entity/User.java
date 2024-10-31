package com.example.GroceryExpress.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity 
@Data
@Table(name="DataUser")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name cannot be empty")
    @Size(min=3,max=30,message = "Size should be between 3-30")
    private String name;

    @NotBlank(message="email cannot be empty")
    @Email(regexp = "^[A-Za-z0-9]+@[a-z]+\\.[A-zZa-z]{2,6}+$")
    private String email;

    @NotBlank(message="Phoneno cannot be empty")
    @Size(min=10,max=12,message = "Enter Valid number")
    private String phoneno;

    @NotBlank(message="City cannot be empty")
    private String city;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 3, message="Minimum length of password should be 3")
    private String password;

    @NotBlank(message = "Please select role")
    private String role;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products =new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProList> proLists = new ArrayList<>();
}
