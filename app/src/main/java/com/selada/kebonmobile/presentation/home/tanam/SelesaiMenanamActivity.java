package com.selada.kebonmobile.presentation.home.tanam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.selada.kebonmobile.R;
import com.selada.kebonmobile.presentation.MainActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelesaiMenanamActivity extends AppCompatActivity {

    @OnClick(R.id.btn_selesai)
    void onClickSelesai(){
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selesai_menanam);
        ButterKnife.bind(this);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}