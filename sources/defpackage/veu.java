package defpackage;

import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import org.eclipse.californium.scandium.dtls.cipher.ThreadLocalCrypto;
import org.eclipse.californium.scandium.dtls.cipher.ThreadLocalCryptoMap;

/* loaded from: classes7.dex */
public class veu extends ThreadLocalCrypto<KeyFactory> {
    public static final ThreadLocalCryptoMap<veu> e = new ThreadLocalCryptoMap<>(new ThreadLocalCryptoMap.Factory<veu>() { // from class: veu.3
        @Override // org.eclipse.californium.scandium.dtls.cipher.ThreadLocalCryptoMap.Factory
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public veu getInstance(String str) {
            return new veu(str);
        }
    });

    public veu(final String str) {
        super(new ThreadLocalCrypto.Factory<KeyFactory>() { // from class: veu.4
            @Override // org.eclipse.californium.scandium.dtls.cipher.ThreadLocalCrypto.Factory
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public KeyFactory getInstance() throws GeneralSecurityException {
                String str2 = str;
                return KeyFactory.getInstance(vbu.a(str2, str2));
            }
        });
    }
}
