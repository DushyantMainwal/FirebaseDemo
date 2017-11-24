package com.demo.firebasedemo;


import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Dushyant Mainwal on 29-Oct-17.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    Context context;
    ArrayList<String> fullNameList;
    ArrayList<String> userNameList;
    ArrayList<String> profilePicList;

    class SearchViewHolder extends RecyclerView.ViewHolder {
        ImageView profileImage;
        TextView full_name, user_name;

        public SearchViewHolder(View itemView) {
            super(itemView);
            profileImage = (ImageView) itemView.findViewById(R.id.profileImage);
            full_name = (TextView) itemView.findViewById(R.id.full_name);
            user_name = (TextView) itemView.findViewById(R.id.user_name);
        }
    }

    public SearchAdapter(Context context, ArrayList<String> fullNameList, ArrayList<String> userNameList, ArrayList<String> profilePicList) {
        this.context = context;
        this.fullNameList = fullNameList;
        this.userNameList = userNameList;
        this.profilePicList = profilePicList;
    }

    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_list_items, parent, false);
        return new SearchAdapter.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        holder.full_name.setText(fullNameList.get(position));
        holder.user_name.setText(userNameList.get(position));
        Glide.with(context).load(profilePicList.get(position)).asBitmap().placeholder(R.mipmap.ic_launcher_round).into(holder.profileImage);

        holder.full_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Full Name Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return fullNameList.size();
    }
}
