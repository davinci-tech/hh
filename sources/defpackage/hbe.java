package defpackage;

/* loaded from: classes4.dex */
public class hbe<K, V> {
    private V b;
    private K d;

    public hbe(K k, V v) {
        this.d = k;
        this.b = v;
    }

    public void a(K k) {
        this.d = k;
    }

    public void e(V v) {
        this.b = v;
    }

    public K c() {
        return this.d;
    }

    public V a() {
        return this.b;
    }
}
