package com.example.johnnhidalgo.project.Admin;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.johnnhidalgo.project.Admin.modules.Clientes.ClientesActivity;
import com.example.johnnhidalgo.project.Admin.modules.Masitas.MasitasList;
import com.example.johnnhidalgo.project.Admin.modules.Pedidos.PedidosList;
import com.example.johnnhidalgo.project.Admin.modules.Personal.PersonalActivity;
import com.example.johnnhidalgo.project.Admin.modules.Food.FoodList;
import com.example.johnnhidalgo.project.Admin.modules.Venta.VentaList;
import com.example.johnnhidalgo.project.R;

public class MenuAdminActivity extends AppCompatActivity {

    Button btnCafeteria, btnMasitas, btnPersonal, btnClientes, btnVentas, btnPedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);
        btnCafeteria = (Button)findViewById(R.id.btnCafeteria);
        btnMasitas = (Button)findViewById(R.id.btnMasitas);
        btnPersonal = (Button)findViewById(R.id.btnPersonal);
        btnClientes = (Button)findViewById(R.id.btnClientes);
        btnVentas = (Button)findViewById(R.id.btnVentas);
        btnPedidos = (Button)findViewById(R.id.btnPedidos);


        btnPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent accountsIntent = new Intent(getApplicationContext(), PersonalActivity.class);
                startActivity(accountsIntent);
            }
        });
        btnClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent accountsIntent = new Intent(getApplicationContext(), ClientesActivity.class);
                startActivity(accountsIntent);
            }
        });

        btnCafeteria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent accountsIntent = new Intent(getApplicationContext(), FoodList.class);
                startActivity(accountsIntent);
            }
        });
        btnMasitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent accountsIntent = new Intent(getApplicationContext(), MasitasList.class);
                startActivity(accountsIntent);
            }
        });

        btnPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent accountsIntent = new Intent(getApplicationContext(), PedidosList.class);
                startActivity(accountsIntent);
            }
        });

        btnVentas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent accountsIntent = new Intent(getApplicationContext(), VentaList.class);
                startActivity(accountsIntent);
            }
        });

    }
}
