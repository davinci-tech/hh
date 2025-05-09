package com.huawei.hms.hihealth.result;

import com.huawei.hms.hihealth.data.ActivityRecord;
import com.huawei.hms.hihealth.data.DataType;
import com.huawei.hms.hihealth.data.SampleSet;
import com.huawei.hms.support.api.client.Status;
import java.util.List;

/* loaded from: classes9.dex */
public class ActivityRecordReply extends aab<ActivityRecordResult> {
    public Status getStatus() {
        return getResult().getStatus();
    }

    public List<SampleSet> getSampleSet(ActivityRecord activityRecord, DataType dataType) {
        return getResult().getSampleSet(activityRecord, dataType);
    }

    public List<SampleSet> getSampleSet(ActivityRecord activityRecord) {
        return getResult().getSampleSet(activityRecord);
    }

    public List<ActivityRecord> getActivityRecords() {
        return getResult().getActivityRecords();
    }
}
