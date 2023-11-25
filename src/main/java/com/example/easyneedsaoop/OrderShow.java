package com.example.easyneedsaoop;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class OrderShow {

    @FXML
    private Label customer_name;

    @FXML
    private Button details_btn;

    @FXML
    private Label date;

    @FXML
    private Label house_Name;
    rentOrderData data;

    public void setData(rentOrderData data){
        this.data=data;
        customer_name.setText(data.getOwnerName());
        house_Name.setText(data.getHouseName());
        date.setText(String.valueOf(data.getDate()));
    }
}
