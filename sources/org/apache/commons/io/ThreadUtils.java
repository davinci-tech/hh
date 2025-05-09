package org.apache.commons.io;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.TemporalAmount;

/* loaded from: classes10.dex */
public final class ThreadUtils {
    private static int getNanosOfMilli(Duration duration) {
        return duration.getNano() % 1000000;
    }

    public static void sleep(Duration duration) throws InterruptedException {
        Instant plus = Instant.now().plus((TemporalAmount) duration);
        do {
            Thread.sleep(duration.toMillis(), getNanosOfMilli(duration));
            duration = Duration.between(Instant.now(), plus);
        } while (!duration.isNegative());
    }

    @Deprecated
    public ThreadUtils() {
    }
}
