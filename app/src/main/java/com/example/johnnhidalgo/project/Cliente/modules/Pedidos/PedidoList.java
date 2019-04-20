package com.example.johnnhidalgo.project.Cliente.modules.Pedidos;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.johnnhidalgo.project.Admin.modules.Food.FoodList;
import com.example.johnnhidalgo.project.Admin.modules.Masitas.MasitasList;
import com.example.johnnhidalgo.project.Database.DatabaseHelper;
import com.example.johnnhidalgo.project.R;
import com.example.johnnhidalgo.project.adapters.FoodListAdapter;
import com.example.johnnhidalgo.project.adapters.MasitaListAdapter;
import com.example.johnnhidalgo.project.modelos.Food;
import com.example.johnnhidalgo.project.modelos.Masitas;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class PedidoList extends AppCompatActivity {

    GridView gridViewCafeteria,gridViewMasitas;

    ArrayList<Food> listFood;
    ArrayList<Masitas> listMasitas;

    FoodListAdapter adapterFood = null;
    MasitaListAdapter adapterMasitas = null;

    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_list);

        gridViewCafeteria = (GridView) findViewById(R.id.gridViewCafetería);
        gridViewMasitas = (GridView) findViewById(R.id.gridViewMasita);

        listFood = new ArrayList<>();
        listMasitas = new ArrayList<>();

        adapterFood = new FoodListAdapter(this, R.layout.food_items, listFood);
        adapterMasitas = new MasitaListAdapter(this, R.layout.masitas_items, listMasitas);


        gridViewCafeteria.setAdapter(adapterFood);
        gridViewMasitas.setAdapter(adapterMasitas);

        db = new DatabaseHelper(getApplicationContext());


        // get all data from sqlite
        Cursor cursorCafeteria = db.getDataMasitas("SELECT * FROM cafeteria");
        Cursor cursorMasitas = db.getDataMasitas("SELECT * FROM masitas");

        listFood.clear();
        listMasitas.clear();



        while (cursorCafeteria.moveToNext()) {
            int id = cursorCafeteria.getInt(0);
            String name = cursorCafeteria.getString(1);
            String price = cursorCafeteria.getString(2);
            byte[] image = cursorCafeteria.getBlob(3);

            listFood.add(new Food(name, price, image, id));
        }
        while (cursorMasitas.moveToNext()) {
            int id = cursorMasitas.getInt(0);
            String name = cursorMasitas.getString(1);
            String price = cursorMasitas.getString(2);
            byte[] image = cursorMasitas.getBlob(3);

            listMasitas.add(new Masitas(name, price, image, id));
        }

        adapterFood.notifyDataSetChanged();
        adapterMasitas.notifyDataSetChanged();

        gridViewCafeteria.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                CharSequence[] items = {"Update", "Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(PedidoList.this);

                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            // update
                            Cursor c = db.getData("SELECT id FROM cafeteria");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            // show dialog update at here
//                            showDialogUpdate(PedidoList.this, arrID.get(position));

                        } else {
                            // delete
                            Cursor c = db.getData("SELECT id FROM cafeteria");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
//                            showDialogDelete(arrID.get(position));
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });

        gridViewMasitas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                CharSequence[] items = {"Update", "Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(PedidoList.this);

                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            // update
                            Cursor c = db.getDataMasitas("SELECT id FROM masitas");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            // show dialog update at here
//                            showDialogUpdate(PedidoList.this, arrID.get(position));

                        } else {
                            // delete
                            Cursor c = db.getDataMasitas("SELECT id FROM masitas");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
//                            showDialogDelete(arrID.get(position));
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });


    }

    ImageView imageViewMasitas;
    ImageView imageViewFood;
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == 888){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 888);
            }
            else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == 888 && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageViewMasitas.setImageBitmap(bitmap);
                imageViewFood.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


}
