package defpackage;

import com.huawei.wisesecurity.kfs.crypto.cipher.EncryptHandler;
import com.huawei.wisesecurity.kfs.crypto.codec.Decoder;
import com.huawei.wisesecurity.kfs.crypto.codec.Encoder;
import com.huawei.wisesecurity.kfs.crypto.key.KeyStoreProvider;
import java.nio.charset.StandardCharsets;
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
public class tta implements EncryptHandler {

    /* renamed from: a, reason: collision with root package name */
    private final tsx f17365a;
    private final AlgorithmParameterSpec c;
    private final KeyStoreProvider d;
    private final Key e;

    public tta(KeyStoreProvider keyStoreProvider, Key key, tsx tsxVar, AlgorithmParameterSpec algorithmParameterSpec) {
        this.d = keyStoreProvider;
        this.e = key;
        this.c = algorithmParameterSpec;
        this.f17365a = tsxVar;
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.cipher.EncryptHandler
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public tta from(byte[] bArr) throws ttp {
        this.f17365a.a(tty.d(bArr));
        return this;
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.cipher.EncryptHandler
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public tta from(String str) throws ttp {
        return from(str.getBytes(StandardCharsets.UTF_8));
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.cipher.EncryptHandler
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public tta fromBase64(String str) throws ttp {
        return d(str, Decoder.BASE64);
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.cipher.EncryptHandler
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public tta fromBase64Url(String str) throws ttp {
        return d(str, Decoder.BASE64URL);
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.cipher.EncryptHandler
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public tta fromHex(String str) throws ttp {
        return d(str, Decoder.HEX);
    }

    private tta d(String str, Decoder decoder) throws ttp {
        try {
            from(decoder.decode(str));
            return this;
        } catch (ttm e) {
            throw new ttp("Fail to decode plain text : " + e.getMessage());
        }
    }

    private void e() throws ttp {
        Cipher cipher;
        try {
            String transformation = this.f17365a.d().getTransformation();
            if (this.d == KeyStoreProvider.ANDROID_KEYSTORE) {
                cipher = Cipher.getInstance(transformation);
            } else {
                cipher = Cipher.getInstance(transformation, this.d.getProviderName());
            }
            cipher.init(1, this.e, this.c);
            tsx tsxVar = this.f17365a;
            tsxVar.b(cipher.doFinal(tsxVar.e()));
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | NoSuchProviderException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            throw new ttp("Fail to encrypt: " + e.getMessage());
        }
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.cipher.EncryptHandler
    public byte[] to() throws ttp {
        e();
        return this.f17365a.c();
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.cipher.EncryptHandler
    public String toBase64() throws ttp {
        return a(Encoder.BASE64);
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.cipher.EncryptHandler
    public String toBase64Url() throws ttp {
        return a(Encoder.BASE64URL);
    }

    @Override // com.huawei.wisesecurity.kfs.crypto.cipher.EncryptHandler
    public String toHex() throws ttp {
        return a(Encoder.HEX);
    }

    private String a(Encoder encoder) throws ttp {
        try {
            e();
            return encoder.encode(this.f17365a.c());
        } catch (ttm e) {
            throw new ttp("Fail to encode cipher bytes: " + e.getMessage());
        }
    }
}
