package com.ronaldinhoaugusto.sistemataxi;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;

public class ActCadastroCorrida extends AppCompatActivity implements View.OnClickListener
{
    private TextView lblTitle, lblEmpty;
    private EditText txtName, txtModeloCarro, txtPlaca, txtCliente, txtTelefoneCliente;
    private Button btnCadastrarCorrida;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_cadastro_corrida);

        lblTitle = (TextView) findViewById(R.id.lblTitle);
        lblEmpty = (TextView) findViewById(R.id.lblEmpty);
        txtName  = (EditText) findViewById(R.id.txtName);
        txtModeloCarro = (EditText) findViewById(R.id.txtModeloCarro);
        txtPlaca = (EditText) findViewById(R.id.txtPlaca);
        txtCliente = (EditText) findViewById(R.id.txtCliente);
        txtTelefoneCliente = (EditText) findViewById(R.id.txtTelefoneCliente);
        btnCadastrarCorrida = (Button) findViewById(R.id.btnCadastrarCorrida);

        btnCadastrarCorrida.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        AlertDialog.Builder dlg = new AlertDialog.Builder(ActCadastroCorrida.this);
        dlg.setMessage("Corrida Cadastrada! Enviando Motorista...");
        dlg.setNeutralButton("OK",null);
        dlg.show();
    }
}
