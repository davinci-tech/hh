package defpackage;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import org.eclipse.californium.scandium.dtls.cipher.ThreadLocalCrypto;

/* loaded from: classes7.dex */
public class vew extends ThreadLocalCrypto<MessageDigest> {
    public vew(final String str) {
        super(new ThreadLocalCrypto.Factory<MessageDigest>() { // from class: vew.3
            @Override // org.eclipse.californium.scandium.dtls.cipher.ThreadLocalCrypto.Factory
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public MessageDigest getInstance() throws GeneralSecurityException {
                return MessageDigest.getInstance(str);
            }
        });
    }
}
