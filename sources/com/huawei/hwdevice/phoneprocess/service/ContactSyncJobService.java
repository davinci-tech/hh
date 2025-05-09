package com.huawei.hwdevice.phoneprocess.service;

import android.app.job.JobParameters;
import android.app.job.JobService;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.service.ContactSyncJobService;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.cun;
import defpackage.jsz;
import defpackage.jze;
import defpackage.jzz;
import defpackage.kal;
import java.util.List;

/* loaded from: classes5.dex */
public class ContactSyncJobService extends JobService {
    private static boolean b = false;
    private static final Object c = new Object();

    @Override // android.app.job.JobService
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    @Override // android.app.job.JobService
    public boolean onStartJob(JobParameters jobParameters) {
        LogUtil.a("ContactSyncJobService", "onStartJob: start");
        jze.a().d();
        if (jobParameters == null || b()) {
            d();
            LogUtil.a("ContactSyncJobService", "onStartJob: return");
            return false;
        }
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "ContactSyncJobService");
        DeviceInfo deviceInfo = deviceList.size() != 0 ? deviceList.get(0) : null;
        if (deviceInfo == null) {
            LogUtil.h("ContactSyncJobService", "onStartJob deviceInfo is null");
            return false;
        }
        DeviceCapability deviceCapability = jsz.b(BaseApplication.getContext()).queryDeviceCapability(2, "", "ContactSyncJobService").get(deviceInfo.getDeviceIdentify());
        if (deviceCapability == null || !deviceCapability.isSupportSyncContacts()) {
            LogUtil.h("ContactSyncJobService", "onStartJob is not support");
            return false;
        }
        bPh_(jobParameters, deviceInfo);
        LogUtil.a("ContactSyncJobService", "onStartJob: end");
        return true;
    }

    private void bPh_(final JobParameters jobParameters, final DeviceInfo deviceInfo) {
        LogUtil.a("ContactSyncJobService", "handleSync: start");
        b(true);
        ThreadPoolManager.d().execute(new Runnable() { // from class: kju
            @Override // java.lang.Runnable
            public final void run() {
                ContactSyncJobService.this.bPi_(deviceInfo, jobParameters);
            }
        });
        LogUtil.a("ContactSyncJobService", "handleSync: end");
    }

    public /* synthetic */ void bPi_(DeviceInfo deviceInfo, JobParameters jobParameters) {
        jze.a().b(kal.a(), 0);
        jze.a().e(deviceInfo);
        b(false);
        jobFinished(jobParameters, false);
        LogUtil.a("ContactSyncJobService", "executed sync in ThreadPool end.");
    }

    public void d() {
        LogUtil.a("ContactSyncJobService", "startListen: start");
        jze.a().b(this, kal.a());
    }

    private boolean b() {
        return jzz.b(getApplicationContext()) || e();
    }

    public static void b(boolean z) {
        synchronized (c) {
            b = z;
        }
    }

    public static boolean e() {
        boolean z;
        synchronized (c) {
            z = b;
        }
        return z;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        d();
    }
}
