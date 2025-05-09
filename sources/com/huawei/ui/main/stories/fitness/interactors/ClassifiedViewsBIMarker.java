package com.huawei.ui.main.stories.fitness.interactors;

import android.app.Activity;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.ui.main.stories.utils.FitnessUtils;
import defpackage.ixx;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class ClassifiedViewsBIMarker {

    public enum Type {
        DayView,
        WeekView,
        MonthView,
        YearView
    }

    public static void duw_(Activity activity, Type type) {
        HashMap hashMap = new HashMap();
        String d = FitnessUtils.d(activity.getClass().getSimpleName());
        hashMap.put("click", "1");
        hashMap.put("activityName", d);
        if ("Step".equals(d) || "Calorie".equals(d) || "Distance".equals(d) || "Climb".equals(d)) {
            int i = AnonymousClass2.d[type.ordinal()];
            if (i == 1) {
                ixx.d().d(activity, AnalyticsValue.HEALTH_DETAIL_DAY_TAB_21300001.value(), hashMap, 0);
                return;
            }
            if (i == 2) {
                ixx.d().d(activity, AnalyticsValue.HEALTH_DETAIL_WEEK_TAB_21300002.value(), hashMap, 0);
            } else if (i == 3) {
                ixx.d().d(activity, AnalyticsValue.HEALTH_DETAIL_MONTH_TAB_21300003.value(), hashMap, 0);
            } else {
                if (i == 4) {
                    ixx.d().d(activity, AnalyticsValue.HEALTH_DETAIL_YEAR_TAB_21300004.value(), hashMap, 0);
                    return;
                }
                throw new RuntimeException("UnKnownBI Point");
            }
        }
    }

    /* renamed from: com.huawei.ui.main.stories.fitness.interactors.ClassifiedViewsBIMarker$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] d;

        static {
            int[] iArr = new int[Type.values().length];
            d = iArr;
            try {
                iArr[Type.DayView.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                d[Type.WeekView.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                d[Type.MonthView.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                d[Type.YearView.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }
}
