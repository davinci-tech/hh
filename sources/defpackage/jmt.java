package defpackage;

/* loaded from: classes5.dex */
public class jmt {

    /* renamed from: a, reason: collision with root package name */
    private String f13963a;
    private int c = 0;
    private int b = 0;

    public String c() {
        return this.f13963a;
    }

    public void e(String str) {
        this.f13963a = str;
    }

    public int b() {
        return this.c;
    }

    public boolean equals(Object obj) {
        String str;
        if (obj == this) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass() || !(obj instanceof jmt)) {
            return false;
        }
        jmt jmtVar = (jmt) obj;
        if (jmtVar.c == this.c && this.b == jmtVar.b && (str = this.f13963a) != null) {
            return str.equals(jmtVar.f13963a);
        }
        return false;
    }

    public int hashCode() {
        String str = this.f13963a;
        return ((((str != null ? str.hashCode() : 0) * 31) + this.c) * 31) + this.b;
    }
}
