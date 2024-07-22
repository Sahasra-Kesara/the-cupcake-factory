package com.example.thebloomroom.Modules;

public class UserClass {

    private String userId;
    private String userPassword;
    private String userType;

    //Empty constructor
    public UserClass(){}

    //Constructor
    public UserClass(String userId, String userPassword, String userType) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userType = userType;
    }

    //Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

}
