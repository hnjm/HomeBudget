package com.example.michal.budzetdomowy;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SaldoActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, View.OnClickListener{

     // jak pokazac w xml, w danym polu wartosc jakiejs zmiennej???

    SaldoManager saldoManager;

    public SaldoActivity() {
        this.saldoManager = new SaldoManager(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saldo);
        saldoManager.dbMaterial3.open();
        saldoManager.dbMaterial4.open();
        saldoManager.aktualizacja_salda();
        TextView textView = findViewById(R.id.textView);
        textView.setText(String.valueOf(saldoManager.saldo));
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onClick(View v) {

    }
}
