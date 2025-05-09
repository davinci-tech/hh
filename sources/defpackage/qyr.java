package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public class qyr {
    private static void b(AnalyticsValue analyticsValue, Map<String, Object> map) {
        if (analyticsValue == null || map == null) {
            LogUtil.h("R_WeightBiUtils", "setEvent analyticsValue ", analyticsValue, " map ", map);
        } else {
            map.put("click", 1);
            ixx.d().d(BaseApplication.e(), analyticsValue.value(), map, 0);
        }
    }

    public static void e() {
        b(AnalyticsValue.HEALTH_HEALTH_WEIGHT_DETAIL_BIND_2030014, new HashMap(1));
    }

    public static void d(int i) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("event", Integer.valueOf(i));
        b(AnalyticsValue.WEIGHT_PAGE_INPUT_DATA_2160122, hashMap);
    }

    public static void c(int i, int i2, String[] strArr) {
        HashMap hashMap = new HashMap(4);
        hashMap.put("type", String.valueOf(i));
        hashMap.put("from", Integer.valueOf(i2));
        hashMap.put("dataType", strArr);
        b(AnalyticsValue.HEALTH_HEALTH_WEIGHT_INPUT_2030017, hashMap);
    }
}
