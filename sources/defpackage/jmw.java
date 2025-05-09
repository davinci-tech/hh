package defpackage;

import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class jmw extends jmx {

    /* renamed from: a, reason: collision with root package name */
    private String f13965a;
    private String b;
    private int c;
    private List<jmu> d;
    private String e = null;

    public jmw(Map<String, String> map, List<jmu> list, String str) {
        a(map.get("issuerid"));
        b(map.get("appletId"));
        d(map.get("cplc"));
        c(map.get("transactionId"));
        e(map.get("commandId"));
        e(list);
        d(list.size());
        f(str);
    }

    public String d() {
        return this.e;
    }

    private void e(String str) {
        this.e = str;
    }

    public int c() {
        return this.c;
    }

    private void d(int i) {
        this.c = i;
    }

    public List<jmu> a() {
        return this.d;
    }

    private void e(List<jmu> list) {
        this.d = list;
    }

    public String e() {
        return this.f13965a;
    }

    private void f(String str) {
        this.f13965a = str;
    }

    public String b() {
        return this.b;
    }
}
