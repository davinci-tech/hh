package com.huawei.hms.hihealth;

import android.app.PendingIntent;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.health.aabz;
import com.huawei.hms.health.aacl;
import com.huawei.hms.health.aacu;
import com.huawei.hms.hihealth.data.DataCollector;
import com.huawei.hms.hihealth.data.DataType;
import com.huawei.hms.hihealth.data.SamplePoint;
import com.huawei.hms.hihealth.data.SampleSet;
import com.huawei.hms.hihealth.options.DataCollectorsOptions;
import com.huawei.hms.hihealth.options.DeleteOptions;
import com.huawei.hms.hihealth.options.ModifyDataMonitorOptions;
import com.huawei.hms.hihealth.options.ReadOptions;
import com.huawei.hms.hihealth.options.UpdateOptions;
import com.huawei.hms.hihealth.result.ReadReply;
import com.huawei.hms.support.hwid.result.AuthHuaweiId;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class DataController {
    private aabm aab;

    public Task<Void> update(UpdateOptions updateOptions) {
        return ((aacl) this.aab).aab(updateOptions);
    }

    @Deprecated
    public Task<Void> unregisterModifyDataMonitor(PendingIntent pendingIntent) {
        ((aacl) this.aab).aab(pendingIntent);
        throw null;
    }

    @Deprecated
    public Task<Void> syncAll() {
        return ((aacl) this.aab).aaba();
    }

    @Deprecated
    public Task<Void> registerModifyDataMonitor(ModifyDataMonitorOptions modifyDataMonitorOptions) {
        ((aacl) this.aab).aab(modifyDataMonitorOptions);
        throw null;
    }

    @Deprecated
    public Task<SampleSet> readTodaySummationFromDevice(DataType dataType) {
        ((aacl) this.aab).aaba(dataType);
        throw null;
    }

    public Task<List<SampleSet>> readTodaySummation(List<DataType> list) {
        return ((aacl) this.aab).aaba(list);
    }

    public Task<SampleSet> readTodaySummation(DataType dataType) {
        return ((aacl) this.aab).aab(dataType);
    }

    public Task<Map<DataType, SamplePoint>> readLatestData(List<DataType> list) {
        return ((aacl) this.aab).aab(list);
    }

    public Task<List<SampleSet>> readDailySummation(List<DataType> list, int i, int i2) {
        return ((aacl) this.aab).aab(list, i, i2);
    }

    public Task<SampleSet> readDailySummation(DataType dataType, int i, int i2) {
        return ((aacl) this.aab).aab(dataType, i, i2);
    }

    public Task<ReadReply> read(ReadOptions readOptions) {
        return aabz.aab(((aacl) this.aab).aab(readOptions), new aacu());
    }

    public Task<Void> insert(SampleSet sampleSet) {
        return ((aacl) this.aab).aab(sampleSet);
    }

    @Deprecated
    public Task<List<DataCollector>> getDataCollectors(DataCollectorsOptions dataCollectorsOptions) {
        ((aacl) this.aab).aab(dataCollectorsOptions);
        throw null;
    }

    public Task<Void> delete(DeleteOptions deleteOptions) {
        return ((aacl) this.aab).aab(deleteOptions);
    }

    public Task<Void> clearAll() {
        return ((aacl) this.aab).aab();
    }

    @Deprecated
    public DataController(AuthHuaweiId authHuaweiId) {
        this.aab = null;
        this.aab = aacl.aabb();
    }

    DataController() {
        this.aab = null;
        this.aab = aacl.aabb();
    }
}
