package com.example.myfavbeerproject.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AmountHop {

    @SerializedName("value")
    @Expose
    private double value;
    @SerializedName("unit")
    @Expose
    private String unit;


    public AmountHop(double value, String unit) {
        super();
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

}
