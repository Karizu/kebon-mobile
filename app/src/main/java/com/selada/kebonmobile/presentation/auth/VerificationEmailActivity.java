package com.selada.kebonmobile.presentation.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.selada.kebonmobile.R;
import com.selada.kebonmobile.presentation.MainActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class VerificationEmailActivity extends AppCompatActivity {

    @OnClick(R.id.tv_skip)
    void onClickSkip(){
        startActivity(new Intent(VerificationEmailActivity.this, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_email);
        ButterKnife.bind(this);


    }
}