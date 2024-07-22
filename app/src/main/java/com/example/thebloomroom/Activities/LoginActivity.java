package com.example.thebloomroom.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thebloomroom.Database.DBHelper;
import com.example.thebloomroom.Modules.UserClass;
import com.example.thebloomroom.R;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    TextView textViewGoToTheRegister;

    EditText editTextUserId, editTextPassword;
    Button buttonLogin;

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textViewGoToTheRegister = (TextView) findViewById(R.id.txt_L_GoToRegister);

        //This button for go to Register activity
        textViewGoToTheRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentLogToReg = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intentLogToReg);

            }
        });


        editTextUserId =(EditText) findViewById(R.id.txt_L_UserId);
        editTextPassword =(EditText) findViewById(R.id.txt_L_Password);
        buttonLogin =(Button) findViewById(R.id.btn_L_Login);

        dbHelper = new DBHelper(this);
        dbHelper.openDB();


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<UserClass> userDetails = dbHelper.ValidLogin(editTextUserId.getText().toString(),
                        editTextPassword.getText().toString());


                if (userDetails.size()!=0){

                    UserClass user = userDetails.get(0);
                    String userType = user.getUserType();//Admin

                    Toast.makeText(LoginActivity.this, "User found " + userType, Toast.LENGTH_LONG).show();

                    if (userType.equals("Admin")){



                        Intent intentRegister = new Intent(LoginActivity.this, AdminActivity.class);
                        startActivity(intentRegister);

                    } else {

                        Intent intentReg = new Intent(LoginActivity.this, UserActivity.class);
                        startActivity(intentReg);
                    }
                } else {

                    Toast.makeText(getApplicationContext(), "Invalid User", Toast.LENGTH_LONG).show();

                }
            }
        });

    }
}