package com.nitesh.dubaiinterview.SpotsDialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import com.nitesh.dubaiinterview.CustomPackage.TypefacedTextView;
import com.nitesh.dubaiinterview.R;


public class SpotsDialog extends AlertDialog {

    private static final int DELAY = 150;
    private static final int DURATION = 1500;

    private int size;
    private CharSequence message;
    private SpotsInitializer spots;
    private AnimatorPlayer animator;
    private Context context;
    private ProgressLayout spotProgress;
    private TypefacedTextView progress_txt;

    public SpotsDialog(Context context) {
        this(context, R.style.SpotsDialogDefault);
    }

    public SpotsDialog(Context context, CharSequence message) {
        this(context);
        this.message = message;
    }

    public SpotsDialog(Context context, CharSequence message, int theme) {
        this(context, theme);
        this.message = message;
        this.context = context;
    }

    public SpotsDialog(Context context, int theme) {
        super(context, theme);
        this.context = context;
    }


    public void setloaderMessage(String message) {
        this.message = message;
        if (message != null && !message.isEmpty()) {
            progress_txt.setText(message);
        } else {
            progress_txt.setText("Please wait...");
        }
    }


    @Override
    public void setMessage(CharSequence message) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spots_dialog);
        setCanceledOnTouchOutside(false);

        progress_txt = (TypefacedTextView) findViewById(R.id.progress_txt);
//        Glide.with(context).load(R.drawable.nsdlloader).into(gifloader);

    }

    //~


}
