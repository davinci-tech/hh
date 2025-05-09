package defpackage;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.security.GeneralSecurityException;
import java.security.PublicKey;
import java.security.cert.CertPath;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.security.auth.x500.X500Principal;
import org.eclipse.californium.elements.util.SslContextUtil;
import org.eclipse.californium.scandium.dtls.AlertMessage;
import org.eclipse.californium.scandium.dtls.CertificateType;
import org.eclipse.californium.scandium.dtls.HandshakeResultHandler;
import org.eclipse.californium.scandium.dtls.x509.ConfigurationHelperSetup;
import org.eclipse.californium.scandium.dtls.x509.NewAdvancedCertificateVerifier;
import org.eclipse.californium.scandium.util.ServerName;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public class vez implements NewAdvancedCertificateVerifier, ConfigurationHelperSetup {
    private static final X509Certificate[] d = new X509Certificate[0];
    private static final vas[] e = new vas[0];

    /* renamed from: a, reason: collision with root package name */
    protected final Logger f17701a = vha.a(getClass());
    private final List<CertificateType> b;
    private final X509Certificate[] c;
    private final boolean h;
    private final Set<vas> i;

    @Override // org.eclipse.californium.scandium.dtls.x509.NewAdvancedCertificateVerifier
    public void setResultHandler(HandshakeResultHandler handshakeResultHandler) {
    }

    public vez(X509Certificate[] x509CertificateArr, vas[] vasVarArr, List<CertificateType> list, boolean z) {
        if (x509CertificateArr == null && vasVarArr == null) {
            throw new IllegalArgumentException("no trusts provided!");
        }
        if (list == null) {
            list = new ArrayList<>(2);
            if (vasVarArr != null) {
                list.add(CertificateType.RAW_PUBLIC_KEY);
            }
            if (x509CertificateArr != null) {
                list.add(CertificateType.X_509);
            }
        } else {
            if (list.isEmpty()) {
                throw new IllegalArgumentException("list of supported certificate types must not be empty!");
            }
            if (list.contains(CertificateType.RAW_PUBLIC_KEY) && vasVarArr == null) {
                throw new IllegalArgumentException("RPK support requires RPK trusts!");
            }
            if (list.contains(CertificateType.X_509) && x509CertificateArr == null) {
                throw new IllegalArgumentException("x509support requires x509 trusts!");
            }
        }
        this.c = x509CertificateArr == null ? null : (X509Certificate[]) Arrays.copyOf(x509CertificateArr, x509CertificateArr.length);
        this.i = vasVarArr != null ? new HashSet(Arrays.asList(vasVarArr)) : null;
        this.b = Collections.unmodifiableList(list);
        this.h = z;
    }

    @Override // org.eclipse.californium.scandium.dtls.x509.ConfigurationHelperSetup
    public void setupConfigurationHelper(vfd vfdVar) {
        vfdVar.a(this.c);
        Set<vas> set = this.i;
        if (set != null) {
            Iterator<vas> it = set.iterator();
            while (it.hasNext()) {
                vfdVar.a(it.next().b());
            }
        }
    }

    @Override // org.eclipse.californium.scandium.dtls.x509.NewAdvancedCertificateVerifier
    public List<CertificateType> getSupportedCertificateTypes() {
        return this.b;
    }

    @Override // org.eclipse.californium.scandium.dtls.x509.NewAdvancedCertificateVerifier
    public vcl verifyCertificate(vcp vcpVar, vfe vfeVar, InetSocketAddress inetSocketAddress, boolean z, boolean z2, boolean z3, vce vceVar) {
        this.f17701a.debug("Verify for SNI: {}, IP: {}", vfeVar, vcb.b((SocketAddress) inetSocketAddress));
        try {
            CertPath c = vceVar.c();
            if (c == null) {
                if (this.i == null) {
                    throw new vdb("RPK verification not enabled!", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.UNSUPPORTED_CERTIFICATE));
                }
                PublicKey e2 = vceVar.e();
                if (!this.i.isEmpty()) {
                    if (!this.i.contains(new vas(e2))) {
                        this.f17701a.debug("Certificate validation failed: Raw public key is not trusted");
                        throw new vdb("Raw public key is not trusted!", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.BAD_CERTIFICATE));
                    }
                }
                return new vcl(vcpVar, e2, (Object) null);
            }
            if (this.c == null) {
                throw new vdb("x509 verification not enabled!", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.UNSUPPORTED_CERTIFICATE));
            }
            try {
                if (!vceVar.b()) {
                    Certificate certificate = c.getCertificates().get(0);
                    if (certificate instanceof X509Certificate) {
                        X509Certificate x509Certificate = (X509Certificate) certificate;
                        if (!vbi.b(x509Certificate, z)) {
                            this.f17701a.debug("Certificate validation failed: key usage doesn't match");
                            throw new vdb("Key Usage doesn't match!", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.BAD_CERTIFICATE));
                        }
                        if (z2) {
                            a(vfeVar, inetSocketAddress, x509Certificate);
                        }
                    }
                    c = vbi.b(z3, c, this.c);
                }
                return new vcl(vcpVar, c, (Object) null);
            } catch (GeneralSecurityException e3) {
                if (this.f17701a.isTraceEnabled()) {
                    this.f17701a.trace("Certificate validation failed", (Throwable) e3);
                } else if (this.f17701a.isDebugEnabled()) {
                    this.f17701a.debug("Certificate validation failed due to {}", e3.getMessage());
                }
                throw new vdb("Certificate chain could not be validated", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.DECRYPT_ERROR), e3);
            }
        } catch (vdb e4) {
            this.f17701a.debug("Certificate validation failed!", (Throwable) e4);
            return new vcl(vcpVar, e4, (Object) null);
        }
    }

    public void a(vfe vfeVar, InetSocketAddress inetSocketAddress, X509Certificate x509Certificate) throws vdb {
        String str;
        String str2;
        ServerName b;
        if (x509Certificate == null) {
            throw new NullPointerException("Certficate must not be null!");
        }
        if (vfeVar == null && inetSocketAddress == null) {
            return;
        }
        if (inetSocketAddress != null) {
            str2 = vcb.b(inetSocketAddress);
            InetAddress address = inetSocketAddress.getAddress();
            str = address != null ? address.getHostAddress() : null;
        } else {
            str = null;
            str2 = null;
        }
        if (vfeVar != null && (b = vfeVar.b(ServerName.NameType.HOST_NAME)) != null) {
            str2 = b.e();
        }
        String str3 = (str2 == null || !str2.equals(str)) ? str2 : null;
        if (str3 != null) {
            if (vbi.c(x509Certificate, str3)) {
                return;
            }
            String e2 = vbi.e(x509Certificate);
            this.f17701a.debug("Certificate {} validation failed: destination doesn't match", e2);
            throw new vdb("Certificate " + e2 + ": Destination '" + str3 + "' doesn't match!", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.BAD_CERTIFICATE));
        }
        if (vbi.e(x509Certificate, str)) {
            return;
        }
        String e3 = vbi.e(x509Certificate);
        this.f17701a.debug("Certificate {} validation failed: literal IP doesn't match", e3);
        throw new vdb("Certificate " + e3 + ": Literal IP " + str + " doesn't match!", new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.BAD_CERTIFICATE));
    }

    @Override // org.eclipse.californium.scandium.dtls.x509.NewAdvancedCertificateVerifier
    public List<X500Principal> getAcceptedIssuers() {
        X509Certificate[] x509CertificateArr;
        if (!this.h && (x509CertificateArr = this.c) != null) {
            return vbi.c(Arrays.asList(x509CertificateArr));
        }
        return vbi.c(null);
    }

    public static e a() {
        return new e();
    }

    public static class e {
        protected vas[] b;
        protected List<CertificateType> c;
        protected boolean d;
        protected X509Certificate[] e;

        public e b(Certificate... certificateArr) {
            if (certificateArr == null) {
                this.e = null;
            } else if (certificateArr.length == 0) {
                this.e = vez.d;
            } else {
                X509Certificate[] d = SslContextUtil.d(certificateArr);
                SslContextUtil.d(d);
                this.e = d;
            }
            return this;
        }

        public e c() {
            this.b = vez.e;
            return this;
        }

        public NewAdvancedCertificateVerifier b() {
            return new vez(this.e, this.b, this.c, this.d);
        }
    }
}
