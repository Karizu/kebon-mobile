package com.selada.kebonmobile.presentation.status.tab.tanaman.panen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.selada.kebonmobile.R;
import com.selada.kebonmobile.presentation.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class KonfirmasiAddressActivity extends AppCompatActivity {

    @BindView(R.id.cb_gojek)
    CheckBox cb_gojek;
    @BindView(R.id.cb_grab)
    CheckBox cb_grab;
    @BindView(R.id.cb_jnt)
    CheckBox cb_jnt;
    @BindView(R.id.cb_jne)
    CheckBox cb_jne;
    @BindView(R.id.tv_address)
    TextView tv_address;

    private String metodePengiriman = "";

    @OnClick(R.id.layout_address)
    void onClickAddress(){

    }

    @OnClick(R.id.cb_gojek)
    void onClickCbGojek(){
        metodePengiriman = "gojek";
        cb_gojek.setChecked(true);
        cb_grab.setChecked(false);
        cb_jne.setChecked(false);
        cb_jnt.setChecked(false);
    }

    @OnClick(R.id.cb_grab)
    void onClickCbGrab(){
        metodePengiriman = "grab";
        cb_gojek.setChecked(false);
        cb_grab.setChecked(true);
        cb_jne.setChecked(false);
        cb_jnt.setChecked(false);
    }

    @OnClick(R.id.cb_jnt)
    void onClickCbJnt(){
        metodePengiriman = "jnt";
        cb_gojek.setChecked(false);
        cb_grab.setChecked(false);
        cb_jne.setChecked(false);
        cb_jnt.setChecked(true);
    }

    @OnClick(R.id.cb_jne)
    void onClickCbJne(){
        metodePengiriman = "jne";
        cb_gojek.setChecked(false);
        cb_grab.setChecked(false);
        cb_jne.setChecked(true);
        cb_jnt.setChecked(false);
    }

    @OnClick(R.id.btn_proses)
    void onClickProses(){
        if (metodePengiriman.equals("")){
            Toast.makeText(this, "Silahkan pilih metode pengiriman", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, KonfirmasiPanenOnlineActivity.class);
            startActivity(intent);
            this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }
    }

    @OnClick(R.id.btn_back)
    void onClickBack(){
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi_address);
        ButterKnife.bind(this);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}