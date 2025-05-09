package com.huawei.agconnect.credential.obs;

import android.text.TextUtils;
import com.huawei.agconnect.common.api.Logger;
import com.huawei.agconnect.datastore.annotation.SharedPreference;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKey;

/* loaded from: classes2.dex */
public class g {

    /* renamed from: a, reason: collision with root package name */
    private static final String f1769a = "AesComponent";
    private static final int b = 32;

    @SharedPreference(fileName = "com.huawei.appgallery.datastore", key = "fish")
    String fish;

    @SharedPreference(fileName = "com.huawei.agconnect.internal", key = "jerry")
    String jerry;

    public SecretKey a(String str, int i) {
        if (TextUtils.isEmpty(this.jerry) || TextUtils.isEmpty(this.fish)) {
            h.a().f(this);
            h.a().e(this);
            if (a(this.jerry) || a(this.fish)) {
                this.jerry = n.a(32);
                this.fish = n.a(32);
                h.a().b(this);
                h.a().c(this);
            }
        }
        try {
            return n.a(a(al.d(), this.jerry, "767d9b01105c2acd25fb7955a5394f78"), this.fish, str, i);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException unused) {
            Logger.e(f1769a, "Exception when reading the 'T&J&C&F' for 'KeyManager'.");
            return null;
        }
    }

    private boolean a(String str) {
        return TextUtils.isEmpty(str) || str.length() != 32;
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        public static g f1770a = new g();

        private a() {
        }
    }

    private static String a(String str, String str2, String str3) {
        return n.a(n.a(n.a(n.a(str, -4), str2), 6), str3);
    }

    public static g a() {
        return a.f1770a;
    }
}
