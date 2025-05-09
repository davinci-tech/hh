package defpackage;

import android.emcom.IOneHopAppCallback;
import android.emcom.IOneHopAuthReqCallback;

/* loaded from: classes5.dex */
public class lsk {
    private static volatile lsk b;
    private static lsl c;

    private lsk() {
        if (c == null) {
            c = lsl.d();
        }
    }

    public static lsk c() {
        lsk lskVar;
        synchronized (lsk.class) {
            if (b == null) {
                b = new lsk();
            }
            lskVar = b;
        }
        return lskVar;
    }

    public int d(String str, int i, IOneHopAppCallback.Stub stub) {
        return c.b(str, i, stub);
    }

    public String e() {
        return c.a();
    }

    public int b(String str, IOneHopAuthReqCallback.Stub stub) {
        return c.b(str, stub);
    }
}
