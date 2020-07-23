package com.example.plataformaupt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plataformaupt.Api.Api;
import com.example.plataformaupt.Api.Servicios.ServicioPeticion;
import com.example.plataformaupt.ViewModels.PeticionLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private String APITOKEN="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Drawable originalDrawable=getResources().getDrawable(R.drawable.download);
        Bitmap originalBitmap=((BitmapDrawable)originalDrawable).getBitmap();

        RoundedBitmapDrawable roundedBitmapDrawable= RoundedBitmapDrawableFactory.create(getResources(),originalBitmap);


        //roundedDrawable.setCornerRadius(originalBitmap.getHeight());
        ImageView imageView=(ImageView)findViewById(R.id.Logo);
        imageView.setImageDrawable(roundedBitmapDrawable);

        final SharedPreferences preferencias=getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String token=preferencias.getString("TOKEN","");
        if (token!=""){
            Toast.makeText(MainActivity.this,"Bienvenido Nuevamente",Toast.LENGTH_LONG).show();
            startActivity(new Intent(MainActivity.this,Menu.class));
        }
    startActivity(new Intent(MainActivity.this,Menu.class));
        final EditText correo=findViewById(R.id.username);
        final EditText password=findViewById(R.id.edtContra);
        Button loginButton=(Button)findViewById(R.id.btnLogin);


    loginButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (correo.getText().toString().isEmpty() || correo.getText().toString() == "") {
                correo.setSelectAllOnFocus(true);
                correo.requestFocus();
                mensajeError("Introduce un correo");
                return;
            }
            if (password.getText().toString().isEmpty() || password.getText().toString() == "") {
                password.setSelectAllOnFocus(true);
                password.requestFocus();
                mensajeError("Introduce una contraseña");
                return;
            }

            ServicioPeticion service = Api.getApi(MainActivity.this).create(ServicioPeticion.class);
            Call<PeticionLogin> loginCall = service.getLogin(correo.getText().toString(), password.getText().toString());
            loginCall.enqueue(new Callback<PeticionLogin>() {
                @Override
                public void onResponse(Call<PeticionLogin> call, Response<PeticionLogin> response) {
                    PeticionLogin peticion = response.body();
                    if (peticion.estado == "true") {
                        APITOKEN = peticion.token;
                        guardarPreferencias();
                        Toast.makeText(MainActivity.this, "Bienvenido", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(MainActivity.this, Menu.class));
                    } else {
                        Toast.makeText(MainActivity.this, "Datos Incorrectos", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<PeticionLogin> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
                }
            });


        }
        private void mensajeError(String error){
            Toast.makeText(getApplicationContext(),error,Toast.LENGTH_LONG).show();
        }
        private void cargarPreferencias() {
            SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
            String token=preferencias.getString("TOKEN","Haber que sale");
        }
        public void guardarPreferencias(){
            SharedPreferences preferencias=getSharedPreferences("credenciales",Context.MODE_PRIVATE);
            String token=APITOKEN;
            SharedPreferences.Editor editor=preferencias.edit();
            editor.putString("TOKEN",token);
            editor.commit();
        }
    });
        TextView crear=(TextView)findViewById(R.id.registroLogin);
        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Registro.class);
                startActivity(i);
            }
        });
        //Token: dTUMKlECQjeQRbP63DWtkO:APA91bF1QmAlfi0zfUXq87mUvip3eOuYa6sAmyYFQb49Ojjaj2J7LKfqpK-xLHFARh8-DjNDkz3dwhxVYXV3Vd_rLsZa1TuyRJHnHylDNE-zNVyhWrQdlFFNoSLVX8D5WSdd6hm3-DAF


    }
}
