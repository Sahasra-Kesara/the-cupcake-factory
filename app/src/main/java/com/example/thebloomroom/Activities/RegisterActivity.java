package com.example.thebloomroom.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thebloomroom.Database.DBHelper;
import com.example.thebloomroom.Modules.UserClass;
import com.example.thebloomroom.R;

public class RegisterActivity extends AppCompatActivity {

    TextView textViewGoToTheLogin;

    EditText editTextUserId, editTextPassword, editTextConfirmPassword;
    Spinner spinnerUserType;
    Button buttonRegister;

    String userType[]={"Admin","Member"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        textViewGoToTheLogin = (TextView) findViewById(R.id.txt_R_GoToLogin);

        //This button for go to login activity
        textViewGoToTheLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentRegToLog = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intentRegToLog);

            }
        });



        editTextUserId =(EditText) findViewById(R.id.txt_R_UserId);
        editTextPassword = (EditText) findViewById(R.id.txt_R_Password);
        editTextConfirmPassword =(EditText) findViewById(R.id.txt_R_ConfirmPassword);
        spinnerUserType = (Spinner) findViewById(R.id.sp_R_UserType);
        buttonRegister = (Button) findViewById(R.id.btn_R_Register);


        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,userType);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUserType.setAdapter(ad);

        DBHelper DBHelper = new DBHelper(this);
        DBHelper.openDB();


        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (editTextUserId.getText().toString().isEmpty() ||
                        editTextPassword.getText().toString().isEmpty() ||
                        editTextConfirmPassword.getText().toString().isEmpty()){

                    Toast.makeText(RegisterActivity.this, "Fields can't be empty", Toast.LENGTH_LONG).show();

                } else if(editTextPassword.getText().toString().length()<3){

                    Toast.makeText(RegisterActivity.this, "Password must have more than 3 characters", Toast.LENGTH_LONG).show();

                } else if(!editTextPassword.getText().toString().equals(editTextConfirmPassword.getText().toString())){

                    Toast.makeText(RegisterActivity.this, "Password and confirm password should match", Toast.LENGTH_LONG).show();
                } else {

                    UserClass userClass = new UserClass(editTextUserId.getText().toString(),
                            editTextPassword.getText().toString(), spinnerUserType.getSelectedItem().toString());

                    if (DBHelper.CreateNewUser(userClass)){

                        Toast.makeText(RegisterActivity.this, "User created", Toast.LENGTH_LONG).show();

                        Toast.makeText(RegisterActivity.this, editTextUserId.getText().toString() + " has Register as " +
                                spinnerUserType.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
                    } else {

                        Toast.makeText(RegisterActivity.this, "User creation failed", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });


    }
}