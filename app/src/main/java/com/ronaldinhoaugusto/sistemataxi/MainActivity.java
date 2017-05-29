package com.ronaldinhoaugusto.sistemataxi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private GeTaxiDB bd;
    private Button btnCadastrarMot, btnConsultarMot;
    private Button btnCadastrarCliente, btnConsultarCliente;
    private Button btnCadastrarAtendente, btnConsultarAtendente;
    private Button btnCadastrarNovaCorr, btnConsultarCorr;
    private Button btnSolicitaTaxi, btnRegistraChamada;

    //private String message = "Processo executado com êxito!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCadastrarMot = (Button) findViewById(R.id.btnCadastrarMot);
        btnConsultarMot = (Button) findViewById(R.id.btnConsultarMot);
        btnCadastrarCliente = (Button) findViewById(R.id.btnCadastrarCliente);
        btnConsultarCliente = (Button) findViewById(R.id.btnConsultarCliente);
        btnCadastrarAtendente = (Button) findViewById(R.id.btnCadastrarAtendente);
        btnConsultarAtendente = (Button) findViewById(R.id.btnConsultarAtendente);
        btnCadastrarNovaCorr = (Button) findViewById(R.id.btnCadastrarNovaCorr);
        btnConsultarCorr = (Button) findViewById(R.id.btnConsultarCorr);
        //btnSolicitaTaxi = (Button) findViewById(R.id.btnSolicitaTaxi);
        //btnRegistraChamada = (Button) findViewById(R.id.btnRegistraChamada);
        bd = new GeTaxiDB(MainActivity.this);

        btnCadastrarMot.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                cadastroMotorista(v);
            }
        });
        btnConsultarMot.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                consultarMotorista(v);
            }
        });
        btnCadastrarCliente.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cadastroCliente(v);
            }
        });
        btnConsultarCliente.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                consultarCliente(v);
            }
        });
        btnCadastrarAtendente.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cadastroAtendente(v);
            }
        });

        btnConsultarAtendente.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                consultarAtendente(v);
            }
        });
        btnCadastrarNovaCorr.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                cadastroCorrida(v);
            }
        });
        btnConsultarCorr.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                consultarCorrida(v);
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
    public void cadastroCliente(View v)
    {
        Intent it = new Intent(this, ActCadastroCliente.class);
        startActivity(it);
    }
    public void consultarCliente(View v)
    {
        Intent it = new Intent(this, ActConsultaCliente.class);
        startActivity(it);
    }
    public void cadastroAtendente(View v)
    {
        Intent it = new Intent(this, ActCadastroAtendente.class);
        startActivity(it);
    }
    public void consultarAtendente(View v)
    {
        Intent it = new Intent(this, ActConsultaAtendente.class);
        startActivity(it);
    }
    public void cadastroCorrida(View v)
    {
        Intent it = new Intent(this, ActCadastroCorrida.class);
        startActivity(it);
    }
    public void consultarCorrida(View v)
    {
        Intent it = new Intent(this, ActConsultaCorrida.class);
        startActivity(it);
    }
    public void consultarSolicitacoes(View v)
    {
        //Intent it = new Intent(this, ActCadastroMotorista.class);
        //startActivity(it);
    }
    public void consultarRegistrosChamadas(View v)
    {
        //Intent it = new Intent(this, ActConsultaMotorista.class);
        //startActivity(it);
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

    @Override
    public void onClick(View v) {

    }
}
