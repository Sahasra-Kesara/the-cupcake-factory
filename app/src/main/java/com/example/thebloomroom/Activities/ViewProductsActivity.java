package com.example.thebloomroom.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import com.example.thebloomroom.Database.DBConnector;
import com.example.thebloomroom.R;
import android.database.Cursor;
import android.widget.SimpleCursorAdapter;

public class ViewProductsActivity extends AppCompatActivity {

    ListView listViewProducts;
    DBConnector dbConnector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_products);

        listViewProducts = (ListView) findViewById(R.id.listViewProducts);
        dbConnector = new DBConnector(this);

        loadProducts();
    }

    private void loadProducts() {
        Cursor cursor = dbConnector.getReadableDatabase().rawQuery("SELECT * FROM Product", null);
        String[] fromColumns = {"ProductName", "Price"};
        int[] toViews = {android.R.id.text1};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_2,
                cursor,
                fromColumns,
                toViews,
                0
        );

        listViewProducts.setAdapter(adapter);
    }
}
