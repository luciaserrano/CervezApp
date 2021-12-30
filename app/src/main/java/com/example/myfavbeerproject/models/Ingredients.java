package com.example.myfavbeerproject.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Ingredients implements Parcelable {

    @SerializedName("malt")
    @Expose
    private List<Malt> malt = null;
    @SerializedName("hops")
    @Expose
    private List<Hop> hops = null;
    @SerializedName("yeast")
    @Expose
    private String yeast;


    public Ingredients(List<Malt> malt, List<Hop> hops, String yeast) {
        super();
        this.malt = malt;
        this.hops = hops;
        this.yeast = yeast;
    }

    protected Ingredients(Parcel in) {
        malt = in.createTypedArrayList(Malt.CREATOR);
        hops = in.createTypedArrayList(Hop.CREATOR);
        yeast = in.readString();
    }

    public static final Creator<Ingredients> CREATOR = new Creator<Ingredients>() {
        @Override
        public Ingredients createFromParcel(Parcel in) {
            return new Ingredients(in);
        }

        @Override
        public Ingredients[] newArray(int size) {
            return new Ingredients[size];
        }
    };

    public List<Malt> getMalt() {
        return malt;
    }

    public void setMalt(List<Malt> malt) {
        this.malt = malt;
    }

    public List<Hop> getHops() {
        return hops;
    }

    public void setHops(List<Hop> hops) {
        this.hops = hops;
    }

    public String getYeast() {
        return yeast;
    }

    public void setYeast(String yeast) {
        this.yeast = yeast;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(malt);
        dest.writeTypedList(hops);
        dest.writeString(yeast);
    }
}
