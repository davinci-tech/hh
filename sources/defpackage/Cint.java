package defpackage;

/* renamed from: int, reason: invalid class name */
/* loaded from: classes7.dex */
public class Cint {

    /* renamed from: a, reason: collision with root package name */
    private String f13503a;
    private String b;
    private boolean d;
    private String e;

    public Cint(boolean z, String str) {
        this(z, str, null, null);
    }

    public Cint(boolean z, String str, String str2) {
        this(z, str, null, str2);
    }

    public Cint(boolean z, String str, String str2, String str3) {
        this.d = z;
        this.b = str;
        this.f13503a = str2;
        this.e = str3;
    }

    public boolean e() {
        return this.d;
    }

    public boolean a() {
        return "getCount".equals(this.b);
    }

    public String c() {
        return this.f13503a;
    }

    public String b() {
        return this.b;
    }

    public String d() {
        return this.e;
    }
}
