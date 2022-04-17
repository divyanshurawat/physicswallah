package com.physics.wallah.api;



import com.physics.wallah.model.Details;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface  GetDataService {

    @GET("easygautam/data/users")
    Call<List<Details>> getAllDetails();
}
