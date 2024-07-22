package com.example.thebloomroom.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.thebloomroom.Database.DBHelper;
import com.example.thebloomroom.Modules.ProductClass;
import com.example.thebloomroom.R;

import java.util.ArrayList;

public class ViewProductActivity extends AppCompatActivity {

    ListView listViewProducts;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);

        dbHelper = new DBHelper(this);
        dbHelper.openDB();

        listViewProducts = (ListView) findViewById(R.id.lst_L_Products);

        ArrayList<String> theList = new ArrayList<>();
        Cursor cursor = dbHelper.searchAllProduct();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Product Found", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                theList.add(cursor.getString(0));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);
                listViewProducts.setAdapter(listAdapter);
            }
        }


        listViewProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String productId = String.valueOf(position+1);
                ArrayList<ProductClass> productList = dbHelper.viewProductDetails(productId);
                if (productList.size() != 0) {
                    Intent intentViewList = new Intent(ViewProductActivity.this, ViewProductInfoActivity.class);
                    ProductClass product = productList.get(0);
                    intentViewList.putExtra("ProductID", product.getProductId());
                    intentViewList.putExtra("ProductName", product.getProductName());
                    intentViewList.putExtra("CategoryID", product.getCategoryId());
                    intentViewList.putExtra("Price", String.valueOf(product.getPrice()));
                    intentViewList.putExtra("Quantity", String.valueOf(product.getQuantity()));
                    startActivity(intentViewList);
                }
            }
        });

    }
}