package com.skyver.mytestproject;

import android.graphics.drawable.Drawable;

/**
 * Created by skyver on 7/14/17.
 */

public class Cards {
    private String eventName;
    private String eventTime;
    private String eventHost;
    private String eventPlace;
    private String eventAddress;
    private String eventDescription;
    private Drawable picture;

    public Cards(String eventName,
                 String eventTime,
                 String eventHost,
                 String eventPlace,
                 String eventAddress,
                 String eventDescription,
                 Drawable picture) {
        this.eventName = eventName;
        this.eventTime = eventTime;
        this.eventHost = eventHost;
        this.eventPlace = eventPlace;
        this.eventAddress = eventAddress;
        this.eventDescription = eventDescription;
        this.picture = picture;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventHost() {
        return eventHost;
    }

    public void setEventHost(String eventHost) {
        this.eventHost = eventHost;
    }

    public String getEventPlace() {
        return eventPlace;
    }

    public void setEventPlace(String eventPlace) {
        this.eventPlace = eventPlace;
    }

    public String getEventAddress() {
        return eventAddress;
    }

    public void setEventAddress(String eventAddress) {
        this.eventAddress = eventAddress;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public Drawable getPicture() {
        return picture;
    }

    public void setPicture(Drawable picture) {
        this.picture = picture;
    }
}
