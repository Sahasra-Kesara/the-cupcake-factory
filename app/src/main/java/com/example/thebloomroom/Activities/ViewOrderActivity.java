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
import com.example.thebloomroom.Modules.OrderClass;
import com.example.thebloomroom.R;

import java.util.ArrayList;

public class ViewOrderActivity extends AppCompatActivity {

    ListView listViewOrders;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);

        dbHelper = new DBHelper(this);
        dbHelper.openDB();

        listViewOrders = (ListView) findViewById(R.id.lst_L_Orders);

        ArrayList<String> theList = new ArrayList<>();
        Cursor cursor = dbHelper.viewALLOrders();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Product Found", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                theList.add(cursor.getString(0));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);
                listViewOrders.setAdapter(listAdapter);
            }
        }

        listViewOrders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String categoryName = String.valueOf(position + 1);
                ArrayList<OrderClass> OrderList = dbHelper.ViewOrders(categoryName);
                if (OrderList.size() != 0) {
                    Intent intentViewList = new Intent(ViewOrderActivity.this, ViewOrderInfoActivity.class);
                    OrderClass order = OrderList.get(0);
                    intentViewList.putExtra("ProductID", order.getCategoryName());
                    intentViewList.putExtra("CategoryName", order.getCategoryName());
                    intentViewList.putExtra("ProductName", order.getProductName());
                    intentViewList.putExtra("Quantity", String.valueOf(order.getQuantity()));
                    intentViewList.putExtra("Date", String.valueOf(order.getDate()));
                    startActivity(intentViewList);
                }
            }
        });


    }
}