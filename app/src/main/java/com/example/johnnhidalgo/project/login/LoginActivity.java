package com.example.johnnhidalgo.project.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.johnnhidalgo.project.Admin.MenuAdminActivity;
import com.example.johnnhidalgo.project.Database.DataBase;
import com.example.johnnhidalgo.project.R;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

public class LoginActivity extends AppCompatActivity {

    EditText usernameLog, passwordLog;
    Button loginLog;
    DataBase dbLog;

    private View mProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbLog = new DataBase(this);
        usernameLog = (EditText)findViewById(R.id.usernameLog);
        passwordLog = (EditText)findViewById(R.id.passwordLog);
        loginLog = (Button) findViewById(R.id.btnLoginLog);

        mProgressView = findViewById(R.id.login_progress);

        loginLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameLog.getText().toString();
                String password = passwordLog.getText().toString();

                Boolean checkuser= dbLog.usenamepassword(username,password);

                if (checkuser == true){
                    Toast.makeText(getApplicationContext(), "Bienbenido", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, MenuAdminActivity.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}