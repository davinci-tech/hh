package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.health.device.wifi.interfaces.CommBaseCallbackInterface;
import com.huawei.health.device.wifi.lib.handler.StaticHandler;
import com.huawei.hms.network.embedded.y;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.List;

/* loaded from: classes3.dex */
public class ctz {
    private static volatile ctz c;
    private static final Object e = new Object();

    /* renamed from: a, reason: collision with root package name */
    private String f11477a;
    private CommBaseCallbackInterface d;
    private d g;
    private int i;
    private Context j;
    private WifiManager o;
    private int f = -1;
    private int m = y.c;
    private boolean b = false;
    private BroadcastReceiver h = new BroadcastReceiver() { // from class: ctz.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                cpw.a(false, "WiFiConnectUtils", "mWiFiConnReceiver intent is null ");
                return;
            }
            cpw.a(false, "WiFiConnectUtils", "mWiFiConnReceiver action ", intent.getAction());
            if ("android.net.wifi.supplicant.STATE_CHANGE".equals(intent.getAction())) {
                int intExtra = intent.getIntExtra("supplicantError", -1);
                cpw.a(false, "WiFiConnectUtils", "mWiFiConnReceiver SUPPLICANT_STATE_CHANGED_ACTION. errValue: ", Integer.valueOf(intExtra));
                if (intExtra == 1) {
                    cpw.d(false, "WiFiConnectUtils", "mWiFiConnReceiver SUPPLICANT_STATE_CHANGED_ACTION");
                    ctz.this.g.sendEmptyMessage(105);
                    return;
                }
                return;
            }
            if ("android.net.wifi.STATE_CHANGE".equals(intent.getAction()) || "android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
                cpw.a(false, "WiFiConnectUtils", "mWiFiConnReceiver NETWORK_STATE_CHANGED_ACTION");
                if (networkInfo == null || !NetworkInfo.State.CONNECTED.equals(networkInfo.getState())) {
                    return;
                }
                ctz.this.g.sendEmptyMessageDelayed(104, 200L);
                return;
            }
            LogUtil.h("WiFiConnectUtils", "mWiFiConnReceiver receive some unidentified broadcasts action:", intent.getAction());
        }
    };

    private boolean c(int i) {
        return i == 58 || (i == 10 || i == 26);
    }

    private ctz(Context context) {
        this.g = null;
        this.j = context.getApplicationContext();
        this.g = new d(this);
        this.o = (WifiManager) this.j.getSystemService("wifi");
    }

    static class d extends StaticHandler<ctz> {
        d(ctz ctzVar) {
            super(ctzVar);
        }

        @Override // com.huawei.health.device.wifi.lib.handler.StaticHandler
        /* renamed from: Ms_, reason: merged with bridge method [inline-methods] */
        public void handleMessage(ctz ctzVar, Message message) {
            if (ctzVar == null || message == null) {
                return;
            }
            switch (message.what) {
                case 101:
                case 103:
                case 105:
                    ctzVar.g();
                    ctzVar.a(message.what);
                    break;
                case 102:
                    ctzVar.a(message.what);
                    break;
                case 104:
                    ctzVar.a();
                    break;
                default:
                    LogUtil.h("WiFiConnectUtils", "TimerHandler what:", Integer.valueOf(message.what));
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        int i = this.i;
        if (i > -1) {
            LogUtil.a("WiFiConnectUtils", "removeNetWork networkId:", Integer.valueOf(this.i), "|isRemoveSuccess:", Boolean.valueOf(this.o.removeNetwork(i)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        CommBaseCallbackInterface commBaseCallbackInterface = this.d;
        if (commBaseCallbackInterface != null) {
            commBaseCallbackInterface.onResult(i, null, null);
        }
        d();
    }

    private void e() {
        cpw.a(false, "initData isRegisterReceiver ,", Boolean.valueOf(this.b));
        if (this.b) {
            return;
        }
        cpw.a(false, "initData Register wifi Receiver", new Object[0]);
        IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        intentFilter.addAction("android.net.wifi.supplicant.STATE_CHANGE");
        this.j.registerReceiver(this.h, intentFilter);
        this.b = true;
    }

    public void d() {
        this.m = y.c;
        this.f11477a = null;
        this.f = -1;
        if (this.b) {
            this.j.unregisterReceiver(this.h);
            this.b = false;
        }
        d dVar = this.g;
        if (dVar != null) {
            dVar.removeCallbacksAndMessages(null);
        }
    }

    public static ctz c(Context context) {
        ctz ctzVar;
        synchronized (e) {
            if (context != null) {
                if (c == null) {
                    c = new ctz(context.getApplicationContext());
                }
            }
            ctzVar = c;
        }
        return ctzVar;
    }

    public void Mo_(ScanResult scanResult, String str, boolean z, CommBaseCallbackInterface commBaseCallbackInterface) {
        this.d = commBaseCallbackInterface;
        a(scanResult.SSID, str, Ml_(scanResult), z);
    }

    public void c(String str, String str2, int i, boolean z, CommBaseCallbackInterface commBaseCallbackInterface) {
        this.d = commBaseCallbackInterface;
        a(str, str2, i, z);
    }

    private boolean a(String str, String str2, int i, boolean z) {
        if (!this.o.isWifiEnabled()) {
            a(103);
        }
        e();
        WifiConfiguration Mq_ = Mq_(str, i);
        if (Mq_ == null) {
            this.i = this.o.addNetwork(Mp_(i, str, str2, new WifiConfiguration()));
        } else {
            int i2 = Mq_.networkId;
            if (z) {
                int updateNetwork = this.o.updateNetwork(Mp_(i, str, str2, Mq_));
                this.i = updateNetwork;
                if (updateNetwork <= -1) {
                    LogUtil.a("WiFiConnectUtils", "modify wifi config networkId error, use old network connect");
                    this.i = i2;
                }
            } else {
                this.i = Mq_.networkId;
            }
        }
        LogUtil.a("WiFiConnectUtils", "connect networkId is:", Integer.valueOf(this.i));
        this.g.sendEmptyMessageDelayed(101, this.m);
        this.f11477a = str;
        int i3 = this.i;
        this.f = i3;
        if (i3 >= 0) {
            boolean enableNetwork = this.o.enableNetwork(i3, true);
            cpw.a(false, "WiFiConnectUtils", "connect enableNetwork is:", Boolean.valueOf(enableNetwork));
            if (enableNetwork) {
                cpw.a(false, "WiFiConnectUtils", "connect saveConfiguration:", Boolean.valueOf(this.o.saveConfiguration()), "--reconnect:", Boolean.valueOf(this.o.reconnect()));
                return true;
            }
        }
        return false;
    }

    public void c() {
        WifiInfo connectionInfo;
        if (!this.o.isWifiEnabled() || (connectionInfo = this.o.getConnectionInfo()) == null) {
            return;
        }
        int networkId = connectionInfo.getNetworkId();
        this.o.disableNetwork(networkId);
        cpw.a(false, "WiFiConnectUtils", " disableCurrentNetwork current netid is: ", Integer.valueOf(networkId));
    }

    public boolean b() {
        if (this.f <= 0) {
            return false;
        }
        c();
        cpw.a(false, "WiFiConnectUtils", "reConnectLastConfig mLastNetid is:", Integer.valueOf(this.f));
        this.o.enableNetwork(this.f, true);
        return this.o.reassociate();
    }

    public WifiConfiguration Mp_(int i, String str, String str2, WifiConfiguration wifiConfiguration) {
        WifiConfiguration Mn_ = Mn_(str, wifiConfiguration);
        if (i == 0) {
            Mn_.wepKeys[0] = "\"\"";
            Mn_.allowedKeyManagement.set(0);
            Mn_.wepTxKeyIndex = 0;
            return Mn_;
        }
        if (i == 1) {
            return Mk_(str2, Mn_);
        }
        if (i != 2) {
            if (i != 3) {
                return null;
            }
            Mn_.allowedKeyManagement.set(2);
            Mn_.allowedKeyManagement.set(3);
            return Mn_;
        }
        Mn_.allowedAuthAlgorithms.set(0);
        Mn_.allowedGroupCiphers.set(2);
        Mn_.allowedPairwiseCiphers.set(1);
        Mn_.allowedGroupCiphers.set(3);
        Mn_.allowedPairwiseCiphers.set(2);
        Mn_.status = 2;
        Mn_.allowedKeyManagement.set(1);
        if (str2.length() == 0) {
            return Mn_;
        }
        if (str2.matches("[0-9A-Fa-f]{64}")) {
            Mn_.preSharedKey = str2;
            return Mn_;
        }
        Mn_.preSharedKey = "\"" + str2 + '\"';
        return Mn_;
    }

    private WifiConfiguration Mk_(String str, WifiConfiguration wifiConfiguration) {
        wifiConfiguration.wepKeys[0] = "\"" + str + "\"";
        wifiConfiguration.allowedGroupCiphers.set(3);
        wifiConfiguration.allowedGroupCiphers.set(2);
        wifiConfiguration.allowedGroupCiphers.set(0);
        wifiConfiguration.allowedGroupCiphers.set(1);
        wifiConfiguration.allowedKeyManagement.set(0);
        wifiConfiguration.allowedAuthAlgorithms.set(0);
        wifiConfiguration.allowedAuthAlgorithms.set(1);
        int length = str.length();
        if (str.matches("[0-9A-Fa-f]*")) {
            if (c(length)) {
                wifiConfiguration.wepKeys[0] = str;
            } else {
                wifiConfiguration.wepKeys[0] = "\"" + str + '\"';
            }
        } else {
            wifiConfiguration.wepKeys[0] = "\"" + str + '\"';
        }
        wifiConfiguration.wepTxKeyIndex = 0;
        return wifiConfiguration;
    }

    private WifiConfiguration Mn_(String str, WifiConfiguration wifiConfiguration) {
        wifiConfiguration.allowedAuthAlgorithms.clear();
        wifiConfiguration.allowedGroupCiphers.clear();
        wifiConfiguration.allowedKeyManagement.clear();
        wifiConfiguration.allowedPairwiseCiphers.clear();
        wifiConfiguration.allowedProtocols.clear();
        wifiConfiguration.SSID = b(str);
        wifiConfiguration.hiddenSSID = true;
        return wifiConfiguration;
    }

    public String b(String str) {
        return "\"" + str + "\"";
    }

    public WifiConfiguration Mq_(String str, int i) {
        List<WifiConfiguration> Mu_ = cub.Mu_(this.o);
        if (koq.b(Mu_)) {
            cpw.a(false, "WiFiConnectUtils", "configs == null");
            return null;
        }
        for (WifiConfiguration wifiConfiguration : Mu_) {
            cpw.a(false, "WiFiConnectUtils", "isExsitsConfiguration =>", wifiConfiguration.SSID);
            if (wifiConfiguration.SSID.equals("\"" + str + "\"") && i == Mm_(wifiConfiguration)) {
                return wifiConfiguration;
            }
        }
        return null;
    }

    public static int Mm_(WifiConfiguration wifiConfiguration) {
        if (wifiConfiguration == null) {
            cpw.e(false, "WiFiConnectUtils", "config == null");
            return 0;
        }
        if (wifiConfiguration.allowedKeyManagement.get(1)) {
            return 2;
        }
        if (wifiConfiguration.allowedKeyManagement.get(2) || wifiConfiguration.allowedKeyManagement.get(3)) {
            return 3;
        }
        return wifiConfiguration.wepKeys[0] != null ? 1 : 0;
    }

    public static int Ml_(ScanResult scanResult) {
        if (scanResult == null) {
            cpw.e(false, "WiFiConnectUtils", "scanResult == null");
            return 0;
        }
        if (scanResult.capabilities.contains("WEP") || scanResult.capabilities.contains("wep")) {
            return 1;
        }
        if (scanResult.capabilities.contains("PSK")) {
            return 2;
        }
        if (scanResult.capabilities.contains("SAE")) {
            return 7;
        }
        return scanResult.capabilities.contains("EAP") ? 3 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        String c2 = cub.c(this.j);
        if (!TextUtils.isEmpty(c2)) {
            String e2 = cub.e(c2);
            cpw.a(true, "WiFiConnectUtils", "compareConnect current ssid ", e2, "|mConnectSsid = ", this.f11477a);
            String str = this.f11477a;
            if (str != null && str.equals(e2)) {
                if (cub.g(this.j)) {
                    cpw.a(false, "WiFiConnectUtils", "compareConnect connect success...");
                    this.g.sendEmptyMessage(102);
                    return;
                }
                return;
            }
            b();
            return;
        }
        cpw.a(false, "WiFiConnectUtils", "compareConnect not connect wifi...");
    }

    public void a(Context context, String str, int i) {
        if (context == null) {
            LogUtil.h("WiFiConnectUtils", "clearConfig() context is null");
            return;
        }
        WifiConfiguration Mq_ = Mq_(str, i);
        if (Mq_ != null) {
            Object systemService = context.getSystemService("wifi");
            if (systemService instanceof WifiManager) {
                WifiManager wifiManager = (WifiManager) systemService;
                if (Mq_.networkId > 0) {
                    wifiManager.disconnect();
                    wifiManager.removeNetwork(Mq_.networkId);
                    wifiManager.saveConfiguration();
                } else {
                    wifiManager.disconnect();
                    Mr_(wifiManager, Mq_.SSID);
                }
            }
        }
    }

    public void Mr_(WifiManager wifiManager, String str) {
        if (wifiManager == null) {
            LogUtil.h("WiFiConnectUtils", "removeWifiBySsid wifiManager is null");
            return;
        }
        List<WifiConfiguration> Mu_ = cub.Mu_(wifiManager);
        if (koq.b(Mu_)) {
            LogUtil.b("WiFiConnectUtils", "removeWifiBySsid wifiConfigs is null");
            return;
        }
        for (WifiConfiguration wifiConfiguration : Mu_) {
            if (wifiConfiguration.SSID.equals(str)) {
                wifiManager.removeNetwork(wifiConfiguration.networkId);
                wifiManager.saveConfiguration();
            }
        }
    }
}
