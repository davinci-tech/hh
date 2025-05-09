package defpackage;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/* loaded from: classes7.dex */
public class uxg {

    /* renamed from: a, reason: collision with root package name */
    private final int f17571a;
    private final uxg b;
    private Map<Character, uxg> c;
    private Set<String> d;
    private uxg e;

    public uxg() {
        this(0);
    }

    public uxg(int i) {
        this.c = new HashMap();
        this.e = null;
        this.d = null;
        this.f17571a = i;
        this.b = i == 0 ? this : null;
    }

    private uxg d(Character ch, boolean z) {
        uxg uxgVar;
        uxg uxgVar2 = this.c.get(ch);
        return (z || uxgVar2 != null || (uxgVar = this.b) == null) ? uxgVar2 : uxgVar;
    }

    public uxg d(Character ch) {
        return d(ch, false);
    }

    public uxg a(Character ch) {
        return d(ch, true);
    }

    public uxg c(Character ch) {
        uxg a2 = a(ch);
        if (a2 != null) {
            return a2;
        }
        uxg uxgVar = new uxg(this.f17571a + 1);
        this.c.put(ch, uxgVar);
        return uxgVar;
    }

    public void d(String str) {
        if (this.d == null) {
            this.d = new TreeSet();
        }
        this.d.add(str);
    }

    public void c(Collection<String> collection) {
        Iterator<String> it = collection.iterator();
        while (it.hasNext()) {
            d(it.next());
        }
    }

    public Collection<String> a() {
        Set<String> set = this.d;
        return set == null ? Collections.emptyList() : set;
    }

    public uxg d() {
        return this.e;
    }

    public void d(uxg uxgVar) {
        this.e = uxgVar;
    }

    public Collection<uxg> b() {
        return this.c.values();
    }

    public Collection<Character> c() {
        return this.c.keySet();
    }
}
