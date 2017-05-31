package com.ronaldinhoaugusto.sistemataxi;

/**
 * Created by MEU PC on 30/05/2017.
 */

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

public class GeTaxiHome extends BaseActivity implements OnMapReadyCallback {
    public static final int REQUEST_PLACE_ORIGEM = 200;
    public static final int REQUEST_PLACE_DESTINO = 300;
    public static Location userLocation = null;
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private TextView txtOrigem, txtDestino, txtValor;
    private SupportMapFragment mapView;
    private Button btn_request, btn_origem, btn_destino;
    private double latitude_origem, longitude_origem;
    private double latitude_destino, longitude_destino;
    private MarkerOptions markerOrigem, markerDestino;
    private String where_origem, where_destino;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getaxi_home);
        setTitleHome(R.id.toolbar, R.id.iv_title, R.drawable.ic_burger, R.drawable.logo_actbar);

        mapView = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapView.getMapAsync(this);
        if(userLocation == null) {
            buildGoogleApiClient();
        }

        txtOrigem = (TextView) findViewById(R.id.tv_pickup);
        txtDestino = (TextView) findViewById(R.id.tv_dropoff);
        txtValor = (TextView) findViewById(R.id.txtvalor);

        btn_request = (Button) findViewById(R.id.bt_request);
        btn_origem = (Button) findViewById(R.id.getOrigem);
        btn_destino = (Button) findViewById(R.id.getDestino);

        btn_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GeTaxiHome.this, ActMotoristasAcessiveis.class);
                intent.putExtra("origem", txtOrigem.getText().toString());
                intent.putExtra("destino", txtDestino.getText().toString());
                intent.putExtra("valor", txtValor.getText().toString());
                startActivity(intent);

            }
        });

    }

    public Bitmap getBitmapFromView(String title, int dotBg) {

        LinearLayout llmarker = (LinearLayout) findViewById(R.id.ll_marker);
        TextView markerImageView = (TextView) findViewById(R.id.tv_title);
        markerImageView.setText(title);
        View dot = (View) findViewById(R.id.dot_marker);
        dot.setBackgroundResource(dotBg);

        llmarker.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        Bitmap bitmap = Bitmap.createBitmap(llmarker.getMeasuredWidth(), llmarker.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        llmarker.layout(0, 0, llmarker.getMeasuredWidth(), llmarker.getMeasuredHeight());
        llmarker.draw(canvas);
        return bitmap;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        boolean success = googleMap.setMapStyle(new MapStyleOptions(getResources()
                .getString(R.string.style_json)));

        if (!success) {
            Log.e("Style", "Style parsing failed.");
        }
        //LatLng jakarta = new LatLng(-6.232812, 106.820933);
        //LatLng southjakarta = new LatLng(-6.22865,106.8151753);
        //mMap.addMarker(new MarkerOptions().position(jakarta).icon(BitmapDescriptorFactory.fromBitmap(getBitmapFromView("Origem", R.drawable.dot_pickup))));
        //mMap.addMarker(new MarkerOptions().position(southjakarta).icon(BitmapDescriptorFactory.fromBitmap(getBitmapFromView("Destino", R.drawable.dot_dropoff))));
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(jakarta, 15f));


    }

    public void addLocalOrigem(View v) {
        startActivityForResult(new Intent(getApplicationContext(), LocationGetActivity.class), REQUEST_PLACE_ORIGEM);
    }

    public void addLocalDestino(View v) {
        startActivityForResult(new Intent(getApplicationContext(), LocationGetActivity.class), REQUEST_PLACE_DESTINO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PLACE_ORIGEM && resultCode == RESULT_OK) {

            this.latitude_origem = data.getDoubleExtra("lat", 0);
            this.longitude_origem = data.getDoubleExtra("lng", 0);

            if (Build.VERSION.SDK_INT >= 23) {
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
            }
            mapView.getMapAsync(this);
            mMap.setMyLocationEnabled(true);
            mMap.clear();
            markerOrigem = new MarkerOptions()
                    .position(new LatLng(latitude_origem, longitude_origem))
                    .title("Origem!");
            markerDestino = new MarkerOptions()
                    .position(new LatLng(latitude_destino, longitude_destino))
                    .title("Destino!");
            mMap.addMarker(markerOrigem);
            mMap.addMarker(markerDestino);

            this.where_origem = data.getStringExtra("where");

            txtOrigem.setVisibility(View.VISIBLE);
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(
                    new LatLng(latitude_origem, longitude_origem), 14);
            mMap.animateCamera(cameraUpdate);

            txtOrigem.setText(where_origem);
        }

        if (requestCode == REQUEST_PLACE_DESTINO && resultCode == RESULT_OK) {

            this.latitude_destino = data.getDoubleExtra("lat", 0);
            this.longitude_destino = data.getDoubleExtra("lng", 0);

            if (Build.VERSION.SDK_INT >= 23) {
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
            }
            mapView.getMapAsync(this);
            mMap.setMyLocationEnabled(true);
            mMap.clear();
            markerOrigem = new MarkerOptions()
                    .position(new LatLng(latitude_origem, longitude_origem))
                    .title("Origem!");
            markerDestino = new MarkerOptions()
                    .position(new LatLng(latitude_destino, longitude_destino))
                    .title("Destino!");
            mMap.addMarker(markerOrigem);
            mMap.addMarker(markerDestino);

            this.where_destino = data.getStringExtra("where");

            txtDestino.setVisibility(View.VISIBLE);
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(
                    new LatLng(latitude_destino, longitude_destino), 14);
            mMap.animateCamera(cameraUpdate);

            txtDestino.setText(where_destino);
        }
    }

    private void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(Bundle bundle) {
                        if(userLocation == null) {
                            if (ActivityCompat.checkSelfPermission(GeTaxiHome.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(GeTaxiHome.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                                // TODO: Consider calling
                                //    ActivityCompat#requestPermissions
                                // here to request the missing permissions, and then overriding
                                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                //                                          int[] grantResults)
                                // to handle the case where the user grants the permission. See the documentation
                                // for ActivityCompat#requestPermissions for more details.
                                return;
                            }
                            userLocation = (LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient));
                            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(
                                    new LatLng(userLocation.getLatitude(), userLocation.getLongitude()), 15);
                            mMap.animateCamera(cameraUpdate);
                            mMap.getUiSettings().setMyLocationButtonEnabled(true);
                            mMap.setMyLocationEnabled(true);
                        }
                    }

                    @Override
                    public void onConnectionSuspended(int i) {

                    }
                })
                .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(ConnectionResult connectionResult) {

                    }
                })
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }
}
