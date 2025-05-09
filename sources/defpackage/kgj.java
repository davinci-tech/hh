package defpackage;

import com.huawei.hwdevice.phoneprocess.mgr.linkage.channel.eventprocessor.EventProcessor;
import com.huawei.hwservicesmgr.LinkageDeviceCommandListener;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes9.dex */
public class kgj implements EventProcessor {
    @Override // com.huawei.hwdevice.phoneprocess.mgr.linkage.channel.eventprocessor.EventProcessor
    public void handleSampleEvent(cvp cvpVar, LinkageDeviceCommandListener linkageDeviceCommandListener) {
        ReleaseLogUtil.e("R_LINKAGE_Event_DefaultProcessor", "handleSampleEvent start");
    }
}
