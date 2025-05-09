package com.huawei.hms.hihealth.options;

import com.huawei.hms.hihealth.data.SamplePoint;
import java.util.List;

/* loaded from: classes9.dex */
public interface OnActivityRecordListener {
    default void onReceive(List<SamplePoint> list) {
    }

    void onStatusChange(int i);
}
