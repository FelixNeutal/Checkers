package com.example.checkers.Views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewFactory {
    public void showMainMenu() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/MainMenu.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void showGameScreen() {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/GameScreen.fxml"));
//        Scene scene = null;
//        try {
//            scene = new Scene(loader.load());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        Stage stage = new Stage();
//        stage.setScene(scene);
//        stage.show();
        GameView gameView = new GameView();
        gameView.run();
    }

    public void closeStage(Stage stage) {
        stage.close();
    }
}
