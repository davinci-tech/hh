package defpackage;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.eclipse.californium.elements.config.BasicDefinition;
import org.eclipse.californium.elements.config.BasicListDefinition;
import org.eclipse.californium.elements.config.CertificateAuthenticationMode;
import org.eclipse.californium.elements.config.Configuration;
import org.eclipse.californium.scandium.ConnectionListener;
import org.eclipse.californium.scandium.DatagramFilter;
import org.eclipse.californium.scandium.DtlsHealth;
import org.eclipse.californium.scandium.auth.ApplicationLevelInfoSupplier;
import org.eclipse.californium.scandium.config.DtlsConfig;
import org.eclipse.californium.scandium.dtls.CertificateType;
import org.eclipse.californium.scandium.dtls.ConnectionIdGenerator;
import org.eclipse.californium.scandium.dtls.HelloExtension;
import org.eclipse.californium.scandium.dtls.SessionListener;
import org.eclipse.californium.scandium.dtls.SessionStore;
import org.eclipse.californium.scandium.dtls.SignatureAndHashAlgorithm;
import org.eclipse.californium.scandium.dtls.cipher.CipherSuite;
import org.eclipse.californium.scandium.dtls.cipher.CipherSuiteSelector;
import org.eclipse.californium.scandium.dtls.cipher.XECDHECryptography;
import org.eclipse.californium.scandium.dtls.pskstore.AdvancedPskStore;
import org.eclipse.californium.scandium.dtls.resumption.ResumptionVerifier;
import org.eclipse.californium.scandium.dtls.x509.CertificateProvider;
import org.eclipse.californium.scandium.dtls.x509.ConfigurationHelperSetup;
import org.eclipse.californium.scandium.dtls.x509.NewAdvancedCertificateVerifier;

/* loaded from: classes7.dex */
public final class vcd {

    /* renamed from: a, reason: collision with root package name */
    private ApplicationLevelInfoSupplier f17660a;
    private vfd b;
    private InetSocketAddress c;
    private AdvancedPskStore d;
    private NewAdvancedCertificateVerifier e;
    private CertificateProvider f;
    private ConnectionListener g;
    private CipherSuiteSelector h;
    private ConnectionIdGenerator i;
    private Configuration j;
    private DtlsHealth k;
    private String l;
    private DatagramFilter m;
    private ResumptionVerifier n;
    private vdu o;
    private SessionListener p;
    private List<CipherSuite> q;
    private SessionStore r;
    private List<CipherSuite.CertificateKeyAlgorithm> s;
    private String t;
    private List<XECDHECryptography.SupportedGroup> u;
    private Boolean v;
    private List<SignatureAndHashAlgorithm> x;

    private vcd(Configuration configuration) {
        if (configuration == null) {
            throw new NullPointerException("Configuration must not be null!");
        }
        this.j = new Configuration(configuration);
    }

    public Configuration g() {
        return this.j;
    }

    public <T> T d(BasicDefinition<T> basicDefinition) {
        return (T) this.j.a((BasicDefinition) basicDefinition);
    }

    public Long b(vay vayVar, TimeUnit timeUnit) {
        return this.j.a(vayVar, timeUnit);
    }

    public int c(vay vayVar, TimeUnit timeUnit) {
        return this.j.d(vayVar, timeUnit);
    }

    public vdu t() {
        return this.o;
    }

    public Integer h() {
        Integer num = (Integer) this.j.a((BasicDefinition) DtlsConfig.al);
        return num == null ? Integer.valueOf(((Integer) this.j.a((BasicDefinition) DtlsConfig.w)).intValue() / 2) : num;
    }

    public Boolean ae() {
        return this.v;
    }

    public ConnectionIdGenerator l() {
        return this.i;
    }

    @Deprecated
    public Integer aa() {
        return (Integer) this.j.a((BasicDefinition) DtlsConfig.ba);
    }

