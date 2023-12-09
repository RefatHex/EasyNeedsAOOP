package com.example.easyneedsaoop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Cloth_MainCard {

    @FXML
    private ImageView image;

    @FXML
    private Button orderBtn;

    @FXML
    private Label price;

    @FXML
    private Label prodName;

    @FXML
    private TextField quantity;

    @FXML
    private Label shopname;
    @FXML
    private Label id;

    @FXML
    private TextField size;

    private String sellerUsername;
    private ClothShopData data;
    private Image imagee;
    public void setData(ClothShopData data){
        this.data=data;
        id.setText(String.valueOf(data.getProductID()));
        String path="File:"+data.getImage();
        imagee=new Image(path,249,152,false,true);
        image.setImage(imagee);
        sellerUsername=data.getSellerUsername();
        shopname.setText(data.getShopName());
        price.setText(String.valueOf(data.getPrice()));
        prodName.setText(data.getProdName());
    }
    public void placeOrder() {
        String insertSql = "INSERT INTO clothOrder (username, ownerUserName, productID, price, date) VALUES (?, ?, ?, ?, NOW())";
        Connection connect = database.connectDB();

        try (PreparedStatement preparedStatement = connect.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, com.example.easyneedsaoop.data.username);
            preparedStatement.setString(2, data.getSellerUsername());
            preparedStatement.setInt(3, data.getProductID());
            preparedStatement.setDouble(4, data.getPrice());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        Alert alert ;
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success");
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


}
