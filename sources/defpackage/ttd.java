package defpackage;

import android.security.keystore.KeyGenParameterSpec;
import com.huawei.wisesecurity.kfs.crypto.cipher.CipherAlg;
import com.huawei.wisesecurity.kfs.crypto.key.KeyStoreKeyManager;
import com.huawei.wisesecurity.kfs.crypto.key.KfsKeyPurpose;
import com.huawei.wisesecurity.kfs.crypto.signer.KfsSigner;
import com.huawei.wisesecurity.kfs.crypto.signer.SignAlg;
import defpackage.ttg;
import defpackage.ttq;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import org.eclipse.californium.elements.util.JceNames;

/* loaded from: classes7.dex */
public class ttd extends KeyStoreKeyManager {
    private boolean c(int i) {
        return (i == 2048 || i == 3072 || i == 4096) ? false : true;
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.key.KeyStoreKeyManager
    public void generateKey(ttf ttfVar) throws ttn {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(JceNames.RSA, getProvider().getProviderName());
            keyPairGenerator.initialize(new KeyGenParameterSpec.Builder(ttfVar.d(), ttfVar.c().getValue()).setAttestationChallenge(getProvider().getName().getBytes(StandardCharsets.UTF_8)).setSignaturePaddings("PKCS1", "PSS").setEncryptionPaddings("PKCS1Padding", "OAEPPadding").setDigests("SHA-256", "SHA-384", "SHA-512").setKeySize(ttfVar.b()).build());
            if (keyPairGenerator.generateKeyPair() != null) {
            } else {
                throw new ttn("generate rsa key pair failed with bad key");
            }
        } catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchProviderException e) {
            throw new ttn("generate rsa key pair failed, " + e.getMessage());
        }
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.key.KeyStoreKeyManager
    public void validateParam(ttf ttfVar) throws ttr {
        if (c(ttfVar.b())) {
            throw new ttr("bad rsa key len");
        }
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.key.KeyStoreKeyManager
    public void validateKey(ttf ttfVar) throws ttn {
        if (KfsKeyPurpose.containsPurpose(ttfVar.c(), KfsKeyPurpose.PURPOSE_CRYPTO)) {
            validateCrypto(new ttg.d(getProvider()).a(CipherAlg.RSA_OAEP).withKeyStoreAlias(ttfVar.d()).build());
        }
        if (KfsKeyPurpose.containsPurpose(ttfVar.c(), KfsKeyPurpose.PURPOSE_SIGN)) {
            validateSign((KfsSigner) new ttq.e(getProvider()).withAlg(SignAlg.RSA_SHA256).withKeyStoreAlias(ttfVar.d()).build());
        }
    }
}
