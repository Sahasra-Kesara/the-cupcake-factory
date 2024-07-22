package com.example.thebloomroom.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.thebloomroom.R;

public class AdminActivity extends AppCompatActivity {

    TextView textViewAddCategory, textViewAddProduct, textViewViewOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        textViewAddCategory = (TextView) findViewById(R.id.txt_A_Add_Category);
        textViewAddProduct = (TextView) findViewById(R.id.txt_A_Add_Product);
        textViewViewOrders = (TextView) findViewById(R.id.txt_A_View_Orders);


        textViewAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentCategory = new Intent(AdminActivity.this, CategoryActivity.class);
                startActivity(intentCategory);
            }
        });


        textViewAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentProduct = new Intent(AdminActivity.this, ProductActivity.class);
                startActivity(intentProduct);
            }
        });


        textViewViewOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentViewOrders = new Intent(AdminActivity.this, ViewOrderActivity.class);
                startActivity(intentViewOrders);
            }
        });
    }
}