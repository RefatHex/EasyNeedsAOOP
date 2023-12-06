package com.example.easyneedsaoop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.*;

public class AdminControlPage implements Initializable {
    @FXML
    private Button clothing_btn;
    @FXML
    private AnchorPane instructor_form;
    @FXML
    private AnchorPane Clothing_Form;

    @FXML
    private Button customer_btn;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private AnchorPane catering_form;

    @FXML
    private Button dashboard_btn;

    @FXML
    private Button edu_btn;

    @FXML
    private Button food_btn;

    @FXML
    private Button hospital_btn;

    @FXML
    private Button logoutBtn;

    @FXML
    private AnchorPane mainForm;

    @FXML
    private TableView<rentData> rentInTable;
    @FXML
    private TableView<CateringData> cateringTable;

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
    private TextField rentIn_contact;

    @FXML
    private Button rentIn_deleteBtn;

    @FXML
    private ComboBox<?> rentIn_dinning;

    @FXML
    private TextField rentIn_einfo;

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
    private TextField rentIn_flatNo;

    @FXML
    private TextField rentIn_rooms;

    @FXML
    private ComboBox<?> rentIn_subletOption;

    @FXML
    private Button rent_btn;

    @FXML
    private Label userName;

    @FXML
    private TableColumn<rentData, String> rentIn_col_date;
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private Alert alert;
    private Image image;
    private boolean[] opinionList={true,false};
    @FXML
    private ComboBox<String> billPay;

    @FXML
    private TextField cateringContact;

    @FXML
    private TextField cateringOwnerID;

    @FXML
    private TextField cateringOwnerName;

    @FXML
    private TextField cateringShopBranch;

    @FXML
    private TextField cateringShopName;
    @FXML
    private TextField mealPrice;

    @FXML
    private TextField extraInfoText;

    @FXML
    private TableColumn<?, ?> catering_col_Branch1;

    @FXML
    private TableColumn<?, ?> catering_col_Id1;

    @FXML
    private TableColumn<?, ?> catering_col_address1;

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
    private Button catering_deleteBtn1;
    @FXML
    private ImageView catering_imageView1;

    @FXML
    private Label imgLbl1;
    @FXML
    private Label imgLbl2;
    public String[] mealTypeOption={"Daily","Weekly","Monthly"};
    public String[] mealDeliveryOption={"Daily","Weekly","Monthly"};
    public String[] billOption={"Daily","Weekly","Monthly"};

    @FXML
    private ComboBox<String> mealType;

    @FXML
    private ComboBox<String> mealDelivery;
    //Clothing veriables
    @FXML
    private TableColumn<String, String> clothIn_col_ProdID;

    @FXML
    private TableColumn<String, String> clothIn_col_ProdInfo;

    @FXML
    private TableColumn<String, String> clothIn_col_ProdName;

    @FXML
    private TableColumn<String, String> clothIn_col_ProdPrice;

    @FXML
    private TableColumn<String, String> clothIn_col_ProdType;

    @FXML
    private TableColumn<String, String> clothIn_col_category;

    @FXML
    private TableColumn<String, String> clothIn_col_date;
    @FXML
    private ImageView clothinIn_imageView;

    @FXML
    private TableView<ClothShopData> clothingTable;
    @FXML
    private ComboBox<?> prodCat;

    @FXML
    private TextField prodID;

    @FXML
    private TextField prodInfo;

    @FXML
    private TextField prodName;

    @FXML
    private TextField prodPrice;

    @FXML
    private ComboBox<?> prodType;

    private String[] categoryOption={"kids","Man","Women"};
    private String[] prodTypeOption={"T-Shirt","Shirt","Panjabi","Shari","Skirt"};
    //Instructor Page
    private String[] courseCategoryOption ={"Education","Tech","Programming","Study Material","Motivational"};
    private String[] courseTypeOption ={"Weekly","Yearly","Semester","Trimester"};

    @FXML
    private ComboBox<?> courseCategory;

