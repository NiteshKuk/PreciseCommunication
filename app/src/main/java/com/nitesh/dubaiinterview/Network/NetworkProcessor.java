package com.nitesh.dubaiinterview.Network;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.nitesh.dubaiinterview.CommonUtils;
import com.nitesh.dubaiinterview.Commondata;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.EOFException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.nitesh.dubaiinterview.Commondata.Error.E001;
import static com.nitesh.dubaiinterview.Commondata.Error.RETRY_TEMP;


public class NetworkProcessor implements ErrorHandlerCallback, InternetReceiverSubscriber {
    private static NetworkProcessor networkProcessor;
    private static Context context;
    //    private static SpotsDialog spotsDialog;
    private Call<ResponseBody> Req;
    private Commondata.ApiType apiType;
    private CommonUtils commonUtils;

    private NetworkProcessor() {
    }

    public static NetworkProcessor getInstance(Context context) {
        if (networkProcessor == null) {
            networkProcessor = new NetworkProcessor();
        }
        NetworkProcessor.context = context;
        return networkProcessor;
    }

    public void networkCall(Call<ResponseBody> request,
                            final ResponseHandler handler, Commondata.ApiType apiType) {
        this.apiType = apiType;
//        Request a = request.request();
//        a.url()
        this.Req = request;
        commonUtils = new CommonUtils(context);
        InternetConnectionDetector internetConnectionDetector =
                new InternetConnectionDetector(context);
        if (internetConnectionDetector.isConnectingToInternet()) {
            String traceName = "trace_" + apiType;


            String api =
                    getApiEndpoint();

//            Request a = Req.request();
//            a.url();
            Req.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    // region Handle response
                    handleResponse(call, response);
                    // end region
                }

                private void handleResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    String res;
                    final String api = getApiEndpoint();
                    final String time = String.valueOf(calculateTime(response));
                    try {
                        if (context == null || Req == null || Req.isCanceled() || call.isCanceled()) {
                            return;
                        } else {
                            res = response.body().string();

                            if (response.body() == null || response.body().toString().equalsIgnoreCase("{}")) {
                                handler.hideProgressInfo();
                                handler.onError("No Response " + response.code(), apiType);
                                // System.out.println("=====ERROR: stp2");
                                //PreciseLogs.logE("NetworkProcessor Error", "API ------> " + apiType + "\t" + "No response");
                                if (context != null && !((Activity) context).isFinishing()) {
                                    ExceptionHandler
                                            .getInstance()
                                            .setContext(context)
                                            .setApiType(apiType)
                                            .setErrorType(Commondata.ErrorApiType.UNHANDLED)
                                            .setErrorCode(null)
                                            .setMessage(null)
                                            .build();
                                }
                            } else if (response.body().toString().length() == 2 &&
                                    response.body().toString().equalsIgnoreCase("{}")) {
                                handler.hideProgressInfo();
                                handler.onError("Empty list", apiType);
                                if (context != null && !((Activity) context).isFinishing()) {
                                    ExceptionHandler
                                            .getInstance()
                                            .setContext(context)
                                            .setApiType(apiType)
                                            .setErrorType(Commondata.ErrorApiType.UNHANDLED)
                                            .setErrorCode(null)
                                            .setMessage(null)
                                            .build();
                                }
                            } else if (res != null && !res.trim().isEmpty()) {
                                try {
                                    JSONObject j = new JSONObject(res);
                                    String[] result = ResponseCodeExtractor.getInstance().extractMessageAndRespCode(apiType, j);
                                    String status = result[0];
                                    String msg = result[1];
                                    String errorCode = result[2];
                                    commonUtils.logV("#RespCode", status + " =====> " + msg);
                                    if (status.equalsIgnoreCase("00")){
                                        handler.onSuccess(res, status, apiType);
                                    }else{
                                        handler.onError(msg, apiType);
                                    }

                                } catch (JSONException e) {
                                    handler.hideProgressInfo();
                                    ExceptionHandler
                                            .getInstance()
                                            .setContext(context)
                                            .setApiType(apiType)
                                            .setErrorType(Commondata.ErrorApiType.UNHANDLED)
                                            .setErrorCode(null)
                                            .setMessage(null)
                                            .build();

                                }
                            } else {

                                if (!(response.body().contentLength() == -1) || !response.body().toString().isEmpty() || !response.body().equals("")) {
                                    handler.onSuccess(response.body() != null ? response.body().string() : "NULL", "notDetected", apiType);
                                } else {
                                    //PreciseLogs.logE("NetworkProcessor Error", "API ------> " + apiType + "\tEmpty response");
//                                    handler.onError("Empty Response", apiType);
                                    handler.hideProgressInfo();
                                    if (context != null && !((Activity) context).isFinishing()) {
                                        ExceptionHandler
                                                .getInstance()
                                                .setContext(context)
                                                .setApiType(apiType)
                                                .setErrorType(Commondata.ErrorApiType.UNHANDLED)
                                                .build();
                                    }
                                }
                            }


                        }
                    } catch (Exception e) {

                        if (context == null) {
                            return;
                        }
                        handler.hideProgressInfo();
                        commonUtils.logV("Error " + this.getClass().getSimpleName(), "" + e.getMessage() + " " + e.getCause());// e.printStackTrace();
//                        handler.onError(context.getResources().getString(R.string.something_went_wrong), apiType);
                        ExceptionHandler
                                .getInstance()
                                .setContext(context)
                                .setApiType(apiType)
                                .setErrorType(Commondata.ErrorApiType.UNHANDLED)
                                .setErrorCode(null)
                                .setMessage(RETRY_TEMP)
                                .build();
//                        handler.onError(RETRY_TEMP, apiType);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

//                spotsDialog.dismiss();
                    if (context == null || Req == null || Req.isCanceled() || call.isCanceled()) {
                        //request is cancelled, do nothing.
                        return;
                    }


                    @Nullable Commondata.ErrorApiType type = Commondata.ErrorApiType.UNHANDLED;
                    @Nullable String message = E001;
                    @Nullable String excp = null;

                    if (t instanceof ConnectException) {
                        type = Commondata.ErrorApiType.E009_CONNECTION_TIMEOUT;
                        message = Commondata.Error.E009;
                        excp = ConnectException.class.getSimpleName();
                    } else if (t instanceof SocketTimeoutException) {
                        if (isError(apiType)) {
                            type = Commondata.ErrorApiType.E010_SSL_HANDSHAKE_TIMEOUT;
                            message = Commondata.Error.E010;
                            excp = SocketTimeoutException.class.getSimpleName();
                        }
                    } else if (t instanceof EOFException) {
                        type = Commondata.ErrorApiType.E011_EOF;
                        message = Commondata.Error.E011;
                        excp = EOFException.class.getSimpleName();
                    }
                    // maintain sequence as SSLHandshakeException extends SSLException
                    else if (t instanceof SSLHandshakeException) {
                        String stackTrace = getStackTraceInfo(t);
                        if (stackTrace.contains("ssl handshake aborted")) {
                            type = Commondata.ErrorApiType.E012_SSL_HANDSHAKE_TIMEOUT;
                            message = Commondata.Error.E012;
                            excp = SSLHandshakeException.class.getSimpleName().concat(" : ssl handshake aborted");
                        } else if (stackTrace.contains("read error")) {
                            type = Commondata.ErrorApiType.E014_SSL_READ_ERROR;
                            message = Commondata.Error.E014;
                            excp = SSLHandshakeException.class.getSimpleName().concat(" : read error");
                        }
                    } else if (t instanceof SSLException) {
                        type = Commondata.ErrorApiType.E011_EOF;
                        message = Commondata.Error.E011;
                        excp = SSLException.class.getSimpleName();
                    } else if (t instanceof ExtCertPathValidatorException || // <-- Added @PKDec2420180352PM
                            // Already present --v
                            (TextUtils.isEmpty(t.getMessage()) && t.getMessage().contains("Certificate pinning failure!"))) {
                        type = Commondata.ErrorApiType.INSECURE;
                        message = "Insecure Connection\nNetwork is not secured to proceed";
                        excp = ExtCertPathValidatorException.class.getSimpleName();
                    }
                    handler.hideProgressInfo();
                    ExceptionHandler
                            .getInstance()
                            .setContext(context)
                            .setApiType(apiType)
                            .setErrorType(type)
                            .setErrorCode(null)
                            .setMessage(message)
                            .build();


                    String api = getApiEndpoint();
                }
            });

        } else {
            if (context == null || Req == null) {
                return;
            }
            commonUtils.showToast("No Internet Connection!");
            handler.hideProgressInfo();
            ExceptionHandler
                    .getInstance()
                    .setContext(context)
                    .setApiType(apiType)
                    .setErrorType(Commondata.ErrorApiType.NO_INTERNET)
                    .setErrorCode(null)
                    .setMessage("No Internet Connection")
                    .build();


        }
    }


    @Nullable
    private String getApiEndpoint() {
        return Req != null &&
                Req.request() != null &&
                Req.request().url() != null &&
                Req.request().url().pathSegments() != null &&
                Req.request().url().pathSegments().size() == 2
                ? Req.request().url().pathSegments().get(1)
                : null;
    }

    private long calculateTime(Response<ResponseBody> response) {
        try {
            return response.raw().receivedResponseAtMillis() - response.raw().sentRequestAtMillis();
        } catch (Exception e) {
            //e.printStackTrace();
            return 0;
        }
    }


    /**
     * Used when different APIs have same respCode for both, success and error.
     * Ex. DEVICE_REGISTRATION has respCode 00 when the device is registered for the first time,
     * next time onwards, its 01 and IS NOT an exception & is to be handled as successfull.
     */
    @NonNull
    private String getStackTraceInfo(Throwable t) {
        return t != null
                ? t.toString().trim().toLowerCase()
                : "";
    }

    private Commondata.ErrorApiType getErrorType(String res) {

        if (res.toLowerCase().contains("checksum")) {
            return Commondata.ErrorApiType.CHECKSUM;
        } else if (res.toLowerCase().contains("channel")) {
            return Commondata.ErrorApiType.CHANNEL_VALIDATION;
        }

        return Commondata.ErrorApiType.NONE;
    }



    private boolean isError(Commondata.ApiType apiType) {
        return false;
    }

    @Override
    public void errorRetryNetworkData() {

    }

    @Override
    public void onInternetConnected() {

    }

    @Override
    public void onInternetDisconnected() {

    }
}
