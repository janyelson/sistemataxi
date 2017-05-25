package com.ronaldinhoaugusto.sistemataxi;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.widget.*;
import android.view.View;
import android.content.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private Button btnCadastrarMot, btnConsultarMot, btnCadastrarNovaCorr, btnConsultarCorr;
    //private String message = "Processo executado com êxito!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCadastrarMot = (Button) findViewById(R.id.btnCadastrarMot);
        btnConsultarMot = (Button) findViewById(R.id.btnConsultarMot);
        btnCadastrarNovaCorr = (Button) findViewById(R.id.btnCadastrarNovaCorr);
        btnConsultarCorr = (Button) findViewById(R.id.btnConsultarCorr);

        //btnCadastrarMot.setOnClickListener(this);

        btnCadastrarMot.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                cadastroMotorista(v);
                /*
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setMessage(message);
                dlg.setNeutralButton("OK",null);
                dlg.show();
                */
            }
        });

        btnConsultarMot.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                consultarMotorista(v);
                /*
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setMessage("Consulta processada com sucesso!");
                dlg.setNeutralButton("OK",null);
                dlg.show();
                */
            }
        });
        btnCadastrarNovaCorr.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                cadastroCorrida(v);
                /*
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setMessage("Viagem cadastada com sucesso!");
                dlg.setNeutralButton("OK",null);
                dlg.show();
                */
            }
        });
        btnConsultarCorr.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setMessage("Consultando corridas...");
                dlg.setNeutralButton("OK",null);
                dlg.show();
            }
        });
        /*
        Toolbar toolbar = (Toolbar) findViewById(id.toolbar);
        setSupportActionBar(toolbar);
        */
    }

    public void cadastroMotorista(View v)
    {
        Intent it = new Intent(this, ActCadastroMotorista.class);
        startActivity(it);
    }
    public void consultarMotorista(View v)
    {
        Intent it = new Intent(this, ActConsultaMotorista.class);
        startActivity(it);
    }
    public void cadastroCorrida(View v)
    {
        Intent it = new Intent(this, ActCadastroCorrida.class);
        startActivity(it);
    }
    public void consultarCorrida(View v)
    {
        Intent it = new Intent(this, ActCadastroMotorista.class);
        startActivity(it);
    }

    public void onClick(View v)
    {
        /*
        Intent it = new Intent(this, ActCadastroMotorista.class);
        startActivity(it);
        */
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
        /*
        //noinspection SimplifiableIfStatement
        if (id == id.action_settings) {
            return true;
        }
        */

        return super.onOptionsItemSelected(item);
    }
}
