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

public class PrzychodyActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, View.OnClickListener {

    //CDBMaterialPrzychody dbMaterial3;
    SimpleCursorAdapter scAdapter;
    SaldoManager saldoManager;

    public PrzychodyActivity() {
        this.saldoManager = new SaldoManager(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_przychody);
        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);

        //dbMaterial3 = new CDBMaterialPrzychody(this);
        saldoManager.dbMaterial3.open();
        saldoManager.dbMaterial4.open();

        scAdapter=new SimpleCursorAdapter(
                this, R.layout.list_item2, null,
                new String[]{CDBHelper.TYP_ID,
                        CDBHelper.TYP_TYP, CDBHelper.TYP_NORMA},
                new int[]{R.id.tvID, R.id.tvNazwa, R.id.tvNorma}, 0);
        ((ListView)findViewById(R.id.lista3)).setAdapter(
                scAdapter);
        getLoaderManager().initLoader(1, null, this);

        System.out.println("saldo: " + saldoManager.saldo);
        try {
            saldoManager.aktualizacja_salda();
        }
        catch(NumberFormatException ex){
            System.out.println("something went wrong");
        }
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CDBMaterialLoaderPrzychody(this, saldoManager.dbMaterial3);
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
                    String fieldValue2 = ((EditText) findViewById(R.id.editText2)).getText().toString();
                    saldoManager.saldo += Integer.parseInt(fieldValue2);
                    saldoManager.dbMaterial3.addMaterial(
                            ((EditText)findViewById(
                                    R.id.editText2)).getText().toString(),
                            ((EditText)findViewById(
                                    R.id.editText3)).getText().toString());
                    getLoaderManager().getLoader(1).forceLoad();
                }
                catch(NumberFormatException ex){
                    System.out.println("zla wartosc");
                }
                System.out.println(saldoManager.saldo);
                break;
            case R.id.button2:
                try {
                    String fieldValue3 = ((EditText) findViewById(R.id.editText1)).getText().toString();
                    String deletingValue = saldoManager.dbMaterial3.getSingular_Value_InTransaction("SELECT typ FROM TYP WHERE _id = '" + fieldValue3 + "'");

                    saldoManager.saldo -= Integer.parseInt(deletingValue);

                    System.out.println(saldoManager.dbMaterial3.getSingular_Value_InTransaction("SELECT typ FROM TYP WHERE _id = '"+fieldValue3+"'"));
                    //System.out.println(dbMaterial3.getValue(Integer.parseInt(fieldValue)));
                    System.out.println(saldoManager.saldo);

                    saldoManager.dbMaterial3.deleteMaterial(Integer.parseInt(fieldValue3));
                }
                catch(NumberFormatException ex){
                    System.out.println("zla wartosc");
                }

                getLoaderManager().getLoader(1).forceLoad(); //odczyt danych
                break;
        }
    }
}
