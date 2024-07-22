package com.example.thebloomroom.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import com.example.thebloomroom.Database.DBConnector;
import com.example.thebloomroom.R;
import android.database.Cursor;
import android.widget.SimpleCursorAdapter;

public class ViewCategoriesActivity extends AppCompatActivity {

    ListView listViewCategories;
    DBConnector dbConnector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_categories);

        listViewCategories = (ListView) findViewById(R.id.listViewCategories);
        dbConnector = new DBConnector(this);

        loadCategories();
    }

    private void loadCategories() {
        Cursor cursor = dbConnector.getReadableDatabase().rawQuery("SELECT * FROM Category", null);
        String[] fromColumns = {"CategoryName"};
        int[] toViews = {android.R.id.text1};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_1,
                cursor,
                fromColumns,
                toViews,
                0
        );

        listViewCategories.setAdapter(adapter);
    }
}
