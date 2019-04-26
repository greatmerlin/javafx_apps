package com.theo.Client_Management.Main_Window;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindow_Controller implements Initializable {

    public void newClientButtonPushed(ActionEvent event) throws IOException {

        Parent newClientScene = FXMLLoader.load(getClass().getResource("../New_Client_Window/New_Client.fxml"));

        Scene addClient = new Scene(newClientScene,600,400);
        Stage mainStage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        mainStage.setScene(addClient);
        mainStage.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
