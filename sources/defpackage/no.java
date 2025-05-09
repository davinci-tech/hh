package defpackage;

import java.util.LinkedList;

/* loaded from: classes2.dex */
public class no<E> {

    /* renamed from: a, reason: collision with root package name */
    private LinkedList<E> f15406a = new LinkedList<>();
    private int e = 0;

    public void d(E e) {
        synchronized (this) {
            this.e++;
            this.f15406a.addLast(e);
        }
    }

    public E d() {
        E removeFirst;
        synchronized (this) {
            this.e--;
            removeFirst = this.f15406a.removeFirst();
        }
        return removeFirst;
    }

    public int e() {
        int i;
        synchronized (this) {
            i = this.e;
        }
        return i;
    }

    public void a() {
        synchronized (this) {
            this.f15406a.clear();
            this.e = 0;
        }
    }
}
