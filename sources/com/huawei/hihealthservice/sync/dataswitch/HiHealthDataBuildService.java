package com.huawei.hihealthservice.sync.dataswitch;

import com.huawei.hihealth.HiHealthData;
import com.huawei.hwcloudmodel.model.samplepoint.SamplePoint;
import com.huawei.hwcloudmodel.model.unite.HealthDetail;
import defpackage.ikv;
import java.util.List;

/* loaded from: classes7.dex */
public interface HiHealthDataBuildService {
    List<HiHealthData> build(SamplePoint samplePoint, ikv ikvVar, HealthDetail healthDetail);
}
