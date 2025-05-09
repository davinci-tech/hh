package com.huawei.hwdevice.phoneprocess.mgr.hwwifisyncmgr;

import android.content.BroadcastReceiver;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import defpackage.knl;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class WifiConnectReceiver extends BroadcastReceiver {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f6347a = new Object();
    private static WifiConnectReceiver e;
    private List<String> d = new ArrayList(16);

    private WifiConnectReceiver() {
    }

    public static WifiConnectReceiver b() {
        WifiConnectReceiver wifiConnectReceiver;
        synchronized (f6347a) {
            if (e == null) {
                e = new WifiConnectReceiver();
            }
            wifiConnectReceiver = e;
        }
        return wifiConnectReceiver;
    }

    public List<String> e() {
        return this.d;
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x0096, code lost:
    
        if (r5.getState().equals(android.net.NetworkInfo.State.CONNECTED) != false) goto L34;
     */
    @Override // android.content.BroadcastReceiver
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onReceive(android.content.Context r4, android.content.Intent r5) {
        /*
            r3 = this;
            java.lang.String r4 = "WifiConnectReceiver_PhoneService"
            if (r5 == 0) goto Ld4
            java.lang.String r0 = r5.getAction()
            if (r0 != 0) goto Lc
            goto Ld4
        Lc:
            cun r0 = defpackage.cun.c()
            com.huawei.health.devicemgr.api.constant.HwGetDevicesMode r1 = com.huawei.health.devicemgr.api.constant.HwGetDevicesMode.ALL_DEVICES
            r2 = 0
            java.util.List r0 = r0.getDeviceList(r1, r2, r4)
            if (r0 == 0) goto Lba
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L21
            goto Lba
        L21:
            java.lang.String r0 = "current action: "
            java.lang.String r1 = r5.getAction()
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r1}
            health.compact.a.util.LogUtil.d(r4, r0)
            java.lang.String r0 = "android.net.wifi.WIFI_STATE_CHANGED"
            java.lang.String r1 = r5.getAction()
            boolean r0 = r0.equals(r1)
            java.lang.String r1 = "com.huawei.bone.action.CONNECTION_STATE_CHANGED"
            if (r0 != 0) goto L52
            java.lang.String r0 = "android.net.conn.CONNECTIVITY_CHANGE"
            java.lang.String r2 = r5.getAction()
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L52
            java.lang.String r0 = r5.getAction()
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto Laf
        L52:
            java.lang.String r0 = r5.getAction()
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L7b
            java.lang.String r0 = "deviceinfo"
            android.os.Parcelable r5 = r5.getParcelableExtra(r0)
            com.huawei.health.devicemgr.business.entity.DeviceInfo r5 = (com.huawei.health.devicemgr.business.entity.DeviceInfo) r5
            if (r5 != 0) goto L70
            java.lang.String r5 = "ConnectStateChangedReceiver(), deviceInfo is null"
            java.lang.Object[] r5 = new java.lang.Object[]{r5}
            health.compact.a.util.LogUtil.c(r4, r5)
            return
        L70:
            int r0 = r5.getDeviceConnectState()
            r1 = 2
            if (r0 != r1) goto La5
            r3.d(r5)
            goto L98
        L7b:
            java.lang.String r0 = "networkInfo"
            android.os.Parcelable r5 = r5.getParcelableExtra(r0)
            android.net.NetworkInfo r5 = (android.net.NetworkInfo) r5
            if (r5 == 0) goto Lb0
            android.net.NetworkInfo$State r0 = r5.getState()
            if (r0 != 0) goto L8c
            goto Lb0
        L8c:
            android.net.NetworkInfo$State r5 = r5.getState()
            android.net.NetworkInfo$State r0 = android.net.NetworkInfo.State.CONNECTED
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto La5
        L98:
            com.huawei.haf.threadpool.ThreadPoolManager r4 = com.huawei.haf.threadpool.ThreadPoolManager.d()
            com.huawei.hwdevice.phoneprocess.mgr.hwwifisyncmgr.WifiConnectReceiver$3 r5 = new com.huawei.hwdevice.phoneprocess.mgr.hwwifisyncmgr.WifiConnectReceiver$3
            r5.<init>()
            r4.execute(r5)
            goto Laf
        La5:
            java.lang.String r5 = "wifi is not connected"
            java.lang.Object[] r5 = new java.lang.Object[]{r5}
            health.compact.a.util.LogUtil.c(r4, r5)
        Laf:
            return
        Lb0:
            java.lang.String r5 = "info.getState is null"
            java.lang.Object[] r5 = new java.lang.Object[]{r5}
            health.compact.a.util.LogUtil.c(r4, r5)
            return
        Lba:
            java.lang.String r5 = "current deviceList is empty"
            java.lang.Object[] r5 = new java.lang.Object[]{r5}
            health.compact.a.util.LogUtil.c(r4, r5)
            android.content.Context r4 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            health.compact.a.KeyValDbManager r4 = health.compact.a.KeyValDbManager.b(r4)
            java.lang.String r5 = "saved_support_sync_wifi"
            java.lang.String r0 = "0"
            r4.e(r5, r0)
            return
        Ld4:
            java.lang.String r5 = "intent is null"
            java.lang.Object[] r5 = new java.lang.Object[]{r5}
            health.compact.a.util.LogUtil.c(r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hwdevice.phoneprocess.mgr.hwwifisyncmgr.WifiConnectReceiver.onReceive(android.content.Context, android.content.Intent):void");
    }

    private void d(DeviceInfo deviceInfo) {
        if (deviceInfo.getDeviceFactoryReset() == 1) {
            this.d.add(knl.a(deviceInfo.getDeviceIdentify()));
        }
    }
}
