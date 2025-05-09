package com.huawei.hms.hihealth.result;

import android.os.Parcelable;
import com.huawei.hms.common.internal.Objects;
import com.huawei.hms.health.aabr;
import com.huawei.hms.health.aabv;
import com.huawei.hms.health.aabw;
import com.huawei.hms.health.aaby;
import com.huawei.hms.hihealth.data.HealthRecord;
import com.huawei.hms.support.api.client.Status;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class HealthRecordResult extends aabr {
    public static final Parcelable.Creator<HealthRecordResult> CREATOR = new aabr.aab(HealthRecordResult.class);

    @aaby(id = 2)
    private final List<HealthRecord> mHealthRecords;

    @aaby(id = 1)
    private final Status mStatus;

    public String toString() {
        return Objects.toStringHelper(this).add("status", this.mStatus).add("healthRecords", this.mHealthRecords).toString();
    }

    public int hashCode() {
        return Objects.hashCode(this.mStatus, this.mHealthRecords);
    }

    @Override // com.huawei.hms.support.api.client.Result
    public Status getStatus() {
        return this.mStatus;
    }

    public List<HealthRecord> getHealthRecords() {
        return this.mHealthRecords;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HealthRecordResult)) {
            return false;
        }
        HealthRecordResult healthRecordResult = (HealthRecordResult) obj;
        return this.mStatus.equals(healthRecordResult.mStatus) && Objects.equal(this.mHealthRecords, healthRecordResult.mHealthRecords);
    }

    public static HealthRecordResult getHealthRecordResult(Status status) {
        return new HealthRecordResult(status, new ArrayList());
    }

    @aabw
    public HealthRecordResult(@aabv(id = 1) Status status, @aabv(id = 2) List<HealthRecord> list) {
        this.mStatus = status;
        this.mHealthRecords = list;
    }
}
