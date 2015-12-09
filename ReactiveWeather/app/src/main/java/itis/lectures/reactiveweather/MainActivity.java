package itis.lectures.reactiveweather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private static final String[] WEATHER_ITEMS = {
            "Kazan", "Moscow", "Paris", "London",
            "Washington", "Madrid", "Rome", "Berlin"
    };

    private WeatherAdapter mWeatherAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mWeatherAdapter = new WeatherAdapter(this);
        recyclerView.setAdapter(mWeatherAdapter);
    }

    /*
        TODO : add weather forecast for all weather; use any rx architecture you like

        Use ApiFactory.weatherFromQuery() to create weather observable with string
     */

}
