package com.huawei.hms.health;

import com.huawei.hms.hihealth.result.HealthRecordReply;
import com.huawei.hms.hihealth.result.HealthRecordResult;
import com.huawei.hms.support.api.client.Status;

/* loaded from: classes9.dex */
public final class aacv<T> implements aacw<T> {
    private HealthRecordReply aab = new HealthRecordReply();

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.huawei.hms.health.aacw
    public com.huawei.hms.hihealth.result.aab aab(T t) {
        HealthRecordResult healthRecordResult;
        HealthRecordReply healthRecordReply;
        if (t instanceof HealthRecordResult) {
            healthRecordReply = this.aab;
            healthRecordResult = (HealthRecordResult) t;
        } else {
            healthRecordResult = HealthRecordResult.getHealthRecordResult(t instanceof Exception ? aabz.aab(((Exception) t).getMessage()) : Status.FAILURE);
            healthRecordReply = this.aab;
        }
        healthRecordReply.setResult(healthRecordResult);
        return this.aab;
    }
}
