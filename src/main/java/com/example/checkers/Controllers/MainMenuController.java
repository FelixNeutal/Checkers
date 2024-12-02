package com.example.checkers.Controllers;

import com.example.checkers.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    public Button VsFriendBtn;
    public Button VsBotBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        VsFriendBtn.setOnAction(actionEvent -> vsFriendGame());
    }

    private void vsFriendGame() {
        //Close main menu stage
        Stage stage = (Stage) VsFriendBtn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showGameScreen();
        //Game screen via viewfactory
    }
}
