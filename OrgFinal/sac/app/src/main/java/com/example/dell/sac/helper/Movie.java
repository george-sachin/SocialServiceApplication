package com.example.dell.sac.helper;

/**
 * Created by JAI GEORGE on 14-06-2016.
 */
public class Movie {
    public int id;
    public String title;
    public String address;
    public int phone;
    public Movie() {
    }

    public Movie(int id, String title,String address,int phone) {
        this.title = title;
        this.id = id;
        this.address=address;
        this.phone=phone;
    }
}