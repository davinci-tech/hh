package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.operation.ble.BleConstants;
import java.util.HashMap;

/* loaded from: classes4.dex */
public class feq {
    private static int c;

    public static void d(int i) {
        c = i;
    }

    public static int b() {
        return c;
    }

    public static void c(String str) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        hashMap.put(BleConstants.SPORT_TYPE, str);
        ixx.d().d(BaseApplication.e(), AnalyticsValue.VUI_2010093.value(), hashMap, 0);
    }
}
