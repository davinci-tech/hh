package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwonesdk.process.HiHealthProcess;

/* loaded from: classes9.dex */
public class kvn implements HiHealthProcess {
    @Override // com.huawei.hwonesdk.process.HiHealthProcess
    public String postProcess(Object obj, Object obj2) {
        return null;
    }

    @Override // com.huawei.hwonesdk.process.HiHealthProcess
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public HiSyncOption preProcess(String str) {
        kus kusVar = (kus) HiJsonUtil.e(str, kus.class);
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncId(System.currentTimeMillis());
        hiSyncOption.setForceSync(true);
        hiSyncOption.setSyncDataTypes(kuf.a().c(kusVar.c()));
        hiSyncOption.setSyncMethod(2);
        hiSyncOption.setSyncScope(1);
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(0);
        return hiSyncOption;
    }

    @Override // com.huawei.hwonesdk.process.HiHealthProcess
    public void doAction(String str, IBaseResponseCallback iBaseResponseCallback) {
        HiHealthManager.d(BaseApplication.e()).synCloud(preProcess(str), null);
    }
}
