package com.example.easyneedsaoop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.security.spec.ECField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class RentCardDesign implements Initializable {
    @FXML
    private Label bachelor;

    @FXML
    private Button bookNow;

    @FXML
    private Button chat;

    @FXML
    private ImageView houseImage;

    @FXML
    private Label location;

    @FXML
    private Label rent;
    private rentData data;

    public rentData getData() {
        return data;
    }

    private Image image;
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    private ObservableList<rentData> cardDetails= FXCollections.observableArrayList();
    public void setData(rentData data) {
        this.data = data;
        System.out.println(data.getId());
        String path = "File:" + data.getImage();
        image = new Image(path, 249, 152, false, true);
        houseImage.setImage(image);
        location.setText(data.getAddress());
        rent.setText(String.valueOf(data.getRent()));
    }


    public void handleEvent(ActionEvent event) {
        if (event.getSource() == bookNow) {

        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
