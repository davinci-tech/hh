package defpackage;

import com.huawei.wisesecurity.kfs.crypto.key.KeyStoreProvider;
import com.huawei.wisesecurity.kfs.crypto.signer.AsymmetricSignerBuilder;
import com.huawei.wisesecurity.kfs.crypto.signer.KfsSigner;
import com.huawei.wisesecurity.kfs.crypto.signer.SignAlg;
import com.huawei.wisesecurity.kfs.crypto.signer.SignHandler;
import com.huawei.wisesecurity.kfs.crypto.signer.VerifyHandler;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.AlgorithmParameterSpec;
import org.eclipse.californium.elements.util.JceNames;

/* loaded from: classes7.dex */
public class ttq implements KfsSigner {

    /* renamed from: a, reason: collision with root package name */
    private final PrivateKey f17376a;
    private final SignAlg b;
    private final AlgorithmParameterSpec c;
    private final KeyStoreProvider d;
    private final PublicKey e;

    private ttq(KeyStoreProvider keyStoreProvider, SignAlg signAlg, PrivateKey privateKey, PublicKey publicKey, AlgorithmParameterSpec algorithmParameterSpec) {
        this.d = keyStoreProvider;
        this.b = signAlg;
        this.f17376a = privateKey;
        this.e = publicKey;
        this.c = algorithmParameterSpec;
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.signer.KfsSigner
    public SignHandler getSignHandler() throws ttp {
        ttj ttjVar = new ttj();
        ttjVar.d(this.b);
        PrivateKey privateKey = this.f17376a;
        if (privateKey == null) {
            throw new ttp("privateKey is invalid.");
        }
        return new ttk(this.d, privateKey, ttjVar, this.c);
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.signer.KfsSigner
    public VerifyHandler getVerifyHandler() throws ttp {
        ttj ttjVar = new ttj();
        ttjVar.d(this.b);
        PublicKey publicKey = this.e;
        if (publicKey == null) {
            throw new ttp("publicKey is invalid.");
        }
        return new tti(this.d, publicKey, ttjVar, this.c);
    }

    public static class e extends AsymmetricSignerBuilder<ttq> {
        public e() {
            this(KeyStoreProvider.ANDROID_KEYSTORE);
        }

        public e(KeyStoreProvider keyStoreProvider) {
            super(keyStoreProvider);
            withAlg(SignAlg.getPreferredAlg(JceNames.RSA));
        }

        @Override // com.huawei.wisesecurity.kfs.crypto.AsymmetricBuilder
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public ttq build() throws ttp {
            return new ttq(this.keyStoreProvider, this.signAlg, this.privateKey, this.publicKey, this.parameterSpec);
        }
    }
}
