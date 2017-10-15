package com.example.admin.mydiary;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 9/17/2017.
 */
@IgnoreExtraProperties
public class Post {
    private String post_id;
    private String name;
    private String content;
    private String time;
    private String email;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public Post(String id, String name, String content, String time, String email) {
        this.post_id = id;
        this.name = name;
        this.content = content;
        this.time = time;
        this.email = email;
    }

    public Post(String name) {
        this.name = name;
    }

    public Post(){

    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("post_id", post_id);
        result.put("title", name);
        result.put("content", content);
        result.put("date_write", time);
        result.put("email", email);

        return result;
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

    public String getTime() {
        return time;
    }



    public void setTime(String time) {
        this.time = time;
    }


}
