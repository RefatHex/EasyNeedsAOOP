package com.example.easyneedsaoop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class health_booking_cart {

    @FXML
    private Button Book;

    @FXML
    private AnchorPane Slider;

    @FXML
    private Button backBtn;

    @FXML
    private ImageView cartImage;

    @FXML
    private Button chatBtn;

    @FXML
    private Label fee;

    @FXML
    private Label finish;

    @FXML
    private Label name;

    @FXML
    private Button payBtn;

    @FXML
    private AnchorPane slider;

    @FXML
    private Label specialized;

    @FXML
    private Label start;


    HealthData healthData;
    private Image imagee;

    public void setHealthData(HealthData healthData){
        this.healthData = healthData;
        name.setText(healthData.getDocName());
        String path="File:"+ healthData.getImage();
        imagee=new Image(path,249,152,false,true);
        cartImage.setImage(imagee);
        start.setText(String.valueOf(healthData.getStart()));
        finish.setText(String.valueOf(healthData.getEnd()));
        specialized.setText(healthData.getService());
        fee.setText(String.valueOf(healthData.getFee()));

    }


    @FXML
    void backBtnAction(ActionEvent event) {

    }


    public void placeAppointment() {
        String insertSql = "INSERT INTO healthAppointment (cardID,docName,userName,date) VALUES ( ?, ?, ?, NOW())";
        Connection connect = database.connectDB();

        try (PreparedStatement preparedStatement = connect.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, healthData.getCardID());
            preparedStatement.setString(2, healthData.getDocName());
            preparedStatement.setString(3, com.example.easyneedsaoop.data.username);
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        System.out.println("Appointment placed successfully! Appointment ID: " + generatedKeys.getInt(1));
                    } else {
                        System.out.println("Failed to retrieve appointment ID.");
                    }
                }
            } else {
                System.out.println("Failed to place appointment.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
