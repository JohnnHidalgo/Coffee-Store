package com.example.johnnhidalgo.project.Admin.modules.Cafeteria;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.johnnhidalgo.project.R;

public class CafeteriaActivity extends AppCompatActivity {

    FloatingActionButton addCafeteria;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafeteria);


        addCafeteria = (FloatingActionButton)findViewById(R.id.addCafeteria);
        addCafeteria.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(CafeteriaActivity.this, AddCafeteriaActivity.class);
                        startActivity(i);
                    }
                }
        );
    }
}
