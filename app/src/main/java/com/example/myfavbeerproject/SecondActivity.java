package com.example.myfavbeerproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.example.myfavbeerproject.adapters.BeerAdapter;
import com.example.myfavbeerproject.models.Beer;
import com.example.myfavbeerproject.webservice.WebServiceClient;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SecondActivity extends AppCompatActivity {
    private RecyclerView recyclerBeer;
    private BeerAdapter adapter;
    private WebServiceClient webServiceClient;
    private StaggeredGridLayoutManager layoutManager;
    private int cont=1;
    private ImageView leftB,rightB;
    private ProgressBar progressBar;
    private TextView pag;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        // logout
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        leftB = findViewById(R.id.leftB);
        rightB = findViewById(R.id.rightB);
        pag = findViewById(R.id.pag);
        initRetrofit();
        setupView();
        lanzarPeticion();

        leftB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rightB.setVisibility(View.VISIBLE);
                if(cont>=2){
                    cont--;
                    pag.setText(" " + cont);
                    NextPage(cont);
                }
                if(cont==1){
                    leftB.setVisibility(View.GONE);
                }
            }
        });

        rightB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leftB.setVisibility(View.VISIBLE);
                if(cont<=13){

                    cont++;
                    pag.setText(" " + cont);
                    NextPage(cont);
                }
                if(cont==13){
                    rightB.setVisibility(View.GONE);
                }
            }
        });
    }

    private void setupView(){
        recyclerBeer = findViewById(R.id.recyclerBeer);
        progressBar = findViewById(R.id.progressBar);
        adapter = new BeerAdapter(this);
        layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerBeer.setLayoutManager(layoutManager);
        recyclerBeer.setAdapter(adapter);
    }

    private void showProgressBar(boolean activado) {
        progressBar.setVisibility(activado ? View.VISIBLE : View.INVISIBLE);
    }

    private void initRetrofit() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WebServiceClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClientBuilder.build())
                .build();
        webServiceClient = retrofit.create(WebServiceClient.class);
    }

    private void lanzarPeticion()
    {
        showProgressBar(true);
        Call<List<Beer>> peticion= webServiceClient.getBeers();
        peticion.enqueue(new Callback<List<Beer>>() {
            @Override
            public void onResponse(Call<List<Beer>> call, Response<List<Beer>> response) {
                showProgressBar(false);
                adapter.setListBeer(response.body());

            }

            @Override
            public void onFailure(Call<List<Beer>> call, Throwable t) {
                Log.d("RETROFIT", "Error: " + t.getMessage());
            }
        });
    }

    @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.main_menu, menu);
            return true;
        }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuBuscar) {
            startActivity(new Intent(this, SearchActivity.class));
            return true;
        }
        if (item.getItemId() == R.id.menuFavoritos) {
            startActivity(new Intent(this, FavActivity.class));
            return true;
        }
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void NextPage(int cont) { //
        Call<List<Beer>> siguiente = webServiceClient.getNextPage(cont);
        siguiente.enqueue(new Callback<List<Beer>>() {
            @Override
            public void onResponse(Call<List<Beer>> call, Response<List<Beer>> response) {
                if (response.isSuccessful()) {
                    int code = response.code();
                    List<Beer> myBeer = response.body();
                    adapter.setListBeer(myBeer);
                } else {
                    showErrorMessage();
                }
            }

            @Override
            public void onFailure(Call<List<Beer>> call, Throwable t) {
                Log.d("RETROFIT", "Error: " + t.getMessage());
            }
        });
    }

    private void showErrorMessage() {
        Toast.makeText(this, "An unknown error has occurred. Please try again later.", Toast.LENGTH_SHORT).show();
    }
}