package defpackage;

import com.huawei.hwdevice.phoneprocess.mgr.linkage.eventproducer.SampleEventProducer;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class kgz implements SampleEventProducer {
    @Override // com.huawei.hwdevice.phoneprocess.mgr.linkage.eventproducer.SampleEventProducer
    public cvp createSampleEvent(int i, String str) {
        byte[] d = new bms().a(2, 1).d();
        LogUtil.a("LINKAGE_StopLinkageEvent", "createStopLinkageEvent eventData: ", cvx.d(d));
        return sampleEventBulid(800400014L, d);
    }
}
