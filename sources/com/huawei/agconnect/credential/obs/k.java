package com.huawei.agconnect.credential.obs;

import com.huawei.agconnect.datastore.annotation.ICrypto;
import com.huawei.secure.android.common.encrypt.aes.AesCbc;
import com.huawei.secure.android.common.util.HexUtil;
import javax.crypto.SecretKey;

/* loaded from: classes2.dex */
public class k implements ICrypto {

    /* renamed from: a, reason: collision with root package name */
    private static final String f1774a = "PBKDF2WithHmacSHA1";
    private static final int b = 5000;
    private SecretKey c;

    @Override // com.huawei.agconnect.datastore.annotation.ICrypto
    public String encrypt(String str) {
        return AesCbc.encrypt(str, a());
    }

    @Override // com.huawei.agconnect.datastore.annotation.ICrypto
    public String decrypt(String str) {
        return AesCbc.decrypt(str, a());
    }

    public String a() {
        SecretKey b2 = b();
        if (b2 == null) {
            return null;
        }
        return HexUtil.byteArray2HexStr(b2.getEncoded());
    }

    private SecretKey b() {
        if (this.c == null) {
            this.c = g.a().a(f1774a, 5000);
        }
        return this.c;
    }
}
