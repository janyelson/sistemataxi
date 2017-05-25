package com.ronaldinhoaugusto.sistemataxi;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActConsultaMotorista extends AppCompatActivity implements View.OnClickListener {
    private TextView lblTitle, lblName, lblCNH, lblCPF, lblDataAdmissao, lblDataNasc, lblTelefone;
    private EditText txtName;
    private Button btnPesquisar;

    private GeTaxiDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_consulta_motorista);

        db = new GeTaxiDB(ActConsultaMotorista.this);
        lblTitle = (TextView) findViewById(R.id.lblTitle);
        lblName = (TextView) findViewById(R.id.editTextName);
        lblCNH = (TextView) findViewById(R.id.editTextCNH);
        lblCPF = (TextView) findViewById(R.id.editTextCPF);
        lblDataAdmissao = (TextView) findViewById(R.id.editTextDataAdmissao);
        lblDataNasc = (TextView) findViewById(R.id.editTextDataNasc);
        lblTelefone = (TextView) findViewById(R.id.editTextTelefone);
        txtName = (EditText) findViewById(R.id.txtName);
        btnPesquisar = (Button) findViewById(R.id.btnPesquisar);

        btnPesquisar.setOnClickListener(this);
    }

    public void onClick(View v) {
        get();
        AlertDialog.Builder dlg = new AlertDialog.Builder(ActConsultaMotorista.this);
        dlg.setMessage("Consultando Motorista no Sistema...");
        dlg.setNeutralButton("OK", null);
        dlg.show();
    }

    public void get()
    {
        ContentValues values = db.load(GeTaxiModelDB.MotoristaRegisterEntry.TABLE_NAME, txtName.getText().toString());
        Log.e("get", txtName.getText().toString());
        lblName.setText(values.getAsString(GeTaxiModelDB.MotoristaRegisterEntry.COLUMN_NAME_NAME));
        Log.e("get", txtName.getText().toString());
        lblCNH.setText(values.getAsString(GeTaxiModelDB.MotoristaRegisterEntry.COLUMN_NAME_CNH));
        lblCPF.setText(values.getAsString(GeTaxiModelDB.MotoristaRegisterEntry.COLUMN_NAME_CPF));
        lblDataAdmissao.setText(values.getAsString(GeTaxiModelDB.MotoristaRegisterEntry.COLUMN_NAME_DATE_ADMISSION));
        lblDataNasc.setText(values.getAsString(GeTaxiModelDB.MotoristaRegisterEntry.COLUMN_NAME_DATE_NASCIMENTO));
        lblTelefone.setText(values.getAsString(GeTaxiModelDB.MotoristaRegisterEntry.COLUMN_NAME_TELEFONE));
    }
}
