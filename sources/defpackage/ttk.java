package defpackage;

import com.huawei.wisesecurity.kfs.crypto.codec.Decoder;
import com.huawei.wisesecurity.kfs.crypto.codec.Encoder;
import com.huawei.wisesecurity.kfs.crypto.key.KeyStoreProvider;
import com.huawei.wisesecurity.kfs.crypto.signer.SignAlg;
import com.huawei.wisesecurity.kfs.crypto.signer.SignHandler;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Mac;

/* loaded from: classes7.dex */
public class ttk implements SignHandler {

    /* renamed from: a, reason: collision with root package name */
    private final AlgorithmParameterSpec f17374a;
    private final KeyStoreProvider b;
    private final Key c;
    private final ttj e;

    public ttk(KeyStoreProvider keyStoreProvider, Key key, ttj ttjVar, AlgorithmParameterSpec algorithmParameterSpec) {
        this.b = keyStoreProvider;
        this.c = key;
        this.f17374a = algorithmParameterSpec;
        this.e = ttjVar;
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.signer.SignHandler
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ttk from(byte[] bArr) throws ttp {
        this.e.a(tty.d(bArr));
        return this;
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.signer.SignHandler
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public ttk from(String str) throws ttp {
        return from(str.getBytes(StandardCharsets.UTF_8));
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.signer.SignHandler
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public ttk fromBase64(String str) throws ttp {
        return b(str, Decoder.BASE64);
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.signer.SignHandler
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public ttk fromBase64Url(String str) throws ttp {
        return b(str, Decoder.BASE64URL);
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.signer.SignHandler
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ttk fromHex(String str) throws ttp {
        return b(str, Decoder.HEX);
    }

    private ttk b(String str, Decoder decoder) throws ttp {
        try {
            from(decoder.decode(str));
            return this;
        } catch (ttm e) {
            throw new ttp("Fail to decode plain text : " + e.getMessage());
        }
    }

    /* renamed from: ttk$5, reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] e;

        static {
            int[] iArr = new int[SignAlg.values().length];
            e = iArr;
            try {
                iArr[SignAlg.ECDSA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                e[SignAlg.RSA_SHA256.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                e[SignAlg.RSA_SHA256_PSS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                e[SignAlg.HMAC_SHA256.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private void b() throws ttp {
        int i = AnonymousClass5.e[this.e.e().ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            e();
        } else if (i == 4) {
            d();
        } else {
            throw new ttp("unsupported sign alg : " + this.e.e().getTransformation());
        }
    }

    private void e() throws ttp {
        Signature signature;
        try {
            String transformation = this.e.e().getTransformation();
            if (this.b == KeyStoreProvider.ANDROID_KEYSTORE) {
                signature = Signature.getInstance(transformation);
            } else {
                signature = Signature.getInstance(transformation, this.b.getProviderName());
            }
            AlgorithmParameterSpec algorithmParameterSpec = this.f17374a;
            if (algorithmParameterSpec != null) {
                signature.setParameter(algorithmParameterSpec);
            }
            Key key = this.c;
            if (!(key instanceof PrivateKey)) {
                throw new ttp("sign key not private key");
            }
            signature.initSign((PrivateKey) key);
            signature.update(this.e.c());
            this.e.d(signature.sign());
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | NoSuchProviderException | SignatureException e) {
            throw new ttp("Fail to sign : " + e.getMessage());
        }
    }

    private void d() throws ttp {
        Mac mac;
        try {
            String transformation = this.e.e().getTransformation();
            if (this.b == KeyStoreProvider.ANDROID_KEYSTORE) {
                mac = Mac.getInstance(transformation);
            } else {
                mac = Mac.getInstance(transformation, this.b.getProviderName());
            }
            mac.init(this.c);
            mac.update(this.e.c());
            this.e.d(mac.doFinal());
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchProviderException e) {
            throw new ttp("Fail to sign : " + e.getMessage());
        }
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.signer.SignHandler
    public byte[] sign() throws ttp {
        b();
        return this.e.d();
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.signer.SignHandler
    public String signBase64() throws ttp {
        return d(Encoder.BASE64);
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.signer.SignHandler
    public String signBase64Url() throws ttp {
        return d(Encoder.BASE64URL);
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.signer.SignHandler
    public String signHex() throws ttp {
        return d(Encoder.HEX);
    }

    private String d(Encoder encoder) throws ttp {
        try {
            b();
            return encoder.encode(this.e.d());
        } catch (ttm e) {
            throw new ttp("Fail to encode signature bytes: " + e.getMessage());
        }
    }
}
