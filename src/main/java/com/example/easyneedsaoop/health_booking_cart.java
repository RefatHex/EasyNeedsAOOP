package com.example.easyneedsaoop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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

    Alert alert;

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

    public void backBtnAction(){
        backBtn.getScene().getWindow().hide();
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
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MoneyTransferStage.fxml"));
                        Stage stage = new Stage();
                        //stage.initStyle(StageStyle.UNDECORATED);
                        Scene scene = new Scene(fxmlLoader.load());
                        stage.setScene(scene);
                        //stage.initStyle(StageStyle.UNDECORATED);
                        stage.setTitle("Easy Pay");
                        stage.show();
                        System.out.println("Appointment placed successfully! Appointment ID: " + generatedKeys.getInt(1));
                    } else {
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Unable to fix appointment");
                        alert.showAndWait();
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