    @FXML
    private TextField courseDescription;

    @FXML
    private TextField courseExtraInfo;

    @FXML
    private TextField courseID;

    @FXML
    private TextField courseName;

    @FXML
    private TextField coursePrice;

    @FXML
    private ComboBox<?> course_type;

    @FXML
    private TextField instructName;

    @FXML
    private TableColumn<?, ?> instructorIn_col_courseCategory;

    @FXML
    private TableColumn<?, ?> instructorIn_col_courseEInfo;

    @FXML
    private TableColumn<?, ?> instructorIn_col_courseID;

    @FXML
    private TableColumn<?, ?> instructorIn_col_courseName;

    @FXML
    private TableColumn<?, ?> instructorIn_col_coursePrice;

    @FXML
    private TableColumn<?, ?> instructorIn_col_courseType;

    @FXML
    private TableColumn<?, ?> instructorIn_col_date;

    @FXML
    private TableView<CourseData> instructorTable;

    //health Assistant

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
    private TextField hospital;


    public String[] daysInWeeks = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
    public String[] service ={"Paid","Free"};



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
    public void rentInventoryUpdateBtn(){
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
            return;
        }

        String updateData = "UPDATE rentinfo SET " +
                "ownerName=?, houseName=?, userName=?, room=?, flatNo=?, contact=?, " +
                "rent=?, address=?, einfo=?, image=?, bachelor=?, sublet=?, dn_draw=?, date=? " +
                "WHERE id=?";

