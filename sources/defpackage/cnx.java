package defpackage;

import java.io.Serializable;

/* loaded from: classes3.dex */
public class cnx implements Serializable {
    private static final long serialVersionUID = 5732217115586382887L;

    /* renamed from: a, reason: collision with root package name */
    private String f804a;
    private String b;
    private int c;
    private String e;

    public void e(String str) {
        this.e = (String) cpt.d(str);
    }

    public String c() {
        return (String) cpt.d(this.b);
    }

    public void a(String str) {
        this.b = (String) cpt.d(str);
    }

    public String e() {
        return (String) cpt.d(this.f804a);
    }

    public void c(String str) {
        this.f804a = (String) cpt.d(str);
    }

    public int d() {
        return ((Integer) cpt.d(Integer.valueOf(this.c))).intValue();
    }

    public void b(int i) {
        this.c = ((Integer) cpt.d(Integer.valueOf(i))).intValue();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("WeightResultShowModel [weight=");
        sb.append(this.e);
        sb.append(", bodyFat=");
        sb.append(this.b);
        sb.append(", targetWeight=");
        sb.append(this.f804a);
        sb.append(", bodyFatColor=");
        sb.append(this.c);
        sb.append("]");
        return sb.toString();
    }
}
