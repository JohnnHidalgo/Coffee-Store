package com.example.johnnhidalgo.project.Cliente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.johnnhidalgo.project.Admin.modules.Personal.PersonalActivity;
import com.example.johnnhidalgo.project.Cliente.modules.Maps.MapsActivity;
import com.example.johnnhidalgo.project.Cliente.modules.Pedidos.PedidoList;
import com.example.johnnhidalgo.project.R;

public class MenuClienteActivity extends AppCompatActivity {
    Button btnCarta, btnMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_cliente);



        btnCarta = (Button)findViewById(R.id.btnCarta);
        btnMap = (Button)findViewById(R.id.btnMap);



        btnCarta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent accountsIntent = new Intent(getApplicationContext(), PedidoList.class);
                startActivity(accountsIntent);
            }
        });


        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent accountsIntent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(accountsIntent);
            }
        });

    }


}