        try {
            prepare = connect.prepareStatement(updateData);
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
            prepare.setString(15, rentIn_id.getText());


            int affectedRows = prepare.executeUpdate();

            if (affectedRows > 0) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Update Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Updated!");
                alert.showAndWait();

                rentInventoryShowData();
                rentInventoryClearBtn();
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
//Merge data on table
    public ObservableList<rentData> getRentListData() {
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
    //show Data on table
    private ObservableList<rentData> rentInventoryList;
    public void rentInventoryShowData(){
        rentInventoryList= getRentListData();
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

    //Catering options
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
                    "(ownerName, shopName, branchName, userName, price, address, contact, extraInfo,image, mealType, billPay,mealDelivery,date)" +
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
                prepare.setString(9,path);
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
                cateringInventoryShowData();
                alert.showAndWait();

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
                prepare.setString(9,path);
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
    //Merge data on table
    public ObservableList<CateringData> getMealListData() {
        ObservableList<CateringData> listData= FXCollections.observableArrayList();
        String sql="SELECT * FROM cateringInfo";
        connect =database.connectDB();
        try{
            prepare=connect.prepareStatement(sql);
            result=prepare.executeQuery();
            CateringData data;
            while(result.next()){
                data=new CateringData(
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
        }catch(Exception e){
            e.printStackTrace();
        }
        return  listData;
    }
    //show Data on table
    private ObservableList<CateringData> CateringInventoryList;
    public void cateringInventoryShowData(){
        CateringInventoryList= getMealListData();
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

    //Clothing Data

    public void clothShopInventoryAddBtn(){
        if(prodID.getText().isEmpty() ||
                prodName.getText().isEmpty() ||
                prodPrice.getText().isEmpty() ||
                prodCat.getSelectionModel().getSelectedItem() == null ||
                prodType.getSelectionModel().getSelectedItem() == null ||
                prodInfo.getText().isEmpty()) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        } else {
            String insertData = "INSERT INTO clothinginfo " +
                    "(productID, username, shopName, prodName, price,image, category, type, einfo, date)" +
                    "VALUES(?, ?,?, ?, ?, ?, ?, ?, ?, ?)";
            try {
                prepare = connect.prepareStatement(insertData);
                prepare.setString(1, prodID.getText());
                prepare.setString(2, data.username);
                prepare.setString(3, data.name);
                prepare.setString(4, prodName.getText());
                prepare.setString(5, prodPrice.getText());
                String path = data.path;
                path = path.replace("\\", "\\\\");
                prepare.setString(6,path);
                prepare.setString(7, String.valueOf(prodCat.getSelectionModel().getSelectedItem()));
                prepare.setString(8, String.valueOf(prodType.getSelectionModel().getSelectedItem()));
                prepare.setString(9, prodInfo.getText());

                java.util.Date date = new java.util.Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                prepare.setString(10, String.valueOf(sqlDate));

                prepare.executeUpdate();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Added!");
                alert.showAndWait();

                clothShopInventoryShowData();
                clothShopInventoryClearBtn();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    public void clothShopInventoryClearBtn() {
        prodID.clear();
        prodName.clear();
        prodPrice.clear();
        prodCat.getSelectionModel().clearSelection();
        prodType.getSelectionModel().clearSelection();
        prodInfo.clear();
    }

    public void clothShopInventoryUpdateBtn(){
        if(prodID.getText().isEmpty() ||
                prodName.getText().isEmpty() ||
                prodPrice.getText().isEmpty() ||
                prodCat.getSelectionModel().getSelectedItem() == null ||
                prodType.getSelectionModel().getSelectedItem() == null ||
                prodInfo.getText().isEmpty()) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
            return;
        }

        String updateData = "UPDATE clothinginfo SET " +
                "username=?, shopName=?, prodName=?, price=?,image=?, category=?, " +
                "type=?, einfo=?, date=? " +
                "WHERE productID=?";

        try {
            prepare = connect.prepareStatement(updateData);
            prepare.setString(1, data.username);
            prepare.setString(2, data.name);
            prepare.setString(3, prodName.getText());
            prepare.setString(4, prodPrice.getText());
            String path = data.path;
            path = path.replace("\\", "\\\\");
            prepare.setString(5,path);
            prepare.setString(6, String.valueOf(prodCat.getSelectionModel().getSelectedItem()));
            prepare.setString(7, String.valueOf(prodType.getSelectionModel().getSelectedItem()));
            prepare.setString(8, prodInfo.getText());


            java.util.Date date = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            prepare.setString(9, String.valueOf(sqlDate));

            prepare.setString(10, prodID.getText());

            int affectedRows = prepare.executeUpdate();

            if (affectedRows > 0) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Update Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Updated!");
                alert.showAndWait();

                clothShopInventoryShowData();
                clothShopInventoryClearBtn();
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Update Error");
                alert.setHeaderText(null);
                alert.setContentText("No records updated. Please check the product ID.");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void clothShopInventoryDeleteBtn() {
        if (prodID.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please provide both the Product ID and Username to delete a product.");
            alert.showAndWait();
            return;
        }

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this product?", ButtonType.YES, ButtonType.NO);
        confirmationAlert.setTitle("Delete Confirmation");
        confirmationAlert.setHeaderText(null);

        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {
            String deleteQuery = "DELETE FROM clothinginfo WHERE productID=? AND username=?";

            try {
                prepare = connect.prepareStatement(deleteQuery);
                prepare.setString(1, prodID.getText());
                prepare.setString(2, data.username);

                int affectedRows = prepare.executeUpdate();

                if (affectedRows > 0) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Delete Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                    clothShopInventoryShowData();
                    clothShopInventoryClearBtn();
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Delete Error");
                    alert.setHeaderText(null);
                    alert.setContentText("No records deleted. Please check the Product ID and Username.");
                    alert.showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public ObservableList<ClothShopData> getClothShopListData() {
        ObservableList<ClothShopData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM clothinginfo";
        connect = database.connectDB();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            ClothShopData data;
            while (result.next()) {
                data = new ClothShopData(
                        result.getInt("productID"),
                        result.getString("username"),
                        result.getString("shopName"),
                        result.getString("prodName"),
                        result.getDouble("price"),
                        result.getString("image"),
                        result.getString("category"),
                        result.getString("type"),
                        result.getString("einfo"),
                        result.getDate("date")
                );
                listData.add(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    // Show Data on table
    private ObservableList<ClothShopData> clothShopInventoryList;
    public void clothShopInventoryShowData() {
        clothShopInventoryList = getClothShopListData();
        clothIn_col_ProdID.setCellValueFactory(new PropertyValueFactory<>("productID"));
        clothIn_col_ProdInfo.setCellValueFactory(new PropertyValueFactory<>("einfo"));
        clothIn_col_ProdName.setCellValueFactory(new PropertyValueFactory<>("prodName"));
        clothIn_col_ProdPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        clothIn_col_ProdType.setCellValueFactory(new PropertyValueFactory<>("type"));
        clothIn_col_category.setCellValueFactory(new PropertyValueFactory<>("category"));
        clothIn_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        clothingTable.setItems(clothShopInventoryList);
    }

//Instructor page
public void courseInventoryAddBtn() {
    if (courseID.getText().isEmpty() ||
            courseName.getText().isEmpty() ||
            coursePrice.getText().isEmpty() ||
            instructName.getText().isEmpty() ||
            courseCategory.getSelectionModel().getSelectedItem() == null ||
            course_type.getSelectionModel().getSelectedItem() == null) {

        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Please fill all blank fields");
        alert.showAndWait();
    } else {
        String insertData = "INSERT INTO courseInfo " +
                "(courseID, courseName, price, instructorName, userName, description, image, info, category, type, date)" +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            prepare = connect.prepareStatement(insertData);
            prepare.setString(1, courseID.getText());
            prepare.setString(2, courseName.getText());
            prepare.setString(3, coursePrice.getText());
            prepare.setString(4, instructName.getText());
            prepare.setString(5, data.username);
            prepare.setString(6, courseDescription.getText());
            prepare.setString(7, data.path.replace("\\", "\\\\"));
            prepare.setString(8, courseExtraInfo.getText());
            prepare.setString(9, String.valueOf(courseCategory.getSelectionModel().getSelectedItem()));
            prepare.setString(10, String.valueOf(course_type.getSelectionModel().getSelectedItem()));

            java.util.Date date = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            prepare.setString(11, String.valueOf(sqlDate));

            prepare.executeUpdate();

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Successfully Added!");
            alert.showAndWait();


             courseInventoryShowData();
            courseInventoryClearBtn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
    public void courseInventoryUpdateBtn() {
        if (courseID.getText().isEmpty() ||
                courseName.getText().isEmpty() ||
                coursePrice.getText().isEmpty() ||
                instructName.getText().isEmpty() ||
                courseCategory.getSelectionModel().getSelectedItem() == null ||
                course_type.getSelectionModel().getSelectedItem() == null) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
            return;
        }

        String updateData = "UPDATE courseInfo SET " +
                "courseName=?, price=?, instructorName=?, userName=?, description=?, image=?, info=?, category=?, type=?, date=? " +
                "WHERE courseID=?";

        try {
            prepare = connect.prepareStatement(updateData);
            prepare.setString(1, courseName.getText());
            prepare.setDouble(2, Double.parseDouble(coursePrice.getText()));
            prepare.setString(3, instructName.getText());
            prepare.setString(4, data.username);
            prepare.setString(5, courseDescription.getText());
            prepare.setString(6, data.path.replace("\\", "\\\\"));
            prepare.setString(7, courseExtraInfo.getText());
            prepare.setString(8, String.valueOf(courseCategory.getSelectionModel().getSelectedItem()));
            prepare.setString(9, String.valueOf(course_type.getSelectionModel().getSelectedItem()));

            java.util.Date date = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            prepare.setDate(10, sqlDate);

            prepare.setString(11, courseID.getText());

            int affectedRows = prepare.executeUpdate();

            if (affectedRows > 0) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Update Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Updated!");
                alert.showAndWait();

                courseInventoryShowData();
                courseInventoryClearBtn();
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
    public void courseInventoryClearBtn() {
        courseID.clear();
        courseName.clear();
        coursePrice.clear();
        instructName.clear();
        courseDescription.clear();
        courseExtraInfo.clear();
        courseCategory.getSelectionModel().clearSelection();
        course_type.getSelectionModel().clearSelection();
    }
    public void courseInventoryDeleteBtn() {
        if (courseID.getText().isEmpty()) {
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
            String deleteQuery = "DELETE FROM courseInfo WHERE courseID=?";

            try {
                prepare = connect.prepareStatement(deleteQuery);
                prepare.setString(1, courseID.getText());

                int affectedRows = prepare.executeUpdate();

                if (affectedRows > 0) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Delete Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                    courseInventoryShowData();
                    courseInventoryClearBtn();
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

    //MERGE DATA ON TABLE
    public ObservableList<CourseData> getCourseListData() {
        ObservableList<CourseData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM courseInfo";
        connect = database.connectDB();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            CourseData data;
            while (result.next()) {
                data = new CourseData(
                        result.getInt("courseID"),
                        result.getString("courseName"),
                        result.getDouble("price"),
                        result.getString("instructorName"),
                        result.getString("userName"),
                        result.getString("description"),
                        result.getString("image"),
                        result.getString("info"),
                        result.getString("category"),
                        result.getString("type"),
                        result.getDate("date")
                );
                listData.add(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    // Show Data on table
    private ObservableList<CourseData> courseInventoryList;
    public void courseInventoryShowData() {
        courseInventoryList = getCourseListData();

        instructorIn_col_courseID.setCellValueFactory(new PropertyValueFactory<>("courseID"));
        instructorIn_col_courseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        instructorIn_col_coursePrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        instructorIn_col_courseType.setCellValueFactory(new PropertyValueFactory<>("type"));
        instructorIn_col_courseCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        instructorIn_col_courseEInfo.setCellValueFactory(new PropertyValueFactory<>("info"));
        instructorIn_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        instructorTable.setItems(courseInventoryList);
    }

    //health assistace functions

    public void healthAssistanceAddBtn(){
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
                    "( cardID, docName, userName, docMajor, start, end, fee, day, service, location, date,hospitalName)" +
                    "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
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
                prepare.setString(9,serviceType.getSelectionModel().getSelectedItem().toString());
                prepare.setString(10, extraInfo.getText());


                java.util.Date date = new java.util.Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                prepare.setString(11, sqlDate.toString());
                prepare.setString(12, hospital.getText());

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

        String checkExistingData = "SELECT * FROM hassisanceinfo WHERE id=? AND userName=?";
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
            prepare.setString(1, data.username);
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
                Stage stage=new Stage();
                Scene scene = new Scene(fxmlLoader.load());
                //stage.initStyle(StageStyle.UNDECORATED);
                stage.setTitle("EasyNeeds");
                stage.setScene(scene);
                stage.show();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public ObservableList<customersData> customersDataList() {

        ObservableList<customersData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM user";
        connect = database.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            customersData cData;

            while (result.next()) {
                cData = new customersData(result.getInt("id"),
                        result.getDate("Date"),
                        result.getString("Name"),
                        result.getString("occupation"));
                listData.add(cData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<customersData> customersListData;

    public void customersShowData() {
        customersListData = customersDataList();
//
//        customers_col_customerID.setCellValueFactory(new PropertyValueFactory<>("id"));
//        customers_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
//        customers_col_occupation.setCellValueFactory(new PropertyValueFactory<>("occupation"));
//        customers_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
//
//        customers_tableView.setItems(customersListData);
    }



//Static options

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
    public void logout() {
        try {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                logoutBtn.getScene().getWindow().hide();
                Stage stage=new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("loginsignup.fxml"));
                //stage.initStyle(StageStyle.UNDECORATED);
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
    public void optionAdder(){
        List<String> mtype = new ArrayList<>(Arrays.asList(mealTypeOption));
        List<String> delType = new ArrayList<>(Arrays.asList(mealDeliveryOption));
        List<String> billType = new ArrayList<>(Arrays.asList(billOption));
        List<String> catgoryType=new ArrayList<>(Arrays.asList(categoryOption));
        List<String> prodTyp=new ArrayList<>(Arrays.asList(prodTypeOption));
        ObservableList mealData= FXCollections.observableArrayList(mtype);
        ObservableList deliveryData= FXCollections.observableArrayList(delType);
        ObservableList billData= FXCollections.observableArrayList(billType);
        ObservableList categoryData=FXCollections.observableArrayList(catgoryType);
        ObservableList prodTypeData=FXCollections.observableArrayList(prodTyp);
        ObservableList weekData= FXCollections.observableArrayList(daysInWeeks);
        ObservableList serviceData= FXCollections.observableArrayList(service);
        serviceType.setItems(serviceData);
        availableOn.setItems(weekData);
        mealType.setItems(mealData);
        mealDelivery.setItems(deliveryData);
        billPay.setItems(billData);
        prodType.setItems(prodTypeData);
        prodCat.setItems(categoryData);
        List<String> courseCatgoryType=new ArrayList<>(Arrays.asList(courseCategoryOption));
        List<String> CourseType=new ArrayList<>(Arrays.asList(courseTypeOption));
        ObservableList courseCategoryData= FXCollections.observableArrayList(courseCatgoryType);
        ObservableList courseTypeData=FXCollections.observableArrayList(CourseType);
        courseCategory.setItems(courseTypeData);
        course_type.setItems(courseCategoryData);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dashboard_form.setVisible(true);
        rentIn_form.setVisible(false);
        catering_form.setVisible(false);
        Clothing_Form.setVisible(false);
        instructor_form.setVisible(false);
        displayUsername();
        yesOrNo();
        connect = database.connectDB();
        courseInventoryShowData();
        getRentListData();
        rentInventoryShowData();
        cateringInventoryShowData();
        clothShopInventoryShowData();
        healthAssistanceShowData();
        optionAdder();

    }
    public void handleEvent(ActionEvent event) {
        if (event.getSource() == food_btn) {
            dashboard_form.setVisible(false);
            rentIn_form.setVisible(false);
            catering_form.setVisible(true);
            Clothing_Form.setVisible(false);
            instructor_form.setVisible(false);
            health_Form.setVisible(false);
        } else if (event.getSource() == dashboard_btn) {
            dashboard_form.setVisible(true);
            rentIn_form.setVisible(false);
            catering_form.setVisible(false);
            Clothing_Form.setVisible(false);
            instructor_form.setVisible(false);
            health_Form.setVisible(false);
        } else if (event.getSource() == rent_btn) {
            dashboard_form.setVisible(false);
            rentIn_form.setVisible(true);
            catering_form.setVisible(false);
            Clothing_Form.setVisible(false);
            instructor_form.setVisible(false);
            health_Form.setVisible(false);
        }else if (event.getSource()==clothing_btn){
            dashboard_form.setVisible(false);
            rentIn_form.setVisible(false);
            catering_form.setVisible(false);
            Clothing_Form.setVisible(true);
            instructor_form.setVisible(false);
            health_Form.setVisible(false);
        }else if(event.getSource() == customer_btn){
            dashboard_form.setVisible(false);
            rentIn_form.setVisible(false);
            health_Form.setVisible(false);
            catering_form.setVisible(false);
            Clothing_Form.setVisible(false);
            instructor_form.setVisible(false);
        }else if(event.getSource()==hospital_btn){
            dashboard_form.setVisible(false);
            rentIn_form.setVisible(false);
            health_Form.setVisible(true);
            catering_form.setVisible(false);
            Clothing_Form.setVisible(false);
            instructor_form.setVisible(false);
        }else if (event.getSource() ==edu_btn) {
            dashboard_form.setVisible(false);
            rentIn_form.setVisible(false);
            catering_form.setVisible(false);
            Clothing_Form.setVisible(false);
            instructor_form.setVisible(true);
            health_Form.setVisible(false);
        }
    }

}