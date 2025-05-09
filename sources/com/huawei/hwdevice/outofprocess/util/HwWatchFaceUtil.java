package com.huawei.hwdevice.outofprocess.util;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.text.TextUtils;
import com.huawei.datatype.DataDeviceInfo;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hms.mlsdk.asr.MLAsrConstants;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.util.WatchFaceUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsUtil;
import defpackage.ixx;
import defpackage.jqi;
import defpackage.lsk;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.Utils;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes5.dex */
public class HwWatchFaceUtil {
    private static final Object c = new Object();
    private static HwWatchFaceUtil e;
    private String b;
    private IBaseResponseCallback d = new IBaseResponseCallback() { // from class: com.huawei.hwdevice.outofprocess.util.HwWatchFaceUtil.1
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.a("HwWatchFaceUtil", "mGetFirmwareVersionCallback, errorCode:", Integer.valueOf(i));
            if (i == 0 && (obj instanceof DataDeviceInfo)) {
                DataDeviceInfo dataDeviceInfo = (DataDeviceInfo) obj;
                LogUtil.a("HwWatchFaceUtil", "mGetFirmwareVersionCallback, firmware:", dataDeviceInfo.getDeviceSoftVersion());
                HwWatchFaceUtil.this.b = dataDeviceInfo.getDeviceSoftVersion();
            }
        }
    };

    public interface TouchSupportCallback {
        void touchSupportResult(int i);
    }

    public static HwWatchFaceUtil b() {
        HwWatchFaceUtil hwWatchFaceUtil;
        synchronized (c) {
            if (e == null) {
                e = new HwWatchFaceUtil();
            }
            hwWatchFaceUtil = e;
        }
        return hwWatchFaceUtil;
    }

    public void c(String str) {
        this.b = str;
    }

    public String d() {
        String str;
        Configuration configuration = BaseApplication.getContext().getResources().getConfiguration();
        String language = configuration.locale.getLanguage();
        String country = configuration.locale.getCountry();
        if ("my".equals(language) && Locale.getDefault().getScript() != null && "Qaag".equals(Locale.getDefault().getScript())) {
            country = "ZG";
        }
        if ("en".equals(language) && Locale.getDefault().getScript() != null && "Qaag".equals(Locale.getDefault().getScript())) {
            country = "GB";
        }
        String script = configuration.locale.getScript();
        if (!TextUtils.isEmpty(script)) {
            if ("Hans".equalsIgnoreCase(script)) {
                str = "CN";
            } else if ("Hant".equalsIgnoreCase(script)) {
                str = "TW";
            } else {
                LogUtil.h("HwWatchFaceUtil", "script default");
            }
            country = str;
            language = MLAsrConstants.LAN_ZH;
        }
        StringBuffer stringBuffer = new StringBuffer(language);
        stringBuffer.append("_");
        stringBuffer.append(country);
        return stringBuffer.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int a() {
        boolean z;
        String switchSettingFromDb = jqi.a().getSwitchSettingFromDb("onehop_grs_countryCode");
        String switchSettingFromDb2 = jqi.a().getSwitchSettingFromDb("onehop_grs_watchFaceGrsUrl");
        if (TextUtils.isEmpty(switchSettingFromDb) || TextUtils.isEmpty(switchSettingFromDb2)) {
            switchSettingFromDb2 = GRSManager.a(BaseApplication.getContext()).getNoCheckUrl("domainContentcenterDbankcdnNew", GRSManager.a(BaseApplication.getContext()).c());
        }
        boolean isSupportWatchFaceMarket = WatchFaceUtil.isSupportWatchFaceMarket();
        String arrays = Arrays.toString(Thread.currentThread().getStackTrace());
        OpAnalyticsUtil.getInstance().setProbabilityProblemEvent("watchFaceInitAar", "stackTraceMessage, OneHop ability :" + arrays);
        if (!isSupportWatchFaceMarket || TextUtils.isEmpty(switchSettingFromDb2)) {
            z = false;
        } else {
            LogUtil.a("HwWatchFaceUtil", "can get isGetGrsAbility");
            z = true;
        }
        PackageManager packageManager = BaseApplication.getContext().getPackageManager();
        if (packageManager == null) {
            return 0;
        }
        boolean hasSystemFeature = packageManager.hasSystemFeature("android.hardware.nfc");
        boolean i = Utils.i();
        LogUtil.a("HwWatchFaceUtil", "PackageManager isSupportNfc:", Boolean.valueOf(hasSystemFeature), ",isAllowLogin:", Boolean.valueOf(i));
        return (hasSystemFeature && i && z && c()) ? 1 : 0;
    }

    public static void e(final TouchSupportCallback touchSupportCallback) {
        if (!Utils.f()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.hwdevice.outofprocess.util.HwWatchFaceUtil.2
                @Override // java.lang.Runnable
                public void run() {
                    LogUtil.a("HwWatchFaceUtil", "getOpenCardAbility");
                    TouchSupportCallback touchSupportCallback2 = TouchSupportCallback.this;
                    if (touchSupportCallback2 != null) {
                        touchSupportCallback2.touchSupportResult(HwWatchFaceUtil.a());
                    }
                }
            });
        } else if (touchSupportCallback != null) {
            touchSupportCallback.touchSupportResult(0);
        }
    }

    public static boolean c() {
        boolean z = false;
        try {
            Class.forName("lsk");
            String e2 = lsk.c().e();
            LogUtil.a("HwWatchFaceUtil", "HwOneHopSdk versionCodeString :", e2);
            if (TextUtils.isEmpty(e2)) {
                return false;
            }
            String[] split = e2.split("\\.");
            if (split.length > 3) {
                z = (CommonUtil.e(split[1], 0) != 2 || CommonUtil.e(split[2], 0) < 1) ? CommonUtil.e(split[1], 0) > 2 : true;
            }
            LogUtil.a("HwWatchFaceUtil", "isHandoffServiceSupported:", Boolean.valueOf(z));
            return z;
        } catch (ClassNotFoundException unused) {
            LogUtil.b("HwWatchFaceUtil", "isHandoffServiceSupported can not find HwHandoffSdk");
            return false;
        }
    }

    public static void b(Map<String, Object> map, String str) {
        if (map == null || str == null) {
            LogUtil.h("HwWatchFaceUtil", "resultMap or eventId is null.");
            return;
        }
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(16);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            linkedHashMap.put(entry.getKey(), entry.getValue().toString());
        }
        OpAnalyticsUtil.getInstance().setEvent2nd(str, linkedHashMap);
    }

    public static void bJf_(Context context, Intent intent, String str) {
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.a("HwWatchFaceUtil", str, ": ActivityNotFoundException");
        }
    }

    public void e(int i) {
        LogUtil.a("HwWatchFaceUtil", "gotoWatchFaceBiEvent, type:", Integer.valueOf(i));
        String value = AnalyticsValue.WATCH_FACE_RECORD_1090056.value();
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        hashMap.put("type", Integer.valueOf(i));
        ixx.d().d(BaseApplication.getContext(), value, hashMap, 0);
    }
}
