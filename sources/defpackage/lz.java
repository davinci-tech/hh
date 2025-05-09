package defpackage;

import android.os.SystemClock;

/* loaded from: classes8.dex */
public class lz {
    public static long d = -1;

    public static boolean e() {
        synchronized (lz.class) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (elapsedRealtime - d < 3000) {
                return true;
            }
            d = elapsedRealtime;
            return false;
        }
    }
}
