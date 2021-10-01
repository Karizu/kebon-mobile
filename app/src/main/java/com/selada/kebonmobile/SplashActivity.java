package com.selada.kebonmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.selada.kebonmobile.presentation.MainActivity;
import com.selada.kebonmobile.util.PreferenceManager;

import java.util.concurrent.TimeUnit;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new PreferenceManager(this);
        downTimer();
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
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    SplashActivity.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
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