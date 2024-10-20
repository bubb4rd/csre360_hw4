package com.example.hw4;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    static SceneController sceneController;
    @Override
    public void start(Stage stage) throws IOException {
        VBox root = new VBox(100);
        Label titleLabel = new Label("Welcome to the Heart Health Imaging and Recording System");
        titleLabel.getStyleClass().add("text-xl");
        titleLabel.getStyleClass().add("h1");

        VBox buttonContainer = new VBox(10);
        buttonContainer.setAlignment(Pos.CENTER);

        Button intakeButton = new Button("Patient Intake");
        intakeButton.getStyleClass().add("main-button");
        intakeButton.getStyleClass().add("text-xl");



        Button ctButton = new Button("CT Scan Tech View");
        ctButton.getStyleClass().add("main-button");
        ctButton.getStyleClass().add("text-xl");


        Button patientView = new Button("Patient View");
        patientView.getStyleClass().add("main-button");
        patientView.getStyleClass().add("text-xl");

        buttonContainer.getChildren().addAll(intakeButton, ctButton, patientView);
        root.setAlignment(Pos.TOP_CENTER);
        root.getChildren().addAll(titleLabel, buttonContainer);

        Scene mainScene = new Scene(root, 700, 500);
        String css = getClass().getResource("/com/example/hw4/css/Main.css").toExternalForm();
        mainScene.getStylesheets().add(css);
        stage.setTitle("Heart Health Imaging and Recording System");
        stage.setScene(mainScene);
        SceneController sceneController = new SceneController(stage);
        intakeButton.setOnAction(e -> {
            PatientIntake intakeForm = new PatientIntake(sceneController);
            sceneController.switchScene(intakeForm.getScene());
        });
        patientView.setOnAction(e -> {
            PatientView patientForm = new PatientView(sceneController);
            sceneController.switchScene(patientForm.getScene());
        });
        ctButton.setOnAction(e -> {
            CTScanTech scanTechForm = new CTScanTech(sceneController);
            sceneController.switchScene(scanTechForm.getScene());
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}