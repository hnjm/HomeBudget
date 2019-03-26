package com.example.michal.budzetdomowy;

        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;


public class CDBHelper extends  android.database.sqlite.SQLiteOpenHelper {

    public static String TABLE_TYP = "TYP";
    public static String TYP_ID = "_id";
    public static String TYP_TYP = "typ";
    public static String TYP_NORMA =  "norma";

    private static final String DB_NAZWA =  "Material.db";
    private static final String CREATE_TABLE_TYP =  "CREATE TABLE "+TABLE_TYP+"("+
            TYP_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            TYP_TYP+" TEXT,"+TYP_NORMA+" TEXT"+")";

    private static int DB_WERSJA = 1;

    public CDBHelper(Context ctx){
        super(ctx,DB_NAZWA,null,DB_WERSJA);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_TYP);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TYP);
        onCreate(sqLiteDatabase);
    }
}
