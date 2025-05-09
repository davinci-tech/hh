package com.huawei.hms.hihealth;

import com.huawei.hmf.tasks.Task;
import com.huawei.hms.health.aach;
import com.huawei.hms.hihealth.data.DataCollector;
import com.huawei.hms.hihealth.data.DataType;
import com.huawei.hms.hihealth.data.Record;
import com.huawei.hms.hihealth.options.OnSamplePointListener;
import com.huawei.hms.support.hwid.result.AuthHuaweiId;
import java.util.List;

/* loaded from: classes9.dex */
public class AutoRecorderController {
    private aabf aab;

    @Deprecated
    public Task<Void> stopRecord(Record record) {
        return ((aach) this.aab).aab(record);
    }

    public Task<Void> stopRecord(DataType dataType, OnSamplePointListener onSamplePointListener) {
        return ((aach) this.aab).aaba(dataType, onSamplePointListener);
    }

    @Deprecated
    public Task<Void> stopRecord(DataType dataType) {
        return ((aach) this.aab).aabb(dataType);
    }

    @Deprecated
    public Task<Void> stopRecord(DataCollector dataCollector) {
        return ((aach) this.aab).aaba(dataCollector);
    }

    public Task<Void> startRecord(DataType dataType, OnSamplePointListener onSamplePointListener) {
        return ((aach) this.aab).aab(dataType, onSamplePointListener);
    }

    @Deprecated
    public Task<Void> startRecord(DataType dataType) {
        return ((aach) this.aab).aaba(dataType);
    }

    @Deprecated
    public Task<Void> startRecord(DataCollector dataCollector) {
        return ((aach) this.aab).aab(dataCollector);
    }

    @Deprecated
    public Task<List<Record>> getRecords(DataType dataType) {
        return ((aach) this.aab).aab(dataType);
    }

    @Deprecated
    public Task<List<Record>> getRecords() {
        return ((aach) this.aab).aab();
    }

    @Deprecated
    public AutoRecorderController(AuthHuaweiId authHuaweiId) {
        this.aab = null;
        this.aab = aach.aaba();
    }

    AutoRecorderController() {
        this.aab = null;
        this.aab = aach.aaba();
    }
}
