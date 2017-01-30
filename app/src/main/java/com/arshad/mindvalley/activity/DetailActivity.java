package com.arshad.mindvalley.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.arshad.mindvalley.R;
import com.arshad.mindvalley.adapter.CategoryAdapter;
import com.arshad.mindvalley.adapter.DataAdapter;
import com.arshad.mindvalley.global.GlobalFunctions;
import com.arshad.mindvalley.model.DataResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by root on 30/1/17.
 */
public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.rlParent)
    RelativeLayout rlParent;

    @Bind(R.id.ivCover)
    ImageView ivCover;

    @Bind(R.id.ivProfile)
    ImageView ivProfile;

    @Bind(R.id.tvName)
    TextView tvName;

    @Bind(R.id.tvUserName)
    TextView tvUsername;

    @Bind(R.id.tvUserId)
    TextView tvUserId;

    @Bind(R.id.tvView)
    TextView tvView;

    @Bind(R.id.rvCategory)
    RecyclerView rvCategory;

    Context mContext;
    ProgressDialog dialog;
    DataResponse data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        mContext = this;
        dialog = new ProgressDialog(mContext);

        GlobalFunctions.createVerticalRecyclerView(mContext, rvCategory);

        if(getIntent().hasExtra("data")) {
            GlobalFunctions.viewVisible(rlParent);
            data = getIntent().getExtras().getParcelable("data");
            setupViews();
        } else {
            GlobalFunctions.viewHidden(rlParent);
            GlobalFunctions.toastShort(mContext, "Unable to populate views. Please try again");
        }

        tvView.setOnClickListener(this);
    }

    private void setupViews() {
        CategoryAdapter adapter = new CategoryAdapter(mContext, data.getCategories());
        rvCategory.setAdapter(adapter);
        rvCategory.setItemAnimator(new DefaultItemAnimator());

        Picasso.with(mContext).load(data.getUrls().getThumb()).into(ivCover);
        Picasso.with(mContext).load(data.getUser().getProfileImage().getMedium()).into(ivProfile);

        tvName.setText(data.getUser().getName());
        tvUsername.setText(data.getUser().getUsername());
        tvUserId.setText(data.getUser().getId());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvView:
                Intent i = new Intent(mContext, WebViewActivity.class);
                i.putExtra("url", data.getUser().getUserLinks().getHtml());
                mContext.startActivity(i);
                break;
        }
    }
}
