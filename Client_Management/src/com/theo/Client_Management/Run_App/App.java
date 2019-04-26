package com.theo.Client_Management.Run_App;

import com.theo.Client_Management.Model.Client;
import com.theo.Client_Management.Model.Client_Data;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends javafx.application.Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../Main_Window/MainWindow.fxml"));
        primaryStage.setTitle("Business App");
        primaryStage.setScene(new Scene(root, 675, 424));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception {
        try {
            Client_Data.getInstance().storeClients();

        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void init() throws Exception {
        try {
            Client_Data.getInstance().loadClients();

        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
