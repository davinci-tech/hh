package health.compact.a;

import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.text.TextUtils;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import com.huawei.hms.framework.network.grs.GrsClient;
import com.huawei.hms.framework.network.grs.IQueryUrlCallBack;
import com.huawei.hwbasemgr.CloudUrlManager;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.ble.BleConstants;
import defpackage.jac;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
public class GRSManager implements CloudUrlManager {

    /* renamed from: a, reason: collision with root package name */
    private static final List<String> f13116a;
    private static String b;
    private static final String c;
    private static final String d;
    private static Context e;
    private static List<String> i;

    static {
        String str = CommonUtil.cc() ? BleConstants.WEIGHT_KEY : CommonUtil.bc() ? "green" : "";
        d = str;
        c = "healthcloud" + str;
        i = new ArrayList(10);
        ArrayList arrayList = new ArrayList(10);
        f13116a = arrayList;
        b = "";
        i.add("domainConsumerHuawei");
        if (CommonUtil.as()) {
            arrayList.add("domainLegalCloudHuawei");
        }
    }

    private GRSManager() {
    }

    public static GRSManager a(Context context) {
        if (context != null) {
            e = context.getApplicationContext();
        } else {
            e = BaseApplication.e();
        }
        return a.b;
    }

    private boolean a(String str) {
        return (i.contains(str) || AuthorizationUtils.a(e) || f13116a.contains(str)) ? false : true;
    }

    @Override // com.huawei.hwbasemgr.CloudUrlManager
    public String getUrl(String str) {
        if (a(str)) {
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d("R_GRS_GRSManager", "getUrl no authorize");
            return "";
        }
        boolean i2 = Utils.i();
        boolean isLogined = LoginInit.getInstance(e).getIsLogined();
        if (!i2 && !isLogined) {
            com.huawei.hwlogsmodel.LogUtil.h("R_GRS_GRSManager", "getUrl failed with isAllowLogin=", Boolean.valueOf(i2), ", isLogined=", Boolean.valueOf(isLogined));
            return "";
        }
        String synGetGrsUrl = b(c, getCommonCountryCode()).synGetGrsUrl(jac.d(str) + d, str);
        if (TextUtils.isEmpty(synGetGrsUrl)) {
            com.huawei.hwlogsmodel.LogUtil.b("R_GRS_GRSManager", "getUrl error, key is ", str);
        }
        return synGetGrsUrl;
    }

