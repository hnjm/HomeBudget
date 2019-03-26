package com.example.michal.budzetdomowy;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;


public class CMaterialLoaderFamily  extends CursorLoader {
    CDBFamily dbase;

    public CMaterialLoaderFamily(Context context, CDBFamily b) {
        super(context);
        dbase = b;
    }

    @Override
    public Cursor loadInBackground() {
        return dbase.getMaterials();
    }
}