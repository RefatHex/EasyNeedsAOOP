package com.example.easyneedsaoop;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class ChatBox implements Initializable {

    @FXML
    private TextField msgBox;

    @FXML
    private Label receivername;
    @FXML
    private Button sendBtn;


    private ObservableList<messages> messageList = FXCollections.observableArrayList();
    public String senderUsername=data.username;
    public String receiverUsername;
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    @FXML
    private Button button_send;
    @FXML
    private TextField tf_message;
    @FXML
    private VBox vbox_messages;
    @FXML
    private ScrollPane sp_main;
    private Client client;

    public void setData(String receiverUsername){
        this.receiverUsername=receiverUsername;
    }

    private void retrievemessages() {
        try {
            connect = database.connectDB();
            String sql = "SELECT id, message, senderUsername, receiverUsername FROM messages WHERE receiverUsername = ? AND senderUsername = ?";
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, receiverUsername);
            prepare.setString(2, senderUsername);
            result = prepare.executeQuery();

            messages retrievedMessage;
            while (result.next()) {
                retrievedMessage = new messages(
                        result.getInt("id"),
                        result.getString("message"),
                        result.getString("senderUsername"),
                        result.getString("receiverUsername")
                );
                messageList.add(retrievedMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) result.close();
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vbox_messages.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                sp_main.setVvalue((Double) newValue);
            }
        });

        client.listenForMessage();

        buttonAction();
    }

    public void buttonAction(){
            String messageToSend = tf_message.getText();
            if(!messageToSend.isEmpty()) {
                HBox hBox = new HBox();
                hBox.setAlignment(Pos.CENTER_RIGHT);

                hBox.setPadding(new Insets(5,5,5,10));
                Text text = new Text(messageToSend);
                TextFlow textFlow = new TextFlow(text);
                textFlow.setStyle("-fx-color: rgb(239,242,255); " +
                        "-fx-background-color: rgb(15,125,242);" +
                        "-fx-background-radius: 20px;");

                textFlow.setPadding(new Insets(5,10,5,10));
                text.setFill(Color.color(0.934,0.945,0.996));

                hBox.getChildren().add(textFlow);
                vbox_messages.getChildren().add(hBox);


                client.sendMessage(messageToSend);
                tf_message.clear();
            }
        }
    public static void addLabel(String msgFromServer,VBox vBox){
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(5,5,5,10));

        Text text = new Text(msgFromServer);
        TextFlow textFlow = new TextFlow(text);
        textFlow.setStyle("-fx-background-color: rgb(233,233,235); " +
                "-fx-background-radius: 20px;");

        textFlow.setPadding(new Insets(5,10,5,10));
        hBox.getChildren().add(textFlow);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vBox.getChildren().add(hBox);

            }
        });
    }

}

