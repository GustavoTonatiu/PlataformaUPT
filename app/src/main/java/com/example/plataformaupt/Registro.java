package com.example.plataformaupt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plataformaupt.Api.Api;
import com.example.plataformaupt.Api.Servicios.ServicioPeticion;
import com.example.plataformaupt.ViewModels.RegistroUsuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registro extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        TextView inicio=(TextView)findViewById(R.id.regresar);
        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);

            }
        });

        Button registrar=(Button)findViewById(R.id.Crear);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText password1=(EditText)findViewById(R.id.contra);
                EditText nombreUsuario=(EditText)findViewById(R.id.nombreUsuario);
                EditText password2=(EditText)findViewById(R.id.confcontra);


                ServicioPeticion service = Api.getApi(Registro.this).create(ServicioPeticion.class);
                Call<RegistroUsuario> registrarCall =  service.registrarUsuario(nombreUsuario.getText().toString(),password1.getText().toString());
                ((Call) registrarCall).enqueue(new Callback<RegistroUsuario>() {
                    @Override
                    public void onResponse(Call<RegistroUsuario> call, Response<RegistroUsuario> response) {
                        RegistroUsuario peticion = response.body();
                        if(response.body() == null){
                            Toast.makeText(Registro.this, "Ocurrio un Error, intentalo más tarde", Toast.LENGTH_LONG).show();
                            return;
                        }
                        if(peticion.estado == "true"){
                            startActivity(new Intent(Registro.this,MainActivity.class));
                            Toast.makeText(Registro.this, "Datos Registrador", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(Registro.this, peticion.detalle, Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<RegistroUsuario> call, Throwable t) {
                        Toast.makeText(Registro.this, "Erro ", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}