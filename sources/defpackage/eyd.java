package defpackage;

import android.content.Context;
import android.os.PowerManager;

/* loaded from: classes8.dex */
public class eyd {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f12381a = new Object();
    private static PowerManager.WakeLock d;

    public static void c() {
        if (d != null) {
            eym.e("WakelockUtil", "wakelock already init");
            return;
        }
        Context a2 = eyc.a();
        if (a2 != null) {
            eym.b("WakelockUtil", "init wakelock start");
            PowerManager.WakeLock newWakeLock = ((PowerManager) a2.getSystemService("power")).newWakeLock(1, "aar:lbs");
            d = newWakeLock;
            newWakeLock.setReferenceCounted(true);
            return;
        }
        eym.c("WakelockUtil", "init wakelock is null");
    }

    public static void c(int i) {
        if (d == null) {
            eym.c("WakelockUtil", "acquire wakelock is null");
            return;
        }
        synchronized (f12381a) {
            d.acquire(i);
            eym.b("WakelockUtil", "acquire wakelock succeed");
        }
    }

    public static void b() {
        if (d == null) {
            eym.c("WakelockUtil", "release wakelock is null");
            return;
        }
        synchronized (f12381a) {
            if (d.isHeld()) {
                d.release();
                eym.b("WakelockUtil", "release wakelock succeed");
            } else {
                eym.c("WakelockUtil", "WakeLock expired before release");
            }
        }
    }
}
