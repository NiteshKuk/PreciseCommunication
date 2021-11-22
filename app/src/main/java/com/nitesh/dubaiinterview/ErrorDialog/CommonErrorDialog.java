package com.nitesh.dubaiinterview.ErrorDialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import com.nitesh.dubaiinterview.CustomPackage.TypefaceButton;
import com.nitesh.dubaiinterview.CustomPackage.TypefacedTextView;
import com.nitesh.dubaiinterview.R;


public class CommonErrorDialog extends AlertDialog implements View.OnClickListener {
    private final String btnOKTitle;
    Context context;
    TypefaceButton btnNext;
    TypefacedTextView tvErrorMsg;
    String message = "";

    public CommonErrorDialog(Context context,
                             String message,
                             @NonNull String btnOKTitle
                                   ) {
        super(context);
        this.context = context;
        this.message = message;
        this.btnOKTitle = btnOKTitle;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_single_button);
        tvErrorMsg = findViewById(R.id.tvErrorMsg);
        btnNext = findViewById(R.id.btnNext);

        btnNext.setText(btnOKTitle);
        tvErrorMsg.setText(message);

        btnNext.setOnClickListener(this);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCanceledOnTouchOutside(false);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnNext:
                if (isShowing()) {
                    dismiss();
                }
                break;

        }
    }
}
