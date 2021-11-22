package com.nitesh.dubaiinterview.Network;

public interface InternetReceiverSubscriber {

    /**
     * get called after internet connection gets on
     */
    void onInternetConnected();

    /**
     * get called after internet connection goes off
     */
    void onInternetDisconnected();
}