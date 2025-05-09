package defpackage;

import com.huawei.health.devicemgr.api.phoneprocess.SamplePointAfterProcess;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes6.dex */
public class niu implements SamplePointAfterProcess {
    @Override // com.huawei.health.devicemgr.api.phoneprocess.SamplePointAfterProcess
    public HiHealthData onSamplePointAfterProcess(HiHealthData hiHealthData, String str) {
        if (hiHealthData == null) {
            LogUtil.h("ActiveHourPointAfterProcess", "hiHealthData == null");
            return null;
        }
        long startTime = hiHealthData.getStartTime();
        hiHealthData.setStartTime(ggl.a(startTime));
        hiHealthData.setEndTime(ggl.d(startTime));
        LogUtil.a("ActiveHourPointAfterProcess", "onSamplePointAfterProcess activeHour data after ", Long.valueOf(hiHealthData.getStartTime()));
        return hiHealthData;
    }
}
