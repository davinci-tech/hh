package com.huawei.hianalytics;

import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.secure.android.common.encrypt.utils.HexUtil;
import com.huawei.secure.android.common.util.SafeBase64;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import org.eclipse.californium.elements.util.JceNames;

/* loaded from: classes4.dex */
public class h {

    /* renamed from: a, reason: collision with root package name */
    public static final Charset f3873a = StandardCharsets.UTF_8;

    public static String a(String str, String str2, String str3) {
        if (str == null || str3 == null || str3.isEmpty()) {
            HiLog.e("RsaCipher", "content or public key is null");
            return "";
        }
        if (!"2.0".equals(str2)) {
            HiLog.e("RsaCipher", "unsupported pubKey version: " + str2);
            return "";
        }
        try {
            PublicKey generatePublic = KeyFactory.getInstance(JceNames.RSA).generatePublic(new X509EncodedKeySpec(SafeBase64.decode(str, 0)));
            Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
            cipher.init(1, generatePublic);
            return HexUtil.byteArray2HexStr(cipher.doFinal(str3.getBytes(f3873a)));
        } catch (Throwable th) {
            HiLog.e("RsaCipher", "rsaEncrypt failed: " + th.getMessage());
            return "";
        }
    }
}
