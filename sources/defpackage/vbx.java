package defpackage;

import java.net.DatagramPacket;
import java.util.concurrent.TimeUnit;
import org.eclipse.californium.elements.config.BasicDefinition;
import org.eclipse.californium.elements.config.Configuration;
import org.eclipse.californium.scandium.DatagramFilter;
import org.eclipse.californium.scandium.config.DtlsConfig;
import org.eclipse.californium.scandium.dtls.ContentType;

/* loaded from: classes7.dex */
public class vbx implements DatagramFilter {

    /* renamed from: a, reason: collision with root package name */
    private final int f17656a;
    private final long e;

    public vbx() {
        this.e = 0L;
        this.f17656a = 0;
    }

    public vbx(Configuration configuration) {
        long longValue = configuration.a(DtlsConfig.n, TimeUnit.NANOSECONDS).longValue();
        this.e = longValue;
        int intValue = ((Integer) configuration.a((BasicDefinition) DtlsConfig.m)).intValue();
        this.f17656a = intValue;
        if ((longValue == 0) ^ (intValue == 0)) {
            throw new IllegalArgumentException("DTLS MAC error filter configuration ambig! Use 0 for both, or larger than 0 for both!");
        }
    }

    @Override // org.eclipse.californium.scandium.DatagramFilter
    public boolean onReceiving(DatagramPacket datagramPacket) {
        if (datagramPacket.getLength() < 13) {
            return false;
        }
        byte[] data = datagramPacket.getData();
        int offset = datagramPacket.getOffset();
        ContentType typeByValue = ContentType.getTypeByValue(data[offset]);
        if (typeByValue == null || data[offset + 3] != 0 || (data[offset + 4] & 255) > 1 || data[offset + 5] != 0) {
            return false;
        }
        if (typeByValue == ContentType.HANDSHAKE || typeByValue == ContentType.ALERT) {
            return true;
        }
        return (data[offset + 1] & 255) == 254 && (data[offset + 2] & 255) == 253;
    }

    @Override // org.eclipse.californium.scandium.DatagramFilter
    public boolean onReceiving(vdz vdzVar, vcm vcmVar) {
        if (this.f17656a > 0) {
            if (vcmVar.j() instanceof c) {
                return !((c) r10).b(this.f17656a, vdzVar.f(), this.e);
            }
        }
        return true;
    }

    @Override // org.eclipse.californium.scandium.DatagramFilter
    public boolean onMacError(vdz vdzVar, vcm vcmVar) {
        if (this.f17656a <= 0) {
            return false;
        }
        Object j = vcmVar.j();
        if (j == null) {
            j = new c(vdzVar.f());
            vcmVar.e(j);
        }
        if (!(j instanceof c)) {
            return false;
        }
        ((c) j).c(vdzVar.f(), this.e);
        return false;
    }

    static class c {
        private long b;
        private long d;

        private c(long j) {
            this.b = 0L;
            this.d = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void c(long j, long j2) {
            d(j, j2);
            this.b++;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean b(int i, long j, long j2) {
            d(j, j2);
            return this.b > ((long) i);
        }

        private void d(long j, long j2) {
            if (j - this.d > j2) {
                this.b = 0L;
            }
            this.d = j;
        }
    }
}
