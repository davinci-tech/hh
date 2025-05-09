package com.huawei.hwdevice.phoneprocess.mgr.btproxy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.haf.common.utils.NetworkUtil;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.mgr.btproxy.BtProxyNetworkChangeReceiver;
import com.huawei.hwdevice.phoneprocess.mgr.btproxy.callback.BtProxySleepCallback;
import com.huawei.indoorequip.datastruct.MachineControlPointResponse;
import defpackage.cvx;
import defpackage.cwi;
import defpackage.jue;
import defpackage.jum;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.util.LogUtil;

/* loaded from: classes5.dex */
public class BtProxyNetworkChangeReceiver extends BroadcastReceiver {
    private static final Object b = new Object();
    private static volatile BtProxyNetworkChangeReceiver e;

    /* renamed from: a, reason: collision with root package name */
    private ConnectivityManager.NetworkCallback f6332a;
    private int c;
    private int d;
    private int g;
    private int i;
    private Context j = BaseApplication.getContext();
    private DeviceInfo f = null;
    private boolean l = false;
    private boolean n = false;
    private jum h = jum.a();

    public void e(DeviceInfo deviceInfo) {
        this.f = deviceInfo;
        this.l = jue.b().a(deviceInfo);
        e(d(deviceInfo));
        LogUtil.d("BtProxyNetworkChangeReceiver", "initNetworkRegister mIsSupportSleepWithoutBtProxy: ", Boolean.valueOf(this.l));
        jue.b().a(this.f.getDeviceIdentify(), new BtProxySleepCallback() { // from class: juf
            @Override // com.huawei.hwdevice.phoneprocess.mgr.btproxy.callback.BtProxySleepCallback
            public final void onSleepStatusCallback(int i) {
                BtProxyNetworkChangeReceiver.this.a(i);
            }
        });
        g();
        k();
        try {
            NetworkUtil.xJ_(this.f6332a);
        } catch (RuntimeException unused) {
            LogUtil.e("BtProxyNetworkChangeReceiver", "NetworkUtil registerNetworkCallback exception");
        }
        a(NetworkUtil.j());
        LogUtil.d("BtProxyNetworkChangeReceiver", "NetworkUtil registerNetworkCallback is success");
    }

    public /* synthetic */ void a(int i) {
        LogUtil.d("BtProxyNetworkChangeReceiver", "registerSleepStatusListen callback sleepStatus: ", Integer.valueOf(i));
        if (i == 0) {
            h();
        }
    }

    public static BtProxyNetworkChangeReceiver c() {
        BtProxyNetworkChangeReceiver btProxyNetworkChangeReceiver;
        synchronized (b) {
            LogUtil.d("BtProxyNetworkChangeReceiver", "getInstance()");
            if (e == null) {
                e = new BtProxyNetworkChangeReceiver();
            }
            btProxyNetworkChangeReceiver = e;
        }
        return btProxyNetworkChangeReceiver;
    }

