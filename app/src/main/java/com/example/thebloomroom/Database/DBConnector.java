package com.example.thebloomroom.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBConnector extends SQLiteOpenHelper {

    public DBConnector(Context context){

        super(context,"DB_Bloom_Room",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table UserInfo (UserId VARCHAR PRIMARY KEY NOT NULL,Password VARCHAR,UserType VARCHAR)");
        sqLiteDatabase.execSQL("create table Category (CategoryID VARCHAR PRIMARY KEY NOT NULL, CategoryName VARCHAR)");
        sqLiteDatabase.execSQL("create table Product (ProductID VARCHAR PRIMARY KEY NOT NULL, ProductName VARCHAR, CategoryID VARCAHR, Price INTEGER, Quantity INTEGER, FOREIGN KEY(CategoryID) REFERENCES Category(CategoryID))");
        sqLiteDatabase.execSQL("create table Invoice (InvoiceID VARCHAR PRIMARY KEY NOT NULL, ProductID VARCHAR, Quantity INTEGER, Total INTEGER, FOREIGN KEY(ProductID) REFERENCES Product(ProductID))");
        sqLiteDatabase.execSQL("create table Orders (ProductID VARCHAR PRIMARY KEY NOT NULL, CategoryName VARCHAR, ProductName VARCHAR, Quantity INTEGER, Date INTEGER )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
