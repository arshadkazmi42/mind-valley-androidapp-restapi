package com.arshad.mindvalley.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.arshad.mindvalley.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by root on 30/1/17.
 */
public class CategoryViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.tvCategoryName)
    public TextView tvCategoryName;

    @Bind(R.id.tvPhotoCount)
    public TextView tvPhotoCount;

    @Bind(R.id.tvSelf)
    public TextView tvSelf;

    @Bind(R.id.tvPhotos)
    public TextView tvPhotos;

    public CategoryViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
