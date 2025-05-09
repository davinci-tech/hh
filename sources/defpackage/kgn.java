package defpackage;

import android.os.RemoteException;
import com.huawei.hwdevice.phoneprocess.mgr.linkage.channel.eventprocessor.EventProcessor;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.LinkageDeviceCommandListener;
import defpackage.bmt;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes9.dex */
public class kgn implements EventProcessor {
    @Override // com.huawei.hwdevice.phoneprocess.mgr.linkage.channel.eventprocessor.EventProcessor
    public void handleSampleEvent(cvp cvpVar, LinkageDeviceCommandListener linkageDeviceCommandListener) {
        LogUtil.a("LINKAGE_Event_SportStartProcessor", "handleSampleEvent eventData = ", cvx.d(cvpVar.c()));
        try {
            bmt.b b = new bmt().b(cvpVar.c(), 0);
            long b2 = b.b((byte) 1, -1L);
            int b3 = (int) b.b((byte) 2, -1L);
            LogUtil.a("LINKAGE_Event_SportStartProcessor", "handle workoutType = ", Integer.valueOf(b3), ", linkageTimeStamp =", Long.valueOf(b2));
            if (linkageDeviceCommandListener == null) {
                ReleaseLogUtil.e("R_LINKAGE_Event_SportStartProcessor", "startSport mDeviceCommandListener null");
                kgf.c(b3, Integer.MAX_VALUE, 201);
                return;
            }
            try {
                ReleaseLogUtil.e("R_LINKAGE_Event_SportStartProcessor", "startSport start");
                linkageDeviceCommandListener.start(b3);
            } catch (RemoteException unused) {
                ReleaseLogUtil.e("R_LINKAGE_Event_SportStartProcessor", "startSport RemoteException");
                kgf.c(b3, Integer.MAX_VALUE, 201);
            }
        } catch (bmk unused2) {
            ReleaseLogUtil.c("R_LINKAGE_Event_SportStartProcessor", "handle event device tlv error");
        }
    }
}
