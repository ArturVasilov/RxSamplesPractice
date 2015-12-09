package itis.homework.parallelrequests;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.StringRes;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * @author Artur Vasilov
 */
public class RequestStatusView extends LinearLayout {

    private ProgressBar mProgressBar;
    private TextView mRequestView;

    public RequestStatusView(Context context) {
        super(context);
        inflate(context, R.layout.request_status_view, this);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mRequestView = (TextView) findViewById(R.id.textView);
    }

    public void error(@StringRes int textId) {
        mProgressBar.setVisibility(INVISIBLE);
        mRequestView.setText(textId);
        mRequestView.setTextColor(Color.RED);
    }

    public void loaded(@StringRes int textId) {
        mProgressBar.setVisibility(INVISIBLE);
        mRequestView.setText(textId);
        mRequestView.setTextColor(Color.GREEN);
    }

    public void loading(@StringRes int textId) {
        mProgressBar.setVisibility(VISIBLE);
        mRequestView.setText(textId);
        mRequestView.setTextColor(Color.BLUE);
    }

    public void waiting(@StringRes int textId) {
        mProgressBar.setVisibility(INVISIBLE);
        mRequestView.setText(textId);
        mRequestView.setTextColor(Color.GRAY);
    }
}
