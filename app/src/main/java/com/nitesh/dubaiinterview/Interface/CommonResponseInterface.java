package com.nitesh.dubaiinterview.Interface;


import com.nitesh.dubaiinterview.Commondata;

public interface CommonResponseInterface {
    void successResponse(String response, Commondata.ApiType serviceType);
    void errorResponse(String response, Commondata.ApiType serviceType);
    void dismissLoader();
}
