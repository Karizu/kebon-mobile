package com.selada.kebonmobile.presentation.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.selada.kebonmobile.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterPhoneActivity extends AppCompatActivity {

    @BindView(R.id.editTextNomor)
    EditText editTextNomor;

    private String name;

    @OnClick(R.id.btn_lanjutkan)
    void onClickLanjutkan(){
        if (editTextNomor.getText().toString().equals("")) {
            editTextNomor.setError("Nomor handphone harus diisi");
        } else if (!editTextNomor.getText().toString().startsWith("0")) {
            editTextNomor.setError("Format nomor tidak sesuai");
        } else {
            Intent intent = new Intent(RegisterPhoneActivity.this, Register2Activity.class);
            intent.putExtra("name", name);
            intent.putExtra("phone", editTextNomor.getText().toString());
            startActivity(intent);
            RegisterPhoneActivity.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_phone);
        ButterKnife.bind(this);

        initComponent();
    }

    private void initComponent() {
        if (getIntent()!=null){
            name = getIntent().getStringExtra("name");
        }
    }
}