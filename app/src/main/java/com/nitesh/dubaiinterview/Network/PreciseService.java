package com.nitesh.dubaiinterview.Network;

import android.content.Context;


import com.airbnb.lottie.BuildConfig;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import okhttp3.CertificatePinner;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.nitesh.dubaiinterview.BuildConfig.END_POINT;


public class PreciseService {

    /******************************************************************************/

    public static Context context;
    private static PreciseApi sService;

    /**
     * Configure the OkHttp client and initialize the retrofit.
     *
     * @return
     */
    public static PreciseApi getPreciseService() {

        if (sService == null) {

            OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();

            /**
             *
             * Enable the Network Logsa
             *
             */


            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);


            CertificatePinner.Builder builder = new CertificatePinner.Builder();
            if (BuildConfig.FLAVOR.equalsIgnoreCase("uat")) {
                // SHA for new UAT certificate in effect from Feb 20, 2019
                /*builder.add(HOST, "HERE WILL BE CERT KEY")
                        .add(HOST,"HERE WILL BE CERT KEY");

                /**
                 * @author NITESH
                */

//                builder.add(HOST, "sha256/9Be8XsIaVGfzPV9637KFNVKRynbsukH72dVzvprdwUo=");
            }
            CertificatePinner certificatePinner = builder.build();

            /**
             * Set the SHA256 hash obtained from the Certificate
             *
             * Run this command to get the SHA256 fingerprint
             * openssl s_client -connect api.github.com:443 | openssl x509 -pubkey -noout | openssl rsa -pubin -outform der | openssl dgst -sha256 -binary | openssl enc -base64
             *
             *
             */

            /**
             * Force the connection to use only TLS v.1.2 avoiding the fallback to older version to avoid vulnerabilities
             *
             */
            final ConnectionSpec.Builder connectionSpec =
                    new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS);
            connectionSpec.tlsVersions(TlsVersion.TLS_1_2).build();


            /**
             * Enable TLS specific version V.1.2
             * Issue Details : https://github.com/square/okhttp/issues/1934
             */

            TLSSocketFactory tlsSocketFactory = null;
            try {
                tlsSocketFactory = new TLSSocketFactory();
            }
            catch (KeyManagementException e) {
//                e.printStackTrace();
            }
            catch (NoSuchAlgorithmException e) {
//                e.printStackTrace();
            }

            final OkHttpClient client;


            //TODO temp disable sslSocketFactory
            if (BuildConfig.DEBUG) {

                client = new OkHttpClient.Builder()
                        // Uncomment certificatePinner for enabling PublicKeyPinning
                        .certificatePinner(certificatePinner) // public key pinning
                        //.sslSocketFactory(getPinnedCertSslSocketFactory(context), tlsSocketFactory.systemDefaultTrustManager()) // certificate pinning
                        .addNetworkInterceptor(interceptor)
                        .readTimeout(90, TimeUnit.SECONDS)
                        .connectTimeout(90, TimeUnit.SECONDS)
                        .retryOnConnectionFailure(true)//rety if socket closed
                        .connectionSpecs(Collections.singletonList(connectionSpec.build()))
                        .build();
            }
            else {
                try {
                    tlsSocketFactory = new TLSSocketFactory();
                }
                catch (KeyManagementException e) {
                    //e.printStackTrace();
                }
                catch (NoSuchAlgorithmException e) {
                    // e.printStackTrace();
                }
                client = new OkHttpClient.Builder()
                        .certificatePinner(certificatePinner)/*For Public Key Pinning*/
                        .addNetworkInterceptor(interceptor)
                        //.sslSocketFactory(getPinnedCertSslSocketFactory(context), tlsSocketFactory.systemDefaultTrustManager())
                        .readTimeout(90, TimeUnit.SECONDS)
                        .connectTimeout(90, TimeUnit.SECONDS)
                        .connectionSpecs(Collections.singletonList(connectionSpec.build()))
                        .retryOnConnectionFailure(true)
                        .build();
            }

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(END_POINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(client)
                    .build();
            sService = retrofit.create(PreciseApi.class);

        }
        return sService;
    }
    public static void setContext(Context acontext) {
        context = acontext;
    }
}