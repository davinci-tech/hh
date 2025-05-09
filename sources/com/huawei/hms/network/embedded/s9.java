package com.huawei.hms.network.embedded;

/* loaded from: classes9.dex */
public final class s9 {
    public static final String e = ":status";
    public static final String f = ":method";
    public static final String g = ":path";
    public static final String h = ":scheme";
    public static final String i = ":authority";

    /* renamed from: a, reason: collision with root package name */
    public final eb f5479a;
    public final eb b;
    public final int c;
    public static final eb d = eb.d(":");
    public static final eb j = eb.d(":status");
    public static final eb k = eb.d(":method");
    public static final eb l = eb.d(":path");
    public static final eb m = eb.d(":scheme");
    public static final eb n = eb.d(":authority");

    public String toString() {
        return f8.a("%s: %s", this.f5479a.n(), this.b.n());
    }

    public int hashCode() {
        return ((this.f5479a.hashCode() + 527) * 31) + this.b.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof s9) {
            s9 s9Var = (s9) obj;
            if (this.f5479a.equals(s9Var.f5479a) && this.b.equals(s9Var.b)) {
                return true;
            }
        }
        return false;
    }

    public s9(String str, String str2) {
        this(eb.d(str), eb.d(str2));
    }

    public s9(eb ebVar, String str) {
        this(ebVar, eb.d(str));
    }

    public s9(eb ebVar, eb ebVar2) {
        this.f5479a = ebVar;
        this.b = ebVar2;
        this.c = ebVar.j() + 32 + ebVar2.j();
    }
}
