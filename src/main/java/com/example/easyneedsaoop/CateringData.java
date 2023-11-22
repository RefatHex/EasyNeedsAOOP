package com.example.easyneedsaoop;

import java.util.Date;

public class CateringData {
   int id;
   String ownerName;
   String shopName;
   String branchName;
   String userName=data.username;
   String address;
   String contact;
   String  extraInfo;
   String image;
   double price;
   String mealType;
   String billPay;
   String mealDelivery;
   Date date;

   public CateringData(int id, String ownerName, String shopName, String branchName, String userName, String address, String contact, String extraInfo,String image, double price, String mealType, String billPay, String mealDelivery, Date date) {
      this.id = id;
      this.ownerName = ownerName;
      this.shopName = shopName;
      this.branchName = branchName;
      this.userName = userName;
      this.address = address;
      this.contact = contact;
      this.extraInfo = extraInfo;
      this.image=image;
      this.price = price;
      this.mealType = mealType;
      this.billPay = billPay;
      this.mealDelivery = mealDelivery;
      this.date = date;
   }

   public CateringData(int id, String shopName, String userName, String address, String image, double price, String mealType, String mealDelivery) {
      this.id = id;
      this.shopName = shopName;
      this.userName = userName;
      this.address = address;
      this.image = image;
      this.price = price;
      this.mealType = mealType;
      this.mealDelivery = mealDelivery;
   }

   public int getId() {
      return id;
   }

   public String getImage() {
      return image;
   }

   public void setImage(String image) {
      this.image = image;
   }

   public double getPrice() {
      return price;
   }

   public void setPrice(double price) {
      this.price = price;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getOwnerName() {
      return ownerName;
   }

   public void setOwnerName(String ownerName) {
      this.ownerName = ownerName;
   }

   public String getShopName() {
      return shopName;
   }

   public void setShopName(String shopName) {
      this.shopName = shopName;
   }

   public String getBranchName() {
      return branchName;
   }

   public void setBranchName(String branchName) {
      this.branchName = branchName;
   }

   public String getUserName() {
      return userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getContact() {
      return contact;
   }

   public void setContact(String contact) {
      this.contact = contact;
   }

   public String getExtraInfo() {
      return extraInfo;
   }

   public void setExtraInfo(String extraInfo) {
      this.extraInfo = extraInfo;
   }


   public String getMealType() {
      return mealType;
   }

   public void setMealType(String mealType) {
      this.mealType = mealType;
   }

   public String getBillPay() {
      return billPay;
   }

   public void setBillPay(String billPay) {
      this.billPay = billPay;
   }

   public String getMealDelivery() {
      return mealDelivery;
   }

   public void setMealDelivery(String mealDelivery) {
      this.mealDelivery = mealDelivery;
   }

   public Date getDate() {
      return date;
   }

   public void setDate(Date date) {
      this.date = date;
   }

   @Override
   public String toString() {
      return "CateringData{" +
              "id=" + id +
              ", ownerName='" + ownerName + '\'' +
              ", shopName='" + shopName + '\'' +
              ", branchName='" + branchName + '\'' +
              ", userName='" + userName + '\'' +
              ", address='" + address + '\'' +
              ", contact='" + contact + '\'' +
              ", extraInfo='" + extraInfo + '\'' +
              ", image='" + image + '\'' +
              ", price=" + price +
              ", mealType='" + mealType + '\'' +
              ", billPay='" + billPay + '\'' +
              ", mealDelivery='" + mealDelivery + '\'' +
              ", date=" + date +
              '}';
   }
}
