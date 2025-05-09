package defpackage;

import android.util.LruCache;

/* loaded from: classes3.dex */
public class cug {

    /* renamed from: a, reason: collision with root package name */
    private static volatile cug f11479a;
    private static final Object d = new Object();
    private LruCache<String, String> b = new LruCache<>(4);

    private cug() {
    }

    public static cug c() {
        if (f11479a == null) {
            synchronized (d) {
                if (f11479a == null) {
                    f11479a = new cug();
                }
            }
        }
        return f11479a;
    }

    public void a(String str, String str2) {
        this.b.put(str, str2);
    }

    public String d(String str) {
        return this.b.get(str);
    }

    public void e() {
        this.b.evictAll();
    }
}
