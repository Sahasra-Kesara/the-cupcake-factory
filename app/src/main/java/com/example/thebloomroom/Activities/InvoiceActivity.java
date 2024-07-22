package com.example.thebloomroom.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thebloomroom.Database.DBHelper;
import com.example.thebloomroom.Modules.ProductClass;
import com.example.thebloomroom.R;

import java.util.ArrayList;

public class InvoiceActivity extends AppCompatActivity {

    TextView editTextProductId, editTextProductName, editTextPrice, editTextCategory, editTextQuantity;

    Button buttonSearchProduct;

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        dbHelper = new DBHelper(this);
        dbHelper.openDB();

        editTextProductId = (EditText) findViewById(R.id.txt_I_ProductId);
        editTextProductName = (EditText) findViewById(R.id.txt_I_ProductName);
        editTextPrice = (EditText) findViewById(R.id.txt_I_Price);
        editTextCategory = (EditText) findViewById(R.id.txt_I_Category);
        editTextQuantity = (EditText) findViewById(R.id.txt_I_Qty);

        buttonSearchProduct = (Button) findViewById(R.id.btn_I_SearchProduct);

        buttonSearchProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pName = editTextProductName.getText().toString();
                ArrayList<ProductClass> productList = dbHelper.searchProduct(pName);
                if (productList.size() != 0) {
                    ProductClass product = productList.get(0);
                    editTextProductId.setText(product.getProductName());
                    editTextCategory.setText(product.getCategoryId());
                    editTextPrice.setText(String.valueOf(product.getPrice()));
                    editTextQuantity.setText(String.valueOf(product.getQuantity()));
                    Toast.makeText(InvoiceActivity.this, "Product found", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(InvoiceActivity.this, "No product found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}