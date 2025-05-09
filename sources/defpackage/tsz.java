package defpackage;

import com.huawei.wisesecurity.kfs.crypto.cipher.CipherAlg;
import com.huawei.wisesecurity.kfs.crypto.cipher.DecryptHandler;
import com.huawei.wisesecurity.kfs.crypto.cipher.EncryptHandler;
import com.huawei.wisesecurity.kfs.crypto.cipher.KfsCipher;
import com.huawei.wisesecurity.kfs.crypto.key.KeyStoreProvider;
import java.io.IOException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;

/* loaded from: classes7.dex */
public class tsz implements KfsCipher {

    /* renamed from: a, reason: collision with root package name */
    private final KeyStoreProvider f17362a;
    private final Key b;
    private final CipherAlg c;
    private final AlgorithmParameterSpec e;

    /* synthetic */ tsz(KeyStoreProvider keyStoreProvider, CipherAlg cipherAlg, Key key, AlgorithmParameterSpec algorithmParameterSpec, AnonymousClass3 anonymousClass3) {
        this(keyStoreProvider, cipherAlg, key, algorithmParameterSpec);
    }

    private tsz(KeyStoreProvider keyStoreProvider, CipherAlg cipherAlg, Key key, AlgorithmParameterSpec algorithmParameterSpec) {
        this.f17362a = keyStoreProvider;
        this.c = cipherAlg;
        this.b = key;
        this.e = algorithmParameterSpec;
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.cipher.KfsCipher
    public EncryptHandler getEncryptHandler() throws ttp {
        tsx tsxVar = new tsx();
        tsxVar.b(this.c);
        return new tta(this.f17362a, this.b, tsxVar, this.e);
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.cipher.KfsCipher
    public DecryptHandler getDecryptHandler() throws ttp {
        tsx tsxVar = new tsx();
        tsxVar.b(this.c);
        return new ttb(this.f17362a, this.b, tsxVar, this.e);
    }

    public static class c {

        /* renamed from: a, reason: collision with root package name */
        private CipherAlg f17364a;
        private final KeyStoreProvider b;
        private AlgorithmParameterSpec c;
        private Key e;

        public c() {
            this.f17364a = CipherAlg.getPreferredAlg("AES");
            this.b = KeyStoreProvider.ANDROID_KEYSTORE;
        }

        public c(KeyStoreProvider keyStoreProvider) {
            this.f17364a = CipherAlg.getPreferredAlg("AES");
            this.b = keyStoreProvider;
        }

        public c a(CipherAlg cipherAlg) {
            this.f17364a = cipherAlg;
            return this;
        }

        public c e(Key key) {
            this.e = key;
            return this;
        }

        public c d(String str) throws ttn {
            try {
                KeyStore keyStore = KeyStore.getInstance(this.b.getName());
                keyStore.load(null);
                this.e = keyStore.getKey(str, null);
                return this;
            } catch (IOException | KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException | CertificateException e) {
                throw new ttn("keystore get key with alias failed, " + e.getMessage());
            }
        }

        public c a(byte[] bArr) throws ttp {
            int i = AnonymousClass3.f17363a[this.f17364a.ordinal()];
            if (i == 1) {
                this.c = new GCMParameterSpec(128, tty.d(bArr));
            } else if (i == 2 || i == 3) {
                this.c = new IvParameterSpec(tty.d(bArr));
            } else {
                throw new ttp("unsupported cipher alg");
            }
            return this;
        }

        public tsz b() throws ttp {
            AlgorithmParameterSpec algorithmParameterSpec;
            Key key = this.e;
            if (key == null || (algorithmParameterSpec = this.c) == null) {
                throw new ttp("key | parameterSpec cannot be null");
            }
            return new tsz(this.b, this.f17364a, key, algorithmParameterSpec, null);
        }
    }

    /* renamed from: tsz$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f17363a;

        static {
            int[] iArr = new int[CipherAlg.values().length];
            f17363a = iArr;
            try {
                iArr[CipherAlg.AES_GCM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f17363a[CipherAlg.AES_CBC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f17363a[CipherAlg.KEY_STORE_AES_CBC.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
