package com.example.easyneedsaoop;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class RentCardDesign implements Initializable {

    @FXML
    private AnchorPane cardForm;

    @FXML
    private ImageView houseImage;

    @FXML
    private Label houseName;

    @FXML
    private Label location;

    @FXML
    private Label rating;

    @FXML
    private Label rent;

    @FXML
    private Label userName;
    private rentData data;
    private Image image;
    public void setData(rentData data){
        this.data=data;
        houseName.setText(data.getHouseName());
        String path="File:"+data.getImage();
        image=new Image(path,253,200,false,true);
        houseImage.setImage(image);
        location.setText(data.getAddress());
        rent.setText(String.valueOf(data.getRent()));
        rating.setText(String.valueOf(3.00));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
