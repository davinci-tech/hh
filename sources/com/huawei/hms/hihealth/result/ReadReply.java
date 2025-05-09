package com.huawei.hms.hihealth.result;

import com.huawei.hms.hihealth.data.DataCollector;
import com.huawei.hms.hihealth.data.DataType;
import com.huawei.hms.hihealth.data.Group;
import com.huawei.hms.hihealth.data.SampleSet;
import com.huawei.hms.support.api.client.Status;
import java.util.List;

/* loaded from: classes9.dex */
public class ReadReply extends aab<ReadDetailResult> {
    public Status getStatus() {
        return getResult().getStatus();
    }

    public List<SampleSet> getSampleSets() {
        return getResult().getSampleSets();
    }

    public SampleSet getSampleSet(DataType dataType) {
        return getResult().getSampleSet(dataType);
    }

    public SampleSet getSampleSet(DataCollector dataCollector) {
        return getResult().getSampleSet(dataCollector);
    }

    public List<Group> getGroups() {
        return getResult().getGroups();
    }
}
