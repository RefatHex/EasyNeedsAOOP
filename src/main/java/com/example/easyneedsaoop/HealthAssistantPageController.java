package com.example.easyneedsaoop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

public class HealthAssistantPageController implements Initializable {

    @FXML
    private AnchorPane adminAnchorPane;

    @FXML
    private ComboBox<?> availableOn;

    @FXML
    private TableView<?> clothingTable;

    @FXML
    private Button dashboard_btn;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private TextField doctorID;

    @FXML
    private TextField doctorName;

    @FXML
    private TextField endingHour;

    @FXML
    private TextField extraInfo;

    @FXML
    private AnchorPane healthAnchor;

    @FXML
    private AnchorPane health_Form;

    @FXML
    private Button health_addbtn;

    @FXML
    private Button health_clearbtn;

    @FXML
    private TableColumn<?, ?> health_col_ID;

    @FXML
    private TableColumn<?, ?> health_col_availability;

    @FXML
    private TableColumn<?, ?> health_col_date;

    @FXML
    private TableColumn<?, ?> health_col_ending;

    @FXML
    private TableColumn<?, ?> health_col_fee;

    @FXML
    private TableColumn<?, ?> health_col_name;

    @FXML
    private TableColumn<?, ?> health_col_service;

    @FXML
    private TableColumn<?, ?> health_col_specialist;

    @FXML
    private TableColumn<?, ?> health_col_starting;

    @FXML
    private Button health_deletebtn;

    @FXML
    private ImageView health_imageView;

    @FXML
    private Button health_import_btn;

    @FXML
    private Button health_updatebtn;

    @FXML
    private Label imgLbl12;

    @FXML
    private Label imgLbl22;

    @FXML
    private Button inventory_btn;

    @FXML
    private Button logoutBtn;
    @FXML
    private AnchorPane mainForm;
    @FXML
    private AnchorPane menuAnchorPane;
    @FXML
    private Button payment_btn;
    @FXML
    private ComboBox<?> serviceType;
    @FXML
    private TextField specialtyIn;
    @FXML
    private TextField visitingFee;
    @FXML
    private TextField startingHour;
    @FXML
    private Label userName;
    private Image image;
    private Alert alert;
    public String[] daysInWeeks = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
    public String[] service ={"Paid","Free"};
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        displayUsername();
        setAvailabilty();
        setServiceType();

    }
    @FXML
    void InventoryImportBtn(ActionEvent event) {
        FileChooser openFile = new FileChooser();
        openFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image File", "*png", "*jpg"));

        File file = openFile.showOpenDialog(mainForm.getScene().getWindow());

        if (file != null) {

            data.path = file.getAbsolutePath();
            image = new Image(file.toURI().toString(), 126, 120, false, true);
            health_imageView.setImage(image);
            imgLbl12.setVisible(false);
            imgLbl22.setVisible(false);

        }
    }

    @FXML
    void healthInventoryAddBtn(ActionEvent event) {

    }

    @FXML
    void healthInventoryClearBtn(ActionEvent event) {
        doctorID.setText("");
        doctorName.setText("");
        specialtyIn.setText("");
        startingHour.setText("");
        endingHour.setText("");
        extraInfo.setText("");
        visitingFee.setText("");
        availableOn.setValue(null);
        serviceType.setValue(null);
    }

    @FXML
    void healthInventoryDeleteBtn(ActionEvent event) {

    }

    @FXML
    void healthInventoryUpdateBtn(ActionEvent event) {

    }

    @FXML
    void handleEvent(ActionEvent event) throws IOException {
        if (event.getSource() == inventory_btn) {
            dashboard_form.setVisible(false);
            health_Form.setVisible(true);
        } else if (event.getSource() == dashboard_btn) {
            dashboard_form.setVisible(true);
            health_Form.setVisible(false);
        } else if (event.getSource() == payment_btn) {
            dashboard_form.setVisible(false);
            health_Form.setVisible(false);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MoneyTransferStage.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.setTitle("Easy Pay");
            stage.show();
            payment_btn.getScene().getWindow().hide();

        }
    }

    @FXML
    void logout(ActionEvent event) {
        try {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                logoutBtn.getScene().getWindow().hide();
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("loginsignup.fxml"));
                Stage stage=new Stage();
                Scene scene = new Scene(fxmlLoader.load());
                stage.setTitle("EasyNeeds");
                stage.setScene(scene);
                stage.show();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void displayUsername(){
        String user=data.username;
        user=user.substring(0,1).toUpperCase()+ user.substring(1);

        userName.setText(user);
    }

    void setAvailabilty(){
        ObservableList listData= FXCollections.observableArrayList(daysInWeeks);
        availableOn.setItems(listData);
    }
    void setServiceType(){
        ObservableList listData= FXCollections.observableArrayList(service);
        serviceType.setItems(listData);
    }

}
