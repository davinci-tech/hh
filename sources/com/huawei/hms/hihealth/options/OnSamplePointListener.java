package com.huawei.hms.hihealth.options;

import com.huawei.hms.hihealth.data.SamplePoint;

/* loaded from: classes9.dex */
public interface OnSamplePointListener {
    default void onException(int i, String str) {
    }

    void onSamplePoint(SamplePoint samplePoint);
}
