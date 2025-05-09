package defpackage;

import android.os.PowerManager;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class kkp {

    /* renamed from: a, reason: collision with root package name */
    private static kkp f14434a;
    private static final Object c = new Object();
    private PowerManager.WakeLock b;

    private kkp() {
        Object systemService = BaseApplication.e().getSystemService("power");
        if (systemService instanceof PowerManager) {
            LogUtil.a("UpdateTimeFreezeUtils", "init WakeLock");
            this.b = ((PowerManager) systemService).newWakeLock(1, "PhoneService_SetDeviceTime");
        }
    }

    public static kkp c() {
        kkp kkpVar;
        synchronized (c) {
            if (f14434a == null) {
                f14434a = new kkp();
            }
            kkpVar = f14434a;
        }
        return kkpVar;
    }

    public void d() {
        PowerManager.WakeLock wakeLock = this.b;
        if (wakeLock != null) {
            wakeLock.acquire(3000L);
        }
    }

    public void e() {
        this.b = null;
        a();
    }

    private static void a() {
        synchronized (c) {
            f14434a = null;
        }
    }
}
