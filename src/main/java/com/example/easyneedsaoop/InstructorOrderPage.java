package com.example.easyneedsaoop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class InstructorOrderPage {

    @FXML
    private Button backBtn;

    @FXML
    private Button bookBtn;

    @FXML
    private Button chatBtn;

    @FXML
    private CheckBox check1;

    @FXML
    private CheckBox check2;

    @FXML
    private Label courseName;

    @FXML
    private Label locationLabel;

    @FXML
    private Label price;

    @FXML
    private Label userNameLabel;

    @FXML
    private ImageView viwer;
    private Image image;
    private CourseData data;
    private Alert alert;

    public CourseData getData() {
        return data;
    }

    public void setData(CourseData data) {
        this.data=data;
        userNameLabel.setText(data.getUserName());
        courseName.setText(data.getCourseName());
        locationLabel.setText(data.getDescription());
        String path = "File:" + data.getImage();
        image = new Image(path, 626, 274, false, true);
        viwer.setImage(image);
        price.setText(String.valueOf(data.getPrice()));
    }

    public void backBtnAction(ActionEvent event) {
        backBtn.getScene().getWindow().hide();
    }


    public void handleBookingClick(ActionEvent event) {
        if (isAlreadyEnrolled(data.getCourseID(), com.example.easyneedsaoop.data.username)) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Already enrolled in this course");
            alert.showAndWait();
            return;
        }
        String insertSql = "INSERT INTO enrollDB (cardID,OwnerName,userName,date) VALUES ( ?, ?, ?, NOW())";
        Connection connect = database.connectDB();

        try (PreparedStatement preparedStatement = connect.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, data.getCourseID());
            preparedStatement.setString(2, data.getInstructorName());
            preparedStatement.setString(3, com.example.easyneedsaoop.data.username);
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
//                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MoneyTransferStage.fxml"));
//                        Stage stage = new Stage();
//                        //stage.initStyle(StageStyle.UNDECORATED);
//                        Scene scene = new Scene(fxmlLoader.load());
//                        stage.setScene(scene);
//                        //stage.initStyle(StageStyle.UNDECORATED);
//                        stage.setTitle("Easy Pay");
//                        stage.show();
//                        //System.out.println("Course enrolled successfully! Enroll ID: " + generatedKeys.getInt(1));
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Congratulations");
                        alert.setHeaderText(null);
                        alert.setContentText("Course enrolled successfully! Enroll ID: " + generatedKeys.getInt(1));
                        alert.showAndWait();
                    } else {
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Unable to Enroll");
                        alert.showAndWait();

                    }
                }
            } else {
                System.out.println("Failed to place appointment.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Method to check if the user is already enrolled
    private boolean isAlreadyEnrolled(int cardID, String userName) {
        String selectSql = "SELECT * FROM enrollDB WHERE cardID = ? AND userName = ?";
        Connection connect = database.connectDB();

        try (PreparedStatement preparedStatement = connect.prepareStatement(selectSql)) {
            preparedStatement.setInt(1, cardID);
            preparedStatement.setString(2, userName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    public void chatBtnHandler() throws IOException {
        ChatBoxOpenner c=new ChatBoxOpenner();
        c.chatBtnHandler(data.getUserName());
    }
}