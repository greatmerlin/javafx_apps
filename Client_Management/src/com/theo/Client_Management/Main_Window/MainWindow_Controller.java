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
import javafx.scene.control.cell.TextFieldTableCell;
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
import java.util.Iterator;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainWindow_Controller implements Initializable {

    @FXML private TableView<Client> listOfClients;
    @FXML private TableColumn<Client, String> firstNameColumn;
    @FXML private TableColumn<Client, String> lastNameColumn;
    @FXML private TableColumn<Client, LocalDate> dateOfBirthColumn;
    @FXML private AnchorPane mainAnchorPane;

    @FXML private Button closeButton;                                       // data variable for the close Button

    @FXML private void handleCloseButton(){                                 // action for the close Button

        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void newClientButtonPushed(ActionEvent event) throws IOException {

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainAnchorPane.getScene().getWindow());
        dialog.setTitle("Add a new Client");
//        dialog.setHeaderText("Create a new Client");  // use a header instead of a label

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

        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("lastName"));
        dateOfBirthColumn.setCellValueFactory(new PropertyValueFactory<Client, LocalDate>("dateOfBirth"));

        listOfClients.setItems(Client_Data.getInstance().getClients());                  // access the Client_Data and get the Clients
        listOfClients.setItems(Client_Data.getInstance().getClients());                 // or from here load everything
        listOfClients.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listOfClients.getSelectionModel().selectFirst();

        listOfClients.setEditable(true);                                        // to be able to edit cells
        firstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());    // to be able to edit cells
        lastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());     // to be able to edit cells

        listOfClients.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);  // be able to select more than one cells at once
    }

    public void deleteSelectedClient() {

        ObservableList<Client> allClients;
        Client selectedClient;
        allClients = listOfClients.getItems();
        selectedClient = listOfClients.getSelectionModel().getSelectedItem();

        Iterator<Client> iter = allClients.iterator();
        while(iter.hasNext()) {
            Client client = iter.next();
            if (client.equals(selectedClient)) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Delete Todo Item");
                alert.setHeaderText("Delete item: " + selectedClient.getFirstName());
                alert.setContentText("Are you sure?  Press OK to confirm, or cancel to Back out.");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && (result.get() == ButtonType.OK)) {
                    iter.remove();
                }
            }
        }
    }

        public void editFirstName (TableColumn.CellEditEvent editedCell){

            Client clientSelected = listOfClients.getSelectionModel().getSelectedItem(); // return a person Object, the one that we select

            clientSelected.setFirstName(editedCell.getNewValue().toString());

        }

        public void editLastName (TableColumn.CellEditEvent editedCell){

            Client clientSelected = listOfClients.getSelectionModel().getSelectedItem(); // return a person Object, the one that we select

            clientSelected.setLastName(editedCell.getNewValue().toString());
        }
    }
