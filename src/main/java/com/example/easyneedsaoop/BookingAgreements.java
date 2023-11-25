package com.example.easyneedsaoop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

public class BookingAgreements {

    @FXML
    private Button backBtn;
    private Alert alert;
    @FXML
    private Button agreementBtn;

    @FXML
    private Button agreementBtn2;

    @FXML
    private Button bookBtn;

    @FXML
    private Button chatBtn;

    @FXML
    private CheckBox check1;
    @FXML
    private CheckBox check2;

    @FXML
    private Label einfo;

    @FXML
    private Label houseNameLabel;

    @FXML
    private Button imageBrowse;

    @FXML
    private Label locationLabel;

    @FXML
    private Label rent;

    @FXML
    private Label room;

    @FXML
    private TextField urlBox;

    @FXML
    private Label userNameLabel;

    @FXML
    private Label userNameLabel1;

    @FXML
    private ImageView viwer;
    private Image image;
    rentData dataa;
    int id;
    public void InventoryImportBtn(){
        FileChooser openFile = new FileChooser();
        openFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image File", "*png", "*jpg"));

        File file = openFile.showOpenDialog(rent.getScene().getWindow());

        if (file != null) {
            data.path = file.getAbsolutePath();
            urlBox.setText(data.path);

        }
    }
    public void setData(rentData data) {
        this.dataa = data;
        id=data.getId();
        userNameLabel.setText(data.getUserName());
        houseNameLabel.setText(data.getHouseName());
        locationLabel.setText(data.getAddress());
        String path = "File:" + data.getImage();
        image = new Image(path, 626, 274, false, true);
        viwer.setImage(image);
        locationLabel.setText(data.getAddress());
        rent.setText(String.valueOf(data.getRent()));
    }
    private int homeid;
    private String ownerName;
    private String houseName;
    private String ownerUsername;
    private double rentt;
    private String address;
    private String nidImage;
    private boolean bachelor;
    private boolean sublet;
    private boolean dn_draw;
    private Date date;
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    public boolean nidImport(){
        if(urlBox.getText().equals("")){
            return false;
        }
        return true;
    }

    public void placeOrder() {
        String insertSql = "INSERT INTO rentorder (ownerName, houseName, ownerUserName, tanentUserName, rent, address, nidImage, date) VALUES (?, ?, ?, ?, ?, ?, ?, NOW())";
        connect = database.connectDB();

        try (PreparedStatement preparedStatement = connect.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, dataa.getOwnerName());
            preparedStatement.setString(2, dataa.getHouseName());
            preparedStatement.setString(3, dataa.getUserName());
            preparedStatement.setString(4, data.username);
            preparedStatement.setDouble(5, dataa.getRent());
            preparedStatement.setString(6, dataa.getAddress());
            preparedStatement.setString(7, nidImage);

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
     @FXML
     void handleBookingClick(ActionEvent e) {
       if(e.getSource()==bookBtn) {
           if (check1.isSelected() && check2.isSelected() && nidImport()) {
               alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("Success");
               alert.setHeaderText(null);
               alert.setContentText("Successfully Booked");
               alert.showAndWait();
               placeOrder();
           } else {
               alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("Already registered");
               alert.setHeaderText(null);
               alert.setContentText("Cannot proceed without agreeing to terms and conditions");
               alert.showAndWait();
           }
       }

    }
    public void backBtnAction(){
        backBtn.getScene().getWindow().hide();
    }
    public void agreementBtn2Action() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OwnerTenantAgreement.fxml"));
        Stage stage=new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }
}

