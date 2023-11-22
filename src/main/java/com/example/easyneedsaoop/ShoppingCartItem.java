package com.example.easyneedsaoop;

public class ShoppingCartItem {
    private String productName;
    private double price;
    private int quantity;
    private String size;

    public ShoppingCartItem(String productName, double price, int quantity, String size) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.size = size;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
