package com.example.johnnhidalgo.project.login;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.johnnhidalgo.project.Database.DataBase;
import com.example.johnnhidalgo.project.Database.DatabaseHelper;
import com.example.johnnhidalgo.project.R;
import com.example.johnnhidalgo.project.helpers.InputValidation;
import com.example.johnnhidalgo.project.modelos.User;

public class RegisterActivity extends AppCompatActivity {


    private final AppCompatActivity activity = RegisterActivity.this;
    DatabaseHelper db;
    EditText username, password, cpassword;
    Button register,login;
    InputValidation inputValidation;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(activity);
        inputValidation = new InputValidation(activity);
        user = new User();
        username= (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        cpassword = (EditText)findViewById(R.id.cpassword);
        register = (Button)findViewById(R.id.register);
        login = (Button)findViewById(R.id.login);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  usernametxt, passwordtxt, cpasswordtxt;
                usernametxt = username.getText().toString();
                passwordtxt= password.getText().toString();
                cpasswordtxt= cpassword.getText().toString();

                if (usernametxt.equals("")||passwordtxt.equals("")||cpasswordtxt.equals("")){
                    Toast.makeText(getApplicationContext(), "Espacios vacíos", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(passwordtxt.equals(cpasswordtxt)){
                        if (!db.checkUser(usernametxt)) {

                            user.setUserName(usernametxt);
                            user.setUserPass(passwordtxt);

                            db.addUser(user);


                            Toast.makeText(getApplicationContext(),"Registro Correcto!",Toast.LENGTH_SHORT).show();
                            emptyInputEditText();


                        } else {
                            Toast.makeText(getApplicationContext(),"Registro Incorrecto!",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Passwords distintos", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });



    }

    private void emptyInputEditText() {
        username.setText(null);
        password.setText(null);
        cpassword.setText(null);
    }


}
