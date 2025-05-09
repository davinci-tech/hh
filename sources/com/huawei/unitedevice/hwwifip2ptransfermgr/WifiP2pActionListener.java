package com.huawei.unitedevice.hwwifip2ptransfermgr;

import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;

/* loaded from: classes7.dex */
public interface WifiP2pActionListener extends WifiP2pManager.ChannelListener {
    void onConnectionInfoAvailable(WifiP2pInfo wifiP2pInfo);

    void onDisconnection();

    void wifiP2pEnabled(boolean z);
}
