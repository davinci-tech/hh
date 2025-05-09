package defpackage;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public class svi {

    /* renamed from: a, reason: collision with root package name */
    private Map<String, svp> f17248a = new HashMap(16);
    private String b;
    private long c;

    public void d(String str) {
        this.b = str;
    }

    public void c(String str, svp svpVar) {
        this.f17248a.put(str, svpVar);
    }

    public svp c(String str) {
        return this.f17248a.get(str);
    }

    public void a(long j) {
        this.c = j;
    }
}
