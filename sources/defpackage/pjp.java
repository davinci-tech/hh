package defpackage;

import java.lang.ref.WeakReference;

/* loaded from: classes6.dex */
public class pjp<V> {
    private WeakReference<V> e;

    public void b(V v) {
        this.e = new WeakReference<>(v);
    }

    public void b() {
        WeakReference<V> weakReference = this.e;
        if (weakReference != null) {
            weakReference.clear();
            this.e = null;
        }
    }

    public V a() {
        WeakReference<V> weakReference = this.e;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }
}
