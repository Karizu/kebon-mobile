package com.selada.kebonmobile.presentation.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.selada.kebonmobile.IntroActivity;
import com.selada.kebonmobile.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.editTextName)
    TextView editTextName;

    @OnClick(R.id.btn_lanjutkan)
    void onClickLnajutkan(){
        if (editTextName.getText().toString().equals("")) {
            editTextName.setError("Nama harus diisi");
        } else {
            Intent intent = new Intent(RegisterActivity.this, RegisterPhoneActivity.class);
            intent.putExtra("name", editTextName.getText().toString());
            startActivity(intent);
            RegisterActivity.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        RegisterActivity.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}