package com.example.myfavbeerproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.example.myfavbeerproject.adapters.SearchAdapter;
import com.example.myfavbeerproject.bbdd.BeerDB;
import com.example.myfavbeerproject.bbdd.BeerRepository;
import com.example.myfavbeerproject.models.Beer;
import com.example.myfavbeerproject.webservice.WebServiceClient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private SearchAdapter adapter;
    private List<Beer> listBeer;
    private CheckBox abv;
    private CheckBox ibu;
    private HashMap<String, String> map;
    private TextView consulta;
    private Button btSearch;
    private String consul;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);

        // logout
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        setView();
    }

    private void setView()
    {
        listBeer = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        adapter = new SearchAdapter(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        abv = findViewById(R.id.cbAbv);
        ibu = findViewById(R.id.cbIbu);
        consulta = findViewById(R.id.tietSearch);
        btSearch = findViewById(R.id.btSearch);

        btSearch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                map = new HashMap<>();
                consul=consulta.getText().toString();

                map.put("beer_name",consul);

                if(consul.isEmpty())
                {
                    Toast.makeText(SearchActivity.this, "You must write something", Toast.LENGTH_SHORT).show();
                }
                else
                    {
                    if(ibu.isChecked())
                    {
                        map.remove("beer_name");
                        map.put("ibu_gt",consul);
                    }
                    if(abv.isChecked())
                    {
                        map.remove("beer_name");
                        map.put("abv_gt",consul);
                    }
                    sendRequest();
                }
            }
        });
    }

    private void sendRequest()
    {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WebServiceClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClientBuilder.build())
                .build();
        WebServiceClient myClient = retrofit.create(WebServiceClient.class);

        Call<List<Beer>> call = myClient.searchGetBeers(map);
        call.enqueue(new Callback<List<Beer>>()
        {
            @Override
            public void onResponse(Call<List<Beer>> call, Response<List<Beer>> response)
            {
                if (response.isSuccessful())
                {
                    List<Beer> beer = response.body();
                    adapter.setListSearch(beer);
                } else {
                    Toast.makeText(SearchActivity.this, "If you use a filter you must enter a numerical value", Toast.LENGTH_SHORT).show();
                    Log.d("Retrofit", response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Beer>> call, Throwable t)
            {
                Log.d("Retrofit", t.getMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem mItem)
    {
        if (mItem.getItemId() == R.id.menuBuscar)
        {
            startActivity(new Intent(this, SearchActivity.class));
            return true;
        }
        if (mItem.getItemId() == R.id.menuFavoritos)
        {
            startActivity(new Intent(this, FavActivity.class));
            return true;
        }
        if (mItem.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(mItem);
    }
}