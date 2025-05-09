package defpackage;

import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwdevice.phoneprocess.mgr.hwsynctaskmanager.synctask.AbstractSyncTask;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class kfb extends AbstractSyncTask {
    private DeviceInfo d;
    private keu e;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public kfb(com.huawei.health.devicemgr.business.entity.DeviceInfo r4, defpackage.keu r5, long r6) {
        /*
            r3 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "DIC_SYNC_TASK "
            r0.<init>(r1)
            if (r4 != 0) goto Lc
            java.lang.String r1 = ""
            goto L10
        Lc:
            java.lang.String r1 = r4.getDeviceName()
        L10:
            r0.append(r1)
            java.lang.String r1 = "_"
            r0.append(r1)
            int r2 = r5.b()
            r0.append(r2)
            r0.append(r1)
            r0.append(r6)
            java.lang.String r6 = r0.toString()
            r3.<init>(r6)
            r3.d = r4
            r3.e = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.kfb.<init>(com.huawei.health.devicemgr.business.entity.DeviceInfo, keu, long):void");
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.hwsynctaskmanager.synctask.AbstractSyncTask
    public void startTask(IBaseResponseCallback iBaseResponseCallback) {
        DeviceInfo deviceInfo = this.d;
        if (deviceInfo != null) {
            LogUtil.a("DataDicSyncTask", "DataDicSyncTask start:", deviceInfo.getDeviceName());
        }
        if (iBaseResponseCallback == null) {
            LogUtil.h("DataDicSyncTask", "startTask callback is null");
        } else if (this.d == null) {
            LogUtil.h("DataDicSyncTask", "startTask deviceInfo is null");
            iBaseResponseCallback.d(0, null);
        } else {
            kex.a().a(this.d, this.e, iBaseResponseCallback);
            LogUtil.a("DataDicSyncTask", "DataDicSyncTask End");
        }
    }
}
