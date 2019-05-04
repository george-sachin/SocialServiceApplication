package com.example.vanessa.temp.helper;
/**
 * Created by Ravi on 13/05/15.
 */
public class Movie {
   public int id;
    public String title,description;

    public Movie() {
    }

    public Movie(int id,String title,String description) {
        this.title = title;
       this.id = id;
        this.description=description;
    }

    public int getId() {
        return 0;
    }
}