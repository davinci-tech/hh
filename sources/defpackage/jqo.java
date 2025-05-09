package defpackage;

import android.os.RemoteException;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwservicesmgr.IWearPhoneServiceAIDL;
import com.huawei.syncmgr.alarmdata.AlarmDataSyncApi;
import health.compact.a.EnvironmentUtils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

@ApiDefine(uri = AlarmDataSyncApi.class)
@Singleton
/* loaded from: classes5.dex */
public class jqo implements AlarmDataSyncApi {
    @Override // com.huawei.syncmgr.alarmdata.AlarmDataSyncApi
    public void startSyncLowSpo2Alarm(DeviceInfo deviceInfo) {
        ReleaseLogUtil.e("BTSYNC_AlarmData_AlarmDataSyncApiImpl", "startSyncLowSpo2Alarm() isPhoneServiceProcess:", Boolean.valueOf(EnvironmentUtils.e()));
        if (EnvironmentUtils.e()) {
            kej.e().b(deviceInfo);
            return;
        }
        IWearPhoneServiceAIDL e = jez.e();
        if (e != null) {
            try {
                e.notifyPhoneService("syncSpo2AlarmData", deviceInfo, 0, null);
            } catch (RemoteException unused) {
                ReleaseLogUtil.c("BTSYNC_AlarmData_AlarmDataSyncApiImpl", "startSyncLowSpo2Alarm() RemoteException");
            }
        } else {
            ReleaseLogUtil.d("BTSYNC_AlarmData_AlarmDataSyncApiImpl", "startSyncLowSpo2Alarm() binder is null");
            jez.a(BaseApplication.getContext());
        }
    }

    @Override // com.huawei.syncmgr.alarmdata.AlarmDataSyncApi
    public void startSyncHeartRateAlarm(DeviceInfo deviceInfo) {
        ReleaseLogUtil.e("BTSYNC_AlarmData_AlarmDataSyncApiImpl", "startSyncHeartRateAlarm() isPhoneServiceProcess:", Boolean.valueOf(EnvironmentUtils.e()));
        if (EnvironmentUtils.e()) {
            kee.c().a(deviceInfo);
            return;
        }
        IWearPhoneServiceAIDL e = jez.e();
        if (e != null) {
            try {
                e.notifyPhoneService("syncHeartRateAlarmData", deviceInfo, 0, null);
            } catch (RemoteException unused) {
                ReleaseLogUtil.c("BTSYNC_AlarmData_AlarmDataSyncApiImpl", "startSyncHeartRateAlarm() RemoteException");
            }
        } else {
            ReleaseLogUtil.d("BTSYNC_AlarmData_AlarmDataSyncApiImpl", "startSyncHeartRateAlarm() binder is null");
            jez.a(BaseApplication.getContext());
        }
    }
}
