package defpackage;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class lsg {
    private String b;

    /* renamed from: a, reason: collision with root package name */
    private final Object f14848a = new Object();
    private Map<String, byte[]> c = new HashMap();

    int e() {
        int size;
        synchronized (this.f14848a) {
            size = this.c.size();
        }
        return size;
    }

    String a() {
        String str = this.b;
        return str == null ? "" : str;
    }

    Map<String, byte[]> d() {
        Map<String, byte[]> map;
        synchronized (this.f14848a) {
            map = this.c;
        }
        return map;
    }

    public lsg(String str) {
        this.b = str;
    }

    public lsg() {
    }
}
