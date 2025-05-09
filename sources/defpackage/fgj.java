package defpackage;

import java.util.Objects;

/* loaded from: classes4.dex */
public class fgj {

    /* renamed from: a, reason: collision with root package name */
    private int f12495a;
    private int b;
    private int d;

    public fgj(int i, int i2, int i3) {
        e(i);
        b(i2);
        a(i3);
    }

    public int c() {
        return this.f12495a;
    }

    public void e(int i) {
        this.f12495a = i;
    }

    public int a() {
        return this.b;
    }

    public void b(int i) {
        this.b = i;
    }

    public int b() {
        return this.d;
    }

    public void a(int i) {
        this.d = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        fgj fgjVar = (fgj) obj;
        return this.f12495a == fgjVar.f12495a && this.b == fgjVar.b && this.d == fgjVar.d;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.f12495a), Integer.valueOf(this.b), Integer.valueOf(this.d));
    }
}
