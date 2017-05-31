package com.ronaldinhoaugusto.sistemataxi;

/**
 * Created by MEU PC on 30/05/2017.
 */


import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 100;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 101;
    private static final int MY_PERMISSIONS_REQUEST_GET_ACCOUNTS= 102;
    private static final int MY_PERMISSIONS_REQUEST_INTERNET= 103;
    private static final int MY_PERMISSIONS_REQUEST_TO_READ_EXTERNAL_STORAGE = 104;
    private static int mode = 1;

    private LinearLayout dotsLayout;
    private Button btnSignUp, btnLogIn;
    private Switch aSwitch;
    private TextView txtUsuario;
    private EditText lblTextUsuario;
    private GeTaxiDB db;

    //AUX
    private int auxPermission = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_home);

        if (Build.VERSION.SDK_INT >= 23) {
            //int count = 0;
            verifyPermission();
            while (true) {
                if (ContextCompat.checkSelfPermission(Home.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(Home.this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, android.Manifest.permission.GET_ACCOUNTS) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, android.Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    break;
                }
                if(auxPermission == -1) {
                    break;
                }
            }
        }

        if(auxPermission == -1) {
            return;
        }
        db = new GeTaxiDB(Home.this);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        lblTextUsuario = (EditText) findViewById(R.id.lblTextUsuario);
        txtUsuario = (TextView) findViewById(R.id.txtUsuario);
        aSwitch = (Switch) findViewById(R.id.switch1);
        btnSignUp = (Button) findViewById(R.id.bt_signup);
        btnLogIn = (Button) findViewById(R.id.bt_login);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mode == 1) {
                    startActivity(new Intent(Home.this, ActCadastroCliente.class));
                }else {
                    startActivity(new Intent(Home.this, ActCadastroAtendente.class));
                }
            }
        });
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mode == 1) {
                    if (db.auntenticarUsuario(lblTextUsuario.getText().toString())) {
                        startActivity(new Intent(Home.this, GeTaxiHome.class));
                    }
                    else {
                        Toast.makeText(Home.this, "Erro no Usuario!", Toast.LENGTH_LONG);
                    }

                }else {
                    if (lblTextUsuario.getText().toString().trim().length() != 0 && db.auntenticarAtendente(lblTextUsuario.getText().toString())) {
                        startActivity(new Intent(Home.this, MainActivity.class));
                    }
                    else {
                        Toast.makeText(Home.this, "Erro!", Toast.LENGTH_SHORT);
                    }
                }
            }
        });
        aSwitch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(mode == 1) {
                    mode = 0;
                    txtUsuario.setText("Usuario(Administrador):");
                }
                else {
                    mode = 1;
                    txtUsuario.setText("Usuario(Cliente):");
                }
            }
        });
    }

    private void verifyPermission() {

        if (ContextCompat.checkSelfPermission(Home.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(Home.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(Home.this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(Home.this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {


            } else {

                ActivityCompat.requestPermissions(Home.this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
            }

            if (ActivityCompat.shouldShowRequestPermissionRationale(Home.this,
                    android. Manifest.permission.ACCESS_COARSE_LOCATION)) {

            } else {

                ActivityCompat.requestPermissions(Home.this,
                        new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION );
            }

            if (ActivityCompat.shouldShowRequestPermissionRationale(Home.this,
                    android.Manifest.permission.GET_ACCOUNTS)) {

            } else {

                ActivityCompat.requestPermissions(Home.this,
                        new String[]{android.Manifest.permission.GET_ACCOUNTS},
                        MY_PERMISSIONS_REQUEST_GET_ACCOUNTS);
            }

            if (ActivityCompat.shouldShowRequestPermissionRationale(Home.this,
                    android.Manifest.permission.INTERNET)) {

            } else {

                ActivityCompat.requestPermissions(Home.this,
                        new String[]{android.Manifest.permission.INTERNET},
                        MY_PERMISSIONS_REQUEST_INTERNET);
            }

            if (ActivityCompat.shouldShowRequestPermissionRationale(Home.this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE)) {


            } else {

                ActivityCompat.requestPermissions(Home.this,
                        new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_TO_READ_EXTERNAL_STORAGE);
            }
        }
    }



}












