package com.ronaldinhoaugusto.sistemataxi;

/**
 * Created by MEU PC on 31/05/2017.
 */


import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

public class ActMotoristasAcessiveis extends ListActivity implements View.OnClickListener{

    private String[] ss;
    private EditText txtIdMotorista;
    private GeTaxiDB db;
    private String origem, destino, valor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motoristas_acessiveis);

        Intent intent = getIntent();
        origem = intent.getStringExtra("origem");
        destino = intent.getStringExtra("destino");
        valor = intent.getStringExtra("valor");

        txtIdMotorista = (EditText) findViewById(R.id.lblTextIdMotorista);
        Button select = (Button) findViewById(R.id.btnSolicitar);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(SelectDriver.this, Order.class));
                solicitar();
            }
        });

        ss = new String[] {
                GeTaxiModelDB.MotoristaRegisterEntry.COLUMN_NAME_ID,
                GeTaxiModelDB.MotoristaRegisterEntry.COLUMN_NAME_NAME,
                GeTaxiModelDB.MotoristaRegisterEntry.COLUMN_NAME_TELEFONE,
                GeTaxiModelDB.VeiculoRegisterEntry.COLUMN_NAME_ANO,
                GeTaxiModelDB.VeiculoRegisterEntry.COLUMN_NAME_MARCA,
                GeTaxiModelDB.VeiculoRegisterEntry.COLUMN_NAME_PLACA,
                GeTaxiModelDB.VeiculoRegisterEntry.COLUMN_NAME_PASSAGEIROS};
        int[] ii = new int[] {
                R.id.textIdMotorista,
                R.id.textNomeMotorista,
                R.id.textTelefone,
                R.id.textAno,
                R.id.textMarca,
                R.id.textPlaca,
                R.id.textPassageiros
        };
        db = new GeTaxiDB(ActMotoristasAcessiveis.this);
        Cursor mCursor = db.getAllMotorista("");
        ListAdapter adapter = new SimpleCursorAdapter(
                this,
                R.layout.item_motoristas_acessiveis,
                mCursor,
                ss,
                ii,
                0
        );

        setListAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                super.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

    }

    private void solicitar() {
        ContentValues values1 = new ContentValues();
        ContentValues values2 = new ContentValues();
        //values.put(GeTaxiModelDB.ChamadaRegisterEntry.COLUMN_NAME_ID, lblTextProtocolo.getText().toString());
        values1.put(GeTaxiModelDB.ChamadaRegisterEntry.COLUMN_NAME_VALUE, valor);
        values1.put(GeTaxiModelDB.ChamadaRegisterEntry.COLUMN_NAME_LOCAL_ORIGEM, origem);
        values1.put(GeTaxiModelDB.ChamadaRegisterEntry.COLUMN_NAME_LOCAL_DESTINO, destino);



        //values.put(GeTaxiModelDB.SolicitaRegisterEntry.COLUMN_NAME_ID_ATENDENTE, lblTextIdAtendente.getText().toString());
        //values.put(GeTaxiModelDB.SolicitaRegisterEntry.COLUMN_NAME_ID_USUARIO, lblTextPassageiro.getText().toString());
        //bd.insert(values, GeTaxiModelDB.SolicitaRegisterEntry.TABLE_NAME);

        //values2.put(GeTaxiModelDB.RegistraRegisterEntry.COLUMN_NAME_ID_CHAMADA, lblTextProtocolo.getText().toString());
        //values2.put(GeTaxiModelDB.RegistraRegisterEntry.COLUMN_NAME_ID_ATENDENTE, lblTextIdAtendente.getText().toString());
        String motorista = txtIdMotorista.getText().toString();
        //if(txtIdMotorista.getText().toString().trim() == 0) {

        //}
        values2.put(GeTaxiModelDB.RegistraRegisterEntry.COLUMN_NAME_ID_MOTORISTA, motorista);

        db.insertChamada(values1, values2);

        //startActivity(new Intent(ActMotoristasAcessiveis.this, GeTaxiHome.class));
    }
}