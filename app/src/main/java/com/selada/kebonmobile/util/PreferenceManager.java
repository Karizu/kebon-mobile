package com.selada.kebonmobile.util;

import android.content.Context;

import androidx.annotation.NonNull;
import com.orhanobut.hawk.Hawk;


import java.util.List;

public class PreferenceManager {

    private static final String SESSION_TOKEN = "sessionToken";
    private static final String IS_LOGIN = "isLogin";
    private static final String USER_STATUS = "userStatus";


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

    public static void setSessionToken(String sessionToken){
        Hawk.put(SESSION_TOKEN, sessionToken);
    }

    public static String getSessionToken() {
        return Hawk.get(SESSION_TOKEN, "");
    }

    public static void logOut() {
        Hawk.put(IS_LOGIN, false);
    }

    public static void setUserStatus(int userStatus){
        Hawk.put(USER_STATUS, userStatus);
    }

    public static int getUserStatus() {
        return Hawk.get(USER_STATUS, Constant.GUEST);
    }



}
