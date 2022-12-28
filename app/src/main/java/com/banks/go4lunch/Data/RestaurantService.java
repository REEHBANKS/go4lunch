package com.banks.go4lunch.Data;

import com.banks.go4lunch.Model.jsonResponse.OneRestaurantResponse;
import com.banks.go4lunch.Model.jsonResponse.RestaurantResponse;
import com.banks.go4lunch.Model.jsonResponse.AllRestaurantResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestaurantService  {

    @GET("maps/api/place/nearbysearch/json?radius=1500&type=restaurant")
    Observable<AllRestaurantResponse> getAllRestaurantsResponse(@Query("key") String api,
                                                                @Query("location") String location ) ;

    @GET("maps/api/place/details/json?")
    Observable<OneRestaurantResponse> getOneRestaurantByIdResponse(@Query("key") String api,
                                                                   @Query("place_id") String id);

}
