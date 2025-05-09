package defpackage;

import android.text.TextUtils;
import java.util.Locale;

/* loaded from: classes5.dex */
public class jmp {
    private String c;
    private String d;
    private String e;

    public String c() {
        return this.e;
    }

    public void d(String str) {
        this.e = str;
    }

    public void a(String str) {
        this.d = str;
    }

    public String a() {
        return this.d;
    }

    public String b() {
        return this.c;
    }

    public void c(String str) {
        this.c = str;
    }

    public String e() {
        if (TextUtils.isEmpty(this.c) || this.c.length() <= 42) {
            return null;
        }
        return (this.c.substring(0, 4) + this.c.substring(20, 36)).toUpperCase(Locale.getDefault());
    }
}
