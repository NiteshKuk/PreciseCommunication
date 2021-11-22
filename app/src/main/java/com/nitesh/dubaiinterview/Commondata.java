package com.nitesh.dubaiinterview;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public interface Commondata {
    public static String mobAppversion(Context context)  {
        PackageInfo pInfo = null;
        try {
            pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return pInfo.versionName;
    }
    enum ApiType{
        CHECK_DEVICE,REGISTER,LOGIN,REFRESH_TOKEN,STATUS_IMAGE,LOGOUT,FORGOT_PASSWORD,FORGOT_RESET_KEY,SET_STATUS_IMAGE,SET_IMAGE,GET_IMAGE,USERS,USER_DETAIL,USER_ADD_UPDATE,RESET_PASSWORD,USER_DELETE,
        ADD_NOTICE,ALL_NOTICE,USER_SEEN_NOTICE,ADMIN_SEEN_NOTICE,USER_NOTICE,USER_DETAIL_UPDATE,FCM,GET_FCM,REGISTER_BUILDING,GET_REGISTERED_BUILDING,REGISTER_BUILDING_WITH_DETAILS,
        SET_PASSWORD, ADMIN_GET_REQUEST_CHANGE, SEND_USER_REQUEST_CHANGE,REQUESTED_CHANGE, ADMIN_REQUEST_REJECTED,LOGIN_W_MPIN,CREATE_MPIN,FORGOT_MPIN,FORGOT_MPIN_KEY,USER_REGISTER,
        DASHBOARD,DASHBOARD_APPROVE,USER_BILL_UPDATE,CONTACT_US,

        //Complaints
        ALL_COMPLAINTS,CREATE_COMPLAINTS,UPDATE_COMPLAINTS,DELETE_COMPLAINTS,CLOSED_COMPLAINT,OPEN_COMPLAINT,

        //Rate App
        RATE_APP, RATE_APP_DETAIL,

        //AUTO LOGIN

        AUTO_LOGIN,SET_AUTO_LOGIN,

        //Device Detail

        DEVICE_DETAIL,

        //Events
        ADD_EVENT,DELETE_EVENT,UPDATE_EVENT,ALL_EVENTS,ADD_ATTENDIE,
    }

    enum ErrorApiType{
        CHECKSUM, CHANNEL_VALIDATION, IDLE_USER, LONG_TIMEOUT, UNHANDLED, INTERNAL_SERVER_ERROR, NONE, NO_INTERNET,
        E010_SSL_HANDSHAKE_TIMEOUT, E012_SSL_HANDSHAKE_TIMEOUT, E014_SSL_READ_ERROR, E011_EOF, E009_CONNECTION_TIMEOUT, INSECURE, E013_VALIDATIONS_FAILED, UNHANDLED_CUSTOM_MSG, E016_API_RESP_CODE_OTHER_THAN_200;

    }

    public class Error {
        public static final String COMMON_PART_2 = "There seems to be a problem with your request. Please retry in some time.";
        public static final String RETRY_TEMP = COMMON_PART_2;
        public static final String E001 = COMMON_PART_2 + "";// (E002)";
        public static final String E002 = COMMON_PART_2 + " (E099-";
        public static final String LONG_INACTIVITY = "We are taking you to Login screen, please wait.";
        public static final String BEFORE_30_DAYS_CERT_EXP = "Hi, there is a new version of this app with enhanced functionalities. Please download the latest version";
        public static final String LONG_INACTIVITY_IDLE = "IDLE Time Observed We are taking you to Login screen, please wait.";
        // @PKDec242018
        // As shared by Pramit, Dec 24, 2018
        // https://mail.google.com/mail/u/0/#inbox/FMfcgxwBTjzqJSLwsWXwRRhxXxqLdMhM
        public static final String COMMON_PART_1 = "Sorry! It seems there was a problem in connection. Please check your network-Wifi/Cellular and retry. If problem persists, please retry in some time. ";
        public static final String E009 = COMMON_PART_1 + "(E009)";
        public static final String E010 = COMMON_PART_1 + "(E010)";
        public static final String E011 = COMMON_PART_2 + "(E011)";
        public static final String E012 = COMMON_PART_1 + "(E012)";
        public static final String E013 = COMMON_PART_2 + "(E013)";
        public static final String E014 = COMMON_PART_1 + "(E014)";
        public static final String E015 = COMMON_PART_2 + "(E015)";
        public static final String E016 = COMMON_PART_2 + "(E016)";
        public static final String _15 = "15"; // self assigned this code for Channel Validation Failure
    }
    enum OkButtonHandle{
        SOCKET_EXCEPTION,CONTINUE_TOKEN,DISMISS_TOKEN,CONTINUE_UPDATE_VERSION,CANCEL_UPDATE_VERSION,CONTINUE,DISCARD,CONTINUE_MPIN
    }
    enum Notice{
        CREATE_NOTICE,SEEN_NOTICE,ADMIN,USER
    }

    class DrawerConfig{
        public static final int POS_REGISTER = 0;
        public static final int POS_USER = 1;
        public static final int POS_BILLS = 2;
        public static final int POS_ADD_NOTICE = 3;
        public static final int POS_NOTICE = 4;
        public static final int POS_REQUEST_CHANGE = 5;
        public static final int POS_COMPLAINTS = 6;
        public static final int POS_EVENTS = 7;
        public static final int POS_PROFILE = 8;
        public static final int POS_RESET_PASS = 9;
        public static final int POS_CONTACT_US = 10;
        public static final int POS_RATE_APP = 11;
        public static final int POS_LOGOUT = 13;

        public static final int POS_REGISTER_TRUE = 0;
        public static final int POS_USER_TRUE = 1;
        public static final int POS_BILLS_TRUE = 2;
        public static final int POS_NOTICE_TRUE = 3;
        public static final int POS_REQUEST_CHANGE_TRUE = 4;
        public static final int POS_COMPLAINTS_TRUE = 5;
        public static final int POS_EVENTS_TRUE = 6;
        public static final int POS_PROFILE_TRUE = 7;
        public static final int POS_RESET_PASS_TRUE = 8;
        public static final int POS_CONTACT_US_TRUE = 9;
        public static final int POS_RATE_APP_TRUE = 10;
        public static final int POS_LOGOUT_TRUE = 12;

    }
}
