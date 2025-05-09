package defpackage;

import android.util.LruCache;
import java.util.List;

/* loaded from: classes4.dex */
public class ikl {

    /* renamed from: a, reason: collision with root package name */
    private LruCache<String, List<Integer>> f13411a = new LruCache<>(10);

    public List<Integer> a(String str) {
        if (str == null) {
            return null;
        }
        return this.f13411a.get(str);
    }

    public void d(String str, List<Integer> list) {
        if (str == null || list == null) {
            return;
        }
        this.f13411a.put(str, list);
    }

    public void b() {
        this.f13411a.evictAll();
    }
}
