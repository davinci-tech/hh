package defpackage;

import java.security.GeneralSecurityException;
import javax.crypto.KeyAgreement;
import org.eclipse.californium.scandium.dtls.cipher.ThreadLocalCrypto;

/* loaded from: classes7.dex */
public class vet extends ThreadLocalCrypto<KeyAgreement> {
    public vet(final String str) {
        super(new ThreadLocalCrypto.Factory<KeyAgreement>() { // from class: vet.1
            @Override // org.eclipse.californium.scandium.dtls.cipher.ThreadLocalCrypto.Factory
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public KeyAgreement getInstance() throws GeneralSecurityException {
                return KeyAgreement.getInstance(str);
            }
        });
    }
}
