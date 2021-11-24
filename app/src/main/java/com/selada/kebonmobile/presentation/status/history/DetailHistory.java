package com.selada.kebonmobile.presentation.status.history;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.response.farmactivities.Activity;
import com.selada.kebonmobile.presentation.status.history.adapter.CostActivityAdapter;
import com.selada.kebonmobile.presentation.status.history.adapter.DetailActivityAdapter;
import com.selada.kebonmobile.util.MethodUtil;
import com.skydoves.elasticviews.ElasticImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailHistory extends AppCompatActivity {

    @BindView(R.id.tv_lokasi_lahan)
    TextView tv_lokasi_lahan;
    @BindView(R.id.tv_tgl_panen)
    TextView tv_tgl_panen;
    @BindView(R.id.tv_usia_tanaman)
    TextView tv_usia_tanaman;
    @BindView(R.id.tv_jumlah_panen)
    TextView tv_jumlah_panen;
    @BindView(R.id.tv_metode_panen)
    TextView tv_metode_panen;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.img_plant)
    ElasticImageView img_plant;
    @BindView(R.id.tv_title_bar)
    TextView tv_title_bar;
    @BindView(R.id.textViewPanens)
    TextView labelSchedule;
    @BindView(R.id.textViewPanenss)
    TextView objMethodLabel;
    @BindView(R.id.textViewSite)
    TextView objDateLabel;
    @BindView(R.id.rv_plant_activity)
    RecyclerView rv_plant_activity;
    @BindView(R.id.rv_cost_activity)
    RecyclerView rv_cost_activity;
    @BindView(R.id.tv_no_cost)
    TextView tv_no_cost;
    @BindView(R.id.tv_jumlah_obj)
    TextView tv_jumlah_obj;
    @BindView(R.id.tv_sub_total)
    TextView tv_sub_total;
    @BindView(R.id.tv_total_obj)
    TextView tv_total_obj;
    @BindView(R.id.tv_total)
    TextView tv_total;
    @BindView(R.id.layout_recycler_cost)
    LinearLayout layout_recycler_cost;
    @BindView(R.id.layout_address)
    ConstraintLayout layout_address;
    @BindView(R.id.tv_address)
    TextView tv_address;

    @OnClick(R.id.btn_back)
    void onClickBack(){
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_history);
        ButterKnife.bind(this);

        initComponent();
    }

    @SuppressLint("SetTextI18n")
    private void initComponent() {
        if (getIntent()!=null){
            String json = getIntent().getStringExtra("json");
            Activity activity = new Gson().fromJson(json, Activity.class);

            tv_title_bar.setText("Rincian " + activity.getActivityType().getLabel());
            tv_title.setText(activity.getCommodity().getName());
            labelSchedule.setText("Jumlah " + activity.getActivityType().getLabel());
            objMethodLabel.setText("Metode " + activity.getActivityType().getLabel());
            objDateLabel.setText("Jadwal " + activity.getActivityType().getLabel());
            try {
                tv_metode_panen.setText(activity.getActivityMethod().getName());
            } catch (Exception e){
                tv_metode_panen.setText("-");
            }

            tv_lokasi_lahan.setText(activity.getSite().getName());
            tv_jumlah_panen.setText(activity.getTotalObjects()+"");
            tv_usia_tanaman.setText("-");
            String[] date = MethodUtil.formatDateAndTime(activity.getScheduledDate());
            tv_tgl_panen.setText(date[0] + " " + date[1]);

            Glide.with(this)
                    .load(activity.getCommodity().getMainImage().getFullpath())
                    .placeholder(R.drawable.img_plant)
                    .into(img_plant);

            DetailActivityAdapter adapter = new DetailActivityAdapter(activity.getResults(), DetailHistory.this, DetailHistory.this, activity.getActivityType().getLabel());
            rv_plant_activity.setAdapter(adapter);

            if (activity.getCost_details().size()>0){
                tv_no_cost.setVisibility(View.GONE);
                layout_recycler_cost.setVisibility(View.VISIBLE);
                CostActivityAdapter costActivityAdapter = new CostActivityAdapter(activity.getCost_details(), DetailHistory.this, DetailHistory.this, activity.getActivityType().getLabel());
                rv_cost_activity.setAdapter(costActivityAdapter);
                tv_sub_total.setText("Rp " + MethodUtil.toCurrencyNumber(activity.getSubTotalCost()));
                tv_jumlah_obj.setText("Jumlah " + activity.getActivityType().getLabel());
                tv_total_obj.setText(activity.getTotalObjects()+"");
                tv_total.setText("Rp " + MethodUtil.toCurrencyNumber(activity.getTotalCost()));
            } else {
                tv_no_cost.setVisibility(View.VISIBLE);
                layout_recycler_cost.setVisibility(View.GONE);
            }

            try {
                if (!activity.getDeliveryAddress().getAddress_street().equals("")){
                    tv_address.setText(activity.getDeliveryAddress().getAddress_street());
                    layout_address.setVisibility(View.VISIBLE);
                }
            } catch (Exception e){
                layout_address.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}