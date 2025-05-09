package health.compact.a;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

/* loaded from: classes.dex */
public class ThreadManager {
    private static HandlerThread d;

    private ThreadManager() {
    }

    static {
        HandlerThread handlerThread = new HandlerThread("logfile_thread");
        d = handlerThread;
        handlerThread.start();
    }

    public static Looper bRk_() {
        return d.getLooper();
    }

    public static Handler bRj_() {
        return new Handler(bRk_());
    }
}
