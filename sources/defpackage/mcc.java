package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.secure.android.common.encrypt.aes.AesCbc;
import com.huawei.secure.android.common.encrypt.keystore.aes.AesCbcKS;
import com.huawei.secure.android.common.encrypt.utils.EncryptUtil;
import com.huawei.secure.android.common.util.SafeBase64;
import java.security.NoSuchAlgorithmException;
import java.security.ProviderException;
import java.util.concurrent.ConcurrentHashMap;
import javax.crypto.KeyGenerator;

/* loaded from: classes6.dex */
public class mcc {
    private static mcc e;
    private final ConcurrentHashMap<String, mcb> b = new ConcurrentHashMap<>();

    private mcc() {
    }

    public static mcc d() {
        if (e == null) {
            e = new mcc();
        }
        return e;
    }

    public void c(mca mcaVar) {
        String a2 = mcaVar.a();
        if (this.b.containsKey(a2)) {
            return;
        }
        byte[] b = b(mcaVar);
        mcb mcbVar = new mcb();
        mcbVar.c(mcaVar);
        mcbVar.c(b);
        mcj.c("WorkKeyManager", "applyWorkKey workKeyAlias: " + a2);
        this.b.put(a2, mcbVar);
    }

    public String b(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            mcj.b("WorkKeyManager", "encrypt content is empty");
            return "";
        }
        byte[] c = c(str2);
        if (mce.d(c)) {
            mcj.b("WorkKeyManager", "encrypt WorkKey is empty");
            return "";
        }
        String encrypt = AesCbc.encrypt(str, c);
        if (!TextUtils.isEmpty(encrypt)) {
            return encrypt;
        }
        mcj.b("WorkKeyManager", "encrypt failed");
        return "";
    }

    public String c(String str, String str2) {
        String str3;
        if (TextUtils.isEmpty(str)) {
            mcj.b("WorkKeyManager", "decrypt content is empty");
            return str;
        }
        byte[] c = c(str2);
        if (mce.d(c)) {
            mcj.b("WorkKeyManager", "decrypt WorkKey is empty");
            return str;
        }
        try {
            str3 = AesCbc.decrypt(str, c);
        } catch (RuntimeException e2) {
            mcj.d("WorkKeyManager", "decrypt -- RuntimeException:", e2);
            str3 = null;
        }
        if (!TextUtils.isEmpty(str3)) {
            return str3;
        }
        mcj.b("WorkKeyManager", "decrypt failed");
        return str;
    }

    private byte[] c(String str) {
        mcb mcbVar = this.b.get(str);
        if (mcbVar == null || mcbVar.a() == null) {
            return null;
        }
        if (!mce.d(mcbVar.b())) {
            return mcbVar.b();
        }
        byte[] b = b(mcbVar.a());
        mcbVar.c(b);
        this.b.put(str, mcbVar);
        return b;
    }

    private byte[] b(mca mcaVar) {
        Context e2 = mcaVar.e();
        String a2 = mcaVar.a();
        String b = mcaVar.b();
        String c = mcaVar.c();
        String c2 = e2 != null ? mco.c(e2, a2, "", b) : null;
        boolean isEmpty = TextUtils.isEmpty(c2);
        mcj.c("WorkKeyManager", "getWorkKeyFromSP cacheWorkKeyIsEmpty: " + isEmpty);
        if (!isEmpty) {
            try {
                String e3 = e(c2, c);
                boolean isEmpty2 = TextUtils.isEmpty(e3);
                mcj.c("WorkKeyManager", "getWorkKeyFromSP decryptWorkKeyIsEmpty: " + isEmpty2);
                if (!isEmpty2) {
                    byte[] decode = SafeBase64.decode(e3, 0);
                    if (!mce.d(decode)) {
                        mcj.c("WorkKeyManager", "getWorkKeyFromSP decode form cache succeed");
                        return decode;
                    }
                }
            } catch (Exception e4) {
                mcj.d("WorkKeyManager", "getWorkKeyFromSP error", e4);
            }
        }
        byte[] b2 = b();
        mco.b(e2, a2, a(SafeBase64.encodeToString(b2, 0), c), b);
        mcj.d("WorkKeyManager", "generate work key success");
        return b2;
    }

    private byte[] b() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128, EncryptUtil.genSecureRandom());
            return keyGenerator.generateKey().getEncoded();
        } catch (NoSuchAlgorithmException e2) {
            mcj.d("WorkKeyManager", "generate workkey fail.", e2);
            return new byte[0];
        }
    }

    private String a(String str, String str2) {
        try {
            return AesCbcKS.encrypt(str2, str);
        } catch (ProviderException e2) {
            mcj.d("WorkKeyManager", "encryptWorkKey ProviderException: ", e2);
            return "";
        }
    }

    private String e(String str, String str2) {
        try {
            return AesCbcKS.decrypt(str2, str);
        } catch (Exception e2) {
            mcj.d("WorkKeyManager", "decryptWorkKey Exception: ", e2);
            return "";
        }
    }
}
