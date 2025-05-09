package defpackage;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public class kru extends krr {
    public kru() {
        super(1, 1, 10L, TimeUnit.SECONDS, new LinkedBlockingQueue(), "AccountPicker-global-task-Pool");
    }

    static class d {
        private static final kru e = new kru();
    }

    public static kru b() {
        return d.e;
    }
}
