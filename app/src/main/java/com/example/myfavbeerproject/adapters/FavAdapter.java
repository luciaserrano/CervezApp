package com.example.myfavbeerproject.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myfavbeerproject.BeerDetail;
import com.example.myfavbeerproject.R;
import com.example.myfavbeerproject.bbdd.BeerDB;
import com.example.myfavbeerproject.bbdd.BeerRepository;
import com.squareup.picasso.Picasso;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.FavHolder> implements Filterable {

    private List<BeerDB> favList;
    private final Context context;
    private BeerRepository beerRepository;

    public FavAdapter(Context context){
        this.favList = new ArrayList<>();
        this.context = context;
        beerRepository = new BeerRepository(context);
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    @NonNull
    @NotNull
    @Override
    public FavAdapter.FavHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fav, parent, false);
        return new FavAdapter.FavHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull FavHolder holder, int position) {
        BeerDB beerDB = favList.get(position);
        holder.favtvbeer.setText(beerDB.getName());

        if (beerDB.getImageUrl() != " ") {
            Picasso.get().load(beerDB.getImageUrl()).into(holder.imgfavbeer);
        }

        holder.deletefav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beerRepository.deletebeerDB(beerDB);
            }
        });

        holder.itemlist.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(context, BeerDetail.class);
               intent.putExtra("data2", beerDB);
               context.startActivity(intent);
           }
        });
    }

    @Override
    public int getItemCount() {
        return favList.size();
    }

    static class FavHolder extends RecyclerView.ViewHolder{
        private final ImageView imgfavbeer;
        private final TextView favtvbeer;
        private ConstraintLayout itemlist;
        private ImageButton deletefav;

        public FavHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            favtvbeer = itemView.findViewById(R.id.fav_tvbeer);
            itemlist = itemView.findViewById(R.id.fav_ctrlBeer);
            imgfavbeer = itemView.findViewById(R.id.fav_imgBeer);
            deletefav = itemView.findViewById(R.id.delete_fav);
        }
    }

    public void setListBeer(List<BeerDB> listBeer){
        this.favList = listBeer;
        notifyDataSetChanged();
    }
}