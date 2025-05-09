package com.huawei.hms.hihealth.options;

import android.os.Parcelable;
import com.huawei.hms.common.internal.Objects;
import com.huawei.hms.health.aabq;
import com.huawei.hms.health.aabv;
import com.huawei.hms.health.aabw;
import com.huawei.hms.health.aaby;
import com.huawei.hms.hihealth.data.DataType;
import java.util.List;

/* loaded from: classes9.dex */
public class DataCollectorsOptions extends aabq {
    public static final Parcelable.Creator<DataCollectorsOptions> CREATOR = new aabq.aab(DataCollectorsOptions.class);

    @aaby(id = 1)
    private final List<DataType> aab;

    @aaby(id = 2)
    private final List<Integer> aaba;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    /* synthetic */ DataCollectorsOptions(com.huawei.hms.hihealth.options.DataCollectorsOptions.Builder r5, com.huawei.hms.hihealth.options.DataCollectorsOptions.aab r6) {
        /*
            r4 = this;
            java.util.ArrayList r6 = new java.util.ArrayList
            com.huawei.hms.hihealth.data.DataType[] r0 = com.huawei.hms.hihealth.options.DataCollectorsOptions.Builder.aab(r5)
            java.util.List r0 = java.util.Arrays.asList(r0)
            r6.<init>(r0)
            int[] r5 = com.huawei.hms.hihealth.options.DataCollectorsOptions.Builder.aaba(r5)
            java.util.ArrayList r0 = new java.util.ArrayList
            int r1 = r5.length
            r0.<init>(r1)
            int r1 = r5.length
            r2 = 0
        L19:
            if (r2 >= r1) goto L27
            r3 = r5[r2]
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r0.add(r3)
            int r2 = r2 + 1
            goto L19
        L27:
            r4.<init>(r6, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.hihealth.options.DataCollectorsOptions.<init>(com.huawei.hms.hihealth.options.DataCollectorsOptions$Builder, com.huawei.hms.hihealth.options.DataCollectorsOptions$aab):void");
    }

    public static class Builder {
        private DataType[] aab = new DataType[0];
        private int[] aaba = {0, 1};

        public Builder setDataTypes(DataType... dataTypeArr) {
            this.aab = dataTypeArr;
            return this;
        }

        public Builder setDataCollectorTypes(int... iArr) {
            this.aaba = iArr;
            return this;
        }

        public DataCollectorsOptions build() {
            return new DataCollectorsOptions(this, (aab) null);
        }
    }

    public String toString() {
        return Objects.toStringHelper(this).add("dataTypes", this.aab).add("dataCollectorTypes", this.aaba).toString();
    }

    public List<DataType> getDataTypes() {
        return this.aab;
    }

    public List<Integer> getDataCollectorTypes() {
        return this.aaba;
    }

    @aabw
    private DataCollectorsOptions(@aabv(id = 1) List<DataType> list, @aabv(id = 2) List<Integer> list2) {
        this.aab = list;
        this.aaba = list2;
    }
}
