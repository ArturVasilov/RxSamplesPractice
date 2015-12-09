package itis.homework.parallelrequests;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import itis.homework.parallelrequests.app.AppDelegate;
import itis.homework.parallelrequests.network.RequestsService;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * @author Artur Vasilov
 */
public class MainFragment extends Fragment {

    private RequestsService mRequestsService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRequestsService = AppDelegate.get(getActivity()).getRequestsService();
        mRequestsService.reset();

        Observable<Boolean> config = mRequestsService
                .config()
                .subscribeOn(Schedulers.newThread());

        Observable<Boolean> auth = mRequestsService
                .auth()
                .subscribeOn(Schedulers.newThread());

        Observable<Boolean> friends = mRequestsService
                .friends()
                .subscribeOn(Schedulers.newThread());

        Observable<Boolean> groups = mRequestsService
                .groups()
                .subscribeOn(Schedulers.newThread());

        Observable<Boolean> posts = mRequestsService
                .posts()
                .subscribeOn(Schedulers.newThread());

        Observable<Boolean> messagesAndPhotos = mRequestsService
                .messages()
                .concatWith(mRequestsService.photos())
                .subscribeOn(Schedulers.newThread());

         /*
            TODO : put your code somewhere here
            1) Combine all observables to match the rule 2
            2) Be sure to user correct order of request:
            2.1) Photos request must be executed only after messages request has finished
            2.2) Friends, posts, messages and group request must be executed only after
                    auth and config requests has finished

            2.1 is already implemented
         */

        Observable<Boolean> observable = Observable.empty();

        //TODO : create observable from previously created observables to execute all requests in needed order

        observable.subscribe();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
