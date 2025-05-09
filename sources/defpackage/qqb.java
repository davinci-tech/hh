package defpackage;

import android.content.Context;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.HashMap;

/* loaded from: classes7.dex */
public class qqb {
    public static void b(int i) {
        Context e = BaseApplication.e();
        HashMap hashMap = new HashMap(16);
        if (i == 1) {
            hashMap.put("click", "1");
            hashMap.put("from", "1");
            ixx.d().d(e, AnalyticsValue.HEALTH_HEALTH_BLOODPRESS_DETAIL_RECORD_2030020.value(), hashMap, 0);
            return;
        }
        if (i == 2) {
            hashMap.put("click", "1");
            hashMap.put("from", "1");
            ixx.d().d(e, AnalyticsValue.HEALTH_HEALTH_BLOODPRESS_DETAIL_MEASURE_2030023.value(), hashMap, 0);
            return;
        }
        if (i == 3) {
            String value = AnalyticsValue.BLOOD_PRESSURE_DETAIL_MEASURE_2040205.value();
            hashMap.put("click", "1");
            hashMap.put("from", "1");
            hashMap.put("type", 2);
            ixx.d().d(e, value, hashMap, 0);
            return;
        }
        if (i == 4) {
            String value2 = AnalyticsValue.BLOOD_PRESSURE_DETAIL_MEASURE_2040205.value();
            hashMap.put("click", "1");
            hashMap.put("from", "1");
            hashMap.put("type", 1);
            ixx.d().d(e, value2, hashMap, 0);
            return;
        }
        LogUtil.a("BloodPressureBiUtils", "no eventId match");
    }
}
