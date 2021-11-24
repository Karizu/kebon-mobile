package com.selada.kebonmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.selada.kebonmobile.model.response.ApiResponse;
import com.selada.kebonmobile.model.response.LoginResponse;
import com.selada.kebonmobile.model.response.errormsg.ErrorResponse;
import com.selada.kebonmobile.network.ApiCore;
import com.selada.kebonmobile.presentation.MainActivity;
import com.selada.kebonmobile.util.Loading;
import com.selada.kebonmobile.util.MethodUtil;
import com.selada.kebonmobile.util.PreferenceManager;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new PreferenceManager(this);
        downTimer();
    }

    private void refreshToken(){
        Loading.show(SplashActivity.this);
        ApiCore.apiInterface().refreshToken(PreferenceManager.getSessionToken()).enqueue(new Callback<ApiResponse<LoginResponse>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<ApiResponse<LoginResponse>> call, Response<ApiResponse<LoginResponse>> response) {
                Loading.hide(SplashActivity.this);
                try {
                    if (response.isSuccessful()){
                        LoginResponse loginResponse = Objects.requireNonNull(response.body()).getData();
                        String token = loginResponse.getAuthToken();
                        Log.d("x_token", token);

                        PreferenceManager.setIsLogin();
                        PreferenceManager.setSessionToken("Bearer " + token);
                        PreferenceManager.setLoginResponse(loginResponse);

                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        SplashActivity.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        finish();

                    } else {
                        Gson gson = new Gson();
                        ErrorResponse message = gson.fromJson(Objects.requireNonNull(response.errorBody()).charStream(),ErrorResponse.class);
//                        String errorMessage = message.getData().getMessage();
                        Dialog dialog = MethodUtil.getDialogCart(R.layout.dialog_warning, SplashActivity.this);
                        dialog.setCancelable(false);
                        dialog.setCanceledOnTouchOutside(false);
                        TextView desc = dialog.findViewById(R.id.tv_desc);
                        desc.setText("Sesi anda telah berakhir, silahkan lakukan login kembali");
                        ImageView btn_close = dialog.findViewById(R.id.btn_close);
                        btn_close.setOnClickListener(view -> {
                            dialog.dismiss();
                            Intent intent = new Intent(SplashActivity.this, IntroActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            SplashActivity.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                            finish();
                        });
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    MethodUtil.getDialogWarningCatch(SplashActivity.this);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<LoginResponse>> call, Throwable t) {
                t.printStackTrace();
                Loading.hide(SplashActivity.this);
            }
        });
    }

    private void downTimer() {
        long futureMillis = TimeUnit.SECONDS.toMillis(2);
        new CountDownTimer(futureMillis, 5000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000) % 60;
                if (seconds == 0) {
                    cancel();
                    onFinish();
                }
            }

            @Override
            public void onFinish() {
                if (PreferenceManager.getIsLogin()){
                    refreshToken();
                } else {
                    Intent intent = new Intent(SplashActivity.this, IntroActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    SplashActivity.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                }
            }
        }.start();
    }
}