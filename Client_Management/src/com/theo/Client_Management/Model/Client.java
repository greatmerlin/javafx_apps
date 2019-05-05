package com.theo.Client_Management.Model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import java.time.LocalDate;

public class Client {

     private SimpleStringProperty firstName, lastName;
     private ObjectProperty<LocalDate> dateOfBirth;
//     private Integer income;

    public Client(String firstName, String lastName, LocalDate dateOfBirth) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.dateOfBirth = new SimpleObjectProperty<LocalDate>(this, "dateOfBirth", dateOfBirth);
//        this.income = income;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth.get();
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth.set(dateOfBirth);
    }

//    public String getIncome() {
//        return Integer.toString(this.income);
//    }
//
//    public Integer incomeProperty() {
//        return income;
//    }
//
//    public void setIncome(Integer income) {
//        this.income = income;
//    }
}
