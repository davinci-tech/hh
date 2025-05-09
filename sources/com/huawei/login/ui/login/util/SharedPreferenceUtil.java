package com.huawei.login.ui.login.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Process;
import android.text.TextUtils;
import com.huawei.common.Constant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdataaccessmodel.utils.StorageDataCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import health.compact.a.EnvironmentUtils;
import health.compact.a.KeyStoreUtils;
import health.compact.a.KeyValDbManager;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes.dex */
public class SharedPreferenceUtil {
    private static final String ACCESS_TOKEN = "access_token";
    private static final String ACCOUNT_INFO_CACHE = "account_info_cache";
    private static final String ACCOUNT_NAME = "account_name";
    private static final String ACCOUNT_TYPE = "account_type";
    private static final String ACCOUNT_TYPE_HMS = "account_type_hms";
    private static final String AUTH_CODE = "auth_code";
    private static final String BIRTH_DATE = "birth_date";
    private static final String CARD_DATA_INFO_CACHE = "card_data_info_cache";
    private static final String COUNTRY_CODE = "country_code";
    private static final int DECIMAL_10 = 10;
    private static final String DEVICE_TYPE = "device_type";
    private static final String EMAIL = "email";
    private static final String GENDER = "gender";
    private static final String GUARDIAN_ACCOUNT = "guardianAccount";
    private static final String GUARDIAN_UID = "guardianUid";
    private static final String HEALTH_LOGIN_CHANNEL = "health_login_channel";
    private static final String IS_LOGINED = "is_logined";
    private static final String LITE_ACCESS_TOKEN = "lite_access_token";
    private static final String LITE_ACCESS_TOKEN_EXPIRE_TIME = "accessTokenExpireTime";
    private static final String LITE_REFRESH_TOKEN = "lite_refresh_token";
    private static final String LITE_REFRESH_TOKEN_EXPIRE_TIME = "refreshTokenExpireTime";
    private static final Object LOCK_OBJECT = new Object();
    private static final String LOGIN_DATA = "login_data";
    private static final String LOGIN_TYPE = "login_type";
    private static final String MOBILE = "mobile";
    private static final String PLAINTEXT_ACCOUNT_NAME = "plainTextAccountName";
    private static final String SERVER_COUNTRY_TIME = "server_country_time";
    private static final String SESSION_ID = "session_id";
    private static final String SEVER_TOKEN = "server_token";
    private static final String SITE_ID = "site_id";
    private static final String TAG = "PLGLOGIN_ShPrefUt";
    private static final String USER_ID = "user_id";
    private static Context mContext;
    private static SharedPreferenceUtil mSharedUtil;

    private SharedPreferenceUtil() {
    }

    public static SharedPreferenceUtil getInstance(Context context) {
        SharedPreferenceUtil sharedPreferenceUtil;
        synchronized (LOCK_OBJECT) {
            if (mContext == null) {
                mContext = context.getApplicationContext();
            }
            if (mSharedUtil == null) {
                mSharedUtil = new SharedPreferenceUtil();
            }
            sharedPreferenceUtil = mSharedUtil;
        }
        return sharedPreferenceUtil;
    }

    public void setSeverToken(String str, StorageDataCallback storageDataCallback) {
        String str2;
        LoginCache.configServerToken(str);
        KeyValDbManager.b(mContext).a(SEVER_TOKEN, str, new StorageParams(14));
        if (TextUtils.isEmpty(str)) {
            str2 = "";
        } else {
            str2 = KeyStoreUtils.b(str);
            LogUtil.a(TAG, "setSeverToken is not null=", Boolean.valueOf(TextUtils.isEmpty(str2)));
        }
        SharedPreferenceManager.e(String.valueOf(20000), SEVER_TOKEN, str2, (StorageParams) null, storageDataCallback);
    }

