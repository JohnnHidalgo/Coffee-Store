package com.example.johnnhidalgo.project.Admin.modules.Venta;

import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.johnnhidalgo.project.Admin.modules.Food.FoodList;
import com.example.johnnhidalgo.project.Cliente.modules.Pedidos.PedidoList;
import com.example.johnnhidalgo.project.Database.DatabaseHelper;
import com.example.johnnhidalgo.project.R;
import com.example.johnnhidalgo.project.adapters.PedidoListAdapter;
import com.example.johnnhidalgo.project.adapters.VentaListAdapter;
import com.example.johnnhidalgo.project.modelos.PedidoCafeteria;
import com.example.johnnhidalgo.project.modelos.VentaCafeteria;

import java.util.ArrayList;

public class VentaList extends AppCompatActivity {


    GridView gridView;
    ArrayList<VentaCafeteria> list;
    VentaListAdapter adapter = null;
    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venta_list);

        gridView = (GridView) findViewById(R.id.gridViewVenta);
        list = new ArrayList<>();
        adapter = new VentaListAdapter(this, R.layout.venta_items, list);
        gridView.setAdapter(adapter);
        db = new DatabaseHelper(getApplicationContext());
        // get all data from sqlite
        Cursor cursor = db.getDataVenta("SELECT * FROM venta");
        list.clear();


        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            int idCafeteria = Integer.parseInt(cursor.getString(1));
            int cantidad = Integer.parseInt(cursor.getString(2));

            list.add(new VentaCafeteria(id, idCafeteria, cantidad));
        }

        adapter.notifyDataSetChanged();






    }




}
