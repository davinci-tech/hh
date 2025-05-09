package defpackage;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes3.dex */
public class cub {
    public static int My_(WifiManager wifiManager, String str) {
        cpw.c(false, "WiFiUtil", "getOpenNetworkId() ssid ", cpw.d(str));
        if (wifiManager == null || TextUtils.isEmpty(str)) {
            cpw.e(false, "WiFiUtil", "getOpenNetworkId() parameter error");
            return -1;
        }
        int Mz_ = Mz_(wifiManager, str, -1);
        cpw.c(false, "WiFiUtil", "getOpenNetworkId() for over newnetid = ", Integer.valueOf(Mz_));
        if (Mz_ == -1) {
            Mz_ = wifiManager.addNetwork(MF_(str));
            cpw.c(false, "WiFiUtil", "getOpenNetworkId newnetid = ", Integer.valueOf(Mz_), " bSaved:", Boolean.valueOf(wifiManager.saveConfiguration()));
        }
        if (Mz_ == -1) {
            List<WifiConfiguration> Mu_ = Mu_(wifiManager);
            if (koq.b(Mu_)) {
                LogUtil.b("WiFiUtil", "getOpenNetworkId newConfigs is null");
                return Mz_;
            }
            for (WifiConfiguration wifiConfiguration : Mu_) {
                if (!TextUtils.isEmpty(wifiConfiguration.SSID) && TextUtils.equals(str, a(wifiConfiguration.SSID))) {
                    Mz_ = wifiConfiguration.networkId;
                }
            }
        }
        cpw.a(false, "WiFiUtil", "getOpenNetworkId newNetId = ", Integer.valueOf(Mz_));
        return Mz_;
    }

    private static int Mz_(WifiManager wifiManager, String str, int i) {
        List<WifiConfiguration> Mu_ = Mu_(wifiManager);
        if (koq.c(Mu_)) {
            for (WifiConfiguration wifiConfiguration : Mu_) {
                if (TextUtils.isEmpty(wifiConfiguration.SSID)) {
                    cpw.c(false, "WiFiUtil", "getOpenNetworkId ssid NULL");
                } else {
                    String a2 = a(wifiConfiguration.SSID);
                    cpw.c(false, "WiFiUtil", "getOpenNetworkId open oldSsid:", a2, " ssid:", str);
                    if (!TextUtils.equals(str, a2)) {
                        continue;
                    } else {
                        if (wifiConfiguration.allowedKeyManagement.get(0)) {
                            cpw.c(false, "WiFiUtil", "getOpenNetworkId SoftAP is no password, direct useid");
                            return wifiConfiguration.networkId;
                        }
                        boolean removeNetwork = wifiManager.removeNetwork(wifiConfiguration.networkId);
                        cpw.c(false, "WiFiUtil", "getOpenNetworkId removeNetwork target = ", Integer.valueOf(wifiConfiguration.networkId), " isRemoveSucceeded = ", Boolean.valueOf(removeNetwork));
                        if (removeNetwork) {
                            wifiManager.saveConfiguration();
                        } else {
                            i = wifiConfiguration.networkId;
                        }
                    }
                }
            }
            return i;
        }
        cpw.e(false, "WiFiUtil", "getOpenNetworkId()  configs is null");
        return i;
    }

    public static String a(String str) {
        int length;
        if (str == null || (length = str.length()) <= 1 || str.charAt(0) != '\"') {
            return str;
        }
        int i = length - 1;
        return str.charAt(i) == '\"' ? str.substring(1, i) : str;
    }

    public static WifiConfiguration MF_(String str) {
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.allowedProtocols.clear();
        wifiConfiguration.allowedKeyManagement.clear();
        wifiConfiguration.allowedAuthAlgorithms.clear();
        wifiConfiguration.allowedPairwiseCiphers.clear();
        wifiConfiguration.allowedGroupCiphers.clear();
        wifiConfiguration.SSID = "\"" + str + "\"";
        wifiConfiguration.status = 1;
        wifiConfiguration.BSSID = null;
        wifiConfiguration.allowedProtocols.set(0);
        wifiConfiguration.allowedProtocols.set(1);
        wifiConfiguration.allowedKeyManagement.set(0);
        wifiConfiguration.allowedPairwiseCiphers.set(1);
        wifiConfiguration.allowedPairwiseCiphers.set(2);
        wifiConfiguration.allowedGroupCiphers.set(2);
        wifiConfiguration.allowedGroupCiphers.set(3);
        return wifiConfiguration;
    }

