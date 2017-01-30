package com.arshad.mindvalley.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.arshad.mindvalley.R;
import com.arshad.mindvalley.global.GlobalFunctions;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WebViewActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener,
        ViewTreeObserver.OnScrollChangedListener {

    private static final String TAG = WebViewActivity.class.getSimpleName();

    @Bind(R.id.srlSwipeRefresh)
    SwipeRefreshLayout srlSwipeRefresh;

    @Bind(R.id.nsvScrollView)
    NestedScrollView nsvScrollView;

    @Bind(R.id.wvWeb)
    WebView wvWeb;

    private Context mContext;
    private ProgressDialog dialog;
    private String url;

    @Override
    protected void onCreate(Bundle savedIntanceState){
        super.onCreate(savedIntanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);
        mContext = this;
        dialog = new ProgressDialog(mContext);

        /**
         * Getting data from previous activity
         */
        if(getIntent().hasExtra("url")){
            url = getIntent().getStringExtra("url");
            Log.e("URL", url);
        }

        /**
         * SwipeRefresh Color
         */
        srlSwipeRefresh.setColorSchemeColors(ContextCompat.getColor(mContext, R.color.colorPrimary));

        /**
         * Loading URL in webview
         */
        wvWeb.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView webView, String url, Bitmap favicon) {
                super.onPageStarted(webView, url, favicon);
                GlobalFunctions.startProgressDialog(dialog, "Please wait...");
            }

            @Override
            public void onPageFinished(WebView webView, String url) {
                super.onPageFinished(webView, url);
                if (dialog != null) {
                    GlobalFunctions.stopProgressDialog(dialog);
                }
            }

        });
        wvWeb.getSettings().setLoadsImagesAutomatically(true);
        wvWeb.getSettings().setJavaScriptEnabled(true);
        wvWeb.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        wvWeb.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wvWeb.loadUrl(url);

        wvWeb.getViewTreeObserver().addOnScrollChangedListener(this); //WebView scrollListener

        srlSwipeRefresh.setOnRefreshListener(this); //SwipeRefreshListener
    }

    /**
     * OnBackButton Pressed
     */
    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }

    @Override
    public void onStop(){
        super.onStop();
        if(dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    /**
     * Refreshing webview
     */
    @Override
    public void onRefresh() {
        dialog = new ProgressDialog(mContext);
        srlSwipeRefresh.setRefreshing(false);
        wvWeb.loadUrl(wvWeb.getUrl());
    }

    /**
     * on WebView scroll check
     */
    @Override
    public void onScrollChanged() {
        if (wvWeb.getScrollY() == 0) {
            srlSwipeRefresh.setEnabled(true);
        } else {
            srlSwipeRefresh.setEnabled(false);
        }
    }
}
