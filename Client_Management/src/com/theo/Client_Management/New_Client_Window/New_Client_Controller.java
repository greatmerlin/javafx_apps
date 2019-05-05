package com.theo.Client_Management.New_Client_Window;

import com.theo.Client_Management.Model.Client;
import com.theo.Client_Management.Model.Client_Data;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class New_Client_Controller implements Initializable {

    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private DatePicker datePicker;
    @FXML private TextField incomeTextField;

    public Client processResults() {
        String firstName = firstNameTextField.getText().trim();
        String lastName = lastNameTextField.getText().trim();
        LocalDate pickDate = datePicker.getValue();
//        Integer income = Integer.parseInt(incomeTextField.getText());

        Client client = new Client(firstName, lastName, pickDate);   // put all the data into a client's instance
        Client_Data.getInstance().addClient(client);                 // use the DATA_Client class and put the client data into the Method "add client" from the Client_Data class
        return client;
    }

/*    ---- Alternative to ButtonType.CANCEL in Main Window -----

     public void backToMainWindowButtonPushed(ActionEvent event) throws IOException {

        Parent newClientScene = FXMLLoader.load(getClass().getResource("../Main_Window/MainWindow.fxml"));

        Scene addClient = new Scene(newClientScene,600,400);
        Stage mainStage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        mainStage.setScene(addClient);
        mainStage.show();
    }*/

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
