package com.nitesh.dubaiinterview.Network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.net.InetAddress;

public class InternetConnectionDetector {

    private Context _context;

    public InternetConnectionDetector(Context context) {
        this._context = context;
    }

    public boolean isConnectingToInternet() {
        if (_context != null) {
            ConnectivityManager connectivity = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo[] info = connectivity.getAllNetworkInfo();
                if (info != null)
                    for (int i = 0; i < info.length; i++)
                        if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                          /*if(isInternetAvailable()){*/

                            return true;
                         /* }else {
                              return false;
                          }*/
                        }

            }
        }
        return false;
    }

    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com"); //You can replace it with your name
            return !ipAddr.equals("");

        } catch (Exception e) {
            //showLog("Error " + this.getClass().getSimpleName(), "" + e.getMessage() + " " + e.getCause());// e.printStackTrace();
            return false;
        }
    }

}