    public InetSocketAddress e() {
        return this.c;
    }

    public CertificateProvider i() {
        return this.f;
    }

    public CipherSuiteSelector j() {
        return this.h;
    }

    @Deprecated
    public List<CipherSuite> s() {
        return (List) this.j.a((BasicDefinition) DtlsConfig.ab);
    }

    public List<CipherSuite.CertificateKeyAlgorithm> u() {
        return this.s;
    }

    public List<CipherSuite> ab() {
        return this.q;
    }

    public List<SignatureAndHashAlgorithm> z() {
        return this.x;
    }

    public List<XECDHECryptography.SupportedGroup> ac() {
        return this.u;
    }

    public AdvancedPskStore c() {
        return this.d;
    }

    public NewAdvancedCertificateVerifier d() {
        return this.e;
    }

    public ApplicationLevelInfoSupplier b() {
        return this.f17660a;
    }

    @Deprecated
    public CertificateAuthenticationMode f() {
        return (CertificateAuthenticationMode) this.j.a((BasicDefinition) DtlsConfig.f);
    }

    public String k() {
        return this.j.a((BasicDefinition) DtlsConfig.ao) == DtlsConfig.DtlsRole.SERVER_ONLY ? "none" : (String) this.j.a((BasicDefinition) DtlsConfig.k);
    }

    public List<CertificateType> p() {
        CertificateProvider certificateProvider = this.f;
        if (certificateProvider == null) {
            return null;
        }
        return certificateProvider.getSupportedCertificateTypes();
    }

    public List<CertificateType> ad() {
        NewAdvancedCertificateVerifier newAdvancedCertificateVerifier = this.e;
        if (newAdvancedCertificateVerifier == null) {
            return null;
        }
        return newAdvancedCertificateVerifier.getSupportedCertificateTypes();
    }

    public Long a() {
        Long a2 = this.j.a(DtlsConfig.d, TimeUnit.MILLISECONDS);
        if (a2 == null || a2.longValue() > 0) {
            return a2;
        }
        return null;
    }

    public ConnectionListener n() {
        return this.g;
    }

    public SessionListener v() {
        return this.p;
    }

    public DatagramFilter m() {
        return this.m;
    }

    public SessionStore y() {
        return this.r;
    }

    public ResumptionVerifier x() {
        return this.n;
    }

    public String r() {
        return this.l;
    }

    public String w() {
        return this.t;
    }

    @Deprecated
    public int q() {
        return this.j.d(vbc.b, TimeUnit.MILLISECONDS);
    }

    public DtlsHealth o() {
        return this.k;
    }

    @Deprecated
    public Boolean ai() {
        return (Boolean) this.j.a((BasicDefinition) DtlsConfig.af);
    }

    @Deprecated
    public Boolean ah() {
        return (Boolean) this.j.a((BasicDefinition) DtlsConfig.ah);
    }

    @Deprecated
    public Boolean af() {
        return (Boolean) this.j.a((BasicDefinition) DtlsConfig.ae);
    }

    protected Object clone() {
        vcd vcdVar = new vcd(this.j);
        vcdVar.c = this.c;
        vcdVar.e = this.e;
        vcdVar.v = this.v;
        vcdVar.o = this.o;
        vcdVar.d = this.d;
        vcdVar.f = this.f;
        vcdVar.b = this.b;
        vcdVar.h = this.h;
        vcdVar.s = this.s;
        vcdVar.q = this.q;
        vcdVar.x = this.x;
        vcdVar.u = this.u;
        vcdVar.l = this.l;
        vcdVar.t = this.t;
        vcdVar.i = this.i;
        vcdVar.f17660a = this.f17660a;
        vcdVar.g = this.g;
        vcdVar.p = this.p;
        vcdVar.m = this.m;
        vcdVar.r = this.r;
        vcdVar.n = this.n;
        vcdVar.k = this.k;
        return vcdVar;
    }

