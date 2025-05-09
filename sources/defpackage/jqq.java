package defpackage;

import android.os.RemoteException;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.IBaseCallback;
import com.huawei.hwservicesmgr.IWearPhoneServiceAIDL;
import com.huawei.syncmgr.SyncOption;
import com.huawei.syncmgr.coresleep.CoreSleepSyncApi;
import health.compact.a.EnvironmentUtils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

@ApiDefine(uri = CoreSleepSyncApi.class)
@Singleton
/* loaded from: classes5.dex */
public class jqq implements CoreSleepSyncApi {
    @Override // com.huawei.syncmgr.coresleep.CoreSleepSyncApi
    public void startSynCoreSleep(SyncOption syncOption, final IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.e("BTSYNC_CoreSleep_CoreSleepSyncApiImpl", "startSynCoreSleep isphoneserviceprocess is ", Boolean.valueOf(EnvironmentUtils.e()));
        if (EnvironmentUtils.e()) {
            jen.e(kec.c()).a(syncOption, iBaseResponseCallback);
            return;
        }
        try {
            IWearPhoneServiceAIDL e = jez.e();
            if (e != null) {
                e.starSycnCoreSleep(syncOption, new IBaseCallback.Stub() { // from class: jqq.2
                    @Override // com.huawei.hwservicesmgr.IBaseCallback
                    public void onResponse(int i, String str) {
                        LogUtil.c("CoreSleepSyncApiImpl", "startSynCoreSleep code is ", Integer.valueOf(i), " reason is ", str);
                        iBaseResponseCallback.d(i, str);
                    }
                });
            } else {
                ReleaseLogUtil.d("BTSYNC_CoreSleep_CoreSleepSyncApiImpl", "startSynCoreSleep iPhoneServiceAIDL is null");
                jez.a(BaseApplication.getContext());
            }
        } catch (RemoteException e2) {
            ReleaseLogUtil.c("BTSYNC_CoreSleep_CoreSleepSyncApiImpl", "startSynCoreSleep RemoteException ", ExceptionUtils.d(e2));
        }
    }

    @Override // com.huawei.syncmgr.coresleep.CoreSleepSyncApi
    public boolean isSyncing() {
        ReleaseLogUtil.e("BTSYNC_CoreSleep_CoreSleepSyncApiImpl", "start isSyncing");
        if (EnvironmentUtils.e()) {
            return jen.e(kec.c()).b();
        }
        try {
            IWearPhoneServiceAIDL e = jez.e();
            if (e != null) {
                return e.isCoreSleepSyncing();
            }
            ReleaseLogUtil.d("BTSYNC_CoreSleep_CoreSleepSyncApiImpl", "isSyncing iPhoneServiceAIDL is null");
            jez.a(BaseApplication.getContext());
            return false;
        } catch (RemoteException e2) {
            ReleaseLogUtil.c("BTSYNC_CoreSleep_CoreSleepSyncApiImpl", "isSyncing RemoteException ", ExceptionUtils.d(e2));
            return false;
        }
    }

    @Override // com.huawei.syncmgr.coresleep.CoreSleepSyncApi
    public void registerProgressListener(final IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.e("BTSYNC_CoreSleep_CoreSleepSyncApiImpl", "registerProgressListener, callback=", iBaseResponseCallback);
        if (EnvironmentUtils.e()) {
            jen.e(kec.c()).e(iBaseResponseCallback);
            return;
        }
        try {
            IWearPhoneServiceAIDL e = jez.e();
            if (e != null) {
                e.registerCoreSleepProgressListener(new IBaseCallback.Stub() { // from class: jqq.3
                    @Override // com.huawei.hwservicesmgr.IBaseCallback
                    public void onResponse(int i, String str) {
                        LogUtil.c("CoreSleepSyncApiImpl", "registerProgressListener code is ", Integer.valueOf(i), "  reason is ", str);
                        iBaseResponseCallback.d(i, str);
                    }
                });
            } else {
                ReleaseLogUtil.d("BTSYNC_CoreSleep_CoreSleepSyncApiImpl", "registerProgressListener iPhoneServiceAIDL is null");
                jez.a(BaseApplication.getContext());
            }
        } catch (RemoteException e2) {
            ReleaseLogUtil.c("BTSYNC_CoreSleep_CoreSleepSyncApiImpl", "registerProgressListener RemoteException ", ExceptionUtils.d(e2));
        }
    }
}