    public static boolean Mt_(WifiManager wifiManager, int i) {
        cpw.a(false, "WiFiUtil", "connectWifiByNetworkId() enter newnetid ", Integer.valueOf(i));
        if (wifiManager == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 27) {
            wifiManager.disconnect();
            boolean enableNetwork = wifiManager.enableNetwork(i, true);
            cpw.c(false, "WiFiUtil", "SDK >= Android API(27) only enableNetwork flag  ", Boolean.valueOf(enableNetwork));
            wifiManager.reconnect();
            return enableNetwork;
        }
        boolean enableNetwork2 = wifiManager.enableNetwork(i, true);
        cpw.c(false, "WiFiUtil", "only enableNetwork flag  ", Boolean.valueOf(enableNetwork2));
        return enableNetwork2;
    }

    public static boolean MD_(WifiManager wifiManager, int i) {
        cpw.a(false, "WiFiUtil", " reconnectWifiByNetworkId() enter newnetid ", Integer.valueOf(i));
        if (wifiManager == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 27) {
            boolean enableNetwork = wifiManager.enableNetwork(i, true);
            cpw.c(false, "WiFiUtil", "SDK >= Android API(27) reconnect enableNetwork flag ", Boolean.valueOf(enableNetwork));
            wifiManager.reconnect();
            return enableNetwork;
        }
        boolean enableNetwork2 = wifiManager.enableNetwork(i, true);
        cpw.c(false, "WiFiUtil", "reconnect only enableNetwork flag  ", Boolean.valueOf(enableNetwork2));
        return enableNetwork2;
    }

    public static int Mw_(WifiManager wifiManager, String str, String str2, String str3) {
        cpw.a(false, "WiFiUtil", " getNetworkId() ssid ", cpw.d(str));
        int i = -1;
        if (wifiManager == null || TextUtils.isEmpty(str)) {
            cpw.c(false, "WiFiUtil", " getNetworkId() parameter error");
            return -1;
        }
        List<WifiConfiguration> Mu_ = Mu_(wifiManager);
        if (koq.b(Mu_)) {
            return Mx_(-1, str2, str, wifiManager);
        }
        for (WifiConfiguration wifiConfiguration : Mu_) {
            if (!TextUtils.isEmpty(wifiConfiguration.SSID)) {
                String a2 = a(wifiConfiguration.SSID);
                cpw.c(true, "WiFiUtil", "getNetworkId router oldSsid:", a2, " ssid:", str, " rm ssid:", str3);
                boolean removeNetwork = wifiManager.removeNetwork(wifiConfiguration.networkId);
                cpw.a(false, "WiFiUtil", " getNetworkId() removeNetwork remove = ", Integer.valueOf(wifiConfiguration.networkId), " isRemoveSucceeded = ", Boolean.valueOf(removeNetwork));
                if (TextUtils.equals(str3, a2) && removeNetwork) {
                    wifiManager.saveConfiguration();
                }
                if (TextUtils.equals(str, a2)) {
                    if (removeNetwork) {
                        wifiManager.saveConfiguration();
                    } else {
                        if (TextUtils.isEmpty(str2)) {
                            cpw.c(false, "WiFiUtil", " getNetworkId() pazzword is empty ");
                        }
                        if (TextUtils.isEmpty(str2) && wifiConfiguration.allowedKeyManagement.get(0)) {
                            cpw.c(false, "WiFiUtil", " getNetworkId() reuse open mode id: ", Integer.valueOf(wifiConfiguration.networkId));
                            i = wifiConfiguration.networkId;
                        } else if (!TextUtils.isEmpty(str2) && !wifiConfiguration.allowedKeyManagement.get(0)) {
                            cpw.c(false, "WiFiUtil", " getNetworkId() reuse id: ", Integer.valueOf(wifiConfiguration.networkId));
                            i = wifiConfiguration.networkId;
                        } else {
                            cpw.c(false, "WiFiUtil", " getNetworkId() mode id: ", Integer.valueOf(wifiConfiguration.networkId));
                        }
                    }
                }
            }
        }
        return Mx_(i, str2, str, wifiManager);
    }

