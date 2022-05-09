package com.example.savvycoffee.models;

public class Product {
    String title;
    String imageUrl;
    String description;
    String price;

    public Product(String title, String imageUrl, String description, String price) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.description = description;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }
}
