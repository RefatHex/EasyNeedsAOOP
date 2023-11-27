package com.example.easyneedsaoop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class Consumer_Education_Controller implements Initializable {

    @FXML
    private GridPane gridPane1;

    @FXML
    private GridPane gridPane2;

    @FXML
    private ComboBox<?> sortBox;
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    public String[] sortOption={"Relevance","A to Z","Z to A","Newest First","Oldest First"};




    public void addCategoriesToGridPane() {
        gridPane1.getChildren().clear();

        Set<String> uniqueCategories = getUniqueTypes();

        int column = 0;
        int row = 0;

        for (String category : uniqueCategories) {
            Hyperlink hyperlink = new Hyperlink(category);

            hyperlink.setOnAction(event -> handleTypesClick(category));

            gridPane1.add(hyperlink, column, row++);

        }
    }

    private Set<String> getUniqueTypes() {
        String sql = "SELECT DISTINCT type FROM courseinfo";
        Set<String> types = new HashSet<>();

        try{
             connect = database.connectDB();
             prepare = connect.prepareStatement(sql);
             result = prepare.executeQuery();

            while (result.next()) {
                types.add(result.getString("type"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return types;
    }

    
    private void handleTypesClick(String type) {
        System.out.println("Clicked on category: " + type);
    }

    public void displayCourseCards() {
        gridPane2.getChildren().clear();


        ObservableList<CourseData> courseDataList = getCourseData();

        int row = 0;
        int column = 0;

        for (CourseData courseData : courseDataList) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("InstructorCardDown.fxml"));
                AnchorPane pane = loader.load();
                InstructorCardDown cardController = loader.getController();
                cardController.setData(courseData);
                pane.setOnMouseClicked(event -> handleCardClick(cardController.getData()));


                if (column == 4) {
                    column = 0;
                    row += 1;
                }
                // Add margins to create space between cards
                Insets margin = new Insets(10);
                GridPane.setMargin(pane, margin);
                gridPane2.add(pane, column++, row);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void handleCardClick(CourseData clickedData) {
        System.out.println(clickedData.getCourseID());
    }

    // Method to retrieve course data from the courseinfo table
    private ObservableList<CourseData> getCourseData() {
        String sql = "SELECT * FROM courseinfo";
        ObservableList<CourseData> courseDataList = FXCollections.observableArrayList();

        connect = database.connectDB();
        try {
            prepare = connect.prepareStatement(sql);
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

    public void optionAdder(){
        List<String> sortType = new ArrayList<>(Arrays.asList(sortOption));
        ObservableList sortData= FXCollections.observableArrayList(sortType);
        sortBox.setItems(sortData);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        optionAdder();
        addCategoriesToGridPane();
        displayCourseCards();
    }
}