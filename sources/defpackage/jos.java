package defpackage;

import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.healthdatamgr.api.HealthDataMgrApi;
import com.huawei.hihealth.data.model.HiStressMetaData;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class jos {
    public static void a(List<String> list, IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            ReleaseLogUtil.d("DEVMGR_HealthMonitorSwitchUtil", "getHealthMonitorSwitch callback is null");
            return;
        }
        if (list == null || list.size() == 0) {
            ReleaseLogUtil.d("DEVMGR_HealthMonitorSwitchUtil", "getHealthMonitorSwitch keys is error");
            iBaseResponseCallback.d(-1, null);
            return;
        }
        DeviceInfo d = d("HealthMonitorSwitchUtil");
        if (d != null && d.getDeviceConnectState() == 2) {
            LogUtil.a("HealthMonitorSwitchUtil", "getHealthMonitorSwitch switchKeys:", list.toString());
            JSONObject jSONObject = new JSONObject();
            e(e(list, jSONObject), iBaseResponseCallback, jSONObject);
        } else {
            ReleaseLogUtil.d("DEVMGR_HealthMonitorSwitchUtil", "getHealthMonitorSwitch deviceInfo is null or not connected");
            iBaseResponseCallback.d(-2, null);
        }
    }

    private static void e(final List<String> list, final IBaseResponseCallback iBaseResponseCallback, final JSONObject jSONObject) {
        LogUtil.a("HealthMonitorSwitchUtil", "querySwitchSetting switchKeys:", list.toString());
        ThreadPoolManager.d().d("HealthMonitorSwitchUtil", new Runnable() { // from class: jos.5
            @Override // java.lang.Runnable
            public void run() {
                List<String> e = jos.e((List<String>) list);
                if (e.isEmpty()) {
                    ReleaseLogUtil.d("DEVMGR_HealthMonitorSwitchUtil", "getHealthMonitorSwitch querySwitchKeys is null or empty");
                    jos.c(jSONObject, iBaseResponseCallback);
                    return;
                }
                for (String str : e) {
                    LogUtil.a("HealthMonitorSwitchUtil", "querySwitchSetting key:", str);
                    final CountDownLatch countDownLatch = new CountDownLatch(1);
                    jos.a(str, new IBaseResponseCallback() { // from class: jos.5.3
                        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                        /* renamed from: onResponse */
                        public void d(int i, Object obj) {
                            if (obj instanceof String) {
                                String str2 = (String) obj;
                                LogUtil.a("HealthMonitorSwitchUtil", "querySwitchSetting queryKey:", str2, ", switchStatus:", Integer.valueOf(i));
                                try {
                                    jSONObject.put(jos.a(str2), i);
                                } catch (JSONException unused) {
                                    ReleaseLogUtil.c("DEVMGR_HealthMonitorSwitchUtil", "querySwitchSetting JSONException");
                                }
                            }
                            countDownLatch.countDown();
                        }
                    });
                    try {
                        countDownLatch.await(500L, TimeUnit.MILLISECONDS);
                    } catch (InterruptedException unused) {
                        ReleaseLogUtil.c("DEVMGR_HealthMonitorSwitchUtil", "querySwitchSetting InterruptedException");
                    }
                }
                LogUtil.a("HealthMonitorSwitchUtil", "querySwitchSetting responseResult:", jSONObject.toString());
                jos.c(jSONObject, iBaseResponseCallback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(final String str, final IBaseResponseCallback iBaseResponseCallback) {
        jqi.a().getSwitchSetting(str, new IBaseResponseCallback() { // from class: jos.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                int h = CommonUtil.h("0");
                ReleaseLogUtil.e("DEVMGR_HealthMonitorSwitchUtil", "getSwitchStatus key:", str, ", errorCode:", Integer.valueOf(i));
                if (i == 0 && (obj instanceof String)) {
                    String str2 = (String) obj;
                    LogUtil.a("HealthMonitorSwitchUtil", "getSwitchStatus switchValue:", str2);
                    if ("true".equals(str2) || "1".equals(str2)) {
                        h = CommonUtil.h("1");
                    } else if (!"false".equals(str2) && !"0".equals(str2)) {
                        LogUtil.h("HealthMonitorSwitchUtil", "getSwitchStatus key:", str, ", switchValue:", str2);
                    } else {
                        h = CommonUtil.h("0");
                    }
                }
                iBaseResponseCallback.d(h, str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) {
        if (jSONObject == null || jSONObject.length() == 0) {
            LogUtil.h("HealthMonitorSwitchUtil", "callbackResult error");
            iBaseResponseCallback.d(-1, null);
        } else {
            LogUtil.h("HealthMonitorSwitchUtil", "callbackResult success");
            iBaseResponseCallback.d(0, jSONObject);
        }
    }

    private static DeviceInfo d(String str) {
        DeviceInfo deviceInfo = null;
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, str);
        if (deviceList != null && !deviceList.isEmpty()) {
            Iterator<DeviceInfo> it = deviceList.iterator();
            if (it.hasNext()) {
                deviceInfo = it.next();
            }
        }
        LogUtil.a("HealthMonitorSwitchUtil", "getConnectMainDevice() deviceInfo ", deviceInfo, ", tag:", str);
        return deviceInfo;
    }

    private static List<String> e(List<String> list, JSONObject jSONObject) {
        DeviceCapability d = cvs.d();
        ArrayList arrayList = new ArrayList(16);
        for (String str : list) {
            if (!TextUtils.isEmpty(str)) {
                int c = c(str, d);
                if (c == -3) {
                    try {
                        jSONObject.put(str, -3);
                    } catch (JSONException unused) {
                        ReleaseLogUtil.c("DEVMGR_HealthMonitorSwitchUtil", "getSupportSwitch JSONException");
                    }
                } else if (c == -4) {
                    jSONObject.put(str, -4);
                } else {
                    arrayList.add(str);
                }
            }
        }
        LogUtil.a("HealthMonitorSwitchUtil", "getSupportSwitch supportList:", arrayList.toString());
        return arrayList;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static int c(String str, DeviceCapability deviceCapability) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case -2104706991:
                if (str.equals("custom.blood.oxygen.switch")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1981990342:
                if (str.equals("core_sleep_button")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -944684806:
                if (str.equals("heart_rate_switch")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -45309916:
                if (str.equals("press_auto_monitor_switch_status")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            if (deviceCapability.isSupportCycleBloodOxygenSwitch()) {
                return 0;
            }
            ReleaseLogUtil.d("DEVMGR_HealthMonitorSwitchUtil", "isKeySupportCapability isSupportCycleBloodOxygenSwitch false");
            return -3;
        }
        if (c == 1) {
            if (deviceCapability.isSupportCoreSleep()) {
                return 0;
            }
            ReleaseLogUtil.d("DEVMGR_HealthMonitorSwitchUtil", "isKeySupportCapability isSupportCoreSleep false");
            return -3;
        }
        if (c == 2) {
            if (deviceCapability.isSupportContinueHeartRate() || deviceCapability.isSupportHeartRateEnable()) {
                return 0;
            }
            ReleaseLogUtil.d("DEVMGR_HealthMonitorSwitchUtil", "isKeySupportCapability isSupportHeartRate false");
            return -3;
        }
        if (c == 3) {
            if (deviceCapability.isSupportPressAutoMonitor()) {
                return 0;
            }
            ReleaseLogUtil.d("DEVMGR_HealthMonitorSwitchUtil", "isKeySupportCapability isSupportPressAutoMonitor false");
            return -3;
        }
        ReleaseLogUtil.d("DEVMGR_HealthMonitorSwitchUtil", "isKeySupportCapability other key false， not support");
        return -4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<String> e(List<String> list) {
        ArrayList arrayList = new ArrayList(16);
        for (String str : list) {
            if ("heart_rate_switch".equals(str)) {
                DeviceCapability d = cvs.d();
                if (d.isSupportContinueHeartRate()) {
                    str = "continue_heart_rate";
                } else if (d.isSupportHeartRateEnable()) {
                    str = "heart_rate_button";
                } else {
                    LogUtil.h("HealthMonitorSwitchUtil", "replaceHeartRateKey other key false");
                }
                arrayList.add(str);
            } else {
                arrayList.add(str);
            }
        }
        LogUtil.a("HealthMonitorSwitchUtil", "replaceHeartRateKey resultSwitchKeys：", arrayList.toString());
        return arrayList;
    }

    public static void a(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            ReleaseLogUtil.d("DEVMGR_HealthMonitorSwitchUtil", "setHealthMonitorSwitch callback is null");
            return;
        }
        if (jSONObject == null || jSONObject.length() == 0) {
            ReleaseLogUtil.d("DEVMGR_HealthMonitorSwitchUtil", "setHealthMonitorSwitch keyParam is error");
            iBaseResponseCallback.d(-1, null);
            return;
        }
        DeviceInfo d = d("HealthMonitorSwitchUtil");
        if (d != null && d.getDeviceConnectState() == 2) {
            LogUtil.a("HealthMonitorSwitchUtil", "setHealthMonitorSwitch switchKeys:", jSONObject.toString());
            JSONObject jSONObject2 = new JSONObject();
            ArrayList arrayList = new ArrayList(16);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                arrayList.add(keys.next());
            }
            d(e(arrayList, jSONObject2), jSONObject, iBaseResponseCallback, jSONObject2);
            return;
        }
        ReleaseLogUtil.d("DEVMGR_HealthMonitorSwitchUtil", "setHealthMonitorSwitch deviceInfo is null or not connected");
        iBaseResponseCallback.d(-2, null);
    }

    private static void d(final List<String> list, final JSONObject jSONObject, final IBaseResponseCallback iBaseResponseCallback, final JSONObject jSONObject2) {
        LogUtil.a("HealthMonitorSwitchUtil", "saveAndSendHealthSwitch switchKeys:", list.toString(), ",keyParam :", jSONObject);
        ThreadPoolManager.d().d("HealthMonitorSwitchUtil", new Runnable() { // from class: jos.2
            @Override // java.lang.Runnable
            public void run() {
                List<String> e = jos.e((List<String>) list);
                if (e.isEmpty()) {
                    ReleaseLogUtil.d("DEVMGR_HealthMonitorSwitchUtil", "saveAndSendHealthSwitch setSwitchKeys is null or empty");
                    jos.c(jSONObject2, iBaseResponseCallback);
                    return;
                }
                for (String str : e) {
                    LogUtil.a("HealthMonitorSwitchUtil", "saveAndSendHealthSwitch key:", str);
                    final CountDownLatch countDownLatch = new CountDownLatch(1);
                    jos.a(str, jSONObject, new IBaseResponseCallback() { // from class: jos.2.5
                        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                        /* renamed from: onResponse */
                        public void d(int i, Object obj) {
                            if (obj instanceof String) {
                                String str2 = (String) obj;
                                LogUtil.a("HealthMonitorSwitchUtil", "saveAndSendHealthSwitch objectData:", str2);
                                try {
                                    jSONObject2.put(jos.a(str2), i);
                                } catch (JSONException unused) {
                                    ReleaseLogUtil.c("DEVMGR_HealthMonitorSwitchUtil", "saveAndSendHealthSwitch JSONException");
                                }
                            }
                            countDownLatch.countDown();
                        }
                    });
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException unused) {
                        ReleaseLogUtil.c("DEVMGR_HealthMonitorSwitchUtil", "saveAndSendHealthSwitch InterruptedException");
                    }
                }
                LogUtil.a("HealthMonitorSwitchUtil", "saveAndSendHealthSwitch responseResult:", jSONObject2.toString());
                jos.c(jSONObject2, iBaseResponseCallback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(String str, JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) {
        String valueOf = String.valueOf(jSONObject.optInt(a(str)));
        LogUtil.a("HealthMonitorSwitchUtil", "setSwitchKey key:", str);
        if ("press_auto_monitor_switch_status".equals(str) && "1".equals(valueOf)) {
            b(iBaseResponseCallback);
        } else {
            c(valueOf, str, iBaseResponseCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(final String str, final String str2, final IBaseResponseCallback iBaseResponseCallback) {
        if ("press_auto_monitor_switch_status".equals(str2)) {
            str = "1".equals(str) ? "true" : "false";
        }
        jqi.a().setSwitchSetting(str2, str, new IBaseResponseCallback() { // from class: jos.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("HealthMonitorSwitchUtil", "updateAndSendSwitch onResponse key:", str2, ", switchStatus:", str, ", errorCode:", Integer.valueOf(i));
                if (i == 0) {
                    if ("continue_heart_rate".equals(str2)) {
                        jos.b(str);
                    } else {
                        jos.d(str2, str);
                    }
                } else {
                    ReleaseLogUtil.d("DEVMGR_HealthMonitorSwitchUtil", "updateAndSendSwitch setSwitchSetting error");
                }
                iBaseResponseCallback.d(i, str2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String a(String str) {
        return ("continue_heart_rate".equals(str) || "heart_rate_button".equals(str)) ? "heart_rate_switch" : str;
    }

    private static void b(final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("HealthMonitorSwitchUtil", "getCalibratedStatus enter");
        a(new IBaseResponseCallback() { // from class: jos.3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("HealthMonitorSwitchUtil", "getCalibratedStatus errorCode:", Integer.valueOf(i));
                boolean booleanValue = (i != 0 || obj == null) ? false : ((Boolean) obj).booleanValue();
                ReleaseLogUtil.d("DEVMGR_HealthMonitorSwitchUtil", "getCalibratedStatus isCalibrated :", Boolean.valueOf(booleanValue));
                if (booleanValue) {
                    jos.c("1", "press_auto_monitor_switch_status", IBaseResponseCallback.this);
                } else {
                    IBaseResponseCallback.this.d(-2, "press_auto_monitor_switch_status");
                }
            }
        });
    }

    private static void a(final IBaseResponseCallback iBaseResponseCallback) {
        ((HealthDataMgrApi) Services.a("HWHealthDataMgr", HealthDataMgrApi.class)).getUserPressureAdjustDatas(new IBaseResponseCallback() { // from class: jos.7
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (i == 0 && (obj instanceof String)) {
                    HiStressMetaData c = jlg.c((String) obj);
                    if (c == null) {
                        ReleaseLogUtil.d("DEVMGR_HealthMonitorSwitchUtil", "getUserPressureAdjustData metaData is null");
                        IBaseResponseCallback.this.d(-1, false);
                        return;
                    }
                    int fetchStressCalibFlag = c.fetchStressCalibFlag();
                    int fetchStressScore = c.fetchStressScore();
                    LogUtil.a("HealthMonitorSwitchUtil", "getUserPressureAdjustData calibrateFlag :", Integer.valueOf(fetchStressCalibFlag), ", score:", Integer.valueOf(fetchStressScore));
                    if (fetchStressCalibFlag == 1 && fetchStressScore > 0 && fetchStressScore < 100) {
                        IBaseResponseCallback.this.d(0, true);
                        return;
                    } else {
                        ReleaseLogUtil.d("DEVMGR_HealthMonitorSwitchUtil", "getUserPressureAdjustData calibrateFlag or score is error");
                        IBaseResponseCallback.this.d(-1, false);
                        return;
                    }
                }
                ReleaseLogUtil.d("DEVMGR_HealthMonitorSwitchUtil", "getUserPressureAdjustData error");
                IBaseResponseCallback.this.d(-1, false);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x003a, code lost:
    
        if (r6.equals("custom.blood.oxygen.switch") == false) goto L19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void d(java.lang.String r6, java.lang.String r7) {
        /*
            r0 = 0
            int r1 = health.compact.a.CommonUtil.e(r7, r0)
            r6.hashCode()
            int r2 = r6.hashCode()
            r3 = 3
            r4 = 1
            r5 = 2
            switch(r2) {
                case -2104706991: goto L34;
                case -1981990342: goto L29;
                case -1432899336: goto L1e;
                case -45309916: goto L13;
                default: goto L12;
            }
        L12:
            goto L3c
        L13:
            java.lang.String r0 = "press_auto_monitor_switch_status"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L1c
            goto L3c
        L1c:
            r0 = r3
            goto L3d
        L1e:
            java.lang.String r0 = "heart_rate_button"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L27
            goto L3c
        L27:
            r0 = r5
            goto L3d
        L29:
            java.lang.String r0 = "core_sleep_button"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L32
            goto L3c
        L32:
            r0 = r4
            goto L3d
        L34:
            java.lang.String r2 = "custom.blood.oxygen.switch"
            boolean r6 = r6.equals(r2)
            if (r6 != 0) goto L3d
        L3c:
            r0 = -1
        L3d:
            if (r0 == 0) goto L6b
            if (r0 == r4) goto L67
            if (r0 == r5) goto L63
            if (r0 == r3) goto L51
            java.lang.String r6 = "sendSwitchToDeviceMore other key false"
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            java.lang.String r7 = "HealthMonitorSwitchUtil"
            com.huawei.hwlogsmodel.LogUtil.h(r7, r6)
            goto L6e
        L51:
            java.lang.String r6 = "true"
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L5a
            goto L5b
        L5a:
            r4 = r5
        L5b:
            jld r6 = defpackage.jld.b()
            r6.a(r4)
            goto L6e
        L63:
            defpackage.jho.a(r1)
            goto L6e
        L67:
            defpackage.jho.e(r1)
            goto L6e
        L6b:
            defpackage.jho.c(r1)
        L6e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jos.d(java.lang.String, java.lang.String):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(String str) {
        final int e = CommonUtil.e(str, 0);
        jqi.a().getSwitchSetting("heart_rate_mode", new IBaseResponseCallback() { // from class: jos.10
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ReleaseLogUtil.e("DEVMGR_HealthMonitorSwitchUtil", "sendContinueHeartRate heart_rate_mode errorCode: ", Integer.valueOf(i));
                int m = (i == 0 && (obj instanceof String)) ? CommonUtil.m(BaseApplication.getContext(), (String) obj) : 1;
                LogUtil.a("HealthMonitorSwitchUtil", "sendContinueHeartRate heart_rate_mode : ", Integer.valueOf(m));
                if (m == 0) {
                    jho.b(e);
                } else {
                    if (m != 1) {
                        return;
                    }
                    jho.a(e);
                }
            }
        });
    }
}
