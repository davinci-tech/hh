package com.huawei.hianalytics;

import android.text.TextUtils;
import com.huawei.hianalytics.core.common.AesGcmKsWrapper;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.secure.android.common.encrypt.aes.AesCbc;
import com.huawei.secure.android.common.encrypt.hash.PBKDF2;
import com.huawei.secure.android.common.encrypt.utils.EncryptUtil;
import com.huawei.secure.android.common.encrypt.utils.HexUtil;

/* loaded from: classes4.dex */
public class p0 {

    /* renamed from: a, reason: collision with root package name */
    public static p0 f3892a;

    /* renamed from: a, reason: collision with other field name */
    public static final Object f60a = new Object();
    public static final Object b = new Object();

    /* renamed from: a, reason: collision with other field name */
    public String f61a;

    /* renamed from: a, reason: collision with other field name */
    public byte[] f62a;

    /* renamed from: b, reason: collision with other field name */
    public String f63b;

    public static p0 a() {
        if (f3892a == null) {
            synchronized (p0.class) {
                if (f3892a == null) {
                    f3892a = new p0();
                }
            }
        }
        return f3892a;
    }

    /* renamed from: a, reason: collision with other method in class */
    public final boolean m579a() {
        return true;
    }

    /* renamed from: b, reason: collision with other method in class */
    public final void m580b() {
        String generateSecureRandomStr;
        String generateSecureRandomStr2;
        String generateSecureRandomStr3;
        String generateSecureRandomStr4;
        if (TextUtils.isEmpty(this.f61a)) {
            o0 o0Var = new o0();
            long a2 = j.a("Privacy_MY", "assemblyFlash", -1L);
            if (a2 != -1 && System.currentTimeMillis() - a2 <= 31536000000L) {
                generateSecureRandomStr = o0Var.b("aprpap");
                generateSecureRandomStr2 = o0Var.b("febdoc");
                generateSecureRandomStr3 = o0Var.b("marfil");
                generateSecureRandomStr4 = o0Var.b("maywnj");
            } else {
                generateSecureRandomStr = EncryptUtil.generateSecureRandomStr(128);
                o0Var.a("aprpap", generateSecureRandomStr);
                generateSecureRandomStr2 = EncryptUtil.generateSecureRandomStr(128);
                o0Var.a("febdoc", generateSecureRandomStr2);
                generateSecureRandomStr3 = EncryptUtil.generateSecureRandomStr(128);
                o0Var.a("marfil", generateSecureRandomStr3);
                generateSecureRandomStr4 = EncryptUtil.generateSecureRandomStr(128);
                o0Var.a("maywnj", generateSecureRandomStr4);
                j.m558a("Privacy_MY", "assemblyFlash", System.currentTimeMillis());
            }
            byte[] hexStr2ByteArray = HexUtil.hexStr2ByteArray(generateSecureRandomStr);
            byte[] hexStr2ByteArray2 = HexUtil.hexStr2ByteArray(generateSecureRandomStr2);
            byte[] hexStr2ByteArray3 = HexUtil.hexStr2ByteArray(generateSecureRandomStr3);
            byte[] hexStr2ByteArray4 = HexUtil.hexStr2ByteArray("f6040d0e807aaec325ecf44823765544e92905158169f694b282bf17388632cf95a83bae7d2d235c1f039b0df1dcca5fda619b6f7f459f2ff8d70ddb7b601592fe29fcae58c028f319b3b12495e67aa5390942a997a8cb572c8030b2df5c2b622608bea02b0c3e5d4dff3f72c9e3204049a45c0760cd3604af8d57f0e0c693cc");
            int length = hexStr2ByteArray.length;
            if (length > hexStr2ByteArray2.length) {
                length = hexStr2ByteArray2.length;
            }
            if (length > hexStr2ByteArray3.length) {
                length = hexStr2ByteArray3.length;
            }
            if (length > hexStr2ByteArray4.length) {
                length = hexStr2ByteArray4.length;
            }
            char[] cArr = new char[length];
            for (int i = 0; i < length; i++) {
                cArr[i] = (char) (((hexStr2ByteArray[i] ^ hexStr2ByteArray2[i]) ^ hexStr2ByteArray3[i]) ^ hexStr2ByteArray4[i]);
            }
            this.f61a = HexUtil.byteArray2HexStr(PBKDF2.pbkdf2(cArr, HexUtil.hexStr2ByteArray(generateSecureRandomStr4), 10000, 128));
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x005e, code lost:
    
        if (m579a() != false) goto L21;
     */
    /* renamed from: a, reason: collision with other method in class */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void m577a() {
        /*
            r4 = this;
            java.lang.String r0 = r4.f63b
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L73
            java.lang.String r0 = "Privacy_MY"
            java.lang.String r1 = "PrivacyData"
            java.lang.String r2 = ""
            java.lang.String r0 = com.huawei.hianalytics.j.a(r0, r1, r2)
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            r3 = 16
            if (r1 == 0) goto L26
            java.lang.String r0 = com.huawei.secure.android.common.encrypt.utils.EncryptUtil.generateSecureRandomStr(r3)
            java.lang.String r1 = r4.a(r0)
            r4.m578a(r1)
            goto L71
        L26:
            boolean r1 = r4.m579a()
            if (r1 == 0) goto L32
            java.lang.String r1 = "analytics_keystore_formal"
            java.lang.String r2 = com.huawei.hianalytics.core.common.AesGcmKsWrapper.decrypt(r1, r0)
        L32:
            boolean r1 = android.text.TextUtils.isEmpty(r2)
            if (r1 != 0) goto L3a
            r0 = r2
            goto L71
        L3a:
            java.lang.String r1 = "RootKeyManager"
            java.lang.String r2 = "decrypt work key first"
            com.huawei.hianalytics.core.log.HiLog.i(r1, r2)
            java.lang.String r1 = r4.b()
            java.lang.String r0 = com.huawei.secure.android.common.encrypt.aes.AesCbc.decrypt(r0, r1)
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto L61
            java.lang.String r0 = com.huawei.secure.android.common.encrypt.utils.EncryptUtil.generateSecureRandomStr(r3)
            java.lang.String r1 = r4.a(r0)
            r4.m578a(r1)
            boolean r1 = r4.m579a()
            if (r1 == 0) goto L71
            goto L6e
        L61:
            boolean r1 = r4.m579a()
            if (r1 == 0) goto L71
            java.lang.String r1 = r4.a(r0)
            r4.m578a(r1)
        L6e:
            com.huawei.hianalytics.o0.a()
        L71:
            r4.f63b = r0
        L73:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hianalytics.p0.m577a():void");
    }

    public final String b() {
        if (TextUtils.isEmpty(this.f61a)) {
            synchronized (b) {
                m580b();
            }
        }
        return this.f61a;
    }

    /* renamed from: a, reason: collision with other method in class */
    public final void m578a(String str) {
        HiLog.si("RootKeyManager", "refresh local key to the sp");
        j.m559a("Privacy_MY", "PrivacyData", str);
        j.m558a("Privacy_MY", "flashKeyTime", System.currentTimeMillis());
    }

    public final String a(String str) {
        if (!m579a()) {
            return AesCbc.encrypt(str, b());
        }
        HiLog.i("RootKeyManager", "load work key encrypt is gcm ks");
        return AesGcmKsWrapper.encrypt("analytics_keystore_formal", str);
    }

    /* renamed from: a, reason: collision with other method in class */
    public String m576a() {
        if (TextUtils.isEmpty(this.f63b)) {
            synchronized (f60a) {
                m577a();
            }
        }
        return this.f63b;
    }
}
