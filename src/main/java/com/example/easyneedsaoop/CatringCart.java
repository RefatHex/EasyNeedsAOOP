package com.example.easyneedsaoop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class CatringCart implements Initializable {

    @FXML
    private Button Book;

    @FXML
    private AnchorPane Slider;

    @FXML
    private ImageView cartImage;

    @FXML
    private Button chatBtn;

    @FXML
    private Label foodName;

    @FXML
    private ComboBox<?> mealType;

    @FXML
    private Button payBtn;

    @FXML
    private ComboBox<?> plans;

    @FXML
    private Label price;

    @FXML
    private Button backBtn;

    @FXML
    private AnchorPane slider;
    public String[] mealTypeOption={"Daily","Weekly","Monthly"};
    public String[] mealPerDayOption={"Breakfast","Breakfast + Lunch","Breakfast+Lunch+Dinner","Lunch + Dinner", "Breakfast + Dinner"};
    CateringData data;
    private Connection connect;
    private Image image;
    private Alert alert;

    public void setData(CateringData clickedData){
        this.data=clickedData;
        foodName.setText(clickedData.getShopName());
        price.setText(String.valueOf(clickedData.getPrice()));
        String path="File:"+clickedData.getImage();
        image=new Image(path,249,152,false,true);
        cartImage.setImage(image);
    }

    public void optionAdder(){
        List<String> mtype = new ArrayList<>(Arrays.asList(mealTypeOption));
        List<String> preDayMeal = new ArrayList<>(Arrays.asList(mealPerDayOption));
        ObservableList mealData= FXCollections.observableArrayList(mtype);
        ObservableList dayData= FXCollections.observableArrayList(preDayMeal);
        mealType.setItems(mealData);
        plans.setItems(dayData);
    }
    public void placeOrder(){
        String insertSql = "INSERT INTO cateringOrder (listingId, package, ownerUserName, buyerUserName, price, mealType, mealPerDay, date) VALUES (?, ?, ?, ?, ?, ?, ?, NOW())";
        connect = database.connectDB();

        try (PreparedStatement preparedStatement = connect.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, String.valueOf(data.getId()));
            preparedStatement.setString(2, data.getUserName());
            preparedStatement.setString(3, data.getUserName());
            preparedStatement.setString(4, com.example.easyneedsaoop.data.username);
            preparedStatement.setDouble(5, data.getPrice());
            preparedStatement.setString(6,String.valueOf( mealType.getSelectionModel().getSelectedItem()));
            preparedStatement.setString(7, String.valueOf( plans.getSelectionModel().getSelectedItem()));

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        //System.out.println("Order placed successfully! Order ID: " + generatedKeys.getInt(1));
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Congratulations");
                        alert.setHeaderText(null);
                        alert.setContentText("Order placed successfully! Order ID: " + generatedKeys.getInt(1));
                        alert.showAndWait();
                    } else {
                        System.out.println("Failed to retrieve order ID.");
                    }
                }
            } else {
                System.out.println("Failed to place order.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void chatBtnHandler() throws IOException {
        ChatBoxOpenner chatBoxOpenner = new ChatBoxOpenner();
        chatBoxOpenner.chatBtnHandler(data.getUserName());
    }
    public void payBtnAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MoneyTransferStage.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.setTitle("Easy Pay");
        stage.show();
    }
    public void backBtnAction(){
        backBtn.getScene().getWindow().hide();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        optionAdder();
    }
}
