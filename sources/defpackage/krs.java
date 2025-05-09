package defpackage;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public class krs extends krr {
    private krs() {
        super(2, 4, 10L, TimeUnit.SECONDS, new LinkedBlockingDeque(), "AccountPicker-Core-Pool");
    }

    static class c {
        private static final krs b = new krs();
    }

    public static krs a() {
        return c.b;
    }
}
