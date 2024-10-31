package com.example.GroceryExpress.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.security.Principal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.GroceryExpress.Entity.ProList;
import com.example.GroceryExpress.Entity.Product;
import com.example.GroceryExpress.Entity.User;
import com.example.GroceryExpress.Repository.MyRepository;
import com.example.GroceryExpress.Repository.Productrepo;
import com.example.GroceryExpress.Services.ProListService;
import com.example.GroceryExpress.Services.Prodservice;
import com.example.GroceryExpress.helper.Message;
import com.example.GroceryExpress.Services.service;


import jakarta.validation.Valid;



@Controller
public class Control {

    @Autowired
    service service;

    @Autowired
    Productrepo productrepo;

     @Autowired
    MyRepository repo;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ProListService proListService;

    @Autowired
    private Prodservice  prodservice;
    @GetMapping("/")
    public String getHome(Model model,Principal principal) {
        List<Product> products = prodservice.getAllProducts();
        model.addAttribute("products", products);

        if(principal!=null){
            String username = principal.getName();
        User user = this.repo.getUserByName(username);
        

         List<ProList> proLists = proListService.getProductsForUser(user);
        long countb = proLists.size();
        model.addAttribute("mcount", countb);
        }else{
            model.addAttribute("mcount", 0);
        }
        
        return "home";
    }

    @GetMapping("/register")
    public String getregister(Model model) {
        model.addAttribute("log", new User());
        return "register";
    }

    @PostMapping("/process")
    public String postprocess(@Valid @ModelAttribute("log") User user, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "register";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        service.add(user);
        redirectAttributes.addFlashAttribute("message", new Message("Registered Successfully", "succ"));
        return "redirect:/register";

    }

    @GetMapping("/signin")
    public String getlogin() {
        return "login";
    }
    
    @GetMapping("/productd/{id}")
    public String getdetails(@PathVariable("id") int id, Model model) {
        Optional<Product> optionalproducts = prodservice.getById(id);
        Product product = optionalproducts.get();
        model.addAttribute("product", product);
        return "details";
    }
    

}
