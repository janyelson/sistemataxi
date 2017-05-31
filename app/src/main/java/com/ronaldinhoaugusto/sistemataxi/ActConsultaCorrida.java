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

public class ActConsultaCorrida extends ListActivity implements View.OnClickListener{
    private Button btnPesquisar;
    private EditText lblTextBusca, lblTextValor;
    private String[] ss;
    private GeTaxiDB db;
    private int numberCol = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_consulta_corrida);

        db = new GeTaxiDB(ActConsultaCorrida.this);

        lblTextBusca = (EditText) findViewById(R.id.lblTextBusca);
        lblTextValor = (EditText) findViewById(R.id.lblTextValor);
        btnPesquisar = (Button) findViewById(R.id.btnPesquisar);

        btnPesquisar.setOnClickListener(this);
    }

    public void onClick(View v) {
        String message = get();
        AlertDialog.Builder dlg = new AlertDialog.Builder(ActConsultaCorrida.this);
        dlg.setMessage(message);
        dlg.setNeutralButton("OK", null);
        dlg.show();
    }

    public String get()
    {
        ss = new String[] {
                GeTaxiModelDB.ChamadaRegisterEntry.COLUMN_NAME_ID,
                GeTaxiModelDB.ChamadaRegisterEntry.COLUMN_NAME_VALUE,
                GeTaxiModelDB.ChamadaRegisterEntry.COLUMN_NAME_LOCAL_ORIGEM,
                GeTaxiModelDB.ChamadaRegisterEntry.COLUMN_NAME_LOCAL_DESTINO};
        int[] ii = new int[] {
                R.id.textProtocolo,
                R.id.textValor,
                R.id.textOrigem,
                R.id.textDestino
        };

        String retorno = "Ok!";
        String where = lblTextBusca.getText().toString();
        String valor = lblTextValor.getText().toString();
        if (where.trim().length() == 0 || verify(where)) {

            Cursor mCursor;

            if(where.trim().length() == 0) {
                mCursor = db.getAll(GeTaxiModelDB.ChamadaRegisterEntry.TABLE_NAME, "");
            }
            else {
                if(numberCol != 0 && numberCol != 1) {
                    valor = "'" + valor + "'";
                }
                where = "chamada." + where;
                mCursor = db.getAll(GeTaxiModelDB.ChamadaRegisterEntry.TABLE_NAME, " where " + where + " = " + valor);
            }

            ListAdapter adapter = new SimpleCursorAdapter(
                    this,
                    R.layout.item_corrida,
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