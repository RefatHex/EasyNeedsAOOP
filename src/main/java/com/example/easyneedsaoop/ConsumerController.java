package com.example.easyneedsaoop;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class ConsumerController implements Initializable {

    @FXML
    private ImageView DoctorIcon;

    @FXML
    private ImageView DoctorIcon1;

    @FXML
    private ImageView HospitalIcon;

    @FXML
    private ImageView HospitalIcon1;

    @FXML
    private ImageView clothingIcon;

    @FXML
    private ImageView clothingIcon1;

    @FXML
    private ImageView eduationIcon;

    @FXML
    private ImageView eduationIcon1;

    @FXML
    private ImageView foodIcon;

    @FXML
    private ImageView foodIcon1;

    @FXML
    private ImageView homeImage;

    @FXML
    private ImageView homeImage1;

    @FXML
    private Button menu;

    @FXML
    private ImageView menuback;

    @FXML
    private Hyperlink myAccountButton;

    @FXML
    private Hyperlink rewardsLink;

    @FXML
    private ImageView shoppingIcon;

    @FXML
    private AnchorPane slider;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        slider.setTranslateX(-176);
     menu.setOnMouseClicked(mouseEvent -> {
         TranslateTransition slide = new TranslateTransition();
         slide.setDuration((Duration.seconds(0.4)));
         slide.setNode(slider);

         slide.setToX(0);
         slide.play();

         slider.setTranslateX(-176);

         slide.setOnFinished((ActionEvent e)-> {
             menu.setVisible(false);
             menuback.setVisible(true);
         });
         });

        menuback.setOnMouseClicked(mouseEvent -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration((Duration.seconds(0.4)));
            slide.setNode(slider);

            slide.setToX(-176);
            slide.play();

            slider.setTranslateX(0);

            slide.setOnFinished((ActionEvent e)-> {
                menu.setVisible(true);
                menuback.setVisible(false);
            });
        });

}
    }

