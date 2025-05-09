package defpackage;

/* loaded from: classes6.dex */
public class nzt extends nzr {
    private static final long serialVersionUID = -1573149602936045007L;
    private String b;
    private String d;
    private String e;

    public nzt(String str, String str2, String str3) {
        this.b = str;
        this.e = str2;
        this.d = str3;
    }

    public String d() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public String c() {
        return this.e;
    }

    public String a() {
        return this.d;
    }

    @Override // defpackage.nzr
    public String toString() {
        return "UrlPlaceholder{mUrl='" + this.b + "', mFlag='" + this.e + "', mBranchId='" + this.d + "', mValue='" + this.f15570a + "', mPlaceholderType=" + this.c + '}';
    }
}
