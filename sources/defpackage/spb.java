package defpackage;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pGroup;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Looper;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hiai.awareness.client.AwarenessRequest;
import com.huawei.unitedevice.hwwifip2ptransfermgr.HwWifiP2pTransferManager;
import com.huawei.unitedevice.hwwifip2ptransfermgr.WifiP2pActionListener;
import com.huawei.unitedevice.hwwifip2ptransfermgr.WifiP2pBroadcastReceiver;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LogUtil;

/* loaded from: classes7.dex */
public class spb {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f17189a = new Object();
    private static volatile spb b;
    private Context c;
    private HwWifiP2pTransferManager d;
    private WifiP2pManager.Channel e;
    private String f;
    private WifiManager g;
    private WifiP2pBroadcastReceiver i;
    private String j;
    private WifiP2pManager o;
    private volatile int h = 0;
    private WifiP2pActionListener n = new WifiP2pActionListener() { // from class: spb.4
        @Override // com.huawei.unitedevice.hwwifip2ptransfermgr.WifiP2pActionListener
        public void wifiP2pEnabled(boolean z) {
            if (z) {
                spb.this.f = spe.b();
                spb.this.j = spe.c();
                LogUtil.c("WifiP2pGo", "wifiP2pEnabled mSsid is: ", spb.this.f, "wifiP2pEnabled mPassphrase is: ", spb.this.j);
                spb.this.a(1);
                return;
            }
            LogUtil.c("WifiP2pGo", "wifiP2p is not enabled");
            spb.this.d.c(false);
        }

        @Override // com.huawei.unitedevice.hwwifip2ptransfermgr.WifiP2pActionListener
        public void onConnectionInfoAvailable(WifiP2pInfo wifiP2pInfo) {
            LogUtil.c("WifiP2pGo", "onConnectionInfoAvailable");
            if (wifiP2pInfo == null || wifiP2pInfo.groupOwnerAddress == null) {
                return;
            }
            String hostAddress = wifiP2pInfo.groupOwnerAddress.getHostAddress();
            if (hostAddress != null) {
                LogUtil.c("WifiP2pGo", "onConnectionInfoAvailable serverSocketIp is: ", hostAddress);
                spb.this.d.b(hostAddress);
            } else {
                LogUtil.c("WifiP2pGo", "onConnectionInfoAvailable serverSocketIp is null");
                spb.this.d.b("");
            }
        }

        @Override // com.huawei.unitedevice.hwwifip2ptransfermgr.WifiP2pActionListener
        public void onDisconnection() {
            LogUtil.a("WifiP2pGo", "onDisconnection enter.");
        }

        @Override // android.net.wifi.p2p.WifiP2pManager.ChannelListener
        public void onChannelDisconnected() {
            blz.a("WifiP2pStatusKey", "0");
            LogUtil.c("WifiP2pGo", "onChannelDisconnected enter.");
            spb.this.c();
        }
    };

    protected spb(Context context) {
        this.c = context;
    }

    public static spb e(Context context) {
        spb spbVar;
        synchronized (f17189a) {
            if (b == null) {
                b = new spb(context);
            }
            spbVar = b;
        }
        return spbVar;
    }

