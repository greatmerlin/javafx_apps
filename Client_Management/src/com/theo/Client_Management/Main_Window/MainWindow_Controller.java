package com.theo.Client_Management.Main_Window;

import com.theo.Client_Management.Model.Client;
import com.theo.Client_Management.Model.Client_Data;
import com.theo.Client_Management.New_Client_Window.New_Client_Controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import sun.misc.Cleaner;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
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
            Client newClient = controller.processResults();
            listOfClients.getSelectionModel().select(newClient);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

/*        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("lastName"));
        dateOfBirthColumn.setCellValueFactory(new PropertyValueFactory<Client, LocalDate>("dateOfBirth"));*/

        listOfClients = new TableView<>();                                      //be able to right click delete a client
        MenuItem deleteMenuItem = new MenuItem("Delete");
        deleteMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Client client = listOfClients.getSelectionModel().getSelectedItem();
                deleteItem(client);
            }
        });

//        listOfClients.getItems().addAll((Collection<? extends Client>) deleteMenuItem);
        listOfClients.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Client>() {        // load everything
            @Override
            public void changed(ObservableValue<? extends Client> observable, Client oldValue, Client newValue) {
                if(newValue != null) {
                    Client client = listOfClients.getSelectionModel().getSelectedItem();
                    firstNameColumn.setText(client.getFirstName());
                    lastNameColumn.setText(client.getLastName());
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM d, yyyy"); // "d M yy");
                    dateOfBirthColumn.setText(df.format(client.getDateOfBirth()));
                }
            }
        });

        listOfClients.setItems(Client_Data.getInstance().getClients());                 // or from here load everything
        listOfClients.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listOfClients.getSelectionModel().selectFirst();


}


    public void deleteItem(Client client) {                              // Method to delete a client with a confirmation window pop up
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Todo Item");
        alert.setHeaderText("Delete item: " + client.getFirstName());
        alert.setContentText("Are you sure?  Press OK to confirm, or cancel to Back out.");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && (result.get() == ButtonType.OK)) {
            Client_Data.getInstance().deleteClient(client);
        }

    }
}
