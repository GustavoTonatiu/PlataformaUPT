package com.example.plataformaupt;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

public class Fcm extends FirebaseMessagingService {
    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        Log.e("token", "mi token es: " + s);
        guardarTokenNuevos(s);

    }

    private void guardarTokenNuevos(String s) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("token");
        ref.child("usuarioId").setValue(s);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        String from = remoteMessage.getFrom();
        Log.e("TAG", "Mensaje Recibido de: " + from);

        if (remoteMessage.getNotification() != null) {
            Log.e("TAG", "Titulo: " + remoteMessage.getNotification().getTitle());
            Log.e("TAG", "Body: " + remoteMessage.getNotification().getBody());
        }
        // -- Clave valor
        if (remoteMessage.getData().size() > 0) {

            Log.e("TAG", "Mi titulo es " + remoteMessage.getData().get("titulo"));
            Log.e("TAG", "Mi detalle es " + remoteMessage.getData().get("detalle"));
            Log.e("TAG", "Mi color es " + remoteMessage.getData().get("aColor"));

            String titulo = remoteMessage.getData().get("titulo");
            String detalle = remoteMessage.getData().get("titulo");
            mayorQueOreo(titulo,detalle);

            if(remoteMessage.getNotification() != null){
                Log.e("TAG","Notificacion: "+remoteMessage.getNotification().getBody());

            }

            // -- Clave valor
            if (remoteMessage.getData().size() > 0) {
                Log.e("TAG", "Data: "+ remoteMessage.getData());
            }


        }
    }
    private void mayorQueOreo (String titulo, String detalle){

        String id = "mensaje";
        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,id);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel nc = new NotificationChannel(id, "nuevo", NotificationManager.IMPORTANCE_HIGH);
            nc.setShowBadge(true);
            assert nm != null;
            nm.createNotificationChannel(nc);
        }
        builder.setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .setContentTitle(titulo)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText(detalle)
                .setContentIntent(clicknoti())
                .setContentInfo("nuevo");

        Random random = new Random();
        int idNotify = random.nextInt(8000);

        assert  nm != null;
        nm.notify(idNotify,builder.build());

    }

    public PendingIntent clicknoti () {
        Intent it = new Intent(getApplicationContext(), Menu.class);
        it.putExtra("color", "verde");
        it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return PendingIntent.getActivity(this,0,it,0);
    }
}







