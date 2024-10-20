package com.example.hw4;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;

public class PatientView {
    private Patient patient;
    private final SceneController sceneController;

    public PatientView(SceneController sceneController) {
        this.sceneController = sceneController;
    }

    public Scene getScene() {
        Scene mainScene = sceneController.getCurrentScene();
        AnchorPane pane = new AnchorPane();
        VBox root = new VBox(25);
        root.setPadding(new Insets(25, 25, 25, 25));
        Label titleLabel = new Label("Enter patient id");
        titleLabel.getStyleClass().add("h1");
        titleLabel.getStyleClass().add("text-xl");

        HBox hBox = new HBox(25);
        Label idLabel = new Label("Patient Id");
        TextField idField = new TextField();
        hBox.getChildren().addAll(idLabel, idField);

        Button submitButton = new Button("Submit");
        submitButton.getStyleClass().add("main-button");
        submitButton.getStyleClass().add("text-xl");
        submitButton.getStyleClass().add("w-250px");

        root.getChildren().addAll(titleLabel, hBox, submitButton);
        String css = getClass().getResource("/com/example/hw4/css/Main.css").toExternalForm();

        submitButton.setOnAction(e -> {
            if (idField.getText().length() == 5) {
                int id = Integer.parseInt(idField.getText());
                File patientInformation = Data.getFileFromID(id);
                Patient newPatient = Data.getPatientFromData(Data.parseData(patientInformation));
                assert newPatient != null;
                if (newPatient.getScores() == null) {
                    System.out.println("No score data.");
                }
                System.out.println(newPatient.toString());
            }
        });

        Button backButton = new Button("Back");
        backButton.getStyleClass().add("main-button");
        backButton.getStyleClass().add("text-xl");
        backButton.getStyleClass().add("h1");
        backButton.setOnAction(e -> {
            sceneController.previousScene();
        });
        pane.getChildren().addAll(root, backButton);
        Scene scene = new Scene(pane, mainScene.getWidth(), mainScene.getHeight());
        scene.getStylesheets().add(css);
        AnchorPane.setRightAnchor(backButton, 0.0);
        AnchorPane.setBottomAnchor(backButton, 0.0);
        return scene;
    }


}
