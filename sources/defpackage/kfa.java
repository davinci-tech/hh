package defpackage;

import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwdevice.phoneprocess.mgr.hwsynctaskmanager.synctask.AbstractSyncTask;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes5.dex */
public class kfa extends AbstractSyncTask {
    public kfa() {
        super("DFX_NOTIFY_TASK");
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.hwsynctaskmanager.synctask.AbstractSyncTask
    public void startTask(IBaseResponseCallback iBaseResponseCallback) {
        jsf.e();
        ReleaseLogUtil.e("Dfx_NotifyDfxTask", "task end");
        iBaseResponseCallback.d(0, "dfx notify success.");
    }
}
