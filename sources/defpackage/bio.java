package defpackage;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.devicesdk.entity.ExternalDeviceCapability;
import com.huawei.haf.store.SharedStoreManager;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes3.dex */
public class bio {
    private static final Object e = new Object();
    private static AtomicBoolean b = new AtomicBoolean(false);
    private static Set<String> c = new HashSet();

    /* renamed from: a, reason: collision with root package name */
    private static boolean f388a = false;

    private static void e(String str, DeviceInfo deviceInfo) {
        e(str, str + "_deviceInfo", new Gson().toJson(deviceInfo));
        LogUtil.c("UdsMmvkUtil", "insertDeviceInfo deviceName:", deviceInfo.getDeviceName());
    }

    public static void e(boolean z) {
        f388a = z;
    }

    public static void a(String str, DeviceInfo deviceInfo) {
        if (c == null || TextUtils.isEmpty(str) || deviceInfo == null) {
            LogUtil.e("UdsMmvkUtil", "sIdentitySet or identity or deviceInfo is null, updateDeviceInfo failed.");
            return;
        }
        synchronized (e) {
            if (!i(str)) {
                e(str, deviceInfo);
            } else {
                c(str + "_deviceInfo", new Gson().toJson(deviceInfo));
                LogUtil.c("UdsMmvkUtil", "update DeviceInfo.");
            }
        }
        LogUtil.c("UdsMmvkUtil", "updateDeviceInfo deviceName: ", deviceInfo.getDeviceName());
    }

    private static void d(String str, bix bixVar) {
        e(str, str + "_deviceStatus", new Gson().toJson(bixVar));
        LogUtil.c("UdsMmvkUtil", "insert devicestatus.");
    }

    public static void c(String str, bix bixVar) {
        if (c == null || TextUtils.isEmpty(str) || bixVar == null) {
            LogUtil.e("UdsMmvkUtil", "sIdentitySet or identity or deviceInfo is null, updateDeviceStatusData failed.");
            return;
        }
        synchronized (e) {
            if (!i(str)) {
                d(str, bixVar);
            } else {
                c(str + "_deviceStatus", new Gson().toJson(bixVar));
                LogUtil.c("UdsMmvkUtil", "update devicestatus");
            }
        }
    }

    private static void b(String str, ExternalDeviceCapability externalDeviceCapability) {
        e(str, str + "_deviceCapability", new Gson().toJson(externalDeviceCapability));
        LogUtil.c("UdsMmvkUtil", "insert ExternalDeviceCapability.");
    }

    public static void c(String str, ExternalDeviceCapability externalDeviceCapability) {
        if (c == null || TextUtils.isEmpty(str) || externalDeviceCapability == null) {
            LogUtil.e("UdsMmvkUtil", "sIdentitySet or identity or deviceInfo is null, updateExternalDeviceCapability failed.");
            return;
        }
        synchronized (e) {
            if (!i(str)) {
                b(str, externalDeviceCapability);
            } else {
                c(str + "_deviceCapability", new Gson().toJson(externalDeviceCapability));
                LogUtil.c("UdsMmvkUtil", "update ExternalDeviceCapability.");
            }
        }
    }

    public static void b(String str) {
        if (c == null || TextUtils.isEmpty(str)) {
            LogUtil.e("UdsMmvkUtil", "deleteStorageData sIdentitySet or identity is null");
            return;
        }
        LogUtil.c("UdsMmvkUtil", "delete identitty.");
        synchronized (e) {
            d(str);
            c.remove(str);
            if (c.isEmpty()) {
                c("identitys");
            } else {
                c("identitys", new Gson().toJson(c));
            }
        }
    }

    private static boolean i(String str) {
        Set<String> set;
        if (!b.get() && (set = c) != null && set.isEmpty()) {
            a();
        }
        Set<String> set2 = c;
        if (set2 == null) {
            LogUtil.e("UdsMmvkUtil", "database is not create");
            return false;
        }
        return set2.contains(str);
    }

