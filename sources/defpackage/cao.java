package defpackage;

import android.content.Context;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.UnitUtil;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class cao {
    public static void a(Context context, int i, boolean z, boolean z2, int i2) {
        if (context == null) {
            LogUtil.h("Track_SportAutoTrackCommonUtils", "setAutoTrackConfigInfoBi context is null");
            return;
        }
        if (i == 0) {
            LogUtil.h("Track_SportAutoTrackCommonUtils", "setAutoTrackConfigInfoBi launchSourceType is error");
            return;
        }
        HashMap hashMap = new HashMap(5);
        hashMap.put("click", 1);
        hashMap.put("from", Integer.valueOf(i));
        if (z) {
            hashMap.put("event", Integer.valueOf(z2 ? 1 : 2));
        } else {
            hashMap.put("event", 3);
        }
        if (z2) {
            hashMap.put("distance", Integer.valueOf(i2));
        }
        hashMap.put("units", UnitUtil.h() ? "mile" : "kilometer");
        ixx.d().d(context, AnalyticsValue.HEALTH_AUTO_TRACK_TOGGLE_1040073.value(), hashMap, 0);
    }
}
