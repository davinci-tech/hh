package defpackage;

import defpackage.vab;
import java.security.GeneralSecurityException;
import java.security.Principal;
import java.util.Objects;
import javax.crypto.SecretKey;
import javax.security.auth.DestroyFailedException;
import javax.security.auth.Destroyable;
import org.eclipse.californium.scandium.auth.PrincipalSerializer;
import org.eclipse.californium.scandium.dtls.CertificateType;
import org.eclipse.californium.scandium.dtls.CompressionMethod;
import org.eclipse.californium.scandium.dtls.SignatureAndHashAlgorithm;
import org.eclipse.californium.scandium.dtls.cipher.CipherSuite;
import org.eclipse.californium.scandium.dtls.cipher.PseudoRandomFunction;
import org.eclipse.californium.scandium.dtls.cipher.XECDHECryptography;
import org.eclipse.californium.scandium.util.ServerName;

/* loaded from: classes7.dex */
public final class vct implements Destroyable {

    /* renamed from: a, reason: collision with root package name */
    private CipherSuite f17673a;
    private CompressionMethod b;
    private XECDHECryptography.SupportedGroup c;
    private long d;
    private boolean e;
    private boolean f;
    private String g;
    private Principal h;
    private int i;
    private SecretKey j;
    private CertificateType k;
    private Integer l;
    private vfe m;
    private CertificateType n;
    private vdu o;
    private SignatureAndHashAlgorithm p;
    private vej s;

    public vct() {
        this.s = vej.a();
        this.o = vdu.f17688a;
        this.i = 16384;
        this.f17673a = CipherSuite.TLS_NULL_WITH_NULL_NULL;
        this.b = CompressionMethod.NULL;
        this.j = null;
        this.n = CertificateType.X_509;
        this.k = CertificateType.X_509;
        this.d = System.currentTimeMillis();
    }

    public vct(vct vctVar) {
        this.s = vej.a();
        this.o = vdu.f17688a;
        this.i = 16384;
        this.f17673a = CipherSuite.TLS_NULL_WITH_NULL_NULL;
        this.b = CompressionMethod.NULL;
        this.j = null;
        this.n = CertificateType.X_509;
        this.k = CertificateType.X_509;
        d(vctVar);
    }

    public void d(vct vctVar) {
        this.d = vctVar.a();
        this.s = vctVar.n();
        this.o = vctVar.m();
        this.j = vctVar.g();
        this.h = vctVar.l();
        this.f17673a = vctVar.e();
        this.b = vctVar.c();
        this.p = vctVar.r();
        this.c = vctVar.d();
        this.e = vctVar.s();
        this.n = vctVar.p();
        this.k = vctVar.t();
        this.l = vctVar.o();
        this.i = vctVar.j();
        b(vctVar.k());
    }

    @Override // javax.security.auth.Destroyable
    public void destroy() throws DestroyFailedException {
        vfh.e(this.j);
        this.j = null;
        this.e = false;
        this.f17673a = CipherSuite.TLS_NULL_WITH_NULL_NULL;
        this.b = CompressionMethod.NULL;
        this.p = null;
        this.c = null;
        this.h = null;
        this.n = CertificateType.X_509;
        this.k = CertificateType.X_509;
    }

    @Override // javax.security.auth.Destroyable
    public boolean isDestroyed() {
        return vfh.b(this.j);
    }

    public vej n() {
        return this.s;
    }

    void e(vej vejVar) {
        if (vejVar == null) {
            throw new NullPointerException("session identifier must not be null!");
        }
        if (!vejVar.equals(this.s) || vejVar.d()) {
            vfh.e(this.j);
            this.j = null;
            this.s = vejVar;
            return;
        }
        throw new IllegalArgumentException("no new session identifier?");
    }

    public vdu m() {
        return this.o;
    }

    void e(vdu vduVar) {
        if (!vdu.f17688a.equals(vduVar)) {
            throw new IllegalArgumentException(vduVar + " is not supported!");
        }
        this.o = vdu.f17688a;
    }

    public long a() {
        return this.d;
    }

    public String h() {
        return this.g;
    }

    public void a(String str) {
        this.m = null;
        this.g = str;
        if (str != null) {
            this.m = vfe.c(ServerName.d(ServerName.NameType.HOST_NAME, str.getBytes(ServerName.f15914a)));
        }
    }

    public vfe k() {
        return this.m;
    }

    public void b(vfe vfeVar) {
        ServerName b;
        this.g = null;
        this.m = vfeVar;
        if (vfeVar == null || (b = vfeVar.b(ServerName.NameType.HOST_NAME)) == null) {
            return;
        }
        this.g = b.e();
    }

    public boolean q() {
        return this.f;
    }

    void b(boolean z) {
        this.f = z;
    }

