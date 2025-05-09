package defpackage;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public class krq extends krr {
    private krq() {
        super(1, 4, 10L, TimeUnit.SECONDS, new LinkedBlockingDeque(), "AccountPicker-FileSystem-Pool");
    }

    static class c {
        private static final krq d = new krq();
    }

    public static krq a() {
        return c.d;
    }
}
