package com.example.johnnhidalgo.project.Admin.modules.Masitas;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.johnnhidalgo.project.Admin.modules.Food.AddFoodActivity;
import com.example.johnnhidalgo.project.Admin.modules.Food.FoodList;
import com.example.johnnhidalgo.project.Database.DatabaseHelper;
import com.example.johnnhidalgo.project.R;
import com.example.johnnhidalgo.project.adapters.FoodListAdapter;
import com.example.johnnhidalgo.project.adapters.MasitaListAdapter;
import com.example.johnnhidalgo.project.modelos.Food;
import com.example.johnnhidalgo.project.modelos.Masitas;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class MasitasList extends AppCompatActivity {

    GridView gridView;
    ArrayList<Masitas> list;
    MasitaListAdapter adapter = null;
    DatabaseHelper db;
    FloatingActionButton addMasita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masitas_list);


        gridView = (GridView) findViewById(R.id.gridViewMasitas);
        list = new ArrayList<>();
        adapter = new MasitaListAdapter(this, R.layout.masitas_items, list);
        gridView.setAdapter(adapter);
        addMasita = (FloatingActionButton)findViewById(R.id.addMasitas);
        db = new DatabaseHelper(getApplicationContext());
        // get all data from sqlite
        Cursor cursor = db.getDataMasitas("SELECT * FROM masitas");
        list.clear();
        addMasita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MasitasList.this, AddMasitasActivity.class);
                startActivity(i);
            }
        });

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String price = cursor.getString(2);
            byte[] image = cursor.getBlob(3);

            list.add(new Masitas(name, price, image, id));
        }


        adapter.notifyDataSetChanged();

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                CharSequence[] items = {"Update", "Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(MasitasList.this);

                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            // update
                            Cursor c = db.getDataMasitas("SELECT masitas_id FROM masitas");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            // show dialog update at here
                            showDialogUpdate(MasitasList.this, arrID.get(position));

                        } else {
                            // delete
                            Cursor c = db.getDataMasitas("SELECT masitas_id FROM masitas");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            showDialogDelete(arrID.get(position));
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });
    }


    ImageView imageViewMasitas;
    private void showDialogUpdate(Activity activity, final int position){

        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.update_masita_activity);
        dialog.setTitle("Update");

        imageViewMasitas = (ImageView) dialog.findViewById(R.id.imageViewMasitas);
        final EditText edtName = (EditText) dialog.findViewById(R.id.edtNameMasita);
        final EditText edtPrice = (EditText) dialog.findViewById(R.id.edtPriceMasita);
        Button btnUpdate = (Button) dialog.findViewById(R.id.btnUpdateMasita);

        // set width for dialog
        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95);
        // set height for dialog
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.7);
        dialog.getWindow().setLayout(width, height);
        dialog.show();

        imageViewMasitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // request photo library
                ActivityCompat.requestPermissions(
                        MasitasList.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        888
                );
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    AddFoodActivity.sqLiteHelper.updateDataMasitas(
                            edtName.getText().toString().trim(),
                            edtPrice.getText().toString().trim(),
                            AddFoodActivity.imageViewToByte(imageViewMasitas),
                            position
                    );
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Update successfully!!!",Toast.LENGTH_SHORT).show();
                }
                catch (Exception error) {
                    Log.e("Update error", error.getMessage());
                }
                updateMasitasList();
            }
        });
    }

    private void showDialogDelete(final int idMasita){
        final AlertDialog.Builder dialogDelete = new AlertDialog.Builder(MasitasList.this);

        dialogDelete.setTitle("Warning!!");
        dialogDelete.setMessage("Are you sure you want to this delete?");
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    AddFoodActivity.sqLiteHelper.deleteDataMasitas(idMasita);
                    Toast.makeText(getApplicationContext(), "Delete successfully!!!",Toast.LENGTH_SHORT).show();
                } catch (Exception e){
                    Log.e("error", e.getMessage());
                }
                updateMasitasList();
            }

        });

        dialogDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogDelete.show();
    }

    private void updateMasitasList(){
        // get all data from sqlite
        Cursor cursor = AddFoodActivity.sqLiteHelper.getDataMasitas("SELECT * FROM masitas");
        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String price = cursor.getString(2);
            byte[] image = cursor.getBlob(3);

            list.add(new Masitas(name, price, image, id));
        }
        adapter.notifyDataSetChanged();
    }

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

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }






}
