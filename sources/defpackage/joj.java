package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwdevice.mainprocess.mgr.settingcamera.CameraAuthStatusCallback;
import com.huawei.hwdevice.mainprocess.mgr.settingcamera.CameraContorlInterface;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.BroadcastManagerUtil;

/* loaded from: classes5.dex */
public class joj {

    /* renamed from: a, reason: collision with root package name */
    private static joj f13990a;
    private CameraContorlInterface b;
    private joc c = new joc();
    private jod j = new jod();
    private static final Object e = new Object();
    private static BroadcastReceiver d = new BroadcastReceiver() { // from class: joj.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("com.huawei.bone.action.CONNECTION_STATE_CHANGED")) {
                LogUtil.a("SettingCameraManager", "device state changed");
                BaseApplication.e().unregisterReceiver(joj.d);
                joj unused = joj.f13990a = null;
            }
        }
    };

    private joj() {
        e();
        if (this.c.checkSupportCamera() && !this.j.supportAlgoArch()) {
            this.b = this.c;
        } else {
            this.b = this.j;
        }
    }

    public static joj a() {
        joj jojVar;
        synchronized (e) {
            if (f13990a == null) {
                f13990a = new joj();
            }
            jojVar = f13990a;
        }
        return jojVar;
    }

    private static void e() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        BroadcastManagerUtil.bFC_(BaseApplication.e(), d, intentFilter, bin.d, null);
    }

    public boolean c() {
        return this.b.checkSupportCamera();
    }

    public void e(CameraAuthStatusCallback cameraAuthStatusCallback) {
        this.b.getCameraStatus(cameraAuthStatusCallback);
    }

    public void c(boolean z) {
        this.b.updateCameraAuthStatus(z);
    }

    public void a(DeviceInfo deviceInfo) {
        this.b.deleteCameraAuthorization(deviceInfo);
    }

    public void b() {
        this.b.unbindCameraService();
    }
}
