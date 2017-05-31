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

/**
 * Created by MEU PC on 28/05/2017.
 */

public class ActConsultaAtendente extends ListActivity implements View.OnClickListener{
    private Button btnPesquisar;
    private EditText lblTextBusca, lblTextValor;
    private String[] ss;
    private int numberCol = 0;

    private GeTaxiDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_consulta_atendente);

        db = new GeTaxiDB(ActConsultaAtendente.this);

        lblTextBusca = (EditText) findViewById(R.id.lblTextBusca);
        lblTextValor = (EditText) findViewById(R.id.lblTextValor);
        btnPesquisar = (Button) findViewById(R.id.btnPesquisar);

        btnPesquisar.setOnClickListener(this);
    }

    public void onClick(View v) {
        String message = get();
        AlertDialog.Builder dlg = new AlertDialog.Builder(ActConsultaAtendente.this);
        dlg.setMessage(message);
        dlg.setNeutralButton("OK", null);
        dlg.show();
    }

    public String get() {
        ss = new String[]{
                GeTaxiModelDB.AtendenteRegisterEntry.COLUMN_NAME_ID,
                GeTaxiModelDB.AtendenteRegisterEntry.COLUMN_NAME_NAME,
                GeTaxiModelDB.AtendenteRegisterEntry.COLUMN_NAME_SALARIO,
                GeTaxiModelDB.AtendenteRegisterEntry.COLUMN_NAME_CPF,
                GeTaxiModelDB.AtendenteRegisterEntry.COLUMN_NAME_TELEFONE};
        int[] ii = new int[]{
                R.id.textIdAtendente,
                R.id.textNomeAtendente,
                R.id.textSalario,
                R.id.textCPF,
                R.id.textTelefone,
        };

        String retorno = "Ok!";
        String where = lblTextBusca.getText().toString();
        String valor = lblTextValor.getText().toString();
        if (where.trim().length() == 0 || verify(where)) {

            Cursor mCursor;

            if(where.trim().length() == 0) {
                mCursor = db.getAll(GeTaxiModelDB.AtendenteRegisterEntry.TABLE_NAME, "");
            }
            else {
                if(numberCol != 0) {
                    valor = "'" + valor + "'";
                }
                where = "atendente." + where;
                mCursor = db.getAll(GeTaxiModelDB.AtendenteRegisterEntry.TABLE_NAME, " where " + where + " = " + valor);
            }

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
        else retorno = "Erro, atributo nao encontrado";
        return retorno;
    }

    private boolean verify(String col) {
        for(int i = 0; i < ss.length; i++) {
            if(ss[i].equals(col.toLowerCase())) {
                numberCol = i;
                return true;
            }
        }

        return false;
    }
}
