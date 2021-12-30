package com.example.myfavbeerproject.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Hop implements Parcelable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("AmountMalt")
    @Expose
    private AmountHop AmountMalt;
    @SerializedName("add")
    @Expose
    private String add;
    @SerializedName("attribute")
    @Expose
    private String attribute;

    public Hop(String name, AmountHop AmountMalt, String add, String attribute) {
        super();
        this.name = name;
        this.AmountMalt = AmountMalt;
        this.add = add;
        this.attribute = attribute;
    }

    protected Hop(Parcel in) {
        name = in.readString();
        add = in.readString();
        attribute = in.readString();
    }

    public static final Creator<Hop> CREATOR = new Creator<Hop>() {
        @Override
        public Hop createFromParcel(Parcel in) {
            return new Hop(in);
        }

        @Override
        public Hop[] newArray(int size) {
            return new Hop[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AmountHop getAmountMalt() {
        return AmountMalt;
    }

    public void setAmountMalt(AmountHop AmountMalt) {
        this.AmountMalt = AmountMalt;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(add);
        dest.writeString(attribute);
    }
}

