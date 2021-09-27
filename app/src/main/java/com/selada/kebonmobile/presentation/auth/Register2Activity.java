package com.selada.kebonmobile.presentation.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.selada.kebonmobile.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Register2Activity extends AppCompatActivity {

    @BindView(R.id.editTextEmail)
    TextView editTextEmail;

    @OnClick(R.id.btn_lanjutkan)
    void onClickLnajutkan(){
        Intent intent = new Intent(Register2Activity.this, Register3Activity.class);
        startActivity(intent);
        Register2Activity.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        ButterKnife.bind(this);
    }
}