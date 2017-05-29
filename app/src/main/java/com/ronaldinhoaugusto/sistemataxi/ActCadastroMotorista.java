package com.ronaldinhoaugusto.sistemataxi;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class ActCadastroMotorista extends AppCompatActivity implements View.OnClickListener
{
    private EditText lblTextId, lblTextName, lblTextCNH, lblTextCPF, lblTextTelefone;
    private EditText lblTextAno, lblTextMarca, lblTextPlaca, lblTextPassageiros;
    private DatePicker lblTextDataAdmissao, lblTextDataNasc;
    private Calendar DateAdmissao, DateNasc;
    private Button btnCadastrarMot;
    private GeTaxiDB bd;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_cadastro_motorista);

        bd = new GeTaxiDB(ActCadastroMotorista.this);

        lblTextId = (EditText)findViewById(R.id.lblTextId);
        lblTextName = (EditText)findViewById(R.id.lblTextName);
        lblTextCNH = (EditText)findViewById(R.id.lblTextCNH);
        lblTextCPF = (EditText)findViewById(R.id.lblTextCPF);
        lblTextDataAdmissao = (DatePicker)findViewById(R.id.lblTextDataAdmissao);
        lblTextDataNasc = (DatePicker)findViewById(R.id.lblTextDataNasc);
        lblTextTelefone = (EditText)findViewById(R.id.lblTextTelefone);

        lblTextAno = (EditText)findViewById(R.id.lblTextAno);
        lblTextMarca = (EditText)findViewById(R.id.lblTextMarca);
        lblTextPlaca = (EditText)findViewById(R.id.lblTextPlaca);
        lblTextPassageiros = (EditText)findViewById(R.id.lblTextPassageiros);

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
        ContentValues values1 = new ContentValues();
        ContentValues values2 = new ContentValues();

        values1.put(GeTaxiModelDB.MotoristaRegisterEntry.COLUMN_NAME_ID, lblTextId.getText().toString());
        values1.put(GeTaxiModelDB.MotoristaRegisterEntry.COLUMN_NAME_NAME, lblTextName.getText().toString());
        values1.put(GeTaxiModelDB.MotoristaRegisterEntry.COLUMN_NAME_CNH, lblTextCNH.getText().toString());
        values1.put(GeTaxiModelDB.MotoristaRegisterEntry.COLUMN_NAME_CPF, lblTextCPF.getText().toString());

        //DateAdmissao = Calendar.getInstance();
        String data =  lblTextDataAdmissao.getDayOfMonth() + "/"+ lblTextDataAdmissao.getMonth() + "/"+ lblTextDataAdmissao.getYear();
        //DateAdmissao.set(Calendar.DAY_OF_MONTH, lblTextDataAdmissao.getDayOfMonth());
        //DateAdmissao.set(Calendar.MONTH, lblTextDataAdmissao.getMonth());
        //DateAdmissao.set(Calendar.YEAR, lblTextDataAdmissao.getYear());

        values1.put(GeTaxiModelDB.MotoristaRegisterEntry.COLUMN_NAME_DATE_ADMISSION, data);

        //DateNasc = Calendar.getInstance();
        //DateNasc.set(Calendar.DAY_OF_MONTH, lblTextDataNasc.getDayOfMonth());
        //DateNasc.set(Calendar.MONTH, lblTextDataNasc.getMonth());
        //DateNasc.set(Calendar.YEAR, lblTextDataNasc.getYear());

        data =  lblTextDataNasc.getDayOfMonth() + "/"+ lblTextDataNasc.getMonth() + "/"+ lblTextDataNasc.getYear();


        values1.put(GeTaxiModelDB.MotoristaRegisterEntry.COLUMN_NAME_DATE_NASCIMENTO, "" + data);
        values1.put(GeTaxiModelDB.MotoristaRegisterEntry.COLUMN_NAME_TELEFONE, lblTextTelefone.getText().toString());

        values2.put(GeTaxiModelDB.VeiculoRegisterEntry.COLUMN_NAME_ANO, lblTextAno.getText().toString());
        values2.put(GeTaxiModelDB.VeiculoRegisterEntry.COLUMN_NAME_MARCA, lblTextMarca.getText().toString());
        values2.put(GeTaxiModelDB.VeiculoRegisterEntry.COLUMN_NAME_PLACA, lblTextPlaca.getText().toString());
        values2.put(GeTaxiModelDB.VeiculoRegisterEntry.COLUMN_NAME_PASSAGEIROS, lblTextPassageiros.getText().toString());
        values2.put(GeTaxiModelDB.VeiculoRegisterEntry.COLUMN_NAME_ID_MOTORISTA, lblTextId.getText().toString());

        bd.insert(values1, values2);
    }
}
