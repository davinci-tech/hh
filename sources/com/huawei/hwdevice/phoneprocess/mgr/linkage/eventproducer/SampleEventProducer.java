package com.huawei.hwdevice.phoneprocess.mgr.linkage.eventproducer;

import defpackage.cvp;

/* loaded from: classes5.dex */
public interface SampleEventProducer {
    cvp createSampleEvent(int i, String str);

    default cvp sampleEventBulid(long j, byte[] bArr) {
        cvp cvpVar = new cvp();
        cvpVar.a(j);
        cvpVar.b(0);
        cvpVar.setSrcPkgName("hw.sport.linkage.app");
        cvpVar.setWearPkgName("hw.sport.linkage.device");
        cvpVar.e(bArr);
        return cvpVar;
    }
}
