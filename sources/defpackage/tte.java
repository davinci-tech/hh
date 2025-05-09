package defpackage;

import android.security.keystore.KeyGenParameterSpec;
import com.huawei.wisesecurity.kfs.crypto.cipher.CipherAlg;
import com.huawei.wisesecurity.kfs.crypto.key.KeyStoreKeyManager;
import com.huawei.wisesecurity.kfs.crypto.key.KfsKeyPurpose;
import defpackage.tsz;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import javax.crypto.KeyGenerator;

/* loaded from: classes7.dex */
public class tte extends KeyStoreKeyManager {
    private boolean d(int i) {
        return (i == 128 || i == 192 || i == 256) ? false : true;
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.key.KeyStoreKeyManager
    public void generateKey(ttf ttfVar) throws ttn {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", getProvider().getProviderName());
            keyGenerator.init(new KeyGenParameterSpec.Builder(ttfVar.d(), ttfVar.c().getValue()).setKeySize(ttfVar.b()).setAttestationChallenge(getProvider().getName().getBytes(StandardCharsets.UTF_8)).setRandomizedEncryptionRequired(false).setBlockModes("GCM", "CBC").setEncryptionPaddings("NoPadding", "PKCS7Padding").build());
            if (keyGenerator.generateKey() != null) {
            } else {
                throw new ttn("generate aes key failed with bad key");
            }
        } catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchProviderException e) {
            throw new ttn("generate aes key failed, " + e.getMessage());
        }
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.key.KeyStoreKeyManager
    public void validateParam(ttf ttfVar) throws ttr {
        if (d(ttfVar.b())) {
            throw new ttr("bad aes key len");
        }
        if (ttfVar.c() != KfsKeyPurpose.PURPOSE_CRYPTO) {
            throw new ttr("bad purpose for aes key, only crypto is supported");
        }
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.key.KeyStoreKeyManager
    public void validateKey(ttf ttfVar) throws ttn {
        validateCrypto(new tsz.c(getProvider()).a(CipherAlg.AES_GCM).d(ttfVar.d()).a(tua.e(CipherAlg.AES_GCM.getIvLen())).b());
    }
}
