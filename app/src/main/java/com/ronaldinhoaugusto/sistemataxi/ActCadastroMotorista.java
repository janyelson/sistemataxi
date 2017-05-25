package com.ronaldinhoaugusto.sistemataxi;

import android.content.ContentValues;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;

public class ActCadastroMotorista extends AppCompatActivity implements View.OnClickListener
{
    private EditText lblTextName, lblTextCNH, lblTextCPF, lblTextDataAdmissao, lblTextDataNasc, lblTextTelefone;
    private Button btnCadastrarMot;
    private GeTaxiDB bd;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_cadastro_motorista);

        bd = new GeTaxiDB(ActCadastroMotorista.this);

        lblTextName = (EditText)findViewById(R.id.lblTextName);
        lblTextCNH = (EditText)findViewById(R.id.lblTextCNH);
        lblTextCPF = (EditText)findViewById(R.id.lblTextCPF);
        lblTextDataAdmissao = (EditText)findViewById(R.id.lblTextDataAdmissao);
        lblTextDataNasc = (EditText)findViewById(R.id.lblTextDataNasc);
        lblTextTelefone = (EditText)findViewById(R.id.lblTextTelefone);
        btnCadastrarMot = (Button)findViewById(R.id.btnCadastrarMot);

        btnCadastrarMot.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        register();
        AlertDialog.Builder dlg = new AlertDialog.Builder(ActCadastroMotorista.this);
        dlg.setMessage("Motorista Cadastro no Sistema!");
        dlg.setNeutralButton("OK",null);
        dlg.show();
    }

    private void register() {
        ContentValues values = new ContentValues();
        values.put(GeTaxiModelDB.MotoristaRegisterEntry.COLUMN_NAME_NAME, lblTextName.getText().toString());
        values.put(GeTaxiModelDB.MotoristaRegisterEntry.COLUMN_NAME_CNH, lblTextCNH.getText().toString());
        values.put(GeTaxiModelDB.MotoristaRegisterEntry.COLUMN_NAME_CPF, lblTextCPF.getText().toString());
        values.put(GeTaxiModelDB.MotoristaRegisterEntry.COLUMN_NAME_DATE_ADMISSION, lblTextDataAdmissao.getText().toString());
        values.put(GeTaxiModelDB.MotoristaRegisterEntry.COLUMN_NAME_DATE_NASCIMENTO, lblTextDataNasc.getText().toString());
        values.put(GeTaxiModelDB.MotoristaRegisterEntry.COLUMN_NAME_TELEFONE, lblTextTelefone.getText().toString());

        bd.insert(values, GeTaxiModelDB.MotoristaRegisterEntry.TABLE_NAME);

    }
}
