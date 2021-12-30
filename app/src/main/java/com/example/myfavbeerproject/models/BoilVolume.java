package com.example.myfavbeerproject.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class BoilVolume {

    @SerializedName("value")
    @Expose
    private int value;
    @SerializedName("unit")
    @Expose
    private String unit;

    public BoilVolume(int value, String unit) {
        super();
        this.value = value;
        this.unit = unit;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

}
