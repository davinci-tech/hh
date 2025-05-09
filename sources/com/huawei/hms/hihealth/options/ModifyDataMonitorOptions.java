package com.huawei.hms.hihealth.options;

import android.app.PendingIntent;
import android.os.Parcelable;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.huawei.hms.common.internal.Objects;
import com.huawei.hms.common.internal.Preconditions;
import com.huawei.hms.health.aabq;
import com.huawei.hms.health.aabv;
import com.huawei.hms.health.aabw;
import com.huawei.hms.health.aaby;
import com.huawei.hms.hihealth.data.DataCollector;
import com.huawei.hms.hihealth.data.DataType;

/* loaded from: classes9.dex */
public class ModifyDataMonitorOptions extends aabq {
    public static final Parcelable.Creator<ModifyDataMonitorOptions> CREATOR = new aabq.aab(ModifyDataMonitorOptions.class);

    @aaby(id = 1)
    private final PendingIntent aab;

    @aaby(id = 2)
    private DataType aaba;

    @aaby(id = 3)
    private DataCollector aabb;

    public static class Builder {
        private PendingIntent aab;
        private DataType aaba;
        private DataCollector aabb;

        public Builder setModifyDataType(DataType dataType) {
            Preconditions.checkNotNull(dataType);
            this.aaba = dataType;
            return this;
        }

        public Builder setModifyDataIntent(PendingIntent pendingIntent) {
            Preconditions.checkNotNull(pendingIntent);
            this.aab = pendingIntent;
            return this;
        }

        public Builder setModifyDataCollector(DataCollector dataCollector) throws NullPointerException {
            Preconditions.checkNotNull(dataCollector);
            this.aabb = dataCollector;
            return this;
        }

        public ModifyDataMonitorOptions build() {
            Preconditions.checkState((this.aabb == null && this.aaba == null) ? false : true, "Set at least one of DataCollector or DataType");
            Preconditions.checkNotNull(this.aab, "Must set the PendingIntent");
            return new ModifyDataMonitorOptions(this);
        }
    }

    public String toString() {
        return Objects.toStringHelper(this).add(BaseGmsClient.KEY_PENDING_INTENT, this.aab).add("dataType", this.aaba).add("dataCollector", this.aabb).toString();
    }

    public int hashCode() {
        return Objects.hashCode(this.aab, this.aaba, this.aabb);
    }

    public DataType getModifyDataType() {
        return this.aaba;
    }

    public PendingIntent getModifyDataIntent() {
        return this.aab;
    }

    public DataCollector getModifyDataCollector() {
        return this.aabb;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ModifyDataMonitorOptions)) {
            return false;
        }
        ModifyDataMonitorOptions modifyDataMonitorOptions = (ModifyDataMonitorOptions) obj;
        return Objects.equal(this.aab, modifyDataMonitorOptions.aab) && Objects.equal(this.aabb, modifyDataMonitorOptions.aabb) && Objects.equal(this.aaba, modifyDataMonitorOptions.aaba);
    }

    @aabw
    private ModifyDataMonitorOptions(@aabv(id = 1) PendingIntent pendingIntent, @aabv(id = 2) DataType dataType, @aabv(id = 3) DataCollector dataCollector) {
        this.aabb = dataCollector;
        this.aaba = dataType;
        this.aab = pendingIntent;
    }
}
