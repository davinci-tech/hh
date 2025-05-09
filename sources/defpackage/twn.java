package defpackage;

import android.text.TextUtils;

@Deprecated
/* loaded from: classes9.dex */
public class twn {
    public String b;
    public String c;
    public String d;

    public String e() throws twc {
        if (TextUtils.isEmpty(this.b) || TextUtils.isEmpty(this.c)) {
            throw new twc(2001L, "Get AppAuthtication signStr error");
        }
        return this.b + "." + this.c;
    }

    public String a() throws twc {
        if (TextUtils.isEmpty(this.b) || TextUtils.isEmpty(this.c) || TextUtils.isEmpty(this.d)) {
            throw new twc(2001L, "get  AppAuthtication JWS is empty...");
        }
        return e() + "." + this.d;
    }
}
