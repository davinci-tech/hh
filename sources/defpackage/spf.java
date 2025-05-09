package defpackage;

import android.content.Context;
import com.huawei.health.deviceconnect.callback.SendCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.maps.offlinedata.health.p2p.OfflineMapWearEngineManager;
import com.huawei.unitedevice.constant.ConnectState;
import com.huawei.unitedevice.constant.WearEngineModule;
import com.huawei.unitedevice.manager.EngineLogicBaseManager;
import defpackage.spn;
import health.compact.a.ReleaseLogUtil;
import java.io.File;
import java.io.IOException;

/* loaded from: classes9.dex */
public class spf extends EngineLogicBaseManager {
    private static volatile spf d;
    private static final Object b = new Object();

    /* renamed from: a, reason: collision with root package name */
    protected static final Object f17196a = new Object();

    private spf(Context context) {
        super(context);
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void onReceiveDeviceCommand(DeviceInfo deviceInfo, int i, spn spnVar) {
        LogUtil.a("HiWearEngineHttpProxyManager", "HiWearEngineHttpProxyManager onReceiveDeviceCommand");
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public WearEngineModule getManagerModule() {
        return WearEngineModule.HTTPPROXY_MODULE;
    }

    public static spf a() {
        spf spfVar;
        synchronized (b) {
            LogUtil.a("HiWearEngineHttpProxyManager", "getInstance()");
            if (d == null) {
                d = new spf(BaseApplication.getContext());
            }
            spfVar = d;
        }
        return spfVar;
    }

    private static String d() {
        try {
            File filesDir = BaseApplication.getContext().getFilesDir();
            if (filesDir != null) {
                return filesDir.getCanonicalPath();
            }
        } catch (IOException unused) {
            LogUtil.b("HiWearEngineHttpProxyManager", "getSourcePath catch IOException");
        }
        return "";
    }

    public void c(final String str) {
        LogUtil.a("HiWearEngineHttpProxyManager", "sendCloudResponseFileToDevice");
        final String str2 = d() + File.separator + "7" + File.separator + str;
        spn.b bVar = new spn.b();
        bVar.a(new File(str2));
        sendComand(bVar.e(), new SendCallback() { // from class: spf.1
            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onSendResult(int i) {
                ReleaseLogUtil.b("R_HiWearEngineHttpProxyManager", "send HttpProxy response FileToDevice errorCode: ", Integer.valueOf(i));
                File file = new File(str2);
                if (file.exists()) {
                    if (!file.delete()) {
                        LogUtil.h("HiWearEngineHttpProxyManager", "Failed to delete httpProxy cloud response file: ", str);
                    }
                    LogUtil.a("HiWearEngineHttpProxyManager", "Successfully delete httpProxy cloud response file: ", str);
                }
            }

            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onSendProgress(long j) {
                LogUtil.a("HiWearEngineHttpProxyManager", "sendCloudResponseFileToDevice progress: ", Long.valueOf(j));
            }

            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onFileTransferReport(String str3) {
                LogUtil.a("HiWearEngineHttpProxyManager", "Current HttpProxy response file transferWay: ", str3);
            }
        });
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void onDeviceConnectionChange(ConnectState connectState, DeviceInfo deviceInfo) {
        LogUtil.h("HiWearEngineHttpProxyManager", "onDeviceConnectionChange");
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public String getWearPkgName() {
        return "hw.wearable.httpProxy";
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public String getWearFingerprint() {
        return OfflineMapWearEngineManager.WEAR_FINGERPRINT;
    }
}
