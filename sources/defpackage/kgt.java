package defpackage;

import androidx.core.location.LocationRequestCompat;
import com.huawei.hwdevice.phoneprocess.mgr.linkage.eventproducer.SampleEventProducer;

/* loaded from: classes5.dex */
public class kgt implements SampleEventProducer {
    @Override // com.huawei.hwdevice.phoneprocess.mgr.linkage.eventproducer.SampleEventProducer
    public cvp createSampleEvent(int i, String str) {
        cvp cvpVar = new cvp();
        cvpVar.a(LocationRequestCompat.PASSIVE_INTERVAL);
        return cvpVar;
    }
}
