package com.example.johnnhidalgo.project.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.johnnhidalgo.project.R;
import com.example.johnnhidalgo.project.modelos.Masitas;

import java.util.ArrayList;

public class MasitaListAdapter extends BaseAdapter {

    private Context context;
    private  int layout;
    private ArrayList<Masitas> masitasList;

    public MasitaListAdapter(Context context, int layout, ArrayList<Masitas> masitasList) {
        this.context = context;
        this.layout = layout;
        this.masitasList = masitasList;
    }

    @Override
    public int getCount() {
        return masitasList.size();
    }

    @Override
    public Object getItem(int position) {
        return masitasList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    private class ViewHolder{
        ImageView imageView;
        TextView txtName, txtPrice;
    }
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.txtName = (TextView) row.findViewById(R.id.txtNameMasitas);
            holder.txtPrice = (TextView) row.findViewById(R.id.txtPriceMasitas);
            holder.imageView = (ImageView) row.findViewById(R.id.imgMasitas);
            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }


        Masitas masitas = masitasList.get(position);
        holder.txtName.setText(masitas.getName());
        holder.txtPrice.setText(masitas.getPrice());

        byte[] masitaImage = masitas.getImage();

        Bitmap bitmap = BitmapFactory.decodeByteArray(masitaImage, 0, masitaImage.length);
        holder.imageView.setImageBitmap(bitmap);


        return row;
    }

}
