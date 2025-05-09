package com.huawei.health.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BadParcelableException;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.userlabelmgr.api.UserLabelServiceApi;
import com.huawei.hihealth.HiDeviceInfo;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;

/* loaded from: classes3.dex */
public class DeviceBindReceiver extends BroadcastReceiver {
    private static DeviceBindReceiver e;

    public static void d() {
        e = new DeviceBindReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.hihealth.binding_device");
        BroadcastManagerUtil.bFA_(BaseApplication.e(), e, intentFilter, "com.huawei.hihealth.DEFAULT_PERMISSION", null);
    }

    public static void e() {
        if (e != null) {
            try {
                BaseApplication.e().unregisterReceiver(e);
            } catch (IllegalArgumentException unused) {
                ReleaseLogUtil.c("DeviceBindReceiver", "unregister IllegalArgumentException");
            }
            e = null;
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        final HiDeviceInfo hiDeviceInfo;
        if (intent == null) {
            LogUtil.c("DeviceBindReceiver", "Broadcast triggered, but intent is null...");
            return;
        }
        LogUtil.c("DeviceBindReceiver", "BindDeviceBroadcastReceiver enter");
        try {
            hiDeviceInfo = (HiDeviceInfo) intent.getParcelableExtra("devicinfo");
        } catch (BadParcelableException unused) {
            LogUtil.e("DeviceBindReceiver", "bindDeviceBroadcastReceiver BadParcelableException");
            hiDeviceInfo = null;
        }
        if (hiDeviceInfo == null) {
            return;
        }
        String b = SharedPreferenceManager.b(BaseApplication.e(), Integer.toString(10000), "SAVE_BIND_TO_ODMF");
        int deviceTypeMapping = hiDeviceInfo.getDeviceTypeMapping();
        if (!"TRUE".equals(b) || deviceTypeMapping == -1) {
            return;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.receiver.DeviceBindReceiver.1
            @Override // java.lang.Runnable
            public void run() {
                ((UserLabelServiceApi) Services.c("HWUserLabelMgr", UserLabelServiceApi.class)).saveBindingDeviceToOdmf(hiDeviceInfo.getDeviceInfoToODMF());
            }
        });
    }
}
