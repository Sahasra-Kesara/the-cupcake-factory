package com.example.thebloomroom.Modules;

public class OrderClass {

    private String productId;
    private String categoryName;
    private String productName;
    private int quantity;
    private int date;


    public OrderClass() {}

    //Constructor

    public OrderClass(String productId, String categoryName, String productName, int quantity, int date) {
        this.productId = productId;
        this.categoryName = categoryName;
        this.productName = productName;
        this.quantity = quantity;
        this.date = date;
    }


    //Getters and Setters


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int price) {
        this.date = price;
    }
}
