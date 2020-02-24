package com.example.arqui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Portada extends AppCompatActivity {

    Button botonBT;
    Button botonInformacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portada);
        botonBT = (Button)findViewById(R.id.button4);
        botonBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Portada.this, Main4Activity.class);
                startActivity(intent);
            }
        });
        botonInformacion= (Button)findViewById(R.id.button146);
        botonInformacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Portada.this, informacion.class);
                startActivity(intent);
            }
        });

    }
}
