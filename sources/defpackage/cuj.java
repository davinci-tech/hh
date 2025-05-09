package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.haf.bundle.AppBundle;
import com.huawei.hms.framework.network.grs.GrsApi;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.huaweilogin.ThirdPartyLoginManager;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.login.ui.login.util.ILoginCallback;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.operation.ble.BleConstants;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class cuj {
    private static final String APP_NAME;
    private static final int AT_SUBSTRING_FOUR = 4;
    private static final int FALSE_VERSION = 0;
    private static final int NON_PERSONALIZED = 1;
    private static final String PERMISSION = "true";
    private static final int PERSONALIZED = 0;
    private static final String SERVICE_SUFFIX;
    private static final String TAG = "HealthInfoUtils";
    private static final String TAG_RELEASE = "R_HealthInfoUtils";
    private static final int TRUE_VERSION = 1;

    static {
        String str = CommonUtil.cc() ? BleConstants.WEIGHT_KEY : CommonUtil.bc() ? "green" : "";
        SERVICE_SUFFIX = str;
        APP_NAME = "healthcloud" + str;
    }

    public Map<String, Object> getHmsLiteAccountInfo(Context context) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("severToken", LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1008));
        hashMap.put("deviceId", LoginInit.getInstance(BaseApplication.getContext()).getDeviceId());
        hashMap.put("deviceType", CommonUtil.f());
        hashMap.put("siteId", Integer.valueOf(CommonUtil.m(BaseApplication.getContext(), LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1009))));
        hashMap.put("uid", LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011));
        hashMap.put("nationalCode", LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010));
        hashMap.put("account", LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1007));
        gmz d = gmz.d();
        String c = d.c(13);
        String c2 = d.c(14);
        String c3 = d.c(15);
        hashMap.put(JsbMapKeyNames.H5_NPA, Integer.valueOf(!"true".equals(c) ? 1 : 0));
        hashMap.put(JsbMapKeyNames.HW_DSP_NPA, Integer.valueOf(!"true".equals(c2) ? 1 : 0));
        hashMap.put(JsbMapKeyNames.THIRD_DSP_NPA, Integer.valueOf(!"true".equals(c3) ? 1 : 0));
        String c4 = d.c(12);
        String b = SharedPreferenceManager.b(context, Integer.toString(10000), "personalized_recommend");
        if (TextUtils.isEmpty(b)) {
            hashMap.put("nonPersonalizedRecommend", Integer.valueOf(!"true".equals(c4) ? 1 : 0));
        } else {
            hashMap.put("nonPersonalizedRecommend", Integer.valueOf(!"1".equals(b) ? 1 : 0));
        }
        return hashMap;
    }

    public String getCommonCountryCode() {
        return GRSManager.a(BaseApplication.getContext()).getCommonCountryCode();
    }

    public void initFullSdkGrs() {
        LogUtil.a(TAG, "initFullSdkGrs:", Boolean.valueOf(Utils.o()));
        GrsBaseInfo grsBaseInfo = new GrsBaseInfo();
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010);
        grsBaseInfo.setAppName(APP_NAME);
        grsBaseInfo.setCountrySource(accountInfo);
        grsBaseInfo.setSerCountry(accountInfo);
        grsBaseInfo.setRegCountry(accountInfo);
        grsBaseInfo.setVersionName(Build.VERSION.RELEASE);
        GrsApi.grsSdkInit(BaseApplication.getContext(), grsBaseInfo);
    }

    public Map<String, Integer> getHealthVersionType() {
        HashMap hashMap = new HashMap(16);
        hashMap.put("Release", Integer.valueOf(CommonUtil.bv() ? 1 : 0));
        hashMap.put("Debug", Integer.valueOf(CommonUtil.aq() ? 1 : 0));
        hashMap.put("Test", Integer.valueOf(CommonUtil.cc() ? 1 : 0));
        hashMap.put("Beta", Integer.valueOf(CommonUtil.as() ? 1 : 0));
        hashMap.put("NoCloudVersion", Integer.valueOf(Utils.g() ? 1 : 0));
        hashMap.put("GooglePlayOemDisable", 0);
        return hashMap;
    }

    public boolean isHmsLiteEnable(Context context) {
        LogUtil.a(TAG, "isHmsLiteEnable:");
        return CommonUtil.z(context);
    }

    public static void loginByHealthHms(Context context, final ILoginCallback iLoginCallback) {
        if (iLoginCallback != null) {
            LoginInit.getInstance(context).loginHms(context, new ILoginCallback() { // from class: cuj.3
                @Override // com.huawei.login.ui.login.util.ILoginCallback
                public void onLoginSuccess(Object obj) {
                    ILoginCallback.this.onLoginSuccess(obj);
                }

                @Override // com.huawei.login.ui.login.util.ILoginCallback
                public void onLoginFailed(Object obj) {
                    ILoginCallback.this.onLoginFailed(obj);
                }
            });
        }
    }

    public static void loginByHealthHmsLite(Context context, final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback != null) {
            ThirdPartyLoginManager.getInstance().thirdPartyPhoneLogin(context, new IBaseResponseCallback() { // from class: cuj.1
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    ReleaseLogUtil.e(cuj.TAG_RELEASE, "thirdPartyPhoneLogin errorCode:", Integer.valueOf(i));
                    IBaseResponseCallback.this.d(i, obj);
                }
            });
        }
    }

    public boolean ifAllowLogin() {
        return Utils.i();
    }

    public String getServerToken() {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1008);
        if (!TextUtils.isEmpty(accountInfo)) {
            ReleaseLogUtil.e(TAG_RELEASE, "getServerToken: ", accountInfo.substring(0, 4));
        }
        return accountInfo;
    }

    public int getServerTokenType() {
        int tokenTypeValue = ThirdLoginDataStorageUtil.getTokenTypeValue();
        ReleaseLogUtil.e(TAG_RELEASE, "getServerTokenType: ", Integer.valueOf(tokenTypeValue));
        return tokenTypeValue;
    }

    public String getHealthDeviceCapability() {
        LogUtil.a(TAG, "getHealthDeviceCapability:");
        return new Gson().toJson(cvs.d());
    }

    public boolean checkSupportCapbility(int i) {
        boolean c = cwi.c(cvs.a(), i);
        LogUtil.a(TAG, "is support index :", Integer.valueOf(i), ",result:", Boolean.valueOf(c));
        return c;
    }

    public boolean isOversea() {
        LogUtil.a(TAG, "isOversea:", Boolean.valueOf(Utils.o()));
        return Utils.o();
    }

    public String getServiceCountryCode() {
        LogUtil.a(TAG, "getServiceCountryCode");
        return LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1014);
    }

    public boolean getAnalyticsStatus() {
        boolean e = knx.e();
        LogUtil.a(TAG, "getAnalyticsStatus:", Boolean.valueOf(e));
        return e;
    }

    public void printLog(String str, String str2, int i, String str3) {
        if (CommonUtil.bv()) {
            return;
        }
        String str4 = str + "_" + str2;
        if (i == 0) {
            LogUtil.i(str4, str3);
            return;
        }
        if (i == 1) {
            LogUtil.c(str4, str3);
            return;
        }
        if (i == 2) {
            LogUtil.a(str4, str3);
            return;
        }
        if (i == 3) {
            LogUtil.h(str4, str3);
        } else if (i == 4) {
            LogUtil.b(str4, str3);
        } else {
            LogUtil.h(TAG, "printLog logType is wrong");
        }
    }

    public void printReleaseLog(String str, String str2, int i, String str3) {
        String str4 = "R_" + str + "_" + str2;
        if (i == 2) {
            ReleaseLogUtil.e(str4, str3);
        } else if (i == 3) {
            ReleaseLogUtil.d(str4, str3);
        } else {
            if (i != 4) {
                return;
            }
            ReleaseLogUtil.c(str4, str3);
        }
    }

    public Map<String, String> getDeviceInfo() {
        return new HashMap(16);
    }

    public void launchActivity(Context context, Intent intent) {
        AppBundle.e().launchActivity(context, intent, null);
    }
}
