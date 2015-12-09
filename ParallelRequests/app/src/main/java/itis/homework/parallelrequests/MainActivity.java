package itis.homework.parallelrequests;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * @author Artur Vasilov
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Fragment fragment = new MainFragment();
        getFragmentManager().beginTransaction()
                .replace(R.id.content_main, fragment, MainFragment.class.getName())
                .commit();
    }

}
