package defpackage;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.hms.network.embedded.j2;
import com.huawei.hms.network.embedded.w9;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import health.compact.a.CommonUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public class shd<T> {
    Class<T> clazz;
    String urlPath;

    shd(Class<T> cls, String str) {
        this.urlPath = str;
        this.clazz = cls;
    }

    public Map<String, Object> createParams() {
        return addCommonParams(new HashMap());
    }

    protected Map<String, Object> addCommonParams(Map<String, Object> map) {
        map.put("ts", String.valueOf(System.currentTimeMillis()));
        map.put("tokenType", String.valueOf(ThirdLoginDataStorageUtil.getTokenTypeValue()));
        map.put("token", LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1008));
        map.put("source", String.valueOf(1));
        map.put("appId", com.huawei.haf.application.BaseApplication.d());
        map.put("deviceType", String.valueOf(CommonUtil.h(BaseApplication.getContext())));
        map.put("upDeviceType", LoginInit.getInstance(BaseApplication.getContext()).getDeviceType());
        String deviceId = LoginInit.getInstance(BaseApplication.getContext()).getDeviceId();
        if (TextUtils.isEmpty(deviceId)) {
            deviceId = "clientnull";
        }
        map.put("deviceId", deviceId);
        map.put("sysVersion", Build.VERSION.RELEASE);
        map.put("iVersion", String.valueOf(Utils.b()));
        map.put("isManually", String.valueOf(Utils.c()));
        map.put("improveState", String.valueOf(knx.e()));
        map.put("currentManufacturer", Build.MANUFACTURER);
        return map;
    }

    protected String getParamSiteID() {
        return LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1009);
    }

    Map<String, String> createHeader() {
        HashMap hashMap = new HashMap();
        hashMap.put(j2.v, Constants.GZIP);
        hashMap.put("Content-Encoding", Constants.GZIP);
        hashMap.put("x-huid", KeyValDbManager.b(BaseApplication.getContext()).e("user_id"));
        hashMap.put("Connection", w9.j);
        hashMap.put("Content-Type", HealthEngineRequestManager.CONTENT_TYPE);
        hashMap.put("x-version", dealAiLifeBetaName(BaseApplication.getContext()));
        return hashMap;
    }

    private String dealAiLifeBetaName(Context context) {
        String c = CommonUtil.c(context);
        String b = SharedPreferenceManager.b(context, String.valueOf(10008), "key_download_config");
        return (b == null || !b.contains("lfhealthtest2")) ? c : "and_health_10.1.1.999";
    }
}
