package defpackage;

/* loaded from: classes9.dex */
public class phw {

    /* renamed from: a, reason: collision with root package name */
    private long f16140a;
    private CharSequence b;
    private boolean c;
    private int d;
    private int e;
    private String f;

    public int d() {
        return this.d;
    }

    public void b(int i) {
        this.d = i;
    }

    public int b() {
        return this.e;
    }

    public void a(int i) {
        this.e = i;
    }

    public CharSequence c() {
        return this.b;
    }

    public void d(CharSequence charSequence) {
        this.b = charSequence;
    }

    public String a() {
        return this.f;
    }

    public void b(String str) {
        this.f = str;
    }

    public boolean j() {
        return this.c;
    }

    public void c(boolean z) {
        this.c = z;
    }

    public long e() {
        return this.f16140a;
    }

    public void b(long j) {
        this.f16140a = j;
    }

    public String toString() {
        return "ActiveHoursData{mContent='" + ((Object) this.b) + "', mTime='" + this.f + "', mIsMarked=" + this.c + ", mMarkViewTime=" + this.f16140a + '}';
    }
}
