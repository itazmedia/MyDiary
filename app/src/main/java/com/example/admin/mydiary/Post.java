package com.example.admin.mydiary;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by admin on 9/17/2017.
 */

public class Post {
    private String name;
    private Date time;

    public Post(String name) {
        this.name = name;
    }

    public Post(){

    }

    public Post(String name, Date time) {
        this.name = name;
        this.time = time;
    }

    public Post(String name, String time) {
        this.name = name;
        setTime(time);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setTime(String date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            setTime(dateFormat.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
