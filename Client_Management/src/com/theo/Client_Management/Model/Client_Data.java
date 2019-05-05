package com.theo.Client_Management.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

public class Client_Data {

    private static Client_Data instance = new Client_Data();    // client data (instance of itself) class
    private static String filename = "listOfClientsText.txt";   // .txt als text

    private ObservableList<Client> clients;   // list of Clients (in observable List)
    private DateTimeFormatter formatter;      // formatter for the date

    public static Client_Data getInstance() {   // getter for the Data_Client
        return instance;
    }

    private Client_Data() {
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");    //constructor (empty)  set the format of date
    }

    public ObservableList<Client> getClients() {    // getter for the observable list with client objects
        return clients;
    }

    public void addClient(Client client) {         // method to add a client instance to the clients observable list
        clients.add(client);
    }

    public void loadClients() throws IOException {          // Method to load the clients from the text file

        clients = FXCollections.observableArrayList();
        Path path = Paths.get(filename);
        BufferedReader br = Files.newBufferedReader(path);

        String input;

        try {
            while ((input = br.readLine()) != null) {
                String[] itemPieces = input.split("[\t ]");
                

//                String firstName = itemPieces[0];
//                String lastName = itemPieces[1];
//                String dateString = itemPieces[2];
//                String incomeString = itemPieces[3];

//                Integer income = Integer.parseInt(incomeString);
//                LocalDate date = LocalDate.parse(dateString, formatter);    //turn date to String
//
//                Client client = new Client(firstName, lastName, date, income);
//                clients.add(client);

            }

        } finally {
            if(br != null) {
                br.close();
            }
        }
    }

    public void storeClients() throws IOException {             // store clients into the file text

        Path path = Paths.get(filename);
        BufferedWriter bw = Files.newBufferedWriter(path);
        try {
            Iterator<Client> iter = clients.iterator();
            while(iter.hasNext()) {
                Client client = iter.next();
                bw.write(String.format("%s\t%s\t%s",
                        client.getFirstName(),
                        client.getLastName(),
                        client.getDateOfBirth().format(formatter)));
                bw.newLine();
            }

        } finally {
            if(bw != null) {
                bw.close();
            }
        }
    }

    public void deleteClient(Client client) {
        clients.remove(client);
    }

}
