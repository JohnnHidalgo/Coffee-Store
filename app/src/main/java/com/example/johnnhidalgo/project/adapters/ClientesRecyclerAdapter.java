package com.example.johnnhidalgo.project.adapters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.johnnhidalgo.project.Database.DatabaseHelper;
import com.example.johnnhidalgo.project.R;
import com.example.johnnhidalgo.project.modelos.Cliente;

import java.util.List;

public class ClientesRecyclerAdapter extends RecyclerView.Adapter<ClientesRecyclerAdapter.ClienteViewHolder>{
    private List<Cliente> listClientes;
    private DatabaseHelper db;

    public ClientesRecyclerAdapter(List<Cliente> listCliente) {
        this.listClientes = listCliente;
    }

    @NonNull
    @Override
    public ClienteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_cliente_recycler, viewGroup, false);

        return new ClienteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteViewHolder clienteViewHolder, int position) {
        clienteViewHolder.textViewName.setText(listClientes.get(position).getClienteName());
        clienteViewHolder.textViewPassword.setText(listClientes.get(position).getClientePass());
    }

    @Override
    public int getItemCount() {
        Log.v(UsersRecyclerAdapter.class.getSimpleName(),""+listClientes.size());
        return listClientes.size();
    }


    public class ClienteViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewName;
        public AppCompatTextView textViewPassword;
        public Button btnDelete,btnUpdate;


        public ClienteViewHolder(View view) {
            super(view);
            textViewName = (AppCompatTextView) view.findViewById(R.id.textViewNameCliente);
            textViewPassword = (AppCompatTextView) view.findViewById(R.id.textViewPasswordCliente);
            btnDelete = (Button)view.findViewById(R.id.btnDeleteCliente);
            btnUpdate = (Button)view.findViewById(R.id.btnUpdateCliente);
            db = new DatabaseHelper(view.getContext());


            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    AlertDialog.Builder a_builder = new AlertDialog.Builder(v.getContext());
                    a_builder.setMessage("Esta seguro de Eliminar este elemento??")
                            .setCancelable(false)
                            .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Cliente cliente = new Cliente(textViewName.getText().toString(),textViewPassword.getText().toString());
                                    db.deleteCliente(cliente);


                                    Toast.makeText(v.getContext(),"Eliminado",Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("No",new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            }) ;
                    AlertDialog alert = a_builder.create();
                    alert.setTitle("Alerta !!!");
                    alert.show();
                }
            });

            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    AlertDialog.Builder a_builder = new AlertDialog.Builder(v.getContext());
                    final EditText nombre = new EditText(v.getContext());
                    final EditText pass = new EditText(v.getContext());
                    nombre.setHint("nuevo nombre");
                    pass.setHint("nuevo password");
                    LinearLayout ll=new LinearLayout(v.getContext());
                    ll.setOrientation(LinearLayout.VERTICAL);
                    ll.addView(nombre);
                    ll.addView(pass);
                    a_builder.setView(ll);

                    a_builder.setMessage("Esta seguro de cambiar estos datos??")
                            .setCancelable(false)
                            .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String nName= nombre.getText().toString().trim();
                                    String nPass = pass.getText().toString().trim();

                                    if (!db.checkCliente(nName)) {

                                        Cliente cliente = new Cliente(textViewName.getText().toString(),textViewPassword.getText().toString());
                                        db.updateCliente(cliente,nName,nPass);

                                        Toast.makeText(v.getContext(),"Actualizado",Toast.LENGTH_SHORT).show();

                                    }else {
                                        Toast.makeText(v.getContext(),"Datos invalidos",Toast.LENGTH_SHORT).show();
                                    }




                                }
                            })
                            .setNegativeButton("No",new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            }) ;
                    AlertDialog alert = a_builder.create();
                    alert.setTitle("Alerta !!!");
                    alert.show();
                }
            });



        }
    }

}
