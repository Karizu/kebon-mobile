package com.selada.kebonmobile.presentation.home.camera;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;
import com.selada.kebonmobile.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CameraDetailActivity extends AppCompatActivity {

    @BindView(R.id.videoView)
    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_detail);
        ButterKnife.bind(this);

        initComponent();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initComponent() {
        if (getIntent()!=null){
            String url = getIntent().getStringExtra("url");
//            Log.d("url", url);

            mWebView.getSettings().setLoadWithOverviewMode(true);
            mWebView.getSettings().setUseWideViewPort(true);
            mWebView.getSettings().setSupportMultipleWindows(true);
            mWebView.getSettings().setJavaScriptEnabled(true);
            mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            mWebView.loadUrl(url);
        }
    }

    @SuppressLint("NewApi")
    @Override
    protected void onPause() {
        mWebView.onPause();
        // ...
        super.onPause();
    }
}