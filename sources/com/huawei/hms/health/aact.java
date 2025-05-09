package com.huawei.hms.health;

import com.huawei.hms.hihealth.result.ActivityRecordReply;
import com.huawei.hms.hihealth.result.ActivityRecordResult;
import com.huawei.hms.support.api.client.Status;

/* loaded from: classes9.dex */
public final class aact<T> implements aacw<T> {
    private ActivityRecordReply aab = new ActivityRecordReply();

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.huawei.hms.health.aacw
    public com.huawei.hms.hihealth.result.aab aab(T t) {
        ActivityRecordResult activityRecordResult;
        ActivityRecordReply activityRecordReply;
        if (t instanceof ActivityRecordResult) {
            activityRecordReply = this.aab;
            activityRecordResult = (ActivityRecordResult) t;
        } else {
            activityRecordResult = ActivityRecordResult.getActivityRecordResult(t instanceof Exception ? aabz.aab(((Exception) t).getMessage()) : Status.FAILURE);
            activityRecordReply = this.aab;
        }
        activityRecordReply.setResult(activityRecordResult);
        return this.aab;
    }
}
