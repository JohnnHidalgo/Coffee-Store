package com.example.johnnhidalgo.project.Admin.modules.Personal;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.johnnhidalgo.project.Database.DatabaseHelper;
import com.example.johnnhidalgo.project.R;
import com.example.johnnhidalgo.project.adapters.UsersRecyclerAdapter;
import com.example.johnnhidalgo.project.login.LoginActivity;
import com.example.johnnhidalgo.project.login.RegisterActivity;
import com.example.johnnhidalgo.project.modelos.User;

import java.util.ArrayList;
import java.util.List;

public class PersonalActivity extends AppCompatActivity {

    private AppCompatActivity activity = PersonalActivity.this;
    private List<User> listUsers;
    private RecyclerView recyclerViewUsers;
    private UsersRecyclerAdapter usersRecyclerAdapter;
    private DatabaseHelper db;
    FloatingActionButton addUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        recyclerViewUsers = (RecyclerView) findViewById(R.id.recyclerViewUsers);
        addUser = (FloatingActionButton)findViewById(R.id.addUser);

        listUsers = new ArrayList<>();
        usersRecyclerAdapter = new UsersRecyclerAdapter(listUsers);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewUsers.setLayoutManager(mLayoutManager);
        recyclerViewUsers.setItemAnimator(new DefaultItemAnimator());
        recyclerViewUsers.setHasFixedSize(true);
        recyclerViewUsers.setAdapter(usersRecyclerAdapter);
        db = new DatabaseHelper(activity);
        addUser.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(PersonalActivity.this, AddPersonalActivity.class);
                        startActivity(i);
                    }
                }
        );

        getDataFromSQLite();

    }

    private void getDataFromSQLite() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listUsers.clear();
                listUsers.addAll(db.getAllUser());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                usersRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();
    }
}