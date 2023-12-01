package com.example.easyneedsaoop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Cloth_RecentCard implements Initializable {

    @FXML
    private ImageView image;

    @FXML
    private Label price;
    @FXML
    private Label prodName;


    @FXML
    private TextField quantity_box;

    @FXML
    private Label shopname;
    @FXML
    private Button cartBtn;

    @FXML
    private TextField size;
    private ClothShopData data;



    private Image imagee;
    @FXML
    private Label id;
    private String sellerUsername;
    public void setData(ClothShopData data){
        this.data=data;
        sellerUsername=data.getSellerUsername();

        id.setText(String.valueOf(data.getProductID()));
        String path="File:"+data.getImage();
        imagee=new Image(path,249,152,false,true);
        image.setImage(imagee);
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
                        System.out.println("Order placed successfully! Order ID: " + generatedKeys.getInt(1));
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
