package com.example.GroceryExpress.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.GroceryExpress.Entity.ProList;
import com.example.GroceryExpress.Entity.User;
import com.example.GroceryExpress.Repository.ProListRepo;

import java.util.List;

@Service
public class ProListService {

    @Autowired
    ProListRepo proListRepo;

    public void savepro(ProList prolist){
        proListRepo.save(prolist);
    }

    public List<ProList> AllProd(){
        return proListRepo.findAll();
    }

    public ProList getProListById(int id) {
        return proListRepo.findById(id).orElse(null);
    }

    public List<ProList> getProductsForUser(User user) {
        return proListRepo.findByUser(user);
    }
    
    public void deletemybook(int id){
        ProList list = proListRepo.findById(id).get();
        proListRepo.delete(list);
    }

    public long countProducts() {
        return proListRepo.count(); 
    }


    public int calculateGrandTotal(List<ProList> products) {
        int grandTotal = 0;
        for (ProList product : products) {
            String priceStr = product.getPrice();
            String numericPriceStr = priceStr.replace("Rs", "").trim();
            try {
                int price = Integer.parseInt(numericPriceStr);
                grandTotal += price;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        // Return the grand total as a formatted string
        return grandTotal;
    }


    public String calculateTotalForProduct(ProList product) {
        String priceStr = product.getPrice(); 
        int quantity = product.getQuantity();

       
        String numericPriceStr = priceStr.replace("Rs", "").trim();
        try {
            int price = Integer.parseInt(numericPriceStr);  
            int total = price * quantity;  // Multiply by quantity

            // Return the total as a formatted string, e.g., "Rs 200"
            return "Rs "+total;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return priceStr;  // In case of error, return the original price string
        }
    }
    public void increaseQuantity(int id) {
        ProList product = proListRepo.findById(id).orElse(null);
        if (product != null) {
            product.setQuantity(product.getQuantity() + 1);  // Increment quantity
            proListRepo.save(product);
        }
    }

    public void decreaseQuantity(int id) {
        ProList product = proListRepo.findById(id).orElse(null);
        if (product != null && product.getQuantity() > 1) {
            product.setQuantity(product.getQuantity() - 1);  // Decrement quantity
            proListRepo.save(product);
        }
    }

}
