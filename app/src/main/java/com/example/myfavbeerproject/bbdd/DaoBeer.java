package com.example.myfavbeerproject.bbdd;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

@Dao
public interface DaoBeer {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBeer(BeerDB beerDB);

    @Delete
    void deleteBeer(BeerDB beerDB);

    @Query("SELECT * FROM beerdb")
    LiveData<List<BeerDB>> countList();

    @Query("DELETE FROM beerdb WHERE id= :id")
    void deleteBeerDB(int id);
}