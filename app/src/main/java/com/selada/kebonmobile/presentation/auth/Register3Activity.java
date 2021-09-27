package com.selada.kebonmobile.presentation.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.selada.kebonmobile.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class Register3Activity extends AppCompatActivity {

    @OnClick(R.id.btn_lanjutkan)
    void onClickLnajutkan(){
        Intent intent = new Intent(Register3Activity.this, VerificationEmailActivity.class);
        startActivity(intent);
        Register3Activity.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register3);
        ButterKnife.bind(this);

    }
}