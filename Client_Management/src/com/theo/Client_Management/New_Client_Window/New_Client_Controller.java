package com.theo.Client_Management.New_Client_Window;

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

public class New_Client_Controller implements Initializable {

    public void backToMainWindowButtonPushed(ActionEvent event) throws IOException {

        Parent newClientScene = FXMLLoader.load(getClass().getResource("../Main_Window/MainWindow.fxml"));

        Scene addClient = new Scene(newClientScene,600,400);
        Stage mainStage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        mainStage.setScene(addClient);
        mainStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
