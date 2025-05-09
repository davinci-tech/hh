package defpackage;

import com.huawei.wisesecurity.kfs.crypto.codec.Decoder;
import com.huawei.wisesecurity.kfs.crypto.key.KeyStoreProvider;
import com.huawei.wisesecurity.kfs.crypto.signer.SignAlg;
import com.huawei.wisesecurity.kfs.crypto.signer.VerifyHandler;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Mac;

/* loaded from: classes7.dex */
public class tti implements VerifyHandler {

    /* renamed from: a, reason: collision with root package name */
    private final KeyStoreProvider f17372a;
    private final ttj b;
    private final AlgorithmParameterSpec c;
    private final Key e;

    public tti(KeyStoreProvider keyStoreProvider, Key key, ttj ttjVar, AlgorithmParameterSpec algorithmParameterSpec) {
        this.f17372a = keyStoreProvider;
        this.e = key;
        this.c = algorithmParameterSpec;
        this.b = ttjVar;
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.signer.VerifyHandler
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public tti fromData(byte[] bArr) throws ttp {
        this.b.a(bArr);
        return this;
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.signer.VerifyHandler
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public tti fromData(String str) throws ttp {
        return fromData(str.getBytes(Charset.forName("UTF-8")));
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.signer.VerifyHandler
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public tti fromBase64Data(String str) throws ttp {
        return a(str, Decoder.BASE64);
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.signer.VerifyHandler
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public tti fromBase64UrlData(String str) throws ttp {
        return a(str, Decoder.BASE64URL);
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.signer.VerifyHandler
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public tti fromHexData(String str) throws ttp {
        return a(str, Decoder.HEX);
    }

    private tti a(String str, Decoder decoder) throws ttp {
        try {
            fromData(decoder.decode(str));
            return this;
        } catch (ttm e) {
            throw new ttp("Fail to decode sign data: " + e.getMessage());
        }
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.signer.VerifyHandler
    public boolean verify(byte[] bArr) throws ttp {
        this.b.d(bArr);
        return c();
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.signer.VerifyHandler
    public boolean verify(String str) throws ttp {
        return verify(str.getBytes(Charset.forName("UTF-8")));
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.signer.VerifyHandler
    public boolean verifyBase64(String str) throws ttp {
        return b(str, Decoder.BASE64);
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.signer.VerifyHandler
    public boolean verifyBase64Url(String str) throws ttp {
        return b(str, Decoder.BASE64URL);
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.signer.VerifyHandler
    public boolean verifyHex(String str) throws ttp {
        return b(str, Decoder.HEX);
    }

    private boolean b(String str, Decoder decoder) throws ttp {
        try {
            return verify(decoder.decode(str));
        } catch (ttm e) {
            throw new ttp("Fail to decode signature : " + e.getMessage());
        }
    }

    /* renamed from: tti$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] c;

        static {
            int[] iArr = new int[SignAlg.values().length];
            c = iArr;
            try {
                iArr[SignAlg.ECDSA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                c[SignAlg.RSA_SHA256.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                c[SignAlg.RSA_SHA256_PSS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                c[SignAlg.HMAC_SHA256.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private boolean c() throws ttp {
        int i = AnonymousClass3.c[this.b.e().ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            return b();
        }
        if (i == 4) {
            return d();
        }
        throw new ttp("unsupported sign alg : " + this.b.e().getTransformation());
    }

    private boolean b() throws ttp {
        Signature signature;
        try {
            String transformation = this.b.e().getTransformation();
            if (this.f17372a == KeyStoreProvider.ANDROID_KEYSTORE) {
                signature = Signature.getInstance(transformation);
            } else {
                signature = Signature.getInstance(transformation, this.f17372a.getProviderName());
            }
            AlgorithmParameterSpec algorithmParameterSpec = this.c;
            if (algorithmParameterSpec != null) {
                signature.setParameter(algorithmParameterSpec);
            }
            Key key = this.e;
            if (!(key instanceof PublicKey)) {
                throw new ttp("verify key not public key");
            }
            signature.initVerify((PublicKey) key);
            signature.update(this.b.c());
            return signature.verify(this.b.d());
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | NoSuchProviderException | SignatureException e) {
            throw new ttp("Fail to decrypt: " + e.getMessage());
        }
    }

    private boolean d() throws ttp {
        Mac mac;
        try {
            String transformation = this.b.e().getTransformation();
            if (this.f17372a == KeyStoreProvider.ANDROID_KEYSTORE) {
                mac = Mac.getInstance(transformation);
            } else {
                mac = Mac.getInstance(transformation, this.f17372a.getProviderName());
            }
            mac.init(this.e);
            mac.update(this.b.c());
            return e(this.b.d(), mac.doFinal());
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchProviderException e) {
            throw new ttp("Fail to sign : " + e.getMessage());
        }
    }

    private boolean e(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr2 == null || bArr.length != bArr2.length) {
            return false;
        }
        for (int i = 0; i < bArr.length; i++) {
            if (bArr[i] != bArr2[i]) {
                return false;
            }
        }
        return true;
    }
}
