package com.arshad.mindvalley.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.arshad.mindvalley.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by root on 30/1/17.
 */
public class ViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.tvName)
    public TextView tvName;

    @Bind(R.id.tvUserName)
    public TextView tvUserName;

    @Bind(R.id.tvUserId)
    public TextView tvUserId;

    @Bind(R.id.tvLikes)
    public TextView tvLikes;

    @Bind(R.id.tvViewDetails)
    public TextView tvViewDetails;

    @Bind(R.id.ivImage)
    public ImageView ivImage;

    public ViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
