package com.example.myfavbeerproject.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myfavbeerproject.BeerDetail;
import com.example.myfavbeerproject.R;
import com.example.myfavbeerproject.models.Beer;
import com.squareup.picasso.Picasso;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;

public class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.BeerHolder>{

    private List<Beer> beerList;
    private final Context context;

    public BeerAdapter(Context context) {

        this.beerList = new ArrayList<>();
        this.context = context;
    }

    @NotNull
    @Override
    public BeerAdapter.BeerHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_beer, parent, false);
        return new BeerAdapter.BeerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull BeerAdapter.BeerHolder holder, int position) {
        Beer beer=beerList.get(position);
        holder.tvBeer.setText(beer.getName());

        if (beer.getImageUrl() != null) {
            Picasso.get().load(beer.getImageUrl()).into(holder.imgBeer);
        }
        else if (beer.getImageUrl() == " ")
        {
            Drawable myDrawable = context.getResources().getDrawable(R.drawable.noimage);
            holder.imgBeer.setImageDrawable(myDrawable);
        }
        holder.itembeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, BeerDetail.class);
                i.putExtra("data", beer);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return beerList.size();
    }

   static class BeerHolder extends RecyclerView.ViewHolder
   {
        private final ImageView imgBeer;
        private final TextView  tvBeer;
        private ConstraintLayout itembeer;

       public BeerHolder(@NonNull @NotNull View itemView) {
           super(itemView);
           imgBeer= itemView.findViewById(R.id.img_beer);
           tvBeer=itemView.findViewById(R.id.tv_beer);
           itembeer=itemView.findViewById(R.id.ctrlBeer);
       }
   }
    public void setListBeer(List<Beer> listBeer) {
        this.beerList = listBeer;
        notifyDataSetChanged();
    }
}