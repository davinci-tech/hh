package defpackage;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.util.Base64;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes3.dex */
public class cst {
    private static final int[] e = {44, 46, 47, 96, 33, 64};

    public static boolean b(Context context) {
        return c(context);
    }

    public static int[][] a(int[] iArr) {
        int[] iArr2;
        String encodeToString = Base64.encodeToString(ctv.d(iArr), 2);
        cpw.c(false, "ProbeReguestUtil", "Base64.encodeToString ", encodeToString);
        int length = encodeToString.length();
        char[] charArray = encodeToString.toCharArray();
        int i = length % 30;
        int i2 = i == 0 ? 0 : 30 - i;
        int i3 = length / 30;
        if (i2 != 0) {
            i3++;
        }
        char charAt = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".charAt(iArr[0] & 63);
        cpw.c(false, "ProbeReguestUtil", "charCrc ", Character.valueOf(charAt));
        if (i3 > 6) {
            return null;
        }
        if (i2 > 0) {
            int i4 = i2 + length;
            iArr2 = new int[i4];
            for (int i5 = 0; i5 < length; i5++) {
                iArr2[i5] = charArray[i5];
            }
            int i6 = length + 1;
            Arrays.fill(iArr2, length, i6, 32);
            Arrays.fill(iArr2, i6, i4, 35);
        } else {
            iArr2 = new int[length];
            for (int i7 = 0; i7 < length; i7++) {
                iArr2[i7] = charArray[i7];
            }
        }
        int[][] iArr3 = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, i3, 30);
        for (int i8 = 0; i8 < i3; i8++) {
            int[] iArr4 = iArr3[i8];
            iArr4[0] = e[i8];
            iArr4[1] = charAt;
            for (int i9 = 2; i9 < 30; i9++) {
                int i10 = ((i8 * 30) + i9) - 2;
                if (i10 < iArr2.length) {
                    iArr3[i8][i9] = iArr2[i10];
                }
            }
        }
        return iArr3;
    }

    public static WifiConfiguration LW_(String str) {
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.allowedAuthAlgorithms.clear();
        wifiConfiguration.allowedGroupCiphers.clear();
        wifiConfiguration.allowedKeyManagement.clear();
        wifiConfiguration.allowedPairwiseCiphers.clear();
        wifiConfiguration.allowedProtocols.clear();
        wifiConfiguration.SSID = "\"" + str + "\"";
        wifiConfiguration.hiddenSSID = true;
        wifiConfiguration.allowedKeyManagement.set(0);
        wifiConfiguration.status = 2;
        return wifiConfiguration;
    }

    public static boolean c(Context context) {
        List<WifiConfiguration> list;
        boolean z;
        if (context == null) {
            cpw.e(false, "ProbeReguestUtil", "restoreWifiConfig context is null");
            return false;
        }
        WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
        try {
            list = wifiManager.getConfiguredNetworks();
        } catch (Exception unused) {
            cpw.e(false, "ProbeReguestUtil", "restoreWifiConfig getConfiguredNetworks Exception");
            list = null;
        }
        if (list == null) {
            cpw.a(false, "ProbeReguestUtil", "restoreWifiConfig configs is null!");
            return false;
        }
        boolean z2 = true;
        for (WifiConfiguration wifiConfiguration : list) {
            if (wifiConfiguration.SSID == null) {
                cpw.d(false, "ProbeReguestUtil", "restoreWifiConfig ssid is null!");
            } else {
                String a2 = cub.a(wifiConfiguration.SSID);
                if (a2 != null && a2.length() != 0) {
                    int[] iArr = e;
                    int length = iArr.length;
                    int i = 0;
                    while (true) {
                        if (i >= length) {
                            z = false;
                            break;
                        }
                        if (a2.charAt(0) == ((char) iArr[i])) {
                            z = true;
                            break;
                        }
                        i++;
                    }
                    boolean z3 = wifiConfiguration.hiddenSSID && a2.length() == 30;
                    if (z && z3) {
                        cpw.c(false, "ProbeReguestUtil", "restoreWifiConfig ", wifiConfiguration);
                        int i2 = wifiConfiguration.networkId;
                        boolean removeNetwork = wifiManager.removeNetwork(i2);
                        cpw.a(false, "ProbeReguestUtil", "networkId ", Integer.valueOf(i2), " remove  ", Boolean.valueOf(removeNetwork), " disable ", Boolean.valueOf(removeNetwork), "saveConfig ", Boolean.valueOf(wifiManager.saveConfiguration()));
                        if (z2 && !removeNetwork) {
                            z2 = false;
                        }
                    }
                }
            }
        }
        return z2;
    }
}
