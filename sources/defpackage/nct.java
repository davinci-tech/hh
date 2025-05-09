package defpackage;

/* loaded from: classes9.dex */
public class nct {

    /* renamed from: a, reason: collision with root package name */
    private String f15257a;
    private int b;
    private String c;
    private int d;
    private int e;

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof nct)) {
            return false;
        }
        nct nctVar = (nct) obj;
        return this.c.equals(nctVar.c) && this.f15257a.equals(nctVar.f15257a) && this.b == nctVar.b && this.d == nctVar.d && this.e == nctVar.e;
    }
}
