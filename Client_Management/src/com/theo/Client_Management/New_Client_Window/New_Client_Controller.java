package com.theo.Client_Management.New_Client_Window;

import com.theo.Client_Management.Model.Client;
import com.theo.Client_Management.Main_Window.MainWindow_Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class New_Client_Controller implements Initializable {

    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private DatePicker datePicker;

    public Client completedData() {

        Client client = new Client(
                                    firstNameTextField.getText(),
                                    lastNameTextField.getText(),
                                    datePicker.getValue()
        );

        return client;
    }

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
