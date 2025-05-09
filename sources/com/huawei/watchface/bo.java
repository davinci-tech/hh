package com.huawei.watchface;

import android.text.TextUtils;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import com.huawei.secure.android.common.util.SafeString;
import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.api.HwWatchFaceBtManager;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.mvp.model.datatype.WatchFaceSignBean;
import com.huawei.watchface.mvp.model.thread.BaseHttpRequest;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.DensityUtil;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.LanguageUtils;
import com.huawei.watchface.utils.MobileInfoHelper;
import com.huawei.watchface.utils.WatchFaceHttpUtil;
import com.huawei.watchface.utils.constants.WatchFaceConstant;
import java.util.LinkedHashMap;

/* loaded from: classes7.dex */
public class bo extends BaseHttpRequest<WatchFaceSignBean> {

    /* renamed from: a, reason: collision with root package name */
    private int f10930a = 0;

    public void a(int i) {
        this.f10930a = i;
    }

    @Override // com.huawei.watchface.mvp.model.thread.BaseHttpRequest
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public WatchFaceSignBean c(String str) {
        return e(str);
    }

    public String c() {
        HwLog.i("GetSignThread", "getResponse url");
        return a(WatchFaceHttpUtil.d(), d(), f());
    }

    private String d() {
        StringBuilder sb = new StringBuilder(2048);
        sb.append("firmware=6.0.1");
        HwLog.i("GetSignThread", "getSignParams deviceType:" + this.f10930a);
        if (this.f10930a == 0) {
            sb.append("&screen=");
            sb.append(HwWatchFaceBtManager.getInstance(Environment.getApplicationContext()).getWatchFaceScreen(true, HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getDeviceConnectState() == 0));
            sb.append("&phoneType=");
            sb.append(HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getDeviceModel());
            sb.append("&buildNumber=");
            sb.append(HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getDeviceModel());
            sb.append(HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getSoftVersion());
        } else {
            sb.append("&screen=");
            sb.append(Cdo.b());
            sb.append("&phoneType=");
            sb.append(MobileInfoHelper.getDeviceName());
            sb.append("&buildNumber=");
            sb.append(MobileInfoHelper.getDeviceName());
            sb.append(MobileInfoHelper.getBuildNumber());
        }
        sb.append("&locale=");
        sb.append(LanguageUtils.a(false));
        sb.append("&type=");
        sb.append(e());
        sb.append("&isoCode=");
        sb.append(HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getCommonCountryCode());
        sb.append("&userId=");
        sb.append(cv.h());
        sb.append("&versionCode=");
        sb.append(CommonUtils.getVersionCode());
        return sb.toString();
    }

