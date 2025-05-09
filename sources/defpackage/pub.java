package defpackage;

import android.content.Context;
import android.os.Build;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ParamsFactory;
import com.huawei.operation.utils.CloudParamKeys;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.GRSManager;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public class pub implements ParamsFactory {

    /* renamed from: a, reason: collision with root package name */
    private final LoginInit f16260a;
    private final Context e;

    public pub() {
        Context context = BaseApplication.getContext();
        this.e = context;
        this.f16260a = LoginInit.getInstance(context);
    }

    @Override // com.huawei.networkclient.ParamsFactory
    public Map<String, String> getHeaders() {
        HashMap hashMap = new HashMap();
        String huidOrDefault = this.f16260a.getHuidOrDefault();
        if (!this.f16260a.isBrowseMode() && huidOrDefault != null) {
            hashMap.put("x-huid", huidOrDefault);
        }
        hashMap.put("x-version", CommonUtil.c(this.e));
        hashMap.put("Content-Type", HealthEngineRequestManager.CONTENT_TYPE);
        return hashMap;
    }

    @Override // com.huawei.networkclient.ParamsFactory
    public Map<String, Object> getBody(Object obj) {
        Map<String, Object> hashMap = obj == null ? new HashMap<>() : lql.d(obj);
        hashMap.put("tokenType", Integer.valueOf(ThirdLoginDataStorageUtil.getTokenTypeValue()));
        if (this.f16260a.getAccountInfo(1011) != null) {
            hashMap.put("token", this.f16260a.getAccountInfo(1008));
            hashMap.put("siteId", Integer.valueOf(CommonUtils.h(this.f16260a.getAccountInfo(1009))));
        }
        hashMap.put("oaId", "");
        String b = SharedPreferenceManager.b(this.e, Integer.toString(10000), "health_product_recommend");
        if (b != null) {
            hashMap.put("isTrackingEnabled", b);
        } else {
            hashMap.put("isTrackingEnabled", "0");
        }
        if (this.f16260a.isLoginedByWear()) {
            hashMap.put("appId", "com.huawei.bone");
        } else {
            hashMap.put("appId", sqq.e());
        }
        hashMap.put("deviceType", sqq.c());
        hashMap.put("upDeviceType", this.f16260a.getDeviceType());
        hashMap.put("deviceId", this.f16260a.getDeviceId());
        hashMap.put("sysVersion", Build.VERSION.RELEASE);
        hashMap.put(CloudParamKeys.CLIENT_TYPE, Integer.valueOf(nsn.b()));
        hashMap.put("bindDeviceType", null);
        c(hashMap);
        return hashMap;
    }

    private void c(Map<String, Object> map) {
        map.put("manufacturer", Build.MANUFACTURER);
        if ("com.huawei.bone".equals(BaseApplication.getAppPackage())) {
            map.put("appType", 2);
        } else {
            map.put("appType", 1);
        }
        map.put("ts", Long.valueOf(ghz.e(Calendar.getInstance().getTimeInMillis())));
        map.put("timeZone", ggl.b());
        ReleaseLogUtil.e("Suggestion_CloudFactory", "the iVersion is: ", "5");
        map.put("iVersion", "5");
        if (LanguageUtil.j(this.e)) {
            map.put("language", ProfileRequestConstants.X_LANGUAGE_VALUE);
        } else {
            map.put("language", "en_US");
        }
        map.put("environment", Integer.valueOf(CommonUtil.l(BaseApplication.getContext())));
        map.put("countryCode", GRSManager.a(this.e).getCommonCountryCode());
    }
}
