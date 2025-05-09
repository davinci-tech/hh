package defpackage;

import android.util.Log;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/* loaded from: classes7.dex */
public final class sto {
    private volatile String j;
    private static final Object c = new Object();
    private static sto b = new sto();
    private int f = -1;

    /* renamed from: a, reason: collision with root package name */
    private int f17226a = -1;
    private boolean h = false;
    private BlockingQueue<stp> d = new ArrayBlockingQueue(256);
    private d g = new d();
    private boolean i = false;
    private boolean e = false;

    private sto() {
        Log.i("AppLogManager", "AppLogManager onCreate");
    }

    public static sto e() {
        return b;
    }

    public boolean e(stp stpVar) {
        return this.d.offer(stpVar);
    }

    class d extends Thread {
        public d() {
            super.setName("PrintWorker");
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            sto stoVar = sto.this;
            synchronized (sto.c) {
                try {
                    sts.e(stoVar.f, stoVar.j, stoVar.f17226a, true);
                } catch (IOException unused) {
                    Log.i("AppLogManager", "LogWrite init IOException");
                }
                d(stoVar);
            }
            Log.i("AppLogManager", "PrintWoker end.");
            sts.a("I", "AppLogManager", "PrintWoker end.", null);
            sts.b();
            sto.this.i = false;
        }

        private void d(sto stoVar) {
            while (stoVar.i) {
                try {
                    if (sto.this.h) {
                        stp stpVar = (stp) stoVar.d.poll();
                        if (stpVar != null) {
                            sts.a(stpVar.c(), stpVar.e(), stpVar.a(), null);
                            sts.b();
                        } else {
                            Log.i("AppLogManager", "PrintWoker poll timeout , shutdown");
                            sts.b();
                            stp stpVar2 = (stp) stoVar.d.take();
                            sts.a(stpVar2.c(), stpVar2.e(), stpVar2.a(), null);
                            sts.b();
                        }
                    } else {
                        stp stpVar3 = (stp) stoVar.d.poll(60L, TimeUnit.SECONDS);
                        if (stpVar3 != null) {
                            sts.a(stpVar3.c(), stpVar3.e(), stpVar3.a(), null);
                        } else {
                            Log.i("AppLogManager", "PrintWoker poll timeout , shutdown");
                            sts.a("I", "AppLogManager", "PrintWoker poll timeout , shutdown", null);
                            sts.b();
                            stp stpVar4 = (stp) stoVar.d.take();
                            sts.a(stpVar4.c(), stpVar4.e(), stpVar4.a(), null);
                        }
                    }
                } catch (Error unused) {
                    Log.i("AppLogManager", "PrintWoker Error");
                    return;
                } catch (InterruptedException unused2) {
                    Log.i("AppLogManager", "PrintWoker InterruptedException");
                    return;
                } catch (Exception unused3) {
                    Log.i("AppLogManager", "PrintWoker IllegalMonitorStateException");
                    return;
                }
            }
        }
    }
}
