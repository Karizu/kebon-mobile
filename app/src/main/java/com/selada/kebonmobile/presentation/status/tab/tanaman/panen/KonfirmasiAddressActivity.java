package com.selada.kebonmobile.presentation.status.tab.tanaman.panen;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ibnux.locationpicker.LocationPickerActivity;
import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.request.DeliveryAddress;
import com.selada.kebonmobile.model.response.harvest.HarvestInquiryResponse;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

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
    private double latitude;
    private double longitude;
    private String methodId;
    private Integer commodityId = 0;
    private Integer totalObjects = 0;
    private String json;
    private DeliveryAddress deliveryAddress;

    @OnClick(R.id.layout_address)
    void onClickAddress() {
        Intent locationPickerIntent = new Intent(KonfirmasiAddressActivity.this, LocationPickerActivity.class);
        KonfirmasiAddressActivity.this.startActivityForResult(locationPickerIntent, 4268);
    }

    @OnClick(R.id.cb_gojek)
    void onClickCbGojek() {
        metodePengiriman = "gojek";
        cb_gojek.setChecked(true);
        cb_grab.setChecked(false);
        cb_jne.setChecked(false);
        cb_jnt.setChecked(false);
    }

    @OnClick(R.id.cb_grab)
    void onClickCbGrab() {
        metodePengiriman = "grab";
        cb_gojek.setChecked(false);
        cb_grab.setChecked(true);
        cb_jne.setChecked(false);
        cb_jnt.setChecked(false);
    }

    @OnClick(R.id.cb_jnt)
    void onClickCbJnt() {
        metodePengiriman = "jnt";
        cb_gojek.setChecked(false);
        cb_grab.setChecked(false);
        cb_jne.setChecked(false);
        cb_jnt.setChecked(true);
    }

    @OnClick(R.id.cb_jne)
    void onClickCbJne() {
        metodePengiriman = "jne";
        cb_gojek.setChecked(false);
        cb_grab.setChecked(false);
        cb_jne.setChecked(true);
        cb_jnt.setChecked(false);
    }

    @OnClick(R.id.btn_proses)
    void onClickProses() {
        Intent intent = new Intent(this, KonfirmasiPanenOnlineActivity.class);
        intent.putExtra("method_id", methodId);
        intent.putExtra("commodity_id", commodityId);
        intent.putExtra("site_id", totalObjects);
        intent.putExtra("address", tv_address.getText().toString());
        intent.putExtra("json", json);
        intent.putExtra("delivery_address", new Gson().toJson(deliveryAddress));
        startActivity(intent);
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @OnClick(R.id.btn_back)
    void onClickBack() {
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi_address);
        ButterKnife.bind(this);
        initComponent();
        initGeocoder();
    }

    private void initComponent() {
        json = getIntent().getStringExtra("json");
        HarvestInquiryResponse monitoringResponse = new Gson().fromJson(json, HarvestInquiryResponse.class);
        if (monitoringResponse.getHarvests().size()>0) {
            commodityId = monitoringResponse.getHarvests().get(0).getCommodityId();
            totalObjects = monitoringResponse.getHarvests().get(0).getObjectCount();
        }
    }

    private final LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            longitude = location.getLongitude();
            latitude = location.getLatitude();
        }
    };


    @SuppressLint("SetTextI18n")
    private void initGeocoder() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, locationListener);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        double longitude = Objects.requireNonNull(location).getLongitude();
        double latitude = location.getLatitude();

        try {
            Geocoder gCoder = new Geocoder(KonfirmasiAddressActivity.this);
            List<Address> addresses = gCoder.getFromLocation(this.latitude, this.longitude, 1);
            if (addresses != null && addresses.size() > 0) {
                Log.d("Address", new Gson().toJson(addresses));
                tv_address.setText(addresses.get(0).getAddressLine(0) + "\n" +
                        addresses.get(0).getPostalCode());

                String jalan = addresses.get(0).getAddressLine(0);
                String desa = addresses.get(0).getSubLocality();
                String kecamatan = addresses.get(0).getLocality();
                String kabupaten = addresses.get(0).getSubAdminArea();
                String provinsi = addresses.get(0).getAdminArea();
                String negara = addresses.get(0).getCountryName();

                deliveryAddress = new DeliveryAddress();
                deliveryAddress.setAddress_street(jalan);
                deliveryAddress.setAddress_subdistrict(desa);
                deliveryAddress.setAddress_district(kecamatan);
                deliveryAddress.setAddress_city(kabupaten);
                deliveryAddress.setAddress_province(provinsi);
                deliveryAddress.setAddress_country(negara);
                deliveryAddress.setAddress_postal( addresses.get(0).getPostalCode());
                deliveryAddress.setAddress_latitude(String.valueOf(latitude));
                deliveryAddress.setAddress_longitude(String.valueOf(longitude));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setInformasi(Intent data) {
        String lat = data.getStringExtra("lat");
        String lon = data.getStringExtra("lon");

        double latitude = Double.parseDouble(lat);
        double longitude = Double.parseDouble(lon);

        Geocoder geocoder = new Geocoder(KonfirmasiAddressActivity.this, Locale.getDefault());
        try {
            List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
            if (addressList != null && addressList.size() > 0) {
                Address address = addressList.get(0);
                bindView(address, latitude, longitude);
            }
        } catch (IOException e) {
            Log.e("Location Address Loader", "Unable connect to Geocoder", e);
        }
    }

    private void bindView(Address address, double latitude, double longitude) {
        String alamat = address.getAddressLine(0);
        String jalan = address.getThoroughfare();
        String desa = address.getSubLocality();
        String kecamatan = address.getLocality();
        String kabupaten = address.getSubAdminArea();
        String provinsi = address.getAdminArea();
        String negara = address.getCountryName();

        tv_address.setText(alamat);

        deliveryAddress = new DeliveryAddress();
        deliveryAddress.setAddress_street(jalan);
        deliveryAddress.setAddress_subdistrict(desa);
        deliveryAddress.setAddress_district(kecamatan);
        deliveryAddress.setAddress_city(kabupaten);
        deliveryAddress.setAddress_province(provinsi);
        deliveryAddress.setAddress_country(negara);
        deliveryAddress.setAddress_latitude(String.valueOf(latitude));
        deliveryAddress.setAddress_longitude(String.valueOf(longitude));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 4268) {
                setInformasi(data);
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}