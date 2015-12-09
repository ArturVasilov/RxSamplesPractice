package itis.homework.parallelrequests.network;

/**
 * @author Artur Vasilov
 */
public interface RequestsController {

    boolean tryRequest(RequestType requestType);

    void onRequestFinished(RequestType requestType);

    void reset();

}
