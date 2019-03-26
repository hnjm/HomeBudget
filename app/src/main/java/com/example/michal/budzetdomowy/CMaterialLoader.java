package com.example.michal.budzetdomowy;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;


public class CMaterialLoader  extends CursorLoader {
    CDBMaterial dbase;

    public CMaterialLoader(Context context, CDBMaterial b) {
        super(context);
        dbase = b;
    }

    @Override
    public Cursor loadInBackground() {
        return dbase.getMaterials();
    }
}
