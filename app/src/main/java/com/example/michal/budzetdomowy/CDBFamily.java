package com.example.michal.budzetdomowy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


public class CDBFamily {
    private CDBHelperFamily  dbHelper;
    private String[]  MATERIAL_RECORD = {CDBHelper.TYP_ID, CDBHelper.TYP_TYP, CDBHelper.TYP_NORMA};

    private SQLiteDatabase  dbMaterial2;

    public CDBFamily(Context context){
        dbHelper = new CDBHelperFamily(context);
    } //tworzymy obiekt

    public void open() throws SQLException {                                    // nawiazywanie polaczenia z baza
        dbMaterial2 = dbHelper.getWritableDatabase();
    }

    public void close(){ dbMaterial2.close(); }                                     //zamykanie polaczenia z baza

    public void addMaterial(String typ, String norma){
        ContentValues cv = new ContentValues();
        cv.put(CDBHelper.TYP_TYP, typ);
        cv.put(CDBHelper.TYP_NORMA, norma);
        dbMaterial2.insert(CDBHelper.TABLE_TYP, null, cv);
    }

    public Cursor getMaterials(){
        return dbMaterial2.query(CDBHelper.TABLE_TYP,
                MATERIAL_RECORD, null, null, null, null,
                CDBHelper.TYP_TYP);
    }

    public void deleteMaterial(int id){
        System.out.println("usunieto rekord");
        dbMaterial2.delete(CDBHelper.TABLE_TYP,
                CDBHelper.TYP_ID + " = " + id, null);
    }

}