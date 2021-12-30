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

public class Pairing extends Fragment {
    public Beer beer;
    public TextView foodpairingf1;

    public Pairing (Beer beer) {
        this.beer = beer;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.pairing_fragment, container, true);
        foodpairingf1 = v.findViewById(R.id.foodpairingf1);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        foodpairingf1.setText(String.valueOf(beer.getFoodPairing()));
    }
}