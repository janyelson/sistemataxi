package com.ronaldinhoaugusto.sistemataxi;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by MEU PC on 28/05/2017.
 */

public class ActCadastroCorrida extends AppCompatActivity implements View.OnClickListener{
    private EditText lblTextProtocolo, lblTextIdMotorista, lblTextIdAtendente, lblTextValor, lblTextPassageiro;
    private Button btnCadastrarChamada;
    private GeTaxiDB bd;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_cadastro_atendente);

        bd = new GeTaxiDB(ActCadastroCorrida.this);

        lblTextProtocolo = (EditText)findViewById(R.id.lblTextProtocolo);
        lblTextIdMotorista = (EditText)findViewById(R.id.lblTextIdMotorista);
        lblTextIdAtendente = (EditText)findViewById(R.id.lblTextIdAtendente);
        lblTextValor = (EditText)findViewById(R.id.lblTextValor);
        lblTextPassageiro = (EditText)findViewById(R.id.lblTextPassageiro);

        btnCadastrarChamada = (Button)findViewById(R.id.btnCadastrarAtendente);

        btnCadastrarChamada.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        register();
        AlertDialog.Builder dlg = new AlertDialog.Builder(ActCadastroCorrida.this);
        dlg.setMessage("Corrida Cadastro no Sistema!");
        dlg.setNeutralButton("OK",null);
        dlg.show();
    }

    private void register() {
        ContentValues values = new ContentValues();
        values.put(GeTaxiModelDB.ChamadaRegisterEntry.COLUMN_NAME_ID, lblTextProtocolo.getText().toString());
        values.put(GeTaxiModelDB.ChamadaRegisterEntry.COLUMN_NAME_VALUE, lblTextValor.getText().toString());

        bd.insert(values, GeTaxiModelDB.ChamadaRegisterEntry.TABLE_NAME);
        values = new ContentValues();


        values.put(GeTaxiModelDB.SolicitaRegisterEntry.COLUMN_NAME_ID_ATENDENTE, lblTextIdAtendente.getText().toString());
        values.put(GeTaxiModelDB.SolicitaRegisterEntry.COLUMN_NAME_ID_USUARIO, lblTextPassageiro.getText().toString());

        bd.insert(values, GeTaxiModelDB.SolicitaRegisterEntry.TABLE_NAME);
        values = new ContentValues();

        values.put(GeTaxiModelDB.RegistraRegisterEntry.COLUMN_NAME_ID_CHAMADA, lblTextProtocolo.getText().toString());
        values.put(GeTaxiModelDB.RegistraRegisterEntry.COLUMN_NAME_ID_MOTORISTA, lblTextIdMotorista.getText().toString());
        values.put(GeTaxiModelDB.RegistraRegisterEntry.COLUMN_NAME_ID_ATENDENTE, lblTextIdAtendente.getText().toString());

        bd.insert(values, GeTaxiModelDB.RegistraRegisterEntry.TABLE_NAME);

    }
}
