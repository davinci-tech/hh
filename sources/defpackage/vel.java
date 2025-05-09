package defpackage;

import java.util.Iterator;
import org.eclipse.californium.elements.config.CertificateAuthenticationMode;
import org.eclipse.californium.scandium.dtls.CertificateType;
import org.eclipse.californium.scandium.dtls.SignatureAndHashAlgorithm;
import org.eclipse.californium.scandium.dtls.cipher.CipherSuite;
import org.eclipse.californium.scandium.dtls.cipher.CipherSuiteParameters;
import org.eclipse.californium.scandium.dtls.cipher.CipherSuiteSelector;
import org.eclipse.californium.scandium.dtls.cipher.XECDHECryptography;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public class vel implements CipherSuiteSelector {
    protected static final Logger d = vha.a((Class<?>) vel.class);

    @Override // org.eclipse.californium.scandium.dtls.cipher.CipherSuiteSelector
    public boolean select(CipherSuiteParameters cipherSuiteParameters) {
        if (cipherSuiteParameters.d().isEmpty()) {
            cipherSuiteParameters.e(CipherSuiteParameters.GeneralMismatch.CIPHER_SUITE);
            return false;
        }
        Iterator<CipherSuite> it = cipherSuiteParameters.d().iterator();
        while (it.hasNext()) {
            if (c(it.next(), cipherSuiteParameters)) {
                return true;
            }
        }
        return false;
    }

    protected boolean c(CipherSuite cipherSuite, CipherSuiteParameters cipherSuiteParameters) {
        if (cipherSuite.isEccBased()) {
            if (cipherSuiteParameters.t().isEmpty()) {
                cipherSuiteParameters.e(CipherSuiteParameters.GeneralMismatch.EC_GROUPS);
                return false;
            }
            if (cipherSuiteParameters.j() == null) {
                cipherSuiteParameters.e(CipherSuiteParameters.GeneralMismatch.EC_FORMAT);
                return false;
            }
        }
        if (cipherSuite.requiresServerCertificateMessage()) {
            if (cipherSuiteParameters.e() == null) {
                return c(cipherSuiteParameters, cipherSuite);
            }
            return false;
        }
        if (cipherSuite.isEccBased()) {
            cipherSuiteParameters.d(cipherSuiteParameters.t().get(0));
        }
        cipherSuiteParameters.c(cipherSuite);
        return true;
    }

    protected boolean c(CipherSuiteParameters cipherSuiteParameters, CipherSuite cipherSuite) {
        XECDHECryptography.SupportedGroup fromPublicKey;
        CipherSuite.CertificateKeyAlgorithm certificateKeyAlgorithm = cipherSuite.getCertificateKeyAlgorithm();
        if (!vbu.c(certificateKeyAlgorithm.name())) {
            throw new IllegalArgumentException(certificateKeyAlgorithm.name() + " based cipher suites are supported!");
        }
        if (!certificateKeyAlgorithm.isCompatible(cipherSuiteParameters.h())) {
            return false;
        }
        if (cipherSuiteParameters.o().isEmpty()) {
            cipherSuiteParameters.d(CipherSuiteParameters.CertificateBasedMismatch.SERVER_CERT_TYPE);
            return false;
        }
        CertificateAuthenticationMode b = cipherSuiteParameters.b();
        if (b.useCertificateRequest() && cipherSuiteParameters.a().isEmpty()) {
            cipherSuiteParameters.d(CipherSuiteParameters.CertificateBasedMismatch.CLIENT_CERT_TYPE);
            return false;
        }
        if (cipherSuiteParameters.p().isEmpty()) {
            cipherSuiteParameters.d(CipherSuiteParameters.CertificateBasedMismatch.SIGNATURE_ALGORITHMS);
            return false;
        }
        if (cipherSuite.getCertificateKeyAlgorithm() == CipherSuite.CertificateKeyAlgorithm.EC && ((fromPublicKey = XECDHECryptography.SupportedGroup.fromPublicKey(cipherSuiteParameters.h())) == null || !cipherSuiteParameters.t().contains(fromPublicKey))) {
            cipherSuiteParameters.d(CipherSuiteParameters.CertificateBasedMismatch.CERTIFICATE_EC_GROUPS);
            return false;
        }
        SignatureAndHashAlgorithm c = SignatureAndHashAlgorithm.c(cipherSuiteParameters.p(), cipherSuiteParameters.h());
        if (c == null) {
            cipherSuiteParameters.d(CipherSuiteParameters.CertificateBasedMismatch.CERTIFICATE_SIGNATURE_ALGORITHMS);
            return false;
        }
        CertificateType certificateType = cipherSuiteParameters.o().get(0);
        if (CertificateType.X_509.equals(certificateType)) {
            if (cipherSuiteParameters.c() == null) {
                throw new IllegalArgumentException("Certificate type x509 requires a certificate chain!");
            }
            boolean d2 = SignatureAndHashAlgorithm.d(cipherSuiteParameters.p(), cipherSuiteParameters.c());
            if (d2) {
                d2 = XECDHECryptography.SupportedGroup.isSupported(cipherSuiteParameters.t(), cipherSuiteParameters.c());
            }
            if (!d2) {
                if (cipherSuiteParameters.o().contains(CertificateType.RAW_PUBLIC_KEY)) {
                    certificateType = CertificateType.RAW_PUBLIC_KEY;
                } else {
                    cipherSuiteParameters.d(CipherSuiteParameters.CertificateBasedMismatch.CERTIFICATE_PATH_SIGNATURE_ALGORITHMS);
                    return false;
                }
            }
        }
        cipherSuiteParameters.c(cipherSuite);
        cipherSuiteParameters.d(certificateType);
        cipherSuiteParameters.d(c);
        cipherSuiteParameters.d(cipherSuiteParameters.t().get(0));
        cipherSuiteParameters.a(b.useCertificateRequest() ? cipherSuiteParameters.a().get(0) : null);
        return true;
    }
}
