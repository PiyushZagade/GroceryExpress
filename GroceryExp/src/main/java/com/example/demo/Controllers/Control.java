package com.example.demo.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.security.Principal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.Entities.ProList;
import com.example.demo.Entities.Product;
import com.example.demo.Entities.User;
import com.example.demo.Repository.MyRepository;
import com.example.demo.Repository.Productrepo;
import com.example.demo.Services.ProListService;
import com.example.demo.Services.Prodservice;
import com.example.demo.Services.service;
import com.example.demo.helper.Message;

import jakarta.validation.Valid;
// import org.springframework.web.bind.annotation.RequestParam;


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
    

}
