package com.example.convertor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        TextArea xmlArea = new TextArea();
        TextArea jsonArea = new TextArea();

        Button xmlToJsonApi = new Button("xml -> json (avec api)");
        xmlToJsonApi.setOnAction(e->jsonArea.setText(ApiConvertor.xmlToJson(xmlArea.getText())));

        Button jsonToXmlApi = new Button("json -> xml (avec api)");
        jsonToXmlApi.setOnAction(e->xmlArea.setText(ApiConvertor.jsonToXml(jsonArea.getText())));

        Button xmlToJsonManuel = new Button("xml -> json (sans api)");
        xmlToJsonManuel.setOnAction(e->jsonArea.setText(ManualConvertor.xmlToJson(xmlArea.getText())));

        Button jsonToXmlManuel = new Button("json -> xml (sans api)");
        jsonToXmlManuel.setOnAction(e->xmlArea.setText(ManualConvertor.jsonToXml(jsonArea.getText())));

        VBox root = new VBox(10, new Label("XML"), xmlArea,
                new Label("JSON"), jsonArea,
                xmlToJsonApi, jsonToXmlApi,
                xmlToJsonManuel, jsonToXmlManuel);


        stage.setScene(new Scene(root, 500,600));
        stage.setTitle("XML <-> JSON Convertor");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
