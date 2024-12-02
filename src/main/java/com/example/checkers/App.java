package com.example.checkers;

import com.example.checkers.Models.Model;
import com.example.checkers.Views.ViewFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        ViewFactory viewFactory = Model.getInstance().getViewFactory();
        viewFactory.showMainMenu();
    }
}
