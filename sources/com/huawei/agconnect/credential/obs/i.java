package com.huawei.agconnect.credential.obs;

import android.text.TextUtils;
import com.huawei.agconnect.datastore.annotation.ICrypto;
import com.huawei.agconnect.datastore.annotation.SharedPreference;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import com.huawei.secure.android.common.encrypt.aes.AesCbc;

/* loaded from: classes2.dex */
public class i implements ICrypto {

    /* renamed from: a, reason: collision with root package name */
    private static final int f1772a = 16;
    private String b = null;

    @SharedPreference(fileName = "com.huawei.appgallery.datastore", key = MedalConstants.EVENT_KEY)
    String randomKey;

    @Override // com.huawei.agconnect.datastore.annotation.ICrypto
    public String encrypt(String str) {
        return AesCbc.encrypt(str, a());
    }

    @Override // com.huawei.agconnect.datastore.annotation.ICrypto
    public String decrypt(String str) {
        return AesCbc.decrypt(str, a());
    }

    private String b() {
        j.a().d(this);
        if (TextUtils.isEmpty(this.randomKey) || this.randomKey.length() != 16) {
            this.randomKey = n.a(16);
            j.a().b(this);
        }
        return this.randomKey;
    }

    private String a() {
        if (TextUtils.isEmpty(this.b)) {
            this.b = n.a(n.a(al.c(), 6), m.a()) + n.a(n.a("AE6D8285", -4), al.f1751a) + b();
        }
        return this.b;
    }
}
