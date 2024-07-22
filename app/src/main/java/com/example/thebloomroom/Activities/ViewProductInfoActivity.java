package com.example.thebloomroom.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thebloomroom.R;

public class ViewProductInfoActivity extends AppCompatActivity {

    TextView textViewProductId, textViewProductName, textViewCategoryId, textViewPrice, textViewQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product_info);

        textViewProductId = (TextView) findViewById(R.id.tv_VP_ProductId);
        textViewProductName = (TextView) findViewById(R.id.tv_VP_ProductName);
        textViewCategoryId = (TextView) findViewById(R.id.tv_VP_CategoryId);
        textViewPrice = (TextView) findViewById(R.id.tv_VP_Price);
        textViewQuantity = (TextView) findViewById(R.id.tv_VP_Quantity);
        Intent intent = this.getIntent();

        textViewProductId.setText("Product ID: " + intent.getStringExtra("ProductID"));
        textViewProductName.setText("Product Name: " + intent.getStringExtra("ProductName"));
        textViewCategoryId.setText("Category ID: " + intent.getStringExtra("CategoryID"));
        textViewPrice.setText("Price: " + intent.getStringExtra("Price"));
        textViewQuantity.setText("Quantity: " + intent.getStringExtra("Quantity"));
    }
}