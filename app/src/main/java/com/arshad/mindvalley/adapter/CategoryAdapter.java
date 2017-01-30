package com.arshad.mindvalley.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arshad.mindvalley.R;
import com.arshad.mindvalley.activity.WebViewActivity;
import com.arshad.mindvalley.model.Category;
import com.arshad.mindvalley.model.DataResponse;
import com.arshad.mindvalley.viewholder.CategoryViewHolder;
import com.arshad.mindvalley.viewholder.ViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by root on 30/1/17.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    private Context mContext;
    private List<Category> data;

    public CategoryAdapter(Context mContext, List<Category> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from (mContext).inflate(R.layout.single_row_category, parent, false);
        return new CategoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        final Category d = data.get(position);
        holder.tvCategoryName.setText(d.getTitle());
        holder.tvPhotoCount.setText("Photos: " + d.getPhotoCount());

        holder.tvSelf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent selfIntent = new Intent(mContext, WebViewActivity.class);
                selfIntent.putExtra("url", d.getLinks().getSelf());
                mContext.startActivity(selfIntent);
            }
        });

        holder.tvPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoIntent = new Intent(mContext, WebViewActivity.class);
                photoIntent.putExtra("url", d.getLinks().getPhotos());
                mContext.startActivity(photoIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
