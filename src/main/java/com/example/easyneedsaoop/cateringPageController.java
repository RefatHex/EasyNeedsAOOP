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
import java.util.*;
import java.util.Date;

public class cateringPageController implements Initializable {
    @FXML
    private AnchorPane adminAnchorPane;

    @FXML
    private ComboBox<String> billPay;
    @FXML
    private Button chat_btn;

    @FXML
    private TextField cateringContact;

    @FXML
    private TextField cateringOwnerID;

    @FXML
    private TextField cateringOwnerName;

    @FXML
    private Button cateringPaymentBtn;
    @FXML
    private AnchorPane order_form;

    @FXML
    private TextField cateringShopBranch;

    @FXML
    private TextField cateringShopName;

    @FXML
    private Button catering_ImportBtn1;

    @FXML
    private Button catering_UpdateBtn1;

    @FXML
    private Button catering_addBtn1;

    @FXML
    private Button catering_btn;

    @FXML
    private Button catering_clearBtn1;

    @FXML
    private TableColumn<?, ?> catering_col_Branch1;

    @FXML
    private TableColumn<?, ?> catering_col_Id1;

    @FXML
    private TableColumn<?, ?> catering_col_address1;
    @FXML
    private AnchorPane chat_form;

    @FXML
    private TableColumn<?, ?> catering_col_contact1;

    @FXML
    private TableColumn<?, ?> catering_col_date;

    @FXML
    private TableColumn<?, ?> catering_col_eInfo1;

    @FXML
    private TableColumn<?, ?> catering_col_mealPrice;

    @FXML
    private TableColumn<?, ?> catering_col_mealType1;

    @FXML
    private TableColumn<?, ?> catering_col_owner1;

    @FXML
    private TableColumn<?, ?> catering_col_shopName1;


    @FXML
    private AnchorPane catering_form;

    @FXML
    private Button dashboard_btn;
    @FXML
    private GridPane gridPane;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private TextField extraInfoText;

    @FXML
    private Label imgLbl1;

    @FXML
    private Label imgLbl2;

    @FXML
    private Button logoutBtn;

    @FXML
    private AnchorPane mainForm;

    @FXML
    private ComboBox<String> mealDelivery;

    @FXML
    private TextField mealPrice;

    @FXML
    private ComboBox<String> mealType;

    @FXML
    private AnchorPane menuAnchorPane;

    @FXML
    private TableView<CateringData> cateringTable;

    @FXML
    private ImageView imageView;

    @FXML
    private Label userName;
    @FXML
    private GridPane messageGridPane;
    @FXML
    private Button order_btn;
    private ObservableList<rentOrderData> orderDetails = FXCollections.observableArrayList();

