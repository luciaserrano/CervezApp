package com.example.myfavbeerproject.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
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

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchHolder>
{
    private List<Beer> beerList;
    private final Context context;

    public SearchAdapter(Context context)
    {
        this.beerList = new ArrayList<>();
        this.context = context;
    }

    public SearchAdapter.SearchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);
        return new SearchAdapter.SearchHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.SearchHolder holder, int position)
    {
        Beer beer = beerList.get(position);
        holder.tvBeerSearch.setText(beer.getName());
        holder.sIBU.setText(String.valueOf(beer.getIbu()));
        holder.sABV.setText(String.valueOf(beer.getAbv()));

        if (beer.getImageUrl() != " ")
        {
            Picasso.get().load(beer.getImageUrl()).into(holder.imgsearchbeer);
        }

        holder.searchlist.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, BeerDetail.class);
                i.putExtra("data", beer);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() { return beerList.size(); }

    static class SearchHolder extends RecyclerView.ViewHolder
    {
        private final TextView tvBeerSearch;
        private final TextView sIBU;
        private final TextView sABV;
        private final ImageView imgsearchbeer;
        private ConstraintLayout searchlist;

        public SearchHolder(@NonNull @NotNull View itemView)
        {
            super(itemView);
            tvBeerSearch=itemView.findViewById(R.id.tvSearchItem);
            searchlist = itemView.findViewById(R.id.search_ctrlBeer);
            imgsearchbeer = itemView.findViewById(R.id.search_imgbeer);
            sIBU = itemView.findViewById(R.id.sIBU1);
            sABV = itemView.findViewById(R.id.sABV1);
        }
    }

    public void setListSearch(List<Beer> lista)
    {
        this.beerList = lista;
        notifyDataSetChanged();
    }
}