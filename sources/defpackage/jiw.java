package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import com.huawei.haf.bundle.AppBundle;
import com.huawei.haf.bundle.AppBundleLauncher;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.deviceconnect.callback.PingCallback;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.IBaseCallback;
import health.compact.a.LogAnonymous;

/* loaded from: classes5.dex */
public class jiw {
    private static final Object c = new Object();
    private static jiw d;

    public static jiw a() {
        jiw jiwVar;
        LogUtil.a("HwAppMarketApi", "getInstance()");
        synchronized (c) {
            if (d == null) {
                d = new jiw();
            }
            jiwVar = d;
        }
        return jiwVar;
    }

    private jiw() {
    }

    public void i() {
        jiv.e().c();
    }

    public void e(String str) {
        jiv.e().c(str);
    }

    public void a(int i, Object obj) {
        if (cwi.c(jpt.a("HwAppMarketApi"), 44)) {
            LogUtil.a("HwAppMarketApi", "setAppMarketParameter smart");
        } else {
            jiv.e().e(i, obj);
        }
    }

    public void c(DeviceCapability deviceCapability, IBaseResponseCallback iBaseResponseCallback) {
        jiv.e().e(deviceCapability, iBaseResponseCallback);
    }

    public void g() {
        jiv.e().b();
    }

    public void d(boolean z) {
        jiv.e().e(z);
    }

    public void e(IBaseResponseCallback iBaseResponseCallback) {
        mlz.c().sendCommand(1, iBaseResponseCallback);
    }

    public void b(final IBaseCallback iBaseCallback) {
        LogUtil.a("HwAppMarketApi", "setCancelTransferCallback callback : ", iBaseCallback);
        mlz.c().sendCommand(2, iBaseCallback != null ? new IBaseResponseCallback() { // from class: jiw.5
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                IBaseCallback iBaseCallback2 = iBaseCallback;
                if (iBaseCallback2 == null || !(obj instanceof String)) {
                    return;
                }
                try {
                    iBaseCallback2.onResponse(i, String.valueOf(obj));
                } catch (RemoteException e) {
                    LogUtil.b("HwAppMarketApi", "RemoteException : ", LogAnonymous.b((Throwable) e));
                }
            }
        } : null);
    }

    public void d(final PingCallback pingCallback) {
        LogUtil.a("HwAppMarketApi", "pingDevice");
        mlz.c().sendCommand(3, new IBaseResponseCallback() { // from class: jiw.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                PingCallback pingCallback2 = pingCallback;
                if (pingCallback2 != null) {
                    pingCallback2.onPingResult(i);
                }
            }
        });
    }

    public boolean f() {
        return jja.a();
    }

    public String d() {
        return jja.e();
    }

    public int d(Context context) {
        return jja.b().a(BaseApplication.getContext());
    }

    public void j() {
        mlz.c().initAppMarketAdapter();
    }

    public void b() {
        ThreadPoolManager.d().d("HwAppMarketApi", new Runnable() { // from class: jiy
            @Override // java.lang.Runnable
            public final void run() {
                mlz.c().hasNewVersion();
            }
        });
    }

    public void d(int i) {
        mlz.c().launchAppGallery(i);
    }

    public void e(Context context, AppBundleLauncher.InstallCallback installCallback) {
        Intent intent = new Intent();
        intent.putExtra("moduleName", "PluginWearAbility");
        AppBundle.e().launchActivity(context, intent, installCallback);
    }

    public boolean d(String str) {
        return jiv.e().d(str);
    }

    public void a(String str, boolean z) {
        jiv.e().b(str, z);
    }

    public void c() {
        jiv.e().a();
    }
}
