package defpackage;

import java.util.Objects;

/* loaded from: classes4.dex */
public class hoj {

    /* renamed from: a, reason: collision with root package name */
    private String f13299a;
    private String c;
    private String e;

    public hoj(String str, String str2, String str3) {
        this.c = str;
        this.e = str2;
        this.f13299a = str3;
    }

    public String d() {
        return this.e;
    }

    public String e() {
        return this.c;
    }

    public String b() {
        return this.f13299a;
    }

    public int hashCode() {
        return Objects.hash(this.c, this.e, this.f13299a);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof hoj)) {
            return false;
        }
        hoj hojVar = (hoj) obj;
        String str = this.e;
        return str != null && this.c != null && this.f13299a != null && str.equals(hojVar.d()) && this.c.equals(hojVar.e()) && this.f13299a.equals(hojVar.b());
    }
}
