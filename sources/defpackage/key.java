package defpackage;

import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.mgr.hwsynctaskmanager.synctask.AbstractSyncTask;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes5.dex */
public class key extends AbstractSyncTask {
    private ThreadPoolManager b;

    public key(String str) {
        super("ECG_SYNC_TASK" + str);
        this.b = ThreadPoolManager.a(1, 1);
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.hwsynctaskmanager.synctask.AbstractSyncTask
    public void startTask(final IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.e("DEVMGR_EcgSyncTask", "start ecg sync task enter.");
        Map<String, DeviceCapability> queryDeviceCapability = jsz.b(BaseApplication.getContext()).queryDeviceCapability(4, "", "EcgSyncTask");
        DeviceCapability deviceCapability = null;
        if (queryDeviceCapability != null && !queryDeviceCapability.isEmpty()) {
            Iterator<Map.Entry<String, DeviceCapability>> it = queryDeviceCapability.entrySet().iterator();
            while (it.hasNext() && (deviceCapability = it.next().getValue()) == null) {
            }
        }
        if (deviceCapability == null) {
            ReleaseLogUtil.d("DEVMGR_EcgSyncTask", "get capability is null. please check.");
            iBaseResponseCallback.d(-3, "");
        } else if (!deviceCapability.isSupportEcgAuth()) {
            ReleaseLogUtil.d("DEVMGR_EcgSyncTask", "device not support ecg.");
            iBaseResponseCallback.d(-3, "");
        } else {
            this.b.d("EcgSyncTask", new Runnable() { // from class: key.2
                @Override // java.lang.Runnable
                public void run() {
                    if ("true".equals(key.this.c("com.huawei.health.ecg.activate.status"))) {
                        ReleaseLogUtil.e("DEVMGR_EcgSyncTask", "start ecg file.");
                        kcy.e(BaseApplication.getContext()).i(iBaseResponseCallback);
                    } else {
                        ReleaseLogUtil.d("DEVMGR_EcgSyncTask", "account not activity");
                        iBaseResponseCallback.d(-1, "");
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String c(String str) {
        String str2;
        LogUtil.a("EcgSyncTask", "getUserPreference enter");
        HiUserPreference d = d(str);
        if (d != null) {
            str2 = d.getValue();
        } else {
            ReleaseLogUtil.d("DEVMGR_EcgSyncTask", "ecg activity status not save in database, not start ecg task.");
            str2 = null;
        }
        LogUtil.a("EcgSyncTask", "getUserPreference ecg : ", str2);
        return str2;
    }

    private HiUserPreference d(String str) {
        return HiHealthManager.d(BaseApplication.getContext()).getUserPreference(str);
    }
}