    public static final class e {
        private vcd e;

        public e(Configuration configuration) {
            this.e = new vcd(configuration);
        }

        public <T> e a(BasicDefinition<T> basicDefinition, T t) {
            this.e.j.e((BasicDefinition<BasicDefinition<T>>) basicDefinition, (BasicDefinition<T>) t);
            return this;
        }

        public <T> e a(BasicListDefinition<T> basicListDefinition, T... tArr) {
            this.e.j.e((BasicListDefinition) basicListDefinition, (Object[]) tArr);
            return this;
        }

        public e e(InetSocketAddress inetSocketAddress) {
            if (!inetSocketAddress.isUnresolved()) {
                this.e.c = inetSocketAddress;
                return this;
            }
            throw new IllegalArgumentException("Bind address must not be unresolved");
        }

        public e a(AdvancedPskStore advancedPskStore) {
            this.e.d = advancedPskStore;
            return this;
        }

        public e d(NewAdvancedCertificateVerifier newAdvancedCertificateVerifier) {
            if (newAdvancedCertificateVerifier != null) {
                this.e.e = newAdvancedCertificateVerifier;
                return this;
            }
            throw new NullPointerException("CertificateVerifier must not be null");
        }

        public e b(ConnectionIdGenerator connectionIdGenerator) {
            this.e.i = connectionIdGenerator;
            return this;
        }

