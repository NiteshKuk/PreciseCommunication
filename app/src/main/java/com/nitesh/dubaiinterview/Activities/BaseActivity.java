package com.nitesh.dubaiinterview.Activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.nitesh.dubaiinterview.BuildConfig;
import com.nitesh.dubaiinterview.ErrorDialog.CommonErrorDialog;
import com.nitesh.dubaiinterview.R;
import com.nitesh.dubaiinterview.SpotsDialog.SpotsDialog;

import net.sqlcipher.database.SQLiteDatabase;

public class BaseActivity extends AppCompatActivity {
    protected Context context;
    private static final String NAVIGATION_ITEM_TAG = "NAVIGATION_ITEM_TAG";
    SpotsDialog spotsDialog;
    private Dialog dialog;
    private CharSequence message;
    private static RequestOptions requestOptionsWH, requestOptionsTransparent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /** for preventing blank screen */
        super.onCreate(savedInstanceState);
        context = this;
        SQLiteDatabase.loadLibs(this);
        spotsDialog = new SpotsDialog(context, "Please Wait...", R.style.NewDialog);//if activity finished, context getting null.
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (dialog.getWindow() != null)
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_progress);
        dialog.setCancelable(false);
        SQLiteDatabase.loadLibs(this);

    }

    public void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public void logV(String class_tag, String msg) {
        Log.v(class_tag, class_tag + ": " + msg);
    }


    public void showDialog() {
        dialog.show();
    }

    public void hideDialog() {
        dialog.dismiss();
    }

    public void showErrorDialog(String message) {
        CommonErrorDialog commonErrorDialog = new CommonErrorDialog(context, message, getResources().getString(R.string.okBtn));
        commonErrorDialog.show();
    }


    public void showLoader() {
        if (spotsDialog != null && !isFinishing() && context != null) {
            spotsDialog.show();
        }
    }

    public void setLoaderMessage(String message) {
        try {
            if (spotsDialog != null)
                spotsDialog.setloaderMessage(message);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    public void hideLoader() {
        if (spotsDialog != null && spotsDialog.isShowing()) { //&& spotsDialog.isShowing()
            spotsDialog.dismiss();
        }
    }

    public static void cacheUserPicWithGlideWithoutError(Context context, String imageUrl, ImageView imageView) {
        Glide.with(context)
//                .setDefaultRequestOptions(getRequestOptionsWithoutErrorImg(120, 120))
                .load(imageUrl)
                .into(imageView);
    }

    private static RequestOptions getRequestOptionsWithoutErrorImg(int w, int h) {

        if (requestOptionsWH == null) {
            requestOptionsWH = new RequestOptions();
            requestOptionsWH.placeholder(R.drawable.ic_default);
            requestOptionsWH.error(R.drawable.ic_default);
            requestOptionsWH.override(w, h);
            requestOptionsWH.fitCenter();
            requestOptionsWH.diskCacheStrategy(DiskCacheStrategy.ALL);
        }
        return requestOptionsWH;
    }

    public static String getLogoBASEURLMF() {
        String BASE_URL;
        if (BuildConfig.FLAVOR.equals("uat"))
            BASE_URL = "https://housingmanagementnodejs.herokuapp.com/";
        else
            BASE_URL = "https://housingsys-prod.herokuapp.com/";

        return BASE_URL;
    }

}
