package com.huawei.ui.main.stories.fitness.views.heartrate.unixtimelistview.utils;

import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes6.dex */
public class AggregateOptionBuilder {
    private long b;
    private long d;

    public enum DataGroupUnit {
        NONE,
        DAY,
        WEEK,
        MONTH,
        YEAR,
        ALL
    }

    public AggregateOptionBuilder c(long j, long j2) {
        this.d = j;
        this.b = j2;
        return this;
    }

    public HiAggregateOption a(DataGroupUnit dataGroupUnit, String str, int i) {
        int i2;
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(this.d);
        hiAggregateOption.setEndTime(this.b);
        int i3 = 3;
        hiAggregateOption.setAggregateType(3);
        if (str.contains("_DETAIL") || (i2 = AnonymousClass4.b[dataGroupUnit.ordinal()]) == 1) {
            i3 = 0;
        } else if (i2 != 2) {
            if (i2 == 3) {
                b(hiAggregateOption, str);
                i3 = 5;
            } else if (i2 == 4) {
                b(hiAggregateOption, str);
                i3 = 6;
            } else {
                i3 = -1;
                LogUtil.h("AggregateOptionBuilder", "userViewSelf not support,uniteType: ", -1);
            }
        }
        hiAggregateOption.setConstantsKey(new String[]{str});
        hiAggregateOption.setType(new int[]{i});
        hiAggregateOption.setGroupUnitType(i3);
        hiAggregateOption.setReadType(0);
        return hiAggregateOption;
    }

    /* renamed from: com.huawei.ui.main.stories.fitness.views.heartrate.unixtimelistview.utils.AggregateOptionBuilder$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[DataGroupUnit.values().length];
            b = iArr;
            try {
                iArr[DataGroupUnit.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[DataGroupUnit.DAY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[DataGroupUnit.MONTH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[DataGroupUnit.YEAR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private void b(HiAggregateOption hiAggregateOption, String str) {
        if ("HR_NORMAL_MAX".equals(str) || "HR_WARNING_MAX".equals(str) || "BRADYCARDIA_MAX".equals(str)) {
            hiAggregateOption.setAggregateType(4);
        } else if ("HR_NORMAL_MIN".equals(str) || "HR_WARNING_MIN".equals(str) || "BRADYCARDIA_MIN".equals(str)) {
            hiAggregateOption.setAggregateType(5);
        } else {
            LogUtil.h("AggregateOptionBuilder", "YearAndMonth constKey = ", str);
        }
    }
}
