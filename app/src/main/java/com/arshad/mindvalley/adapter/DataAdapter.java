package com.arshad.mindvalley.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arshad.mindvalley.R;
import com.arshad.mindvalley.activity.DetailActivity;
import com.arshad.mindvalley.activity.MainActivity;
import com.arshad.mindvalley.model.DataResponse;
import com.arshad.mindvalley.viewholder.ViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 30/1/17.
 */
public class DataAdapter extends RecyclerView.Adapter<ViewHolder> {

    private Context mContext;
    private MainActivity activity;
    private List<DataResponse> data;

    public DataAdapter(MainActivity activity, Context mContext, List<DataResponse> data) {
        this.activity = activity;
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from (mContext).inflate(R.layout.single_row_data, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final DataResponse d = data.get(position);
        holder.tvName.setText(d.getUser().getName());
        holder.tvUserName.setText("( " + d.getUser().getUsername() + " )");
        holder.tvUserId.setText("ID: " + d.getUser().getId());
        holder.tvLikes.setText(d.getLikes() + "");

        Picasso.with(mContext).load(d.getUser().getProfileImage().getMedium()).into(holder.ivImage);

        holder.tvViewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, DetailActivity.class);
                Bundle b = new Bundle();
                b.putParcelable("data", d);
                i.putExtras(b);

                //Adding Shared transition
                Pair<View, String> p1 = Pair.create((View) holder.ivImage, mContext.getResources().getString(R.string.photoTransitionName));
                Pair<View, String> p2 = Pair.create((View) holder.tvName, mContext.getResources().getString(R.string.nameTransitionName));
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, p1, p2);
                if(Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                    mContext.startActivity(i, options.toBundle());
                } else {
                    mContext.startActivity(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
