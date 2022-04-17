package com.physics.wallah.viewmodel;

import android.content.Context;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.physics.wallah.repository.Repository;
import androidx.recyclerview.widget.RecyclerView;
import com.physics.wallah.model.Details;


public class MainViewModel extends ViewModel {
    Repository repository;

    public MainViewModel(Context context, RecyclerView recyclerView) {
        repository = new Repository(context,recyclerView);
    }

    public MutableLiveData<List<Details>> getDetails() {
        return repository.getDetails();
    }

}
