package defpackage;

import android.text.TextUtils;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes3.dex */
public class bmc {

    /* renamed from: a, reason: collision with root package name */
    private static Map<String, ArrayList<Long>> f434a = new ConcurrentHashMap(16);
    private static long b = 0;
    private static String d = "";

    public static boolean b(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            LogUtil.a("ReconnectPowerConsumptionUtil", "isAllowReconnect deviceIdentify is empty.");
            return false;
        }
        ArrayList<Long> arrayList = f434a.get(str2);
        if (arrayList == null) {
            LogUtil.a("ReconnectPowerConsumptionUtil", "isAllowReconnect list is null.");
            return true;
        }
        long currentTimeMillis = System.currentTimeMillis();
        synchronized (arrayList) {
            Iterator<Long> it = arrayList.iterator();
            while (it.hasNext()) {
                if (currentTimeMillis - it.next().longValue() > 3600000) {
                    it.remove();
                }
            }
        }
        if (arrayList.size() <= 20) {
            return true;
        }
        ReleaseLogUtil.a("DEVMGR_ReconnectPowerConsumptionUtil", "isAllowReconnect remainConnectCount is more than 20.");
        if (!TextUtils.isEmpty(str)) {
            bmw.e(100052, str, "", "");
        }
        return false;
    }

    public static void d(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("ReconnectPowerConsumptionUtil", "updateAbnormalDeviceInfo deviceIdentify is empty.");
            return;
        }
        if (i == 4 || i == 3) {
            b(str);
        } else if (i == 2) {
            f434a.remove(str);
        } else {
            LogUtil.a("ReconnectPowerConsumptionUtil", "updateAbnormalDeviceInfo other connect state no handle.");
        }
    }

    private static void b(String str) {
        LogUtil.c("ReconnectPowerConsumptionUtil", "addDeviceInfo deviceIdentify: ", blt.a(str));
        long currentTimeMillis = System.currentTimeMillis();
        if (str.equals(d) && currentTimeMillis - b < 500) {
            ReleaseLogUtil.a("ReconnectPowerConsumptionUtil", "The same device reports two statuses during a pairing process.");
            d = str;
            b = currentTimeMillis;
            return;
        }
        d = str;
        b = currentTimeMillis;
        if (!f434a.containsKey(str)) {
            LogUtil.a("ReconnectPowerConsumptionUtil", "addDeviceInfo deviceInfo is not exist.");
            ArrayList<Long> arrayList = new ArrayList<>(16);
            arrayList.add(Long.valueOf(System.currentTimeMillis()));
            f434a.put(str, arrayList);
            return;
        }
        ArrayList<Long> arrayList2 = f434a.get(str);
        if (arrayList2 != null) {
            synchronized (arrayList2) {
                arrayList2.add(Long.valueOf(System.currentTimeMillis()));
            }
            f434a.put(str, arrayList2);
            LogUtil.a("ReconnectPowerConsumptionUtil", "addDeviceInfo deviceInfo is exist:", Integer.valueOf(arrayList2.size()));
        }
    }
}
