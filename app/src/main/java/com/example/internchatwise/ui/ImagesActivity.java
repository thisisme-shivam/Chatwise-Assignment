package com.example.internchatwise.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.internchatwise.model.Photo;
import com.example.internchatwise.adapter.PhotoAdapter;
import com.example.internchatwise.databinding.ActivityImagesBinding;

import java.util.ArrayList;

public class ImagesActivity extends AppCompatActivity {
    ImageViewModel imageViewModel;
    PhotoAdapter photoAdapter;
    ActivityImagesBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityImagesBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        imageViewModel  =  new ViewModelProvider(this).get(ImageViewModel.class);
        init();

        addObserver();

    }

    private void addObserver() {
        imageViewModel.getImages().observe(this, new Observer<ArrayList<Photo>>() {
            @Override
            public void onChanged(ArrayList<Photo> photos) {
                if(photos!=null){
                    if(!photos.isEmpty()){
                        Log.i("called","yes");
                        photoAdapter.updateList(photos);
                    }

                }
            }
        });
    }

    private void init(){

        photoAdapter = new PhotoAdapter(getApplicationContext(),new ArrayList<>());
        RecyclerView recyclerView = binding.imagesrv;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(photoAdapter);
    }
}