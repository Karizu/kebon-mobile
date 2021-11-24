package com.selada.kebonmobile.presentation.home.pembayaran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.selada.kebonmobile.R;
import com.selada.kebonmobile.SplashActivity;
import com.selada.kebonmobile.model.SewaLahanModel;
import com.selada.kebonmobile.model.response.invoice.InvoiceDetailResponse;
import com.selada.kebonmobile.model.response.leasesite.Account;
import com.selada.kebonmobile.model.response.leasesite.LeaseSiteResponse;
import com.selada.kebonmobile.model.response.leasesite.PaymentMethod;
import com.selada.kebonmobile.presentation.MainActivity;
import com.selada.kebonmobile.presentation.auth.Register2Activity;
import com.selada.kebonmobile.presentation.auth.Register3Activity;
import com.selada.kebonmobile.presentation.home.pembayaran.adapter.RekeningAdapter;
import com.selada.kebonmobile.util.Constant;
import com.selada.kebonmobile.util.MethodUtil;
import com.selada.kebonmobile.util.PreferenceManager;
import com.selada.kebonmobile.util.RequestPermissionHandler;
import com.skydoves.elasticviews.ElasticButton;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RincianPembayaranActivity extends AppCompatActivity {

    @BindView(R.id.rv_rekening)
    RecyclerView rv_rekening;
    @BindView(R.id.tv_farm_name)
    TextView tv_farm_name;
    @BindView(R.id.tv_jumlah_kavling)
    TextView tv_jumlah_kavling;
    @BindView(R.id.jumlah_kavling)
    TextView label_jumlah_kavling;
    @BindView(R.id.tv_lama_sewa)
    TextView tv_lama_sewa;
    @BindView(R.id.tv_jumlah_pembayaran)
    TextView tv_jumlah_pembayaran;
    @BindView(R.id.tv_metode_bayar)
    TextView tv_metode_bayar;
    @BindView(R.id.tv_due_date)
    TextView tv_due_date;
    @BindView(R.id.tv_status_bayar)
    TextView tv_status_bayar;
    @BindView(R.id.tv_total_bayar)
    TextView tv_total_bayar;
    @BindView(R.id.img_status_bayar)
    ImageView img_status_bayar;
    @BindView(R.id.constraintLayout4)
    ConstraintLayout constraintLayout4;
    @BindView(R.id.layout_petunjuk)
    LinearLayout layout_petunjuk;
    @BindView(R.id.layout_atm)
    ConstraintLayout layout_atm;
    @BindView(R.id.layout_payment_method_success)
    LinearLayout layout_payment_method_success;
    @BindView(R.id.tv_metode_bayar_2)
    TextView tv_metode_bayar_2;
    @BindView(R.id.layout_qris)
    ConstraintLayout layout_qris;
    @BindView(R.id.btn_konfirmasi_pembayaran)
    ElasticButton btn_konfirmasi_pembayaran;

    private String metodeBayar = "";
    private String qris_url = "";
    private RequestPermissionHandler mRequestPermissionHandler;
    private String farmName;
    private String jumlahKavling;
    private String lamaSewa;
    private String jumlahPembayaran;
    private String due_date;
    private String status_bayar;
    private String invoice_id;
    private String dateTime;
    private String objName = "";
    private String orderNo = "";
    private String labelSewa = "";
    private String desc = "";
    private boolean isFromAdapter = false;

    @OnClick(R.id.btn_unduh)
    void onClickBtnUnduh(){
        downloadImage(qris_url);
    }

    @OnClick(R.id.btn_petunjuk)
    void onClickBtnPetunjuk(){
        if (metodeBayar.equals("QRIS")) {
            if (layout_qris.getVisibility() == View.GONE){
                mRequestPermissionHandler.requestPermission(this, new String[] {
                        Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE
                }, 123, new RequestPermissionHandler.RequestPermissionListener() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onFailed() {

                    }
                });
                layout_qris.setVisibility(View.VISIBLE);
            } else {
                layout_qris.setVisibility(View.GONE);
            }
        } else {
            if (layout_atm.getVisibility() == View.GONE){
                layout_atm.setVisibility(View.VISIBLE);
            } else {
                layout_atm.setVisibility(View.GONE);
            }
        }
    }

    @OnClick(R.id.imageView10)
    void onClickChatCs(){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(MethodUtil.getUrlCsData()));
        startActivity(i);
    }

    @OnClick(R.id.btn_beranda)
    void onClickBeranda(){
        Intent intent = new Intent(RincianPembayaranActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.btn_konfirmasi_pembayaran)
    void onClickKonfirmasiPembayaran(){
        String number = MethodUtil.getPhoneCs();
        String msg = "Hallo Kebon \nsaya ingin konfirmasi pesanan " + desc + "dengan detail berikut: "+ "\n";
        String order = "Nomor Order: " + orderNo + "\n";
        String farm = "Nama Lahan: "+ farmName + "\n";
        String total = "Jumlah : "+ jumlahKavling + " " + objName + "\n";
        String lama = "Lama Sewa : "+ lamaSewa + " " +"\n";
        String metode = "Metode Pembayaran : " + metodeBayar + "\n";
        String pay = "Jumlah Pembayaran : "+ jumlahPembayaran +"\n";
        String url = "https://api.whatsapp.com/send?phone="+number;
        try {
            url = "https://api.whatsapp.com/send?phone="+number+"&text=" + URLEncoder.encode(
                    msg + order + farm + total + lama + metode + pay, "UTF-8"
            );
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rincian_pembayaran);
        ButterKnife.bind(this);
        new PreferenceManager(this);
        initComponent();
        mRequestPermissionHandler = new RequestPermissionHandler();
    }

    @SuppressLint("SetTextI18n")
    private void initComponent() {
        if (PreferenceManager.isFirstOpen()){
            PreferenceManager.setIsFirstOpen(false);
            PreferenceManager.setUserStatus(Constant.ON_HOLD);
        }

        if (getIntent()!=null){
            isFromAdapter = getIntent().getBooleanExtra("is_from_adapter", false);
            boolean isPaid;

            if (isFromAdapter){
                //From Adapter History dan Home

                String jsonResponse = getIntent().getStringExtra("invoice_detail_response");
                InvoiceDetailResponse detailResponse = new Gson().fromJson(jsonResponse, InvoiceDetailResponse.class);
                List<com.selada.kebonmobile.model.response.invoice.PaymentMethod> paymentMethods = detailResponse.getInvoice().getPaymentMethods();
                isPaid = detailResponse.getInvoice().getBilling().getPaidStatus();
                status_bayar = isPaid?"Berhasil":"Menunggu Pembayaran";

                if (!isPaid){
                    desc = detailResponse.getInvoice().getBilling().getDescription() + " ";
                    orderNo = detailResponse.getInvoice().getCode();
                    dateTime = detailResponse.getInvoice().getDueDate();
                    try { labelSewa = detailResponse.getInvoice().getBilling().getContract().getPackage_obj().getLeaseDurationName(); } catch (Exception e){}
                    String[] dateAndTime = MethodUtil.formatDateAndTime(dateTime.substring(0, dateTime.length()-4));
                    due_date = "Berlaku hingga: " + dateAndTime[0] + " " + dateAndTime[1];

                    metodeBayar = detailResponse.getInvoice().getBilling().getPreffered_payment_account().getPaymentMethod().getName();
                    List<Account> accountList = new ArrayList<>();
                    Account account = new Account();
                    account.setAccountIssuerInstitutionName(detailResponse.getInvoice().getBilling().getPreffered_payment_account().getAccountIssuerInstitutionName());
                    account.setAccountNumber(detailResponse.getInvoice().getBilling().getPreffered_payment_account().getAccountNumber());
                    account.setAccountName(detailResponse.getInvoice().getBilling().getPreffered_payment_account().getAccountName());
                    account.setImage(detailResponse.getInvoice().getBilling().getPreffered_payment_account().getImage());
                    accountList.add(account);

                    qris_url = detailResponse.getInvoice().getBilling().getPreffered_payment_account().getImage().getFullpath();

                    RekeningAdapter rekeningAdapter = new RekeningAdapter(accountList, this, this);
                    rv_rekening.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
                    rv_rekening.setAdapter(rekeningAdapter);

                }
                else {
                    btn_konfirmasi_pembayaran.setVisibility(View.GONE);
                    dateTime = detailResponse.getInvoice().getPayment().getPaymentDate();
                    String[] dateAndTime = MethodUtil.formatDateAndTime(dateTime.substring(0, dateTime.length()-4));
                    due_date = "Waktu selesai: " + dateAndTime[0] + " " + dateAndTime[1];

                    metodeBayar = detailResponse.getInvoice().getPayment().getAccount().getPayment_method().getName();
                    tv_metode_bayar_2.setText(metodeBayar);
                    layout_payment_method_success.setVisibility(View.VISIBLE);
                    constraintLayout4.setVisibility(View.GONE);
                    layout_petunjuk.setVisibility(View.GONE);
                    btn_konfirmasi_pembayaran.setVisibility(View.GONE);
                }

                farmName = detailResponse.getInvoice().getBilling().getContract().getPackage_obj().getSiteName();
                jumlahKavling = detailResponse.getInvoice().getBilling().getContract().getLeaseQuantity() + "";
                lamaSewa = detailResponse.getInvoice().getBilling().getContract().getLeaseDurationNumber() + " " + labelSewa;
                jumlahPembayaran = "Rp " + MethodUtil.toCurrencyNumber(detailResponse.getInvoice().getBilling().getContract().getLeaseTotalPrice());
                objName = detailResponse.getInvoice().getBilling().getContract().getPackage_obj().getObjectName();

            } else {
                //From Sewa Lahan
                String jsonResponse = getIntent().getStringExtra("lease_site_response");
                LeaseSiteResponse leaseSiteResponse = new Gson().fromJson(jsonResponse, LeaseSiteResponse.class);
                List<Account> accountList = new ArrayList<>();
                Account account = new Account();
                account.setAccountNumber(leaseSiteResponse.getFirstInvoice().getBilling().getPreffered_payment_account().getAccountNumber());
                account.setAccountIssuerInstitutionName(leaseSiteResponse.getFirstInvoice().getBilling().getPreffered_payment_account().getAccountIssuerInstitutionName());
                account.setAccountName(leaseSiteResponse.getFirstInvoice().getBilling().getPreffered_payment_account().getAccountName());
                account.setImage(leaseSiteResponse.getFirstInvoice().getBilling().getPreffered_payment_account().getImage());
                accountList.add(account);

                desc = leaseSiteResponse.getFirstInvoice().getBilling().getDescription() + " ";
                orderNo = leaseSiteResponse.getFirstInvoice().getCode();
                try { labelSewa = leaseSiteResponse.getContract().getPackage_obj().getLeaseDurationName(); } catch (Exception e){}

                qris_url = leaseSiteResponse.getFirstInvoice().getBilling().getPreffered_payment_account().getImage().getFullpath();

                RekeningAdapter rekeningAdapter = new RekeningAdapter(accountList, this, this);
                rv_rekening.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
                rv_rekening.setAdapter(rekeningAdapter);

                String dateTimes = leaseSiteResponse.getFirstInvoice().getDueDate();
                String[] dateAndTime = MethodUtil.formatDateAndTime(dateTimes.substring(0, dateTimes.length()-4));

                metodeBayar = leaseSiteResponse.getFirstInvoice().getBilling().getPreffered_payment_account().getPaymentMethod().getName();
                farmName = leaseSiteResponse.getContract().getPackage().getPackageName();
                jumlahKavling = leaseSiteResponse.getContract().getLeaseQuantity() + " ";
                lamaSewa = leaseSiteResponse.getContract().getLeaseDurationNumber() + " " + labelSewa;
                jumlahPembayaran = "Rp " + MethodUtil.toCurrencyNumber(leaseSiteResponse.getContract().getLeaseTotalPrice());
                due_date = "Berlaku hingga: " + dateAndTime[0] + " " + dateAndTime[1];
                status_bayar = leaseSiteResponse.getFirstInvoice().getBilling().getPaidStatus() ? "Berhasil":"Menunggu Pembayaran";
                try { objName = leaseSiteResponse.getContract().getPackage_obj().getObjectName(); } catch (Exception e){}
            }

            tv_farm_name.setText(farmName);
            tv_jumlah_kavling.setText(jumlahKavling + " " + objName);
            tv_lama_sewa.setText(lamaSewa);
            tv_status_bayar.setText(status_bayar);

            if (status_bayar.equals("Berhasil")) {
                tv_status_bayar.setTextColor(getResources().getColor(R.color.colorGreenText));
                img_status_bayar.setImageDrawable(getResources().getDrawable(R.drawable.ic_paid_status_true));
            }

            tv_metode_bayar.setText(metodeBayar.replace("_", " ").toUpperCase());
            tv_jumlah_pembayaran.setText(jumlahPembayaran);
            tv_total_bayar.setText(jumlahPembayaran);
            tv_due_date.setText(due_date);
        }
    }

    private void downloadImage(String url) {
        Toast.makeText(RincianPembayaranActivity.this, "Mengunduh gambar..", Toast.LENGTH_SHORT).show();
        String filename = "qris_selada.jpg";
        String DIR_NAME = "Kebon App";

        File direct =
                new File(Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                        .getAbsolutePath() + "/" + DIR_NAME + "/");


        if (!direct.exists()) {
            direct.mkdir();
            Log.d("DownloadManager", "dir created for first time");
        }

        DownloadManager dm = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        Uri downloadUri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(downloadUri);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false)
                .setTitle(filename)
                .setMimeType("image/jpeg")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES,
                        File.separator + DIR_NAME + File.separator + filename);

        dm.enqueue(request);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (!isFromAdapter){
            Intent intent = new Intent(RincianPembayaranActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }
}