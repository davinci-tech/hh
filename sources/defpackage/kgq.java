package defpackage;

import android.os.RemoteException;
import com.huawei.hwdevice.phoneprocess.mgr.linkage.channel.eventprocessor.EventProcessor;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.LinkageDeviceCommandListener;
import defpackage.bmt;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes9.dex */
public class kgq implements EventProcessor {
    @Override // com.huawei.hwdevice.phoneprocess.mgr.linkage.channel.eventprocessor.EventProcessor
    public void handleSampleEvent(cvp cvpVar, LinkageDeviceCommandListener linkageDeviceCommandListener) {
        LogUtil.a("LINKAGE_Event_LinkageProcessor", "handleSampleEvent eventData = ", cvx.d(cvpVar.c()));
        try {
            bmt.b b = new bmt().b(cvpVar.c(), 0);
            int a2 = b.a((byte) 1, -1);
            int a3 = b.a((byte) 2, -1);
            long b2 = b.b((byte) 3, -1L);
            int a4 = b.a((byte) 4, -1);
            int a5 = b.a((byte) 5, -1);
            LogUtil.a("LINKAGE_Event_LinkageProcessor", "handle workoutType = ", Integer.valueOf(a2), ", linkageOperation =", Integer.valueOf(a3), ", linkageTimeStamp =", Long.valueOf(b2), ", replyCode =", Integer.valueOf(a4), ", currentSportStatus =", Integer.valueOf(a5));
            kgc.b().a(a2);
            d(a3, a2, a5, linkageDeviceCommandListener);
        } catch (bmk unused) {
            ReleaseLogUtil.c("R_LINKAGE_Event_LinkageProcessor", "handle event device tlv error");
        }
    }

    private void d(int i, int i2, int i3, LinkageDeviceCommandListener linkageDeviceCommandListener) {
        if (i == 0) {
            c(i2, i3, linkageDeviceCommandListener);
            return;
        }
        if (i == 1) {
            b(linkageDeviceCommandListener);
        } else if (i == 2) {
            a(i2, i3, linkageDeviceCommandListener);
        } else {
            ReleaseLogUtil.e("R_LINKAGE_Event_LinkageProcessor", "getCommandId nothing");
        }
    }

    private void c(int i, int i2, LinkageDeviceCommandListener linkageDeviceCommandListener) {
        if (linkageDeviceCommandListener == null) {
            ReleaseLogUtil.e("R_LINKAGE_Event_LinkageProcessor", "startLinkage mDeviceCommandListener null");
            kgf.c(i, i2, 100);
            return;
        }
        try {
            ReleaseLogUtil.e("R_LINKAGE_Event_LinkageProcessor", "startLinkage start");
            linkageDeviceCommandListener.startLinkage(i, i2);
            ReleaseLogUtil.e("R_LINKAGE_Event_LinkageProcessor", "startLinkage end");
        } catch (RemoteException unused) {
            ReleaseLogUtil.e("R_LINKAGE_Event_LinkageProcessor", "startLinkage RemoteException");
            kgf.c(i, i2, 100);
        }
    }

    private void a(int i, int i2, LinkageDeviceCommandListener linkageDeviceCommandListener) {
        if (linkageDeviceCommandListener == null) {
            ReleaseLogUtil.e("R_LINKAGE_Event_LinkageProcessor", "startLinkage mDeviceCommandListener null");
            kgf.c(i, i2, 101);
            return;
        }
        try {
            ReleaseLogUtil.e("R_LINKAGE_Event_LinkageProcessor", "resumeLinkage start");
            linkageDeviceCommandListener.resumeLinkage(i, i2);
            ReleaseLogUtil.e("R_LINKAGE_Event_LinkageProcessor", "resumeLinkage end");
        } catch (RemoteException unused) {
            ReleaseLogUtil.e("R_LINKAGE_Event_LinkageProcessor", "resumeLinkage RemoteException");
            kgf.c(i, i2, 101);
        }
    }

    private void b(LinkageDeviceCommandListener linkageDeviceCommandListener) {
        if (linkageDeviceCommandListener == null) {
            ReleaseLogUtil.e("R_LINKAGE_Event_LinkageProcessor", "startLinkage mDeviceCommandListener null");
            return;
        }
        try {
            ReleaseLogUtil.e("R_LINKAGE_Event_LinkageProcessor", "stopLinkage start");
            linkageDeviceCommandListener.stopLinkage();
            ReleaseLogUtil.e("R_LINKAGE_Event_LinkageProcessor", "stopLinkage end");
        } catch (RemoteException unused) {
            ReleaseLogUtil.e("R_LINKAGE_Event_LinkageProcessor", "stopLinkage RemoteException");
        }
    }
}
