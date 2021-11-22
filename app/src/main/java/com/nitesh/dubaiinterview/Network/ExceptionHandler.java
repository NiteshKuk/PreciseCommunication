package com.nitesh.dubaiinterview.Network;

import android.annotation.SuppressLint;
import android.content.Context;

import com.nitesh.dubaiinterview.Commondata;
import com.nitesh.dubaiinterview.R;

import static com.nitesh.dubaiinterview.Commondata.OkButtonHandle.SOCKET_EXCEPTION;


public class ExceptionHandler implements ErrorSingleButtonDialog.OnDialogHandleClick {
    private static final String TAG = "ExceptionHandler";
    private String message;
    private Commondata.ApiType apiType;
    private Context context;
    private Commondata.ErrorApiType errorType;
    private String respCode;


    private ExceptionHandler() {
    }

    public static ExceptionHandler getInstance() {
        return Loader.INSTANCE;
    }

    @SuppressWarnings("ConstantConditions")
    public void build() {
        ErrorSingleButtonDialog errorSingleButtonDialog = new ErrorSingleButtonDialog(context,this,message + " (" + errorType + ")" ,context.getResources().getString(R.string.okBtn),false,SOCKET_EXCEPTION,SOCKET_EXCEPTION);
        errorSingleButtonDialog.show();
    }

    public ExceptionHandler setMessage(String msg) {
        message = msg;
        return this;
    }

    public ExceptionHandler setApiType(Commondata.ApiType apiType) {
        this.apiType = apiType;
        return this;
    }

    public ExceptionHandler setErrorType(Commondata.ErrorApiType errorType) {
        this.errorType = errorType;
        return this;
    }

    public Context getContext() {
        return context;
    }

    public ExceptionHandler setContext(Context context) {
        this.context = context;
        return this;
    }

    public ExceptionHandler setErrorCode(String respCode) {
        this.respCode = respCode;
        return this;
    }


    @Override
    public void processOkButton(Commondata.OkButtonHandle okButtonHandle) {
    }

    @Override
    public void processCancelButton(Commondata.OkButtonHandle okButtonHandle) {

    }

    /**
     * This class is synchronised by default
     * https://medium.com/@oznusem/the-right-way-to-write-a-singleton-when-developing-for-android-or-any-multithreaded-environment-79782dd48f95
     */
    private static class Loader {
        @SuppressLint("StaticFieldLeak")
        static volatile
        ExceptionHandler INSTANCE = new ExceptionHandler();
    }
}
