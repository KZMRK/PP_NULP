package com.kazmiruk.graphical_interface.app;

import com.kazmiruk.constans.Paths;
import com.kazmiruk.constans.StageNames;
import  javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Objects;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Paths.MAIN_SCENE)));
        Scene scene = new Scene(root);
        Image icon = new Image(Paths.ICON);
        stage.getIcons().add(icon);
        stage.setTitle(StageNames.TOURIST_TICKETS);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
