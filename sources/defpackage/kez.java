package defpackage;

import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwdevice.phoneprocess.mgr.hwsynctaskmanager.synctask.AbstractSyncTask;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class kez extends AbstractSyncTask {

    /* renamed from: a, reason: collision with root package name */
    private IBaseResponseCallback f14335a;
    private keu b;
    private DeviceInfo e;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public kez(com.huawei.health.devicemgr.business.entity.DeviceInfo r4, defpackage.keu r5, long r6, com.huawei.hwbasemgr.IBaseResponseCallback r8) {
        /*
            r3 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "DIC_SEQUENCE_SYNC_TASK "
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
            r3.e = r4
            r3.b = r5
            r3.f14335a = r8
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.kez.<init>(com.huawei.health.devicemgr.business.entity.DeviceInfo, keu, long, com.huawei.hwbasemgr.IBaseResponseCallback):void");
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.hwsynctaskmanager.synctask.AbstractSyncTask
    public void startTask(IBaseResponseCallback iBaseResponseCallback) {
        DeviceInfo deviceInfo = this.e;
        if (deviceInfo != null) {
            LogUtil.a("DataDicSequenceSyncTask", "DataDicSequenceSyncTask start:", deviceInfo.getDeviceName());
        }
        if (iBaseResponseCallback == null) {
            LogUtil.h("DataDicSequenceSyncTask", "startTask callback is null");
        } else {
            kbv.b().b(this.e, this.b, iBaseResponseCallback, this.f14335a);
            LogUtil.a("DataDicSequenceSyncTask", "DataDicSequenceSyncTask End");
        }
    }
}
