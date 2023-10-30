package com.example.easyneedsaoop;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class RentPageController implements Initializable {

    @FXML
    private AnchorPane adminAnchorPane;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Label imgLbl1;

    @FXML
    private Label imgLbl2;

    @FXML
    private Button logoutBtn;

    @FXML
    private AnchorPane mainForm;

    @FXML
    private AnchorPane menuAnchorPane;

    @FXML
    private AnchorPane rentAnchor;

    @FXML
    private Button rentDashboard_btn;

    @FXML
    private TableView<rentData> rentInTable;

    @FXML
    private Button rentIn_ImportBtn;

    @FXML
    private TextField rentIn_Rent;

    @FXML
    private Button rentIn_UpdateBtn;

    @FXML
    private Button rentIn_addBtn;

    @FXML
    private TextField rentIn_address;

    @FXML
    private ComboBox<?> rentIn_bachelorBox;

    @FXML
    private Button rentIn_clearBtn;

    @FXML
    private TableColumn<rentData, String> rentIn_col_Id;

    @FXML
    private TableColumn<rentData, String> rentIn_col_address;

    @FXML
    private TableColumn<rentData, String> rentIn_col_bachelor;

    @FXML
    private TableColumn<rentData, String> rentIn_col_contact;

    @FXML
    private TableColumn<rentData, String> rentIn_col_dinning;

    @FXML
    private TableColumn<rentData, String> rentIn_col_eInfo;

    @FXML
    private TableColumn<rentData, String> rentIn_col_houseName;

    @FXML
    private TableColumn<rentData, String> rentIn_col_owner;

    @FXML
    private TableColumn<rentData, String> rentIn_col_flatNo;

    @FXML
    private TableColumn<rentData, String> rentIn_col_rent;

    @FXML
    private TableColumn<rentData, String> rentIn_col_roomNo;

    @FXML
    private TableColumn<rentData, String> rentIn_col_sublet;
    @FXML
    private TableColumn<rentData, String> rentIn_col_date;

    @FXML
    private TextField rentIn_contact;

    @FXML
    private Button rentIn_deleteBtn;

    @FXML
    private ComboBox<?> rentIn_dinning;

    @FXML
    private TextField rentIn_einfo;

    @FXML
    private TextField rentIn_flatNo;

    @FXML
    private AnchorPane rentIn_form;

    @FXML
    private TextField rentIn_houseName;

    @FXML
    private TextField rentIn_id;

    @FXML
    private ImageView rentIn_imageView;

    @FXML
    private TextField rentIn_owner;

    @FXML
    private TextField rentIn_rooms;

    @FXML
    private ComboBox<?> rentIn_subletOption;

    @FXML
    private Button rentInventory_btn;

    @FXML
    private Button rentPayment_btn;

    @FXML
    private Label userName;
    private Alert alert;
    private Image image;
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        displayUsername();
        yesOrNo();
        getListData();
        rentInventoryShowData();

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

    @FXML
    public void rentInventoryAddBtn(){
        if(rentIn_id.getText().isEmpty()||
                rentIn_owner.getText().isEmpty()||
                rentIn_houseName.getText().isEmpty()||
                rentIn_rooms.getText().isEmpty()||
                rentIn_flatNo.getText().isEmpty()||
                rentIn_subletOption.getSelectionModel().getSelectedItem()==null||
                rentIn_bachelorBox.getSelectionModel().getSelectedItem()==null||
                rentIn_dinning.getSelectionModel().getSelectedItem()==null||
                data.path == null){

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        }else{
            String insertData="INSERT INTO rentinfo "+
                    "(ownerName,houseName,userName,room,flatNo,contact,rent,address,einfo,image,bachelor,sublet,dn_draw,date)" +
                    "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            try {
                prepare=connect.prepareStatement(insertData);
                prepare.setString(1,rentIn_owner.getText());
                prepare.setString(2,rentIn_houseName.getText());
                prepare.setString(3,data.username);
                prepare.setString(4,rentIn_rooms.getText());
                prepare.setString(5,rentIn_flatNo.getText());
                prepare.setString(6,rentIn_contact.getText());
                prepare.setString(7,rentIn_Rent.getText());
                prepare.setString(8,rentIn_address.getText());
                prepare.setString(9,rentIn_einfo.getText());
                String path = data.path;
                path = path.replace("\\", "\\\\");
                prepare.setString(10,path);
                prepare.setString(11, String.valueOf(rentIn_bachelorBox.getSelectionModel().getSelectedItem()));
                prepare.setString(12,String.valueOf( rentIn_subletOption.getSelectionModel().getSelectedItem()));
                prepare.setString(13,String.valueOf( rentIn_dinning.getSelectionModel().getSelectedItem()));
                java.util.Date date = new java.util.Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                prepare.setString(14, String.valueOf(sqlDate));
                prepare.executeUpdate();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Added!");
                alert.showAndWait();

                rentInventoryShowData();
                rentInventoryClearBtn();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void rentInventoryClearBtn(){
        rentIn_id.setText("");
        rentIn_owner.setText("");
        rentIn_houseName.setText("");
        rentIn_rooms.setText("");
        rentIn_flatNo.setText("");
        rentIn_contact.setText("");
        rentIn_Rent.setText("");
        rentIn_address.setText("");
        rentIn_einfo.setText("");
        data.path = "";
        data.id = 0;
        rentIn_imageView.setImage(null);

    }
    public void rentInventoryDeleteBtn() {
        if (rentIn_id.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please provide the ID of the record to be deleted.");
            alert.showAndWait();
            return;
        }

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this record?", ButtonType.YES, ButtonType.NO);
        confirmationAlert.setTitle("Delete Confirmation");
        confirmationAlert.setHeaderText(null);

        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {
            String deleteQuery = "DELETE FROM rentinfo WHERE id=?";

            try {
                prepare = connect.prepareStatement(deleteQuery);
                prepare.setString(1, rentIn_id.getText());

                int affectedRows = prepare.executeUpdate();

                if (affectedRows > 0) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Delete Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                    rentInventoryShowData();
                    rentInventoryClearBtn();
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
    public void displayUsername(){
        String user=data.username;
        user=user.substring(0,1).toUpperCase()+ user.substring(1);

        userName.setText(user);
    }

    @FXML
    public void rentInventoryUpdateBtn() {
        if (rentIn_id.getText().isEmpty() ||
                rentIn_owner.getText().isEmpty() ||
                rentIn_houseName.getText().isEmpty() ||
                rentIn_rooms.getText().isEmpty() ||
                rentIn_flatNo.getText().isEmpty() ||
                rentIn_subletOption.getSelectionModel().getSelectedItem() == null ||
                rentIn_bachelorBox.getSelectionModel().getSelectedItem() == null ||
                rentIn_dinning.getSelectionModel().getSelectedItem() == null ||
                data.path == null) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
            return;
        }
    }
    private ObservableList<rentData> rentInventoryList;
    
    public void rentInventoryShowData(){
        rentInventoryList=getListData();
        rentIn_col_Id.setCellValueFactory(new PropertyValueFactory<>("id"));
        rentIn_col_owner.setCellValueFactory(new PropertyValueFactory<>("ownerName"));
        rentIn_col_houseName.setCellValueFactory(new PropertyValueFactory<>("houseName"));
        rentIn_col_roomNo.setCellValueFactory(new PropertyValueFactory<>("room"));
        rentIn_col_flatNo.setCellValueFactory(new PropertyValueFactory<>("flatNo"));
        rentIn_col_contact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        rentIn_col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        rentIn_col_rent.setCellValueFactory(new PropertyValueFactory<>("rent"));
        rentIn_col_eInfo.setCellValueFactory(new PropertyValueFactory<>("einfo"));
        rentIn_col_bachelor.setCellValueFactory(new PropertyValueFactory<>("bachelor"));
        rentIn_col_sublet.setCellValueFactory(new PropertyValueFactory<>("sublet"));
        rentIn_col_dinning.setCellValueFactory(new PropertyValueFactory<>("dn_draw"));
        rentIn_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        rentInTable.setItems(rentInventoryList);
    }
    public ObservableList<rentData> getListData() {
        ObservableList<rentData> listData= FXCollections.observableArrayList();
        String sql="SELECT * FROM rentinfo";
        connect =database.connectDB();
        try{
            prepare=connect.prepareStatement(sql);
            result=prepare.executeQuery();
            rentData data;
            while(result.next()){
                data=new rentData(
                        result.getInt("id"),
                        result.getString("ownerName"),
                        result.getString("houseName"),
                        result.getInt("room"),
                        result.getInt("flatNo"),
                        result.getString("contact"),
                        result.getDouble("rent"),
                        result.getString("address"),
                        result.getString("einfo"),
                        result.getString("image"),
                        result.getBoolean("bachelor"),
                        result.getBoolean("sublet"),
                        result.getBoolean("dn_draw"),
                        result.getDate("date")
                );
                listData.add(data);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return  listData;
    }
    private boolean[] opinionList={true,false};
    public void yesOrNo(){
        List<Boolean> typeL=new ArrayList<Boolean>();
        for (boolean data:opinionList){
            typeL.add(data);
        }
        ObservableList listData= FXCollections.observableArrayList(typeL);
        rentIn_bachelorBox.setItems(listData);
        rentIn_subletOption.setItems(listData);
        rentIn_dinning.setItems(listData);
    }
    public void InventoryImportBtn(){
        FileChooser openFile = new FileChooser();
        openFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image File", "*png", "*jpg"));

        File file = openFile.showOpenDialog(mainForm.getScene().getWindow());

        if (file != null) {

            data.path = file.getAbsolutePath();
            image = new Image(file.toURI().toString(), 126, 120, false, true);
            rentIn_imageView.setImage(image);
            imgLbl1.setVisible(false);
            imgLbl2.setVisible(false);

        }
    }
    public void handleEvent(ActionEvent event) throws IOException {
        if (event.getSource() == rentInventory_btn) {
            dashboard_form.setVisible(false);
            rentIn_form.setVisible(true);
        } else if (event.getSource() == rentDashboard_btn) {
            dashboard_form.setVisible(true);
            rentIn_form.setVisible(false);
        } else if (event.getSource() == rentPayment_btn) {
            dashboard_form.setVisible(false);
            rentIn_form.setVisible(false);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MoneyTransferStage.fxml"));
            Stage stage=new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.setTitle("Easy Pay");
            stage.show();
            rentPayment_btn.getScene().getWindow().hide();
        }
    }

}