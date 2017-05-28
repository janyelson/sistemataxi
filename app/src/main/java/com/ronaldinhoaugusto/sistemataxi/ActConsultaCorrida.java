package com.ronaldinhoaugusto.sistemataxi;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

/**
 * Created by MEU PC on 28/05/2017.
 */

public class ActConsultaCorrida extends ListActivity implements View.OnClickListener{
    private Button btnPesquisar;

    private GeTaxiDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_consulta_corrida);

        db = new GeTaxiDB(ActConsultaCorrida.this);

        btnPesquisar = (Button) findViewById(R.id.btnPesquisar);

        btnPesquisar.setOnClickListener(this);
    }

    public void onClick(View v) {
        get();
        AlertDialog.Builder dlg = new AlertDialog.Builder(ActConsultaCorrida.this);
        dlg.setMessage("Consultando Corrida no Sistema...");
        dlg.setNeutralButton("OK", null);
        dlg.show();
    }

    public void get()
    {
        String[] ss = new String[] {
                GeTaxiModelDB.ChamadaRegisterEntry.COLUMN_NAME_ID,
                GeTaxiModelDB.ChamadaRegisterEntry.COLUMN_NAME_VALUE};
        int[] ii = new int[] {
                R.id.textProtocolo,
                R.id.textValor,
        };

        Cursor mCursor = db.getAll(GeTaxiModelDB.ChamadaRegisterEntry.TABLE_NAME);

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
}