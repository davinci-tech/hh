package defpackage;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import org.eclipse.californium.elements.util.ClockUtil;
import org.slf4j.Logger;
import org.slf4j.event.Level;

/* loaded from: classes7.dex */
public class vbw {
    private static final boolean c = !Boolean.FALSE.equals(vcb.d("COAP_LOGGING_FILTER"));

    /* renamed from: a, reason: collision with root package name */
    private long f17655a;
    private final long b;
    private final Logger d;
    private final long e;
    private long i;

    public vbw(Logger logger, long j, long j2) {
        this(logger, j, j2, TimeUnit.NANOSECONDS);
    }

    public vbw(Logger logger, long j, long j2, TimeUnit timeUnit) {
        this.d = logger;
        this.e = j;
        this.b = timeUnit.toNanos(j2);
        this.i = ClockUtil.d();
    }

    public void d(String str, Object... objArr) {
        if (this.d.isWarnEnabled()) {
            c(Level.WARN, str, objArr);
        }
    }

    public void c(String str, Object... objArr) {
        if (this.d.isInfoEnabled()) {
            c(Level.INFO, str, objArr);
        }
    }

    public void a(String str, Object... objArr) {
        if (this.d.isDebugEnabled()) {
            c(Level.DEBUG, str, objArr);
        }
    }

    private void c(Level level, String str, Object... objArr) {
        boolean z;
        if (c) {
            long d = ClockUtil.d();
            long j = this.b;
            long j2 = this.i;
            synchronized (this) {
                long j3 = this.f17655a;
                z = j3 < this.e;
                if ((j + j2) - d > 0) {
                    this.f17655a = j3 + 1;
                } else {
                    this.i = d;
                    if (!z) {
                        int length = objArr.length;
                        objArr = Arrays.copyOf(objArr, length + 1);
                        objArr[length] = Long.valueOf(this.f17655a);
                        str = str + " ({} additional errors.)";
                        z = true;
                    }
                    this.f17655a = 0L;
                }
            }
        } else {
            z = true;
        }
        if (z) {
            int i = AnonymousClass3.e[level.ordinal()];
            if (i == 1) {
                this.d.error(str, objArr);
                return;
            }
            if (i == 2) {
                this.d.warn(str, objArr);
                return;
            }
            if (i == 3) {
                this.d.info(str, objArr);
            } else if (i == 4) {
                this.d.debug(str, objArr);
            } else {
                if (i != 5) {
                    return;
                }
                this.d.trace(str, objArr);
            }
        }
    }

    /* renamed from: vbw$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] e;

        static {
            int[] iArr = new int[Level.values().length];
            e = iArr;
            try {
                iArr[Level.ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                e[Level.WARN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                e[Level.INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                e[Level.DEBUG.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                e[Level.TRACE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }
}
