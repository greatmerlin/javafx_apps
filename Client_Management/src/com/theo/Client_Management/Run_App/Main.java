package com.theo.Client_Management.Run_App;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../Main_Window/MainWindow.fxml"));
        primaryStage.setTitle("Business App");
        primaryStage.setScene(new Scene(root, 540, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
