package com.arshad.mindvalley.interfaces;

import com.arshad.mindvalley.model.DataResponse;

import java.util.HashMap;
import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by root on 30/1/17.
 */
public interface GlobalApi {

    @POST("wgkJgazE")
    Call<List<DataResponse>> getData();

}
