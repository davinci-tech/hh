package com.huawei.wisesecurity.kfs.crypto.key;

import android.util.Log;
import com.huawei.wisesecurity.kfs.crypto.cipher.KfsCipher;
import com.huawei.wisesecurity.kfs.crypto.signer.KfsSigner;
import defpackage.ttf;
import defpackage.ttn;
import defpackage.ttr;
import defpackage.ttu;
import defpackage.ttw;
import defpackage.tua;
import defpackage.tue;
import java.io.IOException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Arrays;

/* loaded from: classes7.dex */
public abstract class KeyStoreKeyManager implements KfsKeyManager {
    private static final String TAG = "KeyStoreKeyManager";
    private KeyStore mKeyStore;
    private final KeyStoreProvider provider;

    protected abstract void generateKey(ttf ttfVar) throws ttn;

    protected abstract void validateKey(ttf ttfVar) throws ttn;

    protected abstract void validateParam(ttf ttfVar) throws ttr;

    public KeyStoreKeyManager(KeyStoreProvider keyStoreProvider) {
        this.provider = keyStoreProvider;
    }

    public KeyStoreKeyManager() {
        this(KeyStoreProvider.ANDROID_KEYSTORE);
    }

    protected void initKeyStore() throws ttn {
        if (this.mKeyStore != null) {
            return;
        }
        if (getProvider() == KeyStoreProvider.HUAWEI_KEYSTORE) {
            ttw.a();
        }
        try {
            KeyStore keyStore = KeyStore.getInstance(this.provider.getName());
            this.mKeyStore = keyStore;
            keyStore.load(null);
        } catch (IOException | KeyStoreException | NoSuchAlgorithmException | CertificateException e) {
            throw new ttn("init keystore failed, " + e.getMessage());
        }
    }

    private void clearKey(String str) throws ttn {
        if (hasAlias(str)) {
            try {
                this.mKeyStore.deleteEntry(str);
                Log.i(TAG, "keyEntry: " + str + " removed");
            } catch (KeyStoreException e) {
                throw new ttn("delete key entry failed, " + e.getMessage());
            }
        }
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.key.KfsKeyManager
    public void generate(ttf ttfVar) throws ttn {
        tue.d(ttfVar);
        validateParam(ttfVar);
        generateKey(ttfVar);
        try {
            validateKey(ttfVar);
        } catch (ttn e) {
            Log.i(TAG, "validate key failed, try to remove the key entry for alias:" + ttfVar.d());
            clearKey(ttfVar.d());
            throw e;
        }
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.key.KfsKeyManager
    public Key getKey(String str) throws ttn {
        initKeyStore();
        try {
            return this.mKeyStore.getKey(str, null);
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw new ttn("keystore get key failed, " + e.getMessage());
        }
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.key.KfsKeyManager
    public boolean hasAlias(String str) throws ttn {
        initKeyStore();
        try {
            return this.mKeyStore.containsAlias(str);
        } catch (KeyStoreException e) {
            throw new ttn("keystore check alias failed, " + e.getMessage());
        }
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.key.KfsKeyManager
    public PrivateKey getPrivateKey(String str) throws ttn {
        initKeyStore();
        try {
            return (PrivateKey) this.mKeyStore.getKey(str, null);
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw new ttn("keystore get private key failed, " + e.getMessage());
        }
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.key.KfsKeyManager
    public PublicKey getPublicKey(String str) throws ttn {
        initKeyStore();
        try {
            return this.mKeyStore.getCertificate(str).getPublicKey();
        } catch (KeyStoreException e) {
            throw new ttn("keystore get public key failed, " + e.getMessage());
        }
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.key.KfsKeyManager
    public Certificate[] getCertificateChain(String str) throws ttn {
        initKeyStore();
        try {
            return this.mKeyStore.getCertificateChain(str);
        } catch (KeyStoreException e) {
            throw new ttn("keystore get certificate chain failed, " + e.getMessage());
        }
    }

    protected void validateSign(KfsSigner kfsSigner) throws ttn {
        byte[] e = tua.e(32);
        if (!kfsSigner.getVerifyHandler().fromData(e).verify(kfsSigner.getSignHandler().from(e).sign())) {
            throw new ttu("validate sign key get bad result");
        }
    }

    protected void validateCrypto(KfsCipher kfsCipher) throws ttn {
        byte[] e = tua.e(32);
        if (!Arrays.equals(e, kfsCipher.getDecryptHandler().from(kfsCipher.getEncryptHandler().from(e).to()).to())) {
            throw new ttu("validate crypto key get bad result");
        }
    }

    public KeyStoreProvider getProvider() {
        return this.provider;
    }
}
