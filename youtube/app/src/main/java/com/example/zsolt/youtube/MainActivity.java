package com.example.zsolt.youtube;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView udvozol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        udvozol=findViewById(R.id.udvozol);
        Intent intent = getIntent();
        String nev = intent.getStringExtra("nev");

        udvozol.setText("Üdvözöllek\n"+nev+"!");
    }
}