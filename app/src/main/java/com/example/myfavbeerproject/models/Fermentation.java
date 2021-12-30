package com.example.myfavbeerproject.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Fermentation {

    @SerializedName("temp")
    @Expose
    private TempFermentation temp;


    public Fermentation(TempFermentation temp) {
        super();
        this.temp = temp;
    }

    public TempFermentation getTemp() {
        return temp;
    }

    public void setTemp(TempFermentation temp) {
        this.temp = temp;
    }

}
