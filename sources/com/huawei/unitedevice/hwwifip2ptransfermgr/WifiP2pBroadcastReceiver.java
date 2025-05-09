package com.huawei.unitedevice.hwwifip2ptransfermgr;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.BadParcelableException;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.core.view.PointerIconCompat;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import health.compact.a.LogUtil;

/* loaded from: classes7.dex */
public class WifiP2pBroadcastReceiver extends BroadcastReceiver {
    private WifiP2pManager b;
    private WifiP2pManager.Channel d;
    private WifiP2pActionListener e;

    public WifiP2pBroadcastReceiver(WifiP2pManager wifiP2pManager, WifiP2pManager.Channel channel, WifiP2pActionListener wifiP2pActionListener) {
        this.b = wifiP2pManager;
        this.d = channel;
        this.e = wifiP2pActionListener;
    }

    public static IntentFilter ekb_() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.p2p.STATE_CHANGED");
        intentFilter.addAction("android.net.wifi.p2p.PEERS_CHANGED");
        intentFilter.addAction("android.net.wifi.p2p.CONNECTION_STATE_CHANGE");
        intentFilter.addAction("android.net.wifi.p2p.THIS_DEVICE_CHANGED");
        intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        return intentFilter;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        char c;
        if (intent == null) {
            LogUtil.e("WifiP2pBroadcastReceiver", "intent is null");
            return;
        }
        LogUtil.c("WifiP2pBroadcastReceiver", "Action is: ", intent.getAction());
        if (TextUtils.isEmpty(intent.getAction())) {
            LogUtil.a("WifiP2pBroadcastReceiver", "action is empty.");
            return;
        }
        String action = intent.getAction();
        action.hashCode();
        int hashCode = action.hashCode();
        if (hashCode == -1772632330) {
            if (action.equals("android.net.wifi.p2p.CONNECTION_STATE_CHANGE")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != 941935981) {
            if (hashCode == 1695662461 && action.equals("android.net.wifi.p2p.STATE_CHANGED")) {
                c = 2;
            }
            c = 65535;
        } else {
            if (action.equals("com.huawei.bone.action.CONNECTION_STATE_CHANGED")) {
                c = 1;
            }
            c = 65535;
        }
        if (c == 0) {
            ekd_(intent);
            return;
        }
        if (c == 1) {
            eka_(intent);
        } else if (c == 2) {
            ekc_(intent);
        } else {
            LogUtil.a("WifiP2pBroadcastReceiver", "unknown action.");
        }
    }

    private void ekd_(Intent intent) {
        try {
            NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
            if (networkInfo == null) {
                LogUtil.a("WifiP2pBroadcastReceiver", "error, can not get network info.");
            } else if (networkInfo.isConnected()) {
                this.b.requestConnectionInfo(this.d, new WifiP2pManager.ConnectionInfoListener() { // from class: com.huawei.unitedevice.hwwifip2ptransfermgr.WifiP2pBroadcastReceiver.3
                    @Override // android.net.wifi.p2p.WifiP2pManager.ConnectionInfoListener
                    public void onConnectionInfoAvailable(WifiP2pInfo wifiP2pInfo) {
                        if (wifiP2pInfo != null) {
                            LogUtil.c("WifiP2pBroadcastReceiver", "get WiFip2p info success");
                        } else {
                            LogUtil.c("WifiP2pBroadcastReceiver", "WiFip2p info is null");
                        }
                        WifiP2pBroadcastReceiver.this.e.onConnectionInfoAvailable(wifiP2pInfo);
                    }
                });
                LogUtil.c("WifiP2pBroadcastReceiver", "Connected P2P");
            } else {
                LogUtil.c("WifiP2pBroadcastReceiver", "Disconnect P2P");
            }
        } catch (BadParcelableException unused) {
            LogUtil.e("WifiP2pBroadcastReceiver", "android.net.wifi.p2p.CONNECTION_STATE_CHANGE", " exception, no care this.");
        }
    }

    private void ekc_(Intent intent) {
        int intExtra = intent.getIntExtra("wifi_p2p_state", -1);
        if (intExtra == 2) {
            this.e.wifiP2pEnabled(true);
        }
        if (intExtra == 1) {
            ThreadPoolManager.d().d("WifiP2pBroadcastReceiver", new Runnable() { // from class: com.huawei.unitedevice.hwwifip2ptransfermgr.WifiP2pBroadcastReceiver.2
                @Override // java.lang.Runnable
                public void run() {
                    if (HwWifiP2pTransferManager.d().s()) {
                        LogUtil.c("WifiP2pBroadcastReceiver", "user close wifi button.");
                        if (HwWifiP2pTransferManager.d().n() == 0) {
                            HwWifiP2pTransferManager.d().d(PointerIconCompat.TYPE_GRAB, "ready pair wifi, but button close by user");
                        } else {
                            HwWifiP2pTransferManager.d().d(1022, "wifi button close by user");
                        }
                        HwWifiP2pTransferManager.d().e();
                    }
                }
            });
        }
    }

    private void eka_(Intent intent) {
        Parcelable parcelable;
        try {
            parcelable = intent.getParcelableExtra("deviceinfo");
        } catch (BadParcelableException unused) {
            LogUtil.e("WifiP2pBroadcastReceiver", "fuzzy test exception, no care this.");
            parcelable = null;
        }
        if (!(parcelable instanceof DeviceInfo)) {
            LogUtil.c("WifiP2pBroadcastReceiver", "device info is wrong, please check.");
        } else {
            HwWifiP2pTransferManager.d().d((DeviceInfo) parcelable);
        }
    }
}
