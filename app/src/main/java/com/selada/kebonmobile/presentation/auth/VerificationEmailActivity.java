package com.selada.kebonmobile.presentation.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.selada.kebonmobile.R;
import com.selada.kebonmobile.presentation.MainActivity;
import com.selada.kebonmobile.util.PreferenceManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VerificationEmailActivity extends AppCompatActivity {

//    @BindView(R.id.tv_email)
//    TextView tv_email;
    @BindView(R.id.tv_greeting_name)
    TextView tv_greeting_name;

//    @OnClick(R.id.tv_skip)
//    void onClickSkip(){
//        PreferenceManager.setIsLogin();
//        startActivity(new Intent(VerificationEmailActivity.this, MainActivity.class));
//    }

    @OnClick(R.id.btn_lanjut)
    void onClickLanjut(){
        onBackPressed();
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_verification_email);
        setContentView(R.layout.registration_success_layout);
        ButterKnife.bind(this);

        String name = getIntent().getStringExtra("name");
        tv_greeting_name.setText("Hallo " + name);
//        String email = getIntent().getStringExtra("email");
//        tv_email.setText(email);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(VerificationEmailActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}