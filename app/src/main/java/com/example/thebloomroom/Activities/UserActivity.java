package com.example.thebloomroom.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.thebloomroom.R;

public class UserActivity extends AppCompatActivity {

    TextView textViewSearchProducts, textViewViewProducts, textViewOrderProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        textViewSearchProducts = (TextView) findViewById(R.id.txt_U_Search_Products);
        textViewViewProducts = (TextView) findViewById(R.id.txt_U_View_Products);
        textViewOrderProducts = (TextView) findViewById(R.id.txt_U_Order_Products);


        textViewSearchProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentSearchProduct = new Intent(UserActivity.this, InvoiceActivity.class);
                startActivity(intentSearchProduct);
            }
        });



        textViewViewProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentViewProduct = new Intent(UserActivity.this, ViewProductActivity.class);
                startActivity(intentViewProduct);
            }
        });


        textViewOrderProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentOrderProduct = new Intent(UserActivity.this, OrderActivity.class);
                startActivity(intentOrderProduct);
            }
        });

    }
}