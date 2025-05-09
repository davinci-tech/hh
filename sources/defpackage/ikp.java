package defpackage;

import android.util.LruCache;

/* loaded from: classes4.dex */
public class ikp {
    private LruCache<Integer, Object> b = new LruCache<>(20);

    public Object b(int i) {
        return this.b.get(Integer.valueOf(i));
    }

    public void a(int i, Object obj) {
        if (obj == null) {
            return;
        }
        this.b.put(Integer.valueOf(i), obj);
    }

    public void a() {
        this.b.evictAll();
    }
}
