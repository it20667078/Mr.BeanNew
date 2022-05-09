package com.example.savvycoffee.models;

public class Feedbacks {
    String uid;
    String comment;

    public Feedbacks(String uid, String comment) {
        this.uid = uid;
        this.comment = comment;
    }

    public String getUid() {
        return uid;
    }

    public String getComment() {
        return comment;
    }
}
