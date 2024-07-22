package com.example.thebloomroom.Modules;

public class CategoryClass {

    private String categoryID;
    private String categoryName;

    //Constructor
    public CategoryClass(String categoryID, String categoryName) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }


    public CategoryClass() {}

    //Getters and Setters
    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
