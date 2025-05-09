package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.health.R;
import java.lang.Thread;
import java.lang.ref.WeakReference;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class ic {

    /* renamed from: a, reason: collision with root package name */
    private final ia f6935a;
    private final iu b;
    private int d;
    private ServerSocket e;
    private b f;
    private boolean g;
    private iq h;
    private Context j;
    private final Map<String, Long> c = new ConcurrentHashMap();
    private boolean i = false;

    public boolean b() {
        return this.g;
    }

    public void a(boolean z) {
        this.i = z;
    }

    public void a(Socket socket) {
        try {
            io ioVar = new io(this.j, ig.a(socket.getInputStream()), this.b, this.f6935a, this.c);
            ioVar.a(this.h);
            ioVar.a(new a(this));
            ioVar.a(socket);
        } catch (Throwable th) {
            if (ho.a()) {
                ho.a(3, th);
            }
            ho.d("CreativeHttpServer", "process socket failed, %s", th.getClass().getSimpleName());
        }
    }

    public void a(Context context) {
        if (this.g) {
            return;
        }
        String string = context.getString(R.string._2130851457_res_0x7f023681);
        this.e = new ServerSocket(0, 8, InetAddress.getByName(string));
        this.f = new b();
        int localPort = this.e.getLocalPort();
        this.d = localPort;
        ii.a(string, localPort);
        Thread thread = new Thread("mediaCache") { // from class: com.huawei.openalliance.ad.ic.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                ic.this.c();
            }
        };
        thread.setUncaughtExceptionHandler(this.f);
        thread.start();
        this.g = true;
    }

    public int a() {
        return this.d;
    }

    static class a implements ih {

        /* renamed from: a, reason: collision with root package name */
        WeakReference<ic> f6938a;

        @Override // com.huawei.openalliance.ad.ih
        public void a() {
            ic icVar = this.f6938a.get();
            if (icVar != null) {
                icVar.a(true);
            }
        }

        public a(ic icVar) {
            this.f6938a = new WeakReference<>(icVar);
        }
    }

    public static class b implements Thread.UncaughtExceptionHandler {
        @Override // java.lang.Thread.UncaughtExceptionHandler
        public void uncaughtException(Thread thread, Throwable th) {
            ho.c("CreativeHttpServer", "register socket listener error: %s", th.getClass().getSimpleName());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (this.e == null) {
            return;
        }
        while (!Thread.currentThread().isInterrupted()) {
            try {
                ho.b("CreativeHttpServer", "register listener running...");
                final Socket accept = this.e.accept();
                ho.a("CreativeHttpServer", "accept a new socket: %s, data consume exceed max limit or stream error: %s ", accept, Boolean.valueOf(this.i));
                if (this.i) {
                    return;
                } else {
                    com.huawei.openalliance.ad.utils.m.k(new Runnable() { // from class: com.huawei.openalliance.ad.ic.2
                        @Override // java.lang.Runnable
                        public void run() {
                            ic.this.a(accept);
                        }
                    });
                }
            } catch (Throwable th) {
                ho.d("CreativeHttpServer", "register socket listener error! exception: " + th.getClass().getSimpleName());
                return;
            }
        }
    }

    public ic(Context context, ia iaVar, iu iuVar, iq iqVar) {
        this.f6935a = iaVar;
        this.b = iuVar;
        this.h = iqVar;
        this.j = context.getApplicationContext();
    }
}
