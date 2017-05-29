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

public class ActCadastroCliente extends AppCompatActivity implements View.OnClickListener{
    private EditText lblTextId, lblTextName, lblTextCPF, lblTextTelefone;
    private EditText lblTextRua, lblTextCidade, lblTextEstado;
    private Button btnCadastrarCliente;
    private GeTaxiDB bd;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_cadastro_cliente);

        bd = new GeTaxiDB(ActCadastroCliente.this);

        lblTextId = (EditText)findViewById(R.id.lblTextId);
        lblTextName = (EditText)findViewById(R.id.lblTextName);
        lblTextCPF = (EditText)findViewById(R.id.lblTextCPF);
        lblTextTelefone = (EditText)findViewById(R.id.lblTextTelefone);
        lblTextRua = (EditText)findViewById(R.id.lblTextRua);
        lblTextCidade = (EditText)findViewById(R.id.lblTextCidade);
        lblTextEstado = (EditText)findViewById(R.id.lblTextEstado);

        btnCadastrarCliente= (Button)findViewById(R.id.btnCadastrarCliente);

        btnCadastrarCliente.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        register();
        AlertDialog.Builder dlg = new AlertDialog.Builder(ActCadastroCliente.this);
        dlg.setMessage("Cliente Cadastro no Sistema!");
        dlg.setNeutralButton("OK",null);
        dlg.show();
    }

    private void register() {
        ContentValues values = new ContentValues();
        values.put(GeTaxiModelDB.UsuarioRegisterEntry.COLUMN_NAME_ID, lblTextId.getText().toString());
        values.put(GeTaxiModelDB.UsuarioRegisterEntry.COLUMN_NAME_NAME, lblTextName.getText().toString());
        values.put(GeTaxiModelDB.UsuarioRegisterEntry.COLUMN_NAME_CPF, lblTextCPF.getText().toString());
        values.put(GeTaxiModelDB.UsuarioRegisterEntry.COLUMN_NAME_TELEFONE, lblTextTelefone.getText().toString());
        values.put(GeTaxiModelDB.UsuarioRegisterEntry.COLUMN_NAME_RUA, lblTextRua.getText().toString());
        values.put(GeTaxiModelDB.UsuarioRegisterEntry.COLUMN_NAME_CIDADE, lblTextCidade.getText().toString());
        values.put(GeTaxiModelDB.UsuarioRegisterEntry.COLUMN_NAME_ESTADO, lblTextEstado.getText().toString());

        bd.insert(values, GeTaxiModelDB.UsuarioRegisterEntry.TABLE_NAME);
    }
}
