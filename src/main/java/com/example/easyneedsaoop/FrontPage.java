package com.example.easyneedsaoop;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FrontPage {

    @FXML
    private ImageView user_image;

    @FXML
    private Button user_btn;

    @FXML
    private Button cloth_btn_1;

    @FXML
    private Button cloth_btn_2;

    @FXML
    private Button education_btn_1;

    @FXML
    private Button education_btn_2;

    @FXML
    private Button food_btn_1;

    @FXML
    private Button food_btn_2;

    @FXML
    private Button health_btn_1;

    @FXML
    private Button health_btn_2;

    @FXML
    private Button home_anchor_btn;

    @FXML
    private Button home_btn_1;

    @FXML
    private Button home_btn_2;

    @FXML
    private Button order_btn;

    @FXML
    private Button rewards_btn;

    public void homeAnchorBtnAction(){

    }
    public void orderActionBtn(){

    }
    public void homeActionBtn(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Consumer_rent.fxml"));
        Stage stage=new Stage();
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        home_btn_1.getScene().getWindow().hide();
    }
    public void foodActionBtn(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Consumer_food.fxml"));
        Stage stage=new Stage();
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        food_btn_1.getScene().getWindow().hide();
    }
    public void educationActionBtn(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Consumer_education.fxml"));
        Stage stage=new Stage();
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        education_btn_1.getScene().getWindow().hide();
    }
    public void healthActionBtn(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Consumer_health.fxml"));
        Stage stage=new Stage();
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        education_btn_1.getScene().getWindow().hide();
    }
    public void clothActionBtn(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Consumer_cloth.fxml"));
        Stage stage=new Stage();
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        education_btn_1.getScene().getWindow().hide();
    }
    public void rewardsActionBtn(){

    }

}
