package com.nitesh.dubaiinterview.CustomPackage;

import android.content.Context;
import android.graphics.Typeface;

import java.util.Hashtable;

public class TypefaceLoader {

    private static final String TAG = "TypefaceLoader";

    private static final Hashtable<String, Typeface> cache = new Hashtable<String, Typeface>();

    public static Typeface get(Context context, String assetPath) {
        synchronized (cache) {
            if (!cache.containsKey(assetPath)) {
                try {
                    Typeface t = Typeface.createFromAsset(context.getAssets(), assetPath);
                    cache.put(assetPath, t);
                } catch (Exception e) {
                    //showLog(TAG, "Could not get typeface '" + assetPath + "' because " + e.getMessage());
                    return get(context, assetPath);
                }
            }
            return cache.get(assetPath);
        }
    }
}
