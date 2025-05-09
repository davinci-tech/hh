package com.huawei.hms.hatool;

import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.secure.android.common.encrypt.hash.SHA;
import java.util.UUID;

/* loaded from: classes4.dex */
public class j {
    private static j b;

    /* renamed from: a, reason: collision with root package name */
    private Context f4593a;

    public String f(String str, String str2) {
        return f1.a(str, str2);
    }

    public Pair<String, String> e(String str, String str2) {
        if (!z.f(str, str2)) {
            return new Pair<>("", "");
        }
        String p = s.c().b().p();
        String q = s.c().b().q();
        if (!TextUtils.isEmpty(p) && !TextUtils.isEmpty(q)) {
            return new Pair<>(p, q);
        }
        Pair<String, String> e = x0.e(this.f4593a);
        s.c().b().k((String) e.first);
        s.c().b().l((String) e.second);
        return e;
    }

    public String d(String str, String str2) {
        return f1.b(str, str2);
    }

    static class a extends e0 {

        /* renamed from: a, reason: collision with root package name */
        String f4594a;
        String b;

        @Override // com.huawei.hms.hatool.e0
        public int d() {
            return (z.k(this.f4594a, this.b) ? 4 : 0) | (z.e(this.f4594a, this.b) ? 2 : 0) | (z.h(this.f4594a, this.b) ? 1 : 0);
        }

        @Override // com.huawei.hms.hatool.e0
        public String c() {
            return z.j(this.f4594a, this.b);
        }

        @Override // com.huawei.hms.hatool.e0
        public String b() {
            return z.g(this.f4594a, this.b);
        }

        @Override // com.huawei.hms.hatool.e0
        public String a(String str) {
            return SHA.sha256Encrypt(str);
        }

        @Override // com.huawei.hms.hatool.e0
        public String a() {
            return z.d(this.f4594a, this.b);
        }

        public a(String str, String str2) {
            this.f4594a = str;
            this.b = str2;
        }
    }

    public i c(String str, String str2) {
        return new a(str, str2).a(this.f4593a);
    }

    public String b(String str, String str2) {
        return i0.b(this.f4593a, str, str2);
    }

    public void a(Context context) {
        if (this.f4593a == null) {
            this.f4593a = context;
        }
    }

    public String a(boolean z) {
        if (!z) {
            return "";
        }
        String e = q0.e();
        if (TextUtils.isEmpty(e)) {
            e = d.a(this.f4593a, "global_v2", "uuid", "");
            if (TextUtils.isEmpty(e)) {
                e = UUID.randomUUID().toString().replace(Constants.LINK, "");
                d.b(this.f4593a, "global_v2", "uuid", e);
            }
            q0.h(e);
        }
        return e;
    }

    public String a(String str, String str2) {
        return i0.a(this.f4593a, str, str2);
    }

    public static j a() {
        j jVar;
        synchronized (j.class) {
            if (b == null) {
                b = new j();
            }
            jVar = b;
        }
        return jVar;
    }
}
