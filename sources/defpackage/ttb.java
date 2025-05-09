package defpackage;

import com.huawei.wisesecurity.kfs.crypto.cipher.DecryptHandler;
import com.huawei.wisesecurity.kfs.crypto.codec.Decoder;
import com.huawei.wisesecurity.kfs.crypto.codec.Encoder;
import com.huawei.wisesecurity.kfs.crypto.key.KeyStoreProvider;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/* loaded from: classes7.dex */
public class ttb implements DecryptHandler {

    /* renamed from: a, reason: collision with root package name */
    private final KeyStoreProvider f17366a;
    private final tsx c;
    private final AlgorithmParameterSpec d;
    private final Key e;

    public ttb(KeyStoreProvider keyStoreProvider, Key key, tsx tsxVar, AlgorithmParameterSpec algorithmParameterSpec) {
        this.f17366a = keyStoreProvider;
        this.e = key;
        this.d = algorithmParameterSpec;
        this.c = tsxVar;
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.cipher.DecryptHandler
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public ttb from(byte[] bArr) throws ttp {
        this.c.b(bArr);
        return this;
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.cipher.DecryptHandler
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public ttb fromBase64(String str) throws ttp {
        return e(str, Decoder.BASE64);
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.cipher.DecryptHandler
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public ttb fromBase64Url(String str) throws ttp {
        return e(str, Decoder.BASE64URL);
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.cipher.DecryptHandler
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public ttb fromHex(String str) throws ttp {
        return e(str, Decoder.HEX);
    }

    private ttb e(String str, Decoder decoder) throws ttp {
        try {
            from(decoder.decode(str));
            return this;
        } catch (ttm e) {
            throw new ttp("Fail to decode cipher text: " + e.getMessage());
        }
    }

    private byte[] b() throws ttp {
        Cipher cipher;
        try {
            String transformation = this.c.d().getTransformation();
            if (this.f17366a == KeyStoreProvider.ANDROID_KEYSTORE) {
                cipher = Cipher.getInstance(transformation);
            } else {
                cipher = Cipher.getInstance(transformation, this.f17366a.getProviderName());
            }
            cipher.init(2, this.e, this.d);
            return cipher.doFinal(this.c.c());
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | NoSuchProviderException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            throw new ttp("Fail to decrypt: " + e.getMessage());
        }
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.cipher.DecryptHandler
    public byte[] to() throws ttp {
        return b();
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.cipher.DecryptHandler
    public String toBase64() throws ttp {
        return a(Encoder.BASE64);
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.cipher.DecryptHandler
    public String toHex() throws ttp {
        return a(Encoder.HEX);
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.cipher.DecryptHandler
    public String toRawString() throws ttp {
        return a(Encoder.RAW);
    }

    private String a(Encoder encoder) throws ttp {
        try {
            return encoder.encode(to());
        } catch (ttm e) {
            throw new ttp("Fail to encode plain text: " + e.getMessage());
        }
    }
}
