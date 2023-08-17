package com.iti.finalproject.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Movie {

    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private List<Result> results;

    public int getPage() {
        return page;
    }

    public List<Result> getResults() {
        return results;
    }
}
