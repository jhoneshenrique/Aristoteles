package com.example.android.aristoteles;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jhoneshenrique on 11/3/2017.
 */

public class CriaBanco extends SQLiteOpenHelper {
    private static final String NOME_BANCO = "ari.db";
    public static final String TABELA = "jogador";
    public static final String ID = "_id";
    public static final String STATUSAULA = "statusAula";
    public static final String PONTOS = "pontos";
    public static final String EMAIL = "email";
    private static final int VERSAO = 10;

    public CriaBanco(Context context){
        super(context, NOME_BANCO,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABELA+"("
                + ID + " integer primary key autoincrement,"
                + STATUSAULA + " integer,"
                + PONTOS + " integer,"
                + EMAIL + " text unique"
                +")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);
    }
}