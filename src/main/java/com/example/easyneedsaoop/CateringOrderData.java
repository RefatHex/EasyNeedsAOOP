package com.example.easyneedsaoop;

import java.util.Date;

public class CateringOrderData {
    private  int orderId;
            private int listingId;

       private String  buyerUserName;
   private Double  price;

   private Date date;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getListingId() {
        return listingId;
    }

    public void setListingId(int listingId) {
        this.listingId = listingId;
    }

    public String getBuyerUserName() {
        return buyerUserName;
    }

    public void setBuyerUserName(String buyerUserName) {
        this.buyerUserName = buyerUserName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CateringOrderData(int orderId, int listingId, String buyerUserName, Double price, Date date) {
        this.orderId = orderId;
        this.listingId = listingId;
        this.buyerUserName = buyerUserName;
        this.price = price;
        this.date = date;
    }
}
