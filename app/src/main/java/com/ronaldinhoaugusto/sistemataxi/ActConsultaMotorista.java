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

public class ActConsultaMotorista extends ListActivity implements View.OnClickListener {
    private EditText lblTextBusca, lblTextValor;
    private Button btnPesquisar;
    private String[] ss;
    private boolean tab = true;
    private int numberCol = 0;

    private GeTaxiDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_consulta_motorista);

        db = new GeTaxiDB(ActConsultaMotorista.this);

        lblTextBusca = (EditText) findViewById(R.id.lblTextBusca);
        lblTextValor = (EditText) findViewById(R.id.lblTextValor);
        btnPesquisar = (Button) findViewById(R.id.btnPesquisar);

        btnPesquisar.setOnClickListener(this);
    }

    public void onClick(View v) {
        String message = get();
        AlertDialog.Builder dlg = new AlertDialog.Builder(ActConsultaMotorista.this);
        dlg.setMessage(message);
        dlg.setNeutralButton("OK", null);
        dlg.show();
    }

    public String get()
    {
        ss = new String[] {
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
                GeTaxiModelDB.VeiculoRegisterEntry.COLUMN_NAME_PASSAGEIROS};
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
        String retorno = "Ok!";
        String where = lblTextBusca.getText().toString();
        String valor = lblTextValor.getText().toString();
        if(where.trim().length() == 0 || verify(where)) {
            Cursor mCursor;
            if(where.trim().length() == 0) {
                mCursor = db.getAllMotorista("");
            }
            else {
                if(numberCol != 0 && numberCol != 7 && numberCol != 10) {
                    valor = "'" + valor + "'";
                }
                if(tab) where = "motorista." + where;
                else where = "veiculo." + where;

                mCursor = db.getAllMotorista(" where " + where + " = " + valor);
            }
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
        else retorno = "Erro, atributo nao encontrado";
        return retorno;
    }

    private boolean verify(String col) {
        for(int i = 0; i < ss.length; i++) {
            if(ss[i].equals(col.toLowerCase())) {
                numberCol = i;
                if(i > 6) tab = false;
                return true;
            }
        }

        return false;
    }
}
