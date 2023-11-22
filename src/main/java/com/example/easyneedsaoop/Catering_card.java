package com.example.easyneedsaoop;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Catering_card {

    @FXML
    private Label delivery;

    @FXML
    private ImageView imageview;


    @FXML
    private Label name;

    @FXML
    private Label price;
    private CateringData data;

    public CateringData getData() {
        return data;
    }
    private Image image;

    public void setData(CateringData data){
        this.data=data;
        name.setText(data.shopName);
        String path="File:"+data.getImage();
        image=new Image(path,249,152,false,true);
        imageview.setImage(image);
        price.setText(String.valueOf(data.getPrice()));

        delivery.setText(data.getMealDelivery());
    }

}
