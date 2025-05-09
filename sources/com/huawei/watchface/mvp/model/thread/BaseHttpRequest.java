package com.huawei.watchface.mvp.model.thread;

import android.text.TextUtils;
import com.huawei.hms.network.embedded.j;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.api.HwWatchFaceBtManager;
import com.huawei.watchface.ek;
import com.huawei.watchface.el;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.g;
import com.huawei.watchface.n;
import com.huawei.watchface.utils.ArrayUtils;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.MobileInfoHelper;
import com.huawei.watchface.utils.WatchFaceHttpUtil;
import com.huawei.watchface.utils.constants.WatchFaceConstant;
import java.util.LinkedHashMap;
import java.util.Map;

@el(a = {@ek(a = "resultInfo"), @ek(a = "resultinfo"), @ek(a = j.k), @ek(a = "resultCode"), @ek(a = "resultcode"), @ek(a = "retCode")}, b = {@ek(a = "versionCode"), @ek(a = "hitopid"), @ek(a = "productId")}, c = {@ek(a = "versionCode"), @ek(a = "usertoken", b = true)}, e = {"userToken", HwPayConstant.KEY_RESERVEDINFOR, "licenseReq", "authCode", "deviceId", HwPayConstant.KEY_SIGN, "userRefreshToken", "phoneId", "publicKey"})
/* loaded from: classes7.dex */
public abstract class BaseHttpRequest<T> implements Runnable {
    protected String a() {
        return null;
    }

    protected boolean b() {
        return true;
    }

    protected abstract T c(String str);

    @Override // java.lang.Runnable
    public void run() {
    }

    protected String a(String str, String str2, LinkedHashMap<String, String> linkedHashMap) {
        n.a(Environment.getApplicationContext()).c(false);
        return WatchFaceHttpUtil.a(str, str2, linkedHashMap, getClass());
    }

    protected String a(LinkedHashMap<String, ?> linkedHashMap) {
        if (ArrayUtils.a(linkedHashMap)) {
            return "";
        }
        StringBuilder sb = new StringBuilder(250);
        int i = 0;
        for (Map.Entry<String, ?> entry : linkedHashMap.entrySet()) {
            sb.append(i == 0 ? "" : "&");
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
            i++;
        }
        return sb.toString();
    }

    public LinkedHashMap<String, String> getReqHeaders() {
        return c();
    }

    protected String d(String str) {
        String b;
        HwLog.i("BaseHttpRequest", "getLicenseReq start :" + str);
        try {
            b = g.b(Environment.getApplicationContext(), str);
        } catch (Exception unused) {
            b = g.b(Environment.getApplicationContext(), str);
        }
        if (TextUtils.isEmpty(b)) {
            HwLog.w("BaseHttpRequest", "licenseReq is empty");
        }
        HwLog.i("BaseHttpRequest", "getLicenseReq -- licenseKey== " + str);
        return b;
    }

    private LinkedHashMap<String, String> c() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        if (WatchFaceHttpUtil.a() != null) {
            linkedHashMap.put(WatchFaceConstant.X_SIGN, WatchFaceHttpUtil.a().getSign());
        }
        if (!TextUtils.isEmpty(a())) {
            linkedHashMap.put("versionCode", a());
        }
        linkedHashMap.put(WatchFaceConstant.X_CLIENTTRACEID, MobileInfoHelper.generateUUID());
        linkedHashMap.put("x-hc", "CN");
        linkedHashMap.put(FaqConstants.FAQ_EMUIVERSION, HwWatchFaceBtManager.getInstance(Environment.getApplicationContext()).getWatchFaceMaxVersion(false, HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getDeviceConnectState() == 0));
        linkedHashMap.put(ProfileRequestConstants.X_APPID_KEY, "10003");
        linkedHashMap.put("x-devicemodel", HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getDeviceModel());
        linkedHashMap.put("deviceid", HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getDeviceIdAsTheme(true));
        linkedHashMap.put("deviceType", HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getDeviceTypeNew());
        linkedHashMap.put("terminalType", "1");
        linkedHashMap.put(WatchFaceConstant.X_UID, HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getUserId());
        linkedHashMap.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        linkedHashMap.put(WatchFaceConstant.CIPHER_VERSION, "2");
        n.a(Environment.getApplicationContext()).a(linkedHashMap, b());
        return linkedHashMap;
    }

    public String a(String str) {
        return "";
    }
}
