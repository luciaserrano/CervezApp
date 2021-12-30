package com.example.myfavbeerproject.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.myfavbeerproject.R;
import com.example.myfavbeerproject.models.Beer;
import com.example.myfavbeerproject.models.Hop;
import com.example.myfavbeerproject.models.Malt;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class Ingredients extends Fragment {
    public Beer beer;
    public TextView maltf1, hopf1;
    private LinearLayout linearLayout1, linearLayout;

    public Ingredients (Beer beer) {

        this.beer = beer;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.ingredient_fragment, container, true);
        linearLayout = v.findViewById(R.id.maltas);
        linearLayout1 = v.findViewById(R.id.hops);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        List<Malt> maltas = beer.getIngredients().getMalt();
        for (Malt malt : maltas) {
            TextView textView = new TextView(getActivity());
            textView.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            textView.setText(malt.getName());
            linearLayout.addView(textView);
        }

        List<Hop> hops = beer.getIngredients().getHops();
        for (Hop hop : hops) {
            TextView textView = new TextView(getActivity());
            textView.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            textView.setText(hop.getName());
            linearLayout1.addView(textView);
        }
    }
}