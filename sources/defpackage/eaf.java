package defpackage;

import android.os.Build;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.utils.CloudParamKeys;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class eaf {
    public static JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("publicArgs", c());
        } catch (JSONException unused) {
            LogUtil.b("ArgsUtil", "JSONException");
        }
        return jSONObject;
    }

    public static Map<String, String> e() {
        HashMap hashMap = new HashMap();
        String huidOrDefault = LoginInit.getInstance(BaseApplication.getContext()).getHuidOrDefault();
        if (!LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode() && huidOrDefault != null) {
            hashMap.put("x-huid", huidOrDefault);
        }
        hashMap.put("x-version", CommonUtil.c(BaseApplication.getContext()));
        hashMap.put("Content-Type", HealthEngineRequestManager.CONTENT_TYPE);
        return hashMap;
    }

    private static JSONObject c() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("tokenType", ThirdLoginDataStorageUtil.getTokenTypeValue());
            a(jSONObject);
            jSONObject.put("oaId", "");
            String b = SharedPreferenceManager.b(arx.b(), Integer.toString(10000), "health_product_recommend");
            if (b != null) {
                jSONObject.put("isTrackingEnabled", b);
            } else {
                jSONObject.put("isTrackingEnabled", "0");
            }
            if (LoginInit.getInstance(arx.b()).isLoginedByWear()) {
                jSONObject.put("appId", "com.huawei.bone");
            } else {
                jSONObject.put("appId", sqq.e());
            }
            jSONObject.put("deviceType", sqq.c());
            jSONObject.put("upDeviceType", LoginInit.getInstance(arx.b()).getDeviceType());
            jSONObject.put("deviceId", LoginInit.getInstance(arx.b()).getDeviceId());
            jSONObject.put("sysVersion", Build.VERSION.RELEASE);
            jSONObject.put(CloudParamKeys.CLIENT_TYPE, nsn.b());
            jSONObject.put("manufacturer", Build.MANUFACTURER);
            if ("com.huawei.bone".equals(BaseApplication.getAppPackage())) {
                jSONObject.put("appType", 2);
            } else {
                jSONObject.put("appType", 1);
            }
            jSONObject.put("ts", System.currentTimeMillis());
            jSONObject.put("timeZone", jdl.q(System.currentTimeMillis()));
            if (Utils.o()) {
                jSONObject.put("iVersion", "3");
            } else {
                jSONObject.put("iVersion", "5");
            }
            if (LanguageUtil.j(BaseApplication.getContext())) {
                jSONObject.put("language", ProfileRequestConstants.X_LANGUAGE_VALUE);
            } else {
                jSONObject.put("language", "en_US");
            }
            jSONObject.put("environment", CommonUtil.l(BaseApplication.getContext()));
            jSONObject.put("countryCode", GRSManager.a(BaseApplication.getContext()).getCommonCountryCode());
            jSONObject.put("traceId", String.valueOf(System.currentTimeMillis()) + Math.random());
        } catch (JSONException unused) {
            LogUtil.b("ArgsUtil", "JSONException");
        }
        return jSONObject;
    }

    private static void a(JSONObject jSONObject) throws JSONException {
        LoginInit loginInit = LoginInit.getInstance(BaseApplication.getContext());
        if (loginInit.getUsetId() != null) {
            String accountInfo = loginInit.getAccountInfo(1008);
            int siteId = loginInit.getSiteId();
            jSONObject.put("token", accountInfo);
            jSONObject.put("siteId", siteId);
        }
    }
}
