package com.example.asamid1910_intencje;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    LocationManager locationManager;
    TextView tvLocation;
    Button btnStart, btnStop;

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            double szerokosc = location.getLatitude();
            double dlugosc = location.getLongitude();

            tvLocation.setText("Szerokość: " + szerokosc +
                    "\nDługość: " + dlugosc);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        tvLocation = findViewById(R.id.tvLocation);
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        btnStart.setOnClickListener(v -> {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        1
                );

                return;
            }

            uruchomGPS();
        });

        btnStop.setOnClickListener(v -> {
            locationManager.removeUpdates(locationListener);
            btnStart.setEnabled(true);
            btnStop.setEnabled(false);
        });
    }

    private void uruchomGPS() {
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    2000,
                    1,
                    locationListener
            );

            btnStart.setEnabled(false);
            btnStop.setEnabled(true);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1 && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            uruchomGPS();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationManager.removeUpdates(locationListener);
    }
}
