package com.example.admin.mydiary;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by admin on 9/17/2017.
 */

public class PostAdapter extends ArrayAdapter<Post> {
    Context context;
    ArrayList<Post>  posts;

    public PostAdapter(Context context,ArrayList<Post> posts) {
        super(context, R.layout.item_row,posts);
        this.context = context;
        this.posts = posts;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View single_row = inflater.inflate(R.layout.item_row, null,true);
        TextView txtName = (TextView) single_row.findViewById(R.id.tvTitle);
        TextView txtTime = (TextView) single_row.findViewById(R.id.tvTime);
        Post post = posts.get(position);
        txtName.setText(post.getName());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        txtTime.setText(dateFormat.format(post.getTime()));
        return single_row;
    }
}