    private Image image;
    private Alert alert;
    public String[] mealTypeOption = {"Daily", "Weekly", "Monthly"};
    public String[] mealDeliveryOption = {"Home Delivery", "Dine in"};
    public String[] billOption = {"Daily", "Weekly", "Monthly"};
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    public void addCateringData() {
        if (cateringOwnerID.getText().isEmpty() ||
                cateringOwnerName.getText().isEmpty() ||
                cateringShopName.getText().isEmpty() ||
                cateringShopBranch.getText().isEmpty() ||
                mealType.getSelectionModel().getSelectedItem() == null ||
                billPay.getSelectionModel().getSelectedItem() == null) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all required fields.");
            alert.showAndWait();
        } else {
            String insertData = "INSERT INTO cateringinfo " +
                    "(ownerName, shopName, branchName, userName, price, address, contact, extraInfo, image,mealType, billPay,mealDelivery,date)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";

            try {
                prepare = connect.prepareStatement(insertData);
                prepare.setString(1, cateringOwnerName.getText());
                prepare.setString(2, cateringShopName.getText());
                prepare.setString(3, cateringShopBranch.getText());
                prepare.setString(4, data.username);
                prepare.setString(5, mealPrice.getText());
                prepare.setString(6, cateringShopBranch.getText());
                prepare.setString(7, cateringContact.getText());
                prepare.setString(8, extraInfoText.getText());
                String path = data.path;
                path = path.replace("\\", "\\\\");
                prepare.setString(9, path);
                prepare.setString(10, (String) mealType.getSelectionModel().getSelectedItem());
                prepare.setString(11, (String) billPay.getSelectionModel().getSelectedItem());
                prepare.setString(12, (String) billPay.getSelectionModel().getSelectedItem());

                java.util.Date date = new java.util.Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                prepare.setString(13, String.valueOf(sqlDate));

                prepare.executeUpdate();


                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success Message");
                alert.setHeaderText(null);
                alert.setContentText("Data added successfully!");
                alert.showAndWait();
                cateringInventoryShowData();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void updateCateringData() {
        if (cateringOwnerID.getText().isEmpty() ||
                cateringOwnerName.getText().isEmpty() ||
                cateringShopName.getText().isEmpty() ||
                cateringShopBranch.getText().isEmpty() ||
                mealType.getSelectionModel().getSelectedItem() == null ||
                billPay.getSelectionModel().getSelectedItem() == null) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all required fields.");
            alert.showAndWait();
        } else {
            String updateData = "UPDATE cateringinfo SET " +
                    "ownerName=?, shopName=?, branchName=?, userName=?, " +
                    "address=?, contact=?, extraInfo=?,image=?, mealType=?, billPay=?, " +
                    "mealDelivery=?, date=? " +
                    "WHERE id=?";

            try {
                prepare = connect.prepareStatement(updateData);
                prepare.setString(1, cateringOwnerName.getText());
                prepare.setString(2, cateringShopName.getText());
                prepare.setString(3, cateringShopBranch.getText());
                prepare.setString(4, data.username);
                prepare.setString(5, mealPrice.getText());
                prepare.setString(6, cateringShopBranch.getText());
                prepare.setString(7, cateringContact.getText());
                prepare.setString(8, extraInfoText.getText());
                String path = data.path;
                path = path.replace("\\", "\\\\");
                prepare.setString(9, path);
                prepare.setString(10, (String) mealType.getSelectionModel().getSelectedItem());
                prepare.setString(11, (String) billPay.getSelectionModel().getSelectedItem());
                prepare.setString(12, (String) billPay.getSelectionModel().getSelectedItem());

                java.util.Date date = new java.util.Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                prepare.setString(13, String.valueOf(sqlDate));

                int affectedRows = prepare.executeUpdate();

                if (affectedRows > 0) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Update Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Update Error");
                    alert.setHeaderText(null);
                    alert.setContentText("No records updated. Please check the ID.");
                    alert.showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void clearCateringData() {
        cateringOwnerID.setText("");
        cateringOwnerName.setText("");
        cateringShopName.setText("");
        cateringShopBranch.setText("");
        mealPrice.setText("");
        cateringContact.setText("");
        mealDelivery.setValue(null);
        extraInfoText.setText("");
    }

    public void deleteCateringData() {
        if (cateringOwnerID.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please provide the ID of the record to be deleted.");
            alert.showAndWait();
            cateringInventoryShowData();
            return;
        }

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this record?", ButtonType.YES, ButtonType.NO);
        confirmationAlert.setTitle("Delete Confirmation");
        confirmationAlert.setHeaderText(null);

        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {
            String deleteQuery = "DELETE FROM cateringinfo WHERE id=?";

            try {
                prepare = connect.prepareStatement(deleteQuery);
                prepare.setString(1, cateringOwnerID.getText());

                int affectedRows = prepare.executeUpdate();

                if (affectedRows > 0) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Delete Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                    clearCateringData();
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Delete Error");
                    alert.setHeaderText(null);
                    alert.setContentText("No records deleted. Please check the ID.");
                    alert.showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        connect = database.connectDB();
        cateringInventoryShowData();
        displayUsername();
        optionAdder();
        dashboard_form.setVisible(true);
        catering_form.setVisible(false);
        menuDisplayOrderCard ();
    }

    public void optionAdder() {
        List<String> mtype = new ArrayList<>(Arrays.asList(mealTypeOption));
        List<String> delType = new ArrayList<>(Arrays.asList(mealDeliveryOption));
        List<String> billType = new ArrayList<>(Arrays.asList(billOption));
        ObservableList mealData = FXCollections.observableArrayList(mtype);
        ObservableList deliveryData = FXCollections.observableArrayList(delType);
        ObservableList billData = FXCollections.observableArrayList(billType);

        mealType.setItems(mealData);
        mealDelivery.setItems(deliveryData);
        billPay.setItems(billData);

    }

    public void InventoryImportBtn() {
        FileChooser openFile = new FileChooser();
        openFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image File", "*png", "*jpg"));

        File file = openFile.showOpenDialog(mainForm.getScene().getWindow());

        if (file != null) {

            data.path = file.getAbsolutePath();
            image = new Image(file.toURI().toString(), 126, 120, false, true);
            imageView.setImage(image);
            imgLbl1.setVisible(false);
            imgLbl2.setVisible(false);

        }
    }

    public void handleEvent(ActionEvent event) throws IOException {
        if (event.getSource() == catering_btn) {
            dashboard_form.setVisible(false);
            catering_form.setVisible(true);
        } else if (event.getSource() == dashboard_btn) {
            dashboard_form.setVisible(true);
            catering_form.setVisible(false);
        } else if (event.getSource() == cateringPaymentBtn) {
            dashboard_form.setVisible(false);
            catering_form.setVisible(false);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MoneyTransferStage.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.setTitle("Easy Pay");
            stage.show();
            //cateringPaymentBtn.getScene().getWindow().hide();
        } else if (event.getSource() == order_btn) {
            dashboard_form.setVisible(false);
            catering_form.setVisible(false);
            order_form.setVisible(true);
        } else if (event.getSource() == chat_btn) {
            dashboard_form.setVisible(false);
            catering_form.setVisible(false);
            chat_form.setVisible(true);
            showMessageList();
        }
    }

        public void handleMessengerBtn () throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ChatBox.fxml"));
            AnchorPane pane = loader.load();
            ChatBox cardR = loader.getController();
            cardR.setData("rasel");
            Stage stage = new Stage();
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(pane));
            stage.show();
        }

        public void displayUsername () {
            String user = data.username;
            user = user.substring(0, 1).toUpperCase() + user.substring(1);

            userName.setText(user);
        }

        @FXML
        void logout (ActionEvent event){
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
                    //stage.initStyle(StageStyle.UNDECORATED);
                    Scene scene = new Scene(fxmlLoader.load());
                    stage.setTitle("EasyNeeds");
                    stage.setScene(scene);
                    stage.show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //Merge data on table
        public ObservableList<CateringData> getRentListData () {
            ObservableList<CateringData> listData = FXCollections.observableArrayList();
            String sql = "SELECT * FROM cateringInfo";
            connect = database.connectDB();
            try {
                prepare = connect.prepareStatement(sql);
                result = prepare.executeQuery();
                CateringData data;
                while (result.next()) {
                    data = new CateringData(
                            result.getInt("id"),
                            result.getString("ownerName"),
                            result.getString("shopName"),
                            result.getString("branchName"),
                            result.getString("userName"),
                            result.getString("address"),
                            result.getString("contact"),
                            result.getString("extraInfo"),
                            result.getString("image"),
                            result.getDouble("price"),
                            result.getString("mealType"),
                            result.getString("billPay"),
                            result.getString("mealDelivery"),
                            result.getDate("date")
                    );
                    listData.add(data);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return listData;
        }
        //show Data on table
        private ObservableList<CateringData> CateringInventoryList;
        public void cateringInventoryShowData () {
            CateringInventoryList = getRentListData();
            catering_col_Id1.setCellValueFactory(new PropertyValueFactory<>("id"));
            catering_col_owner1.setCellValueFactory(new PropertyValueFactory<>("ownerName"));
            catering_col_shopName1.setCellValueFactory(new PropertyValueFactory<>("shopName"));
            catering_col_Branch1.setCellValueFactory(new PropertyValueFactory<>("branchName"));
            catering_col_contact1.setCellValueFactory(new PropertyValueFactory<>("contact"));
            catering_col_mealType1.setCellValueFactory(new PropertyValueFactory<>("mealType"));
            catering_col_address1.setCellValueFactory(new PropertyValueFactory<>("address"));
            catering_col_eInfo1.setCellValueFactory(new PropertyValueFactory<>("extraInfo"));
            catering_col_mealPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
            catering_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
            cateringTable.setItems(CateringInventoryList);
        }
        private Map<String, StringBuilder> getMessagesByReceiver () {
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
        public void showMessageList () {
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
        public ObservableList<rentOrderData> menuGetOrderData() {
            String sql = "SELECT * FROM `rentorder` WHERE ownerUserName = ?";
            ObservableList<rentOrderData> listOrderData = FXCollections.observableArrayList();

            connect = database.connectDB();
            try {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, data.username);
                result = prepare.executeQuery();
                rentOrderData orderData;
                while (result.next()) {
                    orderData = new rentOrderData(result.getInt("id"),
                            result.getString("ownerName"),
                            result.getString("houseName"),
                            result.getString("ownerUserName"),
                            result.getString("tanentUserName"),
                            result.getDouble("rent"),
                            result.getString("address"),
                            result.getString("nidImage"),
                            result.getDate("date"));
                    listOrderData.add(orderData);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return listOrderData;
        }

        //copy this
        public void menuDisplayOrderCard () {
            orderDetails.clear();
            orderDetails.addAll(menuGetOrderData());
            int row = 0;
            int column = 0;
            gridPane.getRowConstraints().clear();
            gridPane.getColumnConstraints().clear();
            for (rentOrderData orderDetail : orderDetails) {
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
