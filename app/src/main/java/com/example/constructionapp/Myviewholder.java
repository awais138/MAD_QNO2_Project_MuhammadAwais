package com.example.constructionapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Myviewholder extends RecyclerView.ViewHolder {

    TextView textview_detail, name_id;
    View view;

    public Myviewholder(@NonNull View itemView) {
        super(itemView);
        textview_detail =itemView.findViewById(R.id.name_item);
        name_id= itemView.findViewById(R.id.name);
        view=itemView;


    }
}