        public vcd e() {
            vcd vcdVar = this.e;
            vcdVar.l = vcb.h(vcdVar.l);
            if (this.e.c == null) {
                this.e.c = new InetSocketAddress(0);
            }
            if (this.e.v == null) {
                this.e.v = Boolean.FALSE;
            }
            int intValue = ((Integer) this.e.d(DtlsConfig.w)).intValue();
            if (intValue >= 1) {
                Integer num = (Integer) this.e.j.a((BasicDefinition) DtlsConfig.al);
                if (num != null && num.intValue() >= intValue) {
                    throw new IllegalStateException("Backoff for handshake retransmissions (" + num + ") must be less than the maximum retransmissions (" + intValue + ")!");
                }
                int c = this.e.c(DtlsConfig.am, TimeUnit.MILLISECONDS);
                int c2 = this.e.c(DtlsConfig.y, TimeUnit.MILLISECONDS);
                if (c > c2) {
                    throw new IllegalStateException("Retransmission timeout " + c + " is more than the maximum " + c2 + "!");
                }
                if (c <= 0) {
                    throw new IllegalStateException("Retransmission timeout " + c + " must not be 0 or less!");
                }
                if (c2 <= 0) {
                    throw new IllegalStateException("Maximum retransmission timeout " + c2 + " must not be 0 or less!");
                }
                if (((Float) this.e.d(DtlsConfig.an)).floatValue() < 1.0f) {
                    throw new IllegalStateException("Retransmission timeout random factor " + this.e.d(DtlsConfig.an) + " must not be less than 1.0!");
                }
                if (((Float) this.e.d(DtlsConfig.ak)).floatValue() < 1.0f) {
                    throw new IllegalStateException("Retransmission timeout scale factor " + this.e.d(DtlsConfig.ak) + " must not be less than 1.0!");
                }
                Integer num2 = (Integer) this.e.d(DtlsConfig.v);
                Integer num3 = (Integer) this.e.d(DtlsConfig.aa);
                if (num2 != null && num3 != null && num2.intValue() > num3.intValue()) {
                    throw new IllegalStateException("MTU (" + num2 + " bytes) is larger than the limit (" + num3 + " bytes)!");
                }
                Integer num4 = (Integer) this.e.d(DtlsConfig.ag);
                if (num4 != null && num4.intValue() > 16384) {
                    throw new IllegalStateException("Record size limit " + num4 + " must be less than 16384!");
                }
                DtlsConfig.DtlsRole dtlsRole = (DtlsConfig.DtlsRole) this.e.d(DtlsConfig.ao);
                if (dtlsRole == DtlsConfig.DtlsRole.SERVER_ONLY) {
                    if (this.e.f() == CertificateAuthenticationMode.NONE && this.e.e != null) {
                        throw new IllegalStateException("configured certificate verifier is not used for client authentication mode NONE!");
                    }
                    if (this.e.a() != null) {
                        throw new IllegalStateException("DTLS_AUTO_HANDSHAKE_TIMEOUT must not be used with SERVER_ONLY!");
                    }
                }
                if (((Boolean) this.e.d(DtlsConfig.aj)).booleanValue() && !((Boolean) this.e.d(DtlsConfig.ad)).booleanValue()) {
                    throw new IllegalStateException("Removing stale double principals requires the read-write-lock connection store!!");
                }
                long longValue = this.e.b(DtlsConfig.n, TimeUnit.NANOSECONDS).longValue();
                int intValue2 = ((Integer) this.e.d(DtlsConfig.m)).intValue();
                if (!((longValue == 0) ^ (intValue2 == 0))) {
                    if (this.e.m == null && ((Boolean) this.e.d(DtlsConfig.ay)).booleanValue()) {
                        vcd vcdVar2 = this.e;
                        vcdVar2.m = new vbx(vcdVar2.j);
                    }
                    if (this.e.m == null && intValue2 > 0) {
                        throw new IllegalStateException("Enabled DTLS MAC error filter requires a record-filter!");
                    }
                    Integer aa = this.e.aa();
                    if (aa != null) {
                        HelloExtension.ExtensionType extensionTypeById = HelloExtension.ExtensionType.getExtensionTypeById(aa.intValue());
                        if (extensionTypeById == null) {
                            throw new IllegalStateException(aa + " code point is not supported for extensions!");
                        }
                        if (extensionTypeById != HelloExtension.ExtensionType.CONNECTION_ID && extensionTypeById.getReplacementType() != HelloExtension.ExtensionType.CONNECTION_ID) {
                            throw new IllegalStateException(aa + " (" + extensionTypeById + ") is no supported CID extension code point!");
                        }
                    }
                    vcd vcdVar3 = this.e;
                    vcdVar3.u = (List) vcdVar3.j.a((BasicDefinition) DtlsConfig.g);
                    if (this.e.u == null) {
                        this.e.u = Collections.emptyList();
                    }
                    vcd vcdVar4 = this.e;
                    vcdVar4.x = (List) vcdVar4.j.a((BasicDefinition) DtlsConfig.as);
                    if (this.e.x == null) {
                        this.e.x = Collections.emptyList();
                    }
                    vcd vcdVar5 = this.e;
                    vcdVar5.s = (List) vcdVar5.j.a((BasicDefinition) DtlsConfig.c);
                    if (this.e.s == null) {
                        this.e.s = Collections.emptyList();
                    }
                    if (this.e.h == null && dtlsRole != DtlsConfig.DtlsRole.CLIENT_ONLY) {
                        this.e.h = new vel();
                    }
                    if (this.e.n == null && ((Boolean) this.e.d(DtlsConfig.ar)).booleanValue() && dtlsRole != DtlsConfig.DtlsRole.CLIENT_ONLY) {
                        this.e.n = new vfc();
                    }
                    CertificateProvider certificateProvider = this.e.f;
                    NewAdvancedCertificateVerifier newAdvancedCertificateVerifier = this.e.e;
                    if (this.e.b == null) {
                        vfd vfdVar = new vfd();
                        if (certificateProvider instanceof ConfigurationHelperSetup) {
                            ((ConfigurationHelperSetup) certificateProvider).setupConfigurationHelper(vfdVar);
                            this.e.b = vfdVar;
                        }
                        if (newAdvancedCertificateVerifier instanceof ConfigurationHelperSetup) {
                            ((ConfigurationHelperSetup) newAdvancedCertificateVerifier).setupConfigurationHelper(vfdVar);
                            this.e.b = vfdVar;
                        }
                    }
                    vcd vcdVar6 = this.e;
                    vcdVar6.q = (List) vcdVar6.j.a((BasicDefinition) DtlsConfig.b);
                    if (this.e.q == null || this.e.q.isEmpty()) {
                        d();
                    }
                    if (this.e.q.isEmpty()) {
                        throw new IllegalStateException("Supported cipher suites must be set either explicitly or implicitly by means of setting the identity or PSK store");
                    }
                    if (this.e.ai().booleanValue()) {
                        a(this.e.q);
                    }
                    boolean z = false;
                    boolean z2 = false;
                    boolean z3 = false;
                    for (CipherSuite cipherSuite : this.e.q) {
                        if (cipherSuite.isPskBased()) {
                            a(cipherSuite);
                            z = true;
                        } else if (cipherSuite.requiresServerCertificateMessage()) {
                            d(cipherSuite);
                            z2 = true;
                        }
                        if (cipherSuite.isEccBased()) {
                            z3 = true;
                        }
                    }
                    if (!z && this.e.d != null) {
                        throw new IllegalStateException("Advanced PSK store set, but no PSK cipher suite!");
                    }
                    if (z2) {
                        if (this.e.x.isEmpty()) {
                            ArrayList arrayList = new ArrayList(SignatureAndHashAlgorithm.c);
                            if (this.e.b != null) {
                                vfb.e(arrayList, this.e.b.c());
                            }
                            this.e.x = arrayList;
                        }
                        if (this.e.s.isEmpty()) {
                            ArrayList arrayList2 = new ArrayList();
                            if (SignatureAndHashAlgorithm.b(this.e.x, CipherSuite.CertificateKeyAlgorithm.EC)) {
                                vfb.a(arrayList2, CipherSuite.CertificateKeyAlgorithm.EC);
                            }
                            if (SignatureAndHashAlgorithm.b(this.e.x, CipherSuite.CertificateKeyAlgorithm.RSA)) {
                                vfb.a(arrayList2, CipherSuite.CertificateKeyAlgorithm.RSA);
                            }
                            if (this.e.g().a((BasicDefinition) DtlsConfig.ao) == DtlsConfig.DtlsRole.CLIENT_ONLY) {
                                vfb.a(arrayList2, CipherSuite.CertificateKeyAlgorithm.EC);
                            }
                            this.e.s = arrayList2;
                        }
                    } else {
                        if (!this.e.x.isEmpty()) {
                            throw new IllegalStateException("supported signature and hash algorithms set, but no ecdhe based cipher suite!");
                        }
                        if (certificateProvider == null) {
                            if (this.e.e != null) {
                                throw new IllegalStateException("certificate trust set, but no certificate based cipher suite!");
                            }
                        } else {
                            throw new IllegalStateException("certificate identity set, but no certificate based cipher suite!");
                        }
                    }
                    if (z3) {
                        if (this.e.u.isEmpty()) {
                            ArrayList arrayList3 = new ArrayList(XECDHECryptography.SupportedGroup.getPreferredGroups());
                            if (this.e.b != null) {
                                vfb.e(arrayList3, this.e.b.a());
                            }
                            this.e.u = arrayList3;
                        }
                    } else if (!this.e.u.isEmpty()) {
                        throw new IllegalStateException("supported groups set, but no ecdhe based cipher suite!");
                    }
                    if (this.e.ah().booleanValue()) {
                        d(this.e.u);
                    }
                    if (this.e.af().booleanValue()) {
                        e(this.e.x);
                    }
                    if (this.e.b != null) {
                        this.e.b.b(this.e.x);
                        this.e.b.e(this.e.u);
                        if (certificateProvider != null && certificateProvider.getSupportedCertificateTypes().contains(CertificateType.X_509)) {
                            if (dtlsRole == DtlsConfig.DtlsRole.CLIENT_ONLY) {
                                if (!this.e.b.d(true)) {
                                    throw new IllegalStateException("certificate has no proper key usage for clients!");
                                }
                            } else if (dtlsRole == DtlsConfig.DtlsRole.SERVER_ONLY) {
                                if (!this.e.b.d(false)) {
                                    throw new IllegalStateException("certificate has no proper key usage for servers!");
                                }
                            } else if (this.e.b.d(true)) {
                                if (!this.e.b.d(false)) {
                                    throw new IllegalStateException("certificate has no proper key usage as servers!");
                                }
                            } else {
                                throw new IllegalStateException("certificate has no proper key usage as clients!");
                            }
                        }
                    }
                    if (((Boolean) this.e.d(DtlsConfig.bg)).booleanValue() && !((Boolean) this.e.d(DtlsConfig.be)).booleanValue() && !CipherSuite.containsPskBasedCipherSuite(this.e.q)) {
                        throw new IllegalStateException("HELLO_VERIFY_REQUEST disabled for PSK, requires at least one PSK cipher suite!");
                    }
                    vcd vcdVar7 = this.e;
                    vcdVar7.s = vfb.a(vcdVar7.s);
                    vcd vcdVar8 = this.e;
                    vcdVar8.q = vfb.a(vcdVar8.q);
                    vcd vcdVar9 = this.e;
                    vcdVar9.u = vfb.a(vcdVar9.u);
                    vcd vcdVar10 = this.e;
                    vcdVar10.x = vfb.a(vcdVar10.x);
                    if (this.e.i == null) {
                        Integer num5 = (Integer) this.e.j.a((BasicDefinition) DtlsConfig.i);
                        Integer num6 = (Integer) this.e.j.a((BasicDefinition) DtlsConfig.h);
                        if (num5 != null) {
                            if (num6 != null) {
                                if (num5.intValue() <= 4) {
                                    throw new IllegalStateException(num5 + " bytes are too small for multiple nodes CID! At least, 5 bytes are required.");
                                }
                                b(new vdo(num6.intValue(), num5.intValue()));
                            } else {
                                b(new veo(num5.intValue()));
                            }
                        }
                    }
                    return this.e;
                }
                throw new IllegalStateException("DTLS MAC error filter configuration ambig! Use 0 for both, or larger than 0 for both!");
            }
            throw new IllegalStateException("Maximum retransmissions " + intValue + " must not be less than 1!");
        }

