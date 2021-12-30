package com.example.myfavbeerproject;

import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myfavbeerproject.adapters.ItemAdapter;
import com.example.myfavbeerproject.bbdd.BeerDB;
import com.example.myfavbeerproject.bbdd.BeerRepository;
import com.example.myfavbeerproject.models.Beer;
import com.example.myfavbeerproject.models.Hop;
import com.example.myfavbeerproject.models.Ingredients;
import com.example.myfavbeerproject.models.Malt;
import com.example.myfavbeerproject.webservice.WebServiceClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BeerDetail extends AppCompatActivity {

    private Beer beer;
    private List<Beer> listbeerfav;
    private BeerDB beerfav;
    private FloatingActionButton floating_fav;
    private WebServiceClient webServiceClient;
    private BeerRepository beerRepository;


    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_beer);

        // logout
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Bundle bundle = getIntent().getExtras();
        if(bundle !=null && bundle.containsKey("data")){
            beer = bundle.getParcelable("data");
            setupView();
            llamarFragments();
        } else if (bundle !=null && bundle.containsKey("data2")){
            beerfav = bundle.getParcelable("data2");
            initRetrofit();

        }

    }

    private void setupView(){
        floating_fav = findViewById(R.id.floating_fav);
        beerRepository = new BeerRepository(BeerDetail.this);
        String birranombre = beer.getName();
        int birraid = beer.getId();
        String birradescripcion = beer.getDescription();

        ImageView image = findViewById(R.id.imagenV);
        if(beer.getImageUrl() != null){
            Picasso.get().load(beer.getImageUrl()).into(image);
        }

        String birraimage = beer.getImageUrl();

        floating_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


          BeerDB birradb = new BeerDB(birraid, birranombre, birradescripcion, birraimage);

          beerRepository.insertar(birradb);
                Toast.makeText(BeerDetail.this, "Cerveza añadida a Favoritos", Toast.LENGTH_SHORT).show();

            }
        });
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
       lanzarPeticion();
    }

   private void lanzarPeticion(){

    Call <List<Beer>> request = webServiceClient.selectonebeer(beerfav.getId());
    request.enqueue(new Callback<List<Beer>>() {
        @Override
        public void onResponse(Call<List<Beer>> call, Response<List<Beer>> response) {
            if(response.isSuccessful()){
                listbeerfav=response.body();
                beer = listbeerfav.get(0);
                setupView();
                llamarFragments();

            }
        }

        @Override
        public void onFailure(Call<List<Beer>> call, Throwable t) {

        }
    });

   }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void llamarFragments(){
            ItemAdapter adapter = new ItemAdapter(getSupportFragmentManager(),getLifecycle(), beer);
            ViewPager2 viewP = findViewById(R.id.viewPage);
            viewP.setAdapter(adapter);

            TabLayout tabLayout = findViewById(R.id.tabLayoutF1);
            TabLayoutMediator mediator = new TabLayoutMediator(tabLayout, viewP, new TabLayoutMediator.TabConfigurationStrategy() {
                @Override
                public void onConfigureTab(@NonNull @NotNull TabLayout.Tab tab, int position) {
                    if (position == 0) {
                        tab.setText("Description");
                    } else if (position == 1) {
                        tab.setText("Ingredients");
                    } else if (position == 2) {
                        tab.setText("F.Pairing");
                    }
                }
            });
            mediator.attach();
        }

    }

