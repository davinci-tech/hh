package defpackage;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.utils.ScreenUtil;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.PhoneService;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.List;

/* loaded from: classes.dex */
public class jtf {

    /* renamed from: a, reason: collision with root package name */
    private static jtf f14067a;
    private static final Object e = new Object();
    private long c = 0;
    private Handler d = new Handler(Looper.getMainLooper()) { // from class: jtf.4
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what != 100) {
                return;
            }
            jtf.this.e();
        }
    };

    public static jtf d() {
        jtf jtfVar;
        synchronized (e) {
            if (f14067a == null) {
                LogUtil.a("DEVMGR_KillPhoneServiceManager", "KillPhoneServiceManager init");
                f14067a = new jtf();
            }
            jtfVar = f14067a;
        }
        return jtfVar;
    }

    public void e() {
        ReleaseLogUtil.e("DEVMGR_KillPhoneServiceManager", "Try to kill health enter");
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "killProcessesWhenNoDevices");
        if (deviceList == null) {
            ReleaseLogUtil.c("DEVMGR_KillPhoneServiceManager", "allDeviceInfoList is null");
            h();
        } else if (!deviceList.isEmpty()) {
            j();
        } else if (!b() && !ScreenUtil.a()) {
            a();
        } else {
            h();
        }
    }

    private void a() {
        ReleaseLogUtil.e("DEVMGR_KillPhoneServiceManager", "Kill phoneService process");
        Intent intent = new Intent(BaseApplication.e(), (Class<?>) PhoneService.class);
        intent.setPackage(BaseApplication.d());
        BaseApplication.e().stopService(intent);
    }

    public static void c() {
        synchronized (e) {
            jtf jtfVar = f14067a;
            if (jtfVar != null) {
                jtfVar.c = System.currentTimeMillis();
            }
        }
    }

    private boolean b() {
        return System.currentTimeMillis() - this.c < 120000;
    }

    private void h() {
        ReleaseLogUtil.e("DEVMGR_KillPhoneServiceManager", "Try to kill health after 10 seconds");
        ThreadPoolManager.d().execute(new Runnable() { // from class: jtf.3
            @Override // java.lang.Runnable
            public void run() {
                jtf.this.d.removeMessages(100);
                jtf.this.d.sendEmptyMessageDelayed(100, PreConnectManager.CONNECT_INTERNAL);
            }
        });
    }

    private void j() {
        ReleaseLogUtil.e("DEVMGR_KillPhoneServiceManager", "KillPhoneServiceManager onDestroy");
        synchronized (e) {
            this.d.removeMessages(100);
            this.d = null;
            f14067a = null;
        }
    }
}
