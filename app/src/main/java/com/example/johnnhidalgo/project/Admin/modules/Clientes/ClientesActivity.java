package com.example.johnnhidalgo.project.Admin.modules.Clientes;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.johnnhidalgo.project.Database.DatabaseHelper;
import com.example.johnnhidalgo.project.R;
import com.example.johnnhidalgo.project.adapters.ClientesRecyclerAdapter;
import com.example.johnnhidalgo.project.modelos.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClientesActivity extends AppCompatActivity {

    private AppCompatActivity activity = ClientesActivity.this;
    private List<Cliente> listClientes;
    private RecyclerView recyclerViewClientes;
    private ClientesRecyclerAdapter clientesRecyclerAdapter;
    private DatabaseHelper db;
    FloatingActionButton addCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);
        recyclerViewClientes = (RecyclerView)findViewById(R.id.recyclerViewCliente);
        addCliente = (FloatingActionButton) findViewById(R.id.addCliente);

        listClientes = new ArrayList<>();
        clientesRecyclerAdapter = new ClientesRecyclerAdapter(listClientes);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewClientes.setLayoutManager(mLayoutManager);
        recyclerViewClientes.setItemAnimator(new DefaultItemAnimator());
        recyclerViewClientes.setHasFixedSize(true);
        recyclerViewClientes.setAdapter(clientesRecyclerAdapter);
        db = new DatabaseHelper(activity);
        addCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ClientesActivity.this, AddClientesActivity.class);
                startActivity(i);
            }
        });

        getDataFromSQLite();
    }


    private void getDataFromSQLite() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listClientes.clear();
                listClientes.addAll(db.getAllCliente());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                clientesRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();
    }


}
