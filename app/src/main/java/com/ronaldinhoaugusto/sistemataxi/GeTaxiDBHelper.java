package com.ronaldinhoaugusto.sistemataxi;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.ronaldinhoaugusto.sistemataxi.GeTaxiModelDB.*;

public class GeTaxiDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 17;
    public static final String DATABASE_NAME = "GeTaxi.db";
    private static GeTaxiDBHelper mInstance = null;
    private static Context mCtx = null;

    public static final String SQL_DELETE_ENTRIES_MOTORISTA =
            "DROP TABLE IF EXISTS " + MotoristaRegisterEntry.TABLE_NAME;

    public static final String SQL_DELETE_ENTRIES_VEICULO =
            "DROP TABLE IF EXISTS " + VeiculoRegisterEntry.TABLE_NAME;

    public static final String SQL_DELETE_ENTRIES_CHAMADA =
            "DROP TABLE IF EXISTS " + ChamadaRegisterEntry.TABLE_NAME;

    public static final String SQL_DELETE_ENTRIES_ATENDENTE =
            "DROP TABLE IF EXISTS " + AtendenteRegisterEntry.TABLE_NAME;

    public static final String SQL_DELETE_ENTRIES_USUARIO =
            "DROP TABLE IF EXISTS " + UsuarioRegisterEntry.TABLE_NAME;

    public static final String SQL_DELETE_ENTRIES_SOLICITA =
            "DROP TABLE IF EXISTS " + SolicitaRegisterEntry.TABLE_NAME;

    public static final String SQL_DELETE_ENTRIES_REGISTRA =
            "DROP TABLE IF EXISTS " + RegistraRegisterEntry.TABLE_NAME;


    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INTEGER";
    private static final String BLOP_TYPE = " BLOB";
    private static final String REAL_TYPE = " REAL";
    private static final String DATE_TYPE = " DATE";
    private static final String PRYMARY_KEY = " PRIMARY KEY";
    private static final String FOREIGN_KEY = " FOREIGN KEY";
    private static final String REFERENCES = " REFERENCES";
    private static final String AUTOINCREMENT = " AUTOINCREMENT";
    private static final String NOT_NULL = " NOT NULL";
    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES_MOTORISTA =
            "CREATE TABLE " + MotoristaRegisterEntry.TABLE_NAME + " (" +
                    MotoristaRegisterEntry.COLUMN_NAME_ID + INT_TYPE + PRYMARY_KEY + COMMA_SEP +
                    MotoristaRegisterEntry.COLUMN_NAME_NAME + TEXT_TYPE + NOT_NULL + COMMA_SEP +
                    MotoristaRegisterEntry.COLUMN_NAME_CNH + TEXT_TYPE + NOT_NULL + COMMA_SEP +
                    MotoristaRegisterEntry.COLUMN_NAME_CPF + TEXT_TYPE + NOT_NULL + COMMA_SEP +
                    MotoristaRegisterEntry.COLUMN_NAME_DATE_ADMISSION + INT_TYPE + COMMA_SEP +
                    MotoristaRegisterEntry.COLUMN_NAME_DATE_NASCIMENTO + INT_TYPE + COMMA_SEP +
                    MotoristaRegisterEntry.COLUMN_NAME_LATITUDE + REAL_TYPE + COMMA_SEP +
                    MotoristaRegisterEntry.COLUMN_NAME_LONGITUDE + REAL_TYPE + COMMA_SEP +
                    MotoristaRegisterEntry.COLUMN_NAME_TELEFONE + TEXT_TYPE +
                    " )";

    private static final String SQL_CREATE_ENTRIES_VEICULO =
            "CREATE TABLE " + VeiculoRegisterEntry.TABLE_NAME + " (" +
                    VeiculoRegisterEntry.COLUMN_NAME_ID + INT_TYPE + PRYMARY_KEY + COMMA_SEP +
                    VeiculoRegisterEntry.COLUMN_NAME_ANO + INT_TYPE + NOT_NULL + COMMA_SEP +
                    VeiculoRegisterEntry.COLUMN_NAME_MARCA + TEXT_TYPE + NOT_NULL + COMMA_SEP +
                    VeiculoRegisterEntry.COLUMN_NAME_PLACA + TEXT_TYPE + NOT_NULL + COMMA_SEP +
                    VeiculoRegisterEntry.COLUMN_NAME_PASSAGEIROS + INT_TYPE + COMMA_SEP +
                    VeiculoRegisterEntry.COLUMN_NAME_ID_MOTORISTA + INT_TYPE + COMMA_SEP +
                    FOREIGN_KEY + " (" + VeiculoRegisterEntry.COLUMN_NAME_ID_MOTORISTA + " )" + REFERENCES +
                    " " + MotoristaRegisterEntry.TABLE_NAME + " ( " + MotoristaRegisterEntry.COLUMN_NAME_ID + " )" +
                    " )";

    private static final String SQL_CREATE_ENTRIES_CHAMADA =
            "CREATE TABLE " + ChamadaRegisterEntry.TABLE_NAME + " (" +
                    ChamadaRegisterEntry.COLUMN_NAME_ID + INT_TYPE + PRYMARY_KEY + COMMA_SEP +
                    ChamadaRegisterEntry.COLUMN_NAME_VALUE + REAL_TYPE + NOT_NULL + COMMA_SEP +
                    ChamadaRegisterEntry.COLUMN_NAME_KM + REAL_TYPE + NOT_NULL + COMMA_SEP +
                    ChamadaRegisterEntry.COLUMN_NAME_LOCAL_ORIGEM_LATITUDE + REAL_TYPE + NOT_NULL + COMMA_SEP +
                    ChamadaRegisterEntry.COLUMN_NAME_LOCAL_ORIGEM_LONGITUDE + REAL_TYPE + NOT_NULL + COMMA_SEP +
                    ChamadaRegisterEntry.COLUMN_NAME_DATE_ORIGEM + INT_TYPE + COMMA_SEP +
                    ChamadaRegisterEntry.COLUMN_NAME_LOCAL_DESTINO_LATITUDE + REAL_TYPE + NOT_NULL + COMMA_SEP +
                    ChamadaRegisterEntry.COLUMN_NAME_LOCAL_DESTINO_LONGITUDE + REAL_TYPE + NOT_NULL + COMMA_SEP +
                    ChamadaRegisterEntry.COLUMN_NAME_DATE_DESTINO + INT_TYPE +
                    " )";

    private static final String SQL_CREATE_ENTRIES_ATENDENTE =
            "CREATE TABLE " + AtendenteRegisterEntry.TABLE_NAME + " (" +
                    AtendenteRegisterEntry.COLUMN_NAME_ID + INT_TYPE + PRYMARY_KEY + COMMA_SEP +
                    AtendenteRegisterEntry.COLUMN_NAME_NAME + TEXT_TYPE + NOT_NULL + COMMA_SEP +
                    AtendenteRegisterEntry.COLUMN_NAME_SALARIO + TEXT_TYPE + COMMA_SEP +
                    AtendenteRegisterEntry.COLUMN_NAME_CPF + TEXT_TYPE + NOT_NULL + COMMA_SEP +
                    AtendenteRegisterEntry.COLUMN_NAME_LATITUDE + REAL_TYPE + COMMA_SEP +
                    AtendenteRegisterEntry.COLUMN_NAME_LONGITUDE + REAL_TYPE + COMMA_SEP +
                    AtendenteRegisterEntry.COLUMN_NAME_TELEFONE + TEXT_TYPE +
                    " )";

    private static final String SQL_CREATE_ENTRIES_USUARIO =
            "CREATE TABLE " + UsuarioRegisterEntry.TABLE_NAME + " (" +
                    UsuarioRegisterEntry.COLUMN_NAME_ID + INT_TYPE + PRYMARY_KEY + COMMA_SEP +
                    UsuarioRegisterEntry.COLUMN_NAME_NAME + TEXT_TYPE + NOT_NULL + COMMA_SEP +
                    UsuarioRegisterEntry.COLUMN_NAME_CPF + TEXT_TYPE + NOT_NULL + COMMA_SEP +
                    UsuarioRegisterEntry.COLUMN_NAME_LATITUDE + REAL_TYPE + COMMA_SEP +
                    UsuarioRegisterEntry.COLUMN_NAME_LONGITUDE + REAL_TYPE + COMMA_SEP +
                    UsuarioRegisterEntry.COLUMN_NAME_TELEFONE + TEXT_TYPE +
                    " )";

    private static final String SQL_CREATE_ENTRIES_SOLICITA =
            "CREATE TABLE " + SolicitaRegisterEntry.TABLE_NAME + " (" +
                    SolicitaRegisterEntry.COLUMN_NAME_ID_USUARIO + INT_TYPE + COMMA_SEP +
                    SolicitaRegisterEntry.COLUMN_NAME_ID_ATENDENTE + INT_TYPE + COMMA_SEP +
                    FOREIGN_KEY + " ( " + SolicitaRegisterEntry.COLUMN_NAME_ID_USUARIO + " ) " + REFERENCES +
                    " "  + UsuarioRegisterEntry.TABLE_NAME + "( " + UsuarioRegisterEntry.COLUMN_NAME_ID + " )" + COMMA_SEP +
                    FOREIGN_KEY + " ( " + SolicitaRegisterEntry.COLUMN_NAME_ID_ATENDENTE + " ) " + REFERENCES +
                    " " + AtendenteRegisterEntry.TABLE_NAME + "( " + AtendenteRegisterEntry.COLUMN_NAME_ID + " )" +
                    " )";

    private static final String SQL_CREATE_ENTRIES_REGISTRA =
            "CREATE TABLE " + RegistraRegisterEntry.TABLE_NAME + " (" +
                    RegistraRegisterEntry.COLUMN_NAME_ID_MOTORISTA + INT_TYPE + COMMA_SEP +
                    RegistraRegisterEntry.COLUMN_NAME_ID_ATENDENTE + INT_TYPE + COMMA_SEP +
                    RegistraRegisterEntry.COLUMN_NAME_ID_CHAMADA + INT_TYPE + COMMA_SEP +
                    FOREIGN_KEY + " (" + RegistraRegisterEntry.COLUMN_NAME_ID_MOTORISTA + " )" + REFERENCES +
                    " " + MotoristaRegisterEntry.TABLE_NAME + " ( " + MotoristaRegisterEntry.COLUMN_NAME_ID + " )" + COMMA_SEP +
                    FOREIGN_KEY + " ( " + RegistraRegisterEntry.COLUMN_NAME_ID_ATENDENTE + " )" + REFERENCES +
                    " " + AtendenteRegisterEntry.TABLE_NAME + " (" + AtendenteRegisterEntry.COLUMN_NAME_ID + " )" + COMMA_SEP +
                    FOREIGN_KEY + " (" + RegistraRegisterEntry.COLUMN_NAME_ID_CHAMADA + " )" + REFERENCES +
                    " " + ChamadaRegisterEntry.TABLE_NAME + " (" + ChamadaRegisterEntry.COLUMN_NAME_ID + " )" +
                    " )";



    private GeTaxiDBHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mCtx = context;
    }

    public static GeTaxiDBHelper getInstance(Context ctx) {
        /**
         * use the application context as suggested by CommonsWare.
         * this will ensure that you dont accidentally leak an Activitys
         * context (see this article for more information:
         * http://android-developers.blogspot.nl/2009/01/avoiding-memory-leaks.html)
         */
        if (mInstance == null) {
            mInstance = new GeTaxiDBHelper(ctx.getApplicationContext());
        }
        return mInstance;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES_MOTORISTA);
        db.execSQL(SQL_CREATE_ENTRIES_VEICULO);
        db.execSQL(SQL_CREATE_ENTRIES_CHAMADA);
        db.execSQL(SQL_CREATE_ENTRIES_ATENDENTE);
        db.execSQL(SQL_CREATE_ENTRIES_USUARIO);
        db.execSQL(SQL_CREATE_ENTRIES_SOLICITA);
        db.execSQL(SQL_CREATE_ENTRIES_REGISTRA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES_MOTORISTA);
        db.execSQL(SQL_DELETE_ENTRIES_VEICULO);
        db.execSQL(SQL_DELETE_ENTRIES_CHAMADA);
        db.execSQL(SQL_DELETE_ENTRIES_ATENDENTE);
        db.execSQL(SQL_DELETE_ENTRIES_USUARIO);
        db.execSQL(SQL_DELETE_ENTRIES_SOLICITA);
        db.execSQL(SQL_DELETE_ENTRIES_REGISTRA);
        onCreate(db);
    }
}
