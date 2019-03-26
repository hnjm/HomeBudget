package com.example.michal.budzetdomowy;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements android.view.View.OnClickListener{


    GridLayout mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainGrid = (GridLayout)findViewById(R.id.mainGrid);

        //setEvent
        //setSingleEvent(mainGrid);
        setToggleEvent(mainGrid);
    }


    private void setToggleEvent(GridLayout mainGrid) {
        for(int i=0;i<mainGrid.getChildCount();i++)
        {
            //all child items are cardview so we just cast object to CardView
            final CardView cardView = (CardView)mainGrid.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(cardView.getCardBackgroundColor().getDefaultColor() == -1)
                    {
                        //change backgroundcolor
                        cardView.setCardBackgroundColor(Color.parseColor("#FF6F00"));
                        Toast.makeText(MainActivity.this,"State : True", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        //change backgroundcolor
                        cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                        Toast.makeText(MainActivity.this,"State : False", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setSingleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid

        for(int i=0;i<mainGrid.getChildCount();i++)
        {
            //all child items are cardview so we just cast object to CardView
            CardView cardView = (CardView)mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //i can repleace toast with start new activity code
                    Toast.makeText(MainActivity.this, "Clicked at index"+ finalI, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.oszczednosciButton:
                intent = new Intent(MainActivity.this, OszczednosciActivity.class);
                startActivity(intent);
                break;
            case R.id.familyButton:
                intent = new Intent(MainActivity.this, FamilyActivity.class);
                startActivity(intent);
                break;
            case R.id.addButton:
                intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
                break;
            case R.id.przychodyButton:
                intent = new Intent(MainActivity.this, PrzychodyActivity.class);
                startActivity(intent);
                break;
            case R.id.offButton:
                finish();
                /*intent = new Intent(MainActivity.this, OffActivity.class);
                startActivity(intent);*/
                break;
            case R.id.saldoButton:
                intent = new Intent(MainActivity.this, SaldoActivity.class);
                startActivity(intent);
                break;
        }
    }
}
