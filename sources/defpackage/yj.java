package defpackage;

/* loaded from: classes8.dex */
final class yj {

    /* renamed from: a, reason: collision with root package name */
    private final String f17758a;
    private final String b;

    yj(String str, String str2) {
        this.b = str;
        this.f17758a = str2;
    }

    String e() {
        return this.b;
    }

    String b() {
        return this.f17758a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof yj)) {
            return false;
        }
        yj yjVar = (yj) obj;
        return yjVar.b.equals(this.b) && yjVar.f17758a.equals(this.f17758a);
    }

    public int hashCode() {
        return this.b.hashCode() + this.f17758a.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append("oldVer:");
        sb.append(this.b);
        sb.append(", newVer:");
        sb.append(this.f17758a);
        return sb.toString();
    }
}
