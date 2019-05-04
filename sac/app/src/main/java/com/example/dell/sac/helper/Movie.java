package com.example.dell.sac.helper;

/**
 * Created by JAI GEORGE on 14-06-2016.
 */
public class Movie {
    public String id;
    public String title;
    public String address,thumbnailUrl;
    public String phone;
    public Movie() {
    }

    public Movie(String id, String title,String address,String phone,String thumbnailUrl) {
        this.title = title;
        this.id = id;
        this.address=address;
        this.phone=phone;
        this.thumbnailUrl=thumbnailUrl;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(int year) {
        this.id = id;
    }
}