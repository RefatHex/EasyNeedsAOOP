package com.example.easyneedsaoop;

import java.util.Date;

public class ClothShopData {
    private int productID;

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String sellerUsername;
    private String shopName;
    private String prodName;
    private double price;
    private String image;
    private String category;
    private String type;
    private String einfo;
    private Date date;

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getSellerUsername() {
        return sellerUsername;
    }

    public void setSellerUsername(String sellerUsername) {
        this.sellerUsername = sellerUsername;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEinfo() {
        return einfo;
    }

    public void setEinfo(String einfo) {
        this.einfo = einfo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ClothShopData(int prodID, String username, String shopName, String prodName, double price, String image, String category, String type, String einfo, Date date) {
        this.productID = prodID;
        this.sellerUsername = username;
        this.shopName = shopName;
        this.prodName = prodName;
        this.price = price;
        this.image = image;
        this.category = category;
        this.type = type;
        this.einfo = einfo;
        this.date = date;
    }

    public ClothShopData(int prodID, String shopName,String sellerUsername, String prodName, double price, String image) {
        this.productID = prodID;
        this.sellerUsername=sellerUsername;
        this.shopName = shopName;
        this.prodName = prodName;
        this.price = price;
        this.image = image;
    }

    public ClothShopData(int productID, String username, String sellerUsername, double price, Date date) {
        this.productID = productID;
        this.username = username;
        this.sellerUsername = sellerUsername;
        this.price = price;
        this.date = date;
    }

    @Override
    public String toString() {
        return "ClothShopData{" +
                "productID=" + productID +
                ", username='" + sellerUsername + '\'' +
                ", shopName='" + shopName + '\'' +
                ", prodName='" + prodName + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", category='" + category + '\'' +
                ", type='" + type + '\'' +
                ", einfo='" + einfo + '\'' +
                ", date=" + date +
                '}';
    }
}
