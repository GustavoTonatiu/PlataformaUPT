package com.example.plataformaupt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class Menu extends AppCompatActivity {
    String url="https://notificacionupt.andocodeando.net/api/todasNot";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        WebView link=(WebView)findViewById(R.id.mostrar);
        link.setWebViewClient(new MyWebViewClient());
        WebSettings config=link.getSettings();
        config.setJavaScriptEnabled(true);
        link.loadUrl(url);


        Button cerr=(Button)findViewById(R.id.closeS);
        cerr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Menu.this,MainActivity.class);
                startActivity(i);
                finish();

            }
        });
    }
    //public static void cambiarestado(boolean b){
      //  SharedPreferences preferences=getSharedPreferences(String_servicio,MODE_PRIVATE);
        //preferences.edit().putBoolean(Pre)
    //}

    private class MyWebViewClient extends WebViewClient{
        public  boolean shouldOverrideUrlLoading(WebView view,String url){
            view.loadUrl(url);
            return true;

        }

    }

}
