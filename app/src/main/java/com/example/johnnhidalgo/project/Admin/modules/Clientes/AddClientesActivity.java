package com.example.johnnhidalgo.project.Admin.modules.Clientes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.johnnhidalgo.project.Admin.modules.Personal.AddPersonalActivity;
import com.example.johnnhidalgo.project.Database.DatabaseHelper;
import com.example.johnnhidalgo.project.R;
import com.example.johnnhidalgo.project.helpers.InputValidation;
import com.example.johnnhidalgo.project.modelos.Cliente;

public class AddClientesActivity extends AppCompatActivity {

    private final AppCompatActivity activity = AddClientesActivity.this;
    DatabaseHelper db;
    EditText clientename, clientepassword, clientecpassword;
    Button clienteregister;
    InputValidation inputValidation;
    Cliente cliente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_clientes);

        db = new DatabaseHelper(activity);
        inputValidation = new InputValidation(activity);
        cliente = new Cliente();
        clientename = (EditText)findViewById(R.id.namecliente);
        clientepassword = (EditText)findViewById(R.id.passwordcliente);
        clientecpassword = (EditText)findViewById(R.id.cpasswordcliente);
        clienteregister = (Button) findViewById(R.id.registercliente);


        clienteregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  clientenametxt, passwordtxt, cpasswordtxt;
                clientenametxt = clientename.getText().toString();
                passwordtxt= clientepassword.getText().toString();
                cpasswordtxt= clientecpassword.getText().toString();

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
        clientepassword.setText(null);
        clientecpassword.setText(null);
    }

}
