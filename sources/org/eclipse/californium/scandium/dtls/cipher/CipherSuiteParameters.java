package org.eclipse.californium.scandium.dtls.cipher;

import defpackage.vcb;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.List;
import org.eclipse.californium.elements.config.CertificateAuthenticationMode;
import org.eclipse.californium.scandium.dtls.CertificateType;
import org.eclipse.californium.scandium.dtls.SignatureAndHashAlgorithm;
import org.eclipse.californium.scandium.dtls.SupportedPointFormatsExtension;
import org.eclipse.californium.scandium.dtls.cipher.XECDHECryptography;

/* loaded from: classes7.dex */
public class CipherSuiteParameters {

    /* renamed from: a, reason: collision with root package name */
    private CertificateAuthenticationMode f15910a;
    private CertificateBasedMismatch b;
    private List<CertificateType> c;
    private List<X509Certificate> d;
    private List<CipherSuite> e;
    private SupportedPointFormatsExtension.ECPointFormat f;
    private PublicKey g;
    private GeneralMismatch h;
    private CertificateType i;
    private CipherSuite j;
    private CertificateType k;
    private SignatureAndHashAlgorithm l;
    private List<CertificateType> m;
    private List<SignatureAndHashAlgorithm> n;
    private XECDHECryptography.SupportedGroup o;
    private List<XECDHECryptography.SupportedGroup> q;

    public enum GeneralMismatch {
        CIPHER_SUITE("Peers have no common cipher suite."),
        EC_FORMAT("Peers have no common ec-point format."),
        EC_GROUPS("Peers have no common ec-groups.");

        private final String message;

        GeneralMismatch(String str) {
            this.message = str;
        }

        public String getMessage() {
            return this.message;
        }
    }

    public enum CertificateBasedMismatch {
        SERVER_CERT_TYPE("Peers have no common server certificate type."),
        CLIENT_CERT_TYPE("Peers have no common client certificate type."),
        SIGNATURE_ALGORITHMS("Peers have no common signature and hash algorithm."),
        CERTIFICATE_EC_GROUPS("The peer's node certificate uses no common ec-group."),
        CERTIFICATE_SIGNATURE_ALGORITHMS("The peer's node certificate uses no common signature and hash algorithm."),
        CERTIFICATE_PATH_SIGNATURE_ALGORITHMS("The peer's certificate-chain uses no common signature and hash algorithm.");

        private final String message;

        CertificateBasedMismatch(String str) {
            this.message = str;
        }

        public String getMessage() {
            return this.message;
        }
    }

    public CipherSuiteParameters(PublicKey publicKey, List<X509Certificate> list, CertificateAuthenticationMode certificateAuthenticationMode, List<CipherSuite> list2, List<CertificateType> list3, List<CertificateType> list4, List<XECDHECryptography.SupportedGroup> list5, List<SignatureAndHashAlgorithm> list6, SupportedPointFormatsExtension.ECPointFormat eCPointFormat) {
        this.g = publicKey;
        this.d = list;
        this.f15910a = certificateAuthenticationMode;
        this.e = list2;
        this.m = list3;
        this.c = list4;
        this.q = list5;
        this.n = list6;
        this.f = eCPointFormat;
    }

    public CipherSuiteParameters(PublicKey publicKey, List<X509Certificate> list, CipherSuiteParameters cipherSuiteParameters) {
        this.g = publicKey;
        this.d = list;
        this.f15910a = cipherSuiteParameters.f15910a;
        this.e = cipherSuiteParameters.e;
        this.m = cipherSuiteParameters.m;
        this.c = cipherSuiteParameters.c;
        this.q = cipherSuiteParameters.q;
        this.n = cipherSuiteParameters.n;
        this.f = cipherSuiteParameters.f;
        this.j = cipherSuiteParameters.j;
        this.k = cipherSuiteParameters.k;
        this.i = cipherSuiteParameters.i;
        this.o = cipherSuiteParameters.o;
        this.l = cipherSuiteParameters.l;
    }

    public List<CipherSuite> d() {
        return this.e;
    }

    public List<CertificateType> o() {
        return this.m;
    }

    public List<CertificateType> a() {
        return this.c;
    }

    public List<XECDHECryptography.SupportedGroup> t() {
        return this.q;
    }

    public List<SignatureAndHashAlgorithm> p() {
        return this.n;
    }

    public SupportedPointFormatsExtension.ECPointFormat j() {
        return this.f;
    }

    public PublicKey h() {
        return this.g;
    }

    public List<X509Certificate> c() {
        return this.d;
    }

    public CertificateBasedMismatch e() {
        return this.b;
    }

    public CertificateAuthenticationMode b() {
        return this.f15910a;
    }

    public CipherSuite f() {
        return this.j;
    }

    public CertificateType k() {
        return this.k;
    }

    public CertificateType n() {
        return this.i;
    }

    public XECDHECryptography.SupportedGroup m() {
        return this.o;
    }

    public SignatureAndHashAlgorithm l() {
        return this.l;
    }

    public void e(GeneralMismatch generalMismatch) {
        this.h = generalMismatch;
    }

    public void d(CertificateBasedMismatch certificateBasedMismatch) {
        this.b = certificateBasedMismatch;
    }

