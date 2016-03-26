package com.epicodus.loglegal.models;

import java.util.ArrayList;

public class Legal {
    private String mName;
    private String mPhone;
    private String mWebsite;
    private double mRating;
    private String mImageUrl;
    private ArrayList<String> mAddress = new ArrayList<>();
    private ArrayList<String> mShortAddress = new ArrayList<>();
    private double mLatitude;
    private double mLongitude;
    private ArrayList<String> mCategories = new ArrayList<>();

    public Legal(String name, String phone, String website, double rating, String imageUrl, ArrayList<String> address, ArrayList<String> shortAddress, double latitude, double longitude, ArrayList<String> categories) {
        this.mName = name;
        this.mPhone = phone;
        this.mWebsite = website;
        this.mRating = rating;
        this.mImageUrl = imageUrl;
        this.mAddress = address;
        this.mShortAddress = shortAddress;
        this.mLatitude = latitude;
        this.mLongitude = longitude;
        this.mCategories = categories;
    }

    public String getName() {
        return mName;
    }

    public String getPhone() {
        return mPhone;
    }

    public String getWebsite() {
        return mWebsite;
    }

    public double getRating() {
        return mRating;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public ArrayList<String> getAddress() {
        return mAddress;
    }

    public ArrayList<String> getShortAddress() {
        return mShortAddress;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public ArrayList<String> getCategories() {
        return mCategories;
    }
}
