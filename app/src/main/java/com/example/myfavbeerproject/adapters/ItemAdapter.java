package com.example.myfavbeerproject.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.example.myfavbeerproject.fragment.Description;
import com.example.myfavbeerproject.fragment.Ingredients;
import com.example.myfavbeerproject.fragment.Pairing;
import com.example.myfavbeerproject.models.Beer;
import org.jetbrains.annotations.NotNull;

public class ItemAdapter extends FragmentStateAdapter {
    private Beer beer;

    public ItemAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, Beer beer) {
        super(fragmentManager, lifecycle);
        this.beer = beer;
    }

    @NotNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 0) {
            return new Description(beer);
        } else if (position == 1) {
            return new Ingredients(beer);
        } else if (position == 2) {
            return new Pairing(beer);
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
