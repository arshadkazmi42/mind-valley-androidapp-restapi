package com.arshad.mindvalley.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;

import com.arshad.mindvalley.R;
import com.arshad.mindvalley.adapter.DataAdapter;
import com.arshad.mindvalley.global.GlobalFunctions;
import com.arshad.mindvalley.interfaces.GlobalApi;
import com.arshad.mindvalley.model.DataResponse;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.rvData)
    RecyclerView rvData;

    @Bind(R.id.srlSwipeRefresh)
    SwipeRefreshLayout swipeRefreshLayout;

    Context mContext;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = this;
        dialog = new ProgressDialog(this);

        GlobalFunctions.createVerticalRecyclerView(mContext, rvData);

        loadData();

        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(mContext, R.color.colorPrimary));
    }


    private void loadData() {
        GlobalFunctions.startProgressDialog(dialog, "Please wait");
        final Call<List<DataResponse>> call = GlobalFunctions.initializeRetrofit(mContext).create(GlobalApi.class)
                .getData();

        call.enqueue(new Callback<List<DataResponse>>() {
            @Override
            public void onResponse(Response<List<DataResponse>> response, Retrofit retrofit) {
                GlobalFunctions.stopProgressDialog(dialog);
                try {

                    if(response.code() == 200) {
                        List<DataResponse> data = response.body();
                        DataAdapter adapter = new DataAdapter(MainActivity.this, mContext, data);
                        rvData.setAdapter(adapter);
                        rvData.setItemAnimator(new DefaultItemAnimator());
                    } else {
                        GlobalFunctions.toastShort(mContext, "Unable to load data. Please try again");
                    }

                } catch (Exception e) {
                    GlobalFunctions.toastShort(mContext, "Unable to load data. Please try again");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                GlobalFunctions.toastShort(mContext, "Unable to load data. Please try again");
            }
        });
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
        loadData();
    }
}
