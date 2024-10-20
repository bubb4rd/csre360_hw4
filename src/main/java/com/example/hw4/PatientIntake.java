package com.example.hw4;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PatientIntake {
    private SceneController sceneController;

    public PatientIntake(SceneController sceneController) {
        this.sceneController = sceneController;
    }
    public Scene getScene() {
        Scene mainScene = sceneController.getCurrentScene();
        VBox root = new VBox(25);
        root.setPadding(new Insets(25, 25, 25, 25));
        Label titleLabel = new Label("Patient Intake Form");
        titleLabel.getStyleClass().add("h1");
        titleLabel.getStyleClass().add("text-xl");
        titleLabel.setAlignment(Pos.TOP_CENTER);

        HBox firstNameOption = new HBox(25);
        Label firstName = new Label("First Name");
        TextField firstNameInput = new TextField();
        firstNameOption.getChildren().addAll(firstName, firstNameInput);

        HBox lastNameOption = new HBox(30);
        Label lastName = new Label("Last Name");
        TextField lastNameInput = new TextField();
        lastNameOption.getChildren().addAll(lastName, lastNameInput);

        HBox emailOption = new HBox(55);
        Label email = new Label("Email");
        TextField emailInput = new TextField();
        emailOption.getChildren().addAll(email, emailInput);

        HBox phoneOption = new HBox(50);
        Label phone = new Label("Phone");
        TextField phoneInput = new TextField();
        phoneOption.getChildren().addAll(phone, phoneInput);

        HBox healthOption = new HBox(10);
        Label health = new Label("Health History");
        TextField healthInput = new TextField();
        healthOption.getChildren().addAll(health, healthInput);

        HBox insuranceOption = new HBox(20);
        Label insurance = new Label("Insurance ID");
        TextField insuranceInput = new TextField();
        insuranceOption.getChildren().addAll(insurance, insuranceInput);

        Button saveButton = new Button("Save");
        saveButton.getStyleClass().add("main-button");
        saveButton.getStyleClass().add("w-250px");


        root.getChildren().addAll(titleLabel, firstNameOption, lastNameOption, emailOption, phoneOption, healthOption, insuranceOption, saveButton);

        Scene intakeScene = new Scene(root, mainScene.getWidth(), mainScene.getHeight());
        String css = getClass().getResource("/com/example/hw4/css/Main.css").toExternalForm();
        intakeScene.getStylesheets().add(css);
        saveButton.setOnAction(e -> {
            if (firstNameInput.getText().isEmpty() || lastNameInput.getText().isEmpty() || emailInput.getText().isEmpty() || phoneInput.getText().isEmpty() || healthInput.getText().isEmpty() || insuranceInput.getText().isEmpty()) {

            } else {
                Patient newPatient = new Patient(firstNameInput.getText(), lastNameInput.getText(), emailInput.getText(), phoneInput.getText(), healthInput.getText(), insuranceInput.getText());
                newPatient.addPatient();
                sceneController.previousScene();
            }
        });
        return intakeScene;
    }
}
