package com.example.johnnhidalgo.project.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.johnnhidalgo.project.R;
import com.example.johnnhidalgo.project.modelos.PedidoCafeteria;

import java.util.ArrayList;

public class PedidoListAdapter extends BaseAdapter{
    private Context context;
    private  int layout;
    private ArrayList<PedidoCafeteria> pedidoList;

    public PedidoListAdapter(Context context, int layout, ArrayList<PedidoCafeteria> pedidoList) {
        this.context = context;
        this.layout = layout;
        this.pedidoList = pedidoList;
    }

    @Override
    public int getCount() {
        return pedidoList.size();
    }

    @Override
    public Object getItem(int position) {
        return pedidoList.get(position);
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

            holder.txtIdcafeteria = (TextView) row.findViewById(R.id.txtCafeteria);
            holder.txtcantidad = (TextView) row.findViewById(R.id.txtCantidad);
            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }


        PedidoCafeteria pedidoCafeteria = pedidoList.get(position);


        String id = String.valueOf(pedidoCafeteria.getIdCafeteria());
        String cant = String.valueOf(pedidoCafeteria.getCantidad());

        holder.txtIdcafeteria.setText(id);
        holder.txtcantidad.setText(cant);


        return row;


    }




}
