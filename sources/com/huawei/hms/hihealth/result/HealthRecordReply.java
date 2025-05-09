package com.huawei.hms.hihealth.result;

import com.huawei.hms.hihealth.data.HealthRecord;
import java.util.List;

/* loaded from: classes9.dex */
public class HealthRecordReply extends aab<HealthRecordResult> {
    public List<HealthRecord> getHealthRecords() {
        return getResult().getHealthRecords();
    }
}
