package defpackage;

import android.util.Pair;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
public class dta {
    public static Pair<Integer, Integer> ZO_(int i, int i2, Pair<Float, Float> pair, String str) {
        Pair<Integer, Integer> pair2;
        if (pair == null) {
            LogUtil.h("HRControl_BaseHeartRateControl", "getCourseZoneHeartRateScope courseZoneScope is null");
            return new Pair<>(0, 0);
        }
        LogUtil.a("HRControl_BaseHeartRateControl", "getCourseZoneHeartRateScope restHeartRate = ", Integer.valueOf(i), ", maxHeartRate = ", Integer.valueOf(i2), ", courseZoneScope = ", pair.toString(), ", courseZoneScopeType = ", str);
        float f = i;
        float f2 = i2 - i;
        int floatValue = (int) ((((Float) pair.first).floatValue() * f2) + f);
        int floatValue2 = (int) (f + (f2 * ((Float) pair.second).floatValue()));
        int i3 = floatValue2 - floatValue;
        if (i3 > 25 || i3 < 10) {
            int i4 = (floatValue2 + floatValue) / 2;
            pair2 = new Pair<>(Integer.valueOf(i4 - 10), Integer.valueOf(i4 + 10));
        } else {
            pair2 = new Pair<>(Integer.valueOf(floatValue), Integer.valueOf(floatValue2));
        }
        LogUtil.a("HRControl_BaseHeartRateControl", "getCourseZoneHeartRateScope courseZoneHeartRateScope = ", pair2.toString());
        return pair2;
    }

    public static int ZP_(Pair<Integer, Integer> pair) {
        if (pair == null) {
            LogUtil.h("HRControl_BaseHeartRateControl", "getCourseZoneTargetHeartRate courseZoneHeartRateScope is null");
            return 0;
        }
        LogUtil.a("HRControl_BaseHeartRateControl", "getCourseZoneTargetHeartRate courseZoneHeartRateScope = ", pair.toString());
        return (((Integer) pair.first).intValue() + ((Integer) pair.second).intValue()) / 2;
    }
}
