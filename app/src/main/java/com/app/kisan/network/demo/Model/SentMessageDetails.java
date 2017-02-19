package com.app.kisan.network.demo.Model;

/**
 * Created by adyro on 18-02-2017.
 */

public class SentMessageDetails {

    private String contactName;
    private String currentDateAndTime;

    public SentMessageDetails() {

    }

    public SentMessageDetails(String contactName, String currentDateAndTime) {
        this.contactName = contactName;
        this.currentDateAndTime = currentDateAndTime;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getCurrentDateAndTime() {
        return currentDateAndTime;
    }

    public void setCurrentDateAndTime(String currentDateAndTime) {
        this.currentDateAndTime = currentDateAndTime;
    }
}
