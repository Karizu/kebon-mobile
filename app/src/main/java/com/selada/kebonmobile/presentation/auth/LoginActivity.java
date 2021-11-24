package com.selada.kebonmobile.presentation.auth;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.DeviceInfo;
import com.selada.kebonmobile.model.LoginModel;
import com.selada.kebonmobile.model.response.ApiResponse;
import com.selada.kebonmobile.model.response.LoginResponse;
import com.selada.kebonmobile.model.response.errormsg.ErrorResponse;
import com.selada.kebonmobile.network.ApiCore;
import com.selada.kebonmobile.presentation.MainActivity;
import com.selada.kebonmobile.util.Loading;
import com.selada.kebonmobile.util.MethodUtil;
import com.selada.kebonmobile.util.MyFirebaseMessagingService;
import com.selada.kebonmobile.util.PreferenceManager;

import java.lang.reflect.Field;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.editTextEmail)
    EditText editTextEmail;
    @BindView(R.id.editTextPass)
    EditText editTextPass;

    private String firebaseToken = "";
    private String deviceBrand = "";
    private String deviceType = "";
    private String deviceSeries = "";
    private String deviceOs = "";

    @OnClick(R.id.btn_lanjutkan)
    void onClickBtnLanjutkan(){
        if (editTextEmail.getText().toString().equals("")) {
            editTextEmail.setError("Email harus diisi");
        } else if (editTextPass.getText().toString().equals("")){
            editTextPass.setError("Password harus diisi");
        } else if (!editTextEmail.getText().toString().contains("@")){
            editTextEmail.setError("Format email salah");
        } else {
            doLogin();
        }
    }

    private void doLogin() {
        LoginModel loginModel = new LoginModel();
        loginModel.setUsername(editTextEmail.getText().toString());
        loginModel.setPassword(editTextPass.getText().toString());
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setDevice_firebase_token(firebaseToken);
        deviceInfo.setDevice_brand(deviceBrand);
        deviceInfo.setDevice_series(deviceSeries);
        deviceInfo.setDevice_type(deviceType);
        deviceInfo.setDevice_os(deviceOs);
        loginModel.setDevice_info(deviceInfo);

        Loading.show(this);
        ApiCore.apiInterface().signIn(loginModel).enqueue(new Callback<ApiResponse<LoginResponse>>() {
            @Override
            public void onResponse(Call<ApiResponse<LoginResponse>> call, Response<ApiResponse<LoginResponse>> response) {
                Loading.hide(LoginActivity.this);
                try {
                    if (response.isSuccessful()){
                        LoginResponse loginResponse = Objects.requireNonNull(response.body()).getData();
                        String token = loginResponse.getAuthToken();
                        Log.d("x_token", token);

                        PreferenceManager.setIsLogin();
                        PreferenceManager.setSessionToken("Bearer " + token);
                        PreferenceManager.setLoginResponse(loginResponse);

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        LoginActivity.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    } else {
                        Gson gson = new Gson();
                        ErrorResponse message = gson.fromJson(Objects.requireNonNull(response.errorBody()).charStream(),ErrorResponse.class);
                        String errorMessage = message.getData().getMessage();
                        Dialog dialog = MethodUtil.getDialogCart(R.layout.dialog_warning, LoginActivity.this);
                        TextView desc = dialog.findViewById(R.id.tv_desc);
                        desc.setText(errorMessage);
                        ImageView btn_close = dialog.findViewById(R.id.btn_close);
                        btn_close.setOnClickListener(view -> {
                            dialog.dismiss();
                        });
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(LoginActivity.this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<LoginResponse>> call, Throwable t) {
                t.printStackTrace();
                Loading.hide(LoginActivity.this);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        new PreferenceManager(this);
        initComponent();
    }

    private void initComponent() {
        firebaseToken = MyFirebaseMessagingService.getToken(this);
        deviceBrand = Build.BRAND;
        deviceType = Build.MODEL;
        deviceSeries = Build.MANUFACTURER;
        deviceOs = getOsDevice();

        Log.d("firebaseToken", firebaseToken);
        Log.d("deviceOs", deviceOs);
        try {
            String  details =  "VERSION.RELEASE : "+Build.VERSION.RELEASE
                    +"\nVERSION.INCREMENTAL : "+Build.VERSION.INCREMENTAL
                    +"\nVERSION.SDK.NUMBER : "+Build.VERSION.SDK_INT
                    +"\nBOARD : "+Build.BOARD
                    +"\nBOOTLOADER : "+Build.BOOTLOADER
                    +"\nBRAND : "+Build.BRAND
                    +"\nCPU_ABI : "+Build.CPU_ABI
                    +"\nCPU_ABI2 : "+Build.CPU_ABI2
                    +"\nDISPLAY : "+Build.DISPLAY
                    +"\nFINGERPRINT : "+Build.FINGERPRINT
                    +"\nHARDWARE : "+Build.HARDWARE
                    +"\nHOST : "+Build.HOST
                    +"\nID : "+Build.ID
                    +"\nMANUFACTURER : "+Build.MANUFACTURER
                    +"\nMODEL : "+Build.MODEL
                    +"\nPRODUCT : "+Build.PRODUCT
                    +"\nSERIAL : "+Build.SERIAL
                    +"\nTAGS : "+Build.TAGS
                    +"\nTIME : "+Build.TIME
                    +"\nTYPE : "+Build.TYPE
                    +"\nUNKNOWN : "+Build.UNKNOWN
                    +"\nUSER : "+Build.USER;

            Log.d("Device Details", details);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private String getOsDevice(){
        StringBuilder builder = new StringBuilder();
        builder.append("android : ").append(Build.VERSION.RELEASE);

        Field[] fields = Build.VERSION_CODES.class.getFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            int fieldValue = -1;

            try {
                fieldValue = field.getInt(new Object());
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

            if (fieldValue == Build.VERSION.SDK_INT) {
                builder.append(" : ").append(fieldName).append(" : ");
                builder.append("sdk=").append(fieldValue);
            }
        }

        return builder.toString();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}