package defpackage;

import java.util.List;

/* loaded from: classes4.dex */
public class fgm {

    /* renamed from: a, reason: collision with root package name */
    private List<String> f12496a;
    private String d;

    public String c() {
        return this.d;
    }

    public void c(String str) {
        this.d = str;
    }

    public List<String> d() {
        return this.f12496a;
    }

    public void a(List<String> list) {
        this.f12496a = list;
    }

    public int hashCode() {
        String str = this.d;
        int hashCode = str != null ? str.hashCode() : 0;
        List<String> list = this.f12496a;
        return (hashCode * 31) + (list != null ? list.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof fgm)) {
            return false;
        }
        fgm fgmVar = (fgm) obj;
        String str = this.d;
        return str != null && this.f12496a != null && str.equals(fgmVar.c()) && this.f12496a.equals(fgmVar.d());
    }
}
