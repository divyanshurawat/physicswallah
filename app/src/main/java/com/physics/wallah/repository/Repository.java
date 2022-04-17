package com.physics.wallah.repository;

import android.content.Context;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.physics.wallah.api.RetrofitClientInstance;
import com.physics.wallah.api.GetDataService;
import com.physics.wallah.model.Details;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    Context context;
    RecyclerView recyclerView;
    public Repository(Context applicationContext,RecyclerView recyclerView) {
        context=applicationContext;
        this.recyclerView=recyclerView;
    }

    public MutableLiveData<List<Details>> getDetails() {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<Details>> call = service.getAllDetails();

        final MutableLiveData<List<Details>> personData = new MutableLiveData<>();
        call.enqueue(new Callback<List<Details>>() {
            @Override
            public void onResponse(Call<List<Details>> call, Response<List<Details>> response) {
                personData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Details>> call, Throwable t) {
                System.out.println("onFailure");
                personData.setValue(null);
            }
        });
        return  personData;
    }
}
