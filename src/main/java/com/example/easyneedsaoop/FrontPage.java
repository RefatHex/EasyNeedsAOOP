package com.example.easyneedsaoop;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Optional;

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
    private AnchorPane chat_form;
    @FXML
    private AnchorPane command_form;
    @FXML
    private AnchorPane order_form;
    @FXML
    private AnchorPane homeAnchor;

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
    private Button logoutBtn;

    @FXML
    private Button rewards_btn;
    private Alert alert ;

    public void homeAnchorBtnAction(){
        homeAnchor.setVisible(true);
        command_form.setVisible(true);
        order_form.setVisible(false);
        chat_form.setVisible(false);
    }
    public void orderActionBtn(){
        homeAnchor.setVisible(false);
        command_form.setVisible(true);
        order_form.setVisible(true);
        chat_form.setVisible(false);
    }
    public void msgBtnAction(){
        homeAnchor.setVisible(false);
        command_form.setVisible(true);
        order_form.setVisible(false);
        chat_form.setVisible(true);
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
        //stage.initStyle(StageStyle.UNDECORATED);
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
        //stage.initStyle(StageStyle.UNDECORATED);
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
        //stage.initStyle(StageStyle.UNDECORATED);
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
        //stage.initStyle(StageStyle.UNDECORATED);
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
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        education_btn_1.getScene().getWindow().hide();
    }
    public void rewardsActionBtn(){

    }
    public void logout() {
        try {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                logoutBtn.getScene().getWindow().hide();
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("loginsignup.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(fxmlLoader.load());
                stage.setTitle("EasyNeeds");
                //stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void handleUserBtnAction() throws IOException {
        OpenUserDetails details=new OpenUserDetails();
        details.show();
    }

}
