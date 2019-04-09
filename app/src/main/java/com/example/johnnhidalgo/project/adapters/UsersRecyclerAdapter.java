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
import android.widget.Toast;

import com.example.johnnhidalgo.project.Database.DatabaseHelper;
import com.example.johnnhidalgo.project.R;
import com.example.johnnhidalgo.project.modelos.User;

import java.util.List;

public class UsersRecyclerAdapter extends RecyclerView.Adapter<UsersRecyclerAdapter.UserViewHolder>{
    private List<User> listUsers;
    private DatabaseHelper db;

    public UsersRecyclerAdapter(List<User> listUsers) {
        this.listUsers = listUsers;
    }


    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_user_recycler, viewGroup, false);

        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int position) {

        userViewHolder.textViewName.setText(listUsers.get(position).getUserName());
        userViewHolder.textViewPassword.setText(listUsers.get(position).getUserPass());
    }

    @Override
    public int getItemCount() {
        Log.v(UsersRecyclerAdapter.class.getSimpleName(),""+listUsers.size());
        return listUsers.size();
    }


    public class UserViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewName;
        public AppCompatTextView textViewPassword;
        public Button btnDelete;


        public UserViewHolder(View view) {
            super(view);
            textViewName = (AppCompatTextView) view.findViewById(R.id.textViewName);
            textViewPassword = (AppCompatTextView) view.findViewById(R.id.textViewPassword);
            btnDelete = (Button)view.findViewById(R.id.btnDelete);
            db = new DatabaseHelper(view.getContext());



            btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                AlertDialog.Builder a_builder = new AlertDialog.Builder(v.getContext());
                a_builder.setMessage("Do you want to Close this App !!!")
                        .setCancelable(false)
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                User user = new User(textViewName.getText().toString(),textViewPassword.getText().toString());
                                db.deleteUser(user);


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
                alert.setTitle("Alert !!!");
                alert.show();

            }
        });
        }
    }



}
