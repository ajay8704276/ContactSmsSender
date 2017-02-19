package com.app.kisan.network.demo.Model;


public class Contacts {

    private String fisrtName ;
    private String lastName ;
    private long contactNumber;

    public Contacts(String fisrtName, String lastName, long contactNumber) {
        this.fisrtName = fisrtName;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
    }

    public Contacts(){

    }

    public String getFisrtName() {
        return fisrtName;
    }

    public void setFisrtName(String fisrtName) {
        this.fisrtName = fisrtName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(long contactNumber) {
        this.contactNumber = contactNumber;
    }
}