        private void a(CipherSuite cipherSuite) {
            if (this.e.d != null) {
                if (this.e.d.hasEcdhePskSupported() || !cipherSuite.isEccBased()) {
                    return;
                }
                throw new IllegalStateException("PSK store doesn't support ECDHE! " + cipherSuite.name());
            }
            throw new IllegalStateException("PSK store must be set for configured " + cipherSuite.name());
        }

        private void d(CipherSuite cipherSuite) {
            if (this.e.d(DtlsConfig.ao) == DtlsConfig.DtlsRole.CLIENT_ONLY) {
                if (this.e.e != null) {
                    return;
                }
                throw new IllegalStateException("certificate verifier must be set on client for configured " + cipherSuite.name());
            }
            if (this.e.f != null) {
                List<CipherSuite.CertificateKeyAlgorithm> supportedCertificateKeyAlgorithms = this.e.f.getSupportedCertificateKeyAlgorithms();
                if (!supportedCertificateKeyAlgorithms.contains(cipherSuite.getCertificateKeyAlgorithm())) {
                    throw new IllegalStateException("One of the keys (" + supportedCertificateKeyAlgorithms + ") must be capable for configured " + cipherSuite.name());
                }
                if (this.e.f() == CertificateAuthenticationMode.NONE || this.e.e != null) {
                    return;
                }
                throw new IllegalStateException("certificate verifier must be set for authentication using the configured " + cipherSuite.name());
            }
            throw new IllegalStateException("Identity must be set for configured " + cipherSuite.name());
        }

