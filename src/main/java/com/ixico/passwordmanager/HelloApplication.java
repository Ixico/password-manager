package com.ixico.passwordmanager;

import atlantafx.base.theme.PrimerDark;
import atlantafx.base.theme.PrimerLight;
import com.ixico.passwordmanager.model.MainModel;
import com.ixico.passwordmanager.view.MainView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        Application.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());
        var model = new MainModel();
        var controller = new MainController(model);
        var view = new MainView(controller, model);
        var scene = new Scene(view.getParent(), 800, 480);
        stage.setResizable(false);
        stage.setTitle("On-the-fly");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}