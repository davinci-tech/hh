package defpackage;

import java.security.GeneralSecurityException;
import java.security.PublicKey;
import java.security.cert.CertPath;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.security.auth.x500.X500Principal;
import org.eclipse.californium.scandium.dtls.AlertMessage;
import org.eclipse.californium.scandium.dtls.CertificateType;
import org.eclipse.californium.scandium.dtls.HandshakeMessage;
import org.eclipse.californium.scandium.dtls.HandshakeType;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public final class vce extends HandshakeMessage {

    /* renamed from: a, reason: collision with root package name */
    private static final CertPath f17661a;
    private static final Logger b = vha.a((Class<?>) vce.class);
    private static final ver c;
    private static final List<byte[]> d;
    private final CertPath e;
    private final int g;
    private final byte[] h;
    private final PublicKey i;
    private final List<byte[]> j;

    static {
        CertPath certPath;
        ver verVar = new ver("X.509");
        c = verVar;
        try {
            certPath = verVar.d().generateCertPath(Collections.emptyList());
        } catch (GeneralSecurityException unused) {
            certPath = null;
        }
        f17661a = certPath;
        d = Collections.emptyList();
    }

    public vce() {
        this(f17661a);
    }

    public vce(List<X509Certificate> list) {
        this(list, null);
    }

    public vce(List<X509Certificate> list, List<X500Principal> list2) {
        this(vbi.d(list, list2));
        Logger logger = b;
        if (logger.isDebugEnabled()) {
            int size = this.e.getCertificates().size();
            if (size < list.size()) {
                logger.debug("created CERTIFICATE message with truncated certificate chain [length: {}, full-length: {}]", Integer.valueOf(size), Integer.valueOf(list.size()));
            } else {
                logger.debug("created CERTIFICATE message with certificate chain [length: {}]", Integer.valueOf(size));
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private vce(CertPath certPath) {
        int i;
        if (certPath == null) {
            throw new NullPointerException("Certificate chain must not be null!");
        }
        this.h = null;
        this.e = certPath;
        List<? extends Certificate> certificates = certPath.getCertificates();
        int size = certificates.size();
        if (size == 0) {
            this.i = null;
            this.j = d;
            this.g = 3;
            return;
        }
        List arrayList = new ArrayList(size);
        try {
            Iterator<? extends Certificate> it = certificates.iterator();
            i = 0;
            while (it.hasNext()) {
                byte[] encoded = it.next().getEncoded();
                arrayList.add(encoded);
                i += encoded.length + 3;
            }
        } catch (CertificateEncodingException e) {
            List list = d;
            b.warn("Could not encode certificate chain", (Throwable) e);
            i = 0;
            arrayList = list;
        }
        this.i = arrayList.isEmpty() ? null : certificates.get(0).getPublicKey();
        this.j = arrayList;
        this.g = i + 3;
    }

    public vce(PublicKey publicKey) {
        this.i = publicKey;
        if (publicKey == null) {
            this.h = null;
            this.e = f17661a;
            this.j = d;
            this.g = 3;
            return;
        }
        this.e = null;
        this.j = null;
        byte[] encoded = publicKey.getEncoded();
        this.h = encoded;
        this.g = encoded.length + 3;
    }

    public vce(byte[] bArr) {
        this(a(bArr));
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage
    public HandshakeType getMessageType() {
        return HandshakeType.CERTIFICATE;
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage
    public int getMessageLength() {
        return this.g;
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage, org.eclipse.californium.scandium.dtls.DTLSMessage
    public String toString(int i) {
        CertPath certPath;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString(i));
        String b2 = vcb.b(i + 1);
        String b3 = vcb.b(i + 2);
        byte[] bArr = this.h;
        if (bArr == null && (certPath = this.e) != null) {
            List<? extends Certificate> certificates = certPath.getCertificates();
            sb.append(b2);
            sb.append("Certificate chain: ");
            sb.append(certificates.size());
            sb.append(" certificates");
            sb.append(vcb.a());
            int i2 = 0;
            for (Certificate certificate : certificates) {
                sb.append(b3);
                sb.append("Certificate Length: ");
                sb.append(this.j.get(i2).length);
                sb.append(" bytes");
                sb.append(vcb.a());
                String c2 = vcb.c(certificate);
                sb.append(b3);
                sb.append("Certificate[");
                sb.append(i2);
                sb.append(".]: ");
                sb.append(c2.replaceAll("\n", "\n" + b3));
                sb.append(vcb.a());
                i2++;
            }
        } else if (bArr != null && this.e == null) {
            sb.append(b2);
            sb.append("Raw Public Key: ");
            PublicKey publicKey = this.i;
            sb.append((publicKey != null ? vcb.c(publicKey).replaceAll("\n", "\n" + b3) : "<empty>").replaceAll("\n", "\n" + b3));
            sb.append(vcb.a());
        }
        return sb.toString();
    }

    public PublicKey e() {
        return this.i;
    }

    public CertPath c() {
        return this.e;
    }

    public boolean b() {
        return this.i == null;
    }

    @Override // org.eclipse.californium.scandium.dtls.HandshakeMessage
    public byte[] fragmentToByteArray() {
        vbo vboVar = new vbo(getMessageLength());
        byte[] bArr = this.h;
        if (bArr == null) {
            vboVar.b(getMessageLength() - 3, 24);
            Iterator<byte[]> it = this.j.iterator();
            while (it.hasNext()) {
                vboVar.d(it.next(), 24);
            }
        } else {
            vboVar.d(bArr, 24);
        }
        return vboVar.c();
    }

    public static vce e(vbn vbnVar, CertificateType certificateType) throws vdb {
        int c2 = vbnVar.c(24);
        if (c2 == 0) {
            return new vce(f17661a);
        }
        if (CertificateType.RAW_PUBLIC_KEY == certificateType) {
            b.debug("Parsing RawPublicKey CERTIFICATE message");
            return new vce(vbnVar.a(c2));
        }
        if (CertificateType.X_509 == certificateType) {
            vbn b2 = vbnVar.b(c2);
            b.debug("Parsing X.509 CERTIFICATE message");
            try {
                CertificateFactory d2 = c.d();
                ArrayList arrayList = new ArrayList();
                while (b2.e()) {
                    arrayList.add(d2.generateCertificate(b2.e(b2.c(24))));
                }
                return new vce(d2.generateCertPath(arrayList));
            } catch (GeneralSecurityException e) {
                throw new vdb("Cannot parse X.509 certificate chain provided by peer", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.BAD_CERTIFICATE), e);
            }
        }
        throw new IllegalArgumentException("Certificate type " + certificateType + " not supported!");
    }

    private static PublicKey a(byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        try {
            String g = vbm.g(bArr);
            if (g != null) {
                veu a2 = veu.e.a(g);
                if (a2 == null || a2.b() == null) {
                    return null;
                }
                return a2.b().generatePublic(new X509EncodedKeySpec(bArr));
            }
            b.info("Could not reconstruct the peer's public key [{}]", vcb.e(bArr));
            return null;
        } catch (IllegalArgumentException e) {
            b.warn("Could not reconstruct the peer's public key", (Throwable) e);
            return null;
        } catch (GeneralSecurityException e2) {
            b.warn("Could not reconstruct the peer's public key", (Throwable) e2);
            return null;
        }
    }
}
