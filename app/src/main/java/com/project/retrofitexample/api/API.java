package com.project.retrofitexample.api;

import com.project.retrofitexample.api.model.SearchModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by gleb on 5/24/17.
 */

public interface API {

    @GET("api.php?method=search")
    Call<SearchModel> searchAudio(@Query("q") String query, @Query("key") String key);
}
