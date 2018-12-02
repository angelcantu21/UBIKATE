package com.software.angelcantu.ubicateqr;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;

public class MapsActivity extends FragmentActivity implements OnStreetViewPanoramaReadyCallback {

    private GoogleMap mMap;
    private float c_x, c_y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        StreetViewPanoramaFragment streetViewPanoramaFragment=(StreetViewPanoramaFragment) getFragmentManager().findFragmentById(R.id.map);
       streetViewPanoramaFragment.getStreetViewPanoramaAsync(this);

        Bundle recibir = getIntent().getExtras();

        c_x = recibir.getFloat("cor_x");
        c_y = recibir.getFloat("cor_y");
        Toast.makeText(getApplicationContext(), ""+c_x+" "+c_y, Toast.LENGTH_LONG).show();
    }



    @Override
    public void onStreetViewPanoramaReady(StreetViewPanorama streetViewPanorama) {
        streetViewPanorama.setPosition(new LatLng(c_x, c_y));
    }

}