    private void k() {
        this.f6332a = new ConnectivityManager.NetworkCallback() { // from class: com.huawei.hwdevice.phoneprocess.mgr.btproxy.BtProxyNetworkChangeReceiver.3
            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onAvailable(Network network) {
                super.onAvailable(network);
                if (Build.VERSION.SDK_INT < 28) {
                    LogUtil.d("BtProxyNetworkChangeReceiver", "onAvailable sendNetworkAvailableToDevice");
                    BtProxyNetworkChangeReceiver.this.e();
                }
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onLost(Network network) {
                super.onLost(network);
                BtProxyNetworkChangeReceiver btProxyNetworkChangeReceiver = BtProxyNetworkChangeReceiver.this;
                NetworkInfo bJw_ = btProxyNetworkChangeReceiver.bJw_(btProxyNetworkChangeReceiver.j);
                if (bJw_ != null && bJw_.getType() == 7) {
                    LogUtil.d("BtProxyNetworkChangeReceiver", "onLost: currentNetwork is blueTooth");
                } else {
                    LogUtil.d("BtProxyNetworkChangeReceiver", "currentNetwork is unavailable is Lost");
                    BtProxyNetworkChangeReceiver.this.d();
                }
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
                super.onCapabilitiesChanged(network, networkCapabilities);
                LogUtil.d("BtProxyNetworkChangeReceiver", "enter onCapabilitiesChanged");
                BtProxyNetworkChangeReceiver.this.a(networkCapabilities.hasCapability(16));
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        if (z) {
            LogUtil.d("BtProxyNetworkChangeReceiver", "currentNetwork is available");
            e();
        } else {
            LogUtil.c("BtProxyNetworkChangeReceiver", "currentNetwork is unavailable");
            d();
        }
    }

    public void e() {
        if (!j()) {
            LogUtil.c("BtProxyNetworkChangeReceiver", "sendNetworkAvailableToDevice return");
            return;
        }
        this.i = i();
        String b2 = SharedPreferenceManager.b(this.j, Integer.toString(52), "before_network_type");
        String b3 = SharedPreferenceManager.b(this.j, Integer.toString(52), "before_network _status");
        if (TextUtils.isEmpty(b2) || TextUtils.isEmpty(b3)) {
            return;
        }
        this.d = CommonUtil.w(b2);
        int w = CommonUtil.w(b3);
        this.c = w;
        LogUtil.d("BtProxyNetworkChangeReceiver", "beforeNetwork status is:", Integer.valueOf(w), "beforeNetwork type is:", Integer.valueOf(this.d));
        int i = this.c;
        if (i == 3 && this.g == 3) {
            LogUtil.d("BtProxyNetworkChangeReceiver", "network is not connected and status is not changed");
            return;
        }
        if (i == 4 && this.g == 4 && this.d == this.i) {
            LogUtil.d("BtProxyNetworkChangeReceiver", "network status is connect and network type is not changed");
        } else {
            c(this.d, this.i);
            d(this.i);
        }
    }

    private boolean j() {
        if (!this.l) {
            return true;
        }
        if (jue.b().c(this.f.getDeviceIdentify()) != 0) {
            return false;
        }
        LogUtil.d("BtProxyNetworkChangeReceiver", "wake up or time is above nine or app is foreground");
        return true;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            LogUtil.c("BtProxyNetworkChangeReceiver", "intent is null");
            return;
        }
        String action = intent.getAction();
        LogUtil.d("BtProxyNetworkChangeReceiver", "receive action: ", action);
        if ("android.net.wifi.WIFI_STATE_CHANGED".equals(action) || "android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
            ConnectivityManager connectivityManager = context.getSystemService("connectivity") instanceof ConnectivityManager ? (ConnectivityManager) context.getSystemService("connectivity") : null;
            if (connectivityManager == null) {
                LogUtil.c("BtProxyNetworkChangeReceiver", "connectivityManager is null");
                return;
            }
            if (connectivityManager.getActiveNetworkInfo() == null) {
                LogUtil.c("BtProxyNetworkChangeReceiver", "network info is null");
                d();
                return;
            }
            if (!CommonUtil.aa(this.j)) {
                LogUtil.d("BtProxyNetworkChangeReceiver", "currentNetwork is unavailable");
                d();
                return;
            }
            this.i = i();
            String b2 = SharedPreferenceManager.b(this.j, Integer.toString(52), "before_network_type");
            if (TextUtils.isEmpty(b2)) {
                return;
            }
            int w = CommonUtil.w(b2);
            this.d = w;
            LogUtil.d("BtProxyNetworkChangeReceiver", "beforeNetwork is:", Integer.valueOf(w), "currentNetwork is:", Integer.valueOf(this.i));
            c(this.d, this.i);
            d(this.i);
        }
    }

    private void h() {
        LogUtil.d("BtProxyNetworkChangeReceiver", "out sleep. CommonUtil.isNetworkConnected: ", Boolean.valueOf(CommonUtil.aa(this.j)));
        if (!CommonUtil.aa(this.j)) {
            d();
        } else {
            e();
        }
    }

    public void d() {
        if (!j()) {
            LogUtil.c("BtProxyNetworkChangeReceiver", "sendNetworkUnavailableToDevice return");
            return;
        }
        String str = cvx.e(10) + cvx.e(1) + cvx.e(3);
        LogUtil.d("BtProxyNetworkChangeReceiver", str);
        byte[] a2 = cvx.a(str);
        n();
        d(a2);
    }

    private void c(int i, int i2) {
        if (!j()) {
            LogUtil.c("BtProxyNetworkChangeReceiver", "sendNetworkTypeToDevice return");
            return;
        }
        StringBuilder sb = new StringBuilder(16);
        String e2 = cvx.e(4);
        sb.append(cvx.e(10));
        sb.append(cvx.e(1));
        sb.append(e2);
        String e3 = cvx.e(i);
        sb.append(cvx.e(11));
        sb.append(cvx.e(1));
        sb.append(e3);
        String e4 = cvx.e(i2);
        sb.append(cvx.e(12));
        sb.append(cvx.e(1));
        sb.append(e4);
        byte[] a2 = cvx.a(sb.toString());
        LogUtil.d("BtProxyNetworkChangeReceiver", sb);
        d(a2);
    }

    private void d(int i) {
        SharedPreferenceManager.e(this.j, Integer.toString(52), "before_network _status", Integer.toString(4), new StorageParams());
        SharedPreferenceManager.e(this.j, Integer.toString(52), "before_network_type", Integer.toString(i), new StorageParams());
    }

    private void n() {
        this.g = 3;
        SharedPreferenceManager.e(this.j, Integer.toString(52), "before_network _status", Integer.toString(3), new StorageParams());
    }

    private int i() {
        this.g = 4;
        NetworkInfo bJw_ = bJw_(this.j);
        if (bJw_ == null) {
            LogUtil.c("BtProxyNetworkChangeReceiver", "get net work type error");
            return -1;
        }
        int type = bJw_.getType();
        int i = type == 7 ? 2 : type == 0 ? 0 : type == 1 ? 1 : 5;
        LogUtil.d("BtProxyNetworkChangeReceiver", "current networkType is ", Integer.valueOf(i));
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public NetworkInfo bJw_(Context context) {
        if (context.getSystemService("connectivity") instanceof ConnectivityManager) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager != null) {
                return connectivityManager.getActiveNetworkInfo();
            }
            LogUtil.c("BtProxyNetworkChangeReceiver", "get network info is error, connectivityManager is null");
        }
        return null;
    }

    private void d(byte[] bArr) {
        this.h.a(bArr);
    }

    public void f() {
        ConnectivityManager.NetworkCallback networkCallback = this.f6332a;
        if (networkCallback != null) {
            try {
                NetworkUtil.xK_(networkCallback);
                LogUtil.d("BtProxyNetworkChangeReceiver", "NetworkUtil unregisterNetworkCallback is success");
            } catch (IllegalArgumentException unused) {
                LogUtil.e("BtProxyNetworkChangeReceiver", "unregisterNetworkCallback is failed");
            }
        }
    }

    private void g() {
        LogUtil.d("BtProxyNetworkChangeReceiver", "initNetWorkStatusAndType enter.");
        SharedPreferenceManager.e(this.j, Integer.toString(52), "before_network _status", Integer.toString(-1), new StorageParams());
        SharedPreferenceManager.e(this.j, Integer.toString(52), "before_network_type", Integer.toString(-1), new StorageParams());
    }

    public static void a() {
        if (e == null) {
            LogUtil.c("BtProxyNetworkChangeReceiver", "current instance is null");
        } else {
            e = null;
        }
    }

    public boolean b() {
        return this.n;
    }

    public void e(boolean z) {
        this.n = z;
    }

    public boolean d(DeviceInfo deviceInfo) {
        return cwi.c(deviceInfo, MachineControlPointResponse.OP_CODE_EXTENSION_MULTI_DATA);
    }
}
