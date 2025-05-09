package com.huawei.hwcloudmodel.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.hms.mlsdk.asr.MLAsrConstants;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import com.huawei.up.utils.NSPException;
import defpackage.jce;
import defpackage.knx;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.KeyValDbManager;
import health.compact.a.LocalBroadcast;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import java.util.Map;

/* loaded from: classes5.dex */
public class NspClient extends AbsNspClient {
    private static c b = null;
    private static boolean e = false;
    private Context c;
    private LoginInit d;

    public NspClient(Context context) {
        this.c = null;
        this.d = null;
        if (context == null) {
            LogUtil.h("UIME_NspClient", "Nsplient context is null");
            return;
        }
        Context applicationContext = context.getApplicationContext();
        this.c = applicationContext;
        if (this.d == null) {
            this.d = LoginInit.getInstance(applicationContext);
        }
        if (b == null) {
            b = new c();
            e();
            a();
        }
    }

    @Override // com.huawei.hwcloudmodel.utils.AbsNspClient
    protected jce callService(String str, Map<String, Object> map, int i, int i2, int i3) throws NSPException {
        return callService(str, map, i, i2, i3, 1);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0072  */
    @Override // com.huawei.hwcloudmodel.utils.AbsNspClient
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected defpackage.jce callService(java.lang.String r13, java.util.Map<java.lang.String, java.lang.Object> r14, int r15, int r16, int r17, int r18) throws com.huawei.up.utils.NSPException {
        /*
            Method dump skipped, instructions count: 323
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hwcloudmodel.utils.NspClient.callService(java.lang.String, java.util.Map, int, int, int, int):jce");
    }

    private void a(String str, Map<String, Object> map, Map<String, Object> map2) throws NSPException {
        b(map2);
        boolean o = Utils.o();
        LogUtil.a("UIME_NspClient", "callService isOverSea =", Boolean.valueOf(o));
        if (o) {
            String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1009);
            map2.put("siteId", accountInfo);
            LogUtil.a("UIME_NspClient", "callService getSiteId=", accountInfo);
        }
        map2.put("deviceType", String.valueOf(CommonUtil.h(this.c)));
        String deviceType = LoginInit.getInstance(this.c).getDeviceType();
        LogUtil.c("UIME_NspClient", "callService upDeviceType = ", deviceType);
        map2.put("upDeviceType", deviceType);
        String deviceId = LoginInit.getInstance(this.c).getDeviceId();
        if ("".equals(deviceId)) {
            deviceId = "clientnull";
        }
        LogUtil.c("UIME_NspClient", "callService deviceId=", deviceId);
        map2.put("deviceId", deviceId);
        map2.put("sysVersion", Build.VERSION.RELEASE);
        if (str.equals("/dataQuery/path/getMotionPathByVersion") || str.equals("/dataQuery/path/getMotionPathData")) {
            map2.put("iVersion", Integer.valueOf(Utils.a()));
        } else {
            map2.put("iVersion", Integer.valueOf(Utils.b()));
        }
        map2.put("isManually", Integer.valueOf(Utils.c()));
        map2.put("language", MLAsrConstants.LAN_ZH);
        map2.put("oaId", "");
        String e2 = KeyValDbManager.b(this.c).e("cloud_user_privacy10");
        if (e2 != null && "true".equals(e2)) {
            map2.put("isTrackingEnabled", 1);
        } else {
            map2.put("isTrackingEnabled", 0);
        }
        map2.put("improveState", Boolean.valueOf(knx.e()));
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry != null) {
                map2.put(entry.getKey(), entry.getValue());
            }
        }
        map2.put("currentManufacturer", Build.MANUFACTURER);
    }

    private String b() {
        String b2;
        String url = GRSManager.a(this.c).getUrl("healthDeviceUrl");
        return (CommonUtil.as() && (b2 = SharedPreferenceManager.b(this.c, String.valueOf(10008), "key_download_config")) != null && b2.contains("lfhealthtest2")) ? b2 : url;
    }

    private void b(Map<String, Object> map) throws NSPException {
        Object d;
        map.put("ts", Long.valueOf(System.currentTimeMillis()));
        map.put("tokenType", Integer.valueOf(ThirdLoginDataStorageUtil.getTokenTypeValue()));
        LoginInit loginInit = this.d;
        if (loginInit == null) {
            throw new NSPException(1, "mLogin is null.");
        }
        String accountInfo = loginInit.getAccountInfo(1008);
        LogUtil.c("UIME_NspClient", "callService severToken from db = ", accountInfo);
        if (TextUtils.isEmpty(accountInfo)) {
            LogUtil.h("UIME_NspClient", "callService severToken is null!");
            LoginInit.getInstance(this.c).moveInfoFromSPTODB();
            if (this.d.getIsLogined()) {
                LoginInit.tryLoginWhenTokenInvalid();
            }
            throw new NSPException(1, "severToken is null.");
        }
        map.put("token", accountInfo);
        if (LoginInit.getInstance(this.c).isLoginedByWear()) {
            LogUtil.a("UIME_NspClient", "callService appid wear logined");
            map.put("source", 2);
            d = "com.huawei.bone";
        } else {
            LogUtil.a("UIME_NspClient", "callService appid health logined");
            d = com.huawei.haf.application.BaseApplication.d();
            map.put("source", 1);
        }
        map.put("appId", d);
    }

    private void d(jce jceVar, Gson gson) {
        String c2;
        CloudCommonReponse cloudCommonReponse;
        if (jceVar.b() == null || (c2 = Utils.c(jceVar.b())) == null) {
            return;
        }
        if (c2.length() < 1024) {
            LogUtil.c("UIME_NspClient", "callService response content=", c2);
        } else {
            LogUtil.c("UIME_NspClient", "callService response content=", c2.substring(0, 1023));
        }
        try {
            cloudCommonReponse = (CloudCommonReponse) gson.fromJson(c2, CloudCommonReponse.class);
        } catch (JsonSyntaxException e2) {
            if (c2.length() < 30) {
                LogUtil.a("UIME_NspClient", "processResponseContent fromJson exception response content=", c2);
            } else {
                LogUtil.a("UIME_NspClient", "processResponseContent fromJson exception response content=", c2.substring(0, 30));
            }
            LogUtil.a("UIME_NspClient", "processResponseContent, fromJson exception :", e2.getMessage());
            cloudCommonReponse = null;
        }
        if (cloudCommonReponse != null) {
            if (cloudCommonReponse.getResultCode().intValue() == 1002 || cloudCommonReponse.getResultCode().intValue() == 1004) {
                LogUtil.a("UIME_NspClient", "processResponseContent auth failed, so need to logout!", cloudCommonReponse.getResultCode());
                LoginInit.tryLoginWhenTokenInvalid();
            }
        }
    }

    private void e() {
        LogUtil.a("UIME_NspClient", "registerLogoutBroadcastReceiver register accout logout broadcast ");
        BroadcastManagerUtil.bFA_(this.c, b, new IntentFilter("com.huawei.plugin.account.logout"), LocalBroadcast.c, null);
    }

    private void a() {
        LogUtil.a("UIME_NspClient", "registerLoginBroadcastReceiver register accout login broadcast ");
        BroadcastManagerUtil.bFA_(this.c, b, new IntentFilter("com.huawei.plugin.account.login"), LocalBroadcast.c, null);
    }

    static class c extends BroadcastReceiver {
        c() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String str;
            if (intent == null || context == null) {
                return;
            }
            try {
                str = intent.getAction();
            } catch (Exception unused) {
                LogUtil.b("UIME_NspClient", "onReceive: getAction() exception");
                str = null;
            }
            LogUtil.c("UIME_NspClient", "onReceive:", str);
            if ("com.huawei.plugin.account.logout".equals(str)) {
                boolean unused2 = NspClient.e = true;
            } else if ("com.huawei.plugin.account.login".equals(str)) {
                boolean unused3 = NspClient.e = false;
            } else {
                LogUtil.h("UIME_NspClient", "onReceive is other action");
            }
        }
    }
}
