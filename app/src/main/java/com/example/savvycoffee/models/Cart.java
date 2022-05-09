package com.example.savvycoffee.models;

public class Cart {
    String productName;
    String quantity;
    String portion;
    String netTotal;

    public Cart(String productName, String quantity, String portion, String netTotal) {
        this.productName = productName;
        this.quantity = quantity;
        this.portion = portion;
        this.netTotal = netTotal;
    }

    public Cart() {

    }

    public String getProductName() {
        return productName;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getPortion() {
        return portion;
    }

    public String getNetTotal() {
        return netTotal;
    }
}
