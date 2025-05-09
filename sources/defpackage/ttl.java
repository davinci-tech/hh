package defpackage;

import com.huawei.wisesecurity.kfs.crypto.key.KeyStoreProvider;
import com.huawei.wisesecurity.kfs.crypto.signer.KfsSigner;
import com.huawei.wisesecurity.kfs.crypto.signer.SignAlg;
import com.huawei.wisesecurity.kfs.crypto.signer.SignHandler;
import com.huawei.wisesecurity.kfs.crypto.signer.VerifyHandler;
import java.security.Key;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes7.dex */
public class ttl implements KfsSigner {

    /* renamed from: a, reason: collision with root package name */
    private final Key f17375a;
    private final KeyStoreProvider b;
    private final SignAlg c;

    private ttl(KeyStoreProvider keyStoreProvider, SignAlg signAlg, Key key) {
        this.b = keyStoreProvider;
        this.c = signAlg;
        this.f17375a = key;
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.signer.KfsSigner
    public SignHandler getSignHandler() throws ttp {
        ttj ttjVar = new ttj();
        ttjVar.d(this.c);
        return new ttk(this.b, this.f17375a, ttjVar, null);
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.signer.KfsSigner
    public VerifyHandler getVerifyHandler() throws ttp {
        ttj ttjVar = new ttj();
        ttjVar.d(this.c);
        return new tti(this.b, this.f17375a, ttjVar, null);
    }

    public static class d {
        private Key b;
        private SignAlg c = SignAlg.getPreferredAlg("HMAC");
        private final KeyStoreProvider d = KeyStoreProvider.ANDROID_KEYSTORE;

        public d c(SignAlg signAlg) {
            this.c = signAlg;
            return this;
        }

        public d b(byte[] bArr) {
            this.b = new SecretKeySpec(bArr, this.c.getTransformation());
            return this;
        }

        public ttl e() throws ttp {
            Key key = this.b;
            if (key == null) {
                throw new ttp("key cannot be null");
            }
            return new ttl(this.d, this.c, key);
        }
    }
}
