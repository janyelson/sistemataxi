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

public class ActConsultaCliente extends ListActivity implements View.OnClickListener{
    private Button btnPesquisar;
    private EditText lblTextBusca, lblTextValor;
    private String[] ss;
    private GeTaxiDB db;
    private int numberCol = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_consulta_cliente);

        db = new GeTaxiDB(ActConsultaCliente.this);

        lblTextBusca = (EditText) findViewById(R.id.lblTextBusca);
        lblTextValor = (EditText) findViewById(R.id.lblTextValor);
        btnPesquisar = (Button) findViewById(R.id.btnPesquisar);

        btnPesquisar.setOnClickListener(this);
    }

    public void onClick(View v) {
        String message = get();
        AlertDialog.Builder dlg = new AlertDialog.Builder(ActConsultaCliente.this);
        dlg.setMessage(message);
        dlg.setNeutralButton("OK", null);
        dlg.show();
    }

    public String get()
    {
        ss = new String[] {
                GeTaxiModelDB.UsuarioRegisterEntry.COLUMN_NAME_ID,
                GeTaxiModelDB.UsuarioRegisterEntry.COLUMN_NAME_NAME,
                GeTaxiModelDB.UsuarioRegisterEntry.COLUMN_NAME_CPF,
                GeTaxiModelDB.UsuarioRegisterEntry.COLUMN_NAME_RUA,
                GeTaxiModelDB.UsuarioRegisterEntry.COLUMN_NAME_CIDADE,
                GeTaxiModelDB.UsuarioRegisterEntry.COLUMN_NAME_ESTADO,
                GeTaxiModelDB.UsuarioRegisterEntry.COLUMN_NAME_TELEFONE
        };
        int[] ii = new int[] {
                R.id.textIdCliente,
                R.id.textNomeCliente,
                R.id.textCPF,
                R.id.textRua,
                R.id.textCidade,
                R.id.textEstado,
                R.id.textTelefone
        };

        String retorno = "Ok!";
        String where = lblTextBusca.getText().toString();
        String valor = lblTextValor.getText().toString();
        if(where.trim().length() == 0 || verify(where)) {

            Cursor mCursor;

            if(where.trim().length() == 0) {
                mCursor = db.getAll(GeTaxiModelDB.UsuarioRegisterEntry.TABLE_NAME, "");
            }
            else {
                where = "usuario." + where;
                if(numberCol != 0) {
                    valor = "'" + valor + "'";
                }
                mCursor = db.getAll(GeTaxiModelDB.UsuarioRegisterEntry.TABLE_NAME, " where " + where + " = " + valor);
            }


            ListAdapter adapter = new SimpleCursorAdapter(
                    this,
                    R.layout.item_cliente,
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
