package defpackage;

import android.os.RemoteException;
import com.huawei.hwdevice.phoneprocess.mgr.linkage.channel.eventprocessor.EventProcessor;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.LinkageDeviceCommandListener;
import defpackage.bmt;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes9.dex */
public class kgm implements EventProcessor {
    @Override // com.huawei.hwdevice.phoneprocess.mgr.linkage.channel.eventprocessor.EventProcessor
    public void handleSampleEvent(cvp cvpVar, LinkageDeviceCommandListener linkageDeviceCommandListener) {
        if (linkageDeviceCommandListener == null) {
            ReleaseLogUtil.e("R_LINKAGE_Event_SportResumeProcessor", "resumeSport mDeviceCommandListener null");
            return;
        }
        LogUtil.a("LINKAGE_Event_SportResumeProcessor", "handleSampleEvent eventData = ", cvx.d(cvpVar.c()));
        try {
            bmt.b b = new bmt().b(cvpVar.c(), 0);
            LogUtil.a("LINKAGE_Event_SportResumeProcessor", "handleLinkageEvent: workoutType = ", Integer.valueOf((int) b.b((byte) 2, -1L)), ", linkageTimeStamp =", Long.valueOf(b.b((byte) 1, -1L)));
            try {
                ReleaseLogUtil.e("R_LINKAGE_Event_SportResumeProcessor", "resumeSport start");
                linkageDeviceCommandListener.resume();
            } catch (RemoteException unused) {
                ReleaseLogUtil.e("R_LINKAGE_Event_SportResumeProcessor", "resumeSport RemoteException");
            }
        } catch (bmk unused2) {
            ReleaseLogUtil.c("R_LINKAGE_Event_SportResumeProcessor", "handle event device tlv error");
        }
    }
}
