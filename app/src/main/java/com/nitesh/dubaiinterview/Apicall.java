package com.nitesh.dubaiinterview;

import android.content.Context;

import com.nitesh.dubaiinterview.Interface.CommonResponseInterface;
import com.nitesh.dubaiinterview.Interface.MainResponse;
import com.nitesh.dubaiinterview.Network.ErrorHandlerCallback;
import com.nitesh.dubaiinterview.Network.ErrorSingleButtonDialog;
import com.nitesh.dubaiinterview.Network.PreciseApi;
import com.nitesh.dubaiinterview.Network.PreciseService;
import com.nitesh.dubaiinterview.Network.ResponseHandler;

public class Apicall implements MainResponse, ResponseHandler, ErrorHandlerCallback,
        ErrorSingleButtonDialog.OnDialogHandleClick{
    private static PreciseApi hsmApi;
    private Context mContext;
    private CommonResponseInterface commonResponseInterface;

    public Apicall(Context mContext, CommonResponseInterface commonResponseInterface) {
        this.mContext = mContext;
        PreciseService.setContext(this.mContext);
        hsmApi = PreciseService.getPreciseService();
        this.commonResponseInterface = commonResponseInterface;
    }

    @Override
    public void callApi() {

    }

    @Override
    public void errorRetryNetworkData() {

    }

    @Override
    public void processOkButton(Commondata.OkButtonHandle okButtonHandle) {

    }

    @Override
    public void processCancelButton(Commondata.OkButtonHandle okButtonHandle) {

    }

    @Override
    public void onSuccess(String response, String status, Commondata.ApiType type) {

    }

    @Override
    public void onError(String message, Commondata.ApiType type) {

    }

    @Override
    public void hideProgressInfo() {

    }
}