    private static int Mx_(int i, String str, String str2, WifiManager wifiManager) {
        int i2;
        cpw.c(false, "WiFiUtil", " getNetworkId() for over newnetid = ", Integer.valueOf(i));
        if (i == -1) {
            if (TextUtils.isEmpty(str)) {
                i2 = wifiManager.addNetwork(MF_(str2));
            } else {
                i2 = wifiManager.addNetwork(ME_(str2, str));
            }
            cpw.c(false, "WiFiUtil", "getNetworkId bSaved:", Boolean.valueOf(wifiManager.saveConfiguration()));
        } else {
            i2 = i;
        }
        if (i2 == -1) {
            List<WifiConfiguration> Mu_ = Mu_(wifiManager);
            if (koq.b(Mu_)) {
                LogUtil.b("WiFiUtil", "getNewNetworkId newConfigs is null");
                return i2;
            }
            for (WifiConfiguration wifiConfiguration : Mu_) {
                if (!TextUtils.isEmpty(wifiConfiguration.SSID) && TextUtils.equals(str2, a(wifiConfiguration.SSID))) {
                    i2 = wifiConfiguration.networkId;
                }
            }
        }
        cpw.a(false, "WiFiUtil", "getNetworkId newnetid = ", Integer.valueOf(i));
        return i2;
    }

    public static WifiConfiguration ME_(String str, String str2) {
        cpw.c(false, "WiFiUtil", " setWifiParamsByWpa()");
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.allowedProtocols.clear();
        wifiConfiguration.allowedKeyManagement.clear();
        wifiConfiguration.allowedAuthAlgorithms.clear();
        wifiConfiguration.allowedPairwiseCiphers.clear();
        wifiConfiguration.allowedGroupCiphers.clear();
        wifiConfiguration.SSID = "\"" + str + "\"";
        wifiConfiguration.preSharedKey = "\"" + str2 + "\"";
        wifiConfiguration.status = 2;
        wifiConfiguration.BSSID = null;
        wifiConfiguration.allowedAuthAlgorithms.set(0);
        wifiConfiguration.allowedKeyManagement.set(1);
        wifiConfiguration.allowedPairwiseCiphers.set(1);
        wifiConfiguration.allowedPairwiseCiphers.set(2);
        wifiConfiguration.allowedGroupCiphers.set(2);
        wifiConfiguration.allowedGroupCiphers.set(3);
        return wifiConfiguration;
    }

    public static int a(Context context) {
        if (context == null) {
            return -1;
        }
        WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
        if (connectionInfo == null) {
            cpw.e(false, "WiFiUtil", "getConnectionInfo is null!");
            return 0;
        }
        if (connectionInfo.getMacAddress() == null) {
            cpw.e(false, "WiFiUtil", "getConnectionWiFiInfo(): No WiFi Device");
            return 0;
        }
        return connectionInfo.getIpAddress();
    }

