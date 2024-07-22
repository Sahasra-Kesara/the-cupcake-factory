package com.example.thebloomroom.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thebloomroom.Database.DBConnector;
import com.example.thebloomroom.R;

public class ViewOrderInfoActivity extends AppCompatActivity {

    TextView textViewProductId, textViewCategoryName, textViewProductName, textViewQuantity, textViewDate;
    Button btnUpdate, btnDelete;
    DBConnector dbConnector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order_info);

        textViewProductId = (TextView) findViewById(R.id.tv_VP_ProductId);
        textViewCategoryName = (TextView) findViewById(R.id.tv_VP_CategoryName);
        textViewProductName = (TextView) findViewById(R.id.tv_VP_ProductName);
        textViewQuantity = (TextView) findViewById(R.id.tv_VP_Quantity);
        textViewDate = (TextView) findViewById(R.id.tv_VP_Date);
        btnUpdate = (Button) findViewById(R.id.btn_update);
        btnDelete = (Button) findViewById(R.id.btn_delete);

        dbConnector = new DBConnector(this);

        Intent intent = this.getIntent();

        textViewProductId.setText("Product Id: " + intent.getStringExtra("ProductID"));
        textViewCategoryName.setText("Category Name: " + intent.getStringExtra("CategoryName"));
        textViewProductName.setText("Product Name: " + intent.getStringExtra("ProductName"));
        textViewQuantity.setText("Quantity: " + intent.getStringExtra("Quantity"));
        textViewDate.setText("Date is: " + intent.getStringExtra("Date"));

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Implement update logic here
                updateOrder();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Implement delete logic here
                deleteOrder();
            }
        });
    }

    private void updateOrder() {
        SQLiteDatabase db = dbConnector.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ProductID", textViewProductId.getText().toString());
        contentValues.put("CategoryName", textViewCategoryName.getText().toString());
        contentValues.put("ProductName", textViewProductName.getText().toString());
        contentValues.put("Quantity", Integer.parseInt(textViewQuantity.getText().toString()));
        contentValues.put("Date", Integer.parseInt(textViewDate.getText().toString()));

        // Assume ProductID is the unique identifier
        int result = db.update("Orders", contentValues, "ProductID=?", new String[]{textViewProductId.getText().toString()});
        if (result > 0) {
            Toast.makeText(this, "Order Updated Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Order Update Failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteOrder() {
        SQLiteDatabase db = dbConnector.getWritableDatabase();
        int result = db.delete("Orders", "ProductID=?", new String[]{textViewProductId.getText().toString()});
        if (result > 0) {
            Toast.makeText(this, "Order Deleted Successfully", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity after deletion
        } else {
            Toast.makeText(this, "Order Deletion Failed", Toast.LENGTH_SHORT).show();
        }
    }
}
