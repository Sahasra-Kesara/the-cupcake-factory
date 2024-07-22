package com.example.thebloomroom.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.thebloomroom.Database.DBHelper;
import com.example.thebloomroom.Modules.OrderClass;
import com.example.thebloomroom.R;

import java.util.Vector;

public class OrderActivity extends AppCompatActivity {

    EditText editTextProductId, editTextCategoryName, editTextProductName, editTextQuantity,editTextDate;

    Button buttonOrderNow;

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        dbHelper = new DBHelper(this);
        dbHelper.openDB();

        editTextProductId = (EditText) findViewById(R.id.txt_O_ProductId);
        editTextCategoryName = (EditText) findViewById(R.id.txt_O_CategoryName);
        editTextProductName = (EditText) findViewById(R.id.txt_O_ProductName);
        editTextQuantity = (EditText) findViewById(R.id.txt_O_Quantity);
        editTextDate = (EditText) findViewById(R.id.txt_O_OrderDate);

        buttonOrderNow = (Button) findViewById(R.id.btn_O_Order);


        buttonOrderNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextProductId.getText().toString().isEmpty() || editTextCategoryName.getText().toString().isEmpty() || editTextProductName.getText().toString().isEmpty() || editTextQuantity.getText().toString().isEmpty() || editTextDate.getText().toString().isEmpty()) {
                    Toast.makeText(OrderActivity.this, "Fields Can't be blank", Toast.LENGTH_SHORT).show();
                } else {
                    OrderClass order = new OrderClass(editTextProductId.getText().toString(), editTextCategoryName.getText().toString(), editTextProductName.getText().toString(), Integer.parseInt(editTextQuantity.getText().toString()),Integer.parseInt(editTextDate.getText().toString()));
                    if (dbHelper.insertOrder(order)) {
                        Toast.makeText(OrderActivity.this, "Order Sent", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(OrderActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}