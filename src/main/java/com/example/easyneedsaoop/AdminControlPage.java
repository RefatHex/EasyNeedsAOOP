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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminControlPage implements Initializable {
    @FXML
    private Button clothing_btn;

    @FXML
    private Button customer_btn;

    @FXML
    private AnchorPane dashboardForm;

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
    private TableColumn<rentData, String> rentIn_col_person;

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
    private TextField rentIn_person;

    @FXML
    private TextField rentIn_rooms;

    @FXML
    private ComboBox<?> rentIn_subletOption;

    @FXML
    private Button rent_btn;

    @FXML
    private Label userName;

    @FXML
    private TableColumn<?, ?> rentIn_col_date;
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private Alert alert;
    private boolean[] opinionList={true,false};

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
                      result.getInt("person"),
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
    private ObservableList<rentData> rentInventoryList;
    public void rentInventoryShowData(){
        rentInventoryList=getListData();
        rentIn_col_Id.setCellValueFactory(new PropertyValueFactory<>("id"));
        rentIn_col_owner.setCellValueFactory(new PropertyValueFactory<>("ownerName"));
        rentIn_col_houseName.setCellValueFactory(new PropertyValueFactory<>("houseName"));
        rentIn_col_roomNo.setCellValueFactory(new PropertyValueFactory<>("room"));
        rentIn_col_person.setCellValueFactory(new PropertyValueFactory<>("person"));
        rentIn_col_contact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        rentIn_col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        rentIn_col_eInfo.setCellValueFactory(new PropertyValueFactory<>("einfo"));
        rentIn_col_bachelor.setCellValueFactory(new PropertyValueFactory<>("bachelor"));
        rentIn_col_sublet.setCellValueFactory(new PropertyValueFactory<>("sublet"));
        rentIn_col_dinning.setCellValueFactory(new PropertyValueFactory<>("dn_draw"));
        rentIn_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        rentInTable.setItems(rentInventoryList);
    }


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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayUsername();
        yesOrNo();
        getListData();
        rentInventoryShowData();
    }

    public void signoutButtonAction(ActionEvent actionEvent) {
    }
}
