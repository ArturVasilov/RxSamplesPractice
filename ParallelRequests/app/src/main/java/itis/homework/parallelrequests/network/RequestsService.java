package itis.homework.parallelrequests.network;

import rx.Observable;

/**
 * @author Artur Vasilov
 */
public interface RequestsService {

    Observable<Boolean> config();

    Observable<Boolean> auth();

    Observable<Boolean> friends();

    Observable<Boolean> posts();

    Observable<Boolean> groups();

    Observable<Boolean> messages();

    Observable<Boolean> photos();

    void reset();

}