    private String e() {
        return Environment.sIsAarInTheme ? "1" : "5";
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0069  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.huawei.watchface.mvp.model.datatype.WatchFaceSignBean e(java.lang.String r6) {
        /*
            r5 = this;
            java.lang.String r0 = "dealSignReceive"
            java.lang.String r1 = "GetSignThread"
            com.huawei.watchface.utils.HwLog.i(r1, r0)
            boolean r0 = android.text.TextUtils.isEmpty(r6)
            r2 = 0
            if (r0 == 0) goto L14
            java.lang.String r6 = "dealSignReceive receive is empty."
            com.huawei.watchface.utils.HwLog.i(r1, r6)
            return r2
        L14:
            r0 = -1
            com.huawei.watchface.dx r3 = com.huawei.watchface.dx.a()     // Catch: com.google.gson.JsonSyntaxException -> L55
            java.lang.Class<com.huawei.watchface.mvp.model.datatype.WatchFaceSignBean> r4 = com.huawei.watchface.mvp.model.datatype.WatchFaceSignBean.class
            java.lang.Object r6 = r3.a(r6, r4)     // Catch: com.google.gson.JsonSyntaxException -> L55
            com.huawei.watchface.mvp.model.datatype.WatchFaceSignBean r6 = (com.huawei.watchface.mvp.model.datatype.WatchFaceSignBean) r6     // Catch: com.google.gson.JsonSyntaxException -> L55
            if (r6 == 0) goto L4f
            java.lang.String r3 = r6.getSign()     // Catch: com.google.gson.JsonSyntaxException -> L56
            boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch: com.google.gson.JsonSyntaxException -> L56
            if (r3 != 0) goto L4f
            r5.a(r6)     // Catch: com.google.gson.JsonSyntaxException -> L56
            r5.b(r6)     // Catch: com.google.gson.JsonSyntaxException -> L56
            java.lang.String r3 = r6.getResultCode()     // Catch: com.google.gson.JsonSyntaxException -> L56
            r4 = 10
            int r0 = com.huawei.watchface.utils.IntegerUtils.a(r3, r4)     // Catch: com.google.gson.JsonSyntaxException -> L56
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: com.google.gson.JsonSyntaxException -> L56
            java.lang.String r4 = "dealSignReceive errorCode :"
            r3.<init>(r4)     // Catch: com.google.gson.JsonSyntaxException -> L56
            r3.append(r0)     // Catch: com.google.gson.JsonSyntaxException -> L56
            java.lang.String r3 = r3.toString()     // Catch: com.google.gson.JsonSyntaxException -> L56
            com.huawei.watchface.utils.HwLog.i(r1, r3)     // Catch: com.google.gson.JsonSyntaxException -> L56
            goto L5b
        L4f:
            java.lang.String r3 = "dealSignReceive error is watch face unknown!"
            com.huawei.watchface.utils.HwLog.w(r1, r3)     // Catch: com.google.gson.JsonSyntaxException -> L56
            goto L5b
        L55:
            r6 = r2
        L56:
            java.lang.String r3 = "WatchFaceSignBean is error JsonSyntaxException!"
            com.huawei.watchface.utils.HwLog.e(r1, r3)
        L5b:
            if (r0 != 0) goto L69
            int r0 = r5.f10930a
            if (r0 != 0) goto L65
            com.huawei.watchface.utils.WatchFaceHttpUtil.a(r6)
            goto L68
        L65:
            com.huawei.watchface.utils.WatchFaceHttpUtil.b(r6)
        L68:
            return r6
        L69:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r3 = "dealSignReceive errorCode:"
            r6.<init>(r3)
            r6.append(r0)
            java.lang.String r6 = r6.toString()
            com.huawei.watchface.utils.HwLog.w(r1, r6)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.watchface.bo.e(java.lang.String):com.huawei.watchface.mvp.model.datatype.WatchFaceSignBean");
    }

    private void a(WatchFaceSignBean watchFaceSignBean) {
        String str;
        if (watchFaceSignBean == null) {
            HwLog.w("GetSignThread", "setSignUserId() watchFaceSignBean is null.");
            return;
        }
        String sign = watchFaceSignBean.getSign();
        if (TextUtils.isEmpty(sign)) {
            HwLog.w("GetSignThread", "setSignUserId() sign is empty.");
            return;
        }
        try {
            str = SafeString.substring(sign, sign.indexOf("@") + 1);
        } catch (IndexOutOfBoundsException unused) {
            HwLog.w("GetSignThread", "setSignUserId() IndexOutOfBoundsException.");
            str = "";
        }
        watchFaceSignBean.setUserId(str);
    }

    private void b(WatchFaceSignBean watchFaceSignBean) {
        if (watchFaceSignBean == null) {
            HwLog.w("GetSignThread", "setSignEncryptSecret() watchFaceSignBean is null.");
        } else {
            watchFaceSignBean.setEncryptSecret(ai.a(DensityUtil.getStringById(CommonUtils.z() ? R$string.encryptSecret : R$string.encryptSecretBelowAndroidO)));
        }
    }

    private LinkedHashMap<String, String> f() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("versionCode", CommonUtils.getVersionCode() + "");
        linkedHashMap.put(WatchFaceConstant.X_CLIENTTRACEID, MobileInfoHelper.generateUUID());
        linkedHashMap.put("x-hc", "CN");
        linkedHashMap.put(FaqConstants.FAQ_EMUIVERSION, HwWatchFaceBtManager.getInstance(Environment.getApplicationContext()).getWatchFaceMaxVersion(true, HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getDeviceConnectState() == 0));
        linkedHashMap.put(ProfileRequestConstants.X_APPID_KEY, "10003");
        linkedHashMap.put("x-devicemodel", HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getDeviceModel());
        linkedHashMap.put("deviceid", HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getDeviceIdAsTheme(true));
        linkedHashMap.put("deviceType", HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getDeviceType());
        linkedHashMap.put("terminalType", "1");
        linkedHashMap.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        return linkedHashMap;
    }
}
