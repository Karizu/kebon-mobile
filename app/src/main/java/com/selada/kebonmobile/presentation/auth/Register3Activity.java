package com.selada.kebonmobile.presentation.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.RegisterModel;
import com.selada.kebonmobile.model.response.ApiResponse;
import com.selada.kebonmobile.model.response.LoginResponse;
import com.selada.kebonmobile.model.response.RegisterResponse;
import com.selada.kebonmobile.network.ApiCore;
import com.selada.kebonmobile.util.Loading;
import com.selada.kebonmobile.util.MethodUtil;
import com.selada.kebonmobile.util.PreferenceManager;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.anshul.libray.PasswordEditText;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register3Activity extends AppCompatActivity {

    @BindView(R.id.editTextPass)
    PasswordEditText editTextPass;
    @BindView(R.id.editTextKonfirmPass)
    PasswordEditText editTextKonfirmPass;

    private String name, email;

    @OnClick(R.id.btn_lanjutkan)
    void onClickLnajutkan(){
        if (!editTextKonfirmPass.getText().toString().equals(editTextPass.getText().toString())){
            editTextKonfirmPass.setError("Password tidak sesuai");
        } else {
            doRegister();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register3);
        ButterKnife.bind(this);

        initComponent();
    }

    private void initComponent() {
        if (getIntent()!=null){
            name = getIntent().getStringExtra("name");
            email = getIntent().getStringExtra("email");
        }
    }

    private void doRegister() {
        RegisterModel registerModel = new RegisterModel();
        registerModel.setFullname(name);
        registerModel.setUsername(email);
        registerModel.setPassword(editTextPass.getText().toString());
        registerModel.setConf_password(editTextKonfirmPass.getText().toString());

        Loading.show(this);
        ApiCore.apiInterface().register(registerModel).enqueue(new Callback<ApiResponse<LoginResponse>>() {
            @Override
            public void onResponse(Call<ApiResponse<LoginResponse>> call, Response<ApiResponse<LoginResponse>> response) {
                Loading.hide(Register3Activity.this);
                try {
                    if (response.isSuccessful()){
                        LoginResponse registerResponse = response.body().getData();
                        String token = registerResponse.getAuthToken();
                        Log.d("x_token", token);

                        PreferenceManager.setIsLogin();
                        PreferenceManager.setSessionToken("Bearer " + token);
                        PreferenceManager.setLoginResponse(registerResponse);

                        Intent intent = new Intent(Register3Activity.this, VerificationEmailActivity.class);
                        intent.putExtra("name", name);
                        intent.putExtra("email", email);
                        intent.putExtra("password", editTextPass.getText().toString());
                        intent.putExtra("conf_password", editTextKonfirmPass.getText().toString());
                        startActivity(intent);
                        Register3Activity.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    } else {
                        LoginResponse registerResponse = response.body().getData();
                        String errorMessage = registerResponse.getMessage();
                        Dialog dialog = MethodUtil.getDialogCart(R.layout.dialog_warning, Register3Activity.this);
                        TextView title = dialog.findViewById(R.id.tv_title);
                        TextView desc = dialog.findViewById(R.id.tv_desc);
                        desc.setText(errorMessage);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(Register3Activity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<LoginResponse>> call, Throwable t) {
                Loading.hide(Register3Activity.this);
                t.printStackTrace();
            }
        });
    }
}