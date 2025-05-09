package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hihealth.util.ReleaseLogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.OpAnalyticsUtil;
import health.compact.a.CommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.HuaweiHealth;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class ivz {

    /* renamed from: a, reason: collision with root package name */
    private static final Integer f13635a = 100;
    private static String b = "lastSetOpEventDate";
    private static Context c;
    private final HashMap<String, Integer> d;

    static class a {
        private static final ivz c = new ivz();
    }

    public static ivz d(Context context) {
        if (context != null) {
            c = context.getApplicationContext();
        }
        return a.c;
    }

    private ivz() {
        this.d = new HashMap<>(16);
    }

    public void e(Context context, String str, Map<String, Object> map, int i) {
        if (CommonUtil.as()) {
            String accountInfo = LoginInit.getInstance(context).getAccountInfo(1011);
            if (!TextUtils.isEmpty(accountInfo) && !HuaweiHealth.b().equals(accountInfo) && accountInfo.length() > 4) {
                int length = accountInfo.length();
                int i2 = length / 3;
                String substring = accountInfo.substring(0, i2);
                int i3 = length / 2;
                String substring2 = accountInfo.substring(i2, i3);
                String substring3 = accountInfo.substring(i3, length);
                map.put("HF", substring);
                map.put("HS", substring2);
                map.put("HT", substring3);
            }
        }
        ixx.d().d(context, str, map, i);
    }

    public void e(String str, LinkedHashMap<String, String> linkedHashMap, boolean z) {
        if (CommonUtil.as()) {
            String accountInfo = LoginInit.getInstance(c).getAccountInfo(1011);
            if (!TextUtils.isEmpty(accountInfo) && !HuaweiHealth.b().equals(accountInfo) && accountInfo.length() > 4) {
                int length = accountInfo.length();
                int i = length / 3;
                String substring = accountInfo.substring(0, i);
                int i2 = length / 2;
                String substring2 = accountInfo.substring(i, i2);
                String substring3 = accountInfo.substring(i2, length);
                linkedHashMap.put("HF", substring);
                linkedHashMap.put("HS", substring2);
                linkedHashMap.put("HT", substring3);
            }
            if (!Utils.o()) {
                linkedHashMap.put(OpAnalyticsConstants.DEVICE_SERIAL_NUMBER, CommonUtil.i());
            }
        }
        if (z) {
            OpAnalyticsUtil.getInstance().setEventWithReportImmediately(str, linkedHashMap);
        } else {
            OpAnalyticsUtil.getInstance().setEvent(str, linkedHashMap);
        }
    }

    public void c(String str, LinkedHashMap<String, String> linkedHashMap, boolean z) {
        try {
            if (d(str + linkedHashMap.hashCode())) {
                return;
            }
            e(str, linkedHashMap, z);
        } catch (Exception e) {
            ReleaseLogUtil.c("Debug_HiOpAnalyticsUtil", "limitSetOpEvent Exception! ", e.getMessage());
        }
    }

    private boolean d(String str) {
        int c2 = HiDateUtil.c(System.currentTimeMillis());
        if (c2 != SharedPreferenceManager.a("HiHealthService", b, 0)) {
            SharedPreferenceManager.b("HiHealthService", b, c2);
            this.d.clear();
        }
        if (this.d.containsKey(str)) {
            Integer num = this.d.get(str);
            if (num.intValue() > f13635a.intValue()) {
                return true;
            }
            this.d.put(str, Integer.valueOf(num.intValue() + 1));
        } else {
            this.d.put(str, 1);
        }
        return false;
    }
}
