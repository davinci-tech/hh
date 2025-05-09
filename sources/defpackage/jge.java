package defpackage;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.haf.bundle.AppBundle;
import com.huawei.haf.bundle.AppBundleLauncher;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class jge {
    private static jge c;
    private static final Object d = new Object();

    /* renamed from: a, reason: collision with root package name */
    private mmj f13806a;
    private DeviceInfo e;
    private String g;
    private int i = -1;
    private AppBundleLauncher.InstallCallback b = new AppBundleLauncher.InstallCallback() { // from class: jge.3
        @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
        public boolean call(Context context, Intent intent) {
            LogUtil.a("DetectionDiagnosisManager", "diagnosisplugin install success");
            jge.this.d(context);
            return false;
        }
    };

    private jge() {
        d();
        LogUtil.c("DetectionDiagnosisManager", "default constructor");
    }

    public static jge b() {
        jge jgeVar;
        synchronized (d) {
            if (c == null) {
                c = new jge();
            }
            jgeVar = c;
        }
        return jgeVar;
    }

    private void d() {
        this.f13806a = mmj.e();
    }

    public void c(DeviceInfo deviceInfo, int i) {
        LogUtil.a("DetectionDiagnosisManager", "to startDetection enter");
        if (i == 0) {
            this.g = "wearableEntrance";
        } else if (i == 1) {
            this.g = "wearUnconnectionEntrance";
        } else if (i == 2) {
            this.g = "wearUnconnectionRemoteEntrance";
        } else {
            this.g = "";
        }
        if (deviceInfo == null || i == -1 || TextUtils.isEmpty(this.g) || this.f13806a == null) {
            LogUtil.h("DetectionDiagnosisManager", "startDetection invalid parameters");
            return;
        }
        this.e = deviceInfo;
        this.i = i;
        Intent intent = new Intent();
        intent.putExtra("moduleName", "PluginDeviceDetectDiagnosis");
        AppBundle.e().launchActivity(BaseApplication.getContext(), intent, new AppBundleLauncher.InstallCallback() { // from class: jgm
            @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
            public final boolean call(Context context, Intent intent2) {
                return jge.this.bHc_(context, intent2);
            }
        });
    }

    /* synthetic */ boolean bHc_(Context context, Intent intent) {
        this.f13806a.b(this.b);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final Context context) {
        int i;
        final String deviceUdid;
        LogUtil.a("DetectionDiagnosisManager", "openDetectionPage enter");
        if (this.f13806a == null || this.e == null || TextUtils.isEmpty(this.g) || (i = this.i) == -1) {
            LogUtil.h("DetectionDiagnosisManager", "openDetectionPage invalid parameters");
            return;
        }
        if (i == 0) {
            deviceUdid = TextUtils.isEmpty(this.e.getDeviceUdid()) ? this.e.getUuid() : this.e.getDeviceUdid();
        } else {
            deviceUdid = TextUtils.isEmpty(this.e.getUdidFromDevice()) ? this.e.getDeviceUdid() : this.e.getUdidFromDevice();
        }
        if (this.i == 0 && TextUtils.isEmpty(deviceUdid)) {
            LogUtil.h("DetectionDiagnosisManager", "openDetectionPage deviceId is null");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: jgj
                @Override // java.lang.Runnable
                public final void run() {
                    jge.this.b(deviceUdid, context);
                }
            });
        }
    }

    /* synthetic */ void b(String str, Context context) {
        this.f13806a.openDiagnosisPage(this.i, str, this.e, this.g, context);
    }
}
