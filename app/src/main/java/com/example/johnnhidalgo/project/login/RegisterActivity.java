package com.example.johnnhidalgo.project.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.johnnhidalgo.project.Database.DataBase;
import com.example.johnnhidalgo.project.R;
import com.example.johnnhidalgo.project.modelos.User;

public class RegisterActivity extends AppCompatActivity {

    DataBase db;
    EditText username, password, cpassword;
    Button register,login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DataBase(this);
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
                    Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(passwordtxt.equals(cpasswordtxt)){

                        Boolean checkusername = db.checkusername(usernametxt);

                        if (checkusername== true){


                            Boolean insert = db.addUser(new User(usernametxt,passwordtxt));
                            if (insert == true){
                                Toast.makeText(getApplicationContext(),"Registro Completado", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Username existente", Toast.LENGTH_SHORT).show();

                        }
                    }
                    Toast.makeText(getApplicationContext(),"Password distintos", Toast.LENGTH_SHORT).show();
                }




            }
        });



    }
}
