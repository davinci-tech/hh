package com.huawei.agconnect.credential.obs;

import android.text.TextUtils;
import com.huawei.agconnect.datastore.annotation.ICrypto;
import com.huawei.secure.android.common.encrypt.aes.AesCbc;
import com.huawei.secure.android.common.util.HexUtil;
import javax.crypto.SecretKey;

/* loaded from: classes2.dex */
public class l implements ICrypto {

    /* renamed from: a, reason: collision with root package name */
    private static final String f1775a = "PBKDF2WithHmacSHA256";
    private static final int b = 1;
    private static final String c = "AGC_V3_";
    private SecretKey d;

    @Override // com.huawei.agconnect.datastore.annotation.ICrypto
    public String encrypt(String str) {
        return c + AesCbc.encrypt(str, a());
    }

    @Override // com.huawei.agconnect.datastore.annotation.ICrypto
    public String decrypt(String str) {
        return (TextUtils.isEmpty(str) || !str.startsWith(c)) ? "" : AesCbc.decrypt(str.substring(7), a());
    }

    public String a() {
        SecretKey b2 = b();
        if (b2 == null) {
            return null;
        }
        return HexUtil.byteArray2HexStr(b2.getEncoded());
    }

    private SecretKey b() {
        if (this.d == null) {
            this.d = g.a().a(f1775a, 1);
        }
        return this.d;
    }
}
