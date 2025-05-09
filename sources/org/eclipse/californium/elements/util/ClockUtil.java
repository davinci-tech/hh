package org.eclipse.californium.elements.util;

/* loaded from: classes7.dex */
public class ClockUtil {
    private static volatile Realtime b = new Realtime() { // from class: org.eclipse.californium.elements.util.ClockUtil.3
        @Override // org.eclipse.californium.elements.util.ClockUtil.Realtime
        public long nanoRealtime() {
            return System.nanoTime();
        }
    };

    public interface Realtime {
        long nanoRealtime();
    }

    public static long d() {
        return b.nanoRealtime();
    }
}
