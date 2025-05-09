package defpackage;

import org.eclipse.californium.scandium.dtls.CertificateType;
import org.eclipse.californium.scandium.dtls.cipher.CipherSuite;

/* loaded from: classes7.dex */
public class vdi {

    /* renamed from: a, reason: collision with root package name */
    private final CipherSuite.KeyExchangeAlgorithm f17678a;
    private final CertificateType d;

    public vdi(CipherSuite.KeyExchangeAlgorithm keyExchangeAlgorithm, CertificateType certificateType) {
        if (keyExchangeAlgorithm == null) {
            throw new NullPointerException("key exchange must not be null!");
        }
        if (certificateType == null) {
            throw new NullPointerException("certificate type must not be null!");
        }
        this.f17678a = keyExchangeAlgorithm;
        this.d = certificateType;
    }

    public CipherSuite.KeyExchangeAlgorithm a() {
        return this.f17678a;
    }

    public CertificateType d() {
        return this.d;
    }

    public String toString() {
        return "KeyExgAl=" + this.f17678a + ", cert.type=" + this.d;
    }
}
