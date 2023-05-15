package com.example.task6d.model;

public class newsModel {

    String receiver;
    String location;

    public newsModel(String receiver,String location) {

        this.receiver=receiver;
        this.location=location;
    }
    public String getReceiver(){
        return receiver;
    }
    public String getLocation(){
        return location;
    }

}
