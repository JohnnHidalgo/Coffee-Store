package com.example.johnnhidalgo.project.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.johnnhidalgo.project.Admin.MenuAdminActivity;
import com.example.johnnhidalgo.project.Database.DatabaseHelper;
import com.example.johnnhidalgo.project.R;

public class LoginActivity extends AppCompatActivity {
    private final AppCompatActivity activity = LoginActivity.this;

    EditText usernameLog, passwordLog;
    Button loginLog, registerLog;
    DatabaseHelper dbLog;

    private View mProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbLog = new DatabaseHelper(activity);
        usernameLog = (EditText)findViewById(R.id.usernameLog);
        passwordLog = (EditText)findViewById(R.id.passwordLog);
        loginLog = (Button) findViewById(R.id.btnLoginLog);
        registerLog = (Button)findViewById(R.id.btnRegisterLog);

        mProgressView = findViewById(R.id.login_progress);

        loginLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameLog.getText().toString();
                String password = passwordLog.getText().toString();


                if(dbLog.checkCliente(username,password)){
                    Toast.makeText(getApplicationContext(),"Bienvenido",Toast.LENGTH_SHORT).show();
                    //Cambiar menu de cliente
                    Intent accountsIntent = new Intent(activity, MenuAdminActivity.class);
                    startActivity(accountsIntent);
                }
                else if(dbLog.checkUser(username,password)){
                    Toast.makeText(getApplicationContext(),"Bienvenido",Toast.LENGTH_SHORT).show();
                    Intent accountsIntent = new Intent(activity, MenuAdminActivity.class);
                    startActivity(accountsIntent);
                } else {
                    Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
                }

            }
        });

        registerLog.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });


    }
}