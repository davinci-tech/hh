package com.huawei.openalliance.ad;

/* loaded from: classes5.dex */
public class jf {

    /* renamed from: a, reason: collision with root package name */
    private final String f7119a = "PRELOAD_" + hashCode();
    private int b = 0;
    private a c;

    public interface a {
        void a();
    }

    public void b() {
        synchronized (this) {
            int i = this.b - 1;
            this.b = i;
            if (i < 0) {
                this.b = 0;
            }
            ho.a("PreloadWebViewMonitor", "dec count: " + this.b);
            if (this.b <= 0) {
                com.huawei.openalliance.ad.utils.dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.jf.1
                    @Override // java.lang.Runnable
                    public void run() {
                        jf.this.c();
                    }
                }, this.f7119a, 60000L);
            }
        }
    }

    public void a() {
        synchronized (this) {
            this.b++;
            com.huawei.openalliance.ad.utils.dj.a(this.f7119a);
            ho.a("PreloadWebViewMonitor", "inc count: " + this.b);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        ho.a("PreloadWebViewMonitor", "unbindService");
        this.c.a();
    }

    public jf(a aVar) {
        this.c = aVar;
    }
}