    private static void a() {
        try {
            Set<String> set = (Set) new Gson().fromJson(e("identitys"), HashSet.class);
            c = set;
            if (set != null) {
                ReleaseLogUtil.b("DEVMGR_UdsMmvkUtil", "loadedIdentitySet sIdentitySet size: ", Integer.valueOf(set.size()));
            }
        } catch (JsonSyntaxException unused) {
            LogUtil.e("UdsMmvkUtil", "handleCursorData sIdentitySet fromJson error0");
        }
    }

    public static boolean b() {
        Set set;
        String e2;
        try {
            e2 = e("identitys");
        } catch (Exception unused) {
            LogUtil.e("UdsMmvkUtil", "isHasDevice sIdentitySet fromJson error");
            set = null;
        }
        if (!TextUtils.isEmpty(e2) && !"[]".equals(e2)) {
            set = (Set) new Gson().fromJson(e("identitys"), HashSet.class);
            return (set == null || set.isEmpty()) ? false : true;
        }
        return false;
    }

    public static void c() {
        LogUtil.c("UdsMmvkUtil", "get Data From Database");
        synchronized (e) {
            if (f388a) {
                e();
                f388a = false;
            } else {
                if (b.get()) {
                    LogUtil.a("UdsMmvkUtil", "Database is loaded.");
                    return;
                }
                d();
                if (!b.get()) {
                    LogUtil.a("UdsMmvkUtil", "SyncData not OK.");
                } else {
                    e();
                }
            }
        }
    }

