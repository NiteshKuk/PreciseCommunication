package com.nitesh.dubaiinterview.Network;


import com.nitesh.dubaiinterview.Commondata;

import org.json.JSONException;
import org.json.JSONObject;

public class ResponseCodeExtractor {

    public static ResponseCodeExtractor getInstance() {
        return Loader.codeExtractor;
    }

    String[] extractMessageAndRespCode(Commondata.ApiType apiType, JSONObject j) {
        String[] result = new String[3];
        try {
            result[0] = j.has("status") ? j.getString("status") : "";
            result[1] = j.has("message") ? j.getString("message") : "";
            result[2] = j.has("errorCode") ? j.getString("errorCode") : "";

            if(j.has("status"))
                result[0] = j.has("status") ? j.getString("status") : "";
            if(j.has("message"))
                result[1] = j.has("message") ? j.getString("message") : "";
            if (j.has(""))
                result[2] = j.has("errorCode") ? j.getString("errorCode") : "";
        }
        catch (JSONException e) {
            result = new String[]{"", ""};
        }
        return result;
    }

    private static class Loader {
        private static final ResponseCodeExtractor codeExtractor = new ResponseCodeExtractor();
    }
}
