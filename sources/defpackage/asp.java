package defpackage;

import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwdevice.phoneprocess.mgr.hwsynctaskmanager.synctask.AbstractSyncTask;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
public class asp extends AbstractSyncTask {
    public asp() {
        super("BLOOD_SUGAR_SYNC_TASK");
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.hwsynctaskmanager.synctask.AbstractSyncTask
    public void startTask(IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("BloodSugarSyncTask", "BloodSugarSyncTask start");
        if (iBaseResponseCallback == null) {
            LogUtil.h("BloodSugarSyncTask", "callback == null");
            return;
        }
        ask.e();
        iBaseResponseCallback.d(0, "");
        LogUtil.a("BloodSugarSyncTask", "BloodSugarSyncTask End");
    }
}
