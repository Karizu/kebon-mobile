package com.selada.kebonmobile.presentation.home.tanam;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.selada.kebonmobile.IntroActivity;
import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.response.ApiResponse;
import com.selada.kebonmobile.model.response.SchemeResponse;
import com.selada.kebonmobile.network.ApiCore;
import com.selada.kebonmobile.presentation.auth.RegisterActivity;
import com.selada.kebonmobile.presentation.home.tanam.adapter.PilihMetodeAdapter;
import com.selada.kebonmobile.util.Constant;
import com.selada.kebonmobile.util.Loading;
import com.selada.kebonmobile.util.MethodUtil;
import com.selada.kebonmobile.util.PreferenceManager;
import com.skydoves.elasticviews.ElasticButton;
import com.skydoves.elasticviews.ElasticImageView;
import com.skydoves.elasticviews.ElasticLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PilihMetodeActivity extends AppCompatActivity {

    @BindView(R.id.btn_chat)
    ElasticImageView btn_chat;
    @BindView(R.id.tv_title_bar)
    TextView tv_title_bar;
    @BindView(R.id.btn_pilih)
    ElasticButton btn_pilih;
    @BindView(R.id.rvMetodeTanam)
    RecyclerView rvMetodeTanam;

    private int metodeType = 0;
    private int id;
    private int schemeId = 0;

    @OnClick(R.id.btn_back)
    void onClickBack(){
        onBackPressed();
    }

    @OnClick(R.id.btn_istilah)
    void onClickIstilah(){

    }

    @OnClick(R.id.btn_pilih)
    void onClickPilih(){
        if (schemeId == 0){
            Toast.makeText(this, "Silahkan pilih metode tanam", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(PilihMetodeActivity.this, PilihTanamanActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("scheme_id", schemeId);
        startActivity(intent);
        PilihMetodeActivity.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_metode);
        ButterKnife.bind(this);
        new PreferenceManager(this);
        initComponent();
    }

    @SuppressLint("SetTextI18n")
    private void initComponent() {
        id = getIntent().getIntExtra("id", 0);

        btn_pilih.setEnabled(false);
        btn_chat.setVisibility(View.GONE);
        tv_title_bar.setText("Pilih Metode");

        getListScheme();
    }

    private void getListScheme(){
        Loading.show(PilihMetodeActivity.this);
        ApiCore.apiInterface().getListScheme(PreferenceManager.getSessionToken()).enqueue(new Callback<ApiResponse<List<SchemeResponse>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<SchemeResponse>>> call, Response<ApiResponse<List<SchemeResponse>>> response) {
                Loading.hide(PilihMetodeActivity.this);
                try {
                    if (response.isSuccessful()){
                        assert response.body() != null;
                        if (response.body().getData()!=null){
                            PilihMetodeAdapter adapter = new PilihMetodeAdapter(response.body().getData(), PilihMetodeActivity.this, PilihMetodeActivity.this);
                            rvMetodeTanam.setAdapter(adapter);
                        }
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    MethodUtil.getDialogWarningCatch(PilihMetodeActivity.this);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<SchemeResponse>>> call, Throwable t) {
                Loading.hide(PilihMetodeActivity.this);
                t.printStackTrace();
            }
        });
    }

    public void setData(int schemeId){
        btn_pilih.setEnabled(true);
        this.schemeId = schemeId;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}