package com.huawei.hms.support.hwid.common.b;

import android.text.TextUtils;
import com.huawei.hms.support.hwid.common.e.g;
import com.huawei.secure.android.common.encrypt.rsa.RSAEncrypt;
import com.huawei.secure.android.common.util.SafeBase64;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import org.eclipse.californium.elements.util.JceNames;

/* loaded from: classes9.dex */
public class b {
    public static PublicKey a(String str) throws Exception {
        return KeyFactory.getInstance(JceNames.RSA).generatePublic(new X509EncodedKeySpec(a.a(str)));
    }

    public static String a(String str, String str2) {
        byte[] decode;
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            String encrypt = RSAEncrypt.encrypt(str, a(str2));
            return (TextUtils.isEmpty(encrypt) || (decode = SafeBase64.decode(encrypt, 0)) == null) ? "" : a.a(decode);
        } catch (Exception unused) {
            g.b("HwIdEncrypter", "rsaEncrpter Exception");
            return "";
        }
    }
}
