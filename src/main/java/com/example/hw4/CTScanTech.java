package com.example.hw4;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class CTScanTech {
    private final SceneController sceneController;

    public CTScanTech(SceneController sceneController) {
        this.sceneController = sceneController;
    }
    public Scene getScene() {
        Scene mainScene = sceneController.getCurrentScene();
        AnchorPane pane = new AnchorPane();

        HBox patientHBox = new HBox(200);
        Label patientIdLabel = new Label("Patient ID");
        TextField patientIdInput = new TextField();
        patientHBox.getChildren().addAll(patientIdLabel, patientIdInput);

        HBox cacScoreHBox = new HBox(100);
        Label cacScoreLabel = new Label("The total Agatston CAC score");
        TextField cacScoreInput = new TextField();
        cacScoreInput.setEditable(false);
        cacScoreHBox.getChildren().addAll(cacScoreLabel, cacScoreInput);

        VBox scoreData = new VBox(10);
        Label subtitleLabel = new Label("Vessel level Agatston CAC score");
        subtitleLabel.getStyleClass().add("h1");
        HBox lmScoreHBox = new HBox(100);
        Label lmScoreLabel = new Label("LM:");
        TextField lmScoreInput = new TextField();
        lmScoreHBox.getChildren().addAll(lmScoreLabel, lmScoreInput);

        HBox ladScoreHBox = new HBox(100);
        Label ladScoreLabel = new Label("LAD:");
        TextField ladScoreInput = new TextField();
        ladScoreHBox.getChildren().addAll(ladScoreLabel, ladScoreInput);

        HBox lcxScoreHBox = new HBox(100);
        Label lcxScoreLabel = new Label("LCX:");
        TextField lcxScoreInput = new TextField();
        lcxScoreHBox.getChildren().addAll(lcxScoreLabel, lcxScoreInput);

        HBox rcaScoreHBox = new HBox(100);
        Label rcaScoreLabel = new Label("RCA:");
        TextField rcaScoreInput = new TextField();
        rcaScoreHBox.getChildren().addAll(rcaScoreLabel, rcaScoreInput);

        HBox pdaScoreHBox = new HBox(100);
        Label pdaScoreLabel = new Label("PDA:");
        TextField pdaScoreInput = new TextField();
        pdaScoreHBox.getChildren().addAll(pdaScoreLabel, pdaScoreInput);

        Button saveButton = new Button("Save");
        saveButton.getStyleClass().add("main-button");
        saveButton.getStyleClass().add("w-250px");

        scoreData.getChildren().addAll(subtitleLabel, lmScoreHBox, ladScoreHBox, lcxScoreHBox, rcaScoreHBox, pdaScoreHBox, saveButton);
        pane.getChildren().addAll(patientHBox, cacScoreHBox, scoreData);
        patientHBox.setPadding(new Insets(25, 25, 25, 25));
        cacScoreHBox.setPadding(new Insets(25, 25, 25, 25));
        scoreData.setPadding(new Insets(25, 25, 25, 25));
        Scene scene = new Scene(pane, mainScene.getWidth(), mainScene.getHeight());
        String css = getClass().getResource("/com/example/hw4/css/Main.css").toExternalForm();
        scene.getStylesheets().add(css);
        AnchorPane.setTopAnchor(patientHBox, 0.0);
        AnchorPane.setTopAnchor(cacScoreHBox, 50.0);
        AnchorPane.setTopAnchor(scoreData, 100.0);

        ArrayList<TextField> scoreInputs = new ArrayList<>();
        scoreInputs.add(lmScoreInput);
        scoreInputs.add(ladScoreInput);
        scoreInputs.add(lcxScoreInput);
        scoreInputs.add(rcaScoreInput);
        scoreInputs.add(pdaScoreInput);

        scoreInputs.forEach(input -> {
            input.textProperty().addListener((observable, oldValue, newValue) -> {
                int totalScore = 0;
                for (TextField scoreInput : scoreInputs) {
                    try {
                        totalScore += Integer.parseInt(scoreInput.getText());
                    } catch (NumberFormatException e) {
                    }
                }
                cacScoreInput.setText(String.valueOf(totalScore));
            });
        });

        saveButton.setOnAction(e -> {

        });
        return scene;
    }
}
