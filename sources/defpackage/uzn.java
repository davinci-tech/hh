package defpackage;

import java.net.InetSocketAddress;
import org.eclipse.californium.core.network.stack.CongestionControlLayer;
import org.eclipse.californium.core.network.stack.RemoteEndpoint;
import org.eclipse.californium.elements.config.Configuration;

/* loaded from: classes7.dex */
public class uzn extends CongestionControlLayer {
    public uzn(String str, Configuration configuration) {
        super(str, configuration);
    }

    @Override // org.eclipse.californium.core.network.stack.CongestionControlLayer
    public RemoteEndpoint createRemoteEndpoint(InetSocketAddress inetSocketAddress) {
        return new d(inetSocketAddress, this.defaultReliabilityLayerParameters.e(), this.defaultReliabilityLayerParameters.g());
    }

    static class d extends RemoteEndpoint {

        /* renamed from: a, reason: collision with root package name */
        private long f17620a;
        private long b;
        private long c;
        private long d;

        private d(InetSocketAddress inetSocketAddress, int i, int i2) {
            super(inetSocketAddress, i, i2, true);
        }

        private void a(long j) {
            this.f17620a = j;
            long j2 = j / 2;
            this.d = j2;
            long max = Math.max(j2, 50L);
            this.c = max;
            this.b = max;
            long j3 = this.f17620a;
            d();
            updateRTO(j3 + (max * 4));
        }

        private void b(long j) {
            long round = this.f17620a + Math.round((j - r0) * 0.125d);
            this.f17620a = round;
            long j2 = this.d;
            if (j < round - j2) {
                this.d = Math.round((j2 * 0.96875d) + (Math.abs(j - round) * 0.03125d));
            } else {
                this.d = Math.round(j2 * 0.75d) + Math.round(Math.abs(j - this.f17620a) * 0.25d);
            }
            long j3 = this.d;
            if (j3 > this.c) {
                this.c = j3;
                if (j3 > this.b) {
                    this.b = j3;
                }
            }
            long j4 = this.c;
            long j5 = this.b;
            if (j4 < j5) {
                this.b = Math.round((j5 * 0.75d) + (j4 * 0.25d));
            }
            this.c = 50L;
            long j6 = this.f17620a;
            long j7 = this.b;
            d();
            updateRTO(j6 + (j7 * 4));
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

        private void d() {
            uzn.LOGGER.trace("SRTT: {}, RTTVAR: {}, mdev: {}, mdev_max: {}", Long.valueOf(this.f17620a), Long.valueOf(this.b), Long.valueOf(this.d), Long.valueOf(this.c));
        }
    }
}
