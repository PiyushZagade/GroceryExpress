package com.example.GroceryExpress.Services;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
public class Imageservice {

    private  ResourceLoader resourceLoader;

    public void ImageService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public Resource loadImage(String filename) {
        return resourceLoader.getResource("classpath:static/images/" + filename);
    }
}
