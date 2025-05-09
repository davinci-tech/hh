package defpackage;

/* loaded from: classes3.dex */
public class bis {

    /* renamed from: a, reason: collision with root package name */
    private int f393a;
    private int c;
    private int d;
    private int e;

    public int e() {
        return this.e;
    }

    public void d(int i) {
        this.e = i;
    }

    public void a(int i) {
        this.f393a = i;
    }

    public void e(int i) {
        this.c = i;
    }

    public int a() {
        return this.d;
    }

    public void c(int i) {
        this.d = i;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(0);
        stringBuffer.append("bondStatus: ").append(this.e).append(" BondStatusInfo: ").append(this.f393a).append("VersionInfo: ").append(this.c).append(" ServiceMtu: ").append(this.d);
        return stringBuffer.toString();
    }
}
