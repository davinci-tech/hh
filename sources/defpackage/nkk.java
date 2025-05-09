package defpackage;

import java.util.LinkedList;

/* loaded from: classes9.dex */
public class nkk {
    LinkedList<Runnable> c = new LinkedList<>();

    public void b() {
        synchronized (this.c) {
            while (!this.c.isEmpty()) {
                try {
                    this.c.removeFirst().run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void b(Runnable runnable) {
        synchronized (this.c) {
            this.c.addLast(runnable);
        }
    }

    public void d() {
        synchronized (this.c) {
            this.c.clear();
        }
    }

    public void c() {
        d();
        this.c = null;
    }
}
