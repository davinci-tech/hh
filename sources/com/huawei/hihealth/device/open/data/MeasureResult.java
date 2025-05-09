package com.huawei.hihealth.device.open.data;

import java.util.ArrayList;

/* loaded from: classes.dex */
public interface MeasureResult {
    MeasureRecord createAndJoinRecord();

    MeasureRecord createRecord();

    ArrayList<MeasureRecord> getRecords();

    void joinRecord(MeasureRecord measureRecord);
}
