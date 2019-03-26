package com.example.michal.budzetdomowy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.security.InvalidParameterException;
import java.util.ArrayList;


public class CDBMaterialPrzychody {
    private CDBHelperPrzychody dbHelper;
    private String[]  MATERIAL_RECORD = {CDBHelper.TYP_ID, CDBHelper.TYP_TYP, CDBHelper.TYP_NORMA};

    private SQLiteDatabase  dbMaterial;

    public CDBMaterialPrzychody(Context context){
        dbHelper = new CDBHelperPrzychody (context);
    } //tworzymy obiekt

    public void open() throws SQLException {                                    // nawiazywanie polaczenia z baza
        dbMaterial = dbHelper.getWritableDatabase();
    }

    public void close(){ dbMaterial.close(); }                                     //zamykanie polaczenia z baza

    public void addMaterial(String typ, String norma){
        try {
            ContentValues cv = new ContentValues();
            cv.put(CDBHelper.TYP_TYP, typ);
            cv.put(CDBHelper.TYP_NORMA, norma);
            dbMaterial.insert(CDBHelper.TABLE_TYP, null, cv);
        }
        catch(NumberFormatException ex){
            System.out.println("nie prawidłowa wartość");
        }
    }

    public Cursor getMaterials(){
        return dbMaterial.query(CDBHelper.TABLE_TYP,
                MATERIAL_RECORD, null, null, null, null,
                CDBHelper.TYP_TYP);
    }

    public void deleteMaterial(int id){
        try {
            dbMaterial.delete(CDBHelper.TABLE_TYP,
                    CDBHelper.TYP_ID + " = " + id, null);
        }
        catch(NumberFormatException ex){

        }
    }

    public String getValue(int id){
        Cursor c = dbMaterial.rawQuery("SELECT typ FROM TYP WHERE _id = ?", new String[] {"5"});
        //Cursor c = dbMaterial.rawQuery("SELECT * FROM TYP WHERE _id = "+id+"", null);
        //Cursor c = dbMaterial.execSQL("SELECT * FROM TYP WHERE _id = "+id+"");
        return c.getString(c.getColumnIndex("typ")); //nie wiem czy to odpowiednia kolumna
        //return c.getString(this);
    }


    public String getSingular_Value_InTransaction(String query){
        //Declaration of variables
        Cursor a1 = null;

        try{
            a1 = dbMaterial.rawQuery(query,null);
            a1.moveToFirst();

            if(a1.getString(0) != null){
                String result = a1.getString(0);
                a1.close();
                return result;
            }
            else{
                a1.close();
                return "";
            }
        }
        catch (NullPointerException ex){
            a1.close();
            return "";
        }
        catch (CursorIndexOutOfBoundsException ex){
            a1.close();
            return "";
        }
    }

}
//rawQuery("SELECT id, name FROM people WHERE name = ? AND id = ?", new String[] {"David", "2"});
//rawQuery("SELECT typ FROM TYP WHERE id = ?", new String[] {"2"});
