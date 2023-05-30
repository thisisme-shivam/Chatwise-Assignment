package com.example.internchatwise.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.internchatwise.model.Photo;
import com.example.internchatwise.repository.LoadImageRepository;

import java.util.ArrayList;

public class ImageViewModel extends ViewModel {
    LoadImageRepository loadImageRepository;
    public ImageViewModel(){
        init();

    }

    private void init() {
        loadImageRepository = new LoadImageRepository();

    }
    MutableLiveData<ArrayList<Photo>> getImages(){
        return loadImageRepository.getImageUrls();

    }
}