    private static void e() {
        a();
        Set<String> set = c;
        if (set == null) {
            LogUtil.a("UdsMmvkUtil", "sIdentityList is null.");
            return;
        }
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            a(it.next());
        }
    }

    private static void d() {
        SharedPreferences zZ_ = SharedStoreManager.zZ_("keyvaldb_encrypt_udsdevice");
        if ("ok".equals(zZ_.getString("sync_data", ""))) {
            b.set(true);
            return;
        }
        for (int i = 0; i < 6; i++) {
            try {
                Thread.sleep(500L);
                if ("ok".equals(zZ_.getString("sync_data", ""))) {
                    b.set(true);
                    return;
                }
            } catch (InterruptedException unused) {
                LogUtil.e("UdsMmvkUtil", "waitSyncData InterruptedException");
                return;
            }
        }
    }

    private static void a(String str) {
        String e2 = e(str + "_deviceInfo");
        DeviceInfo deviceInfo = new DeviceInfo();
        if (e2 != null) {
            try {
                deviceInfo = (DeviceInfo) new Gson().fromJson(e2, DeviceInfo.class);
            } catch (JsonSyntaxException unused) {
                LogUtil.e("UdsMmvkUtil", "handleCursorData DeviceInfo fromJson error1");
            }
        }
        LogUtil.c("UdsMmvkUtil", "handleCursorData DeviceInfo fromJson:", deviceInfo.toString());
        if (deviceInfo.getDeviceConnectState() == 1 || deviceInfo.getDeviceConnectState() == 2) {
            deviceInfo.setDeviceConnectState(3);
        }
        bjx.a().d(str, deviceInfo);
        String e3 = e(str + "_deviceStatus");
        if (e3 != null) {
            try {
                bjx.a().e(str, (bix) new Gson().fromJson(e3, bix.class));
            } catch (JsonSyntaxException unused2) {
                LogUtil.e("UdsMmvkUtil", "handleCursorData DeviceStatus fromJson error2");
            }
        }
        String e4 = e(str + "_deviceCapability");
        if (e4 != null) {
            try {
                LogUtil.c("UdsMmvkUtil", "handleCursorData deviceCapabilityStr is not null.");
                bjx.a().d(str, (ExternalDeviceCapability) new Gson().fromJson(e4, ExternalDeviceCapability.class));
            } catch (JsonSyntaxException unused3) {
                LogUtil.e("UdsMmvkUtil", "handleCursorData ExternalDeviceCapability fromJson error3");
            }
        }
        bjy.d().c(deviceInfo);
    }

    private static String e(String str) {
        if (str == null) {
            LogUtil.a("UdsMmvkUtil", "getValue, key is null");
            return "[]";
        }
        return SharedStoreManager.zZ_("keyvaldb_encrypt_udsdevice").getString(str, "[]");
    }

    private static void c(String str, String str2) {
        if (str == null) {
            LogUtil.a("UdsMmvkUtil", "setValue, key is null");
            return;
        }
        SharedPreferences.Editor edit = SharedStoreManager.zZ_("keyvaldb_encrypt_udsdevice").edit();
        edit.putString(str, str2);
        edit.commit();
    }

    private static void e(String str, String str2, String str3) {
        SharedPreferences.Editor edit = SharedStoreManager.zZ_("keyvaldb_encrypt_udsdevice").edit();
        c.add(str);
        edit.putString("identitys", new Gson().toJson(c));
        edit.putString(str2, str3);
        edit.commit();
    }

    private static void c(String str) {
        if (str == null) {
            LogUtil.a("UdsMmvkUtil", "deleteKey, key is null");
            return;
        }
        SharedPreferences.Editor edit = SharedStoreManager.zZ_("keyvaldb_encrypt_udsdevice").edit();
        edit.remove(str);
        edit.commit();
    }

    private static void d(String str) {
        SharedPreferences.Editor edit = SharedStoreManager.zZ_("keyvaldb_encrypt_udsdevice").edit();
        edit.remove(str + "_deviceInfo");
        edit.remove(str + "_deviceStatus");
        edit.remove(str + "_deviceCapability");
        edit.commit();
    }

    public static void a(Collection<DeviceInfo> collection) {
        d(collection, "ble_reconnect_devices");
    }

    public static void d(Collection<DeviceInfo> collection) {
        d(collection, "br_reconnect_devices");
    }

    private static void d(Collection<DeviceInfo> collection, String str) {
        HashMap hashMap = new HashMap(collection.size());
        for (DeviceInfo deviceInfo : collection) {
            HashMap hashMap2 = new HashMap(16);
            hashMap2.put("deviceBtType", Integer.toString(deviceInfo.getDeviceBtType()));
            hashMap2.put("deviceBtMode", deviceInfo.getDeviceBtMode());
            hashMap.put(deviceInfo.getDeviceMac(), hashMap2);
        }
        SharedPreferences.Editor edit = SharedStoreManager.zZ_("keyvaldb_encrypt_udsdevice").edit();
        edit.putString(str, new Gson().toJson(hashMap));
        edit.commit();
    }

    public static void b(String str, Collection<com.huawei.health.devicemgr.business.entity.DeviceInfo> collection) {
        if (!"reconnectDevices".equals(str)) {
            LogUtil.c("UdsMmvkUtil", "saveOldReconnectDeviceList not reconnectDevices");
            return;
        }
        HashMap hashMap = new HashMap(collection.size());
        HashMap hashMap2 = new HashMap(collection.size());
        for (com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo : collection) {
            HashMap hashMap3 = new HashMap(16);
            hashMap3.put("deviceBtType", Integer.toString(deviceInfo.getDeviceBluetoothType()));
            hashMap3.put("deviceBtMode", deviceInfo.getManufacture());
            if (deviceInfo.getDeviceBluetoothType() == 2) {
                hashMap.put(deviceInfo.getDeviceIdentify(), hashMap3);
            } else {
                hashMap2.put(deviceInfo.getDeviceIdentify(), hashMap3);
            }
        }
        SharedPreferences.Editor edit = SharedStoreManager.zZ_("keyvaldb_encrypt_udsdevice").edit();
        edit.putString("old_ble_reconnect_devices", new Gson().toJson(hashMap));
        edit.putString("old_br_reconnect_devices", new Gson().toJson(hashMap2));
        edit.commit();
    }
}
