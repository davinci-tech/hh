package com.huawei.health.faservice;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwdevicemanager.DeviceManager;
import com.huawei.hwdevicemanager.IInitCallback;
import com.huawei.systemserver.dmaccessservice.common.DeviceBasicInfo;
import defpackage.koq;
import health.compact.a.CommonUtil;
import health.compact.a.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes3.dex */
public class HopDeviceUtil {
    private HopDeviceUtil() {
    }

    public static HopDeviceUtil d() {
        return SingletonHolder.SINGLETON_INSTANCE;
    }

    static class SingletonHolder {
        private static final HopDeviceUtil SINGLETON_INSTANCE = new HopDeviceUtil();

        private SingletonHolder() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<DeviceBasicInfo> e() {
        Bundle bundle = new Bundle();
        bundle.putInt("sortType", 0);
        bundle.putString("siftFilter", a());
        List<DeviceBasicInfo> arrayList = new ArrayList<>(0);
        try {
            arrayList = DeviceManager.getInstance(BaseApplication.e()).getTrustedDeviceList("com.huawei.health", bundle);
        } catch (SecurityException e) {
            LogUtil.a("HopDeviceUtil", "getTrustedDeviceList exception: ", e.getMessage());
        }
        LogUtil.c("HopDeviceUtil", "trusted deviceList :", arrayList);
        return d(arrayList);
    }

    private String a() {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        hashMap2.put("harmonyVersion", "2.0.0");
        hashMap.put("system", hashMap2);
        hashMap.put("devType", new String[]{"09C"});
        hashMap.put("groupType", "1");
        hashMap.put("curComType", 4);
        HashMap hashMap3 = new HashMap();
        hashMap3.put("commonFilter", hashMap);
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(hashMap3);
        HashMap hashMap4 = new HashMap();
        hashMap4.put("filters", arrayList);
        String e = HiJsonUtil.e(hashMap4);
        LogUtil.c("HopDeviceUtil", "trustedDeviceFilter: ", e);
        return e;
    }

    public void b() throws ClassNotFoundException, NoClassDefFoundError {
        if (!CommonUtil.az()) {
            LogUtil.c("HopDeviceUtil", "the system is not support. no need to release");
        } else {
            ThreadPoolManager.d().c("HopDeviceUtil", new Runnable() { // from class: com.huawei.health.faservice.HopDeviceUtil$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    HopDeviceUtil.this.c();
                }
            });
        }
    }

    /* synthetic */ void c() {
        DeviceManager.getInstance(BaseApplication.e()).unInitDeviceManager("com.huawei.health", new IInitCallback() { // from class: com.huawei.health.faservice.HopDeviceUtil.1
            public void onInitDone(int i) {
                LogUtil.c("HopDeviceUtil", "unInit onInitDone result: " + i);
            }

            public void onException(int i) {
                LogUtil.a("HopDeviceUtil", "unInit onException errorCode: " + i);
                HopDeviceUtil.this.g();
            }
        });
        g();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        try {
            DeviceManager.getInstance(BaseApplication.e()).release();
        } catch (IllegalArgumentException e) {
            LogUtil.a("HopDeviceUtil", "may the deviceManager not init, exception:", e.getMessage());
        }
    }

    public void b(final UiCallback<List<?>> uiCallback) throws ClassNotFoundException, NoClassDefFoundError {
        LogUtil.c("HopDeviceUtil", "enter hasAvailableFaDevice");
        if (CommonUtil.az()) {
            ThreadPoolManager.d().c("HopDeviceUtil", new Runnable() { // from class: com.huawei.health.faservice.HopDeviceUtil$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    HopDeviceUtil.this.e(uiCallback);
                }
            });
        } else {
            uiCallback.onFailure(2, "the system is not support.");
        }
    }

    /* synthetic */ void e(final UiCallback uiCallback) {
        try {
            DeviceManager.getInstance(BaseApplication.e()).initDeviceManager("com.huawei.health", new IInitCallback() { // from class: com.huawei.health.faservice.HopDeviceUtil.2
                public void onInitDone(int i) {
                    LogUtil.c("HopDeviceUtil", "onInitDone result: " + i);
                    if (i == 0) {
                        List e = HopDeviceUtil.this.e();
                        if (koq.c(e)) {
                            uiCallback.onSuccess(new Handler(Looper.getMainLooper()), e);
                            return;
                        } else {
                            uiCallback.onFailure(1, "no find available device");
                            return;
                        }
                    }
                    uiCallback.onFailure(2, "initDeviceManager fail, result code:", Integer.valueOf(i));
                }

                public void onException(int i) {
                    LogUtil.a("HopDeviceUtil", "onException errorCode: " + i);
                    HopDeviceUtil.this.g();
                }
            });
        } catch (SecurityException unused) {
            LogUtil.e("HopDeviceUtil", "SecurityException happened, executor initDeviceManager failed.");
            uiCallback.onFailure(2, "SecurityException happened, executor initDeviceManager failed.");
        }
    }

    private List<DeviceBasicInfo> d(List<DeviceBasicInfo> list) {
        if (koq.b(list)) {
            LogUtil.c("HopDeviceUtil", "the getTrustedDeviceList method return null");
            return new ArrayList(0);
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (DeviceBasicInfo deviceBasicInfo : list) {
            if (deviceBasicInfo.isDeviceOnline() && deviceBasicInfo.getDeviceType() == DeviceBasicInfo.DeviceType.TELEVISION) {
                arrayList.add(deviceBasicInfo);
            }
        }
        LogUtil.c("HopDeviceUtil", "the available deviceList Info: ", list);
        return arrayList;
    }
}
