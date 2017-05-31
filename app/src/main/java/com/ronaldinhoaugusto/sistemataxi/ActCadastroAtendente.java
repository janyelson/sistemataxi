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

public class ActCadastroAtendente extends AppCompatActivity implements View.OnClickListener{
    private EditText lblTextId, lblTextName, lblTextSalario, lblTextCPF, lblTextTelefone;
    private Button btnCadastrarAtendente;
    private GeTaxiDB bd;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_cadastro_atendente);

        bd = new GeTaxiDB(ActCadastroAtendente.this);

        //lblTextId = (EditText)findViewById(R.id.lblTextId);
        lblTextName = (EditText)findViewById(R.id.lblTextName);
        lblTextSalario = (EditText)findViewById(R.id.lblTextSalario);
        lblTextCPF = (EditText)findViewById(R.id.lblTextCPF);
        lblTextTelefone = (EditText)findViewById(R.id.lblTextTelefone);

        btnCadastrarAtendente = (Button)findViewById(R.id.btnCadastrarAtendente);

        btnCadastrarAtendente.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        register();
        AlertDialog.Builder dlg = new AlertDialog.Builder(ActCadastroAtendente.this);
        dlg.setMessage("Atendente Cadastro no Sistema!");
        dlg.setNeutralButton("OK",null);
        dlg.show();
    }

    private void register() {
        ContentValues values = new ContentValues();
        //values.put(GeTaxiModelDB.AtendenteRegisterEntry.COLUMN_NAME_ID, lblTextId.getText().toString());
        values.put(GeTaxiModelDB.AtendenteRegisterEntry.COLUMN_NAME_NAME, lblTextName.getText().toString());
        values.put(GeTaxiModelDB.AtendenteRegisterEntry.COLUMN_NAME_SALARIO, lblTextSalario.getText().toString());
        values.put(GeTaxiModelDB.AtendenteRegisterEntry.COLUMN_NAME_CPF, lblTextCPF.getText().toString());
        values.put(GeTaxiModelDB.AtendenteRegisterEntry.COLUMN_NAME_TELEFONE, lblTextTelefone.getText().toString());

        bd.insert(values, GeTaxiModelDB.AtendenteRegisterEntry.TABLE_NAME);
    }
}
