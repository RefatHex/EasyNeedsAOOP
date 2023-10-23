package com.example.easyneedsaoop;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class ConsumerPage {

    @FXML
    private ImageView DoctorIcon1;

    @FXML
    private ImageView HospitalIcon1;

    @FXML
    private ImageView clothingIcon1;

    @FXML
    private ImageView eduationIcon1;

    @FXML
    private ImageView foodIcon1;

    @FXML
    private ImageView homeImage1;

    @FXML
    private Button menuBtn;

    @FXML
    private ImageView menuback;

    @FXML
    private Hyperlink myAccountButton;

    @FXML
    private Hyperlink rewardsLink;

    @FXML
    private ImageView shoppingIcon;

    @FXML
    private AnchorPane sidePanel;
    private boolean slided = false;

    public void menuBtn(){
         TranslateTransition slider = new TranslateTransition();
            if (slided) {
                slider.setToX(-100);
                slider.setOnFinished((ActionEvent e) -> {
                    sidePanel.setVisible(false);
                });
            } else {
                sidePanel.setVisible(true);
                slider.setToX(0);
            }

            slider.setNode(sidePanel);
            slider.setDuration(Duration.seconds(.5));
            slider.play();

            slided = !slided;
    }
}
