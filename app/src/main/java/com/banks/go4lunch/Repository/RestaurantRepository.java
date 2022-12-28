package com.banks.go4lunch.Repository;

import android.location.Location;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.banks.go4lunch.BuildConfig;
import com.banks.go4lunch.Data.RestaurantService;
import com.banks.go4lunch.Data.RetrofitClient;
import com.banks.go4lunch.Model.Restaurant;
import com.banks.go4lunch.Model.jsonResponse.RestaurantResponse;
import com.banks.go4lunch.Model.jsonResponse.AllRestaurantResponse;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class RestaurantRepository {

    MutableLiveData<List<Restaurant>> result = new MutableLiveData<>();

    public LiveData<List<Restaurant>> getRestaurantLiveData() {
        return result;
    }

    MutableLiveData<Restaurant> oneResult = new MutableLiveData<>();

    public LiveData<Restaurant> getOneRestaurantLiveData() {
        return oneResult;
    }



    public void fetchRestaurant(Double latitude, Double longitude) {
        streamFetchRestaurantResponse(latitude,longitude)
                .subscribeWith(new DisposableObserver<List<Restaurant>>() {

                    @Override
                    public void onNext(@NonNull List<Restaurant> restaurants) {
                        result.setValue(restaurants);


                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("Renel", "On error");

                    }

                    @Override
                    public void onComplete() {
                        Log.d("Renel", "On complete");

                    }
                });
    }



    public  Observable<List<Restaurant>> streamFetchRestaurantResponse(Double latitude, Double longitude) {

        String lat = Double.toString(latitude);
        String lng = Double.toString(longitude);
        String location = lat + "," + lng;




        RestaurantService restaurantService = RetrofitClient.getRetrofit().create(RestaurantService.class);
        return restaurantService.getAllRestaurantsResponse(BuildConfig.RR_KEY, location)

                .map((Function<AllRestaurantResponse, List<Restaurant>>) resultsResponse -> {
                    ArrayList<Restaurant> restaurants = new ArrayList<>();


                    for (RestaurantResponse restaurantResponse : resultsResponse.getResults()) {
                        Boolean isOpen = restaurantResponse.getOpeningResponse() != null ? restaurantResponse.getOpeningResponse().getOpen_now() : false;
                        String photoIsHere = restaurantResponse.getPhotosResponse() == null || restaurantResponse.getPhotosResponse().isEmpty() ? null :
                                restaurantResponse.getPhotosResponse().get(0).getPhotoReference();

                        float[] results = new float[1];
                        Location.distanceBetween(latitude, longitude, restaurantResponse.getGeometryResponse().getLocationResponse().getLat()
                                ,restaurantResponse.getGeometryResponse().getLocationResponse().getLng(), results);
                        float distanceResults = results[0];
                        int distance = (int) distanceResults;


                        Restaurant restaurant = new Restaurant(restaurantResponse.getPlace_id(),
                                restaurantResponse.getName(),
                                restaurantResponse.getGeometryResponse().getLocationResponse().getLat(),
                                restaurantResponse.getGeometryResponse().getLocationResponse().getLng(),
                                photoIsHere,
                                restaurantResponse.getVicinity(),
                                isOpen,
                                restaurantResponse.getRating(),
                                distance);

                        restaurants.add(restaurant);
                    }
                    return restaurants;
                })


                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void fetchOneRestaurant(LatLng latLng, String id,Float rating) {
        streamFetchOneRestaurantResponse(latLng,id,rating)
                .subscribeWith(new DisposableObserver<Restaurant>() {

                    @Override
                    public void onNext(@NonNull Restaurant restaurant) {
                        oneResult.setValue(restaurant);




                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("MAMAN", "On error one rest");

                    }

                    @Override
                    public void onComplete() {
                        Log.d("JUSTIN", "On complete");

                    }
                });
    }


    public  Observable<Restaurant>  streamFetchOneRestaurantResponse(LatLng latLng,String id,Float rating) {



        RestaurantService restaurantService = RetrofitClient.getRetrofit().create(RestaurantService.class);
        return restaurantService.getOneRestaurantByIdResponse(BuildConfig.RR_KEY, id)
                .map(resultOneResponse -> {

                    Log.d("Grayce", resultOneResponse.getResult().getName());



                        Boolean isOpen = resultOneResponse.getResult().getOpeningResponse() != null ? resultOneResponse.getResult().getOpeningResponse().getOpen_now() : false;
                        String photoIsHere = resultOneResponse.getResult().getPhotosResponse() == null ||  resultOneResponse.getResult().getPhotosResponse().isEmpty() ? null :
                                resultOneResponse.getResult().getPhotosResponse().get(0).getPhotoReference();




                         /*  float[] results = new float[1];
                            Location.distanceBetween(latitude, longitude, restaurantResponse.getGeometryResponse().getLocationResponse().getLat()
                                    ,restaurantResponse.getGeometryResponse().getLocationResponse().getLng(), results);
                            float distanceResults = results[0];
                            int distance = (int) distanceResults;
*/



                    return new Restaurant(resultOneResponse.getResult().getPlace_id(),
                            resultOneResponse.getResult().getName(),
                            resultOneResponse.getResult().getGeometryResponse().getLocationResponse().getLat(),
                            resultOneResponse.getResult().getGeometryResponse().getLocationResponse().getLng(),
                            photoIsHere,
                            resultOneResponse.getResult().getVicinity(),
                            isOpen,
                            rating,
                            0,
                            resultOneResponse.getResult().getFormatted_phone_number(),
                            resultOneResponse.getResult().getWebsite());


                })

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

}

