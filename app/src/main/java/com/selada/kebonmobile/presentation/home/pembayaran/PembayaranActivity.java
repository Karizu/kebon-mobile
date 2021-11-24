package com.selada.kebonmobile.presentation.home.pembayaran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.SewaLahanModel;
import com.selada.kebonmobile.model.request.LeaseSiteRequest;
import com.selada.kebonmobile.model.response.ApiResponse;
import com.selada.kebonmobile.model.response.SiteLeasableResponse;
import com.selada.kebonmobile.model.response.SiteResponse;
import com.selada.kebonmobile.model.response.errormsg.ErrorResponse;
import com.selada.kebonmobile.model.response.invoice.PaymentMethod;
import com.selada.kebonmobile.model.response.leasesite.Account;
import com.selada.kebonmobile.model.response.leasesite.LeaseSiteResponse;
import com.selada.kebonmobile.network.ApiCore;
import com.selada.kebonmobile.presentation.auth.Register3Activity;
import com.selada.kebonmobile.presentation.auth.VerificationEmailActivity;
import com.selada.kebonmobile.presentation.home.lahan.SewaLahanActivity;
import com.selada.kebonmobile.util.Loading;
import com.selada.kebonmobile.util.MethodUtil;
import com.selada.kebonmobile.util.PreferenceManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PembayaranActivity extends AppCompatActivity {

    @BindView(R.id.tv_title_bar)
    TextView tv_title_bar;
    @BindView(R.id.tv_farm_name)
    TextView tv_farm_name;
    @BindView(R.id.tv_jumlah_kavling)
    TextView tv_jumlah_kavling;
    @BindView(R.id.jumlah_kavling)
    TextView jumlah_kavling;
    @BindView(R.id.tv_lama_sewa)
    TextView tv_lama_sewa;
    @BindView(R.id.tv_harga)
    TextView tv_harga;
    @BindView(R.id.tv_sub_total)
    TextView tv_sub_total;
    @BindView(R.id.tv_duration)
    TextView tv_duration;
    @BindView(R.id.tv_total_bayar)
    TextView tv_total_bayar;
    @BindView(R.id.spinner_payment_method)
    Spinner spinner_payment_method;
    @BindView(R.id.frame_sub_spinner)
    FrameLayout frame_sub_spinner;
    @BindView(R.id.spinner_sub_payment_method)
    Spinner spinner_sub_payment_method;
    @BindView(R.id.imageView)
    ImageView imageView;

    private String preffered_payment_account_id = "";
    private LeaseSiteRequest leaseSiteRequest;
    private SewaLahanModel lahanModel;
    private String objDurationLabel;

    @OnClick(R.id.btn_bayar)
    void onClickBtnBayar(){
        if (preffered_payment_account_id.equals("")) {
            Toast.makeText(this, "Silahkan pilih metode pembayaran", Toast.LENGTH_SHORT).show();
        } else {
            doLeaseSite();
        }
    }

    @OnClick(R.id.btn_back)
    void onClickBack(){
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran);
        ButterKnife.bind(this);
        new PreferenceManager(this);
        initComponent();
    }

    @SuppressLint("SetTextI18n")
    private void initComponent() {
        getLeasableSite();
        tv_title_bar.setText("Pembayaran");

        lahanModel = PreferenceManager.getSewaLahan();

        int totalPrice = Integer.parseInt(lahanModel.getTotalHarga())*Integer.parseInt(lahanModel.getJumlahKavling());

        jumlah_kavling.setText("Jumlah " + lahanModel.getJenisSewa() + " : ");
        tv_farm_name.setText(lahanModel.getNamaLahan());
        tv_jumlah_kavling.setText(lahanModel.getJumlahKavling() + " " + lahanModel.getJenisSewa());
        tv_harga.setText("Rp. " + MethodUtil.toCurrencyNumber(Integer.parseInt(lahanModel.getHarga())));
        tv_sub_total.setText("Rp. " + MethodUtil.toCurrencyNumber(Integer.parseInt(lahanModel.getTotalHarga())));
        tv_total_bayar.setText("Rp. " + MethodUtil.toCurrencyNumber(totalPrice));

        getPaymentMethod();
    }

    private void getLeasableSite(){
        Loading.show(PembayaranActivity.this);
        ApiCore.apiInterface().getListSiteLeasable(PreferenceManager.getSessionToken()).enqueue(new Callback<ApiResponse<List<SiteLeasableResponse>>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<ApiResponse<List<SiteLeasableResponse>>> call, Response<ApiResponse<List<SiteLeasableResponse>>> response) {
                Loading.hide(PembayaranActivity.this);
                try {
                    if (response.isSuccessful()){
                        List<SiteLeasableResponse> siteResponses = Objects.requireNonNull(response.body()).getData();
                        for (SiteLeasableResponse siteResponse: siteResponses){
                            if (siteResponse.getId() == Integer.parseInt(lahanModel.getId())){
                                if (siteResponse.getLeaseableObjects().size()>0){
                                    objDurationLabel = siteResponse.getLeaseableObjects().get(0).getLeaseDurationName();
                                    tv_lama_sewa.setText(lahanModel.getLamaSewa() + " " + objDurationLabel);
                                    tv_duration.setText(lahanModel.getJumlahKavling() + " " + lahanModel.getJenisSewa());
                                }

                                Glide.with(PembayaranActivity.this)
                                        .load(siteResponse.getLogo().getFullpath())
                                        .placeholder(R.drawable.img_plant)
                                        .into(imageView);
                            }
                        }
                    } else {
                        MethodUtil.getDialogWarning(response.errorBody(), PembayaranActivity.this);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    MethodUtil.getDialogWarningCatch(PembayaranActivity.this);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<SiteLeasableResponse>>> call, Throwable t) {
                t.printStackTrace();
                Loading.hide(PembayaranActivity.this);
            }
        });
    }

    private void doLeaseSite(){
        leaseSiteRequest = new LeaseSiteRequest();
        leaseSiteRequest.setPackage_id(lahanModel.getId());
        leaseSiteRequest.setLease_duration(lahanModel.getLamaSewa());
        leaseSiteRequest.setQuantity(lahanModel.getJumlahKavling());
        leaseSiteRequest.setPreffered_payment_account_id(preffered_payment_account_id);

        Loading.show(this);
        ApiCore.apiInterface().leaseASite(leaseSiteRequest, PreferenceManager.getSessionToken()).enqueue(new Callback<ApiResponse<LeaseSiteResponse>>() {
            @Override
            public void onResponse(Call<ApiResponse<LeaseSiteResponse>> call, Response<ApiResponse<LeaseSiteResponse>> response) {
                try {
                    if (response.isSuccessful()){
                        LeaseSiteResponse siteResponse = Objects.requireNonNull(response.body()).getData();
                        if (siteResponse!=null){

                            //add history lease site to local
                            lahanModel.setMetodeBayar(spinner_payment_method.getSelectedItem().toString());
                            lahanModel.setInvoiceId(siteResponse.getFirstInvoice().getId()+"");
                            List<SewaLahanModel> sewaLahanModels = new ArrayList<>();
                            sewaLahanModels.add(lahanModel);
                            PreferenceManager.setHistorySewalahan(sewaLahanModels);

                            String jsonResponse = new Gson().toJson(siteResponse);
                            Intent intent = new Intent(PembayaranActivity.this, RincianPembayaranActivity.class);
                            intent.putExtra("payment_method", spinner_payment_method.getSelectedItem().toString());
                            intent.putExtra("lease_site_response", jsonResponse);
                            startActivity(intent);
                            PembayaranActivity.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        } else {
                            Loading.hide(PembayaranActivity.this);
                            String errorMessage = "Terjadi kesalahan (Response Code: Empty)";
                            Dialog dialog = MethodUtil.getDialogCart(R.layout.dialog_warning, PembayaranActivity.this);
                            TextView desc = dialog.findViewById(R.id.tv_desc);
                            desc.setText(errorMessage);
                        }
                    } else {
                        Loading.hide(PembayaranActivity.this);
                        MethodUtil.getDialogWarning(response.errorBody(), PembayaranActivity.this);
                    }
                } catch (Exception e){
                    Loading.hide(PembayaranActivity.this);
                    e.printStackTrace();
                    MethodUtil.getDialogWarningCatch(PembayaranActivity.this);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<LeaseSiteResponse>> call, Throwable t) {
                t.printStackTrace();
                Loading.hide(PembayaranActivity.this);
            }
        });
    }

    private void getPaymentMethod(){
        Loading.show(this);
        ApiCore.apiInterface().getPaymentMethods(PreferenceManager.getSessionToken()).enqueue(new Callback<ApiResponse<List<PaymentMethod>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<PaymentMethod>>> call, Response<ApiResponse<List<PaymentMethod>>> response) {
                Loading.hide(PembayaranActivity.this);
                try {
                    if (response.isSuccessful()){
                        List<PaymentMethod> methodList = Objects.requireNonNull(response.body()).getData();
                        if (methodList.size()>0){
                            List<String> paymentMethodNameList = new ArrayList<>();
                            List<Integer> paymentMethodIdList = new ArrayList<>();

                            for (PaymentMethod method: methodList){
                                paymentMethodNameList.add(method.getName());
                                paymentMethodIdList.add(method.getId());
                            }

                            ArrayAdapter aa = new ArrayAdapter(PembayaranActivity.this, R.layout.custom_spinner_payment_method, paymentMethodNameList);
                            aa.setDropDownViewResource(R.layout.custom_spinner_drop_item_payment_method);
                            spinner_payment_method.setAdapter(aa);
                            spinner_payment_method.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                    List<String> accountNameList = new ArrayList<>();
                                    List<String> accountIdList = new ArrayList<>();

                                    int methodId = paymentMethodIdList.get(i);
                                    Log.d("methodId", methodId+"");
                                    for (PaymentMethod method: methodList){
                                        if (method.getId() == methodId){
                                            for (Account account: method.getAccounts()){
                                                accountNameList.add(account.getAccountIssuerInstitutionName());
                                                accountIdList.add(account.getId()+"");
                                            }
                                        }
                                    }

                                    ArrayAdapter arrayAdapter = new ArrayAdapter(PembayaranActivity.this, R.layout.custom_spinner_payment_method, accountNameList);
                                    arrayAdapter.setDropDownViewResource(R.layout.custom_spinner_drop_item_payment_method);
                                    spinner_sub_payment_method.setAdapter(arrayAdapter);
                                    spinner_sub_payment_method.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                            preffered_payment_account_id = accountIdList.get(i);
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> adapterView) {

                                        }
                                    });
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {

                                }
                            });
                        }
                    } else {
                        MethodUtil.getDialogWarning(response.errorBody(), PembayaranActivity.this);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    MethodUtil.getDialogWarningCatch(PembayaranActivity.this);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<PaymentMethod>>> call, Throwable t) {
                t.printStackTrace();
                Loading.hide(PembayaranActivity.this);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        PembayaranActivity.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}