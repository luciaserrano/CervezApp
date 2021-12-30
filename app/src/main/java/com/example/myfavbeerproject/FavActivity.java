package com.example.myfavbeerproject;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myfavbeerproject.adapters.FavAdapter;
import com.example.myfavbeerproject.bbdd.BeerDB;
import com.example.myfavbeerproject.bbdd.BeerRepository;
import com.example.myfavbeerproject.webservice.WebServiceClient;
import java.util.List;

public class FavActivity extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private FavAdapter adapter;
    private List<BeerDB> listBeer;
    private WebServiceClient webServiceClient;
    private LinearLayoutManager layoutManager;
    private ProgressBar progressBar;
    private BeerRepository beerRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fav_list);
        setupview();
        setTitle("Favoritas");

        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setupview()
    {
        recyclerView = findViewById(R.id.recyclerview);
        progressBar = findViewById(R.id.progressBar);
        adapter = new FavAdapter(this);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        beerRepository = new BeerRepository(this);
        LiveData<List<BeerDB>> listacervezasBD = beerRepository.listarTodas();
        showProgressBar(true);

        listacervezasBD.observe(this, new Observer<List<BeerDB>>()
        {
            @Override
            public void onChanged(List<BeerDB> beerDBS)
            {
                adapter.setListBeer(beerDBS);
            }
        });
        showProgressBar(false);
    }

    private void showProgressBar(boolean activado)
    {
        progressBar.setVisibility(activado ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem mItem)
    {
        if (mItem.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(mItem);
    }
}