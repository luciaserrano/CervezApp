package com.example.myfavbeerproject.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Malt implements Parcelable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("AmountMalt")
    @Expose
    private AmountMalt AmountMalt;


    public Malt(String name, AmountMalt AmountMalt) {
        super();
        this.name = name;
        this.AmountMalt = AmountMalt;
    }

    protected Malt(Parcel in) {
        name = in.readString();
    }

    public static final Creator<Malt> CREATOR = new Creator<Malt>() {
        @Override
        public Malt createFromParcel(Parcel in) {
            return new Malt(in);
        }

        @Override
        public Malt[] newArray(int size) {
            return new Malt[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AmountMalt getAmountMalt() {
        return AmountMalt;
    }

    public void setAmountMalt(AmountMalt AmountMalt) {
        this.AmountMalt = AmountMalt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }
}
