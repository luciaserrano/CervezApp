package com.example.myfavbeerproject.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class MashTemp {

    @SerializedName("temp")
    @Expose
    private TempMashTemp temp;
    @SerializedName("duration")
    @Expose
    private int duration;


    public MashTemp(TempMashTemp temp, int duration) {
        super();
        this.temp = temp;
        this.duration = duration;
    }

    public TempMashTemp getTemp() {
        return temp;
    }

    public void setTemp(TempMashTemp temp) {
        this.temp = temp;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

}
