package com.example.thebloomroom.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thebloomroom.Database.DBHelper;
import com.example.thebloomroom.Modules.CategoryClass;
import com.example.thebloomroom.R;

public class CategoryActivity extends AppCompatActivity {

    EditText editTextCategoryID, editTextCategoryName;
    Button btnSubmit;

    private DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        dbHelper = new DBHelper(this);
        dbHelper.openDB();

        editTextCategoryID = (EditText) findViewById(R.id.txt_c_categoryId);
        editTextCategoryName = (EditText) findViewById(R.id.txt_c_category_name);
        btnSubmit = (Button) findViewById(R.id.btn_c_submit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editTextCategoryID.getText().toString().isEmpty() || editTextCategoryName.getText().toString().isEmpty()) {
                    Toast.makeText(CategoryActivity.this, "Fields can't be blank", Toast.LENGTH_SHORT).show();
                } else {
                    CategoryClass categoryClass = new CategoryClass(editTextCategoryID.getText().toString(), editTextCategoryName.getText().toString());
                    if (dbHelper.InsertCategory(categoryClass)) {
                        Toast.makeText(getApplicationContext(), "New Category Insert", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

    }
}