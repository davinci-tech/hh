package defpackage;

import android.text.TextUtils;

/* loaded from: classes7.dex */
public class jz {

    /* renamed from: a, reason: collision with root package name */
    public String f14212a;
    public Boolean d;

    public boolean c() {
        Boolean bool = this.d;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    public void c(String str) {
        this.f14212a = str;
    }

    public boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return TextUtils.equals(this.f14212a, str);
    }

    public boolean a() {
        return this.d != null;
    }

    public void b(boolean z) {
        this.d = Boolean.valueOf(z);
    }
}
