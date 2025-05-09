package com.huawei.hihealth.device.open.data;

import com.huawei.hihealth.device.indoor.MeasureResultImpl;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class MeasureResultBuilder {
    private MeasureResultBuilder() {
    }

    public static MeasureResult prepareResult(ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        return new MeasureResultImpl(arrayList, arrayList2);
    }
}
