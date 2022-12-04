package com.kazmiruk.graphical_interface.dialog;

import com.kazmiruk.constans.Paths;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Warning/information window
 */
public class AlertDialog {

    @FXML
    public static void display(String title, String message) {
        Stage stage = createAlertDialogStage(title);
        Button buttonOk = createOKButton();
        buttonOk.setOnAction(e -> stage.close());
        Label label = createAlterDialogMsgLabel(message);

        VBox layout = new VBox(5);
        layout.getChildren().addAll(label, buttonOk);
        layout.setAlignment(Pos.CENTER);

        Scene scene = createAlertDialogScene(layout);

        stage.setScene(scene);
        stage.showAndWait();
    }

    private static Stage createAlertDialogStage(String title) {
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setMinWidth(250);
        stage.setMaxHeight(100);
        Image icon = new Image(Paths.ICON);
        stage.getIcons().add(icon);
        stage.initModality(Modality.APPLICATION_MODAL);
        return stage;
    }

    private static Button createOKButton() {
        Button buttonOk = new Button("OK");
        buttonOk.getStyleClass().add("btn");
        return buttonOk;
    }

    private static Label createAlterDialogMsgLabel(String message) {
        Label label = new Label();
        label.setText(message);
        return label;
    }

    private static Scene createAlertDialogScene(VBox layout) {
        Scene scene = new Scene(layout);
        scene.getStylesheets().add(Objects.requireNonNull(AlertDialog.class.getResource("../styles/styles.css")).toExternalForm());
        return scene;
    }

}
