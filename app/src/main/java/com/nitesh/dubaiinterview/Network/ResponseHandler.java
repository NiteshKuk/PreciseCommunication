package com.nitesh.dubaiinterview.Network;

import com.nitesh.dubaiinterview.Commondata;

public interface ResponseHandler {
    void onSuccess(String response,String status, Commondata.ApiType type);

    void onError(String message, Commondata.ApiType type);

    void hideProgressInfo();



}