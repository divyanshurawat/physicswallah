package com.physics.wallah.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.physics.wallah.model.Details;
import com.physics.wallah.viewmodel.MainViewModelFactory;
import com.physics.wallah.R;
import com.physics.wallah.adapter.CustomAdapter;
import com.physics.wallah.viewmodel.MainViewModel;

import java.util.List;

public class DetailFragment extends Fragment {
    private CustomAdapter adapter;
    private RecyclerView recyclerView;

    int count = 0;
    private ProgressBar loadingPB;
    private NestedScrollView nestedSV;

    MutableLiveData<List<Details>> photoList = new MutableLiveData<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment, container, false);

        loadingPB = view.findViewById(R.id.idPBLoading);
        nestedSV = view.findViewById(R.id.Nested);


        recyclerView = view.findViewById(R.id.customRecyclerView);
        setupViewModel();


        return view;
    }
    private void setupViewModel() {
        MainViewModelFactory factory = new MainViewModelFactory(getContext(),recyclerView);
        MainViewModel viewModel = ViewModelProviders.of(this,factory).get(MainViewModel.class);

        viewModel.getDetails().observe(getActivity(),  new Observer<List<Details>>(){
            @Override
            public void onChanged(List<Details> retroPhotos) {
                if(retroPhotos.isEmpty()){
                    loadingPB.setVisibility(View.GONE);
                    Toast.makeText(getContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                }
                else{
                    loadingPB.setVisibility(View.GONE);
                    generateDataList( retroPhotos );
                }
            }
        });
    }


    private void generateDataList( List<Details> photoList) {
        CustomAdapter adapter;
        adapter = new CustomAdapter(getContext(),photoList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        nestedSV.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                // on scroll change we are checking when users scroll as bottom.
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                    // in this method we are incrementing page number,
                    // making progress bar visible and calling get data method.
                    count++;
                    // on below line we are making our progress bar visible.
                    loadingPB.setVisibility(View.VISIBLE);
                    if (count < 7) {

                 adapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }
}
