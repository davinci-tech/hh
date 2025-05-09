package defpackage;

import com.huawei.hwdevice.phoneprocess.mgr.linkage.eventproducer.SampleEventProducer;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class kha implements SampleEventProducer {
    @Override // com.huawei.hwdevice.phoneprocess.mgr.linkage.eventproducer.SampleEventProducer
    public cvp createSampleEvent(int i, String str) {
        byte[] d = new bms().d(1, System.currentTimeMillis()).d(2, i).d();
        LogUtil.a("LINKAGE_SportStopEvent", "SportStopEvent create: ", cvx.d(d));
        return sampleEventBulid(800400003L, d);
    }
}
