package com.example.johnnhidalgo.project.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.johnnhidalgo.project.Database.DatabaseHelper;
import com.example.johnnhidalgo.project.R;
import com.example.johnnhidalgo.project.helpers.InputValidation;
import com.example.johnnhidalgo.project.modelos.Cliente;
import com.example.johnnhidalgo.project.modelos.User;

public class RegisterActivity extends AppCompatActivity {


    private final AppCompatActivity activity = RegisterActivity.this;
    DatabaseHelper db;
    EditText clientename, password, cpassword;
    Button register,login;
    InputValidation inputValidation;
    Cliente cliente;
//    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(activity);
        inputValidation = new InputValidation(activity);
        cliente = new Cliente();
        clientename= (EditText)findViewById(R.id.clientename);
        password = (EditText)findViewById(R.id.clientepassword);
        cpassword = (EditText)findViewById(R.id.clientecpassword);
        register = (Button)findViewById(R.id.clienteregister);
        login = (Button)findViewById(R.id.login);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  clientenametxt, passwordtxt, cpasswordtxt;
                clientenametxt = clientename.getText().toString();
                passwordtxt= password.getText().toString();
                cpasswordtxt= cpassword.getText().toString();

                if (clientenametxt.equals("")||passwordtxt.equals("")||cpasswordtxt.equals("")){
                    Toast.makeText(getApplicationContext(), "Espacios vacíos", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(passwordtxt.equals(cpasswordtxt)){
                        if (!db.checkCliente(clientenametxt)) {

                            cliente.setClienteName(clientenametxt);
                            cliente.setClientePass(passwordtxt);

                            db.addCliente(cliente);


                            Toast.makeText(getApplicationContext(),"Registro Correcto!",Toast.LENGTH_SHORT).show();
                            emptyInputEditText();


                        } else {
                            Toast.makeText(getApplicationContext(),"Registro Incorrecto!",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Passwords distintos", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });



    }

    private void emptyInputEditText() {
        clientename.setText(null);
        password.setText(null);
        cpassword.setText(null);
    }


}
