package com.huawei.hms.hihealth;

import com.huawei.hmf.tasks.Task;
import com.huawei.hms.health.aabz;
import com.huawei.hms.health.aacm;
import com.huawei.hms.health.aacv;
import com.huawei.hms.hihealth.options.HealthRecordDeleteOptions;
import com.huawei.hms.hihealth.options.HealthRecordInsertOptions;
import com.huawei.hms.hihealth.options.HealthRecordReadOptions;
import com.huawei.hms.hihealth.options.HealthRecordUpdateOptions;
import com.huawei.hms.hihealth.result.HealthRecordReply;

/* loaded from: classes9.dex */
public class HealthRecordController {
    private aacm aab;

    public Task<Void> updateHealthRecord(HealthRecordUpdateOptions healthRecordUpdateOptions) {
        return this.aab.aab(healthRecordUpdateOptions);
    }

    public Task<HealthRecordReply> getHealthRecord(HealthRecordReadOptions healthRecordReadOptions) {
        return aabz.aab(this.aab.aab(healthRecordReadOptions), new aacv());
    }

    public Task<Void> deleteHealthRecord(HealthRecordDeleteOptions healthRecordDeleteOptions) {
        return this.aab.aab(healthRecordDeleteOptions);
    }

    public Task<String> addHealthRecord(HealthRecordInsertOptions healthRecordInsertOptions) {
        return this.aab.aab(healthRecordInsertOptions);
    }

    HealthRecordController() {
        this.aab = null;
        this.aab = aacm.aab();
    }
}
