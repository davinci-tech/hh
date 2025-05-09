package defpackage;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;
import org.eclipse.californium.core.network.stack.CongestionControlLayer;
import org.eclipse.californium.core.network.stack.RemoteEndpoint;
import org.eclipse.californium.elements.config.Configuration;
import org.eclipse.californium.elements.util.ClockUtil;

/* loaded from: classes7.dex */
public class uzl extends CongestionControlLayer {
    private final boolean b;

    @Override // org.eclipse.californium.core.network.stack.CongestionControlLayer
    public float calculateVBF(long j, float f) {
        if (j > 3000) {
            return 1.5f;
        }
        if (j < 1000) {
            return 3.0f;
        }
        return f;
    }

    public uzl(String str, Configuration configuration, boolean z) {
        super(str, configuration);
        this.b = z;
        setDithering(true);
    }

    @Override // org.eclipse.californium.core.network.stack.CongestionControlLayer
    public RemoteEndpoint createRemoteEndpoint(InetSocketAddress inetSocketAddress) {
        return new b(inetSocketAddress, this.defaultReliabilityLayerParameters.e(), this.defaultReliabilityLayerParameters.g(), this.b, null);
    }

    static class b extends RemoteEndpoint {

        /* renamed from: a, reason: collision with root package name */
        private final boolean f17618a;
        private uzo c;
        private long d;
        private uzo e;

        /* synthetic */ b(InetSocketAddress inetSocketAddress, int i, int i2, boolean z, AnonymousClass2 anonymousClass2) {
            this(inetSocketAddress, i, i2, z);
        }

        private b(InetSocketAddress inetSocketAddress, int i, int i2, boolean z) {
            super(inetSocketAddress, i, i2, true);
            this.f17618a = z;
            long j = i;
            this.c = new uzo(1, j);
            this.e = new uzo(4, j);
            this.d = ClockUtil.d();
        }

        @Override // org.eclipse.californium.core.network.stack.RemoteEndpoint
        public void processRttMeasurement(RemoteEndpoint.RtoType rtoType, long j) {
            long c;
            double d;
            synchronized (this) {
                if (!this.f17618a || rtoType == RemoteEndpoint.RtoType.STRONG) {
                    int i = AnonymousClass2.b[rtoType.ordinal()];
                    if (i == 1) {
                        c = this.c.c(j);
                        d = 0.25d;
                    } else {
                        if (i != 2) {
                            return;
                        }
                        c = this.e.c(j);
                        d = 0.5d;
                    }
                    updateRTO(Math.round((c * d) + ((1.0d - d) * getRTO())));
                    this.d = ClockUtil.d();
                }
            }
        }

        @Override // org.eclipse.californium.core.network.stack.RemoteEndpoint
        public void checkAging() {
            synchronized (this) {
                long b = b(TimeUnit.MILLISECONDS);
                long rto = getRTO();
                while (true) {
                    if (rto < 1000) {
                        long j = 16 * rto;
                        if (b > j) {
                            b -= j;
                            rto *= 2;
                            updateRTO(rto);
                            this.d = ClockUtil.d();
                        }
                    }
                    if (rto <= 3000) {
                        break;
                    }
                    long j2 = 4 * rto;
                    if (b <= j2) {
                        break;
                    }
                    b -= j2;
                    rto = (rto / 2) + 1000;
                    updateRTO(rto);
                    this.d = ClockUtil.d();
                }
            }
        }

        private long b(TimeUnit timeUnit) {
            return timeUnit.convert(ClockUtil.d() - this.d, TimeUnit.NANOSECONDS);
        }
    }

    /* renamed from: uzl$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[RemoteEndpoint.RtoType.values().length];
            b = iArr;
            try {
                iArr[RemoteEndpoint.RtoType.WEAK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[RemoteEndpoint.RtoType.STRONG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }
}
