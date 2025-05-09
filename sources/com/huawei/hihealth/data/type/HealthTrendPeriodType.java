package com.huawei.hihealth.data.type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class HealthTrendPeriodType {
    private static final List<Integer> b;

    static {
        ArrayList arrayList = new ArrayList();
        b = arrayList;
        arrayList.add(1);
        arrayList.add(2);
    }

    private HealthTrendPeriodType() {
    }

    public static List<Integer> c() {
        return Collections.unmodifiableList(b);
    }
}
