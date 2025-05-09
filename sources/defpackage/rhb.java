package defpackage;

/* loaded from: classes8.dex */
public final class rhb {

    /* renamed from: a, reason: collision with root package name */
    private String f16759a;
    private boolean b;
    private boolean c;
    private String d;
    private String e;
    private int f;
    private int i;

    public rhb(int i, String str, boolean z, boolean z2, int i2) {
        this.i = i;
        this.f16759a = str;
        this.c = z;
        this.b = z2;
        this.f = i2;
    }

    public rhb(int i, String str, String str2, boolean z, int i2) {
        this.i = i;
        this.e = str;
        this.d = str2;
        this.c = z;
        this.f = i2;
    }

    public String c() {
        return this.f16759a;
    }

    public int i() {
        return this.f;
    }

    public boolean j() {
        return this.b;
    }

    public boolean h() {
        return this.c;
    }

    public void d(boolean z) {
        this.c = z;
    }

    public boolean e() {
        return this.c;
    }

    public int d() {
        return this.i;
    }

    public String a() {
        return this.e;
    }

    public String b() {
        return this.d;
    }

    public String toString() {
        return "ThirdPartAccountAuthBean{itemViewType=" + this.i + ", itemTitleShow=" + this.b + ", operationType=" + this.f + "', isOpen=" + this.c + '}';
    }
}
