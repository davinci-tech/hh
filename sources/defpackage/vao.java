package defpackage;

import java.io.ByteArrayInputStream;
import java.security.cert.CertPath;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import org.eclipse.californium.elements.auth.AbstractExtensiblePrincipal;

/* loaded from: classes7.dex */
public class vao extends AbstractExtensiblePrincipal<vao> {

    /* renamed from: a, reason: collision with root package name */
    private final CertPath f17634a;
    private final X509Certificate d;

    public vao(CertPath certPath) {
        this(certPath, null);
    }

    private vao(CertPath certPath, var varVar) {
        super(varVar);
        if (!"X.509".equals(certPath.getType())) {
            throw new IllegalArgumentException("Cert path must contain X.509 certificates only");
        }
        if (certPath.getCertificates().isEmpty()) {
            throw new IllegalArgumentException("Cert path must not be empty");
        }
        this.f17634a = certPath;
        this.d = (X509Certificate) certPath.getCertificates().get(0);
    }

    @Override // org.eclipse.californium.elements.auth.ExtensiblePrincipal
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public vao amend(var varVar) {
        return new vao(this.f17634a, varVar);
    }

    public static vao c(byte[] bArr) {
        try {
            return new vao(CertificateFactory.getInstance("X.509").generateCertPath(new ByteArrayInputStream(bArr), "PkiPath"));
        } catch (CertificateException unused) {
            throw new IllegalArgumentException("byte array does not contain X.509 certificate path");
        }
    }

    public byte[] b() {
        try {
            return this.f17634a.getEncoded("PkiPath");
        } catch (CertificateEncodingException unused) {
            return vbj.c;
        }
    }

    @Override // java.security.Principal
    public String getName() {
        return this.d.getSubjectX500Principal().getName();
    }

    @Override // java.security.Principal
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return this.d.equals(((vao) obj).d);
        }
        return false;
    }

    @Override // java.security.Principal
    public int hashCode() {
        return this.d.hashCode();
    }

    @Override // java.security.Principal
    public String toString() {
        return "x509 [" + getName() + "]";
    }
}
