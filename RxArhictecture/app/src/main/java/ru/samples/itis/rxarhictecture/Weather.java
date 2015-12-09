package ru.samples.itis.rxarhictecture;

import com.google.gson.annotations.SerializedName;

/**
 * @author Artur Vasilov
 */
public class Weather {

    @SerializedName("name")
    private String mName;

    @SerializedName("main")
    private MainParams mParams;

    private class MainParams {

        @SerializedName("humidity")
        private float mHumidity;

        @SerializedName("pressure")
        private float mPressure;

        @SerializedName("temp")
        private float mTemperature;

    }

    public String getName() {
        return mName;
    }
}
