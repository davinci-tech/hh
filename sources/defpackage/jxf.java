package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class jxf {
    private static final Object e = new Object();
    private static Map<String, ArrayList<Long>> b = new ConcurrentHashMap(16);

    public static boolean e(String str) {
        synchronized (e) {
            if (TextUtils.isEmpty(str)) {
                LogUtil.h("DataSyncPowerConsumptionUtil", "isAllowReconnect deviceIdentify is empty.");
                return false;
            }
            ArrayList<Long> arrayList = b.get(str);
            if (arrayList == null) {
                LogUtil.h("DataSyncPowerConsumptionUtil", "isAllowReconnect list is null.");
                return true;
            }
            long currentTimeMillis = System.currentTimeMillis();
            Iterator<Long> it = arrayList.iterator();
            while (it.hasNext()) {
                if (currentTimeMillis - it.next().longValue() > 1800000) {
                    it.remove();
                }
            }
            if (arrayList.size() <= 5) {
                return true;
            }
            ReleaseLogUtil.d("DEVMGR_DataSyncPowerConsumptionUtil", "isAllowReconnect remainConnectCount is more than 5.");
            return false;
        }
    }

    public static void c(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DataSyncPowerConsumptionUtil", "updateAbnormalDeviceInfo deviceIdentify is empty.");
        } else {
            b(str);
        }
    }

    private static void b(String str) {
        LogUtil.a("DataSyncPowerConsumptionUtil", "addDeviceInfo deviceIdentify: ", blt.a(str));
        if (!b.containsKey(str)) {
            LogUtil.h("DataSyncPowerConsumptionUtil", "addDeviceInfo deviceInfo is not exist.");
            ArrayList<Long> arrayList = new ArrayList<>(16);
            arrayList.add(Long.valueOf(System.currentTimeMillis()));
            b.put(str, arrayList);
            return;
        }
        ArrayList<Long> arrayList2 = b.get(str);
        if (arrayList2 != null) {
            arrayList2.add(Long.valueOf(System.currentTimeMillis()));
            b.put(str, arrayList2);
            LogUtil.h("DataSyncPowerConsumptionUtil", "addDeviceInfo deviceInfo is exist:", Integer.valueOf(arrayList2.size()));
        }
    }
}
