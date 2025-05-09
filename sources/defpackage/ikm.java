package defpackage;

import android.util.LruCache;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes7.dex */
public class ikm {
    private LruCache<String, ikv> b = new LruCache<>(20);

    /* renamed from: a, reason: collision with root package name */
    private Set<String> f13412a = new HashSet(16);

    public ikv e(String str) {
        if (str == null) {
            return null;
        }
        return this.b.get(str);
    }

    public void c(String str, ikv ikvVar) {
        if (str == null || ikvVar == null) {
            return;
        }
        this.b.put(str, ikvVar);
    }

    public void c(String str) {
        this.f13412a.add(str);
    }

    public boolean a(String str) {
        return this.f13412a.contains(str);
    }

    public void b() {
        this.b.evictAll();
    }
}
