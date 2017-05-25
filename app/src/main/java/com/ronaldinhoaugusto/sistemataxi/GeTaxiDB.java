package com.ronaldinhoaugusto.sistemataxi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class GeTaxiDB {
    private GeTaxiDBHelper geTaxiDBHelper;

    public GeTaxiDB(Context context) {
        geTaxiDBHelper = new GeTaxiDBHelper(context);
    }

    public void insert(ContentValues values, String tabela) {
        SQLiteDatabase db = geTaxiDBHelper.getWritableDatabase();
        long result = db.insert(tabela, null, values);
        db.close();
    }

    public void update(ContentValues values, String tabela, String id) {
        SQLiteDatabase db = geTaxiDBHelper.getWritableDatabase();
        long result = db.update(tabela, values, id, null);
        db.close();
    }

    public ContentValues load(String tabela, String nome) {
        SQLiteDatabase db = geTaxiDBHelper.getReadableDatabase();
        ContentValues values = new ContentValues();

        Cursor c = db.query(
                tabela,
                null,
                "nome" + " = ? ",
                new String[]{nome},
                null,
                null,
                null
        );

        switch(tabela) {
            case "motorista":
                if (c.moveToFirst()) {
                    values.put(GeTaxiModelDB.MotoristaRegisterEntry.COLUMN_NAME_ID, c.getInt(0));
                    values.put(GeTaxiModelDB.MotoristaRegisterEntry.COLUMN_NAME_NAME, c.getString(1));
                    values.put(GeTaxiModelDB.MotoristaRegisterEntry.COLUMN_NAME_CNH, c.getString(2));
                    values.put(GeTaxiModelDB.MotoristaRegisterEntry.COLUMN_NAME_CPF, c.getString(3));
                    values.put(GeTaxiModelDB.MotoristaRegisterEntry.COLUMN_NAME_DATE_ADMISSION, c.getLong(4));
                    values.put(GeTaxiModelDB.MotoristaRegisterEntry.COLUMN_NAME_DATE_NASCIMENTO, c.getLong(5));
                    //values.put(GeTaxiModelDB.MotoristaRegisterEntry.COLUMN_NAME_LATITUDE, c.getDouble(6));
                    //values.put(GeTaxiModelDB.MotoristaRegisterEntry.COLUMN_NAME_LONGITUDE, c.getDouble(7));
                    values.put(GeTaxiModelDB.MotoristaRegisterEntry.COLUMN_NAME_TELEFONE, c.getString(8));
                }
                break;
            case "veiculo":
                if (c.moveToFirst()) {
                    values.put(GeTaxiModelDB.VeiculoRegisterEntry.COLUMN_NAME_ID, c.getInt(0));
                    values.put(GeTaxiModelDB.VeiculoRegisterEntry.COLUMN_NAME_ANO, c.getInt(1));
                    values.put(GeTaxiModelDB.VeiculoRegisterEntry.COLUMN_NAME_MARCA, c.getString(2));
                    values.put(GeTaxiModelDB.VeiculoRegisterEntry.COLUMN_NAME_PLACA, c.getString(3));
                    values.put(GeTaxiModelDB.VeiculoRegisterEntry.COLUMN_NAME_ID_MOTORISTA, c.getInt(4));
                }
                break;
            case "chamada":
                if (c.moveToFirst()) {
                    values.put(GeTaxiModelDB.ChamadaRegisterEntry.COLUMN_NAME_ID, c.getInt(0));
                    values.put(GeTaxiModelDB.ChamadaRegisterEntry.COLUMN_NAME_VALUE, c.getDouble(1));
                    values.put(GeTaxiModelDB.ChamadaRegisterEntry.COLUMN_NAME_KM, c.getDouble(2));
                    values.put(GeTaxiModelDB.ChamadaRegisterEntry.COLUMN_NAME_LOCAL_ORIGEM_LATITUDE, c.getDouble(3));
                    values.put(GeTaxiModelDB.ChamadaRegisterEntry.COLUMN_NAME_LOCAL_ORIGEM_LONGITUDE, c.getDouble(4));
                    values.put(GeTaxiModelDB.ChamadaRegisterEntry.COLUMN_NAME_DATE_ORIGEM, c.getLong(5));
                    values.put(GeTaxiModelDB.ChamadaRegisterEntry.COLUMN_NAME_LOCAL_DESTINO_LATITUDE, c.getDouble(6));
                    values.put(GeTaxiModelDB.ChamadaRegisterEntry.COLUMN_NAME_LOCAL_DESTINO_LONGITUDE, c.getDouble(7));
                    values.put(GeTaxiModelDB.ChamadaRegisterEntry.COLUMN_NAME_DATE_DESTINO, c.getLong(8));
                }
                break;
            case "atendente":
                if (c.moveToFirst()) {
                    values.put(GeTaxiModelDB.AtendenteRegisterEntry.COLUMN_NAME_ID, c.getInt(0));
                    values.put(GeTaxiModelDB.AtendenteRegisterEntry.COLUMN_NAME_NAME, c.getString(1));
                    values.put(GeTaxiModelDB.AtendenteRegisterEntry.COLUMN_NAME_SALARIO, c.getDouble(2));
                    values.put(GeTaxiModelDB.AtendenteRegisterEntry.COLUMN_NAME_CPF, c.getString(3));
                    values.put(GeTaxiModelDB.AtendenteRegisterEntry.COLUMN_NAME_LATITUDE, c.getDouble(4));
                    values.put(GeTaxiModelDB.AtendenteRegisterEntry.COLUMN_NAME_LONGITUDE, c.getDouble(5));
                    values.put(GeTaxiModelDB.AtendenteRegisterEntry.COLUMN_NAME_TELEFONE, c.getLong(6));
                }
                break;
            case "usuario":
                if (c.moveToFirst()) {
                    values.put(GeTaxiModelDB.UsuarioRegisterEntry.COLUMN_NAME_ID, c.getInt(0));
                    values.put(GeTaxiModelDB.UsuarioRegisterEntry.COLUMN_NAME_NAME, c.getString(1));
                    values.put(GeTaxiModelDB.UsuarioRegisterEntry.COLUMN_NAME_CPF, c.getString(2));
                    values.put(GeTaxiModelDB.UsuarioRegisterEntry.COLUMN_NAME_LONGITUDE, c.getDouble(3));
                    values.put(GeTaxiModelDB.UsuarioRegisterEntry.COLUMN_NAME_LATITUDE, c.getDouble(4));
                    values.put(GeTaxiModelDB.UsuarioRegisterEntry.COLUMN_NAME_TELEFONE, c.getLong(5));
                }
                break;
            default:
        }
        db.close();
        return values;
    }
}