package defpackage;

import android.os.RemoteException;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.mgr.hwsyncmgr.periodrri.HwDevicePeriodRriFileManager;
import com.huawei.hwservicesmgr.IWearPhoneServiceAIDL;
import com.huawei.syncmgr.periodrri.PeriodRriSyncApi;
import health.compact.a.EnvironmentUtils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

@ApiDefine(uri = PeriodRriSyncApi.class)
@Singleton
/* loaded from: classes5.dex */
public class jqu implements PeriodRriSyncApi {
    @Override // com.huawei.syncmgr.periodrri.PeriodRriSyncApi
    public void startSyncPeriodRriFile() {
        ReleaseLogUtil.e("BTSYNC_PeriodRri_PeriodRriSyncApiImpl", "startSyncPeriodRriFile() isPhoneServiceProcess:", Boolean.valueOf(EnvironmentUtils.e()));
        if (EnvironmentUtils.e()) {
            HwDevicePeriodRriFileManager.getInstance().getPeriodRriFileFromDevice();
            return;
        }
        IWearPhoneServiceAIDL e = jez.e();
        if (e != null) {
            try {
                e.notifyPhoneService("syncPeriodRri", null, 0, null);
            } catch (RemoteException unused) {
                ReleaseLogUtil.c("BTSYNC_PeriodRri_PeriodRriSyncApiImpl", "startSyncPeriodRriFile() RemoteException");
            }
        } else {
            ReleaseLogUtil.d("BTSYNC_PeriodRri_PeriodRriSyncApiImpl", "startSyncPeriodRriFile() binder is null");
            jez.a(BaseApplication.getContext());
        }
    }

    @Override // com.huawei.syncmgr.periodrri.PeriodRriSyncApi
    public boolean isPeriodRriSyncing() {
        ReleaseLogUtil.e("BTSYNC_PeriodRri_PeriodRriSyncApiImpl", "isPeriodRriSyncing() isPhoneServiceProcess:", Boolean.valueOf(EnvironmentUtils.e()));
        if (EnvironmentUtils.e()) {
            return HwDevicePeriodRriFileManager.getInstance().isPeriodRriSyncing();
        }
        IWearPhoneServiceAIDL e = jez.e();
        if (e != null) {
            try {
                return e.notifyPhoneService("isPeriodRriSyncing", null, 230227, null);
            } catch (RemoteException unused) {
                ReleaseLogUtil.c("BTSYNC_PeriodRri_PeriodRriSyncApiImpl", "isPeriodRriSyncing() RemoteException");
                return false;
            }
        }
        ReleaseLogUtil.d("BTSYNC_PeriodRri_PeriodRriSyncApiImpl", "isPeriodRriSyncing() binder is null");
        jez.a(BaseApplication.getContext());
        return false;
    }
}
