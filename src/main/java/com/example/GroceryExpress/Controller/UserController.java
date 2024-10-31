package com.example.GroceryExpress.Controller;

import java.io.File;
import java.nio.file.Files; // nio file are manually imported
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.security.Principal;
import java.util.List;
import com.example.GroceryExpress.Entity.ProList;
import com.example.GroceryExpress.Entity.Product;
import com.example.GroceryExpress.Entity.User;
import com.example.GroceryExpress.Repository.MyRepository;
import com.example.GroceryExpress.Repository.Productrepo;
import com.example.GroceryExpress.Services.ProListService;
import com.example.GroceryExpress.Services.Prodservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    MyRepository repo;

    @Autowired
    Productrepo productrepo;

    @Autowired
    ProListService proListService;

    @Autowired
    Prodservice prodservice;

    @RequestMapping("/index")
    public String dashboard(Model model, Principal principal) {

        String name = principal.getName();
        User user = repo.getUserByName(name);
        model.addAttribute("op", user);
        List<Product> products = prodservice.getAllProducts();
        model.addAttribute("products", products);

        List<ProList> proLists = proListService.getProductsForUser(user);
        long countb = proLists.size();
        model.addAttribute("mcount", countb);

        return "dashboard";
    }

    @GetMapping("/addproduct")
    public String getaddproduct(Model model) {
        model.addAttribute("prod", new Product());
        return "addproduct";
    }

    @PostMapping("/processproduct")
    public String addproduct(@ModelAttribute Product product, @RequestParam("pimage") MultipartFile file,
            Principal principal, RedirectAttributes redirectAttributes) {

        try {

            if (!file.isEmpty()) {
                product.setImage(file.getOriginalFilename());
                File savefile = new ClassPathResource("static/images").getFile();

                Path path = Paths.get(savefile.getAbsolutePath() + File.separator + file.getOriginalFilename());
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING); // for this manually need
                                                                                              // to import autocomplete
                                                                                              // doesnt work

            }

            String username = principal.getName();
            User user = this.repo.getUserByName(username);
            product.setUser(user); // remember imp step before saving image

            user.getProducts().add(product);
            this.repo.save(user);

            redirectAttributes.addFlashAttribute("saved", "Product Added Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("err", e.getMessage());
        }

        return "redirect:/user/addproduct";
    }

    @GetMapping("/mybook")
    public String getMyBook(Model model, Principal principal) {

        String username = principal.getName();
        User usern = this.repo.getUserByName(username);
        model.addAttribute("user", usern);

        List<ProList> proLists = proListService.getProductsForUser(usern);
        

        for (ProList prod : proLists) {
            String totalForProduct = proListService.calculateTotalForProduct(prod);
            prod.setPrice(totalForProduct);  // Set the new formatted total price
        }
        int grandTotal = proListService.calculateGrandTotal(proLists);
        model.addAttribute("total", grandTotal);
        model.addAttribute("prods", proLists);
        return "mybook";
    }

    @PostMapping("/addpro/{id}")
    public String saveProlist(@PathVariable("id") int id,Principal principal) {
        String username = principal.getName();
        User user = this.repo.getUserByName(username);
        Product product = prodservice.getProductById(id);
        int quantity=1;
        ProList prolist = new ProList(product.getId(), product.getName(), product.getType(), product.getWeight(),
                product.getPrice(),quantity,user);
    
        proListService.savepro(prolist);
        return "redirect:/user/index";
    }

    @PostMapping("/addtohome/{id}")
    public String savehome(@PathVariable("id") int id, Model model, Principal principal) {
        String username = principal.getName();
        User user = this.repo.getUserByName(username);
        Product product = prodservice.getProductById(id);
        int quantity=1;
        ProList prolist = new ProList(product.getId(), product.getName(), product.getType(), product.getWeight(),
                product.getPrice(),quantity,user);
        proListService.savepro(prolist);
        return "redirect:/";
    }

    @PostMapping("/delpro/{id}")
    public String deleteproduct(@PathVariable("id") int id) {
        prodservice.deleteproductId(id);
        return "redirect:/user/index";
    }

    @PostMapping("/updpro/{id}")
    public String getupdate(@PathVariable("id") int id, Model model) {
        Product product = productrepo.findById(id).get();
        if (product != null) {
            model.addAttribute("expro", product);
        }

        return "update";
    }

    @PostMapping("/updatefo/{id}")
    public String updateprod(@PathVariable("id") int id, @ModelAttribute Product prod,
            @RequestParam("pimage") MultipartFile file) {

        try {

            if (!file.isEmpty()) {
                prod.setImage(file.getOriginalFilename());
                File saveFile = new ClassPathResource("static/images").getFile();
                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + file.getOriginalFilename());
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            } else {
                Product existpro = prodservice.getProductById(id);
                prod.setImage(existpro.getImage());
            }

            prodservice.updateProd(id, prod);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/user/index";
    }

    @PostMapping("delmybook/{id}")
    public String postMethodName(@PathVariable("id") int id) {
        proListService.deletemybook(id);
        return "redirect:/user/mybook";
    }

    @PostMapping("/increaseQuantity/{id}")
    public String increaseQuantity(@PathVariable("id") int id) {
        proListService.increaseQuantity(id);
        return "redirect:/user/mybook";  // Redirect to mybook
    }

    @PostMapping("/decreaseQuantity/{id}")
    public String decreaseQuantity(@PathVariable("id") int id) {
        proListService.decreaseQuantity(id);
        return "redirect:/user/mybook";  // Redirect to mybook
    }
}