    public void c(CipherSuite cipherSuite) {
        if (cipherSuite == null) {
            throw new NullPointerException("Cipher suite must not be null!");
        }
        if (!this.e.contains(cipherSuite)) {
            throw new IllegalArgumentException(cipherSuite + " is no common cipher suite!");
        }
        this.j = cipherSuite;
    }

    public void d(CertificateType certificateType) {
        if (certificateType != null && !this.m.contains(certificateType)) {
            throw new IllegalArgumentException(certificateType + " server certificate type is no common certificate type.");
        }
        this.k = certificateType;
    }

    public void a(CertificateType certificateType) {
        if (certificateType != null && !this.c.contains(certificateType)) {
            throw new IllegalArgumentException(certificateType + " client certificate type is no common certificate type.");
        }
        this.i = certificateType;
    }

    public void d(XECDHECryptography.SupportedGroup supportedGroup) {
        if (supportedGroup != null && !this.q.contains(supportedGroup)) {
            throw new IllegalArgumentException(supportedGroup + " is no common group/curve.");
        }
        this.o = supportedGroup;
    }

    public void d(SignatureAndHashAlgorithm signatureAndHashAlgorithm) {
        if (signatureAndHashAlgorithm != null && !this.n.contains(signatureAndHashAlgorithm)) {
            throw new IllegalArgumentException(signatureAndHashAlgorithm + " is no common signature and hash algorithm.");
        }
        this.l = signatureAndHashAlgorithm;
    }

    public String i() {
        GeneralMismatch generalMismatch = this.h;
        if (generalMismatch != null) {
            return generalMismatch.getMessage();
        }
        CertificateBasedMismatch certificateBasedMismatch = this.b;
        if (certificateBasedMismatch != null) {
            return certificateBasedMismatch.getMessage();
        }
        return null;
    }

    public String g() {
        String i = i();
        if (i == null) {
            return i;
        }
        StringBuilder sb = new StringBuilder(i);
        sb.append(vcb.a());
        sb.append("\tcipher suites: ");
        Iterator<CipherSuite> it = this.e.iterator();
        while (it.hasNext()) {
            sb.append(it.next().name());
            sb.append(",");
        }
        sb.setLength(sb.length() - 1);
        if (this.b == CertificateBasedMismatch.CERTIFICATE_EC_GROUPS) {
            sb.append(vcb.a());
            sb.append("\t\tec-groups: ");
            Iterator<XECDHECryptography.SupportedGroup> it2 = this.q.iterator();
            while (it2.hasNext()) {
                sb.append(it2.next().name());
                sb.append(",");
            }
            sb.setLength(sb.length() - 1);
        } else if (this.b == CertificateBasedMismatch.CERTIFICATE_SIGNATURE_ALGORITHMS || this.b == CertificateBasedMismatch.CERTIFICATE_PATH_SIGNATURE_ALGORITHMS) {
            sb.append(vcb.a());
            sb.append("\t\tsignatures: ");
            Iterator<SignatureAndHashAlgorithm> it3 = this.n.iterator();
            while (it3.hasNext()) {
                sb.append(it3.next().c());
                sb.append(",");
            }
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("cipher suites: ");
        for (CipherSuite cipherSuite : this.e) {
            if (this.j == cipherSuite) {
                sb.append("#");
            }
            sb.append(cipherSuite.name());
            sb.append(",");
        }
        sb.setLength(sb.length() - 1);
        sb.append(vcb.a());
        List<X509Certificate> list = this.d;
        if (list != null && !list.isEmpty()) {
            sb.append("x509-DN: [");
            sb.append(this.d.get(0).getSubjectX500Principal().getName());
            sb.append("]");
            sb.append(vcb.a());
        }
        if (this.g != null) {
            if (this.f15910a == CertificateAuthenticationMode.NEEDED) {
                sb.append("client certificate required");
            } else if (this.f15910a == CertificateAuthenticationMode.WANTED) {
                sb.append("client certificate wanted");
            } else {
                sb.append("no client certificate");
            }
            sb.append(vcb.a());
        }
        sb.append("server certificate types: ");
        for (CertificateType certificateType : this.m) {
            if (this.k == certificateType) {
                sb.append("#");
            }
            sb.append(certificateType.name());
            sb.append(",");
        }
        sb.setLength(sb.length() - 1);
        sb.append(vcb.a());
        sb.append("client certificate types: ");
        for (CertificateType certificateType2 : this.c) {
            if (this.i == certificateType2) {
                sb.append("#");
            }
            sb.append(certificateType2.name());
            sb.append(",");
        }
        sb.setLength(sb.length() - 1);
        sb.append(vcb.a());
        sb.append("ec-groups: ");
        for (XECDHECryptography.SupportedGroup supportedGroup : this.q) {
            if (this.o == supportedGroup) {
                sb.append("#");
            }
            sb.append(supportedGroup.name());
            sb.append(",");
        }
        sb.setLength(sb.length() - 1);
        sb.append(vcb.a());
        sb.append("signatures: ");
        for (SignatureAndHashAlgorithm signatureAndHashAlgorithm : this.n) {
            if (this.l == signatureAndHashAlgorithm) {
                sb.append("#");
            }
            sb.append(signatureAndHashAlgorithm.c());
            sb.append(",");
        }
        sb.setLength(sb.length() - 1);
        sb.append(vcb.a());
        return sb.toString();
    }
}
