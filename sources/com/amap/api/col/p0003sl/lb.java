package com.amap.api.col.p0003sl;

/* loaded from: classes2.dex */
public abstract class lb implements Runnable {
    a f;

    interface a {
        void a(lb lbVar);

        void b(lb lbVar);
    }

    public abstract void runTask();

    @Override // java.lang.Runnable
    public final void run() {
        a aVar;
        try {
            if (Thread.interrupted()) {
                return;
            }
            runTask();
            if (Thread.interrupted() || (aVar = this.f) == null) {
                return;
            }
            aVar.a(this);
        } catch (Throwable th) {
            iv.c(th, "ThreadTask", "run");
            th.printStackTrace();
        }
    }

    public final void cancelTask() {
        try {
            a aVar = this.f;
            if (aVar != null) {
                aVar.b(this);
            }
        } catch (Throwable th) {
            iv.c(th, "ThreadTask", "cancelTask");
            th.printStackTrace();
        }
    }
}
