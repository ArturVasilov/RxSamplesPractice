package ru.samples.itis.rxarhictecture;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        RxView.clicks(fab)
                .subscribe(aVoid -> {
                    fab.setVisibility(View.GONE);
                });

        EditText editText = new EditText(this);
        RxTextView.textChanges(editText)
                .subscribe(charSequence -> {
                    Toast.makeText(this, charSequence, Toast.LENGTH_LONG).show();
                });
    }

}


