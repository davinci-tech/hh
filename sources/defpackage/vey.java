package defpackage;

import java.security.GeneralSecurityException;
import javax.crypto.Mac;
import org.eclipse.californium.scandium.dtls.cipher.ThreadLocalCrypto;

/* loaded from: classes7.dex */
public class vey extends ThreadLocalCrypto<Mac> {
    public vey(final String str) {
        super(new ThreadLocalCrypto.Factory<Mac>() { // from class: vey.5
            @Override // org.eclipse.californium.scandium.dtls.cipher.ThreadLocalCrypto.Factory
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public Mac getInstance() throws GeneralSecurityException {
                return Mac.getInstance(str);
            }
        });
    }
}
