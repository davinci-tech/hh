package defpackage;

import java.security.GeneralSecurityException;
import java.security.KeyPairGenerator;
import org.eclipse.californium.scandium.dtls.cipher.ThreadLocalCrypto;

/* loaded from: classes7.dex */
public class vev extends ThreadLocalCrypto<KeyPairGenerator> {
    public vev(final String str) {
        super(new ThreadLocalCrypto.Factory<KeyPairGenerator>() { // from class: vev.2
            @Override // org.eclipse.californium.scandium.dtls.cipher.ThreadLocalCrypto.Factory
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public KeyPairGenerator getInstance() throws GeneralSecurityException {
                String str2 = str;
                return KeyPairGenerator.getInstance(vbu.a(str2, str2));
            }
        });
    }
}
