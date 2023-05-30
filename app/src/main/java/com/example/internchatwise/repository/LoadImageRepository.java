package com.example.internchatwise.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.internchatwise.model.Photo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class LoadImageRepository {
    MutableLiveData<ArrayList<Photo>> imageUrlsMutableList;
    ArrayList<Photo> photoArrayList;
    public LoadImageRepository(){
        photoArrayList  = new ArrayList<>();
        imageUrlsMutableList = new MutableLiveData<>(photoArrayList);
        loadImages();
    }

    private void loadImages() {
        // Create Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PhotoApiService apiService = retrofit.create(PhotoApiService.class);
        Call<List<Photo>> call = apiService.getPhotos();
        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                Log.d("Response", "Status Code: " + response.code());
                if (response.isSuccessful()) {
                    List<Photo> photos = response.body();
                    Log.i("value ",photos.get(0).getId());
                    if (photos != null) {
                        Log.i("size of photo", String.valueOf(photos.size()));
                        photoArrayList.clear();
                        photoArrayList.addAll(photos);

                        imageUrlsMutableList.postValue(photoArrayList);
                    }

                } else {
                    // Handle error

                }
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                // Handle failure

            }
        });


    }

    public MutableLiveData<ArrayList<Photo>> getImageUrls() {
        Log.i("jj","jj");
        return imageUrlsMutableList;
    }

    public interface PhotoApiService {
        @GET("photos")
        Call<List<Photo>> getPhotos();
    }

}
