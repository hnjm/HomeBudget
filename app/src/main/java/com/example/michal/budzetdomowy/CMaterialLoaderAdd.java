package com.example.michal.budzetdomowy;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;


public class CMaterialLoaderAdd  extends CursorLoader {
    CDBMaterialAdd dbase;

    public CMaterialLoaderAdd(Context context, CDBMaterialAdd b) {
        super(context);
        dbase = b;
    }

    @Override
    public Cursor loadInBackground() {
        return dbase.getMaterials();
    }
}
