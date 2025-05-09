package defpackage;

import health.compact.a.CommonUtil;

/* loaded from: classes3.dex */
public class dcu {

    /* renamed from: a, reason: collision with root package name */
    private String f11593a;
    private String b;
    private String c;
    private boolean d = false;
    private String e;

    public dcu(String str, String str2, String str3, String str4) {
        this.f11593a = str;
        this.c = str2;
        this.e = str3;
        this.b = str4;
    }

    public boolean equals(Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            dcu dcuVar = obj instanceof dcu ? (dcu) obj : null;
            String str = this.f11593a;
            if (str != null && this.c != null && dcuVar != null && str.equals(dcuVar.e()) && this.c.equals(dcuVar.c())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return CommonUtil.h(e());
    }

    public String e() {
        return (String) cpt.d(this.f11593a);
    }

    public String d() {
        return (String) cpt.d(this.b);
    }

    public String c() {
        return (String) cpt.d(this.c);
    }

    public String a() {
        return (String) cpt.d(this.e);
    }

    public boolean b() {
        return this.d;
    }

    public void a(boolean z) {
        this.d = z;
    }
}
