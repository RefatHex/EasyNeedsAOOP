package com.example.easyneedsaoop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
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
    private Button backBtn;
    @FXML
    private GridPane gridPane2;

    @FXML
    private ComboBox<?> sortBox;
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    public String[] sortOption={"Relevance","Z to A","Newest First","Oldest First"};
    private String selectedType = "";
    public void refreshBtnAction(){
        selectedType="";
        displayInstructorCardsForUsernames();
    }



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
        selectedType = type;
        displayInstructorCardsForUsernames();
    }
    public void displayInstructorCardsForUsernames() {
        gridPane2.getChildren().clear();

        List<String> distinctUsernames = getDistinctUsernames();

        int row = 0;
        int column = 0;

        for (String username : distinctUsernames) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("InstructorCard.fxml"));
                AnchorPane pane = loader.load();
                InstructorCard cardController = loader.getController();

                // Set the username and selected type as parameters to setData method
                cardController.setName(username, selectedType);

                // Add margins to create space between cards
                Insets margin = new Insets(10);
                GridPane.setMargin(pane, margin);
                gridPane2.add(pane, column, row++);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Method to retrieve distinct usernames from the courseinfo table
    private List<String> getDistinctUsernames() {
        String sql = "SELECT DISTINCT userName FROM courseinfo";
        List<String> usernames = new ArrayList<>();

        connect = database.connectDB();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                usernames.add(result.getString("userName"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usernames;
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
        displayInstructorCardsForUsernames();
    }
    public void backBtnAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FrontPage.fxml"));
        Stage stage=new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
        backBtn.getScene().getWindow().hide();
    }
    public void handleUserBtnAction() throws IOException {
        OpenUserDetails details=new OpenUserDetails();
        details.show();
    }
}