    public void setSiteID(int i, StorageDataCallback storageDataCallback) {
        SharedPreferenceManager.e(String.valueOf(20000), SITE_ID, String.valueOf(i), new StorageParams(1), storageDataCallback);
        LogUtil.a(TAG, "setSiteID completed");
        LogUtil.c(TAG, " site id:", Integer.valueOf(i));
    }

    public int getSiteID() {
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(20000), SITE_ID);
        if (TextUtils.isEmpty(b)) {
            LogUtil.a(TAG, "getSiteID() id = null");
            return 0;
        }
        try {
            return Integer.parseInt(b, 10);
        } catch (Exception unused) {
            LogUtil.a(TAG, "getSiteID() parse int error");
            LogUtil.c(TAG, "id = ", b);
            return 0;
        }
    }

    public void setHuaweiAccountLoginFlag(String str) {
        Context context = mContext;
        if (context == null) {
            LogUtil.b(TAG, "setHuaweiAccountLoginFlag: mContext is null !");
        } else {
            SharedPreferenceManager.e(context, String.valueOf(20000), "huawei_account_login_init", str, new StorageParams(1));
            LogUtil.a(TAG, "setHuaweiAccountLoginFlag:", str);
        }
    }

    public void setUserID(String str) {
        if (mContext == null) {
            LogUtil.b(TAG, "setUserID: mContext is null !");
            return;
        }
        ReleaseLogUtil.b(TAG, "setUserID uid is isEmpty:", Boolean.valueOf(TextUtils.isEmpty(str)));
        KeyValDbManager.b(mContext).e("user_id", str);
        LogUtil.c(TAG, "userid is : ", str);
    }

    public String getUserID() {
        Context context = mContext;
        if (context == null) {
            LogUtil.b(TAG, "getUserID: mContext is null !");
            return "";
        }
        String e = KeyValDbManager.b(context).e("user_id");
        LogUtil.c(TAG, "userid is:", e, ";pid:", Integer.valueOf(Process.myPid()));
        return e;
    }

    public void setAccountName(String str) {
        if (mContext == null) {
            LogUtil.b(TAG, "setAccountName: mContext is null !");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            SharedPreferenceManager.e(mContext, LOGIN_DATA, ACCOUNT_NAME, "", (StorageParams) null);
        } else {
            SharedPreferenceManager.e(mContext, LOGIN_DATA, ACCOUNT_NAME, KeyStoreUtils.b(str), (StorageParams) null);
        }
        LogUtil.a(TAG, "setAccountName completed !!!");
        LogUtil.c(TAG, "accountName is : ", str);
    }

    public String getAccountName() {
        Context context = mContext;
        if (context == null) {
            LogUtil.b(TAG, "getAccountName: mContext is null !");
            return "";
        }
        String b = SharedPreferenceManager.b(context, LOGIN_DATA, ACCOUNT_NAME);
        byte[] e = KeyStoreUtils.e(b);
        if (e != null) {
            b = new String(e, StandardCharsets.UTF_8);
        }
        LogUtil.c(TAG, "setAccountName completed !!! accountName is:", b, ";pid:", Integer.valueOf(Process.myPid()));
        return b;
    }

    public void setPlainTextAccountName(String str) {
        LogUtil.a(TAG, "setPlainTextAccountName enter.", str);
        setSharedPreference(mContext.getSharedPreferences(LOGIN_DATA, 0), PLAINTEXT_ACCOUNT_NAME, str, true);
    }

    public String getPlainTextAccountName() {
        String sharedPreference = getSharedPreference(mContext.getSharedPreferences(LOGIN_DATA, 0), PLAINTEXT_ACCOUNT_NAME, true);
        LogUtil.a(TAG, "getPlainTextAccountName enter.", sharedPreference);
        return sharedPreference;
    }

    public void setGender(String str) {
        LogUtil.a(TAG, "setGender gender.");
        Context context = mContext;
        if (context == null) {
            LogUtil.b(TAG, "setGender: mContext is null !");
        } else {
            SharedPreferenceManager.e(context, LOGIN_DATA, "gender", str, new StorageParams(2));
        }
    }

    public String getGender() {
        Context context = mContext;
        if (context != null) {
            return SharedPreferenceManager.b(context, LOGIN_DATA, "gender");
        }
        LogUtil.b(TAG, "getGender: mContext is null !");
        return null;
    }

    public String getBirthDate() {
        Context context = mContext;
        if (context != null) {
            byte[] e = KeyStoreUtils.e(SharedPreferenceManager.b(context, LOGIN_DATA, BIRTH_DATE));
            if (e != null) {
                return new String(e, StandardCharsets.UTF_8);
            }
        } else {
            LogUtil.b(TAG, "getBirthDate: mContext is null !");
        }
        return null;
    }

    public void setBirthDate(String str) {
        LogUtil.a(TAG, "setBirthDate birthDate.");
        Context context = mContext;
        if (context == null) {
            LogUtil.b(TAG, "setBirthDate: mContext is null !");
        } else {
            SharedPreferenceManager.e(context, LOGIN_DATA, BIRTH_DATE, KeyStoreUtils.b(str), new StorageParams(2));
        }
    }

    public void setDeviceType(String str) {
        Context context = mContext;
        if (context == null) {
            LogUtil.b(TAG, "setDeviceType: mContext is null !");
        } else {
            context.getSharedPreferences(LOGIN_DATA, 0).edit().putString("device_type", str).commit();
            LogUtil.a(TAG, "setDeviceType completed !!! deviceType is : ", str);
        }
    }

    public String getDeviceType() {
        Context context = mContext;
        if (context == null) {
            LogUtil.b(TAG, "getDeviceType: mContext is null !");
            return "";
        }
        String string = context.getSharedPreferences(LOGIN_DATA, 0).getString("device_type", null);
        LogUtil.c(TAG, "getDeviceType completed !!! deviceType is:", string, ";pid:", Integer.valueOf(Process.myPid()));
        return string;
    }

    public void setLoginType(int i) {
        BaseApplication.getContext().getSharedPreferences(LOGIN_DATA, 0).edit().putInt(LOGIN_TYPE, i).commit();
        LogUtil.a(TAG, "setLoginType completed !!!");
    }

    public int getLoginType() {
        SharedPreferences sharedPreferences = BaseApplication.getContext().getSharedPreferences(LOGIN_DATA, 0);
        LogUtil.a(TAG, "getLoginType completed !!!");
        return sharedPreferences.getInt(LOGIN_TYPE, -1);
    }

    public void setHealthLoginChannel(int i) {
        KeyValDbManager.b(mContext).d(HEALTH_LOGIN_CHANNEL, String.valueOf(i), null);
        BaseApplication.getContext().getSharedPreferences(LOGIN_DATA, 0).edit().putInt(HEALTH_LOGIN_CHANNEL, i).commit();
        LogUtil.a(TAG, "setHealthLoginChannel completed !!!");
    }

    public int getHealthLoginChannel() {
        SharedPreferences sharedPreferences = BaseApplication.getContext().getSharedPreferences(LOGIN_DATA, 0);
        LogUtil.c(TAG, "getHealthLoginChannel completed !!!");
        return sharedPreferences.getInt(HEALTH_LOGIN_CHANNEL, -1);
    }

    public void setSessionID(String str) {
        Context context = mContext;
        if (context == null) {
            LogUtil.b(TAG, "setSessionID: mContext is null !");
        } else {
            context.getSharedPreferences(LOGIN_DATA, 0).edit().putString(SESSION_ID, str).commit();
            LogUtil.a(TAG, "setSessionID completed !!!");
        }
    }

    public String getSessionID() {
        Context context = mContext;
        if (context == null) {
            LogUtil.b(TAG, "getSessionID: mContext is null !");
            return "";
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(LOGIN_DATA, 0);
        LogUtil.a(TAG, "getSessionID completed !!!");
        return sharedPreferences.getString(SESSION_ID, null);
    }

    public void setAccessToken(String str, StorageDataCallback storageDataCallback) {
        SharedPreferenceManager.e(String.valueOf(20000), "access_token", str, new StorageParams(1), storageDataCallback);
        LogUtil.a(TAG, "setAccessToken completed !!!");
    }

    public String getAccessToken() {
        return SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(20000), "access_token");
    }

    public void setIsLogined(boolean z) {
        if (mContext == null) {
            LogUtil.b(TAG, "setIsLogined: mContext is null !");
        } else {
            SharedPreferenceManager.e(LOGIN_DATA, IS_LOGINED, z);
            LogUtil.a(TAG, "setIsLogined isLogined= ", Boolean.valueOf(z));
        }
    }

    public void retrySetIsLogined() {
        if (getIsLogined()) {
            return;
        }
        setIsLogined(true);
        LogUtil.a(TAG, "retrySetIsLogined isLogined true");
    }

    public boolean getIsLogined() {
        if (mContext == null) {
            LogUtil.b(TAG, "getIsLogined: mContext is null !");
            return false;
        }
        return SharedPreferenceManager.a(LOGIN_DATA, IS_LOGINED, false);
    }

    private static void setSharedPreference(SharedPreferences sharedPreferences, String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str2)) {
            sharedPreferences.edit().remove(str).commit();
            return;
        }
        if (z) {
            String e = KeyStoreUtils.e(str2.getBytes(StandardCharsets.UTF_8));
            if (TextUtils.isEmpty(e)) {
                return;
            }
            sharedPreferences.edit().putString(str, e).commit();
            return;
        }
        sharedPreferences.edit().putString(str, str2).commit();
    }

    private static String getSharedPreference(SharedPreferences sharedPreferences, String str, boolean z) {
        String string = sharedPreferences.getString(str, null);
        if (!z || TextUtils.isEmpty(string)) {
            return string;
        }
        byte[] e = KeyStoreUtils.e(string);
        if (e != null) {
            return new String(e, StandardCharsets.UTF_8);
        }
        return null;
    }

    public void setCountryCode(String str) {
        setSharedPreference(mContext.getSharedPreferences(LOGIN_DATA, 0), "country_code", str, true);
        LoginCache.configCountryCode(str);
    }

    public String getCountryCode() {
        String countryCode = LoginCache.getCountryCode();
        if (!TextUtils.isEmpty(countryCode)) {
            return countryCode;
        }
        String sharedPreference = getSharedPreference(mContext.getSharedPreferences(LOGIN_DATA, 0), "country_code", true);
        if (TextUtils.isEmpty(LoginCache.getCountryCode())) {
            LoginCache.configCountryCode(sharedPreference);
        }
        return sharedPreference;
    }

    public void setAccountType(String str) {
        Context context = mContext;
        if (context == null) {
            LogUtil.h(TAG, "setAccountType: mContext is null !");
        } else {
            SharedPreferenceManager.e(context, String.valueOf(20000), ACCOUNT_TYPE, str, new StorageParams(1));
            LogUtil.a(TAG, "setAccountType completed :", str);
        }
    }

    public String getAccountType() {
        LogUtil.c(TAG, "Enter getAccountType");
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(20000), ACCOUNT_TYPE);
        LogUtil.a(TAG, "getAcType OK! ", b);
        return b;
    }

    public int saveAccountInfo(String str) {
        if (TextUtils.isEmpty(str)) {
            return SharedPreferenceManager.c(mContext, String.valueOf(20000), ACCOUNT_INFO_CACHE);
        }
        return SharedPreferenceManager.e(mContext, String.valueOf(20000), ACCOUNT_INFO_CACHE, str, new StorageParams(1));
    }

    public String restoreAccountInfo() {
        return SharedPreferenceManager.b(mContext, String.valueOf(20000), ACCOUNT_INFO_CACHE);
    }

    public void writeHealthData(String str, String str2) {
        setSharedPreference(mContext.getSharedPreferences(CARD_DATA_INFO_CACHE, 0), str, str2, true);
    }

    public String readHealthData(String str) {
        return getSharedPreference(mContext.getSharedPreferences(CARD_DATA_INFO_CACHE, 0), str, true);
    }

    public void deleteAllHealthData() {
        Set<String> d = SharedPreferenceManager.d(mContext, CARD_DATA_INFO_CACHE);
        if (!d.isEmpty()) {
            Iterator<String> it = d.iterator();
            while (it.hasNext()) {
                writeHealthData(it.next(), null);
            }
        }
        writeHealthData("version", EnvironmentUtils.a());
    }

    public void loadAllHealthData() {
        long currentTimeMillis = System.currentTimeMillis();
        LogUtil.a(TAG, "loadAllHealthData, newVersion=", EnvironmentUtils.a(), ", version=", readHealthData("version"), ", times=", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
    }

    public void setAuthCode(String str) {
        Context context = mContext;
        if (context == null) {
            LogUtil.h(TAG, "setAuthCode: mContext is null !");
        } else {
            SharedPreferenceManager.e(context, String.valueOf(20000), AUTH_CODE, str, new StorageParams(1));
        }
    }

    public String getAuthCode() {
        LogUtil.c(TAG, "Enter getAuthCode");
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(20000), AUTH_CODE);
        LogUtil.a(TAG, "getAuthCode completed !!!");
        return b;
    }

    public void setLiteAccessToken(String str) {
        Context context = mContext;
        if (context == null) {
            LogUtil.b(TAG, "setLiteAccessToken: mContext is null !");
        } else {
            SharedPreferenceManager.e(context, String.valueOf(20000), LITE_ACCESS_TOKEN, str, new StorageParams(1));
        }
    }

    public void setAtExpireTime(String str) {
        Context context = mContext;
        if (context == null) {
            LogUtil.b(TAG, "setAtExpireTime: mContext is null !");
        } else {
            SharedPreferenceManager.e(context, String.valueOf(20000), LITE_ACCESS_TOKEN_EXPIRE_TIME, str, new StorageParams(1));
            LogUtil.a(TAG, "setATatExpireTime completed !!! at is : ", str);
        }
    }

    public String getAtExpireTime() {
        LogUtil.c(TAG, "Enter getATatExpireTime");
        return SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(20000), LITE_ACCESS_TOKEN_EXPIRE_TIME);
    }

    public void setRefreshTicket(String str) {
        Context context = mContext;
        if (context == null) {
            LogUtil.b(TAG, "setRefreshTicket: mContext is null !");
        } else {
            SharedPreferenceManager.e(context, String.valueOf(20000), LITE_REFRESH_TOKEN, str, new StorageParams(1));
        }
    }

    public String getRefreshTicket() {
        LogUtil.c(TAG, "Enter getRT");
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(20000), LITE_REFRESH_TOKEN);
        return TextUtils.isEmpty(b) ? ThirdLoginDataStorageUtil.getRefreshTokenFromDb() : b;
    }

    public void setRtExpireTime(String str) {
        Context context = mContext;
        if (context == null) {
            LogUtil.b(TAG, "setRtExpireTime: mContext is null !");
            return;
        }
        SharedPreferenceManager.e(context, String.valueOf(20000), LITE_REFRESH_TOKEN_EXPIRE_TIME, String.valueOf(str), new StorageParams(1));
        LogUtil.a(TAG, "setRTExpireTime completed !!! rtExpireTime is : ", str);
    }

    public String getRtExpireTime() {
        LogUtil.c(TAG, "Enter getRTExpireTime");
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(20000), LITE_REFRESH_TOKEN_EXPIRE_TIME);
        LogUtil.a(TAG, "getRTExpireTime completed !!!");
        return b;
    }

    public String getGuardianAccount() {
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(20000), GUARDIAN_ACCOUNT);
        if (!TextUtils.isEmpty(b)) {
            return b;
        }
        LogUtil.h(TAG, "getGuardianAccount from sp is null!");
        return "";
    }

    public void setGuardianAccount(String str) {
        Context context = mContext;
        if (context == null) {
            LogUtil.h(TAG, "setGuardianAccount: mContext is null !");
        } else {
            SharedPreferenceManager.e(context, String.valueOf(20000), GUARDIAN_ACCOUNT, str, new StorageParams(1));
            LogUtil.a(TAG, "setGuardianAccount completed.");
        }
    }

    public String getGuardianUid() {
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(20000), GUARDIAN_UID);
        if (!TextUtils.isEmpty(b)) {
            return b;
        }
        LogUtil.h(TAG, "getGuardianUid from sp is null!");
        return "";
    }

    public void setGuardianUid(Long l) {
        Context context = mContext;
        if (context == null) {
            LogUtil.h(TAG, "setGuardianUid: mContext is null !");
            return;
        }
        SharedPreferenceManager.e(context, String.valueOf(20000), GUARDIAN_UID, String.valueOf(l), new StorageParams(1));
        LogUtil.a(TAG, "setGuardianUid completed.");
    }

    public static boolean isSameAsLastLoginSiteId() {
        LogUtil.a(TAG, "enter isSameAsLastLoginCountryCode");
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010);
        boolean isSameCountryCode = isSameCountryCode(accountInfo);
        if (!isSameCountryCode) {
            updateLastCountryCode(accountInfo);
        }
        LogUtil.a(TAG, "isSameAsLastLoginCountryCode: ", Boolean.valueOf(isSameCountryCode));
        return isSameCountryCode;
    }

    public static void updateLastCountryCode(String str) {
        LogUtil.a(TAG, "enter updateLastLoginCountryCode");
        SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(20000), "hw_health_last_login_site_id", str, (StorageParams) null);
    }

    public static boolean isSameCountryCode(String str) {
        Context context = BaseApplication.getContext();
        String b = SharedPreferenceManager.b(context, Integer.toString(20000), "hw_health_last_login_site_id");
        if (TextUtils.isEmpty(b)) {
            b = SharedPreferenceManager.b(context, Integer.toString(10000), "agr_first_sign_country");
        }
        return TextUtils.isEmpty(b) || String.valueOf(str).equals(b);
    }

    public void setUpdateState() {
        LogUtil.a(TAG, "enter setUpdateState");
        SharedPreferenceManager.e(LOGIN_DATA, Constant.HAS_UPDATE_FROM_ST_TO_AT, true);
    }

    public boolean getUpdateState() {
        return SharedPreferenceManager.a(LOGIN_DATA, Constant.HAS_UPDATE_FROM_ST_TO_AT, false);
    }

    public void setServerCountryTime(long j) {
        LogUtil.a(TAG, "enter setServerCountryTime：", Long.valueOf(j));
        SharedPreferenceManager.e(Integer.toString(20000), SERVER_COUNTRY_TIME, j);
    }

    public long getServerCountryTime() {
        return SharedPreferenceManager.b(Integer.toString(20000), SERVER_COUNTRY_TIME, 0L);
    }

    public void setMobile(String str) {
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(20000), "mobile", str, new StorageParams(1));
    }

    public String getMobile() {
        return SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(20000), "mobile");
    }

    public void setEmail(String str) {
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(20000), "email", str, new StorageParams(1));
    }

    public String getEmail() {
        return SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(20000), "email");
    }

    public void setAccountTypeHms(String str) {
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(20000), ACCOUNT_TYPE_HMS, str, new StorageParams(1));
    }

    public String getAccountTypeHms() {
        return SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(20000), ACCOUNT_TYPE_HMS);
    }
}