    public void d(vab.d dVar) {
        Object obj;
        if (this.s.d()) {
            obj = new vbj(("TIME:" + Long.toString(this.d)).getBytes());
        } else {
            obj = this.s;
        }
        dVar.d(uzz.s, obj);
        dVar.d(uzz.j, this.f17673a.name());
        if (this.e) {
            dVar.d(uzz.h, Boolean.TRUE);
        }
    }

    public CipherSuite e() {
        return this.f17673a;
    }

    void d(CipherSuite cipherSuite) {
        if (cipherSuite == null) {
            throw new NullPointerException("Negotiated cipher suite must not be null!");
        }
        if (!cipherSuite.isValidForNegotiation()) {
            throw new IllegalArgumentException("Negotiated cipher suite must be valid for negotiation!");
        }
        this.f17673a = cipherSuite;
    }

    public CompressionMethod c() {
        return this.b;
    }

    void a(CompressionMethod compressionMethod) {
        this.b = compressionMethod;
    }

    public final CipherSuite.KeyExchangeAlgorithm f() {
        CipherSuite cipherSuite = this.f17673a;
        if (cipherSuite == null) {
            throw new IllegalStateException("Cipher suite has not been set (yet)");
        }
        return cipherSuite.getKeyExchange();
    }

    public void e(boolean z) {
        this.e = z;
    }

    public boolean s() {
        return this.e;
    }

    public SecretKey g() {
        return vfh.a(this.j);
    }

    public void e(SecretKey secretKey) {
        if (this.j == null) {
            if (!this.s.d()) {
                if (secretKey == null) {
                    throw new NullPointerException("Master secret must not be null");
                }
                byte[] encoded = secretKey.getEncoded();
                vbj.b(encoded);
                if (encoded.length != PseudoRandomFunction.Label.MASTER_SECRET_LABEL.length()) {
                    throw new IllegalArgumentException(String.format("Master secret must consist of of exactly %d bytes but has %d bytes", Integer.valueOf(PseudoRandomFunction.Label.MASTER_SECRET_LABEL.length()), Integer.valueOf(encoded.length)));
                }
                this.j = vfh.a(secretKey);
            }
            this.d = System.currentTimeMillis();
            return;
        }
        throw new IllegalStateException("master secret already available!");
    }

    public int i() {
        CipherSuite cipherSuite = this.f17673a;
        if (cipherSuite == null) {
            throw new IllegalStateException("Missing cipher suite.");
        }
        return cipherSuite.getMaxCiphertextExpansion();
    }

    void b(int i) {
        if (i < 0 || i > 16384) {
            throw new IllegalArgumentException("Max. fragment length must be in range [0...16384]");
        }
        this.i = i;
    }

    public int j() {
        return this.i;
    }

    void c(int i) {
        this.l = Integer.valueOf(vdx.c(i));
    }

    public Integer o() {
        return this.l;
    }

    public int b() {
        Integer num = this.l;
        if (num != null) {
            return num.intValue();
        }
        return this.i;
    }

    CertificateType p() {
        return this.n;
    }

    void c(CertificateType certificateType) {
        this.n = certificateType;
    }

    public CertificateType t() {
        return this.k;
    }

    void e(CertificateType certificateType) {
        this.k = certificateType;
    }

    public SignatureAndHashAlgorithm r() {
        return this.p;
    }

    void c(SignatureAndHashAlgorithm signatureAndHashAlgorithm) {
        this.p = signatureAndHashAlgorithm;
    }

    public XECDHECryptography.SupportedGroup d() {
        return this.c;
    }

    void e(XECDHECryptography.SupportedGroup supportedGroup) {
        this.c = supportedGroup;
    }

    public Principal l() {
        return this.h;
    }

    public void d(Principal principal) {
        if (principal == null) {
            throw new NullPointerException("Peer identity must not be null");
        }
        this.h = principal;
    }

    public int hashCode() {
        vej vejVar = this.s;
        return vejVar == null ? (int) this.d : vejVar.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        vct vctVar = (vct) obj;
        return vfh.c(this.j, vctVar.j) && vbj.d(this.s, vctVar.s) && this.f17673a == vctVar.f17673a && this.b == vctVar.b && this.e == vctVar.e && this.f == vctVar.f && this.n == vctVar.n && this.k == vctVar.k && this.c == vctVar.c && this.d == vctVar.d && Objects.equals(this.p, vctVar.p) && Objects.equals(this.m, vctVar.m) && Objects.equals(this.l, vctVar.l) && Objects.equals(this.h, vctVar.h) && Objects.equals(this.o, vctVar.o);
    }

