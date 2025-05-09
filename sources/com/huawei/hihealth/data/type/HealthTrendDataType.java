package com.huawei.hihealth.data.type;

import com.huawei.operation.ble.BleConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class HealthTrendDataType {
    private static final List<String> b;

    static {
        ArrayList arrayList = new ArrayList();
        b = arrayList;
        arrayList.add("RestingHeartRate");
        arrayList.add(BleConstants.BLOOD_OXYGEN);
        arrayList.add("Stress");
        arrayList.add("AllSleepTime");
        arrayList.add("Step");
        arrayList.add("ExerciseIntensity");
        arrayList.add("Calorie");
        arrayList.add("ActiveHour");
    }

    private HealthTrendDataType() {
    }

    public static List<String> e() {
        return Collections.unmodifiableList(b);
    }
}
