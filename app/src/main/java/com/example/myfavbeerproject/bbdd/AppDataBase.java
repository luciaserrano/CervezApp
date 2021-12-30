package com.example.myfavbeerproject.bbdd;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = BeerDB.class, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public static final String DB_NAME = "db_birra";
    public abstract DaoBeer daoBeer();
    private static AppDataBase dataBase;

    public static AppDataBase getDataBase(final Context context){
        if(dataBase== null){
            synchronized (AppDataBase.class){
                if (dataBase == null){
                    dataBase = Room.databaseBuilder(context.getApplicationContext(),
                            AppDataBase.class, DB_NAME).build();
                }
            }
        }
        return dataBase;
    }
    static final ExecutorService dbWriterExecutor = Executors.newFixedThreadPool(1);
}