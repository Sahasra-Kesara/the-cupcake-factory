package com.example.thebloomroom.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.thebloomroom.Modules.CategoryClass;
import com.example.thebloomroom.Modules.InvoiceClass;
import com.example.thebloomroom.Modules.OrderClass;
import com.example.thebloomroom.Modules.ProductClass;
import com.example.thebloomroom.Modules.UserClass;

import java.util.ArrayList;
import java.util.Vector;

public class DBHelper {

    private Context context;
    private SQLiteDatabase sqLiteDatabase;

    public DBHelper(Context context){

        this.context = context;
    }

    public DBHelper openDB(){

        DBConnector dbCon = new DBConnector(context);
        sqLiteDatabase = dbCon.getWritableDatabase();
        return this;
    }



    //Register and login
    public boolean CreateNewUser (UserClass userClass){
        try {
            sqLiteDatabase.execSQL("insert into UserInfo values('"+userClass.getUserId()+"','"+userClass.getUserPassword()+"','"+userClass.getUserType()+"')");
            return true;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }


    public ArrayList<UserClass> ValidLogin(String UserId, String Password) {

        ArrayList<UserClass> userList = new ArrayList<>();
        try{

            Cursor cursor = sqLiteDatabase.rawQuery("select * from UserInfo where UserId = '"+UserId+"' and  Password = '"+Password+"' ", null);
            if(cursor.moveToNext())//T
            {
                do {
                    UserClass user = new UserClass();// If here shows an error go to the user class and put an empty constructor
                    user.setUserId(cursor.getString(0));
                    user.setUserPassword(cursor.getString(1));
                    user.setUserType(cursor.getString(2));
                    userList.add(user);
                } while (cursor.moveToNext());//F

            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return userList;
    }




    //Category
    public boolean InsertCategory(CategoryClass categoryClass) {
        try {
            sqLiteDatabase.execSQL("insert into Category values('"+categoryClass.getCategoryID() + "' , '" + categoryClass.getCategoryName()+ "' )");
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public Vector<String> getCategory_Name() {
        Vector<String> vecCategory = new Vector<String>();
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("Select CategoryName from Category ", null);
            if (cursor.moveToFirst()) {
                do {
                    vecCategory.add(cursor.getString(0));
                } while (cursor.moveToNext());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return vecCategory;
    }
    public String getCategory_Id(String categoryName) {
        String categoryId = null;
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("Select CategoryID from Category where CategoryName= '"+ categoryName +"'", null);
            if (cursor.moveToFirst()) {
                categoryId = cursor.getString(0);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return categoryId;
    }


    //Product
    public boolean insertProduct(ProductClass productClass) {
        try {
            sqLiteDatabase.execSQL("insert into Product values('" + productClass.getProductId() + "','" + productClass.getProductName() + "','" + productClass.getCategoryId() + "'," +
                    productClass.getPrice() + "," + productClass.getQuantity() + ")");
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    //Search product
    public ArrayList<ProductClass> searchProduct(String productName) {
        ArrayList<ProductClass> productList = new ArrayList<ProductClass>();
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("Select * from Product where ProductName = '"+ productName + "' ",  null);
            if (cursor.moveToFirst()) {
                do {
                    ProductClass product = new ProductClass();
                    product.setProductName(cursor.getString(0));
                    product.setProductId(cursor.getString(1));
                    product.setCategoryId(cursor.getString(2));
                    product.setPrice(cursor.getInt(3));
                    product.setQuantity(cursor.getInt(4));
                    productList.add(product);
                } while (cursor.moveToNext());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return productList;
    }
    public void buyProduct(String product_Id, int qty) {
        try {
            sqLiteDatabase.execSQL("update Product set Quantity=Quantity-" + qty + "where product_Id = '" + product_Id + "'");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public boolean insertInvoice(InvoiceClass invoice) {
        try {
            sqLiteDatabase.execSQL("insert into Invoice values('" + invoice.getInvoiceId() + "', '" + invoice.getProductId() + "'," + invoice.getQty() + "," + invoice.getTotal() + ")");
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public Cursor searchAllProduct() {
        Cursor cursor = null;
        try {
            cursor = sqLiteDatabase.rawQuery("Select ProductName from Product ", null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return cursor;
    }


    //DisplayProduct
    public ArrayList<ProductClass> viewProductDetails(String productId) {
        ArrayList<ProductClass> productList = new ArrayList<ProductClass>();
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("Select * from Product where ProductID = '"+ productId + "' ", null);
            if (cursor.moveToFirst()) {
                do {
                    ProductClass product = new ProductClass();
                    product.setProductId(cursor.getString(0));
                    product.setProductName(cursor.getString(1));
                    product.setCategoryId(cursor.getString(2));
                    product.setPrice(cursor.getInt(3));
                    product.setQuantity(cursor.getInt(4));
                    productList.add(product);
                } while (cursor.moveToNext());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return productList;
    }



    //Insert Order
    public boolean insertOrder(OrderClass orderClass) {
        try {
            sqLiteDatabase.execSQL("insert into Orders values('" + orderClass.getProductId() + "', '" + orderClass.getCategoryName() + "','" + orderClass.getProductName() + "','" + orderClass.getQuantity() + "'," +
                    orderClass.getDate() + ")");
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }


    //View order
    public ArrayList<OrderClass> ViewOrders(String productId) {
        ArrayList<OrderClass> orderList = new ArrayList<OrderClass>();
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("Select * from Orders where ProductID = '"+ productId + "' ",  null);
            if (cursor.moveToFirst()) {
                do {
                    OrderClass order = new OrderClass();
                    order.setCategoryName(cursor.getString(0));
                    order.setProductName(cursor.getString(1));
                    order.setQuantity(cursor.getInt(2));
                    order.setDate(cursor.getInt(3));
                    orderList.add(order);
                } while (cursor.moveToNext());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return orderList;
    }

    public Cursor viewALLOrders() {
        Cursor cursor = null;
        try {
            cursor = sqLiteDatabase.rawQuery("Select ProductName from Orders ", null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return cursor;
    }



}