    public void d(vbo vboVar) {
        int c = vbt.c(vboVar, 2, 16);
        vboVar.c(this.d, 64);
        if (this.m == null) {
            vboVar.b(0, 8);
        } else {
            vboVar.b(1, 8);
            this.m.b(vboVar);
        }
        Integer num = this.l;
        if (num != null) {
            vboVar.b(num.intValue(), 16);
        } else {
            vboVar.b(65535, 16);
        }
        vboVar.b(this.i, 16);
        vboVar.b(this.s, 8);
        vboVar.b(this.f17673a.getCode(), 16);
        vboVar.b(this.b.getCode(), 8);
        vboVar.b(this.n.getCode(), 8);
        vboVar.b(this.k.getCode(), 8);
        vboVar.b(this.e ? 1 : 0, 8);
        vfg.a(vboVar, this.j);
        if (this.p == null) {
            vboVar.b(0, 8);
        } else {
            vboVar.b(1, 8);
            vboVar.b(this.p.a().getCode(), 8);
            vboVar.b(this.p.e().getCode(), 8);
        }
        if (this.c == null) {
            vboVar.b(0, 8);
        } else {
            vboVar.b(1, 8);
            vboVar.b(this.c.getId(), 16);
        }
        if (this.h == null) {
            vboVar.b(0, 8);
        } else {
            vboVar.b(1, 8);
            PrincipalSerializer.d(this.h, vboVar);
        }
        vbt.d(vboVar, c, 16);
    }

    public static vct b(vbn vbnVar) {
        int a2 = vbt.a(vbnVar, 2, 16);
        if (a2 > 0) {
            return new vct(vbnVar.b(a2));
        }
        return null;
    }

    private vct(vbn vbnVar) {
        this.s = vej.a();
        this.o = vdu.f17688a;
        this.i = 16384;
        this.f17673a = CipherSuite.TLS_NULL_WITH_NULL_NULL;
        this.b = CompressionMethod.NULL;
        this.j = null;
        this.n = CertificateType.X_509;
        this.k = CertificateType.X_509;
        this.d = vbnVar.d(64);
        if (vbnVar.c() == 1) {
            vfe e = vfe.e();
            this.m = e;
            try {
                e.a(vbnVar);
                ServerName b = this.m.b(ServerName.NameType.HOST_NAME);
                if (b != null) {
                    this.g = b.e();
                }
            } catch (IllegalArgumentException unused) {
                this.m = null;
            }
        }
        int c = vbnVar.c(16);
        if (c < 65535) {
            this.l = Integer.valueOf(c);
        }
        this.i = vbnVar.c(16);
        byte[] h = vbnVar.h(8);
        if (h != null) {
            this.s = new vej(h);
        }
        int c2 = vbnVar.c(16);
        CipherSuite typeByCode = CipherSuite.getTypeByCode(c2);
        this.f17673a = typeByCode;
        if (typeByCode == null) {
            throw new IllegalArgumentException("unknown cipher suite 0x" + Integer.toHexString(c2) + "!");
        }
        int c3 = vbnVar.c(8);
        CompressionMethod methodByCode = CompressionMethod.getMethodByCode(c3);
        this.b = methodByCode;
        if (methodByCode == null) {
            throw new IllegalArgumentException("unknown compression method 0x" + Integer.toHexString(c3) + "!");
        }
        int c4 = vbnVar.c(8);
        CertificateType typeFromCode = CertificateType.getTypeFromCode(c4);
        this.n = typeFromCode;
        if (typeFromCode == null) {
            throw new IllegalArgumentException("unknown send certificate type 0x" + Integer.toHexString(c4) + "!");
        }
        int c5 = vbnVar.c(8);
        CertificateType typeFromCode2 = CertificateType.getTypeFromCode(c5);
        this.k = typeFromCode2;
        if (typeFromCode2 == null) {
            throw new IllegalArgumentException("unknown send certificate type 0x" + Integer.toHexString(c5) + "!");
        }
        this.e = vbnVar.c(8) == 1;
        this.j = vfg.e(vbnVar);
        if (vbnVar.c() == 1) {
            this.p = new SignatureAndHashAlgorithm(vbnVar.c(8), vbnVar.c(8));
        }
        if (vbnVar.c() == 1) {
            int c6 = vbnVar.c(16);
            XECDHECryptography.SupportedGroup fromId = XECDHECryptography.SupportedGroup.fromId(c6);
            this.c = fromId;
            if (fromId == null) {
                throw new IllegalArgumentException("unknown ec-group 0x" + Integer.toHexString(c6) + "!");
            }
        }
        if (vbnVar.c() == 1) {
            try {
                this.h = PrincipalSerializer.e(vbnVar);
            } catch (GeneralSecurityException e2) {
                throw new IllegalArgumentException("principal failure", e2);
            }
        }
        vbnVar.b("dtls-session");
    }
}
