package com.huawei.watchface;

import android.text.TextUtils;
import com.huawei.hms.network.embedded.x1;
import com.huawei.secure.android.common.encrypt.aes.AesCbc;
import com.huawei.secure.android.common.encrypt.keystore.aes.AesCbcKS;
import com.huawei.secure.android.common.encrypt.utils.EncryptUtil;
import com.huawei.watchface.utils.ArrayUtils;
import com.huawei.watchface.utils.HwLog;
import java.security.ProviderException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/* loaded from: classes7.dex */
public class ao {

    /* renamed from: a, reason: collision with root package name */
    private static Map<String, byte[]> f10895a = new HashMap();
    private static final byte[] b = new byte[0];
    private static final HashSet<String> c;

    static {
        HashSet<String> hashSet = new HashSet<>();
        c = hashSet;
        try {
            hashSet.add("storagePw");
            hashSet.add("savePw");
            f10895a.put("storagePw", b("storagePw"));
            f10895a.put("savePw", b("savePw"));
        } catch (Exception e) {
            HwLog.e(x1.f5564a, "init Exception:" + HwLog.printException(e));
        }
    }

    public static String a(String str, String str2) {
        String str3 = null;
        try {
        } catch (Exception e) {
            HwLog.e(x1.f5564a, "encrypt Exception:" + HwLog.printException(e));
        }
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        byte[] b2 = b(str2);
        if (ArrayUtils.a(b2)) {
            return "";
        }
        str3 = AesCbc.encrypt(str, b2);
        return TextUtils.isEmpty(str3) ? "" : str3;
    }

    public static String b(String str, String str2) {
        String str3 = null;
        try {
        } catch (RuntimeException e) {
            HwLog.e(x1.f5564a, "decrypt -- RuntimeException:" + HwLog.printException((Exception) e));
        } catch (Exception e2) {
            HwLog.e(x1.f5564a, "decrypt -- Exception:" + HwLog.printException(e2));
        }
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        byte[] b2 = b(str2);
        if (ArrayUtils.a(b2)) {
            return str;
        }
        str3 = AesCbc.decrypt(str, b2);
        return TextUtils.isEmpty(str3) ? str : str3;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x004e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x004f A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String c(java.lang.String r3, java.lang.String r4) {
        /*
            java.lang.String r0 = "KeyGen"
            boolean r1 = android.text.TextUtils.isEmpty(r3)
            if (r1 == 0) goto L9
            return r3
        L9:
            byte[] r4 = b(r4)
            boolean r1 = com.huawei.watchface.utils.ArrayUtils.a(r4)
            r2 = 0
            if (r1 == 0) goto L15
            return r2
        L15:
            java.lang.String r3 = com.huawei.secure.android.common.encrypt.aes.AesCbc.decrypt(r3, r4)     // Catch: java.lang.Exception -> L1a java.lang.RuntimeException -> L31
            goto L48
        L1a:
            r3 = move-exception
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r1 = "decrypt -- Exception:"
            r4.<init>(r1)
            java.lang.String r3 = com.huawei.watchface.utils.HwLog.printException(r3)
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            com.huawei.watchface.utils.HwLog.e(r0, r3)
            goto L47
        L31:
            r3 = move-exception
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r1 = "decrypt -- RuntimeException:"
            r4.<init>(r1)
            java.lang.String r3 = com.huawei.watchface.utils.HwLog.printException(r3)
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            com.huawei.watchface.utils.HwLog.e(r0, r3)
        L47:
            r3 = r2
        L48:
            boolean r4 = android.text.TextUtils.isEmpty(r3)
            if (r4 == 0) goto L4f
            return r2
        L4f:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.watchface.ao.c(java.lang.String, java.lang.String):java.lang.String");
    }

    private static byte[] b(String str) {
        if (!c.contains(str)) {
            HwLog.e(x1.f5564a, "KeyAlias is illegal");
            return b;
        }
        return c(str);
    }

    private static byte[] c(String str) {
        byte[] bArr;
        byte[] bArr2 = null;
        try {
            if (f10895a.containsKey(str)) {
                bArr = f10895a.get(str);
                try {
                    if (!ArrayUtils.a(bArr)) {
                        return bArr;
                    }
                } catch (Exception e) {
                    e = e;
                    HwLog.e(x1.f5564a, "getCommonKey Exception:" + HwLog.printException(e));
                    return bArr;
                }
            }
            bArr2 = a(str);
            f10895a.put(str, bArr2);
            return bArr2;
        } catch (Exception e2) {
            e = e2;
            bArr = bArr2;
        }
    }

    public static byte[] a(String str) {
        String b2 = dp.b(str, "KeyPref", "");
        byte[] bArr = new byte[0];
        if (TextUtils.isEmpty(b2)) {
            byte[] a2 = a();
            dp.a(str, d(aj.a(a2)), "KeyPref");
            HwLog.w(x1.f5564a, "generate work key success, keytype is " + str);
            return a2;
        }
        try {
            return aj.a(e(b2));
        } catch (IllegalArgumentException e) {
            HwLog.i(x1.f5564a, "IllegalArgumentException" + HwLog.printException((Exception) e));
            return bArr;
        }
    }

    private static byte[] a() {
        HwLog.d(x1.f5564a, "genWKey");
        return EncryptUtil.generateSecureRandom(16);
    }

    private static String d(String str) {
        try {
            return AesCbcKS.encrypt("themeRootKey", str);
        } catch (ProviderException e) {
            HwLog.e(x1.f5564a, "encryptWorkKey ProviderException ：" + HwLog.printException((Exception) e));
            return "";
        } catch (Exception e2) {
            HwLog.e(x1.f5564a, "encryptWorkKey exception：" + HwLog.printException(e2));
            return "";
        }
    }

    private static String e(String str) {
        try {
            return AesCbcKS.decrypt("themeRootKey", str);
        } catch (ProviderException e) {
            HwLog.e(x1.f5564a, "用根密钥解密工作密钥发生异常 ：" + HwLog.printException((Exception) e));
            return str;
        } catch (Exception e2) {
            HwLog.e(x1.f5564a, "用根密钥解密工作密钥发生异常 exception：" + HwLog.printException(e2));
            return str;
        }
    }
}
