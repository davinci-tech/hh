package defpackage;

import com.huawei.hwdevice.phoneprocess.mgr.linkage.channel.SampelBaseHandler;
import com.huawei.hwservicesmgr.IBaseCallback;
import com.huawei.hwservicesmgr.LinkageDeviceCommandListener;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes9.dex */
public class kgk implements SampelBaseHandler {
    @Override // com.huawei.hwdevice.phoneprocess.mgr.linkage.channel.SampelBaseHandler
    public void handle(cvr cvrVar, LinkageDeviceCommandListener linkageDeviceCommandListener, IBaseCallback iBaseCallback) {
        if (cvrVar instanceof cvp) {
            cvp cvpVar = (cvp) cvrVar;
            long e = cvpVar.e();
            ReleaseLogUtil.e("R_LINKAGE_Event_SampleEventHandler", "handle eventId: ", Long.valueOf(e));
            kgo.a(Long.valueOf(e)).handleSampleEvent(cvpVar, linkageDeviceCommandListener);
            return;
        }
        ReleaseLogUtil.e("R_LINKAGE_Event_SampleEventHandler", "message not SampleEvent");
    }
}
