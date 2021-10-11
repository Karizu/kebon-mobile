package com.selada.kebonmobile.util;

import android.content.Context;

import androidx.annotation.NonNull;
import com.orhanobut.hawk.Hawk;
import com.selada.kebonmobile.model.SewaLahanModel;
import com.selada.kebonmobile.model.response.LoginResponse;


import java.util.List;

public class PreferenceManager {

    private static final String IS_LOGIN = "isLogin";
    private static final String USER_STATUS = "userStatus";
    private static final String SESSION_TOKEN = "sessionToken";
    private static final String SEWA_LAHAN = "sewaLahan";
    private static final String LOGIN_RESPONSE = "loginResponse";


    private static Context ctx;
    private static PreferenceManager mInstance;

    public PreferenceManager(Context context) {
//        Hawk.init(context)
//                .setEncryptionMethod(HawkBuilder.EncryptionMethod.HIGHEST)
//                .setStorage(HawkBuilder.newSharedPrefStorage(context))
//                .setPassword("P@ssw0rd123")
//                .build();
        Hawk.init(context).build();
    }

    public static synchronized PreferenceManager getInstance(Context context){
        if (mInstance == null)
            mInstance = new PreferenceManager(context);
        return mInstance;
    }

    public static void setIsLogin(){
        Hawk.put(IS_LOGIN, true);
    }

    public static boolean getIsLogin() {
        return Hawk.get(IS_LOGIN, false);
    }

    public static void setSessionToken(String sessionToken){
        Hawk.put(SESSION_TOKEN, sessionToken);
    }

    public static String getSessionToken() {
        return Hawk.get(SESSION_TOKEN, "");
    }

    public static void logOut() {
        Hawk.put(IS_LOGIN, false);
        Hawk.deleteAll();
    }

    public static void setUserStatus(int userStatus){
        Hawk.put(USER_STATUS, userStatus);
    }

    public static int getUserStatus() {
        return Hawk.get(USER_STATUS, Constant.GUEST);
    }

    public static void setSewaLahan(SewaLahanModel sewaLahanModel){
        Hawk.put(SEWA_LAHAN, sewaLahanModel);
    }

    public static SewaLahanModel getSewaLahan() {
        return Hawk.get(SEWA_LAHAN, null);
    }

    public static void setLoginResponse(LoginResponse loginResponse){
        Hawk.put(LOGIN_RESPONSE, loginResponse);
    }

    public static LoginResponse getLoginResponse() {
        return Hawk.get(LOGIN_RESPONSE, null);
    }


}
