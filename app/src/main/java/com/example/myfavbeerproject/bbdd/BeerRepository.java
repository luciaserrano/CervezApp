package com.example.myfavbeerproject.bbdd;

import android.content.Context;
import androidx.lifecycle.LiveData;
import java.util.List;

public class BeerRepository {

    private final DaoBeer daoBeer;
    private final AppDataBase dataBase;

    public BeerRepository(Context context) {
        dataBase = AppDataBase.getDataBase(context);
        daoBeer = dataBase.daoBeer();
    }

    public void insertar(BeerDB beerDB){
        new Thread(new Runnable() {
            @Override
            public void run() {
                daoBeer.insertBeer(beerDB);
            }
        }).start();
    }

    public void delete(BeerDB beerDB){
        new Thread(new Runnable() {
            @Override
            public void run() {
                daoBeer.deleteBeer(beerDB);
            }
        }).start();
    }

    public LiveData<List<BeerDB>> listarTodas(){
        return daoBeer.countList();
    }

    public void deletebeerDB(BeerDB beerDB){
        new Thread(new Runnable() {
            @Override
            public void run() {
                daoBeer.deleteBeerDB(beerDB.getId());
            }
        }) .start();
    }
}