package com.selada.kebonmobile.presentation.akun;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.selada.kebonmobile.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailAkunActivity extends AppCompatActivity {

    @OnClick(R.id.layout_name)
    void onClickEditName(){

    }

    @OnClick(R.id.layout_phone)
    void onClickEditPhone(){

    }

    @OnClick(R.id.layout_email)
    void onClickEditEmail(){

    }

    @OnClick(R.id.layout_address)
    void onClickEditAddress(){

    }

    @OnClick(R.id.layout_change_pass)
    void onClickEditPass(){

    }

    @OnClick(R.id.btn_back)
    void onClickBack(){
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_akun);
        ButterKnife.bind(this);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}