package com.nitesh.dubaiinterview.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;

import com.google.android.material.textfield.TextInputLayout;
import com.nitesh.dubaiinterview.CommonUtils;
import com.nitesh.dubaiinterview.CustomPackage.TypefaceButton;
import com.nitesh.dubaiinterview.CustomPackage.TypefacedEditText;
import com.nitesh.dubaiinterview.CustomPackage.TypefacedTextView;
import com.nitesh.dubaiinterview.R;
import com.nitesh.dubaiinterview.SQL.CRUDCipher;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import butterknife.Optional;

import static com.nitesh.dubaiinterview.CommonUtils.validCellPhone;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.et_name)
    TypefacedEditText et_name;
    @BindView(R.id.et_password)
    TypefacedEditText et_password;
    @BindView(R.id.et_contact)
    TypefacedEditText et_contact;

    @BindView(R.id.til_name)
    TextInputLayout til_name;
    @BindView(R.id.til_password)
    TextInputLayout til_password;
    @BindView(R.id.til_contact)
    TextInputLayout til_contact;
    @BindView(R.id.btnNext)
    TypefaceButton btnNext;
    @BindView(R.id.tv_heading)
    TypefacedTextView tv_heading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
    @Optional
    @OnClick({R.id.btnNext,R.id.tv_heading})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnNext:
                if (verifyInputFields()){
                    showLoader();
                    setLoaderMessage("Registering User");
                    CRUDCipher crudCipher = new CRUDCipher(MainActivity.this,et_name.getText().toString(),et_contact.getText().toString(),et_password.getText().toString());
                    crudCipher.insertSthToDb();
                    new Handler().postDelayed(() -> {
                        hideLoader();
                        et_contact.setText("");
                        et_name.setText("");
                        et_password.setText("");
                        startActivity(new Intent(MainActivity.this,LoginActivity.class));
                    },2000);
                }
                break;
            case R.id.tv_heading:
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                break;
        }
    }
    private boolean verifyInputFields() {
        String password = et_password.getText().toString();

        if (TextUtils.isEmpty(et_name.getText().toString())) {
            til_name.setError("Name is Required");
            return false;
        }
        if (TextUtils.isEmpty(et_password.getText().toString())) {
            til_password.setError("Password is Required");
            return false;
        }

        if (TextUtils.isEmpty(et_contact.getText().toString())) {
            til_contact.setError("Contact is  Required");
            return false;
        }
        if (!validCellPhone(et_contact.getText().toString())){
            til_contact.setError("Please Enter Valid Mobile Number");
            return false;
        }
        if (et_contact.getText().toString().length()<10){
            til_contact.setError("Please enter valid Mobile No");
            return false;
        }
        if (!TextUtils.isEmpty(password)) {
            boolean verifyPassword = CommonUtils.Password_pattern(password);
            if (!verifyPassword) {
                til_password.setError(getResources().getString(R.string.password_incorrect));
                return false;
            }
        }

        return true;
    }

    @OnTextChanged(R.id.et_name)
    void onNameChanged(CharSequence text) {

        if (!TextUtils.isEmpty(text))
            til_name.setErrorEnabled(false);
    }

    @OnTextChanged(R.id.et_contact)
    void onContactChanged(CharSequence text) {

        if (!TextUtils.isEmpty(text))
            til_contact.setErrorEnabled(false);
    }

    @OnTextChanged(R.id.et_password)
    void onPasswordChanged(CharSequence text) {

        if (!TextUtils.isEmpty(text))
            til_password.setErrorEnabled(false);
    }
}