package com.example.easyneedsaoop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Cloth_MainCard {

    @FXML
    private ImageView image;

    @FXML
    private Button orderBtn;

    @FXML
    private Label price;

    @FXML
    private Label prodName;

    @FXML
    private TextField quantity;

    @FXML
    private Label shopname;
    @FXML
    private Label id;

    @FXML
    private TextField size;

    private String sellerUsername;
    private ClothShopData data;
    private Image imagee;
    public void setData(ClothShopData data){
        this.data=data;
        id.setText(String.valueOf(data.getProductID()));
        String path="File:"+data.getImage();
        imagee=new Image(path,249,152,false,true);
        image.setImage(imagee);
        sellerUsername=data.getSellerUsername();
        shopname.setText(data.getShopName());
        price.setText(String.valueOf(data.getPrice()));
        prodName.setText(data.getProdName());
    }
    public void handleEvent(ActionEvent e){
        if(e.getSource()==orderBtn){

        }
    }

}
