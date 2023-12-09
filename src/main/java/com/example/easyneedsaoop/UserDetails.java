package com.example.easyneedsaoop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class UserDetails implements Initializable {
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    @FXML
    private Button change_btn;

    @FXML
    private Label name;

    @FXML
    private TextField new_pass;

    @FXML
    private TextField old_pass;

    @FXML
    private Label user_id;

    @FXML
    private Label username;
    int count=0;

    public void setData(){
        userData user=getUserListData(data.username);
        if(user!=null){
            user_id.setText(String.valueOf(user.getId()));
            username.setText(data.username);
            name.setText(user.getName());
        }else{
            System.out.println("no user in this name");
        }
    }
    public userData getUserListData(String targetUsername) {
        String sql = "SELECT * FROM user WHERE username = ?";
        connect = database.connectDB();
        userData data = null;
        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, targetUsername);
            result = prepare.executeQuery();
            if (result.next()) {
                data = new userData(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getString("username"),
                        result.getString("password")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
    public void handlePassChange(){
        String targetUsername = username.getText();
        String oldPasswordInput = old_pass.getText();
        String newPasswordInput = new_pass.getText();
        userData currentUserData=getUserListData(data.username);
        if (currentUserData != null && currentUserData.getPassword().equals(oldPasswordInput)) {
            currentUserData.setPassword(newPasswordInput);

            if (updateUserPassword(currentUserData.getId(), newPasswordInput)) {
                System.out.println("Password updated successfully!");

            } else {
                System.out.println("Error updating password. Please try again.");

            }

            old_pass.clear();
            new_pass.clear();
        } else {
            System.out.println("Incorrect old password. Please try again.");
        }
    }

    public boolean updateUserPassword(int userId, String newPassword) {
        String sql = "UPDATE user SET password = ? WHERE id = ?";
        connect = database.connectDB();
        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, newPassword);
            prepare.setInt(2, userId);
            int rowsAffected = prepare.executeUpdate();

            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
