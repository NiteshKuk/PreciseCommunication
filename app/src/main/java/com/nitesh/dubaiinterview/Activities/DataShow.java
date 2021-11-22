package com.nitesh.dubaiinterview.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;
import com.nitesh.dubaiinterview.CustomPackage.TypefacedTextView;
import com.nitesh.dubaiinterview.DataClass;
import com.nitesh.dubaiinterview.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class DataShow extends BaseActivity {
    @BindView(R.id.tv_currency)
    TypefacedTextView tv_currency;
    @BindView(R.id.tv_last_trade_price)
    TypefacedTextView tv_last_trade_price;
    @BindView(R.id.tv_lowest_ask)
    TypefacedTextView tv_lowest_ask;
    @BindView(R.id.tv_per_change)
    TypefacedTextView tv_per_change;
    @BindView(R.id.tv_base_currency)
    TypefacedTextView tv_base_currency;
    @BindView(R.id.tv_quote_currency)
    TypefacedTextView tv_quote_currency;
    @BindView(R.id.tv_is_frozen)
    TypefacedTextView tv_is_frozen;
    @BindView(R.id.tv_highest_trade_price)
    TypefacedTextView tv_highest_trade_price;
    @BindView(R.id.tv_lowest_trade_price)
    TypefacedTextView tv_lowest_trade_price;

    @BindView(R.id.tv_whole)
    TypefacedTextView tv_whole;

    private OkHttpClient client;
    private WebSocket webSocket;
    private DataClass dataClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_show);
        ButterKnife.bind(this);
        client = new OkHttpClient();
        start();
    }

    public class EchoWebSocketListener extends WebSocketListener {
        private static final int NORMAL_CLOSURE_STATUS = 1000;

        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            output("OPENED");
            DataShow.this.webSocket = webSocket;
        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {
            output("Receiving : " + text);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                    dataClass = new DataClass(text);
//                    StringBuilder stringBuilder = new StringBuilder();
//                    stringBuilder.append(dataClass.getData());
                    tv_whole.setText(text);
                }
            });
            JSONObject jsonObject = new JSONObject();
            int value = 0;
            try {
                jsonObject.put("command", "subscribe");
                jsonObject.put("channel", 1002);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            webSocket.send(jsonObject.toString());

            JSONObject jo = new JSONObject();
            try {
                jo.put(String.valueOf(value), text);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            webSocket.close(NORMAL_CLOSURE_STATUS, "Done");
                            output("TOTAL:Receiving : " + jo);
                        }
                    }, 2000);
                }
            });
            POJOCLASS pojoclass = new Gson().fromJson(jo.toString(), POJOCLASS.class);
            Log.e("DATACLASS", pojoclass.get0());

        }

        @Override
        public void onMessage(WebSocket webSocket, ByteString bytes) {
            output("Receiving bytes : " + bytes.hex());
        }

        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            webSocket.close(NORMAL_CLOSURE_STATUS, null);
            output("Closing : " + code + " / " + reason);
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {

            output("Error : " + t.getMessage());
        }

    }

    private void output(final String txt) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.e("Login", txt);
            }
        });
    }

    private void start() {
        Request request = new Request.Builder().url("wss://api2.poloniex.com").build();
        EchoWebSocketListener listener = new EchoWebSocketListener();
        WebSocket ws = client.newWebSocket(request, listener);
        client.dispatcher().executorService().shutdown();
    }
}