    public static boolean g(Context context) {
        if (context == null) {
            return false;
        }
        Object systemService = context.getSystemService("connectivity");
        if (!(systemService instanceof ConnectivityManager)) {
            LogUtil.h("WiFiUtil", "isWifiConnected obj is not instanceof ConnectivityManager");
            return false;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) systemService).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected() && activeNetworkInfo.getType() == 1;
    }

    public static boolean f(Context context) {
        if (context == null) {
            return false;
        }
        try {
            List<ScanResult> scanResults = ((WifiManager) context.getSystemService("wifi")).getScanResults();
            String e = e(c(context));
            cpw.a(false, "WiFiUtil", "is24GHzWiFi currentSsid: ", e);
            if (koq.b(scanResults)) {
                cpw.a(false, "WiFiUtil", "scanResults.size() <= 0 ");
                return true;
            }
            for (ScanResult scanResult : scanResults) {
                String str = scanResult.SSID;
                if (TextUtils.isEmpty(str)) {
                    cpw.a(false, "WiFiUtil", "is24GHzWiFi scanResult scanResult.SSID is null");
                } else if (str.equals(e)) {
                    int i = scanResult.frequency;
                    cpw.a(true, "WiFiUtil", "currentSsid = ", e, " |wifiFrequency = ", Integer.valueOf(i));
                    if (i >= 2400 && i <= 3000) {
                        return true;
                    }
                } else {
                    continue;
                }
            }
            return false;
        } catch (RuntimeException unused) {
            return true;
        }
    }

    public static boolean b(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            cpw.d(false, "WiFiUtil", "isScanResultsHaveCurrentSsid context or ssid is null");
            return false;
        }
        cpw.a(false, "WiFiUtil", "isScanResultsHaveCurrentSsid compareSsid: ", str);
        try {
            List<ScanResult> scanResults = ((WifiManager) context.getSystemService("wifi")).getScanResults();
            if (koq.b(scanResults)) {
                cpw.a(false, "WiFiUtil", "scanResults.size() <= 0 ");
                return true;
            }
            Iterator<ScanResult> it = scanResults.iterator();
            while (it.hasNext()) {
                String str2 = it.next().SSID;
                if (TextUtils.isEmpty(str2)) {
                    cpw.a(false, "WiFiUtil", "isScanResultsHaveCurrentSsid scanResult scanResult.SSID is null");
                } else if (str2.equals(str)) {
                    cpw.a(false, "WiFiUtil", "isScanResultsHaveCurrentSsid compare consistent");
                    return true;
                }
            }
            return false;
        } catch (RuntimeException unused) {
            cpw.e(false, "WiFiUtil", "isScanResultsHaveCurrentSsid RuntimeException");
            return true;
        }
    }

    public static String c(Context context) {
        WifiInfo connectionInfo;
        if (context == null || (connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo()) == null) {
            return "";
        }
        if (connectionInfo.getMacAddress() != null) {
            return connectionInfo.getSSID();
        }
        cpw.d(false, "WiFiUtil", "getConnectionWiFiInfo(): No WiFi Device");
        return "";
    }

    public static int b(String str, Context context) {
        if (context == null || TextUtils.isEmpty(str)) {
            cpw.d(false, "WiFiUtil", "getWiFiMode() context is error");
            return 0;
        }
        cpw.a(false, "WiFiUtil", "getWiFiMode() SSID ", str);
        WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
        try {
            List<ScanResult> scanResults = wifiManager.getScanResults();
            if (koq.b(scanResults)) {
                LogUtil.b("WiFiUtil", "getScanResults isEmpty");
                return 0;
            }
            for (ScanResult scanResult : scanResults) {
                cpw.a(false, "WiFiUtil", "getWiFiMode() scanResult SSID ", scanResult.SSID);
                if (scanResult.SSID.equals(str)) {
                    return ctz.Ml_(scanResult);
                }
            }
            List<WifiConfiguration> Mu_ = Mu_(wifiManager);
            if (koq.c(Mu_)) {
                for (WifiConfiguration wifiConfiguration : Mu_) {
                    cpw.a(false, "WiFiUtil", "getWiFiMode() configuration SSID ", wifiConfiguration.SSID);
                    if (wifiConfiguration.SSID.equals("\"" + str + "\"")) {
                        return ctz.Mm_(wifiConfiguration);
                    }
                }
            }
            return 0;
        } catch (RuntimeException unused) {
            LogUtil.b("WiFiUtil", "getScanResults RuntimeException");
            return 0;
        }
    }

    public static List<WifiConfiguration> Mu_(WifiManager wifiManager) {
        try {
            return wifiManager.getConfiguredNetworks();
        } catch (Exception unused) {
            LogUtil.b("WiFiUtil", "getConfiguredNetworksUtil Exception");
            return null;
        }
    }

    public static String e(String str) {
        int length;
        return (TextUtils.isEmpty(str) || !str.startsWith("\"") || !str.endsWith("\"") || (length = str.length()) <= 1) ? str : str.substring(1, length - 1);
    }

    public static void j(Context context) {
        if (context != null) {
            ctv.Mj_(context, new Intent("android.settings.SETTINGS"));
        } else {
            cpw.e(false, "WiFiUtil", "goToNetWorkSetting context is null");
        }
    }

    public static boolean d(Context context) {
        if (context == null) {
            cpw.e(false, "WiFiUtil", "checkWifiStatus context is null");
            return false;
        }
        boolean isWifiEnabled = ((WifiManager) context.getSystemService("wifi")).isWifiEnabled();
        cpw.c(false, "WiFiUtil", "checkWifiStatus ", Boolean.valueOf(isWifiEnabled));
        return isWifiEnabled;
    }

    public static void k(Context context) {
        if (context != null) {
            ctv.Mj_(context, new Intent("android.settings.SETTINGS"));
        } else {
            cpw.e(false, "WiFiUtil", "startPhoneSetting context is null");
        }
    }

    public static void m(Context context) {
        if (ctv.b()) {
            n(context);
        } else {
            o(context);
        }
    }

    private static void n(Context context) {
        Intent intent = new Intent("android.settings.PRIVACY_SETTINGS");
        intent.addFlags(268435456);
        ctv.Mj_(context, intent);
    }

    private static void o(Context context) {
        Intent intent = new Intent("android.settings.WIFI_SETTINGS");
        intent.addFlags(268435456);
        ctv.Mj_(context, intent);
    }

    public static boolean l(Context context) {
        if (context == null) {
            cpw.a(false, "WiFiUtil", "startWifiScan context is null");
            return false;
        }
        boolean startScan = ((WifiManager) context.getSystemService("wifi")).startScan();
        cpw.a(false, "WiFiUtil", "isScan = ", Boolean.valueOf(startScan));
        return startScan;
    }

    public static List<ScanResult> c(Context context, boolean z) {
        List<ScanResult> list;
        ArrayList arrayList = new ArrayList(16);
        if (context == null) {
            LogUtil.h("WiFiUtil", "getWifiList() context is null");
            return arrayList;
        }
        try {
            list = ((WifiManager) context.getSystemService("wifi")).getScanResults();
        } catch (Exception unused) {
            LogUtil.b("WiFiUtil", "getWifiList() WifiManager getScanResults exception");
            list = null;
        }
        if (koq.b(list)) {
            LogUtil.h("WiFiUtil", "getWifiList() scanResults is null");
            return arrayList;
        }
        for (ScanResult scanResult : list) {
            if (scanResult.SSID != null && !"".equals(scanResult.SSID) && (z || MB_(scanResult))) {
                int Mv_ = Mv_(arrayList, scanResult);
                if (Mv_ == -1) {
                    arrayList.add(scanResult);
                } else {
                    ScanResult scanResult2 = (ScanResult) arrayList.get(Mv_);
                    boolean z2 = scanResult.level - scanResult2.level > 0;
                    if (!MB_(scanResult2) && MB_(scanResult)) {
                        arrayList.set(Mv_, scanResult);
                    } else if (MB_(scanResult) && z2) {
                        arrayList.set(Mv_, scanResult);
                    } else {
                        LogUtil.a("WiFiUtil", "getWifiList() keep old scanResult");
                    }
                }
            }
        }
        LogUtil.a("WiFiUtil", "getWifiList() wifi list size = ", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    public static List<WifiConfiguration> e(Context context) {
        List<WifiConfiguration> arrayList = new ArrayList<>(16);
        if (context == null) {
            LogUtil.h("WiFiUtil", "getConfiguredNetworksList() context is null");
            return arrayList;
        }
        try {
            arrayList = ((WifiManager) context.getSystemService("wifi")).getConfiguredNetworks();
        } catch (Exception unused) {
            LogUtil.b("WiFiUtil", "getConfiguredNetworksUtil Exception");
        }
        if (koq.b(arrayList)) {
            LogUtil.h("WiFiUtil", "getConfiguredNetworksList() scanResults is null");
            return arrayList;
        }
        LogUtil.a("WiFiUtil", "getConfiguredNetworksList() wifi list size = ", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    public static List<ScanResult> b(Context context) {
        List<ScanResult> list;
        ArrayList arrayList = new ArrayList(16);
        if (context == null) {
            LogUtil.h("WiFiUtil", "get5GhzWifiList() context is null");
            return arrayList;
        }
        try {
            list = ((WifiManager) context.getSystemService("wifi")).getScanResults();
        } catch (Exception unused) {
            LogUtil.b("WiFiUtil", "get5GhzWifiList() WifiManager getScanResults exception");
            list = null;
        }
        if (koq.b(list)) {
            LogUtil.h("WiFiUtil", "get5GhzWifiList() scanResults is null");
            return arrayList;
        }
        for (ScanResult scanResult : list) {
            if (scanResult.SSID != null && !"".equals(scanResult.SSID) && !MB_(scanResult)) {
                int Mv_ = Mv_(arrayList, scanResult);
                if (Mv_ == -1) {
                    arrayList.add(scanResult);
                } else {
                    boolean z = scanResult.level - ((ScanResult) arrayList.get(Mv_)).level > 0;
                    if (!MB_(scanResult) && z) {
                        arrayList.set(Mv_, scanResult);
                    } else {
                        LogUtil.a("WiFiUtil", "get5GhzWifiList() keep old scanResult");
                    }
                }
            }
        }
        LogUtil.a("WiFiUtil", "get5GhzWifiList() wifi list size: ", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    private static int Mv_(List<ScanResult> list, ScanResult scanResult) {
        for (int i = 0; i < list.size(); i++) {
            if (scanResult.SSID.equals(list.get(i).SSID)) {
                return i;
            }
        }
        return -1;
    }

    public static boolean d(Context context, String str) {
        if (context == null) {
            cpw.a(false, "WiFiUtil", "isWifiConsistency context is null");
            return false;
        }
        WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
        return connectionInfo != null && e(connectionInfo.getSSID()).equals(str);
    }

    public static boolean MB_(ScanResult scanResult) {
        if (scanResult == null) {
            return false;
        }
        cpw.c(true, "WiFiUtil", "ssid = ", scanResult.SSID, " |frequency = ", Integer.valueOf(scanResult.frequency));
        int i = scanResult.frequency;
        return i >= 2400 && i <= 3000;
    }

    public static boolean i(Context context) {
        if (context == null) {
            return false;
        }
        try {
            List<ScanResult> scanResults = ((WifiManager) context.getSystemService("wifi")).getScanResults();
            String e = e(c(context));
            if (koq.b(scanResults)) {
                LogUtil.h("WiFiUtil", "scanResults isNotEmpty");
                return false;
            }
            ScanResult scanResult = null;
            for (ScanResult scanResult2 : scanResults) {
                String str = scanResult2.SSID;
                if (str != null && str.equals(e)) {
                    scanResult = scanResult2;
                }
            }
            return MC_(scanResult);
        } catch (RuntimeException unused) {
            LogUtil.h("WiFiUtil", "scanResults RuntimeException");
            return false;
        }
    }

    public static boolean MC_(ScanResult scanResult) {
        if (scanResult == null) {
            return false;
        }
        String upperCase = scanResult.capabilities.toUpperCase(Locale.ENGLISH);
        return (upperCase.contains("WEP") || upperCase.contains("PSK") || upperCase.contains("EAP")) ? false : true;
    }

    public static boolean a(Context context, boolean z) {
        cpw.a(false, "WiFiUtil", "setWifiEnabled...");
        return ((WifiManager) context.getSystemService("wifi")).setWifiEnabled(z);
    }

    public static boolean h(Context context) {
        cpw.a(false, "WiFiUtil", "isWifiOpen...");
        return ((WifiManager) context.getSystemService("wifi")).getWifiState() == 3;
    }

    public static ScanResult MA_(Context context, String str) {
        for (ScanResult scanResult : c(context, true)) {
            if (scanResult.SSID.equals(str)) {
                return scanResult;
            }
        }
        return null;
    }
}
