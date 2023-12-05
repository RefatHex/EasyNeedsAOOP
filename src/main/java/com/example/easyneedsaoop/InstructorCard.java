package com.example.easyneedsaoop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class InstructorCard {

    @FXML
    private GridPane gridPane;

    @FXML
    private Label name;
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    public void setName(String name) {
        this.name.setText(name);
        displayCourseCards(name);
    }

    public void displayCourseCards(String name) {
        gridPane.getChildren().clear();


        ObservableList<CourseData> courseDataList = getCourseData(name);

        int row = 0;
        int column = 0;

        for (CourseData courseData : courseDataList) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("InstructorCardUp.fxml"));
                AnchorPane pane = loader.load();
                InstructorCardUp cardController = loader.getController();
                cardController.setData(courseData);
                pane.setOnMouseClicked(event -> handleCardClick(cardController.getData()));



                // Add margins to create space between cards
                Insets margin = new Insets(10);
                GridPane.setMargin(pane, margin);
                gridPane.add(pane, column++, row);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void handleCardClick(CourseData clickedData) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InstructorOrderPage.fxml"));
            AnchorPane pane = loader.load();

            InstructorOrderPage orderPage = loader.getController();
            orderPage.setData(clickedData);


            // Create a new stage and set the scene
            Stage stage = new Stage();
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(pane));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve course data from the courseinfo table
    private ObservableList<CourseData> getCourseData(String username) {
        String sql = "SELECT * FROM courseinfo WHERE userName = ?";
        ObservableList<CourseData> courseDataList = FXCollections.observableArrayList();

        connect = database.connectDB();
        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, username);
            result = prepare.executeQuery();

            CourseData courseDetails;
            while (result.next()) {
                courseDetails = new CourseData(
                        result.getInt("courseID"),
                        result.getString("courseName"),
                        result.getDouble("price"),
                        result.getString("instructorName"),
                        result.getString("userName"),
                        result.getString("description"),
                        result.getString("image"),
                        result.getString("info"),
                        result.getString("category"),
                        result.getString("type"),
                        result.getDate("date")
                );
                courseDataList.add(courseDetails);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courseDataList;
    }


}