    public void e(final String str, final GrsQueryCallback grsQueryCallback) {
        if (a(str)) {
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d("R_GRS_GRSManager", "getUrl no authorize");
            if (grsQueryCallback != null) {
                grsQueryCallback.onCallBackFail(1001);
                return;
            }
            return;
        }
        if (Looper.getMainLooper() == Looper.myLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: health.compact.a.GRSManager.1
                @Override // java.lang.Runnable
                public void run() {
                    GRSManager.this.e(str, grsQueryCallback);
                }
            });
            return;
        }
        boolean i2 = Utils.i();
        boolean isLogined = LoginInit.getInstance(e).getIsLogined();
        String commonCountryCode = getCommonCountryCode();
        if (TextUtils.isEmpty(commonCountryCode) && (!i2 || !isLogined)) {
            com.huawei.hwlogsmodel.LogUtil.h("R_GRS_GRSManager", "getUrl failed with isAllowLogin=", Boolean.valueOf(i2), ", isLogined=", Boolean.valueOf(isLogined), " countryCode=", commonCountryCode);
            if (grsQueryCallback != null) {
                grsQueryCallback.onCallBackSuccess("");
                return;
            }
        }
        b(c, commonCountryCode).ayncGetGrsUrl(jac.d(str) + d, str, new IQueryUrlCallBack() { // from class: health.compact.a.GRSManager.4
            @Override // com.huawei.hms.framework.network.grs.IQueryUrlCallBack
            public void onCallBackSuccess(String str2) {
                if (TextUtils.isEmpty(str2)) {
                    health.compact.a.hwlogsmodel.ReleaseLogUtil.c("R_GRS_GRSManager", "getUrl error, key is ", str);
                    GrsQueryCallback grsQueryCallback2 = grsQueryCallback;
                    if (grsQueryCallback2 != null) {
                        grsQueryCallback2.onCallBackFail(1001);
                        return;
                    }
                    return;
                }
                GrsQueryCallback grsQueryCallback3 = grsQueryCallback;
                if (grsQueryCallback3 != null) {
                    grsQueryCallback3.onCallBackSuccess(str2);
                }
            }

            @Override // com.huawei.hms.framework.network.grs.IQueryUrlCallBack
            public void onCallBackFail(int i3) {
                GrsQueryCallback grsQueryCallback2 = grsQueryCallback;
                if (grsQueryCallback2 != null) {
                    grsQueryCallback2.onCallBackFail(i3);
                }
            }
        });
    }

    @Override // com.huawei.hwbasemgr.CloudUrlManager
    public String getCountryCode() {
        String accountInfo = Utils.i() ? LoginInit.getInstance(e).getAccountInfo(1010) : null;
        if (!TextUtils.isEmpty(accountInfo)) {
            return accountInfo;
        }
        String i2 = CommonUtil.i(e);
        return TextUtils.isEmpty(i2) ? Locale.getDefault().getCountry() : i2;
    }

    @Override // com.huawei.hwbasemgr.CloudUrlManager
    public String getMccCountryCode() {
        String f = CommonUtil.f(e);
        return TextUtils.isEmpty(f) ? Locale.getDefault().getCountry() : f;
    }

    @Override // com.huawei.hwbasemgr.CloudUrlManager
    public String getNoCheckUrl(String str, String str2) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            com.huawei.hwlogsmodel.LogUtil.h("GRSManager", "getNoCheckUrl  ANR!!!");
        }
        return getNoCheckUrl(str, jac.d(str) + d, str2);
    }

    @Override // com.huawei.hwbasemgr.CloudUrlManager
    public String getNoCheckUrl(String str, String str2, String str3) {
        if (a(str)) {
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d("R_GRS_GRSManager", "getNoCheckUrl no authorize");
            return "";
        }
        if (TextUtils.isEmpty(str3)) {
            str3 = getCountryCode();
        }
        com.huawei.hwlogsmodel.LogUtil.a("GRSManager", "getNoCheckUrl, key=", str, "service=", str2, ", countryCode=", str3);
        return b(c, str3).synGetGrsUrl(str2, str);
    }

    private GrsClient b(String str, String str2) {
        e(str2);
        GrsBaseInfo grsBaseInfo = new GrsBaseInfo();
        grsBaseInfo.setAppName(str);
        grsBaseInfo.setCountrySource(str2);
        grsBaseInfo.setSerCountry(str2);
        grsBaseInfo.setRegCountry(str2);
        grsBaseInfo.setVersionName(Build.VERSION.RELEASE);
        return new GrsClient(e, grsBaseInfo);
    }

    private static void e(String str) {
        synchronized (GRSManager.class) {
            if (TextUtils.isEmpty(b) || !b.equals(str)) {
                b = str;
                BaseApplication.e().getSharedPreferences("grs_init_info", 0).edit().putString("grs_countrycode_key", str).apply();
            }
        }
    }

    public String c() {
        String accountInfo = LoginInit.getInstance(e).getAccountInfo(1010);
        if (!Utils.l() && !TextUtils.isEmpty(accountInfo)) {
            com.huawei.hwlogsmodel.LogUtil.h("GRSManager", "getCommonCountryCode from step1");
            return accountInfo;
        }
        if (TextUtils.isEmpty(accountInfo)) {
            return e();
        }
        return "0".equals(LoginInit.getInstance(e).getAccountInfo(1011)) ? e() : accountInfo;
    }

    @Override // com.huawei.hwbasemgr.CloudUrlManager
    public String getCommonCountryCode() {
        String accountInfo = LoginInit.getInstance(e).getAccountInfo(1010);
        if (!TextUtils.isEmpty(accountInfo)) {
            com.huawei.hwlogsmodel.LogUtil.a("GRSManager", "getCommonCountryCode from step1");
            return accountInfo;
        }
        return e();
    }

    private String e() {
        String f = CommonUtil.f(e);
        if (!TextUtils.isEmpty(f)) {
            com.huawei.hwlogsmodel.LogUtil.a("GRSManager", "getCommonCountryCode from step2");
            return f;
        }
        String i2 = CommonUtil.i(e);
        if (!TextUtils.isEmpty(i2)) {
            com.huawei.hwlogsmodel.LogUtil.a("GRSManager", "getCommonCountryCode from step4");
            return i2;
        }
        String country = Locale.getDefault().getCountry();
        if (!TextUtils.isEmpty(country)) {
            com.huawei.hwlogsmodel.LogUtil.a("GRSManager", "getCommonCountryCode from step5");
            return country;
        }
        String b2 = SharedPreferenceManager.b(e, Integer.toString(PrebakedEffectId.RT_SNIPER_RIFLE), "select_country");
        com.huawei.hwlogsmodel.LogUtil.a("GRSManager", "getCommonCountryCode from step6");
        return b2;
    }

    static class a {
        private static final GRSManager b = new GRSManager();
    }
}
