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
import com.example.thebloomroom.Modules.ProductClass;
import com.example.thebloomroom.R;

import java.util.Vector;

public class ProductActivity extends AppCompatActivity {

    EditText editTextProductId, editTextProductName, editTextPrice, editTextQuantity;
    Spinner spinnerCategoryName;
    Button buttonAddProduct;

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        dbHelper = new DBHelper(this);
        dbHelper.openDB();

        editTextProductId = (EditText) findViewById(R.id.txt_P_ProductId);
        editTextProductName = (EditText) findViewById(R.id.txt_P_ProductName);
        editTextPrice = (EditText) findViewById(R.id.txt_P_Price);
        editTextQuantity = (EditText) findViewById(R.id.txt_P_Quantity);

        buttonAddProduct = (Button) findViewById(R.id.btn_P_AddProduct);
        spinnerCategoryName = (Spinner) findViewById(R.id.sp_P_Category);

        Vector<String> vecCategory = dbHelper.getCategory_Name();

        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, vecCategory);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoryName.setAdapter(ad);

        buttonAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextProductId.getText().toString().isEmpty() || editTextProductName.getText().toString().isEmpty() || editTextPrice.getText().toString().isEmpty() || editTextQuantity.getText().toString().isEmpty()) {
                    Toast.makeText(ProductActivity.this, "Fields Can't be blank", Toast.LENGTH_SHORT).show();
                } else {
                    String categoryId = dbHelper.getCategory_Id(spinnerCategoryName.getSelectedItem().toString());
                    ProductClass product = new ProductClass(editTextProductId.getText().toString(), editTextProductName.getText().toString(), categoryId, Integer.parseInt(editTextPrice.getText().toString()), Integer.parseInt(editTextQuantity.getText().toString()));
                    if (dbHelper.insertProduct(product)) {
                        Toast.makeText(ProductActivity.this, "New Product insert", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ProductActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}