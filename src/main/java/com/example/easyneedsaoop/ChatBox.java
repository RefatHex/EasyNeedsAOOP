package com.example.easyneedsaoop;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
import java.util.ResourceBundle;

public class ChatBox implements Initializable {
    @FXML
    private Button button_send;
    @FXML
    private TextField tf_message;
    @FXML
    private VBox vbox_messages;
    @FXML
    private ScrollPane sp_main;
    private Client client;
    private String username = data.username;
    private String targetUsername;
    @FXML
    private Button backBtn;

    public void setData(String targetUsername) {
        this.targetUsername = targetUsername;
        initializeClient();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vbox_messages.heightProperty().addListener((observable, oldValue, newValue) ->
                sp_main.setVvalue((Double) newValue));

        if (targetUsername == null) {
            targetUsername = "defaultTargetUsername";
        }

        initializeClient();

        button_send.setOnAction(event -> {
            String messageToSend = tf_message.getText();
            if (!messageToSend.isEmpty()) {
                addMessageToUI("You: " + messageToSend, true);
                new Thread(() -> client.sendMessage(messageToSend)).start();
                tf_message.clear();
            }
        });
    }

    private void initializeClient() {
        try {
            client = new Client(new Socket("localhost", 5555), username, targetUsername, this);
            System.out.println("Connected to server");
            client.listenForMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void backBtnAction() {
        backBtn.getScene().getWindow().hide();
    }

    public void receiveMessageFromServer(String message) {
        addLabel(message);
    }

    // Add a message to the UI
    private void addMessageToUI(String message, boolean isSentByMe) {
        Platform.runLater(() -> {
            HBox hBox = new HBox();
            hBox.setAlignment(isSentByMe ? Pos.CENTER_RIGHT : Pos.CENTER_LEFT);
            hBox.setPadding(new Insets(5, 5, 5, 10));

            Text text = new Text(message);
            TextFlow textFlow = new TextFlow(text);
            textFlow.setStyle("-fx-color: rgb(239,242,255); " +
                    "-fx-background-color: " + (isSentByMe ? "rgb(15,125,242)" : "rgb(233,233,235)") +
                    ";-fx-background-radius: 20px;");

            textFlow.setPadding(new Insets(5, 10, 5, 10));
            text.setFill(isSentByMe ? Color.color(0.934, 0.945, 0.996) : Color.BLACK);

            hBox.getChildren().add(textFlow);
            vbox_messages.getChildren().add(hBox);
        });
    }

    // Add this method to handle incoming messages and add them to the UI
    public void addLabel(String message) {
        Platform.runLater(() -> {
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER_LEFT);
            hBox.setPadding(new Insets(5, 5, 5, 10));

            Text text = new Text(message);
            TextFlow textFlow = new TextFlow(text);
            textFlow.setStyle("-fx-background-color: rgb(233,233,235); " +
                    "-fx-background-radius: 20px;");

            textFlow.setPadding(new Insets(5, 10, 5, 10));
            hBox.getChildren().add(textFlow);

            vbox_messages.getChildren().add(hBox);
        });
    }
}