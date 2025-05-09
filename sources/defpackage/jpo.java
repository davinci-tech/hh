package defpackage;

import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import health.compact.a.KeyValDbManager;
import health.compact.a.util.LogUtil;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class jpo {
    private static ConcurrentHashMap<String, Integer> e = new ConcurrentHashMap<>(16);

    public static boolean e(String str, cvc cvcVar) {
        boolean z = false;
        if (TextUtils.isEmpty(str)) {
            LogUtil.c("DevicePairModeUtils", "deviceName is empty");
            return false;
        }
        if (cvcVar == null || cvcVar.f() == null) {
            LogUtil.c("DevicePairModeUtils", "wearDeviceInfo is null");
            return false;
        }
        String be = cvcVar.f().be();
        LogUtil.d("DevicePairModeUtils", "pairModelBluetoothName: ", be);
        String[] split = be.split(",");
        int length = split.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            String str2 = split[i];
            if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str2.trim()) && str.toUpperCase(Locale.ENGLISH).contains(str2.trim().toUpperCase(Locale.ENGLISH))) {
                z = true;
                break;
            }
            i++;
        }
        LogUtil.d("DevicePairModeUtils", "isSupportPairMode: ", Boolean.valueOf(z));
        return z;
    }

    public static int c(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.c("DevicePairModeUtils", "getPairMode deviceIdentify is empty.");
            return -1;
        }
        if (e.get(str) != null) {
            return e.get(str).intValue();
        }
        int b = b(str);
        e.put(str, Integer.valueOf(b));
        return b;
    }

    public static void a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.c("DevicePairModeUtils", "setPairMode deviceIdentify is empty.");
        } else {
            e.put(str, Integer.valueOf(i));
            d(str, i);
        }
    }

    private static void d(String str, int i) {
        String str2 = "pair_mode_key" + knl.a(str);
        LogUtil.d("DevicePairModeUtils", "pairMode: ", Integer.valueOf(i), " pairMode saveKey: ", str2);
        KeyValDbManager.b(BaseApplication.getContext()).e(str2, String.valueOf(i));
    }

    private static int b(String str) {
        String str2 = "pair_mode_key" + knl.a(str);
        LogUtil.d("DevicePairModeUtils", "getDevicePairMode queryKey: ", str2);
        String e2 = KeyValDbManager.b(BaseApplication.getContext()).e(str2);
        if (TextUtils.isEmpty(e2)) {
            LogUtil.c("DevicePairModeUtils", "getDevicePairMode queryValue is empty.");
            return -1;
        }
        try {
            return Integer.parseInt(e2);
        } catch (NumberFormatException unused) {
            LogUtil.e("DevicePairModeUtils", "getDevicePairMode occur NumberFormatException");
            return -1;
        }
    }
}
