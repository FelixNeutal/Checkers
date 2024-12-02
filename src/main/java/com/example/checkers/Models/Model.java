package com.example.checkers.Models;

import com.example.checkers.Views.ViewFactory;

public class Model {
    private static Model instance = null;
    private ViewFactory viewFactory = null;

    private Model() {
        viewFactory = new ViewFactory();
    }

    public static Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }
}
