package com.example.task6d.model;

import java.io.Serializable;

public class Item implements Serializable {
    private int imageId;

    private String receiver;
    private String location;

    public Item(String receiver,String location) {

        this.receiver=receiver;
        this.location=location;
    }

    public int getImageId() {
        return imageId;
    }

    public String getReceiver(){

        return receiver;
    }

    public String getLocation(){

        return location;
    }
}

