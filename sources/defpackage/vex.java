package defpackage;

import java.security.GeneralSecurityException;
import java.security.Signature;
import org.eclipse.californium.scandium.dtls.cipher.ThreadLocalCrypto;
import org.eclipse.californium.scandium.dtls.cipher.ThreadLocalCryptoMap;

/* loaded from: classes7.dex */
public class vex extends ThreadLocalCrypto<Signature> {
    public static final ThreadLocalCryptoMap<vex> e = new ThreadLocalCryptoMap<>(new ThreadLocalCryptoMap.Factory<vex>() { // from class: vex.4
        @Override // org.eclipse.californium.scandium.dtls.cipher.ThreadLocalCryptoMap.Factory
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public vex getInstance(String str) {
            return new vex(str);
        }
    });

    public vex(final String str) {
        super(new ThreadLocalCrypto.Factory<Signature>() { // from class: vex.3
            @Override // org.eclipse.californium.scandium.dtls.cipher.ThreadLocalCrypto.Factory
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Signature getInstance() throws GeneralSecurityException {
                String str2 = str;
                return Signature.getInstance(vbu.a(str2, str2));
            }
        });
    }
}
