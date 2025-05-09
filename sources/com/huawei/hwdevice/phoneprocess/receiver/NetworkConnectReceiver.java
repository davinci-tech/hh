package com.huawei.hwdevice.phoneprocess.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.manager.powerkit.PowerKitManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwUpdateService;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.cun;
import defpackage.jrd;
import defpackage.jsd;
import defpackage.kbg;
import defpackage.kbh;
import defpackage.kkg;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import java.util.List;

/* loaded from: classes.dex */
public class NetworkConnectReceiver extends BroadcastReceiver {
    private static final Object d = new Object();
    private static Handler e;

    public static void bPg_(Handler handler) {
        e = handler;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            LogUtil.a("NetworkConnectReceiver", "receive action: ", action);
            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action) || "android.net.wifi.WIFI_STATE_CHANGED".equals(action)) {
                ConnectivityManager connectivityManager = context.getSystemService("connectivity") instanceof ConnectivityManager ? (ConnectivityManager) context.getSystemService("connectivity") : null;
                if (connectivityManager == null) {
                    LogUtil.h("NetworkConnectReceiver", "connectivityManager is null");
                    return;
                }
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo == null) {
                    LogUtil.h("NetworkConnectReceiver", "network info is null");
                    return;
                }
                try {
                    bPe_(context, activeNetworkInfo);
                    bPf_(context, activeNetworkInfo);
                } catch (Exception e2) {
                    LogUtil.b("NetworkConnectReceiver", "onReceive Exception: ", ExceptionUtils.d(e2));
                }
            }
            if ("com.huawei.health.action.ACTION_SPORT_INTENSITY_INDEX".equals(action)) {
                LogUtil.a("NetworkConnectReceiver", "onReceive sendMediumToHighStrength");
                List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "NetworkConnectReceiver");
                kkg.d(deviceList.size() > 0 ? deviceList.get(0) : null);
            }
        }
    }

    private void bPe_(final Context context, NetworkInfo networkInfo) {
        boolean z = networkInfo.getType() == 1;
        if (networkInfo.isConnected() && z) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.hwdevice.phoneprocess.receiver.NetworkConnectReceiver.5
                @Override // java.lang.Runnable
                public void run() {
                    NetworkConnectReceiver.this.e(context);
                }
            });
        }
    }

    private void bPf_(final Context context, final NetworkInfo networkInfo) {
        synchronized (d) {
            final boolean z = true;
            if (networkInfo.getType() != 1) {
                z = false;
            }
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.hwdevice.phoneprocess.receiver.NetworkConnectReceiver.2
                @Override // java.lang.Runnable
                public void run() {
                    boolean b = PowerKitManager.e().b();
                    LogUtil.a("NetworkConnectReceiver", "connected: ", Boolean.valueOf(networkInfo.isConnected()), ";sleep:", Boolean.valueOf(b));
                    if (!networkInfo.isConnected() || b) {
                        return;
                    }
                    NetworkConnectReceiver.this.e();
                    if (z) {
                        NetworkConnectReceiver.this.c(context);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Context context) {
        LogUtil.a("NetworkConnectReceiver", "checkWifiConnected start");
        e.postDelayed(new Runnable() { // from class: com.huawei.hwdevice.phoneprocess.receiver.NetworkConnectReceiver.3
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("NetworkConnectReceiver", "checkWifiConnected end");
                List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, null, "NetworkConnectReceiver");
                DeviceInfo a2 = deviceList.size() > 0 ? NetworkConnectReceiver.this.a(deviceList) : null;
                if (a2 == null) {
                    LogUtil.h("NetworkConnectReceiver", "otherConnectedDevice == null");
                    return;
                }
                boolean e2 = jrd.e();
                LogUtil.a("NetworkConnectReceiver", "checkWifiConnected isMobileTraffic ", Boolean.valueOf(e2));
                NetworkConnectReceiver.this.c(a2, !e2);
            }
        }, 1000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(Context context) {
        LogUtil.a("NetworkConnectReceiver", "handleUploadLog enter");
        if (CommonUtil.bv()) {
            jsd.e(context, "");
            kbh.a().a(context);
        } else {
            LogUtil.a("NetworkConnectReceiver", "betaUpload enter");
            kbg.e().a(context);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DeviceInfo a(List<DeviceInfo> list) {
        for (DeviceInfo deviceInfo : list) {
            if (jrd.a(deviceInfo.getDeviceIdentify())) {
                return deviceInfo;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final DeviceInfo deviceInfo, boolean z) {
        LogUtil.a("NetworkConnectReceiver", "Enter sendConnectStateBroadcast().");
        if (nsn.o()) {
            LogUtil.h("NetworkConnectReceiver", "startMainService Utils.isFastClick(): ", Boolean.valueOf(nsn.o()));
            return;
        }
        e.removeMessages(101);
        if (z) {
            LogUtil.a("NetworkConnectReceiver", "send MESSAGE_NETWORK_WIFI_CONNECTED");
            e.postDelayed(new Runnable() { // from class: com.huawei.hwdevice.phoneprocess.receiver.NetworkConnectReceiver.1
                @Override // java.lang.Runnable
                public void run() {
                    boolean e2 = jrd.e();
                    LogUtil.h("NetworkConnectReceiver", "startMainService MESSAGE_NETWORK_WIFI_CONNECTED isMobileTraffic ", Boolean.valueOf(e2));
                    if (e2) {
                        return;
                    }
                    Intent intent = new Intent(BaseApplication.getContext(), (Class<?>) HwUpdateService.class);
                    intent.putExtra("extra_band_imei", deviceInfo.getDeviceIdentify());
                    intent.setAction("action_band_auto_download");
                    try {
                        BaseApplication.getContext().startService(intent);
                    } catch (IllegalStateException | SecurityException e3) {
                        LogUtil.b("startMainService MESSAGE_NETWORK_WIFI_CONNECTED Exception", ExceptionUtils.d(e3));
                    }
                }
            }, 60000L);
            return;
        }
        LogUtil.a("NetworkConnectReceiver", "remove EphemerisConstants.MESSAGE_NETWORK_WIFI_CONNECTED");
        Intent intent = new Intent(BaseApplication.getContext(), (Class<?>) HwUpdateService.class);
        intent.setAction("action_cancel_download_all_ota");
        try {
            BaseApplication.getContext().startService(intent);
        } catch (IllegalStateException | SecurityException e2) {
            LogUtil.b("startMainService MESSAGE_NETWORK_WIFI_CONNECTED Exception", ExceptionUtils.d(e2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        Handler handler = e;
        if (handler != null) {
            handler.sendEmptyMessageDelayed(100, 3000L);
        } else {
            LogUtil.h("NetworkConnectReceiver", "mHandler is null");
        }
    }
}
