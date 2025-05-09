package defpackage;

import android.net.wifi.WifiManager;
import android.text.TextUtils;
import com.huawei.android.net.wifi.NfcWifiManagerEx;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class kfw {
    public static String c(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("WifiConfigurationUtil", "getConnectionWifiPassword() ssid is error");
            return "";
        }
        String a2 = a();
        if (TextUtils.isEmpty(a2)) {
            LogUtil.h("WifiConfigurationUtil", "getConnectionWifiPassword() wifi information is empty");
            return "";
        }
        if (!a2.contains(str)) {
            return "";
        }
        String d = d(a2);
        return d.substring(d.indexOf(str) + str.length());
    }

    private static String d(String str) {
        if (str.length() <= 2) {
            return str;
        }
        int indexOf = str.indexOf(":");
        LogUtil.a("WifiConfigurationUtil", "dealWifiPassword() index first: ", Integer.valueOf(indexOf));
        if (indexOf != -1) {
            int indexOf2 = str.indexOf(":", indexOf + 1);
            LogUtil.a("WifiConfigurationUtil", "dealWifiPassword() index second: ", Integer.valueOf(indexOf2));
            if (indexOf2 != -1) {
                String substring = str.substring(indexOf2);
                LogUtil.a("WifiConfigurationUtil", "dealWifiPassword() second index find");
                return substring;
            }
            String substring2 = str.substring(str.indexOf(":"));
            LogUtil.a("WifiConfigurationUtil", "dealWifiPassword() second index not find");
            return substring2;
        }
        LogUtil.a("WifiConfigurationUtil", "dealWifiPassword() first index not find");
        return str;
    }

    public static String a() {
        String wpaSuppConfig = NfcWifiManagerEx.getWpaSuppConfig((WifiManager) null);
        if (wpaSuppConfig != null) {
            return wpaSuppConfig;
        }
        LogUtil.h("WifiConfigurationUtil", "getWifiConfigurationInformation() wifi information is empty");
        return "";
    }
}
