package com.nitesh.dubaiinterview.Network;

import com.google.gson.JsonObject;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface PreciseApi {
    @POST("getdeviceversion")
    Call<ResponseBody> deviceCheck();

    @POST("login")
    Call<ResponseBody> login(@Body JsonObject login);

    @POST("register")
    Call<ResponseBody> register(@Body JsonObject register);

    @POST("refreshtoken")
    Call<ResponseBody> refreshToken(@Header("Authorization") String access_token, @Body JsonObject refresh);

    @POST("logout")
    Call<ResponseBody> logout(@Header("Authorization") String access_token, @Header("Content-Type") String content_type, @Body JsonObject logout);

    @GET("getimagestatus")
    Call<ResponseBody> statusImage(@Header("Authorization") String access_token);

    @POST("alluser")
    Call<ResponseBody> users(@Header("Authorization") String access_token,@Body JsonObject users);

    @POST("usercharges")
    Call<ResponseBody> usersDetail(@Header("Authorization") String access_token,@Body JsonObject userdetail);


    @POST("addupdateusercharges")
    Call<ResponseBody> addUpdateUserDetail(@Header("Authorization") String access_token,@Body JsonObject userdetail);

    @POST("userdelete")
    Call<ResponseBody> userdelete(@Header("Authorization") String access_token,@Body JsonObject userdelete);

    @POST("forgotpassword")
    Call<ResponseBody> forgotPassword(@Body JsonObject forgot);

    @POST("forgotresetkey")
    Call<ResponseBody> forgotResetKey(@Body JsonObject forgot);

    @Multipart
    @POST("image")
    Call<ResponseBody> Image(
            @Header("id") String id,
            @Header("name") String status,
            @Part MultipartBody.Part image);
    /*@Multipart
    @POST("statusimage")
    Call<ResponseBody> statusImageWithImage(
            @Header("Authorization") String access_token,
            @Header("id") RequestBody id,
            @Header("name") RequestBody status,
            @Part MultipartBody.Part image);*/

    @Multipart
    @POST("statusimage")
    Call<ResponseBody> statusImageWithoutImage(
            @Header("Authorization") String access_token,
            @Part("id") RequestBody id,
            @Part("status") RequestBody status
    );

    @GET("image")
    Call<ResponseBody> getImage();
    @POST("resetpassword")
    Call<ResponseBody> resetPassword(@Header("Authorization") String access_token,@Body JsonObject resetPassword);

    @POST("addnotice")
    Call<ResponseBody> addNotice(@Header("Authorization") String access_token,@Body JsonObject addNotice);

    @POST("allnotice")
    Call<ResponseBody> allNotice(@Header("Authorization") String access_token,@Body JsonObject addNotice);

    @POST("userseennotice")
    Call<ResponseBody> userseenNotice(@Header("Authorization") String access_token,@Body JsonObject addNotice);

    @POST("adminseennotice")
    Call<ResponseBody> adminseenNotice(@Header("Authorization") String access_token,@Body JsonObject addNotice);

    @POST("usernotice")
    Call<ResponseBody> userNotice(@Header("Authorization") String access_token,@Body JsonObject addNotice);

    @POST("userupdate")
    Call<ResponseBody> userupdate(@Header("Authorization") String access_token,@Body JsonObject addNotice);

    @POST("userupdatecharges")
    Call<ResponseBody> userupdatecharges(@Header("Authorization") String access_token,@Body JsonObject addNotice);

    @POST("fcm")
    Call<ResponseBody> fcm(@Header("Authorization") String access_token,@Body JsonObject addNotice);

    @POST("getfcmtoken")
    Call<ResponseBody> gettokenfcm(@Header("Authorization") String access_token,@Body JsonObject addNotice);

    @POST("registerbuilding")
    Call<ResponseBody> registerbuilding(@Body JsonObject registerbldg);

    @POST("getregisterbuilding")
    Call<ResponseBody> getregisterbuilding();

    @POST("registeradmin")
    Call<ResponseBody> registerbuildingwithDetails(@Body JsonObject registerbldgWDetails);

    @POST("setpassword")
    Call<ResponseBody> setPassword(@Body JsonObject setpass);

    @POST("getrequestdetail")
    Call<ResponseBody> getRequestdetail(@Header("Authorization") String access_token,@Body JsonObject requestDetail);

    @POST("requestchangedetail")
    Call<ResponseBody> sendRequestdetail(@Header("Authorization") String access_token,@Body JsonObject requestDetail);

    @POST("getrequestuser")
    Call<ResponseBody> requesteddetail(@Header("Authorization") String access_token,@Body JsonObject requestDetail);

    @POST("creatempin")
    Call<ResponseBody> createMpin(@Body JsonObject requestDetail);

    @POST("loginmpin")
    Call<ResponseBody> loginMpin(@Body JsonObject requestDetail);

    @POST("forgotmpin")
    Call<ResponseBody> forgotMpin(@Body JsonObject requestDetail);

    @POST("forgotmpinkey")
    Call<ResponseBody> forgotResetkeyMpin(@Body JsonObject requestDetail);

    @POST("createComplaint")
    Call<ResponseBody> createComplaint(@Header("Authorization") String access_token,@Body JsonObject requestDetail);

    @POST("getComplaint")
    Call<ResponseBody> getComplaint(@Header("Authorization") String access_token,@Body JsonObject requestDetail);

    @POST("updateComplaint")
    Call<ResponseBody> updateComplaint(@Header("Authorization") String access_token,@Body JsonObject requestDetail);

    @POST("deleteComplaint")
    Call<ResponseBody> deleteComplaint(@Header("Authorization") String access_token,@Body JsonObject requestDetail);

    @POST("closedComplaint")
    Call<ResponseBody> closedComplaint(@Header("Authorization") String access_token,@Body JsonObject requestDetail);

    @POST("openComplaint")
    Call<ResponseBody> openComplaint(@Header("Authorization") String access_token,@Body JsonObject requestDetail);

    @POST("registeruser")
    Call<ResponseBody> registerUser(@Body JsonObject requestDetail);

    @POST("dashboard")
    Call<ResponseBody> dashboard(@Header("Authorization") String access_token,@Body JsonObject requestDetail);

    @POST("userapproved")
    Call<ResponseBody> userapprove(@Header("Authorization") String access_token,@Body JsonObject requestDetail);

    @POST("rateapp")
    Call<ResponseBody> rateApp(@Header("Authorization") String access_token,@Body JsonObject requestDetail);

    @POST("rateappdetail")
    Call<ResponseBody> rateAppDetail(@Header("Authorization") String access_token);

    @POST("contactus")
    Call<ResponseBody> conatctus(@Header("Authorization") String access_token,@Body JsonObject requestDetail);

    @POST("autologin")
    Call<ResponseBody> autoLogin(@Body JsonObject requestDetail);

    @POST("setautologin")
    Call<ResponseBody> setAutoLogin(@Body JsonObject requestDetail);

    @POST("devicedetail")
    Call<ResponseBody> deviceDetail(@Body JsonObject requestDetail);

    @POST("addEvent")
    Call<ResponseBody> eventAdd(@Body JsonObject requestDetail);

    @POST("updateEvent")
    Call<ResponseBody> eventUpdate(@Body JsonObject requestDetail);

    @POST("allEvent")
    Call<ResponseBody> eventslist(@Header("Authorization") String access_token,@Body JsonObject requestDetail);

    @POST("deleteEvent")
    Call<ResponseBody> eventDelete(@Body JsonObject requestDetail);

    @POST("addAttendieEvent")
    Call<ResponseBody> eventAttendie(@Body JsonObject requestDetail);
}
