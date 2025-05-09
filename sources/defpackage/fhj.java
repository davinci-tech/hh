package defpackage;

import android.text.TextUtils;

/* loaded from: classes7.dex */
public class fhj {

    /* renamed from: a, reason: collision with root package name */
    private String f12514a;
    private fgy b;
    private int c;
    private String d;
    private int e;

    public fhj(fgy fgyVar) {
        this.b = fgyVar;
        if (fgyVar == null) {
            return;
        }
        String g = fgyVar.g();
        if (TextUtils.isEmpty(g)) {
            return;
        }
        this.e = fgt.c(g, "\\.", 0);
        this.c = fgt.c(g, "\\.", 1);
    }

    public int e() {
        return this.e;
    }

    public int b() {
        return this.c;
    }

    public String d() {
        return this.f12514a;
    }

    public void c(String str) {
        this.f12514a = str;
    }

    public String c() {
        return this.d;
    }

    public void a(String str) {
        this.d = str;
    }
}
