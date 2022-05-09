package com.example.savvycoffee.models;

public class Users {
    String first_name;
    String last_name;
    String gender;
    String email;
    String phoneNo;
    String userName;
    String password;

    public Users(String first_name, String last_name, String gender, String email, String phoneNo, String userName, String password) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.email = email;
        this.phoneNo = phoneNo;
        this.userName = userName;
        this.password = password;
    }

    public String getFirstName() {
        return first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
