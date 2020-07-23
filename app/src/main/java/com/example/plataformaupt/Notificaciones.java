package com.example.plataformaupt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Notificaciones extends AppCompatActivity {

    Button especifico,general;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificaciones);
        especifico=findViewById(R.id.btnEsp);
        general=findViewById(R.id.btnGen);
        FirebaseMessaging.getInstance().subscribeToTopic("atodos").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(Notificaciones.this,"Se agrego a la lista",Toast.LENGTH_SHORT).show();
            }
        });

        especifico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llamarEspecifico();
            }

            private void llamarEspecifico() {
                RequestQueue myrequest= Volley.newRequestQueue(getApplicationContext());
                JSONObject json =new JSONObject();
                try{
                   // String token="dTUMKlECQjeQRbP63DWtkO:APA91bF1QmAlfi0zfUXq87mUvip3eOuYa6sAmyYFQb49Ojjaj2J7LKfqpK-xLHFARh8-DjNDkz3dwhxVYXV3Vd_rLsZa1TuyRJHnHylDNE-zNVyhWrQdlFFNoSLVX8D5WSdd6hm3-DAF";
                    json.put("to","/topics/"+"atodos");
                    JSONObject notificacion=new JSONObject();

                    notificacion.put("titulo","A todos");
                    notificacion.put("titulo","A todos");
                    json.put("data",notificacion);
                    String URL="https://fcm.googleapis.com/fcm/send";
                    JsonObjectRequest request= new JsonObjectRequest(Request.Method.POST,URL,json,null,null){
                        @Override

                        public Map<String, String> getHeaders() {
                            Map<String,String>header=new HashMap<>();
                            header.put("content-type","application/json");
                            header.put("authorization","key=AAAAlRDkWFs:APA91bE7Pv7l4Hg3BtKFpuy74cNnm8362_S2WWE7tUZSpk7YDLkIRYMgJYTBkmCDKRTdbltFObje9RtNewouXdLDFCVRMqPgWDJXkAbNirxxeCpamLsRDbwVKCKg9eCJkJi8nfbVGCtX");
                            return header;
                        }
                    };
                    myrequest.add(request);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}