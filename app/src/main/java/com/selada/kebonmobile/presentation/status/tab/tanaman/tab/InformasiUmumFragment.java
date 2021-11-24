package com.selada.kebonmobile.presentation.status.tab.tanaman.tab;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.response.detailcommodities.ActiveSite;
import com.selada.kebonmobile.model.response.detailcommodities.Commodity;
import com.selada.kebonmobile.model.response.detailcommodities.DetailCommoditiesResponse;
import com.selada.kebonmobile.util.MethodUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InformasiUmumFragment extends Fragment {

    @BindView(R.id.tv_lokasi_lahan)
    TextView tv_lokasi_lahan;
    @BindView(R.id.tv_mulai_tanam)
    TextView tv_mulai_tanam;
    @BindView(R.id.tv_waktu_panen)
    TextView tv_waktu_panen;
    @BindView(R.id.tv_jumlah_lubang)
    TextView tv_jumlah_lubang;
    @BindView(R.id.tv_usia_tanaman)
    TextView tv_usia_tanaman;
    @BindView(R.id.text_title)
    TextView text_title;

    private DetailCommoditiesResponse commoditiesResponse;

    public InformasiUmumFragment(DetailCommoditiesResponse commoditiesResponse) {
        this.commoditiesResponse = commoditiesResponse;
    }

    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_status_informasi_umum, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        initComponent();
    }

    @SuppressLint("SetTextI18n")
    private void initComponent() {

        Commodity commodity = commoditiesResponse.getCommodity();
        List<ActiveSite> activeSiteList = commoditiesResponse.getActiveSites();

        String[] startDate = formatDateAndTime(activeSiteList.get(0).getDevelopmentSummaries().get(0).getStartHarvestDate());
        String[] endDate = formatDateAndTime(activeSiteList.get(0).getDevelopmentSummaries().get(0).getEndHarvestDate());

        text_title.setText("Data Tanaman " + commodity.getName());
        tv_lokasi_lahan.setText(commodity.getName());
        tv_mulai_tanam.setText(startDate[0]);
        tv_waktu_panen.setText(endDate[0]);
        tv_jumlah_lubang.setText(activeSiteList.get(0).getObjectCount() + " " + activeSiteList.get(0).getObject_type_label());
        tv_usia_tanaman.setText(activeSiteList.get(0).getDevelopmentSummaries().get(0).getAge() + " Hari");
    }

    public static String[] formatDateAndTime(String dateTime) {
        String[] tempDateTime = new String[2];
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
            Date date = simpleDateFormat.parse(dateTime);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", new Locale("id", "ID"));
            @SuppressLint("SimpleDateFormat") SimpleDateFormat timeFormat = new SimpleDateFormat("HH : mm");
            tempDateTime[0] = dateFormat.format(date);
            tempDateTime[1] = timeFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return tempDateTime;
    }
}
