package com.example.GroceryExpress.Controller;

import org.springframework.core.io.Resource;

import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageController {
    private ResourceLoader resourceLoader;

    public ImageController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @GetMapping("/images/{filename}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        Resource resource = resourceLoader.getResource("classpath:static/images/" + filename);
        
        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "image/jpeg");  // Change MIME type as needed

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}
