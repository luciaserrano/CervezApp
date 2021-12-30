package com.example.myfavbeerproject.bbdd;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class BeerDB implements Parcelable {
    @PrimaryKey
    private int id;
    private String name;
    private String description;
    private String imageUrl;

    public BeerDB(int id, String name, String description, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
    }


    protected BeerDB(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        imageUrl = in.readString();
    }

    public static final Creator<BeerDB> CREATOR = new Creator<BeerDB>() {
        @Override
        public BeerDB createFromParcel(Parcel in) {
            return new BeerDB(in);
        }

        @Override
        public BeerDB[] newArray(int size) {
            return new BeerDB[size];
        }
    };

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(imageUrl);
    }
}