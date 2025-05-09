package defpackage;

import java.util.LinkedList;

/* loaded from: classes3.dex */
public class cjo<E> {
    private LinkedList<E> e = new LinkedList<>();
    private volatile int b = 0;
    private final byte[] d = new byte[1];

    public void c(E e) {
        synchronized (this.d) {
            this.b++;
            this.e.addLast(e);
        }
    }

    public E b() {
        synchronized (this.d) {
            if (this.b <= 0) {
                this.b = 0;
                return null;
            }
            this.b--;
            return this.e.removeFirst();
        }
    }

    public int d() {
        int i;
        synchronized (this.d) {
            i = this.b;
        }
        return i;
    }

    public void a() {
        synchronized (this.d) {
            this.e.clear();
            this.b = 0;
        }
    }
}
