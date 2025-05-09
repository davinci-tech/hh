package defpackage;

import java.security.GeneralSecurityException;
import java.security.cert.CertificateFactory;
import org.eclipse.californium.scandium.dtls.cipher.ThreadLocalCrypto;

/* loaded from: classes7.dex */
public class ver extends ThreadLocalCrypto<CertificateFactory> {
    public ver(final String str) {
        super(new ThreadLocalCrypto.Factory<CertificateFactory>() { // from class: ver.4
            @Override // org.eclipse.californium.scandium.dtls.cipher.ThreadLocalCrypto.Factory
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public CertificateFactory getInstance() throws GeneralSecurityException {
                return CertificateFactory.getInstance(str);
            }
        });
    }
}
