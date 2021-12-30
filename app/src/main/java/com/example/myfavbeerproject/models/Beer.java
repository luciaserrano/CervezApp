package com.example.myfavbeerproject.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.myfavbeerproject.models.BoilVolume;
import com.example.myfavbeerproject.models.Ingredients;
import com.example.myfavbeerproject.models.Method;
import com.example.myfavbeerproject.models.Volume;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.List;
public class Beer implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("tagline")
    @Expose
    private String tagline;
    @SerializedName("first_brewed")
    @Expose
    private String firstBrewed;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("abv")
    @Expose
    private double abv;
    @SerializedName("ibu")
    @Expose
    private double ibu;
    @SerializedName("target_fg")
    @Expose
    private int targetFg;
    @SerializedName("target_og")
    @Expose
    private double targetOg;
    @SerializedName("ebc")
    @Expose
    private int ebc;
    @SerializedName("srm")
    @Expose
    private double srm;
    @SerializedName("ph")
    @Expose
    private double ph;
    @SerializedName("attenuation_level")
    @Expose
    private double attenuationLevel;
    @SerializedName("volume")
    @Expose
    private Volume volume;
    @SerializedName("boil_volume")
    @Expose
    private BoilVolume boilVolume;
    @SerializedName("method")
    @Expose
    private Method method;
    @SerializedName("ingredients")
    @Expose
    private Ingredients ingredients;
    @SerializedName("food_pairing")
    @Expose
    private List<String> foodPairing = null;
    @SerializedName("brewers_tips")
    @Expose
    private String brewersTips;
    @SerializedName("contributed_by")
    @Expose
    private String contributedBy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getFirstBrewed() {
        return firstBrewed;
    }

    public void setFirstBrewed(String firstBrewed) {
        this.firstBrewed = firstBrewed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getAbv() {
        return abv;
    }

    public void setAbv(double abv) {
        this.abv = abv;
    }

    public double getIbu() {
        return ibu;
    }

    public void setIbu(double ibu) {
        this.ibu = ibu;
    }

    public int getTargetFg() {
        return targetFg;
    }

    public void setTargetFg(int targetFg) {
        this.targetFg = targetFg;
    }

    public double getTargetOg() {
        return targetOg;
    }

    public void setTargetOg(double targetOg) {
        this.targetOg = targetOg;
    }

    public int getEbc() {
        return ebc;
    }

    public void setEbc(int ebc) {
        this.ebc = ebc;
    }

    public double getSrm() {
        return srm;
    }

    public void setSrm(double srm) {
        this.srm = srm;
    }

    public double getPh() {
        return ph;
    }

    public void setPh(double ph) {
        this.ph = ph;
    }

    public double getAttenuationLevel() {
        return attenuationLevel;
    }

    public void setAttenuationLevel(double attenuationLevel) {
        this.attenuationLevel = attenuationLevel;
    }

    public Volume getVolume() {
        return volume;
    }

    public void setVolume(Volume volume) {
        this.volume = volume;
    }

    public BoilVolume getBoilVolume() {
        return boilVolume;
    }

    public void setBoilVolume(BoilVolume boilVolume) {
        this.boilVolume = boilVolume;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Ingredients getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredients ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getFoodPairing() {
        return foodPairing;
    }

    public void setFoodPairing(List<String> foodPairing) {
        this.foodPairing = foodPairing;
    }

    public String getBrewersTips() {
        return brewersTips;
    }

    public void setBrewersTips(String brewersTips) {
        this.brewersTips = brewersTips;
    }

    public String getContributedBy() {
        return contributedBy;
    }

    public void setContributedBy(String contributedBy) {
        this.contributedBy = contributedBy;
    }

    public static Creator<Beer> getCREATOR() {
        return CREATOR;
    }

    public Beer(int id, String name, String tagline, String firstBrewed, String description, String imageUrl, double abv, double ibu, int targetFg, double targetOg, int ebc, double srm, double ph, double attenuationLevel, Volume volume, BoilVolume boilVolume, Method method, Ingredients ingredients, List<String> foodPairing, String brewersTips, String contributedBy) {
        this.id = id;
        this.name = name;
        this.tagline = tagline;
        this.firstBrewed = firstBrewed;
        this.description = description;
        this.imageUrl = imageUrl;
        this.abv = abv;
        this.ibu = ibu;
        this.targetFg = targetFg;
        this.targetOg = targetOg;
        this.ebc = ebc;
        this.srm = srm;
        this.ph = ph;
        this.attenuationLevel = attenuationLevel;
        this.volume = volume;
        this.boilVolume = boilVolume;
        this.method = method;
        this.ingredients = ingredients;
        this.foodPairing = foodPairing;
        this.brewersTips = brewersTips;
        this.contributedBy = contributedBy;
    }

    protected Beer(Parcel in) {
        id = in.readInt();
        name = in.readString();
        tagline = in.readString();
        firstBrewed = in.readString();
        description = in.readString();
        imageUrl = in.readString();
        abv = in.readDouble();
        ibu = in.readDouble();
        targetFg = in.readInt();
        targetOg = in.readDouble();
        ebc = in.readInt();
        srm = in.readDouble();
        ph = in.readDouble();
        attenuationLevel = in.readDouble();
        ingredients = in.readParcelable(Ingredients.class.getClassLoader());
        foodPairing = in.createStringArrayList();
        brewersTips = in.readString();
        contributedBy = in.readString();
    }

    public static final Creator<Beer> CREATOR = new Creator<Beer>() {
        @Override
        public Beer createFromParcel(Parcel in) {
            return new Beer(in);
        }

        @Override
        public Beer[] newArray(int size) {
            return new Beer[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(tagline);
        dest.writeString(firstBrewed);
        dest.writeString(description);
        dest.writeString(imageUrl);
        dest.writeDouble(abv);
        dest.writeDouble(ibu);
        dest.writeInt(targetFg);
        dest.writeDouble(targetOg);
        dest.writeInt(ebc);
        dest.writeDouble(srm);
        dest.writeDouble(ph);
        dest.writeDouble(attenuationLevel);
        dest.writeParcelable(ingredients, flags);
        dest.writeStringList(foodPairing);
        dest.writeString(brewersTips);
        dest.writeString(contributedBy);
    }
}

