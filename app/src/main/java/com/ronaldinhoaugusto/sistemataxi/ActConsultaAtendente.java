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

/**
 * Created by MEU PC on 28/05/2017.
 */

public class ActConsultaAtendente extends ListActivity implements View.OnClickListener{
    private TextView lblTitle, lblName, lblCNH, lblCPF, lblDataAdmissao, lblDataNasc, lblTelefone;
    private EditText txtName;
    private Button btnPesquisar;

    private GeTaxiDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_consulta_atendente);

        db = new GeTaxiDB(ActConsultaAtendente.this);

        btnPesquisar = (Button) findViewById(R.id.btnPesquisar);

        btnPesquisar.setOnClickListener(this);
    }

    public void onClick(View v) {
        get();
        AlertDialog.Builder dlg = new AlertDialog.Builder(ActConsultaAtendente.this);
        dlg.setMessage("Consultando Atendente no Sistema...");
        dlg.setNeutralButton("OK", null);
        dlg.show();
    }

    public void get()
    {
        String[] ss = new String[] {
                GeTaxiModelDB.AtendenteRegisterEntry.COLUMN_NAME_ID,
                GeTaxiModelDB.AtendenteRegisterEntry.COLUMN_NAME_NAME,
                GeTaxiModelDB.AtendenteRegisterEntry.COLUMN_NAME_SALARIO,
                GeTaxiModelDB.AtendenteRegisterEntry.COLUMN_NAME_CPF,
                GeTaxiModelDB.AtendenteRegisterEntry.COLUMN_NAME_TELEFONE};
        int[] ii = new int[] {
                R.id.textIdAtendente,
                R.id.textNomeAtendente,
                R.id.textSalario,
                R.id.textCPF,
                R.id.textTelefone,
        };

        Cursor mCursor = db.getAll(GeTaxiModelDB.AtendenteRegisterEntry.TABLE_NAME);

        ListAdapter adapter = new SimpleCursorAdapter(
                this,
                R.layout.item_atendente,
                mCursor,
                ss,
                ii,
                0
        );
        setListAdapter(adapter);
    }
}
