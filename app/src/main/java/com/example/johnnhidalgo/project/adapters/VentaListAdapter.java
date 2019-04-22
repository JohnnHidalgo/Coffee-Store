package com.example.johnnhidalgo.project.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.johnnhidalgo.project.R;
import com.example.johnnhidalgo.project.modelos.VentaCafeteria;

import java.util.ArrayList;

public class VentaListAdapter extends BaseAdapter{

    private Context context;
    private  int layout;
    private ArrayList<VentaCafeteria> ventaList;

    public VentaListAdapter(Context context, int layout, ArrayList<VentaCafeteria> ventaList) {
        this.context = context;
        this.layout = layout;
        this.ventaList = ventaList;
    }

    @Override
    public int getCount() {
        return ventaList.size();
    }

    @Override
    public Object getItem(int position) {
        return ventaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        TextView txtIdcafeteria, txtcantidad;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View row = view;
        ViewHolder holder = new ViewHolder();
        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.txtIdcafeteria = (TextView) row.findViewById(R.id.txtCafeteriaVenta);
            holder.txtcantidad = (TextView) row.findViewById(R.id.txtCantidadVenta);
            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }


        VentaCafeteria ventaCafeteria = ventaList.get(position);


        String id = String.valueOf(ventaCafeteria.getIdCafeteria());
        String cant = String.valueOf(ventaCafeteria.getCantidad());

        holder.txtIdcafeteria.setText(id);
        holder.txtcantidad.setText(cant);

        return row;

    }

}
