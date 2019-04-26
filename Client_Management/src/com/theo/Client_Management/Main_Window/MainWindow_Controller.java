package com.theo.Client_Management.Main_Window;

import com.theo.Client_Management.Model.Client;
import com.theo.Client_Management.New_Client_Window.New_Client_Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sun.misc.Cleaner;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainWindow_Controller implements Initializable {

    @FXML private TableView<Client> listOfClients;
    @FXML private TableColumn<Client, String> firstNameColumn;
    @FXML private TableColumn<Client, String> lastNameColumn;
    @FXML private TableColumn<Client, LocalDate> dateOfBirthColumn;
    @FXML private AnchorPane mainAnchorPane;

    public void newClientButtonPushed(ActionEvent event) throws IOException {

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainAnchorPane.getScene().getWindow());
        dialog.setTitle("Add a new Client");
        dialog.setHeaderText("Create a new Client");

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../New_Client_Window/New_Client.fxml"));

        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }catch (IOException e){
            System.out.println("Could't load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            New_Client_Controller controller = fxmlLoader.getController();
            Client newClient = controller.completedData();
            listOfClients.getSelectionModel().select(newClient);
        }


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("lastName"));
        dateOfBirthColumn.setCellValueFactory(new PropertyValueFactory<Client, LocalDate>("dateOfBirth"));

    }
}
