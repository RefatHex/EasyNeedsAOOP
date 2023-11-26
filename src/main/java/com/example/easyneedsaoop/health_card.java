package com.example.easyneedsaoop;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class health_card {

    @FXML
    private Label fee;

    @FXML
    private Label hsopital;

    @FXML
    private ImageView image;

    @FXML
    private Label name;

    @FXML
    private Label specialize;
    private Image imagee;

    private HealthData data;



    public void setData(HealthData data){
        this.data=data;
        fee.setText(String.valueOf(data.getFee()));
        name.setText(data.getDocName());
        specialize.setText(data.getService());
        hsopital.setText(data.getHospital());
        String path="File:"+data.getImage();
        imagee=new Image(path,249,152,false,true);
        image.setImage(imagee);
    }
    public HealthData getData() {
        return data;
    }
}
