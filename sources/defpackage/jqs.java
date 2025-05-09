package defpackage;

import android.os.RemoteException;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.IWearPhoneServiceAIDL;
import com.huawei.syncmgr.basicdata.BasicDataSyncApi;
import health.compact.a.EnvironmentUtils;

@ApiDefine(uri = BasicDataSyncApi.class)
@Singleton
/* loaded from: classes5.dex */
public class jqs implements BasicDataSyncApi {
    @Override // com.huawei.syncmgr.basicdata.BasicDataSyncApi
    public void startSynBasicData(IBaseResponseCallback iBaseResponseCallback) {
        if (EnvironmentUtils.e()) {
            jwy.b().e(true, (DeviceInfo) null, iBaseResponseCallback);
            return;
        }
        IWearPhoneServiceAIDL e = jez.e();
        if (e != null) {
            try {
                e.notifyPhoneService("syncBasicData", new DeviceInfo(), 0, null);
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