        private void a(List<CipherSuite> list) {
            StringBuilder sb = new StringBuilder();
            for (CipherSuite cipherSuite : list) {
                if (!cipherSuite.isRecommended()) {
                    if (sb.length() > 0) {
                        sb.append(", ");
                    }
                    sb.append(cipherSuite.name());
                }
            }
            if (sb.length() <= 0) {
                return;
            }
            throw new IllegalStateException("Not recommended cipher suites " + ((Object) sb) + " used! (Requires to set DTLS_RECOMMENDED_CIPHER_SUITES_ONLY to false.)");
        }

        private void d(List<XECDHECryptography.SupportedGroup> list) {
            StringBuilder sb = new StringBuilder();
            for (XECDHECryptography.SupportedGroup supportedGroup : list) {
                if (!supportedGroup.isRecommended()) {
                    if (sb.length() > 0) {
                        sb.append(", ");
                    }
                    sb.append(supportedGroup.name());
                }
            }
            if (sb.length() <= 0) {
                return;
            }
            throw new IllegalStateException("Not recommended supported groups (curves) " + ((Object) sb) + " used! (Requires to set DTLS_RECOMMENDED_CURVES_ONLY to false.)");
        }

        private void e(List<SignatureAndHashAlgorithm> list) {
            StringBuilder sb = new StringBuilder();
            for (SignatureAndHashAlgorithm signatureAndHashAlgorithm : list) {
                if (!signatureAndHashAlgorithm.d()) {
                    if (sb.length() > 0) {
                        sb.append(", ");
                    }
                    sb.append(signatureAndHashAlgorithm.c());
                }
            }
            if (sb.length() <= 0) {
                return;
            }
            throw new IllegalStateException("Not recommended signature and hash algorithms " + ((Object) sb) + " used! (Requires to set DTLS_RECOMMENDED_SIGNATURE_AND_HASH_ALGORITHMS_ONLY to false.)");
        }

