package com.example.easyneedsaoop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

public class HealthAssistantPageController implements Initializable {

    @FXML
    private AnchorPane adminAnchorPane;
    @FXML
    private AnchorPane order_form;
    @FXML
    private ComboBox<?> availableOn;

    @FXML
    private TableView<HealthData> healthAssistantTable;



    @FXML
    private TextField doctorID;

    @FXML
    private TextField doctorName;

    @FXML
    private TextField endingHour;

    @FXML
    private TextField extraInfo;
    @FXML
    private TextField hospital;

    @FXML
    private AnchorPane healthAnchor;

    @FXML
    private AnchorPane health_Form;

    @FXML
    private Button health_addbtn;

    @FXML
    private Button health_clearbtn;
    @FXML
    private Button chat_btn;
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
    private AnchorPane chat_form;
    @FXML
    private Button order_btn;

    @FXML
    private TableColumn<?, ?> health_col_name;

    @FXML
    private TableColumn<?, ?> health_col_service;

    @FXML
    private TableColumn<?, ?> health_col_specialist;
    private ObservableList<healthappointmentOrder> orderDetails = FXCollections.observableArrayList();
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
    @FXML
    private GridPane messageGridPane;

    public String[] daysInWeeks = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    public String[] service = {"Paid", "Free"};
    private Image image;
    private Alert alert;
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    @FXML
    private GridPane gridPane;


