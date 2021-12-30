package com.example.myfavbeerproject;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myfavbeerproject.models.Beer;
import com.example.myfavbeerproject.webservice.WebServiceClient;
import com.squareup.picasso.Picasso;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RandomActivity extends AppCompatActivity {
    private Beer beer;
    private TextView name_beer;
    private ImageView image_beer,like,dislike;
    private WebServiceClient webServiceClient;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.random_layout);

        // logout
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name_beer = findViewById(R.id.name_beer);
        image_beer = findViewById(R.id.image_beer);
        like = findViewById(R.id.like);
        dislike = findViewById(R.id.dislike);
        initRetrofit();
        lanzarPerticion();
    like.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(RandomActivity.this, "Dislike :(", Toast.LENGTH_SHORT).show();
            initRetrofit();
            lanzarPerticion();
        }
    });

    dislike.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(RandomActivity.this, "Like :)", Toast.LENGTH_SHORT).show();
            initRetrofit();
            lanzarPerticion();
        }
    });
    }

    private void initRetrofit() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY); // Para utlizar la librería
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder().addInterceptor(loggingInterceptor); // Para ver todos los logs

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WebServiceClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClientBuilder.build()) //mensaje de lost
                .build();
        webServiceClient = retrofit.create(WebServiceClient.class); // Aqui están todos los métodos
    }

    private void lanzarPerticion() {
        Call<List<Beer>> peticion = webServiceClient.getBeersRandom();
        peticion.enqueue(new Callback<List<Beer>>() {
            @Override
            public void onResponse(Call<List<Beer>> call, Response<List<Beer>> response) {

                if (response.isSuccessful())
                {
                    int code = response.code();
                    List<Beer> beerR = response.body();
                    for (int i = 0; i<beerR.size(); i++)
                    {
                        beerR.get(i);
                        beer = beerR.get(i);
                        name_beer.setText(beer.getName());

                        if(beer.getImageUrl() != null)
                        {
                            Picasso.get().load(beer.getImageUrl()).into(image_beer);
                        }
                    }
                } else {
                    showErrorMessage();
                }
            }

            @Override
            public void onFailure(Call<List<Beer>> call, Throwable t)
            {
                Log.d("RETROFIT", "Error: " + t.getMessage());
            }
        });
    }
    private void showErrorMessage()
    {
        Toast.makeText(this, "An unknown error has occurred. Please try again later.", Toast.LENGTH_SHORT).show();
    }

    // logout
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}