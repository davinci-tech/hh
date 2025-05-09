package com.huawei.agconnect.credential.obs;

import com.huawei.agconnect.common.api.AgcCrypto;
import com.huawei.agconnect.core.service.auth.Token;
import com.huawei.agconnect.datastore.annotation.SharedPreference;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;

/* loaded from: classes8.dex */
public class ap implements Token {

    /* renamed from: a, reason: collision with root package name */
    private static final long f1752a = 120000;
    private static final String b = "com.huawei.agconnect";

    @SharedPreference(crypto = AgcCrypto.class, fileName = b, isDynamic = true, key = "expires")
    long expires;

    @SharedPreference(crypto = AgcCrypto.class, fileName = b, isDynamic = true, key = "tokenString")
    String tokenString;

    @SharedPreference(crypto = AgcCrypto.class, fileName = b, isDynamic = true, key = HwPayConstant.KEY_VALIDTIME)
    long validTime;

    @Override // com.huawei.agconnect.core.service.auth.Token
    public long getIssuedAt() {
        return 0L;
    }

    @Override // com.huawei.agconnect.core.service.auth.Token
    public long getNotBefore() {
        return 0L;
    }

    @Override // com.huawei.agconnect.core.service.auth.Token
    public String getTokenString() {
        return this.tokenString;
    }

    @Override // com.huawei.agconnect.core.service.auth.Token
    public long getExpiration() {
        return this.expires;
    }

    public boolean a() {
        return this.tokenString != null && System.currentTimeMillis() <= this.validTime;
    }

    private void a(String str, long j) {
        this.tokenString = str;
        this.expires = j;
        this.validTime = (System.currentTimeMillis() + (j * 1000)) - 120000;
    }

    public ap(String str, long j) {
        this.validTime = 0L;
        a(str, j);
    }

    public ap(Token token) {
        this(token.getTokenString(), token.getExpiration());
    }

    public ap() {
        this.validTime = 0L;
    }
}
