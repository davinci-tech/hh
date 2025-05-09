package defpackage;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.android.net.wifi.NfcWifiManagerEx;
import com.huawei.datatype.DeviceCommand;
import com.huawei.haf.common.utils.NetworkUtil;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.binder.ParserInterface;
import com.huawei.hwdevice.phoneprocess.mgr.hwwifisyncmgr.WifiConnectReceiver;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.Base64;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.HwEncryptUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.LocalBroadcast;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public class kfv implements ParserInterface {
    private static volatile kfv c;

    /* renamed from: a, reason: collision with root package name */
    private ConnectivityManager.NetworkCallback f14355a;
    private Context d;
    private WifiConnectReceiver i;
    private static final Object e = new Object();
    private static AtomicInteger b = new AtomicInteger(0);

    private kfv(Context context) {
        this.d = context;
    }

    public static kfv d() {
        kfv kfvVar;
        synchronized (e) {
            if (c == null) {
                c = new kfv(BaseApplication.getContext());
            }
            kfvVar = c;
        }
        return kfvVar;
    }

    public void c() {
        LogUtil.a("HwWifiSyncMgr", "enter syncWifiBySharedPreference()");
        i();
    }

    public void a() {
        LogUtil.a("HwWifiSyncMgr", "enter registerWifiConnectReceiver");
        synchronized (e) {
            if (b.get() != 0) {
                return;
            }
            try {
                KeyValDbManager.b(BaseApplication.getContext()).e("saved_support_sync_wifi", "1");
                this.i = WifiConnectReceiver.b();
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
                if (EnvironmentInfo.r()) {
                    h();
                    try {
                        NetworkUtil.xJ_(this.f14355a);
                        b(NetworkUtil.i());
                    } catch (SecurityException unused) {
                        LogUtil.b("HwWifiSyncMgr", "registerWifiConnectReceiver registerNetworkCallback exception");
                    }
                    LogUtil.a("HwWifiSyncMgr", "registerWifiConnectReceiver registerNetworkCallback is success");
                } else {
                    intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
                }
                BroadcastManagerUtil.bFC_(BaseApplication.getContext(), this.i, intentFilter, LocalBroadcast.c, null);
                b.incrementAndGet();
            } catch (IllegalArgumentException unused2) {
                LogUtil.b("HwWifiSyncMgr", "unRegisterWifiConnectReceiver is failed");
            }
        }
    }

    public void e() {
        LogUtil.a("HwWifiSyncMgr", "enter unRegisterWifiConnectReceiver");
        synchronized (e) {
            if (this.i != null && b.get() == 1) {
                try {
                    BaseApplication.getContext().unregisterReceiver(this.i);
                    if (this.f14355a != null && EnvironmentInfo.r()) {
                        NetworkUtil.xK_(this.f14355a);
                        this.f14355a = null;
                    }
                    this.i = null;
                    b.decrementAndGet();
                } catch (IllegalArgumentException unused) {
                    LogUtil.b("HwWifiSyncMgr", "unRegisterWifiConnectReceiver is failed");
                }
            }
        }
    }

    public boolean b() {
        if (NfcWifiManagerEx.getWpaSuppConfig((WifiManager) null) != null) {
            return true;
        }
        LogUtil.h("HwWifiSyncMgr", "isCouldGetWifiConfigurationInformation() wifi information is empty");
        return false;
    }

    private void c(byte[] bArr) {
        ReleaseLogUtil.e("R_HwWifiSyncMgr", "parseSyncWifiResult enter.");
        String d = cvx.d(bArr);
        if (TextUtils.isEmpty(d) || d.length() < 4) {
            LogUtil.h("HwWifiSyncMgr", "parseDeviceData data is error");
            return;
        }
        String substring = d.substring(0, 2);
        String substring2 = d.substring(2, 4);
        try {
            int parseInt = Integer.parseInt(substring, 16);
            int parseInt2 = Integer.parseInt(substring2, 16);
            if (parseInt != 1 || parseInt2 != 47) {
                LogUtil.h("HwWifiSyncMgr", "The phone received the wifi data is error.");
                return;
            }
        } catch (NumberFormatException unused) {
            LogUtil.b("HwWifiSyncMgr", "The phone received the wifi data parseInt has exception");
        }
        c(d.substring(4));
    }

    private void i() {
        if (!kft.b(this.d)) {
            LogUtil.a("HwWifiSyncMgr", "The phone no wifi connection");
        } else if (j()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: kfv.5
                @Override // java.lang.Runnable
                public void run() {
                    kfv.this.f();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        ReleaseLogUtil.e("R_HwWifiSyncMgr", "dealSynchronizedWifi enter.");
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, null, "HwWifiSyncMgr");
        if (deviceList.isEmpty()) {
            LogUtil.a("HwWifiSyncMgr", "No device connected. ");
            return;
        }
        List<kfs> g = g();
        LogUtil.a("HwWifiSyncMgr", "cachedWifiList size: ", Integer.valueOf(g.size()));
        for (DeviceInfo deviceInfo : deviceList) {
            Map<String, DeviceCapability> queryDeviceCapability = jsz.b(this.d).queryDeviceCapability(1, deviceInfo.getDeviceIdentify(), "HwWifiSyncMgr");
            if (queryDeviceCapability.containsKey(deviceInfo.getDeviceIdentify())) {
                DeviceCapability deviceCapability = queryDeviceCapability.get(deviceInfo.getDeviceIdentify());
                if (deviceCapability == null || !deviceCapability.isSupportSyncWifi()) {
                    LogUtil.a("HwWifiSyncMgr", "Cur device is not support sync wifi capability: ", deviceInfo.toString());
                } else {
                    Iterator<kfs> it = g.iterator();
                    while (it.hasNext()) {
                        d().d(deviceInfo.getDeviceIdentify(), it.next());
                    }
                }
            }
        }
    }

    private void a(String str, String str2, String str3, int i) {
        DeviceCommand e2 = e(d(str2, str3, i));
        e2.setmIdentify(str);
        jsz.b(this.d).sendDeviceData(e2);
    }

    private String d(String str, String str2, int i) {
        String c2 = cvx.c(str);
        String c3 = cvx.c(str2);
        String e2 = cvx.e(i);
        return d(1, c2) + d(2, c3) + d(3, e2);
    }

    private String d(int i, String str) {
        return cvx.e(i) + cvx.e(str.length() / 2) + str;
    }

    private DeviceCommand e(String str) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(1);
        deviceCommand.setCommandID(47);
        byte[] a2 = cvx.a(str);
        deviceCommand.setDataContent(a2);
        deviceCommand.setDataLen(a2.length);
        return deviceCommand;
    }

    private boolean j() {
        HiUserPreference userPreference = HiHealthManager.d(this.d).getUserPreference("sync_wifi_toggle_status");
        if (userPreference == null) {
            HiHealthManager.d(this.d).setUserPreference(new HiUserPreference("sync_wifi_toggle_status", "1"), true);
            userPreference = HiHealthManager.d(this.d).getUserPreference("sync_wifi_toggle_status");
        }
        if (userPreference == null) {
            LogUtil.h("HwWifiSyncMgr", "The phone sync wifi HiUserPreference is error");
            return false;
        }
        String value = userPreference.getValue();
        if (TextUtils.isEmpty(value)) {
            LogUtil.h("HwWifiSyncMgr", "The phone sync wifi toggle status is error");
            return false;
        }
        if (!"0".equals(value)) {
            return true;
        }
        LogUtil.a("HwWifiSyncMgr", "The phone sync wifi toggle is closed");
        return false;
    }

    private void d(String str, kfs kfsVar) {
        if (kfsVar == null) {
            LogUtil.h("HwWifiSyncMgr", "sendMessageEntity is null");
            return;
        }
        String c2 = kfsVar.c();
        int e2 = kfsVar.e();
        try {
            String str2 = new String(Base64.e(HwEncryptUtil.c(BaseApplication.getContext()).a(1, kfsVar.b())), StandardCharsets.UTF_8);
            LogUtil.a("HwWifiSyncMgr", "sendCachedWi******age p******d.len = " + str2.length());
            if (c2 == null) {
                LogUtil.h("HwWifiSyncMgr", "ssid is null");
            } else {
                LogUtil.a("HwWifiSyncMgr", "sendSsid:", CommonUtil.l(c2));
                a(str, c2, str2, e2);
            }
        } catch (GeneralSecurityException unused) {
            LogUtil.b("HwWifiSyncMgr", "data is not right");
        }
    }

    private void c(String str) {
        try {
            List<cwd> e2 = new cwl().a(str).e();
            if (e2 != null && !e2.isEmpty()) {
                if (Integer.parseInt(e2.get(0).c(), 16) == 100000) {
                    LogUtil.a("HwWifiSyncMgr", "The device received wifi data success");
                    return;
                } else {
                    LogUtil.a("HwWifiSyncMgr", "The device received wifi data fail");
                    return;
                }
            }
            LogUtil.h("HwWifiSyncMgr", "The device return tlvList data is error");
        } catch (cwg unused) {
            LogUtil.b("HwWifiSyncMgr", "parseDeviceData TlvException is happened");
        } catch (NumberFormatException unused2) {
            LogUtil.b("HwWifiSyncMgr", "parseDeviceData NumberFormatException is happened");
        }
    }

    @Override // com.huawei.hwdevice.phoneprocess.binder.ParserInterface
    public void getResult(DeviceInfo deviceInfo, byte[] bArr) {
        c(bArr);
    }

    private List<kfs> g() {
        try {
            List<kfs> list = (List) new Gson().fromJson(KeyValDbManager.b(BaseApplication.getContext()).e("cached_wifi_message"), new TypeToken<List<kfs>>() { // from class: kfv.2
            }.getType());
            if (list != null && list.size() >= 1) {
                LogUtil.a("HwWifiSyncMgr", "cachedList;", Integer.valueOf(list.size()));
                return list;
            }
            LogUtil.h("HwWifiSyncMgr", "cachedList is Empty");
            return new ArrayList();
        } catch (JsonSyntaxException unused) {
            LogUtil.b("HwWifiSyncMgr", "get cachedList jsonSyntaxException");
            return Arrays.asList(kft.e());
        } catch (NumberFormatException unused2) {
            LogUtil.b("HwWifiSyncMgr", "sendMessage NumberFormatException");
            return Arrays.asList(kft.e());
        }
    }

    private void h() {
        if (EnvironmentInfo.r()) {
            ConnectivityManager.NetworkCallback networkCallback = this.f14355a;
            if (networkCallback != null) {
                NetworkUtil.xK_(networkCallback);
                this.f14355a = null;
            }
            this.f14355a = new ConnectivityManager.NetworkCallback() { // from class: kfv.4
                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onAvailable(Network network) {
                    super.onAvailable(network);
                    kfv.this.b(true);
                }

                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onLost(Network network) {
                    super.onLost(network);
                }

                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
                    super.onCapabilitiesChanged(network, networkCapabilities);
                    LogUtil.a("HwWifiSyncMgr", "enter onCapabilitiesChanged");
                    if (networkCapabilities.hasCapability(16)) {
                        LogUtil.a("HwWifiSyncMgr", "currentNetwork is available");
                        kfv.this.b(true);
                    } else {
                        LogUtil.a("HwWifiSyncMgr", "currentNetwork is unavailable");
                    }
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        if (!z) {
            LogUtil.h("HwWifiSyncMgr", "updateWifiMessageDeal isNetworkConnected is false");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: kfv.1
                @Override // java.lang.Runnable
                public void run() {
                    kft.b();
                }
            });
        }
    }
}
