package com.selada.kebonmobile.presentation.akun.email;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.selada.kebonmobile.R;
import com.selada.kebonmobile.presentation.akun.DetailAkunActivity;
import com.selada.kebonmobile.presentation.akun.nama.UbahNamaActivity;
import com.selada.kebonmobile.util.PreferenceManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UbahEmailActivity extends AppCompatActivity {

    @BindView(R.id.ubahEmail)
    EditText ubahEmail;

    @OnClick(R.id.save_email)
    void onClickSaveEmail(){
        finish();
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
//        Toast.makeText(getApplicationContext(), "Successfully saved", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_back)
    void onClickBack(){
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_email);
        ButterKnife.bind(this);

        ubahEmail.setText(PreferenceManager.getLoginResponse().getUser().getUsername());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}