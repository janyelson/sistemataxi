package com.ronaldinhoaugusto.sistemataxi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class GeTaxiDB {
    private GeTaxiDBHelper geTaxiDBHelper;

    public GeTaxiDB(Context context) {
        geTaxiDBHelper = GeTaxiDBHelper.getInstance(context);
    }

    public boolean insert(ContentValues values, String tabela) {

        try {
            SQLiteDatabase db = geTaxiDBHelper.getWritableDatabase();
            long result = db.insertOrThrow(tabela, null, values);
        }catch (Exception e) {
            Log.e("Erro:", e.toString());
            return false;
        }

        return true;
        //db.close();
    }

    public boolean insert(ContentValues motorista, ContentValues veiculo) {
        try {
            SQLiteDatabase db = geTaxiDBHelper.getWritableDatabase();
            long idMotorista = db.insertOrThrow(GeTaxiModelDB.MotoristaRegisterEntry.TABLE_NAME, null, motorista);
            veiculo.put(GeTaxiModelDB.VeiculoRegisterEntry.COLUMN_NAME_ID_MOTORISTA, "" + idMotorista);
            db.insertOrThrow(GeTaxiModelDB.VeiculoRegisterEntry.TABLE_NAME, null, veiculo);
        }catch(Exception e) {
            Log.e("Motorista:", "id errado");
            return false;
        }

        return true;
    }

    public boolean insertChamada(ContentValues chamada, ContentValues registro) {
        try {
            SQLiteDatabase db = geTaxiDBHelper.getWritableDatabase();
            long protocolo = db.insertOrThrow(GeTaxiModelDB.ChamadaRegisterEntry.TABLE_NAME, null, chamada);
            registro.put(GeTaxiModelDB.RegistraRegisterEntry.COLUMN_NAME_ID_CHAMADA, "" + protocolo);
            db.insertOrThrow(GeTaxiModelDB.RegistraRegisterEntry.TABLE_NAME, null, registro);
        }catch(Exception e) {
            Log.e("Registro:", "id errado");
            return false;
        }

        return true;
    }

    public void update(ContentValues values, String tabela, String id) {
        SQLiteDatabase db = geTaxiDBHelper.getWritableDatabase();
        long result = db.update(tabela, values, id, null);
        db.close();
    }

    public Cursor getAllMotorista(String where) {
        SQLiteDatabase db = geTaxiDBHelper.getReadableDatabase();
        String sql = "SELECT * FROM motorista INNER JOIN veiculo ON motorista._id = veiculo.id_motorista" + where ;
        Cursor c = db.rawQuery( sql, null);
        //db.close();
        return c;
    }

    public Cursor getAll(String tabela, String where) {
        SQLiteDatabase db = geTaxiDBHelper.getReadableDatabase();
        String sql = "SELECT * FROM " + tabela + where ;
        Cursor c = db.rawQuery( sql, null);

        //Cursor c = db.query(
        //        tabela,
        //        null,
        //        null,
        //        null,
        //        null,
        //        null,
        //        null
        //);

        return c;
    }

    public boolean auntenticarUsuario(String nome) {
        String tabela = GeTaxiModelDB.UsuarioRegisterEntry.TABLE_NAME;
        SQLiteDatabase db = geTaxiDBHelper.getReadableDatabase();
        String sql = "SELECT * FROM " + tabela;
        Cursor c = db.rawQuery( sql, null);

        if (c.moveToFirst()) {
            do {
                String n = c.getString(c.getColumnIndex(GeTaxiModelDB.UsuarioRegisterEntry.COLUMN_NAME_NAME));
                if (n.toLowerCase().equals(nome.toLowerCase())) {
                    return true;
                }
            } while (c.moveToNext());
        }
            return false;
    }

    public boolean auntenticarAtendente(String nome) {
        String tabela = GeTaxiModelDB.AtendenteRegisterEntry.TABLE_NAME;
        SQLiteDatabase db = geTaxiDBHelper.getReadableDatabase();
        String sql = "SELECT * FROM " + tabela;
        Cursor c = db.rawQuery( sql, null);

        if (c.moveToFirst()) {
            do {
                String n = c.getString(1);
                if (n.toLowerCase().equals(nome.toLowerCase())) {
                    return true;
                }
            } while (c.moveToNext());
        }
        return false;
    }
}
