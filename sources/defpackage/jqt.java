package defpackage;

import android.os.RemoteException;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.IWearPhoneServiceAIDL;
import com.huawei.syncmgr.SyncStressDataApi;
import health.compact.a.EnvironmentUtils;

@ApiDefine(uri = SyncStressDataApi.class)
@Singleton
/* loaded from: classes5.dex */
public class jqt implements SyncStressDataApi {
    @Override // com.huawei.syncmgr.SyncStressDataApi
    public void syncStressDetailData() {
        if (EnvironmentUtils.e()) {
            kdq.c().a();
            return;
        }
        IWearPhoneServiceAIDL e = jez.e();
        if (e != null) {
            try {
                e.notifyPhoneService("stressRelaxManager", null, 0, null);
                return;
            } catch (RemoteException e2) {
                jez.a(BaseApplication.getContext());
                LogUtil.b("SyncStressDataApiImpl", "startSyncWorkOut exception:", ExceptionUtils.d(e2));
                return;
            }
        }
        LogUtil.h("SyncStressDataApiImpl", "startSyncWorkOut() iPhoneServiceAIDL is null");
        jez.a(BaseApplication.getContext());
    }
}
