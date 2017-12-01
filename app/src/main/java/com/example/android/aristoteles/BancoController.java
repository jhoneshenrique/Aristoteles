package com.example.android.aristoteles;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by jhoneshenrique on 11/3/2017.
 */

public class BancoController {
    private SQLiteDatabase db;
    private CriaBanco banco;

    public BancoController(Context context){

        banco = new CriaBanco(context);
    }

    public String insereDado(int statusAula, int pontos,String email){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CriaBanco.STATUSAULA, statusAula);
        valores.put(CriaBanco.PONTOS, pontos);
        valores.put(CriaBanco.EMAIL, email);

        resultado = db.insert(CriaBanco.TABELA, null, valores);
        db.close();

        if (resultado ==-1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";
    }

    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos =  {banco.ID,banco.STATUSAULA};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor carregaDadoById(int id){
        Cursor cursor;
        String[] campos =  {banco.ID,banco.STATUSAULA,banco.PONTOS,banco.EMAIL};
        String where = CriaBanco.ID + "=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query(CriaBanco.TABELA,campos,where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }


    public Cursor carregaDadoByEmail(String email){
        Cursor cursor;
        String[] campos =  {banco.ID,banco.STATUSAULA,banco.PONTOS,banco.EMAIL};
        String where = CriaBanco.EMAIL + "=" + email;
        db = banco.getReadableDatabase();
        cursor = db.query(CriaBanco.TABELA,campos,where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public void alteraRegistro(int id,int statusAula, int pontos,String email){
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = CriaBanco.ID + "=" + id;

        valores = new ContentValues();
        valores.put(CriaBanco.STATUSAULA, statusAula);
        valores.put(CriaBanco.PONTOS, pontos);
        valores.put(CriaBanco.EMAIL, email);


        db.update(CriaBanco.TABELA,valores,where,null);
        db.close();
    }
}
