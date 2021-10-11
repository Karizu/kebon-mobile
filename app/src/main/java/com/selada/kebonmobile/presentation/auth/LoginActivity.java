package com.selada.kebonmobile.presentation.auth;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.LoginModel;
import com.selada.kebonmobile.model.response.ApiResponse;
import com.selada.kebonmobile.model.response.LoginResponse;
import com.selada.kebonmobile.network.ApiCore;
import com.selada.kebonmobile.presentation.MainActivity;
import com.selada.kebonmobile.util.Loading;
import com.selada.kebonmobile.util.MethodUtil;
import com.selada.kebonmobile.util.PreferenceManager;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.editTextEmail)
    EditText editTextEmail;
    @BindView(R.id.editTextPass)
    EditText editTextPass;

    @OnClick(R.id.btn_lanjutkan)
    void onClickBtnLanjutkan(){
        if (editTextEmail.getText().toString().equals("")) {
            editTextEmail.setError("Email harus diisi");
        } else if (editTextPass.getText().toString().equals("")){
            editTextPass.setError("Password harus diisi");
        } else if (!editTextEmail.getText().toString().contains("@")){
            editTextEmail.setError("Format email salah");
        } else {
            doLogin();
        }
    }

    private void doLogin() {
        LoginModel loginModel = new LoginModel();
        loginModel.setUsername(editTextEmail.getText().toString());
        loginModel.setPassword(editTextPass.getText().toString());

        Loading.show(this);
        ApiCore.apiInterface().signIn(loginModel).enqueue(new Callback<ApiResponse<LoginResponse>>() {
            @Override
            public void onResponse(Call<ApiResponse<LoginResponse>> call, Response<ApiResponse<LoginResponse>> response) {
                Loading.hide(LoginActivity.this);
                try {
                    if (response.isSuccessful()){
                        LoginResponse loginResponse = Objects.requireNonNull(response.body()).getData();
                        String token = loginResponse.getAuthToken();
                        Log.d("x_token", token);

                        PreferenceManager.setIsLogin();
                        PreferenceManager.setSessionToken("Bearer " + token);
                        PreferenceManager.setLoginResponse(loginResponse);

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                        LoginActivity.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    } else {
                        LoginResponse loginResponse = Objects.requireNonNull(response.body()).getData();
                        String errorMessage = loginResponse.getMessage();
                        Dialog dialog = MethodUtil.getDialogCart(R.layout.dialog_warning, LoginActivity.this);
                        ImageView btn_close = dialog.findViewById(R.id.btn_close);
                        TextView desc = dialog.findViewById(R.id.tv_desc);
                        desc.setText(errorMessage);
                        btn_close.setOnClickListener(view -> {
                            dialog.dismiss();
                        });
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(LoginActivity.this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<LoginResponse>> call, Throwable t) {
                t.printStackTrace();
                Loading.hide(LoginActivity.this);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        new PreferenceManager(this);
        initComponent();
    }

    private void initComponent() {

    }
}