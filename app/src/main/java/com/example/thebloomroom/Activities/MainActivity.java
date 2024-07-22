package com.example.thebloomroom.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.thebloomroom.R;

public class MainActivity extends AppCompatActivity {

    ImageView imageBackgroundMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageBackgroundMenu = (ImageView) findViewById(R.id.img_M_BG);

        imageBackgroundMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentMenuToRegister = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intentMenuToRegister);

            }
        });
    }
}