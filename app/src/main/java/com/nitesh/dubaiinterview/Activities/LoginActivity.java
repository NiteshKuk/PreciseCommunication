package com.nitesh.dubaiinterview.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.nitesh.dubaiinterview.CommonUtils;
import com.nitesh.dubaiinterview.CustomPackage.TypefaceButton;
import com.nitesh.dubaiinterview.CustomPackage.TypefacedEditText;
import com.nitesh.dubaiinterview.CustomPackage.TypefacedTextView;
import com.nitesh.dubaiinterview.R;
import com.nitesh.dubaiinterview.SQL.CRUDCipher;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.Socket;
import java.nio.charset.StandardCharsets;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

import static com.nitesh.dubaiinterview.CommonUtils.validCellPhone;

public class LoginActivity extends BaseActivity implements View.OnClickListener{
    @BindView(R.id.ed_contact)
    TypefacedEditText ed_contact;
    @BindView(R.id.ed_pass)
    TypefacedEditText et_password;
    @BindView(R.id.img_show)
    ImageButton img_show;
    @BindView(R.id.button)
    TypefacedTextView button;

    private boolean isHide = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


        img_show.setOnClickListener(v -> {
            if (!isHide) {
                et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                img_show.setImageResource(R.drawable.show);
                et_password.setSelection(et_password.getText().length());
                isHide = true;
            } else {
                et_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                img_show.setImageResource(R.drawable.hide);
                et_password.setSelection(et_password.getText().length());
                isHide = false;
            }
        });

        et_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(et_password.getText().toString().trim())) {
                    img_show.setVisibility(View.VISIBLE);
                } else {
                    img_show.setVisibility(View.GONE);
                    et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    img_show.setImageResource(R.drawable.show);
                    isHide = true;
                }
            }
        });
    }
    @Optional
    @OnClick({R.id.button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                if (verifyInputFields()){
                    CRUDCipher crudCipher = new CRUDCipher(LoginActivity.this);
                    if (crudCipher.checkDB()) {
                        showLoader();
                        String contact = crudCipher.getContact();
                        String pass = crudCipher.getPass();
                        if (!contact.equalsIgnoreCase(ed_contact.getText().toString().trim())) {
                            hideLoader();
                            ed_contact.setText("");
                            showErrorDialog("Contact Number not exist");
                        } else if (!pass.equalsIgnoreCase(et_password.getText().toString().trim())) {
                            et_password.setText("");
                            hideLoader();
                            showErrorDialog("Password is incorrect");
                        } else {
                            Toast.makeText(context, "Login Success", Toast.LENGTH_SHORT).show();
                            new Handler().postDelayed(() -> {hideLoader();startActivity(new Intent(LoginActivity.this,DataShow.class));}, 2000);
                        }
                    }else{
                        ed_contact.setText("");
                        et_password.setText("");
                        showErrorDialog("Please Register First ");
                    }
                }
                break;
        }
    }
    private boolean verifyInputFields() {
        String password = et_password.getText().toString();

        if (TextUtils.isEmpty(ed_contact.getText().toString())) {
            showErrorDialog("Contact is  Required");
            return false;
        }
        if (!validCellPhone(ed_contact.getText().toString())){
            showErrorDialog("Please Enter Valid Mobile Number");
            return false;
        }
        if (ed_contact.getText().toString().length()<10){
            showErrorDialog("Please enter valid Mobile No");
            return false;
        }
        if (TextUtils.isEmpty(et_password.getText().toString())) {
            showErrorDialog("Password is Required");
            return false;
        }


        if (!TextUtils.isEmpty(password)) {
            boolean verifyPassword = CommonUtils.Password_pattern(password);
            if (!verifyPassword) {
                showErrorDialog(getResources().getString(R.string.password_incorrect));
                return false;
            }
        }

        return true;
    }

}