package defpackage;

import org.json.JSONArray;

/* loaded from: classes5.dex */
public class lnp {
    private static final String e = "RequestGetDevServInfo";
    private lnq d;
    private lnu c = null;

    /* renamed from: a, reason: collision with root package name */
    private lnr f14785a = null;

    public void a(lnq lnqVar) {
        this.d = lnqVar;
    }

    public void d(lnu lnuVar) {
        this.c = lnuVar;
    }

    public void c(lnr lnrVar) {
        this.f14785a = lnrVar;
    }

    public String a() {
        String str = e;
        loq.c(str, "Build RequestInfo JsonObj start");
        JSONArray jSONArray = new JSONArray();
        lnq lnqVar = this.d;
        if (lnqVar != null) {
            jSONArray.put(lnqVar.d());
        }
        lnr lnrVar = this.f14785a;
        if (lnrVar != null) {
            jSONArray.put(lnrVar.c());
        }
        lnu lnuVar = this.c;
        if (lnuVar != null) {
            jSONArray.put(lnuVar.d());
        }
        if (loq.b.booleanValue()) {
            loq.c(str, "Build RequestInfo JsonObj result:" + jSONArray.toString());
        }
        return jSONArray.toString();
    }
}
