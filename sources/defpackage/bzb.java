package defpackage;

/* loaded from: classes3.dex */
public class bzb {
    private int[] i;
    private int b = 4;
    private int c = 0;
    private int e = -1;

    /* renamed from: a, reason: collision with root package name */
    private boolean f551a = false;
    private int d = 1;

    public int e() {
        return this.d;
    }

    public void a(int i) {
        this.d = i;
    }

    public int c() {
        return this.b;
    }

    public void d(int i) {
        this.b = i;
    }

    public int a() {
        return this.c;
    }

    public void b(int i) {
        this.c = i;
    }

    public int d() {
        return this.e;
    }

    public void c(int i) {
        this.e = i;
    }

    public int[] b() {
        int[] iArr = this.i;
        return iArr == null ? new int[0] : (int[]) iArr.clone();
    }

    public void e(int[] iArr) {
        if (iArr == null) {
            this.i = new int[0];
        } else {
            this.i = (int[]) iArr.clone();
        }
    }

    public boolean g() {
        return this.f551a;
    }

    public void c(boolean z) {
        this.f551a = z;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Option{type=");
        sb.append(this.b);
        sb.append(", duration=");
        sb.append(this.c);
        sb.append(", isSupportDevice=");
        sb.append(this.d);
        sb.append(", rhythm=");
        sb.append(this.e);
        int[] iArr = this.i;
        if (iArr == null || iArr.length < 1) {
            sb.append(", vibrateDuration=[]");
        } else {
            sb.append(", vibrateDuration=[");
            for (int i : this.i) {
                sb.append(i);
                sb.append(", ");
            }
            sb.append("]");
        }
        sb.append(", screenOn=");
        sb.append(this.f551a);
        sb.append('}');
        return sb.toString();
    }
}
