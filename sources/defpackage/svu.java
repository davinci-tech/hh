package defpackage;

import android.os.Handler;
import android.os.HandlerThread;

/* loaded from: classes7.dex */
public class svu {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f17257a = new Object();
    private static volatile svu e;
    private final Handler c;

    public static svu b() {
        if (e == null) {
            synchronized (f17257a) {
                if (e == null) {
                    e = new svu();
                }
            }
        }
        return e;
    }

    private svu() {
        HandlerThread handlerThread = new HandlerThread("wear_wallet_handler_oversea");
        handlerThread.start();
        this.c = new Handler(handlerThread.getLooper());
    }

    public Handler eXR_() {
        return eXP_();
    }

    public Handler eXQ_() {
        return eXP_();
    }

    public Handler eXP_() {
        return this.c;
    }
}
