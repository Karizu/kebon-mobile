package com.selada.kebonmobile.presentation.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.selada.kebonmobile.R;
import com.selada.kebonmobile.model.DeviceInfo;
import com.selada.kebonmobile.model.RegisterModel;
import com.selada.kebonmobile.model.response.ApiResponse;
import com.selada.kebonmobile.model.response.LoginResponse;
import com.selada.kebonmobile.model.response.RegisterResponse;
import com.selada.kebonmobile.model.response.errormsg.ErrorResponse;
import com.selada.kebonmobile.network.ApiCore;
import com.selada.kebonmobile.presentation.MainActivity;
import com.selada.kebonmobile.presentation.home.pembayaran.PembayaranActivity;
import com.selada.kebonmobile.util.Loading;
import com.selada.kebonmobile.util.MethodUtil;
import com.selada.kebonmobile.util.MyFirebaseMessagingService;
import com.selada.kebonmobile.util.PreferenceManager;

import java.lang.reflect.Field;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.anshul.libray.PasswordEditText;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register3Activity extends AppCompatActivity {

    @BindView(R.id.editTextPass)
    PasswordEditText editTextPass;
    @BindView(R.id.editTextKonfirmPass)
    PasswordEditText editTextKonfirmPass;

    private String name, email, phone;
    private String firebaseToken = "";
    private String deviceBrand = "";
    private String deviceType = "";
    private String deviceSeries = "";
    private String deviceOs = "";

    @OnClick(R.id.btn_lanjutkan)
    void onClickLnajutkan(){
        if (!editTextKonfirmPass.getText().toString().equals(editTextPass.getText().toString())){
            editTextKonfirmPass.setError("Password tidak sesuai");
        } else {
            doRegister();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register3);
        ButterKnife.bind(this);

        initComponent();
    }

    private void initComponent() {
        if (getIntent()!=null){
            name = getIntent().getStringExtra("name");
            phone = getIntent().getStringExtra("phone");
            email = getIntent().getStringExtra("email");
        }

        firebaseToken = MyFirebaseMessagingService.getToken(this);
        deviceBrand = Build.BRAND;
        deviceType = Build.MODEL;
        deviceSeries = Build.MANUFACTURER;
        deviceOs = getOsDevice();
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

    private void doRegister() {
        RegisterModel registerModel = new RegisterModel();
        registerModel.setFullname(name);
        registerModel.setUsername(email);
        registerModel.setPrimary_contact_phone(phone);
        registerModel.setPassword(editTextPass.getText().toString());
        registerModel.setConf_password(editTextKonfirmPass.getText().toString());
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setDevice_firebase_token(firebaseToken);
        deviceInfo.setDevice_brand(deviceBrand);
        deviceInfo.setDevice_series(deviceSeries);
        deviceInfo.setDevice_type(deviceType);
        deviceInfo.setDevice_os(deviceOs);
        registerModel.setDevice_info(deviceInfo);

        Loading.show(this);
        ApiCore.apiInterface().register(registerModel).enqueue(new Callback<ApiResponse<LoginResponse>>() {
            @Override
            public void onResponse(Call<ApiResponse<LoginResponse>> call, Response<ApiResponse<LoginResponse>> response) {
                Loading.hide(Register3Activity.this);
                try {
                    if (response.isSuccessful()){
                        LoginResponse registerResponse = response.body().getData();
                        String token = registerResponse.getAuthToken();
                        Log.d("x_token", token);

                        PreferenceManager.setIsLogin();
                        PreferenceManager.setSessionToken("Bearer " + token);
                        PreferenceManager.setLoginResponse(registerResponse);

                        Intent intent = new Intent(Register3Activity.this, VerificationEmailActivity.class);
                        intent.putExtra("name", name);
                        intent.putExtra("email", email);
                        intent.putExtra("password", editTextPass.getText().toString());
                        intent.putExtra("conf_password", editTextKonfirmPass.getText().toString());
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        Register3Activity.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    } else {
                        Gson gson = new Gson();
                        ErrorResponse message = gson.fromJson(Objects.requireNonNull(response.errorBody()).charStream(),ErrorResponse.class);
                        String errorMessage = message.getData().getMessage();
                        Dialog dialog = MethodUtil.getDialogCart(R.layout.dialog_warning, Register3Activity.this);
                        TextView desc = dialog.findViewById(R.id.tv_desc);
                        desc.setText(errorMessage);
                        ImageView btn_close = dialog.findViewById(R.id.btn_close);
                        btn_close.setOnClickListener(view -> {
                            dialog.dismiss();
                        });
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    String errorMessage = "Terjadi kesalahan (Response Code: On Catch)";
                    Dialog dialog = MethodUtil.getDialogCart(R.layout.dialog_warning, Register3Activity.this);
                    TextView desc = dialog.findViewById(R.id.tv_desc);
                    desc.setText(errorMessage);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<LoginResponse>> call, Throwable t) {
                Loading.hide(Register3Activity.this);
                t.printStackTrace();
            }
        });
    }
}