package defpackage;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.HiDataFilter;
import health.compact.a.EnvironmentUtils;
import health.compact.a.KeyValDbManager;
import health.compact.a.util.LogUtil;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes5.dex */
public class kft {
    private static final Lock b = new ReentrantLock();

    private kft() {
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0052 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void b() {
        /*
            java.lang.Class<kft> r0 = defpackage.kft.class
            monitor-enter(r0)
            r1 = 1
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L70
            java.lang.String r3 = "enter updateWifiMessage"
            r4 = 0
            r2[r4] = r3     // Catch: java.lang.Throwable -> L70
            java.lang.String r3 = "SyncWifiUtils"
            health.compact.a.util.LogUtil.d(r3, r2)     // Catch: java.lang.Throwable -> L70
            boolean r2 = health.compact.a.CommonUtil.ar()     // Catch: java.lang.Throwable -> L70
            if (r2 != 0) goto L23
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L70
            java.lang.String r2 = "The phone emui version is error"
            r1[r4] = r2     // Catch: java.lang.Throwable -> L70
            java.lang.String r2 = "R_SyncWifiUtils"
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r2, r1)     // Catch: java.lang.Throwable -> L70
            monitor-exit(r0)
            return
        L23:
            kfs r2 = e()     // Catch: java.lang.Throwable -> L70
            java.lang.String r3 = r2.c()     // Catch: java.lang.Throwable -> L70
            boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch: java.lang.Throwable -> L70
            if (r3 != 0) goto L40
            java.lang.String r3 = r2.b()     // Catch: java.lang.Throwable -> L70
            boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch: java.lang.Throwable -> L70
            if (r3 == 0) goto L3c
            goto L40
        L3c:
            b(r2)     // Catch: java.lang.Throwable -> L70
            goto L4c
        L40:
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L70
            java.lang.String r3 = "wifi or password is invalid"
            r2[r4] = r3     // Catch: java.lang.Throwable -> L70
            java.lang.String r3 = "SyncWifiUtils"
            health.compact.a.util.LogUtil.d(r3, r2)     // Catch: java.lang.Throwable -> L70
        L4c:
            boolean r2 = g()     // Catch: java.lang.Throwable -> L70
            if (r2 == 0) goto L6e
            f()     // Catch: java.lang.NumberFormatException -> L56 com.google.gson.JsonSyntaxException -> L63 java.lang.Throwable -> L70
            goto L6e
        L56:
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L70
            java.lang.String r2 = "saveMessage NumberFormatException"
            r1[r4] = r2     // Catch: java.lang.Throwable -> L70
            java.lang.String r2 = "SyncWifiUtils"
            health.compact.a.util.LogUtil.e(r2, r1)     // Catch: java.lang.Throwable -> L70
            goto L6e
        L63:
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L70
            java.lang.String r2 = "jsonSyntaxException when setUserPreference"
            r1[r4] = r2     // Catch: java.lang.Throwable -> L70
            java.lang.String r2 = "SyncWifiUtils"
            health.compact.a.util.LogUtil.e(r2, r1)     // Catch: java.lang.Throwable -> L70
        L6e:
            monitor-exit(r0)
            return
        L70:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.kft.b():void");
    }

    public static kfs e() {
        LogUtil.d("SyncWifiUtils", "getCurrentWifMessageEntity enter");
        kfs kfsVar = new kfs();
        try {
            Lock lock = b;
            if (lock.tryLock(2000L, TimeUnit.MILLISECONDS)) {
                try {
                    LogUtil.d("SyncWifiUtils", "getCurrentWifMessageEntity getWifiInfo");
                    kfsVar = c();
                    lock.unlock();
                    return kfsVar;
                } catch (Throwable th) {
                    b.unlock();
                    throw th;
                }
            }
            LogUtil.d("SyncWifiUtils", "getCurrentWifMessageEntity tryLock is false");
            return c();
        } catch (InterruptedException unused) {
            LogUtil.c("SyncWifiUtils", "getCurrentWifMessageEntity InterruptedException");
            return kfsVar;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x009e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static defpackage.kfs c() {
        /*
            java.lang.String r0 = "getCurrentWifMessageEntityDeal enter"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "SyncWifiUtils"
            health.compact.a.util.LogUtil.d(r1, r0)
            android.content.Context r0 = com.huawei.haf.application.BaseApplication.e()
            java.lang.String r0 = d(r0)
            java.lang.String r2 = "currentSsid:"
            java.lang.String r3 = health.compact.a.CommonUtil.l(r0)
            java.lang.Object[] r2 = new java.lang.Object[]{r2, r3}
            health.compact.a.util.LogUtil.d(r1, r2)
            boolean r2 = b(r0)
            if (r2 != 0) goto L35
            java.lang.String r0 = "The wifi name is invalid"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            health.compact.a.util.LogUtil.c(r1, r0)
            kfs r0 = new kfs
            r0.<init>()
            return r0
        L35:
            r2 = 0
            char r2 = r0.charAt(r2)
            r3 = 34
            r4 = 1
            if (r2 != r3) goto L54
            int r2 = r0.length()
            int r2 = r2 - r4
            char r2 = r0.charAt(r2)
            if (r2 != r3) goto L54
            int r2 = r0.length()
            int r2 = r2 - r4
            java.lang.String r2 = r0.substring(r4, r2)
            goto L55
        L54:
            r2 = r0
        L55:
            android.content.Context r3 = com.huawei.haf.application.BaseApplication.e()
            int r3 = c(r2, r3)
            kfs r5 = new kfs
            r5.<init>()
            r5.c(r3)
            java.lang.String r2 = defpackage.kfw.c(r2)     // Catch: java.lang.Exception -> L6a java.lang.SecurityException -> L74 java.lang.NoClassDefFoundError -> L7e
            goto L89
        L6a:
            java.lang.String r2 = "getPassword Exception"
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            health.compact.a.util.LogUtil.e(r1, r2)
            goto L87
        L74:
            java.lang.String r2 = "getPassword SecurityException"
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            health.compact.a.util.LogUtil.e(r1, r2)
            goto L87
        L7e:
            java.lang.String r2 = "getPassword NoClassDefFoundError"
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            health.compact.a.util.LogUtil.e(r1, r2)
        L87:
            java.lang.String r2 = ""
        L89:
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            if (r3 == 0) goto L9e
            java.lang.String r0 = "password is empty"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            health.compact.a.util.LogUtil.c(r1, r0)
            kfs r0 = new kfs
            r0.<init>()
            return r0
        L9e:
            r5.d(r0)
            java.nio.charset.Charset r0 = java.nio.charset.StandardCharsets.UTF_8
            byte[] r0 = r2.getBytes(r0)
            java.lang.String r0 = health.compact.a.Base64.a(r0)
            android.content.Context r2 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.security.GeneralSecurityException -> Ld8
            health.compact.a.HwEncryptUtil r2 = health.compact.a.HwEncryptUtil.c(r2)     // Catch: java.security.GeneralSecurityException -> Ld8
            java.lang.String r0 = r2.b(r4, r0)     // Catch: java.security.GeneralSecurityException -> Ld8
            r5.c(r0)     // Catch: java.security.GeneralSecurityException -> Ld8
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "getP******d m******y.p******d.len = "
            r0.<init>(r2)
            java.lang.String r2 = r5.b()
            int r2 = r2.length()
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            health.compact.a.util.LogUtil.d(r1, r0)
            return r5
        Ld8:
            java.lang.String r0 = "encryptData is not right"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            health.compact.a.util.LogUtil.e(r1, r0)
            kfs r0 = new kfs
            r0.<init>()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.kft.c():kfs");
    }

    private static boolean b(String str) {
        return (TextUtils.isEmpty(str) || str.startsWith(HiDataFilter.DataFilterExpression.LESS_THAN) || str.endsWith(HiDataFilter.DataFilterExpression.BIGGER_THAN)) ? false : true;
    }

    private static int c(String str, Context context) {
        if (context == null || TextUtils.isEmpty(str)) {
            LogUtil.c("SyncWifiUtils", "getWiFiMode() context is error");
            return 0;
        }
        LogUtil.d("SyncWifiUtils", "getWiFiMode() SSID ", str);
        WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
        try {
            List<ScanResult> scanResults = wifiManager.getScanResults();
            if (koq.b(scanResults)) {
                LogUtil.e("SyncWifiUtils", "getScanResults isEmpty");
                return 0;
            }
            for (ScanResult scanResult : scanResults) {
                if (scanResult.SSID.equals(str)) {
                    return bNA_(scanResult);
                }
            }
            List<WifiConfiguration> bNz_ = bNz_(wifiManager);
            if (koq.b(bNz_)) {
                return 0;
            }
            for (WifiConfiguration wifiConfiguration : bNz_) {
                if (wifiConfiguration.SSID.equals("\"" + str + "\"")) {
                    return bNB_(wifiConfiguration);
                }
            }
            return 0;
        } catch (RuntimeException unused) {
            LogUtil.e("SyncWifiUtils", "getScanResults RuntimeException");
            return 0;
        }
    }

    private static int bNA_(ScanResult scanResult) {
        if (scanResult == null) {
            LogUtil.e("SyncWifiUtils", "scanResult == null");
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

    private static int bNB_(WifiConfiguration wifiConfiguration) {
        if (wifiConfiguration == null) {
            LogUtil.e("SyncWifiUtils", "config == null");
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

    private static List<WifiConfiguration> bNz_(WifiManager wifiManager) {
        try {
            return wifiManager.getConfiguredNetworks();
        } catch (Exception unused) {
            LogUtil.e("SyncWifiUtils", "getConfiguredNetworksUtil Exception");
            return null;
        }
    }

    private static String d(Context context) {
        WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
        String ssid = connectionInfo != null ? connectionInfo.getSSID() : null;
        String trim = ssid != null ? ssid.trim() : null;
        if (!TextUtils.isEmpty(trim) && trim.charAt(0) == '\"' && trim.charAt(trim.length() - 1) == '\"') {
            trim = trim.substring(1, trim.length() - 1);
        }
        if (!b(trim)) {
            trim = a(context);
        }
        return !b(trim) ? e(context) : trim;
    }

    private static String a(Context context) {
        NetworkInfo networkInfo;
        Object systemService;
        try {
            systemService = context.getSystemService("connectivity");
        } catch (ClassCastException unused) {
            LogUtil.c("SyncWifiUtils", "getNetworkInfo error. ");
        }
        if (systemService instanceof ConnectivityManager) {
            networkInfo = ((ConnectivityManager) systemService).getActiveNetworkInfo();
            return networkInfo != null ? "" : "";
        }
        networkInfo = null;
        return networkInfo != null ? "" : "";
    }

    private static String e(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
        if (wifiManager == null) {
            return "";
        }
        int networkId = wifiManager.getConnectionInfo().getNetworkId();
        try {
            List<WifiConfiguration> configuredNetworks = wifiManager.getConfiguredNetworks();
            if (configuredNetworks == null) {
                return "";
            }
            for (WifiConfiguration wifiConfiguration : configuredNetworks) {
                if (wifiConfiguration.networkId == networkId) {
                    return wifiConfiguration.SSID;
                }
            }
            return "";
        } catch (Exception e) {
            LogUtil.e("SyncWifiUtils", "getSSIDByNetworkId Exception: ", ExceptionUtils.d(e));
            return "";
        }
    }

    private static void f() {
        if (EnvironmentUtils.c()) {
            LogUtil.d("SyncWifiUtils", "sendWifiMessage current is Health Main Process():");
            jfq.c().g();
        } else {
            LogUtil.d("SyncWifiUtils", "sendWifiMessage current is Health PhoneService Process():");
            kfv.d().c();
        }
    }

    private static void b(kfs kfsVar) {
        List<kfs> a2 = a();
        Iterator<kfs> it = a2.iterator();
        while (true) {
            if (it.hasNext()) {
                kfs next = it.next();
                if (next != null && kfsVar != null) {
                    if (TextUtils.isEmpty(next.c()) || TextUtils.isEmpty(kfsVar.c())) {
                        LogUtil.c("SyncWifiUtils", "entity content is empty");
                    } else if (!next.c().equals(kfsVar.c())) {
                        continue;
                    } else if (next.toString().equals(kfsVar.toString())) {
                        break;
                    } else {
                        it.remove();
                    }
                }
            } else if (kfsVar != null) {
                LogUtil.d("SyncWifiUtils", "add wifiSSID:", kfsVar.c());
                a2.add(kfsVar);
            }
        }
        if (a2.size() > 2) {
            LogUtil.d("SyncWifiUtils", "enter remove");
            a2.remove(0);
        }
        KeyValDbManager.b(BaseApplication.e()).e("cached_wifi_message", new Gson().toJson(a2));
    }

    /* JADX WARN: Code restructure failed: missing block: B:56:0x00f5, code lost:
    
        health.compact.a.util.LogUtil.d("SyncWifiUtils", "synchronizedWifiMap is null. update: ", r4.toString());
        r1.put(r4.getDeviceIdentify(), r5);
        r2 = true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean g() {
        /*
            java.util.List r0 = d()
            boolean r1 = defpackage.koq.b(r0)
            r2 = 0
            java.lang.String r3 = "SyncWifiUtils"
            if (r1 == 0) goto L17
            java.lang.String r0 = "no connected device. "
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            health.compact.a.util.LogUtil.d(r3, r0)
            return r2
        L17:
            java.util.HashMap r1 = h()
            java.util.Iterator r0 = r0.iterator()
        L1f:
            boolean r4 = r0.hasNext()
            if (r4 == 0) goto L10b
            java.lang.Object r4 = r0.next()
            com.huawei.health.devicemgr.business.entity.DeviceInfo r4 = (com.huawei.health.devicemgr.business.entity.DeviceInfo) r4
            if (r1 != 0) goto L32
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
        L32:
            java.util.List r5 = a()
            boolean r6 = r5.isEmpty()
            if (r6 == 0) goto L47
            java.lang.String r0 = "needSynchronizeWifi cachedList: isEmpty"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            health.compact.a.util.LogUtil.c(r3, r0)
            goto L10b
        L47:
            boolean r6 = r1.isEmpty()
            r7 = 1
            if (r6 != 0) goto Lf5
            java.lang.String r6 = r4.getDeviceIdentify()
            boolean r6 = r1.containsKey(r6)
            if (r6 != 0) goto L5a
            goto Lf5
        L5a:
            java.lang.String r6 = r4.getDeviceIdentify()
            java.lang.Object r6 = r1.get(r6)
            java.util.List r6 = (java.util.List) r6
            java.util.Iterator r8 = r5.iterator()
        L68:
            boolean r9 = r8.hasNext()
            if (r9 == 0) goto L9e
            java.lang.Object r9 = r8.next()
            kfs r9 = (defpackage.kfs) r9
            if (r9 == 0) goto L68
            if (r6 == 0) goto L68
            boolean r9 = r6.contains(r9)
            if (r9 != 0) goto L68
            java.lang.String r2 = r4.getDeviceIdentify()
            boolean r2 = r1.containsKey(r2)
            if (r2 == 0) goto L9d
            java.lang.String r2 = "synchronizedWifiMap update: "
            java.lang.String r6 = r4.toString()
            java.lang.Object[] r2 = new java.lang.Object[]{r2, r6}
            health.compact.a.util.LogUtil.d(r3, r2)
            java.lang.String r2 = r4.getDeviceIdentify()
            r1.put(r2, r5)
        L9d:
            r2 = r7
        L9e:
            int r5 = r4.getDeviceFactoryReset()
            java.lang.String r6 = "getDeviceFactoryReset value: "
            java.lang.Integer r8 = java.lang.Integer.valueOf(r5)
            java.lang.Object[] r6 = new java.lang.Object[]{r6, r8}
            health.compact.a.util.LogUtil.d(r3, r6)
            if (r2 != 0) goto Lea
            if (r5 != r7) goto Lea
            com.huawei.hwdevice.phoneprocess.mgr.hwwifisyncmgr.WifiConnectReceiver r5 = com.huawei.hwdevice.phoneprocess.mgr.hwwifisyncmgr.WifiConnectReceiver.b()
            java.util.List r5 = r5.e()
            java.lang.String r6 = r4.getDeviceIdentify()
            java.lang.String r6 = defpackage.knl.a(r6)
            boolean r5 = r5.contains(r6)
            if (r5 == 0) goto Ldf
            com.huawei.hwdevice.phoneprocess.mgr.hwwifisyncmgr.WifiConnectReceiver r2 = com.huawei.hwdevice.phoneprocess.mgr.hwwifisyncmgr.WifiConnectReceiver.b()
            java.util.List r2 = r2.e()
            java.lang.String r4 = r4.getDeviceIdentify()
            java.lang.String r4 = defpackage.knl.a(r4)
            r2.remove(r4)
            r2 = r7
            goto L1f
        Ldf:
            java.lang.String r4 = "getDeviceFactoryReset list not contains current device"
            java.lang.Object[] r4 = new java.lang.Object[]{r4}
            health.compact.a.util.LogUtil.d(r3, r4)
            goto L1f
        Lea:
            java.lang.String r4 = "getDeviceFactoryReset value not 1"
            java.lang.Object[] r4 = new java.lang.Object[]{r4}
            health.compact.a.util.LogUtil.d(r3, r4)
            goto L1f
        Lf5:
            java.lang.String r0 = "synchronizedWifiMap is null. update: "
            java.lang.String r2 = r4.toString()
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r2}
            health.compact.a.util.LogUtil.d(r3, r0)
            java.lang.String r0 = r4.getDeviceIdentify()
            r1.put(r0, r5)
            r2 = r7
        L10b:
            android.content.Context r0 = com.huawei.haf.application.BaseApplication.e()
            health.compact.a.KeyValDbManager r0 = health.compact.a.KeyValDbManager.b(r0)
            com.google.gson.Gson r3 = new com.google.gson.Gson
            r3.<init>()
            java.lang.String r4 = "synchronized_wifi_message"
            java.lang.String r1 = r3.toJson(r1)
            r0.e(r4, r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.kft.g():boolean");
    }

    private static HashMap<String, List<kfs>> h() {
        try {
            return (HashMap) new Gson().fromJson(KeyValDbManager.b(BaseApplication.e()).e("synchronized_wifi_message"), new TypeToken<HashMap<String, List<kfs>>>() { // from class: kft.4
            }.getType());
        } catch (JsonSyntaxException unused) {
            LogUtil.e("SyncWifiUtils", "synchronizedWifiMap jsonSyntaxException ");
            return null;
        } catch (NumberFormatException unused2) {
            LogUtil.e("SyncWifiUtils", "synchronizedWifiMap NumberFormatException");
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:8:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.util.List<defpackage.kfs> a() {
        /*
            java.lang.String r0 = "SyncWifiUtils"
            android.content.Context r1 = com.huawei.haf.application.BaseApplication.e()
            health.compact.a.KeyValDbManager r1 = health.compact.a.KeyValDbManager.b(r1)
            java.lang.String r2 = "cached_wifi_message"
            java.lang.String r1 = r1.e(r2)
            com.google.gson.Gson r2 = new com.google.gson.Gson     // Catch: java.lang.NumberFormatException -> L25 com.google.gson.JsonSyntaxException -> L30
            r2.<init>()     // Catch: java.lang.NumberFormatException -> L25 com.google.gson.JsonSyntaxException -> L30
            kft$2 r3 = new kft$2     // Catch: java.lang.NumberFormatException -> L25 com.google.gson.JsonSyntaxException -> L30
            r3.<init>()     // Catch: java.lang.NumberFormatException -> L25 com.google.gson.JsonSyntaxException -> L30
            java.lang.reflect.Type r3 = r3.getType()     // Catch: java.lang.NumberFormatException -> L25 com.google.gson.JsonSyntaxException -> L30
            java.lang.Object r1 = r2.fromJson(r1, r3)     // Catch: java.lang.NumberFormatException -> L25 com.google.gson.JsonSyntaxException -> L30
            java.util.List r1 = (java.util.List) r1     // Catch: java.lang.NumberFormatException -> L25 com.google.gson.JsonSyntaxException -> L30
            goto L3b
        L25:
            java.lang.String r1 = "savedMessage NumberFormatException"
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            health.compact.a.util.LogUtil.e(r0, r1)
            goto L3a
        L30:
            java.lang.String r1 = "savedMessage jsonSyntaxException "
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            health.compact.a.util.LogUtil.e(r0, r1)
        L3a:
            r1 = 0
        L3b:
            if (r1 != 0) goto L43
            java.util.ArrayList r1 = new java.util.ArrayList
            r0 = 2
            r1.<init>(r0)
        L43:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.kft.a():java.util.List");
    }

    private static List<DeviceInfo> d() {
        if (EnvironmentUtils.c()) {
            LogUtil.d("SyncWifiUtils", "getConnectedDeviceList current is Health Main Process():");
            return cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, null, "SyncWifiUtils");
        }
        LogUtil.d("SyncWifiUtils", "getConnectedDeviceList current is Health PhoneService Process():");
        return cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, null, "SyncWifiUtils");
    }

    public static boolean b(Context context) {
        if (context == null) {
            LogUtil.e("SyncWifiUtils", "checkWifiStatus context is null");
            return false;
        }
        boolean isWifiEnabled = ((WifiManager) context.getSystemService("wifi")).isWifiEnabled();
        LogUtil.b("SyncWifiUtils", "checkWifiStatus ", Boolean.valueOf(isWifiEnabled));
        return isWifiEnabled;
    }
}