    public void healthAssistanceAddBtn() {
        if (doctorID.getText().isEmpty() ||
                doctorName.getText().isEmpty() ||
                startingHour.getText().isEmpty() ||
                extraInfo.getText().isEmpty() ||
                serviceType.getSelectionModel().getSelectedItem() == null ||
                availableOn.getSelectionModel().getSelectedItem() == null) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        } else {
            String insertData = "INSERT INTO hassisanceinfo " +
                    "( cardID, docName, userName, docMajor, start, end, fee, day, service, location, date,hospitalName,image)" +
                    "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            try {
                prepare = connect.prepareStatement(insertData);
                prepare.setString(1, doctorID.getText());
                prepare.setString(2, doctorName.getText());
                prepare.setString(3, data.username);
                prepare.setString(4, specialtyIn.getText());
                prepare.setString(5, startingHour.getText());
                prepare.setString(6, endingHour.getText());
                prepare.setString(7, visitingFee.getText());
                prepare.setString(8, availableOn.getSelectionModel().getSelectedItem().toString());
                prepare.setString(9, serviceType.getSelectionModel().getSelectedItem().toString());
                prepare.setString(10, extraInfo.getText());


                java.util.Date date = new java.util.Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                prepare.setString(11, sqlDate.toString());
                prepare.setString(12, hospital.getText());
                String path = data.path;
                path = path.replace("\\", "\\\\");
                prepare.setString(13, path);
                prepare.executeUpdate();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Added!");
                alert.showAndWait();

                healthAssistanceShowData();
                healthAssistanceClearBtn();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void healthAssistanceClearBtn() {
        doctorID.clear();
        doctorName.clear();
        startingHour.clear();
        endingHour.clear();
        extraInfo.clear();
        serviceType.getSelectionModel().clearSelection();
        specialtyIn.clear();
        visitingFee.clear();
        availableOn.getSelectionModel().clearSelection();
    }


    public void handleEvent(ActionEvent event) throws IOException {
        if (event.getSource() == inventory_btn) {

            health_Form.setVisible(true);
            chat_form.setVisible(false);
            order_form.setVisible(false);
        } else if (event.getSource() == payment_btn) {
            health_Form.setVisible(false);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MoneyTransferStage.fxml"));
            Stage stage = new Stage();
            //stage.initStyle(StageStyle.UNDECORATED);
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Easy Pay");
            stage.show();
            //payment_btn.getScene().getWindow().hide();
        }else if(event.getSource()==chat_btn){

            health_Form.setVisible(false);
            chat_form.setVisible(true);
            order_form.setVisible(false);
            showMessageList();
        }else if (event.getSource() == order_btn){

            health_Form.setVisible(false);
            menuDisplayOrderCard();
            order_form.setVisible(true);
            chat_form.setVisible(false);
        }
    }

    public void healthAssistanceDeleteBtn() {
        if (doctorID.getText().isEmpty()) {
            return;
        }

        String checkExistingData = "SELECT * FROM hassisanceinfo WHERE cardID=? AND userName=?";
        String deleteData = "DELETE FROM hassisanceinfo WHERE cardID=? AND userName=?";

        try {
            prepare = connect.prepareStatement(checkExistingData);
            prepare.setString(1, doctorID.getText());
            prepare.setString(2, data.username);
            ResultSet resultSet = prepare.executeQuery();

            if (!resultSet.next()) {
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("No record found with the provided ID and userName");
                alert.showAndWait();
                return;
            }

            prepare = connect.prepareStatement(deleteData);
            prepare.setString(1, doctorID.getText());
            prepare.setString(2, data.username);

            int rowsDeleted = prepare.executeUpdate();

            if (rowsDeleted > 0) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Record deleted successfully!");
                alert.showAndWait();

                healthAssistanceShowData();
                healthAssistanceClearBtn();
            } else {
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("No record found with the provided ID and userName");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void healthAssistanceUpdateBtn() {
        if (doctorID.getText().isEmpty() || doctorName.getText().isEmpty()) {
            return;
        }

        String checkExistingData = "SELECT * FROM hassisanceinfo WHERE cardId=? AND userName=?";
        String updateData = "UPDATE hassisanceinfo " +
                "SET cardID=?, docName=?, docMajor=?, start=?, end=?, fee=?, day=?, service=?, location=? " +
                "WHERE cardID=? AND userName=?";

        try {
            prepare = connect.prepareStatement(checkExistingData);
            prepare.setString(1, doctorID.getText());
            prepare.setString(2, data.username);
            ResultSet resultSet = prepare.executeQuery();

            if (!resultSet.next()) {
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("No record found with the provided ID and userName");
                alert.showAndWait();
                return;
            }

            prepare = connect.prepareStatement(updateData);
            prepare.setString(1, doctorID.getText());
            prepare.setString(2, doctorName.getText());
            prepare.setString(3, specialtyIn.getText());
            prepare.setString(4, startingHour.getText());
            prepare.setString(5, endingHour.getText());
            prepare.setString(6, visitingFee.getText());
            prepare.setString(7, availableOn.getSelectionModel().getSelectedItem().toString());
            prepare.setString(8, serviceType.getSelectionModel().getSelectedItem().toString());
            prepare.setString(9, extraInfo.getText());
            prepare.setString(10, doctorID.getText());
            prepare.setString(11, data.username);

            int rowsUpdated = prepare.executeUpdate();

            if (rowsUpdated > 0) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Record updated successfully!");
                alert.showAndWait();

                healthAssistanceShowData();
                healthAssistanceClearBtn();
            } else {
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("No record found with the provided ID and userName");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to fetch health data from the database
    public ObservableList<HealthData> getHealthListData() {
        ObservableList<HealthData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM hassisanceinfo";

        try {
            connect = database.connectDB();
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            HealthData data;

            while (result.next()) {
                data = new HealthData(
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
                        result.getDate("date")
                );
                listData.add(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listData;
    }

    // Method to display health data on the table
    private ObservableList<HealthData> healthInventoryList;

    public void healthAssistanceShowData() {
        healthInventoryList = getHealthListData();

        health_col_ID.setCellValueFactory(new PropertyValueFactory<>("cardID"));
        health_col_name.setCellValueFactory(new PropertyValueFactory<>("docName"));
        health_col_availability.setCellValueFactory(new PropertyValueFactory<>("day"));
        health_col_ending.setCellValueFactory(new PropertyValueFactory<>("end"));
        health_col_fee.setCellValueFactory(new PropertyValueFactory<>("fee"));
        health_col_service.setCellValueFactory(new PropertyValueFactory<>("service"));
        health_col_specialist.setCellValueFactory(new PropertyValueFactory<>("docMajor"));
        health_col_starting.setCellValueFactory(new PropertyValueFactory<>("start"));
        health_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        healthAssistantTable.setItems(healthInventoryList);
    }


    public void logout(ActionEvent event) {
        try {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                logoutBtn.getScene().getWindow().hide();
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("loginsignup.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(fxmlLoader.load());
                stage.setTitle("EasyNeeds");
                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayUsername() {
        String user = data.username;
        user = user.substring(0, 1).toUpperCase() + user.substring(1);

        userName.setText(user);
    }

    void setAvailabilty() {
        ObservableList listData = FXCollections.observableArrayList(daysInWeeks);
        availableOn.setItems(listData);
    }

    void setServiceType() {
        ObservableList listData = FXCollections.observableArrayList(service);
        serviceType.setItems(listData);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        health_Form.setVisible(true);
        healthAssistanceShowData();
        displayUsername();
        setAvailabilty();
        setServiceType();
        healthAssistanceShowData();
        connect = database.connectDB();
        menuDisplayOrderCard();
    }

    public void InventoryImportBtn(ActionEvent event) {
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

    private Map<String, StringBuilder> getMessagesByReceiver() {
        Map<String, StringBuilder> messagesMap = new HashMap<>();
        String sql = "SELECT senderUsername, message FROM messages WHERE receiverUsername = ?";
        try (
                PreparedStatement preparedStatement = connect.prepareStatement(sql)) {

            preparedStatement.setString(1, data.username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String senderUsername = resultSet.getString("senderUsername");
                    String message = resultSet.getString("message");

                    messagesMap.computeIfAbsent(senderUsername, k -> new StringBuilder()).append(message).append("\n");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return messagesMap;
    }

    //copy this to other classes
    public void showMessageList() {
        int row = 0;
        int column = 0;
        messageGridPane.getChildren().clear();
        messageGridPane.getRowConstraints().clear();
        messageGridPane.getColumnConstraints().clear();
        Map<String, StringBuilder> messagesMap = getMessagesByReceiver();

        for (Map.Entry<String, StringBuilder> entry : messagesMap.entrySet()) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Message.fxml"));
                AnchorPane pane = loader.load();
                Message card = loader.getController();

                card.setData(new messageData(entry.getKey(), entry.getValue().toString()));


                // Add margins to create space between cards
                Insets margin = new Insets(10);
                messageGridPane.setMargin(pane, margin);
                messageGridPane.add(pane, column, row++);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ObservableList<healthappointmentOrder> menuGetOrderData() {
        String sql = "SELECT * FROM `healthappointment` WHERE ownerUserName = ?";
        ObservableList<healthappointmentOrder> listOrderData = FXCollections.observableArrayList();

        connect = database.connectDB();
        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, data.username);
            result = prepare.executeQuery();
            healthappointmentOrder orderData;
            while (result.next()) {
                orderData = new healthappointmentOrder(result.getInt("id"),
                        result.getInt("cardID"),
                        result.getString("docName"),
                        result.getString("userName"),
                        result.getDate("date"),
                        result.getString("ownerUserName"));
                listOrderData.add(orderData);}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOrderData;
    }

    //copy this
    public void menuDisplayOrderCard() {
        orderDetails.clear();
        orderDetails.addAll(menuGetOrderData());
        int row = 0;
        int column = 0;
        gridPane.getRowConstraints().clear();
        gridPane.getColumnConstraints().clear();
        for (healthappointmentOrder orderDetail : orderDetails) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("order_show.fxml"));
                AnchorPane pane = loader.load();
                OrderShow cardR = loader.getController();
                cardR.setData(orderDetail);
//                pane.setOnMouseClicked(event -> handleOrderCardClick(cardR));
                // Add margins to create space between cards
                Insets margin = new Insets(10);
                GridPane.setMargin(pane, margin);
                gridPane.add(pane, column, row++);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}