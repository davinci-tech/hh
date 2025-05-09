package defpackage;

import androidx.collection.SparseArrayCompat;
import java.util.Collection;
import java.util.LinkedList;

/* loaded from: classes6.dex */
public class nra<T> {
    private final SparseArrayCompat<SparseArrayCompat<T>> d = new SparseArrayCompat<>();

    public void a(int i, int i2, T t) {
        SparseArrayCompat<T> sparseArrayCompat = this.d.get(i);
        if (sparseArrayCompat == null) {
            SparseArrayCompat<T> sparseArrayCompat2 = new SparseArrayCompat<>();
            sparseArrayCompat2.put(i2, t);
            this.d.put(i, sparseArrayCompat2);
            return;
        }
        sparseArrayCompat.put(i2, t);
    }

    public T a(int i, int i2) {
        SparseArrayCompat<T> sparseArrayCompat = this.d.get(i);
        if (sparseArrayCompat == null) {
            return null;
        }
        return sparseArrayCompat.get(i2);
    }

    public Collection<T> e(int i) {
        LinkedList linkedList = new LinkedList();
        SparseArrayCompat<T> sparseArrayCompat = this.d.get(i);
        int size = sparseArrayCompat.size();
        for (int i2 = 0; i2 < size; i2++) {
            T t = sparseArrayCompat.get(sparseArrayCompat.keyAt(i2));
            if (t != null) {
                linkedList.add(t);
            }
        }
        return linkedList;
    }

    public Collection<T> d(int i) {
        LinkedList linkedList = new LinkedList();
        int size = this.d.size();
        for (int i2 = 0; i2 < size; i2++) {
            T t = this.d.get(this.d.keyAt(i2)).get(i);
            if (t != null) {
                linkedList.add(t);
            }
        }
        return linkedList;
    }

    public Collection<T> b() {
        LinkedList linkedList = new LinkedList();
        int size = this.d.size();
        for (int i = 0; i < size; i++) {
            SparseArrayCompat<T> sparseArrayCompat = this.d.get(this.d.keyAt(i));
            int size2 = sparseArrayCompat.size();
            for (int i2 = 0; i2 < size2; i2++) {
                linkedList.add(sparseArrayCompat.get(sparseArrayCompat.keyAt(i2)));
            }
        }
        return linkedList;
    }

    public void e(int i, int i2) {
        SparseArrayCompat<T> sparseArrayCompat = this.d.get(i);
        if (sparseArrayCompat != null) {
            sparseArrayCompat.remove(i2);
        }
    }

    public void e() {
        this.d.clear();
    }
}
