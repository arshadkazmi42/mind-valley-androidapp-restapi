package com.arshad.mindvalley.global;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.arshad.mindvalley.R;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by root on 30/1/17.
 */
public class GlobalFunctions {

    private static final String TAG = "GlobalFunctions";

    static String app_name = "com.arshad.mindvalley";


    public static Retrofit initializeRetrofit(Context mContext) {
        OkHttpClient client = new OkHttpClient();
        final File baseDir = mContext.getCacheDir();
        if (baseDir != null) {
            final File cacheDir = new File(baseDir, "HttpResponseCache");
            client.setCache(new Cache(cacheDir, 10 * 1024 * 1024));
        }

        //Setting time out for okHTTP
        client.setConnectTimeout(60, TimeUnit.SECONDS); // connect timeout
        client.setReadTimeout(60, TimeUnit.SECONDS);
        client.setRetryOnConnectionFailure(true);

        /**
         * For Logging in retrofit
         */
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.interceptors().add(interceptor);

        return new Retrofit.Builder()
                .baseUrl(Constants.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }



    /**
     * Checking if NetWorkAvailable
     *
     * @param c
     * @return
     */
    public static boolean isNetworkAvailable(Activity c) {
        boolean state;
        ConnectivityManager cmg = (ConnectivityManager) c
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = cmg.getActiveNetworkInfo();
        state = activeNetworkInfo != null && activeNetworkInfo.isConnected();

        if (state) {
            return true;
        } else {
            Log.e(TAG, c.getResources().getString(R.string.noNetwork));
            return false;
        }
    }

    /**
     * Storing string value into SharedPrefs
     *
     * @param c
     * @param key
     * @param value
     */
    public static void setSharedPrefs(Context c, String key, String value) {

        SharedPreferences.Editor editor = c.getSharedPreferences(app_name,
                Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();

    }

    /**
     * Storing int value into SharedPrefs
     *
     * @param c
     * @param key
     * @param value
     */
    public static void setSharedPrefs(Context c, String key, int value) {

        SharedPreferences.Editor editor = c.getSharedPreferences(app_name,
                Context.MODE_PRIVATE).edit();
        editor.putInt(key, value);
        editor.apply();

    }

    /**
     * Storing float value into SharedPrefs
     *
     * @param c
     * @param key
     * @param value
     */
    public static void setSharedPrefs(Context c, String key, float value) {

        SharedPreferences.Editor editor = c.getSharedPreferences(app_name,
                Context.MODE_PRIVATE).edit();
        editor.putFloat(key, value);
        editor.apply();

    }

    /**
     * Fetching float value from SharedPrefs
     *
     * @param c
     * @param key
     * @param default_value
     * @return
     */
    public static float getSharedPrefs(Context c, String key, float default_value) {
        if (c == null) {
            return default_value;
        } else {
            SharedPreferences prefs = c.getSharedPreferences(app_name,
                    Context.MODE_PRIVATE);
            return prefs.getFloat(key, default_value);
        }
    }

    /**
     * Fetching String value from SharedPrefs
     *
     * @param c
     * @param key
     * @param default_value
     * @return
     */
    public static String getSharedPrefs(Context c, String key,
                                        String default_value) {
        if (c == null) {
            return default_value;
        } else {
            SharedPreferences prefs = c.getSharedPreferences(app_name,
                    Context.MODE_PRIVATE);
            return prefs.getString(key, default_value);
        }
    }

    /**
     * Fetching int value from SharedPrefs
     *
     * @param c
     * @param key
     * @param default_value
     * @return
     */
    public static int getSharedPrefs(Context c, String key, int default_value) {
        if (c == null) {
            return default_value;
        } else {
            SharedPreferences prefs = c.getSharedPreferences(app_name,
                    Context.MODE_PRIVATE);
            return prefs.getInt(key, default_value);
        }
    }

    /**
     * Showing Short Toast Message
     */
    public static void toastShort(Context c, String msg) {
        Toast.makeText(c, msg, Toast.LENGTH_SHORT).show();
    }


    /**
     * Make View Visible
     *
     * @param views
     */
    public static void viewVisible(View... views) {
        for (View view : views) {
            view.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Make View Hidden
     *
     * @param views
     */
    public static void viewHidden(View... views) {
        for (View view : views) {
            view.setVisibility(View.GONE);
        }
    }

    /**
     * Make View Invisible
     *
     * @param views
     */
    public static void viewInvisible(View... views) {
        for (View view : views) {
            view.setVisibility(View.INVISIBLE);
        }
    }


    /**
     * Creating HorizontalLayout for RecyclerView
     *
     * @param mContext
     * @param recyclerViews
     */
    public static void createHorizontalRecyclerView(Context mContext, RecyclerView... recyclerViews) {
        for (RecyclerView recyclerView : recyclerViews) {
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        }
    }

    /**
     * Creating VerticalLayout for RecyclerView
     *
     * @param mContext
     * @param recyclerViews
     */
    public static void createVerticalRecyclerView(Context mContext, RecyclerView... recyclerViews) {
        for (RecyclerView recyclerView : recyclerViews) {
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        }
    }

    /**
     * Start ProgressDialog loading
     *
     * @param dialog
     * @param message
     */
    public static void startProgressDialog(ProgressDialog dialog, String message) {
        try {
            dialog.setMessage(message);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
            dialog.setContentView(R.layout.global_progress_dialog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Stop progress dialog loading
     *
     * @param dialog
     */
    public static void stopProgressDialog(ProgressDialog dialog) {
        try {
            if (dialog != null && dialog.isShowing()) {
                Log.e(TAG, "Dismissing Dialog");
                dialog.dismiss();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Remove Duplicates from List
     * @param list
     * @return
     */
    public static List<String> removeDuplicatesFromList(List<String> list){
        try {
            HashSet hs = new HashSet();
            hs.addAll(list);
            list.clear();
            list.addAll(hs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Trim Spaces from String
     * @param input
     * @return
     */
    public static String trim(String input){
        return input.replaceAll("\\s+","");
    }

    /**
     * Handling Response codes
     * @param mContext
     * @param code
     */
    public static void handleResponseCode(Context mContext, int code) {
        switch (code) {
            case 406:
                GlobalFunctions.toastShort(mContext, "Invalid Token");
                break;

            case 500:
                GlobalFunctions.toastShort(mContext, "Something went wrong. Try again");
                break;
        }
    }

    /**
     * Failed Response Handler
     * @param e
     * @param dialog
     * @param mContext
     * @param message
     */
    public static void failedResponse(Throwable e, ProgressDialog dialog, Context mContext, String message) {
        Log.e(TAG, e.getMessage());
        GlobalFunctions.stopProgressDialog(dialog);
        GlobalFunctions.toastShort(mContext, message);
    }

    /**
     * Showing Popup Dialog
     * @param dialog
     */
    public static void showDialog(Dialog dialog) {
        try{
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Dismiss popup dialog
     * @param dialog
     */
    public static void dismissDialog(Dialog dialog) {
        try{
            dialog.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
