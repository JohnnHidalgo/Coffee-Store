package com.example.johnnhidalgo.project.Admin.modules.Pedidos;

import android.content.DialogInterface;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.johnnhidalgo.project.Admin.modules.Food.FoodList;
import com.example.johnnhidalgo.project.Admin.modules.Venta.VentaList;
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


        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                Toast.makeText(getApplicationContext(),"FFF",Toast.LENGTH_LONG).show();
                CharSequence[] items = {"Despachado"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(PedidosList.this);

                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            int cant;
                            int idPedido;

                            Cursor c = db.getDataPedido("SELECT pedido_id FROM pedido");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            ArrayList<Integer> arrCant = new ArrayList<Integer>();


                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                                arrCant.add(c.getInt(0));
                            }

                            idPedido = arrID.get(position);
                            cant = arrCant.get(position);
                            try{
                                db.insertVenta(
                                        idPedido,
                                        cant
                                );
                                Toast.makeText(getApplicationContext(), "Added successfully!", Toast.LENGTH_SHORT).show();

                            }
                            catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }
                });
                dialog.show();

                return true;
            }
        });
    }
}

/*



 */