package defpackage;

import com.huawei.wisesecurity.kfs.crypto.key.KeyStoreProvider;
import com.huawei.wisesecurity.kfs.crypto.signer.AsymmetricSignerBuilder;
import com.huawei.wisesecurity.kfs.crypto.signer.KfsSigner;
import com.huawei.wisesecurity.kfs.crypto.signer.SignAlg;
import com.huawei.wisesecurity.kfs.crypto.signer.SignHandler;
import com.huawei.wisesecurity.kfs.crypto.signer.VerifyHandler;
import java.security.PrivateKey;
import java.security.PublicKey;
import org.eclipse.californium.elements.util.JceNames;

/* loaded from: classes7.dex */
public class tth implements KfsSigner {

    /* renamed from: a, reason: collision with root package name */
    private final PublicKey f17371a;
    private final SignAlg b;
    private final KeyStoreProvider c;
    private final PrivateKey d;

    private tth(KeyStoreProvider keyStoreProvider, SignAlg signAlg, PrivateKey privateKey, PublicKey publicKey) {
        this.c = keyStoreProvider;
        this.b = signAlg;
        this.d = privateKey;
        this.f17371a = publicKey;
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.signer.KfsSigner
    public SignHandler getSignHandler() throws ttp {
        ttj ttjVar = new ttj();
        ttjVar.d(this.b);
        PrivateKey privateKey = this.d;
        if (privateKey == null) {
            throw new ttp("privateKey is invalid.");
        }
        return new ttk(this.c, privateKey, ttjVar, null);
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.signer.KfsSigner
    public VerifyHandler getVerifyHandler() throws ttp {
        ttj ttjVar = new ttj();
        ttjVar.d(this.b);
        PublicKey publicKey = this.f17371a;
        if (publicKey == null) {
            throw new ttp("publicKey is invalid.");
        }
        return new tti(this.c, publicKey, ttjVar, null);
    }

    public static class e extends AsymmetricSignerBuilder<tth> {
        public e() {
            this(KeyStoreProvider.ANDROID_KEYSTORE);
        }

        public e(KeyStoreProvider keyStoreProvider) {
            super(keyStoreProvider);
            withAlg(SignAlg.getPreferredAlg(JceNames.EC));
        }

        @Override // com.huawei.wisesecurity.kfs.crypto.AsymmetricBuilder
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public tth build() throws ttp {
            return new tth(this.keyStoreProvider, this.signAlg, this.privateKey, this.publicKey);
        }
    }
}
