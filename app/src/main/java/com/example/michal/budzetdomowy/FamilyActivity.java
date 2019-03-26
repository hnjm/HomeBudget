package com.example.michal.budzetdomowy;

import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class FamilyActivity extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<Cursor> {

    SimpleCursorAdapter scAdapter;
    CDBFamily dbMaterial2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);
        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);

        dbMaterial2 = new CDBFamily(this);
        dbMaterial2.open();
        scAdapter=new SimpleCursorAdapter(
                this, R.layout.list_item2, null,
                new String[]{CDBHelper.TYP_ID,
                        CDBHelper.TYP_TYP, CDBHelper.TYP_NORMA},
                new int[]{R.id.tvID, R.id.tvNazwa, R.id.tvNorma}, 0);
        ((ListView)findViewById(R.id.lista2)).setAdapter(
                scAdapter);
        getLoaderManager().initLoader(1, null,this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CMaterialLoaderFamily(this, dbMaterial2);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        scAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        scAdapter.swapCursor(null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                try {
                    dbMaterial2.addMaterial(
                            ((EditText) findViewById(
                                    R.id.editText2)).getText().toString(),
                            ((EditText) findViewById(
                                    R.id.editText3)).getText().toString());
                    getLoaderManager().getLoader(1).forceLoad();
                }
                catch(NumberFormatException ex){
                    System.out.println("zla wartosc");
                }
                break;
            case R.id.button2:
                try {
                    String fieldValue = ((EditText) findViewById(R.id.editText1)).getText().toString();
                    dbMaterial2.deleteMaterial(Integer.parseInt(fieldValue));
                    getLoaderManager().getLoader(1).forceLoad();
                }
                catch(NumberFormatException ex){
                    System.out.println("zla wartosc");
                }
                break;
        }
    }
}
