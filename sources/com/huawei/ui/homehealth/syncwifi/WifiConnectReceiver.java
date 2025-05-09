package com.huawei.ui.homehealth.syncwifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import com.huawei.haf.common.utils.NetworkUtil;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import defpackage.cpl;
import defpackage.kft;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.KeyValDbManager;
import health.compact.a.LocalBroadcast;
import health.compact.a.util.LogUtil;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes6.dex */
public class WifiConnectReceiver extends BroadcastReceiver {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f9622a = new Object();
    private static AtomicInteger d = new AtomicInteger(0);
    private static WifiConnectReceiver e;
    private ConnectivityManager.NetworkCallback b;

    private WifiConnectReceiver() {
    }

    public static WifiConnectReceiver a() {
        WifiConnectReceiver wifiConnectReceiver;
        synchronized (f9622a) {
            if (e == null) {
                e = new WifiConnectReceiver();
            }
            wifiConnectReceiver = e;
        }
        return wifiConnectReceiver;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getAction() == null) {
            LogUtil.c("WifiConnectReceiver_Main", "intent is null");
            return;
        }
        List<DeviceInfo> h = cpl.c().h();
        if (h == null || h.isEmpty()) {
            LogUtil.c("WifiConnectReceiver_Main", "enter registerWifiConnectedReceiver deviceInfoList is null");
            KeyValDbManager.b(BaseApplication.getContext()).e("saved_support_sync_wifi", "0");
            return;
        }
        LogUtil.d("WifiConnectReceiver_Main", "current action: ", intent.getAction());
        if ("android.net.wifi.WIFI_STATE_CHANGED".equals(intent.getAction()) || "android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
            NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
            if (networkInfo == null || networkInfo.getState() == null) {
                LogUtil.c("WifiConnectReceiver_Main", "info.getState is null");
            } else if (networkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.homehealth.syncwifi.WifiConnectReceiver.1
                    @Override // java.lang.Runnable
                    public void run() {
                        kft.b();
                    }
                });
            } else {
                LogUtil.c("WifiConnectReceiver_Main", "wifi is not connected");
            }
        }
    }

    public void e() {
        synchronized (f9622a) {
            if (e != null && d.get() == 0) {
                LogUtil.d("WifiConnectReceiver_Main", "enter registerWifiConnectReceiver");
                try {
                    IntentFilter intentFilter = new IntentFilter();
                    intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                    intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
                    if (EnvironmentInfo.r()) {
                        d();
                        try {
                            NetworkUtil.xJ_(this.b);
                            c(NetworkUtil.i());
                        } catch (RuntimeException unused) {
                            LogUtil.e("WifiConnectReceiver_Main", "registerWifiConnectReceiver registerNetworkCallback exception");
                        }
                        LogUtil.d("WifiConnectReceiver_Main", "registerWifiConnectReceiver registerNetworkCallback is success");
                    } else {
                        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
                    }
                    BroadcastManagerUtil.bFC_(BaseApplication.getContext(), e, intentFilter, LocalBroadcast.c, null);
                    d.incrementAndGet();
                } catch (IllegalArgumentException unused2) {
                    LogUtil.e("WifiConnectReceiver_Main", "registerWifiConnectReceiver is failed");
                }
            }
        }
    }

    public void b() {
        h();
        c();
    }

    private void h() {
        synchronized (f9622a) {
            if (e != null && d.get() == 1) {
                try {
                    BaseApplication.getContext().unregisterReceiver(e);
                    d.decrementAndGet();
                    if (this.b != null && EnvironmentInfo.r()) {
                        NetworkUtil.xK_(this.b);
                        this.b = null;
                    }
                } catch (IllegalArgumentException unused) {
                    LogUtil.e("WifiConnectReceiver_Main", "unRegisterWifiConnectReceiver is failed");
                }
            }
        }
    }

    private static void c() {
        synchronized (f9622a) {
            e = null;
        }
    }

    private void d() {
        if (EnvironmentInfo.r()) {
            ConnectivityManager.NetworkCallback networkCallback = this.b;
            if (networkCallback != null) {
                NetworkUtil.xK_(networkCallback);
                this.b = null;
            }
            this.b = new ConnectivityManager.NetworkCallback() { // from class: com.huawei.ui.homehealth.syncwifi.WifiConnectReceiver.3
                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onAvailable(Network network) {
                    super.onAvailable(network);
                    WifiConnectReceiver.this.c(true);
                }

                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onLost(Network network) {
                    super.onLost(network);
                }

                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
                    super.onCapabilitiesChanged(network, networkCapabilities);
                    LogUtil.d("WifiConnectReceiver_Main", "enter onCapabilitiesChanged");
                    if (networkCapabilities.hasCapability(16)) {
                        LogUtil.d("WifiConnectReceiver_Main", "currentNetwork is available");
                        WifiConnectReceiver.this.c(true);
                    } else {
                        LogUtil.d("WifiConnectReceiver_Main", "currentNetwork is unavailable");
                    }
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(boolean z) {
        if (!z) {
            LogUtil.c("WifiConnectReceiver_Main", "updateWifiMessageDeal isNetworkConnected is false");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.homehealth.syncwifi.WifiConnectReceiver.2
                @Override // java.lang.Runnable
                public void run() {
                    kft.b();
                }
            });
        }
    }
}
