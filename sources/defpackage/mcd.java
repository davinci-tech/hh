package defpackage;

import android.text.TextUtils;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.secure.android.common.encrypt.rsa.RSAEncrypt;
import com.huawei.secure.android.common.util.SafeBase64;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.californium.elements.util.JceNames;

/* loaded from: classes6.dex */
public class mcd {
    private static final mcd b = new mcd();
    private volatile Map<String, Key> d = null;

    private mcd() {
    }

    public static mcd c() {
        return b;
    }

    public void a() {
        try {
            if (this.d == null) {
                synchronized (mcd.class) {
                    if (this.d == null) {
                        this.d = RSAEncrypt.generateRSAKeyPair(HealthData.ECG);
                        mcj.c("RSAEncryptHelper", "init success.");
                    }
                }
            }
        } catch (NoSuchAlgorithmException e) {
            mcj.d("RSAEncryptHelper", "init NoSuchAlgorithmException : ", e);
        }
    }

    public static PrivateKey e(String str) {
        if (TextUtils.isEmpty(str)) {
            mcj.b("RSAEncryptHelper", "privateKeyStr is null !");
            return null;
        }
        try {
            return KeyFactory.getInstance(JceNames.RSA).generatePrivate(new PKCS8EncodedKeySpec(SafeBase64.decode(str, 0)));
        } catch (Exception e) {
            mcj.d("RSAEncryptHelper", "load Key Exception:", e);
            return null;
        }
    }

    public Map<String, String> c(boolean z) {
        if (this.d == null) {
            a();
        }
        HashMap hashMap = new HashMap();
        String encodeToString = SafeBase64.encodeToString(((PrivateKey) this.d.get("privateKey")).getEncoded(), 2);
        String encodeToString2 = SafeBase64.encodeToString(((PublicKey) this.d.get("publicKey")).getEncoded(), 2);
        hashMap.put("privateKey", encodeToString);
        hashMap.put("publicKey", encodeToString2);
        if (z) {
            this.d = null;
            mci.c(new Runnable() { // from class: mbz
                @Override // java.lang.Runnable
                public final void run() {
                    mcd.this.a();
                }
            });
        }
        return hashMap;
    }
}
