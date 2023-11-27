package com.example.easyneedsaoop;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class InstructorCardDown {

    @FXML
    private Label category;

    @FXML
    private ImageView image;

    @FXML
    private Label name;

    @FXML
    private Label price;

    @FXML
    private Label type;
    private CourseData data;

    public CourseData getData() {
        return data;
    }

    private  Image imagee;
    public void setData(CourseData data){
        this.data=data;
        name.setText(data.getInstructorName());
        price.setText(String.valueOf(data.getPrice()));
        type.setText(data.getType());
        category.setText(data.getCategory());
        String path="File:"+data.getImage();
        imagee=new Image(path,249,152,false,true);
        image.setImage(imagee);
    }

}
