package com.example.asamid1910_intencje;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText tvURL;
    Button btnSecond, btnBrowser, btnGps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvURL = findViewById(R.id.tvURL);
        btnSecond = findViewById(R.id.btnSecond);
        btnBrowser = findViewById(R.id.btnBrowser);
        btnGps = findViewById(R.id.btnGps);

        btnSecond.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        });

        btnBrowser.setOnClickListener(v -> {
            String adres = tvURL.getText().toString().trim();

            if (!adres.isEmpty()) {
                if (!adres.startsWith("http://") && !adres.startsWith("https://")) {
                    adres = "https://" + adres;
                }

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(adres));
                startActivity(intent);
            }
        });

        btnGps.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
            startActivity(intent);
        });
    }
}
