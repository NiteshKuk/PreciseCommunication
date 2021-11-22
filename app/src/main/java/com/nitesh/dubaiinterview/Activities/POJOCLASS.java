package com.nitesh.dubaiinterview.Activities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class POJOCLASS implements Serializable {
    @SerializedName("0")
    @Expose
    private String _0;

    public String get0() {
        return _0;
    }

    public void set0(String _0) {
        this._0 = _0;
    }
}
