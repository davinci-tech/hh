package defpackage;

/* loaded from: classes8.dex */
public class zm {

    /* renamed from: a, reason: collision with root package name */
    protected final String f17774a;
    protected final boolean c;
    protected final String e;

    public zm(String str, String str2, boolean z) {
        this.f17774a = str;
        this.e = str2;
        this.c = z;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("{");
        b(sb);
        sb.append("}");
        return sb.toString();
    }

    protected StringBuilder b(StringBuilder sb) {
        sb.append("moduleName:");
        sb.append(this.f17774a);
        sb.append('_');
        sb.append(this.e);
        sb.append(", builtIn:");
        sb.append(this.c);
        return sb;
    }
}
