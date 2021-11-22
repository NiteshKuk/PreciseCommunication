package com.nitesh.dubaiinterview.Network;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import com.nitesh.dubaiinterview.Commondata;
import com.nitesh.dubaiinterview.CustomPackage.TypefaceButton;
import com.nitesh.dubaiinterview.CustomPackage.TypefacedTextView;
import com.nitesh.dubaiinterview.R;


public class ErrorSingleButtonDialog extends AlertDialog implements View.OnClickListener {

    static OnDialogHandleClick listener;
    private final String btnOKTitle;
    Context context;
    TypefaceButton btnNext;
    TypefaceButton btnCancel;
    TypefacedTextView tvErrorMsg;
    String message = "";
    Commondata.OkButtonHandle positiveBtnActionType;
    Commondata.OkButtonHandle negativeBtnActionType;
    boolean negativeBtnVisibility;

    public ErrorSingleButtonDialog(Context context, OnDialogHandleClick onDialogHandleClick,
                                   String message,
                                   @NonNull String btnOKTitle,
                                   boolean negativeBtnVisibility,
                                   Commondata.OkButtonHandle positiveBtnActionType,
                                   Commondata.OkButtonHandle negativeBtnActionType) {
        super(context);
        this.context = context;
        this.message = message;
        this.btnOKTitle = btnOKTitle;
        listener = onDialogHandleClick;
        this.positiveBtnActionType = positiveBtnActionType;
        this.negativeBtnActionType = negativeBtnActionType;
        this.negativeBtnVisibility = negativeBtnVisibility;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_single_button);
        tvErrorMsg = findViewById(R.id.tvErrorMsg);
        btnNext = findViewById(R.id.btnNext);
        btnCancel = findViewById(R.id.btnCancel);

        if (negativeBtnVisibility){
            btnCancel.setVisibility(View.VISIBLE);
        }else {
            btnCancel.setVisibility(View.GONE);
        }
        btnNext.setText(btnOKTitle);
        tvErrorMsg.setText(message);


        btnNext.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCanceledOnTouchOutside(false);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnNext:
                if (isShowing()) {
                    dismiss();
                    listener.processOkButton(positiveBtnActionType);
                }
                break;
            case R.id.btnCancel:
                if (isShowing()) {
                    dismiss();
                    listener.processCancelButton(negativeBtnActionType);
                }
        }
    }


    public interface OnDialogHandleClick {
        void processOkButton(Commondata.OkButtonHandle okButtonHandle);

        void processCancelButton(Commondata.OkButtonHandle okButtonHandle);

    }
}
