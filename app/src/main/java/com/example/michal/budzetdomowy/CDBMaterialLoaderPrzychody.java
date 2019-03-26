package com.example.michal.budzetdomowy;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;


public class CDBMaterialLoaderPrzychody  extends CursorLoader {
    CDBMaterialPrzychody dbase;

    public CDBMaterialLoaderPrzychody(Context context, CDBMaterialPrzychody b) {
        super(context);
        dbase = b;
    }

    @Override
    public Cursor loadInBackground() {
        return dbase.getMaterials();
    }
}
