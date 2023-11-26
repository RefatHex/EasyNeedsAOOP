package com.example.easyneedsaoop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Consumer_Health_Controller implements Initializable {

    @FXML
    private Button backBtn;

    @FXML
    private GridPane gridPane;

    @FXML
    private ComboBox<String> locationList;

    @FXML
    private ComboBox<String> nameList;

    @FXML
    private ComboBox<String> serrviceList;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    private Connection connect;


    private ObservableList<HealthData> cardDetails= FXCollections.observableArrayList();

    public ObservableList<HealthData> healthGetData() {
        String sql = "SELECT * FROM `hassisanceinfo`";
        ObservableList<HealthData> listData = FXCollections.observableArrayList();

        connect = database.connectDB();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            HealthData healthData;
            while (result.next()) {
                healthData = new HealthData(
                        result.getInt("cardID"),
                        result.getString("docName"),
                        result.getString("userName"),
                        result.getString("docMajor"),
                        result.getInt("start"),
                        result.getInt("end"),
                        result.getDouble("fee"),
                        result.getString("day"),
                        result.getString("service"),
                        result.getString("location"),
                        result.getDate("date"),
                        result.getString("hospitalName"),
                        result.getString("image")
                );
                listData.add(healthData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public void healthDisplayCard() {
        cardDetails.clear();
        cardDetails.addAll(healthGetData());
        int row = 0;
        int column = 0;
        gridPane.getRowConstraints().clear();
        gridPane.getColumnConstraints().clear();
        for (int i = 0; i < cardDetails.size(); i++) {
            try {
                System.out.println(cardDetails.get(i).toString());
                FXMLLoader loader=new FXMLLoader();
                loader.setLocation(getClass().getResource("Health_MainCard.fxml"));
                AnchorPane pane=loader.load();
                health_card cardR= loader.getController();
                cardR.setData(cardDetails.get(i));
                pane.setOnMouseClicked(event -> handleCardClick(cardR));

                if (column == 4) {
                    column = 0;
                    row += 1;
                }
                Insets margin = new Insets(10);
                GridPane.setMargin(pane, margin);
                gridPane.add(pane, column++, row);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void handleCardClick(health_card card){
        HealthData clickedData = card.getData();
        System.out.println(clickedData.toString());
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("healthCart.fxml"));
            AnchorPane pane = loader.load();

            health_booking_cart healthBookingCart = loader.getController();
            healthBookingCart.setHealthData(clickedData);

            // Create a new stage and set the scene
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(pane));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void populateComboBox(ComboBox<String> comboBox, String columnName) {
        try {
            String query = "SELECT DISTINCT " + columnName + " FROM hassisanceinfo";
                 prepare = connect.prepareStatement(query);
                 result = prepare.executeQuery();

                ObservableList<String> items = FXCollections.observableArrayList();
                while (result.next()) {
                    items.add(result.getString(columnName));
                }
                comboBox.setItems(items);
                comboBox.setEditable(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void populateLocationList() {
        populateComboBox(locationList, "location");
    }

    private void populateNameList() {
        populateComboBox(nameList, "userName");
    }

    private void populateServiceList() {
        populateComboBox(serrviceList, "service");
    }
    public void backBtnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connect=database.connectDB();
        populateLocationList();
        populateNameList();
        populateServiceList();
        healthDisplayCard();
    }
}
