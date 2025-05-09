package defpackage;

import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseAdviceManager;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.List;

/* loaded from: classes5.dex */
public class jwx {
    private static final Object d = new Object();

    public static void c() {
        LogUtil.a("HwSyncOtherTask", "notifyToSyncWorkoutData sending broadcast to sync workout data.");
        ThreadPoolManager.d().d("HwSyncOtherTask", new Runnable() { // from class: jwx.4
            @Override // java.lang.Runnable
            public void run() {
                synchronized (jwx.d) {
                    LogUtil.a("HwSyncOtherTask", "ready register device info.");
                    jwx.e();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, null, "HwSyncOtherTask");
        if (deviceList == null) {
            LogUtil.h("HwSyncOtherTask", "startSyncWorkOut deviceInfoList is null");
            return;
        }
        for (DeviceInfo deviceInfo : deviceList) {
            if (deviceInfo != null) {
                if (!keg.b(deviceInfo)) {
                    return;
                } else {
                    keg.d(deviceInfo);
                }
            }
        }
        HwExerciseAdviceManager.getInstance().syncDeviceWorkoutRecordInfo();
    }
}
