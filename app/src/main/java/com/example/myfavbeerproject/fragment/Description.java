package com.example.myfavbeerproject.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.myfavbeerproject.R;
import com.example.myfavbeerproject.models.Beer;
import org.jetbrains.annotations.NotNull;

public class Description extends Fragment {
    public Beer beer;
    private TextView descripcionf1, abvf1, ibuf1;
    public Description (Beer beer) {
        this.beer = beer;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.description_fragment, container, true);
        descripcionf1 = v.findViewById(R.id.descripcionf1);
        abvf1 = v.findViewById(R.id.abvf1);
        ibuf1 = v.findViewById(R.id.ibuf1);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        descripcionf1.setText(beer.getDescription());
        abvf1.setText(String.valueOf(beer.getAbv()));
        ibuf1.setText(String.valueOf(beer.getIbu()));
    }
}