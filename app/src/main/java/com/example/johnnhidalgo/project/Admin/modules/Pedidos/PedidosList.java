package com.example.johnnhidalgo.project.Admin.modules.Pedidos;

import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.example.johnnhidalgo.project.Database.DatabaseHelper;
import com.example.johnnhidalgo.project.R;
import com.example.johnnhidalgo.project.adapters.FoodListAdapter;
import com.example.johnnhidalgo.project.adapters.PedidoListAdapter;
import com.example.johnnhidalgo.project.modelos.Food;
import com.example.johnnhidalgo.project.modelos.PedidoCafeteria;

import java.util.ArrayList;

public class PedidosList extends AppCompatActivity {

    GridView gridView;
    ArrayList<PedidoCafeteria> list;
    PedidoListAdapter adapter = null;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos_list);


        gridView = (GridView) findViewById(R.id.gridViewPedido);
        list = new ArrayList<>();
        adapter = new PedidoListAdapter(this, R.layout.pedido_items, list);
        gridView.setAdapter(adapter);
        db = new DatabaseHelper(getApplicationContext());
        // get all data from sqlite
        Cursor cursor = db.getData("SELECT * FROM pedido");
        list.clear();


        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            int idCafeteria = Integer.parseInt(cursor.getString(1));
            int cantidad = Integer.parseInt(cursor.getString(2));

            list.add(new PedidoCafeteria(id, idCafeteria, cantidad));
        }

        adapter.notifyDataSetChanged();



    }
}
