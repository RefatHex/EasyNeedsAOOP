package com.example.easyneedsaoop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class Cloth_RecentCard implements Initializable {

    @FXML
    private ImageView image;

    @FXML
    private Label price;
    @FXML
    private Label prodName;


    @FXML
    private TextField quantity_box;

    @FXML
    private Label shopname;
    @FXML
    private Button cartBtn;

    @FXML
    private TextField size;
    private ClothShopData data;



    private Image imagee;
    @FXML
    private Label id;
    private String sellerUsername;
    public void setData(ClothShopData data){
        this.data=data;
        sellerUsername=data.getSellerUsername();

        id.setText(String.valueOf(data.getProductID()));
        String path="File:"+data.getImage();
        imagee=new Image(path,249,152,false,true);
        image.setImage(imagee);
        shopname.setText(data.getShopName());
        price.setText(String.valueOf(data.getPrice()));
        prodName.setText(data.getProdName());
    }
    public void handleEvent(ActionEvent e){
        if(e.getSource()== cartBtn){

        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
