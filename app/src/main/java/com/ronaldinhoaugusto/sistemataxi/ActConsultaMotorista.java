package com.ronaldinhoaugusto.sistemataxi;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class ActConsultaMotorista extends ListActivity implements View.OnClickListener {
    private TextView lblTitle, lblName, lblCNH, lblCPF, lblDataAdmissao, lblDataNasc, lblTelefone;
    private EditText txtName;
    private Button btnPesquisar;

    private GeTaxiDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_consulta_motorista);

        db = new GeTaxiDB(ActConsultaMotorista.this);

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
        String[] ss = new String[] {
                GeTaxiModelDB.MotoristaRegisterEntry.COLUMN_NAME_ID,
                GeTaxiModelDB.MotoristaRegisterEntry.COLUMN_NAME_NAME,
                GeTaxiModelDB.MotoristaRegisterEntry.COLUMN_NAME_CNH,
                GeTaxiModelDB.MotoristaRegisterEntry.COLUMN_NAME_CPF,
                GeTaxiModelDB.MotoristaRegisterEntry.COLUMN_NAME_DATE_ADMISSION,
                GeTaxiModelDB.MotoristaRegisterEntry.COLUMN_NAME_DATE_NASCIMENTO,
                GeTaxiModelDB.MotoristaRegisterEntry.COLUMN_NAME_TELEFONE,
                GeTaxiModelDB.VeiculoRegisterEntry.COLUMN_NAME_ANO,
                GeTaxiModelDB.VeiculoRegisterEntry.COLUMN_NAME_MARCA,
                GeTaxiModelDB.VeiculoRegisterEntry.COLUMN_NAME_PLACA,
                GeTaxiModelDB.VeiculoRegisterEntry.COLUMN_NAME_PASSAGEIROS,};
        int[] ii = new int[] {
                R.id.textIdMotorista,
                R.id.textNomeMotorista,
                R.id.textCNH,
                R.id.textCPF,
                R.id.textDataAdmissao,
                R.id.textDataNasc,
                R.id.textTelefone,
                R.id.textAno,
                R.id.textMarca,
                R.id.textPlaca,
                R.id.textPassageiros
        };

        Cursor mCursor = db.getAllMotorista();

        ListAdapter adapter = new SimpleCursorAdapter(
                this,
                R.layout.item_motorista,
                mCursor,
                ss,
                ii,
                0
        );

        setListAdapter(adapter);

    }
}
