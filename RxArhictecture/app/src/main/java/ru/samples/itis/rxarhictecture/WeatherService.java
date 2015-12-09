package ru.samples.itis.rxarhictecture;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * @author Artur Vasilov
 */
public interface WeatherService {

    @GET("data/2.5/weather")
    Observable<Weather> weather(@Query("q") String query,
                          @Query("appid") String apiKey);

}


