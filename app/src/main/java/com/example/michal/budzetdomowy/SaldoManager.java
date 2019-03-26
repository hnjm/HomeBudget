package com.example.michal.budzetdomowy;

import android.content.Context;


public class SaldoManager {

    CDBMaterialPrzychody dbMaterial3;
    CDBMaterialAdd dbMaterial4;
    int saldo;

    public SaldoManager(Context context) {
        this.dbMaterial3 = new CDBMaterialPrzychody(context);
        this.dbMaterial4 = new CDBMaterialAdd(context);
    }

    public void aktualizacja_salda(){
        for(int i=0; i<=1000; i++){

            String idToSum = dbMaterial3.getSingular_Value_InTransaction("SELECT typ FROM TYP WHERE _id = '"+i+"'");
            if(idToSum == ""){
                saldo+=0;
            }
            else if(idToSum == "wartosc"){
                saldo+=0;
            }
            else {
                saldo += Integer.parseInt(idToSum);
            }

            String idToDel = dbMaterial4.getSingular_Value_InTransaction("SELECT typ FROM TYP WHERE _id = '"+i+"'");
            if(idToDel == ""){
                saldo+=0;
            }
            else if(idToDel == "wartosc"){
                saldo+=0;
            }
            else {
                saldo -= Integer.parseInt(idToDel);
            }
        }
        System.out.println("saldo wynosi: " + saldo);

    }

    public void aktualizacja_salda2(){
        for(int i=0; i<=1000; i++){

            String idToSum = dbMaterial3.getSingular_Value_InTransaction("SELECT typ FROM TYP WHERE _id = '"+i+"'");
            if(idToSum == ""){
                saldo+=0;
            }
            else if(idToSum == "wartosc"){
                saldo+=0;
            }
            else {
                saldo += Integer.parseInt(idToSum);
            }

            String idToDel = dbMaterial4.getSingular_Value_InTransaction("SELECT typ FROM TYP WHERE _id = '"+i+"'");
            if(idToDel == ""){
                saldo+=0;
            }
            else if(idToDel == "wartosc"){
                saldo+=0;
            }
            else {
                saldo -= Integer.parseInt(idToDel);
            }


        }
        System.out.println("saldo wynosi: " + saldo);

    }

}
