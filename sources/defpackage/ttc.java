package defpackage;

import android.security.keystore.KeyGenParameterSpec;
import com.huawei.wisesecurity.kfs.crypto.key.KeyStoreKeyManager;
import com.huawei.wisesecurity.kfs.crypto.key.KeyStoreProvider;
import com.huawei.wisesecurity.kfs.crypto.key.KfsKeyPurpose;
import com.huawei.wisesecurity.kfs.crypto.signer.KfsSigner;
import com.huawei.wisesecurity.kfs.crypto.signer.SignAlg;
import defpackage.tth;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.ProviderException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.ECGenParameterSpec;
import org.eclipse.californium.elements.util.JceNames;

/* loaded from: classes7.dex */
public class ttc extends KeyStoreKeyManager {
    private static final AlgorithmParameterSpec d = new ECGenParameterSpec("secp256r1");

    public ttc() {
    }

    public ttc(KeyStoreProvider keyStoreProvider) {
        super(keyStoreProvider);
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.key.KeyStoreKeyManager
    public void generateKey(ttf ttfVar) throws ttn {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(JceNames.EC, getProvider().getProviderName());
            keyPairGenerator.initialize(new KeyGenParameterSpec.Builder(ttfVar.d(), ttfVar.c().getValue()).setAttestationChallenge(ttfVar.a() ? getProvider().getName().getBytes(StandardCharsets.UTF_8) : null).setDigests("SHA-256", "SHA-384", "SHA-512").setAlgorithmParameterSpec(d).setKeySize(ttfVar.b()).build());
            if (keyPairGenerator.generateKeyPair() != null) {
            } else {
                throw new ttn("generate ec key pair failed with bad key");
            }
        } catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchProviderException | ProviderException e) {
            throw new ttn("generate ec key pair failed, " + e.getMessage());
        }
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.key.KeyStoreKeyManager
    public void validateParam(ttf ttfVar) throws ttr {
        if (ttfVar.b() != 256) {
            throw new ttr("bad ec key len, only ec prime 256 is supported");
        }
        if (ttfVar.c() != KfsKeyPurpose.PURPOSE_SIGN) {
            throw new ttr("bad purpose for ec key, only sign is supported");
        }
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.key.KeyStoreKeyManager
    public void validateKey(ttf ttfVar) throws ttn {
        validateSign((KfsSigner) new tth.e(getProvider()).withAlg(SignAlg.ECDSA).withKeyStoreAlias(ttfVar.d()).build());
    }
}
