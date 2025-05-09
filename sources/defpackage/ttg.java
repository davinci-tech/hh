package defpackage;

import com.huawei.wisesecurity.kfs.crypto.AsymmetricBuilder;
import com.huawei.wisesecurity.kfs.crypto.cipher.CipherAlg;
import com.huawei.wisesecurity.kfs.crypto.cipher.DecryptHandler;
import com.huawei.wisesecurity.kfs.crypto.cipher.EncryptHandler;
import com.huawei.wisesecurity.kfs.crypto.cipher.KfsCipher;
import com.huawei.wisesecurity.kfs.crypto.key.KeyStoreProvider;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.MGF1ParameterSpec;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import org.eclipse.californium.elements.util.JceNames;

/* loaded from: classes7.dex */
public class ttg implements KfsCipher {

    /* renamed from: a, reason: collision with root package name */
    private final PrivateKey f17369a;
    private final AlgorithmParameterSpec b;
    private final KeyStoreProvider c;
    private final CipherAlg d;
    private final PublicKey e;

    private ttg(KeyStoreProvider keyStoreProvider, CipherAlg cipherAlg, PrivateKey privateKey, PublicKey publicKey, AlgorithmParameterSpec algorithmParameterSpec) {
        this.c = keyStoreProvider;
        this.d = cipherAlg;
        this.f17369a = privateKey;
        this.e = publicKey;
        this.b = algorithmParameterSpec;
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.cipher.KfsCipher
    public EncryptHandler getEncryptHandler() throws ttp {
        tsx tsxVar = new tsx();
        tsxVar.b(this.d);
        PublicKey publicKey = this.e;
        if (publicKey == null) {
            throw new ttp("publicKey is invalid.");
        }
        return new tta(this.c, publicKey, tsxVar, this.b);
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.cipher.KfsCipher
    public DecryptHandler getDecryptHandler() throws ttp {
        tsx tsxVar = new tsx();
        tsxVar.b(this.d);
        PrivateKey privateKey = this.f17369a;
        if (privateKey == null) {
            throw new ttp("privateKey is invalid.");
        }
        return new ttb(this.c, privateKey, tsxVar, this.b);
    }

    public static class d extends AsymmetricBuilder<ttg> {

        /* renamed from: a, reason: collision with root package name */
        private final AlgorithmParameterSpec f17370a;
        private CipherAlg d;

        public d() {
            super(KeyStoreProvider.ANDROID_KEYSTORE);
            this.d = CipherAlg.getPreferredAlg(JceNames.RSA);
            this.f17370a = new OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA1, PSource.PSpecified.DEFAULT);
        }

        public d(KeyStoreProvider keyStoreProvider) {
            super(keyStoreProvider);
            this.d = CipherAlg.getPreferredAlg(JceNames.RSA);
            this.f17370a = new OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA1, PSource.PSpecified.DEFAULT);
        }

        public d a(CipherAlg cipherAlg) {
            this.d = cipherAlg;
            return this;
        }

        @Override // com.huawei.wisesecurity.kfs.crypto.AsymmetricBuilder
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public ttg build() throws ttp {
            return new ttg(this.keyStoreProvider, this.d, this.privateKey, this.publicKey, this.f17370a);
        }
    }
}
