package defpackage;

import java.net.InetSocketAddress;
import org.eclipse.californium.core.network.stack.CongestionControlLayer;
import org.eclipse.californium.core.network.stack.RemoteEndpoint;
import org.eclipse.californium.elements.config.Configuration;

/* loaded from: classes7.dex */
public class uzk extends CongestionControlLayer {
    public uzk(String str, Configuration configuration) {
        super(str, configuration);
    }

    @Override // org.eclipse.californium.core.network.stack.CongestionControlLayer
    public RemoteEndpoint createRemoteEndpoint(InetSocketAddress inetSocketAddress) {
        return new b(inetSocketAddress, this.defaultReliabilityLayerParameters.e(), this.defaultReliabilityLayerParameters.g());
    }

    static class b extends RemoteEndpoint {

        /* renamed from: a, reason: collision with root package name */
        private long f17617a;
        private long b;
        private long[] c;
        private long d;
        private float e;
        private float f;
        private int g;

        private b(InetSocketAddress inetSocketAddress, int i, int i2) {
            super(inetSocketAddress, i, i2, true);
            this.c = new long[2];
        }

        private void a(long j) {
            d(j);
            updateRTO((long) (j * 1.75d));
        }

        private void b(long j) {
            d(j);
            float abs = Math.abs((j - this.b) / j);
            this.f = abs;
            this.e = Math.min(Math.max(abs * 2.0f, this.e * 0.9583333f), 1.0f);
            this.f17617a = Math.max(j, this.b);
            this.d = e() + 100;
            long max = Math.max(Math.max((long) Math.max(getRTO() * 0.9583333f, (this.e + 1.0f) * this.f17617a), this.f17617a + 100), this.d);
            c();
            this.b = j;
            updateRTO(max);
        }

        @Override // org.eclipse.californium.core.network.stack.RemoteEndpoint
        public void processRttMeasurement(RemoteEndpoint.RtoType rtoType, long j) {
            synchronized (this) {
                if (rtoType != RemoteEndpoint.RtoType.STRONG) {
                    return;
                }
                if (initialRto()) {
                    a(j);
                } else {
                    b(j);
                }
            }
        }

        private void d(long j) {
            synchronized (this.c) {
                long[] jArr = this.c;
                int i = this.g;
                int i2 = i + 1;
                this.g = i2;
                jArr[i] = j;
                if (i2 >= jArr.length) {
                    this.g = 0;
                }
            }
        }

        private long e() {
            long j;
            synchronized (this.c) {
                j = -1;
                for (long j2 : this.c) {
                    j = Math.max(j, j2);
                }
            }
            return j;
        }

        private void c() {
            uzk.LOGGER.trace("Delta: {}, D: {}, B: {}, RTT_max: {}", Float.valueOf(this.f), Float.valueOf(0.9583333f), Float.valueOf(this.e), Long.valueOf(this.f17617a));
        }
    }
}
