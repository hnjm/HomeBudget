package com.example.michal.budzetdomowy;

import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class OszczednosciActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, View.OnClickListener {
    CDBMaterial dbMaterial;
    SimpleCursorAdapter scAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oszczednosci);
        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);

        dbMaterial = new CDBMaterial(this);
        dbMaterial.open();
        scAdapter=new SimpleCursorAdapter(
                this, R.layout.list_item2, null,
                new String[]{CDBHelper.TYP_ID,
                        CDBHelper.TYP_TYP, CDBHelper.TYP_NORMA},
                new int[]{R.id.tvID, R.id.tvNazwa, R.id.tvNorma}, 0);
        ((ListView)findViewById(R.id.lista1)).setAdapter(
                scAdapter);
        getLoaderManager().initLoader(1, null, this);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CMaterialLoader(this, dbMaterial);
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
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button:
                try {
                    dbMaterial.addMaterial(
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
                    dbMaterial.deleteMaterial(Integer.parseInt(fieldValue));
                    getLoaderManager().getLoader(1).forceLoad(); //odczyt danych
                }
                catch(NumberFormatException ex){
                    System.out.println("zla wartosc");
                }
                break;
        }
    }
}
//((TextView)findViewById(
//                                R.id.tvID)).getId(
//(EditText)findViewById(R.id.editText2).getId()
// 2131427468 button2
// 2131427458 button
// 2131427458 view.getId();
