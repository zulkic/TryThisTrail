package com.devtrek.TryThisTrail;


import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    TextView miTexto1, miTexto2, miTexto3; //esto es solo para mostrar que se pueden obtener

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/** De aca en adelante es lo del GPS */
        //los text view que muestran la info
        miTexto1 = (TextView)findViewById(R.id.textView1);
        miTexto2 = (TextView)findViewById(R.id.textView2);
        miTexto3 = (TextView)findViewById(R.id.textView3);

        LocationManager locManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        final Location localizacion = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if (localizacion != null){
            miTexto1.setText("Latitud: " + String.valueOf(localizacion.getLatitude()));
            miTexto2.setText("Longitud: " + String.valueOf(localizacion.getLongitude()));
            miTexto3.setText("Altitud: " + String.valueOf(localizacion.getAltitude()));
        } else {
            miTexto1.setText("Sin datos de latitud.");
            miTexto2.setText("Sin datos de longitud.");
            miTexto3.setText("Sin datos de altitud.");
        }
        LocationListener locListener = new LocationListener() {
            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onProviderEnabled(String provider) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onProviderDisabled(String provider) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onLocationChanged(Location localizacion) { //obtengo los valores para el GPS
                miTexto1.setText("Latitud: " + String.valueOf(localizacion.getLatitude()));
                miTexto2.setText("Longitud: " + String.valueOf(localizacion.getLongitude()).substring(0,8));
                miTexto3.setText("Altitud: " + String.valueOf(localizacion.getAltitude()).substring(0,8));
                //Print!!   como se estan imprimiendo String los acote a solo 8 caracteres (incluyen el "-" y el ".")
                Log.i("Datos de: ", "Latitud;"+String.valueOf(localizacion.getLatitude())
                        +" Longitud;"+String.valueOf(localizacion.getLongitude()).substring(0,8)
                        +" Altitude;"+String.valueOf(localizacion.getAltitude()).substring(0,8));
            }
        };
        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locListener); //el "1000" es cada 1 segundo.
/** Aca termina lo del GPS */
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