    public void d() {
        HwWifiP2pTransferManager d = HwWifiP2pTransferManager.d();
        this.d = d;
        d.u();
        Object systemService = this.d.c().getSystemService("wifip2p");
        if (systemService instanceof WifiP2pManager) {
            LogUtil.c("WifiP2pGo", "initWifiP2p enter.");
            WifiP2pManager wifiP2pManager = (WifiP2pManager) systemService;
            this.o = wifiP2pManager;
            this.e = wifiP2pManager.initialize(this.d.c(), Looper.getMainLooper(), null);
            this.i = new WifiP2pBroadcastReceiver(this.o, this.e, this.n);
            BroadcastManagerUtil.bFB_(BaseApplication.e(), this.i, WifiP2pBroadcastReceiver.ekb_());
            return;
        }
        LogUtil.a("WifiP2pGo", "WifiP2pManager is null, device not support.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final int i) {
        WifiInfo connectionInfo;
        WifiP2pManager.Channel channel = this.e;
        HwWifiP2pTransferManager hwWifiP2pTransferManager = this.d;
        if (hwWifiP2pTransferManager == null || channel == null) {
            LogUtil.c("WifiP2pGo", "channel is null, or mHwWifiP2pTransferManager is null.");
            return;
        }
        if (hwWifiP2pTransferManager.o()) {
            iyv.c("WifiP2PTransfer", "Failed to create wifip2p GO due to hotspot open.");
            LogUtil.c("WifiP2pGo", "hotSpot open, not create group.");
            return;
        }
        int i2 = 0;
        if (i == 1) {
            this.h = 0;
        }
        WifiP2pConfig.Builder builder = new WifiP2pConfig.Builder();
        builder.setNetworkName(this.f);
        builder.setPassphrase(this.j);
        if (this.c.getSystemService("wifi") instanceof WifiManager) {
            this.g = (WifiManager) this.c.getSystemService("wifi");
        }
        WifiManager wifiManager = this.g;
        if (wifiManager != null && (connectionInfo = wifiManager.getConnectionInfo()) != null) {
            i2 = connectionInfo.getFrequency();
        }
        LogUtil.a("WifiP2pGo", "createGroup frequency: ", Integer.valueOf(i2));
        if (i2 > 2400 && i2 < 2500) {
            builder.setGroupOperatingFrequency(i2);
        } else {
            builder.setGroupOperatingBand(1);
        }
        this.o.createGroup(channel, builder.build(), new WifiP2pManager.ActionListener() { // from class: spb.2
            @Override // android.net.wifi.p2p.WifiP2pManager.ActionListener
            public void onSuccess() {
                spb.this.g();
            }

            @Override // android.net.wifi.p2p.WifiP2pManager.ActionListener
            public void onFailure(int i3) {
                spb.this.e(i, i3);
            }
        });
    }

    public void b() {
        LogUtil.c("WifiP2pGo", "noCreateGroup");
        WifiP2pManager.Channel channel = this.e;
        if (channel != null) {
            channel.close();
        }
        if (this.i != null) {
            try {
                BaseApplication.e().unregisterReceiver(this.i);
            } catch (IllegalArgumentException e) {
                LogUtil.e("WifiP2pGo", "noCreateGroup : ", ExceptionUtils.d(e));
            }
        }
        this.e = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        try {
            Thread.sleep(50L);
        } catch (InterruptedException unused) {
            LogUtil.a("WifiP2pGo", "InterruptedException");
        }
        WifiP2pManager.Channel channel = this.e;
        if (channel == null) {
            LogUtil.a("WifiP2pGo", "channel is null, please check");
        } else {
            this.o.requestGroupInfo(channel, new WifiP2pManager.GroupInfoListener() { // from class: spb.3
                @Override // android.net.wifi.p2p.WifiP2pManager.GroupInfoListener
                public void onGroupInfoAvailable(WifiP2pGroup wifiP2pGroup) {
                    int i;
                    if (wifiP2pGroup != null) {
                        i = wifiP2pGroup.getFrequency();
                        LogUtil.c("WifiP2pGo", "wifiP2pGroup.getFrequency:", Integer.valueOf(wifiP2pGroup.getFrequency()));
                    } else {
                        i = 0;
                    }
                    if (i > 2500) {
                        spb.this.d.c(false);
                        return;
                    }
                    LogUtil.c("WifiP2pGo", "createGroup onSuccess");
                    spb.this.d.c(i);
                    spb.this.d.a(spb.this.f);
                    spb.this.d.e(spb.this.j);
                    spb.this.d.c(true);
                    blz.a("WifiP2pStatusKey", "1");
                }
            });
        }
    }

    private boolean j() {
        LogUtil.c("WifiP2pGo", "permission : ", Integer.valueOf(BaseApplication.e().checkSelfPermission("android.permission.ACCESS_BACKGROUND_LOCATION")));
        return BaseApplication.e().checkSelfPermission("android.permission.ACCESS_BACKGROUND_LOCATION") == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i, int i2) {
        LogUtil.a("WifiP2pGo", "onFailure : ", Integer.valueOf(i2));
        if (i2 == 0) {
            if (!j()) {
                b();
                LogUtil.c("WifiP2pGo", "no permission.");
                iyv.c("WifiP2PTransfer", "No sufficient permission for create wifip2p GO.");
                snu.e().notification(snu.e().noCloudVersion() ? 4 : 5);
                this.d.c(false);
                return;
            }
            LogUtil.c("WifiP2pGo", "check app, has background location permission, no permission error, try again.");
        }
        if (i2 != 1 && i2 != 2) {
            if (i == 1) {
                e();
                return;
            }
            return;
        }
        String d = blz.d("WifiP2pStatusKey", "");
        LogUtil.c("WifiP2pGo", "wifiStatus : ", d);
        if (TextUtils.isEmpty(d) || "1".equals(d)) {
            e();
        } else {
            b();
            this.d.c(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (this.h == 1) {
            LogUtil.a("WifiP2pGo", "wifi busy, app system remove wifi and try again. Do not deal this disconnect");
            this.h = 0;
        } else {
            if (this.d == null) {
                return;
            }
            LogUtil.c("WifiP2pGo", AwarenessRequest.MessageType.DISCONNECT);
            this.d.d(1015, "wifi change, may user close the wifi, please check.");
            this.d.e();
        }
    }

    public void a() {
        blz.a("WifiP2pStatusKey", "0");
        LogUtil.c("WifiP2pGo", "disconnectWifi enter.");
        HwWifiP2pTransferManager hwWifiP2pTransferManager = this.d;
        if (hwWifiP2pTransferManager == null) {
            return;
        }
        if (this.i != null) {
            try {
                hwWifiP2pTransferManager.c().unregisterReceiver(this.i);
                LogUtil.c("WifiP2pGo", "unregisterReceiver success.");
            } catch (IllegalArgumentException e) {
                LogUtil.e("WifiP2pGo", "disconnectWifi broadcast : ", bll.a(e));
            }
        }
        this.d.e(false);
        this.d.c(false);
        WifiP2pManager.Channel channel = this.e;
        if (channel != null) {
            this.o.removeGroup(channel, new WifiP2pManager.ActionListener() { // from class: spb.1
                @Override // android.net.wifi.p2p.WifiP2pManager.ActionListener
                public void onFailure(int i) {
                    LogUtil.c("WifiP2pGo", "removeGroup onFailure:", Integer.valueOf(i));
                }

                @Override // android.net.wifi.p2p.WifiP2pManager.ActionListener
                public void onSuccess() {
                    LogUtil.c("WifiP2pGo", "removeGroup onSuccess");
                }
            });
            this.o.cancelConnect(channel, null);
            this.o.clearLocalServices(channel, null);
            this.o.clearServiceRequests(channel, null);
            try {
                channel.close();
            } catch (NullPointerException unused) {
                LogUtil.e("WifiP2pGo", "system throw NullPointerException.");
            }
        }
        LogUtil.c("WifiP2pGo", "wifi manager clear all.");
    }

    private void e() {
        if (this.d == null) {
            return;
        }
        LogUtil.c("WifiP2pGo", "groupBusyAndTryAgain enter");
        WifiP2pManager.Channel channel = this.e;
        if (channel != null) {
            this.o.removeGroup(channel, new WifiP2pManager.ActionListener() { // from class: spb.5
                @Override // android.net.wifi.p2p.WifiP2pManager.ActionListener
                public void onFailure(int i) {
                    LogUtil.c("WifiP2pGo", "groupBusyAndTryAgain onFailure:", Integer.valueOf(i));
                    spb.this.d.c(false);
                }

                @Override // android.net.wifi.p2p.WifiP2pManager.ActionListener
                public void onSuccess() {
                    LogUtil.c("WifiP2pGo", "groupBusyAndTryAgain onSuccess");
                    spb.this.a(2);
                }
            });
        }
    }
}
