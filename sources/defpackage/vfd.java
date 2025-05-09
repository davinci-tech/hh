package defpackage;

import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.eclipse.californium.scandium.dtls.SignatureAndHashAlgorithm;
import org.eclipse.californium.scandium.dtls.cipher.XECDHECryptography;

/* loaded from: classes7.dex */
public class vfd {
    private boolean b;
    private boolean i;
    private final List<PublicKey> e = new ArrayList();

    /* renamed from: a, reason: collision with root package name */
    private final List<List<X509Certificate>> f17704a = new ArrayList();
    private final List<X509Certificate> f = new ArrayList();
    private final List<SignatureAndHashAlgorithm> d = new ArrayList();
    private final List<XECDHECryptography.SupportedGroup> c = new ArrayList();

    public void a(X509Certificate[] x509CertificateArr) {
        if (x509CertificateArr != null) {
            for (X509Certificate x509Certificate : x509CertificateArr) {
                a(x509Certificate.getPublicKey());
                this.f.add(x509Certificate);
            }
        }
    }

    public void a(PublicKey publicKey) {
        if (publicKey != null) {
            SignatureAndHashAlgorithm.d(this.d, publicKey);
            if (vbm.d(publicKey.getAlgorithm())) {
                XECDHECryptography.SupportedGroup fromPublicKey = XECDHECryptography.SupportedGroup.fromPublicKey(publicKey);
                if (fromPublicKey == null) {
                    throw new IllegalArgumentException("CA's public key ec-group must be supported!");
                }
                vfb.a(this.c, fromPublicKey);
            }
        }
    }

    public List<SignatureAndHashAlgorithm> c() {
        return this.d;
    }

    public List<XECDHECryptography.SupportedGroup> a() {
        return this.c;
    }

    public void b(List<SignatureAndHashAlgorithm> list) {
        for (PublicKey publicKey : this.e) {
            if (SignatureAndHashAlgorithm.c(list, publicKey) == null) {
                throw new IllegalStateException("supported signature and hash algorithms " + list + " doesn't match the public " + publicKey.getAlgorithm() + " key!");
            }
        }
        Iterator<List<X509Certificate>> it = this.f17704a.iterator();
        while (it.hasNext()) {
            if (!SignatureAndHashAlgorithm.d(list, it.next())) {
                throw new IllegalStateException("supported signature and hash algorithms " + list + " doesn't match the certificate chain!");
            }
        }
        Iterator<X509Certificate> it2 = this.f.iterator();
        while (it2.hasNext()) {
            PublicKey publicKey2 = it2.next().getPublicKey();
            if (SignatureAndHashAlgorithm.c(list, publicKey2) == null) {
                throw new IllegalStateException("supported signature and hash algorithms " + list + " doesn't match the trust's public key " + publicKey2.getAlgorithm() + "!");
            }
        }
    }

    public void e(List<XECDHECryptography.SupportedGroup> list) {
        for (XECDHECryptography.SupportedGroup supportedGroup : this.c) {
            if (!supportedGroup.isUsable()) {
                throw new IllegalStateException("public key used with unsupported group (curve) " + supportedGroup.name() + "!");
            }
            if (!list.contains(supportedGroup)) {
                throw new IllegalStateException("public key used with not configured group (curve) " + supportedGroup.name() + "!");
            }
        }
    }

    public boolean d(boolean z) {
        return this.f17704a.isEmpty() || (!z ? !this.i : !this.b);
    }
}
