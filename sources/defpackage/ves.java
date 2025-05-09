package defpackage;

import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import org.eclipse.californium.scandium.dtls.cipher.ThreadLocalCrypto;

/* loaded from: classes7.dex */
public class ves extends ThreadLocalCrypto<Cipher> {
    public ves(final String str) {
        super(new ThreadLocalCrypto.Factory<Cipher>() { // from class: ves.5
            @Override // org.eclipse.californium.scandium.dtls.cipher.ThreadLocalCrypto.Factory
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public Cipher getInstance() throws GeneralSecurityException {
                return Cipher.getInstance(str);
            }
        });
    }
}
