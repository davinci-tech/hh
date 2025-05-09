package com.huawei.indoorequip.device;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import defpackage.bin;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LogUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class DeviceRemovedListenerManager {

    /* renamed from: a, reason: collision with root package name */
    private final BroadcastReceiver f6428a;
    private Map<String, IDeviceRemovedListener> d;

    public interface IDeviceRemovedListener {
        void onDeviceRemoved(String str);
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        private static final DeviceRemovedListenerManager f6429a = new DeviceRemovedListenerManager();
    }

    public static DeviceRemovedListenerManager c() {
        return a.f6429a;
    }

    private DeviceRemovedListenerManager() {
        this.d = new HashMap();
        this.f6428a = new BroadcastReceiver() { // from class: com.huawei.indoorequip.device.DeviceRemovedListenerManager.4
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (intent == null) {
                    LogUtil.c("DeviceRemovedListenerManager", "onReceive ", "intent or mContext is null");
                    return;
                }
                String action = intent.getAction();
                LogUtil.c("DeviceRemovedListenerManager", "DeviceRemovedReceiver receive action: ", action);
                if (TextUtils.isEmpty(action)) {
                    return;
                }
                if ("com.huawei.health.action.DEVICE_CHANGED".equals(action)) {
                    int intExtra = intent.getIntExtra("operation", 0);
                    String stringExtra = intent.getStringExtra("uniqueId");
                    if (intExtra == -1 && !TextUtils.isEmpty(stringExtra) && DeviceRemovedListenerManager.this.d.containsKey(stringExtra)) {
                        IDeviceRemovedListener iDeviceRemovedListener = (IDeviceRemovedListener) DeviceRemovedListenerManager.this.d.get(stringExtra);
                        if (iDeviceRemovedListener != null) {
                            iDeviceRemovedListener.onDeviceRemoved(stringExtra);
                            return;
                        }
                        return;
                    }
                    LogUtil.c("DeviceRemovedListenerManager", "Other operation or mac not match, no response");
                    return;
                }
                LogUtil.a("DeviceRemovedListenerManager", "Other action, no response");
            }
        };
    }

    public void e(String str, IDeviceRemovedListener iDeviceRemovedListener) {
        LogUtil.c("DeviceRemovedListenerManager", "enter registerDeviceRemovedListener");
        if (iDeviceRemovedListener == null) {
            LogUtil.a("DeviceRemovedListenerManager", "listener is null");
        } else {
            if (this.d.containsKey(str)) {
                LogUtil.a("DeviceRemovedListenerManager", "duplicate mac address.");
                return;
            }
            if (this.d.size() == 0) {
                a();
            }
            this.d.put(str, iDeviceRemovedListener);
        }
    }

    public void a(String str) {
        LogUtil.c("DeviceRemovedListenerManager", "enter removeDeviceRemovedListener");
        if (!this.d.containsKey(str)) {
            LogUtil.a("DeviceRemovedListenerManager", "mac not exists");
            return;
        }
        this.d.remove(str);
        if (this.d.size() == 0) {
            b();
        }
    }

    private void a() {
        LogUtil.c("DeviceRemovedListenerManager", "registerDeviceChangedReceiver");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.health.action.DEVICE_CHANGED");
        BroadcastManagerUtil.bFA_(BaseApplication.getContext(), this.f6428a, intentFilter, bin.d, null);
    }

    private void b() {
        BaseApplication.getContext().unregisterReceiver(this.f6428a);
    }
}
