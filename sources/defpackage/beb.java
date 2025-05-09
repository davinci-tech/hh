package defpackage;

import android.content.Context;
import android.os.Build;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ParamsFactory;
import com.huawei.operation.utils.CloudParamKeys;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class beb implements ParamsFactory {
    private final Context b;
    private final LoginInit c;

    public beb() {
        Context context = BaseApplication.getContext();
        this.b = context;
        this.c = LoginInit.getInstance(context);
    }

    @Override // com.huawei.networkclient.ParamsFactory
    public Map<String, String> getHeaders() {
        HashMap hashMap = new HashMap();
        String huidOrDefault = this.c.getHuidOrDefault();
        if (!this.c.isBrowseMode() && huidOrDefault != null) {
            hashMap.put("x-huid", huidOrDefault);
        }
        hashMap.put("x-version", CommonUtil.c(this.b));
        hashMap.put("Content-Type", HealthEngineRequestManager.CONTENT_TYPE);
        return hashMap;
    }

    @Override // com.huawei.networkclient.ParamsFactory
    public Map<String, Object> getBody(Object obj) {
        Map<String, Object> hashMap = obj == null ? new HashMap<>() : lql.d(obj);
        hashMap.put("tokenType", Integer.valueOf(ThirdLoginDataStorageUtil.getTokenTypeValue()));
        c(hashMap);
        hashMap.put("oaId", "");
        String b = SharedPreferenceManager.b(this.b, Integer.toString(10000), "health_product_recommend");
        if (b != null) {
            hashMap.put("isTrackingEnabled", b);
        } else {
            hashMap.put("isTrackingEnabled", "0");
        }
        if (this.c.isLoginedByWear()) {
            hashMap.put("appId", "com.huawei.bone");
        } else {
            hashMap.put("appId", sqq.e());
        }
        hashMap.put("deviceType", sqq.c());
        hashMap.put("upDeviceType", this.c.getDeviceType());
        hashMap.put("deviceId", this.c.getDeviceId());
        hashMap.put("sysVersion", Build.VERSION.RELEASE);
        hashMap.put(CloudParamKeys.CLIENT_TYPE, Integer.valueOf(nsn.b()));
        b(hashMap);
        hashMap.put("manufacturer", Build.MANUFACTURER);
        hashMap.put("appType", Integer.valueOf("com.huawei.bone".equals(BaseApplication.getAppPackage()) ? 2 : 1));
        hashMap.put("ts", Long.valueOf(System.currentTimeMillis()));
        hashMap.put("timeZone", jdl.q(System.currentTimeMillis()));
        LogUtil.a("Suggestion_CloudFactory", "the iVersion is: ", "6");
        hashMap.put("iVersion", "6");
        hashMap.put("language", LanguageUtil.j(this.b) ? ProfileRequestConstants.X_LANGUAGE_VALUE : "en_US");
        hashMap.put("lang", bed.a());
        hashMap.put("environment", Integer.valueOf(CommonUtil.l(BaseApplication.getContext())));
        hashMap.put("countryCode", GRSManager.a(this.b).getCommonCountryCode());
        return hashMap;
    }

    private void c(Map<String, Object> map) {
        if (this.c.getAccountInfo(1011) != null) {
            map.put("token", this.c.getAccountInfo(1008));
            map.put("siteId", Integer.valueOf(Integer.parseInt(this.c.getAccountInfo(1009))));
        }
    }

    private void b(Map<String, Object> map) {
        map.put("bindDeviceType", null);
    }
}