        private void d() {
            List arrayList = new ArrayList();
            if (this.e.f != null || this.e.e != null) {
                ArrayList arrayList2 = new ArrayList();
                if (this.e.g().a((BasicDefinition) DtlsConfig.ao) == DtlsConfig.DtlsRole.CLIENT_ONLY) {
                    if (!this.e.s.isEmpty()) {
                        vfb.e(arrayList2, this.e.s);
                    } else {
                        vfb.a(arrayList2, CipherSuite.CertificateKeyAlgorithm.EC);
                        if (this.e.f != null) {
                            vfb.e(arrayList2, this.e.f.getSupportedCertificateKeyAlgorithms());
                        }
                    }
                } else if (this.e.f != null) {
                    vfb.e(arrayList2, this.e.f.getSupportedCertificateKeyAlgorithms());
                }
                if (!arrayList2.isEmpty()) {
                    arrayList.addAll(CipherSuite.getCertificateCipherSuites(this.e.ai().booleanValue(), arrayList2));
                }
            }
            if (this.e.d != null) {
                if (this.e.d.hasEcdhePskSupported()) {
                    arrayList.addAll(CipherSuite.getCipherSuitesByKeyExchangeAlgorithm(this.e.ai().booleanValue(), CipherSuite.KeyExchangeAlgorithm.ECDHE_PSK));
                }
                arrayList.addAll(CipherSuite.getCipherSuitesByKeyExchangeAlgorithm(this.e.ai().booleanValue(), CipherSuite.KeyExchangeAlgorithm.PSK));
            }
            List<CipherSuite> s = this.e.s();
            if (s != null && !s.isEmpty()) {
                arrayList = CipherSuite.preselectCipherSuites(arrayList, s);
            }
            this.e.q = arrayList;
        }
    }
}
