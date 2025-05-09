package com.huawei.openalliance.ad.inter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.huawei.openalliance.ad.bz;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.ao;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes5.dex */
public class c {
    private static c b;
    private static final byte[] c = new byte[0];
    private Context d;
    private a f;

    /* renamed from: a, reason: collision with root package name */
    private boolean f7031a = false;
    private CopyOnWriteArrayList<WeakReference<b>> e = new CopyOnWriteArrayList<>();

    public interface b {
        void a();
    }

    public void c() {
        String str;
        try {
            ho.b("ExSplashStartReceiver", "unregister receiver");
            a aVar = this.f;
            if (aVar != null) {
                this.d.unregisterReceiver(aVar);
                this.f = null;
            }
        } catch (IllegalStateException unused) {
            str = "unregisterReceiver IllegalStateException";
            ho.c("ExSplashStartReceiver", str);
        } catch (Throwable unused2) {
            str = "unregisterReceiver exception";
            ho.c("ExSplashStartReceiver", str);
        }
    }

    public void b(b bVar) {
        try {
            CopyOnWriteArrayList<WeakReference<b>> copyOnWriteArrayList = this.e;
            if (copyOnWriteArrayList != null && copyOnWriteArrayList.size() > 0) {
                Iterator<WeakReference<b>> it = this.e.iterator();
                while (it.hasNext()) {
                    WeakReference<b> next = it.next();
                    b bVar2 = next.get();
                    if (bVar2 == null || bVar2 == bVar) {
                        this.e.remove(next);
                    }
                }
            }
        } catch (Throwable th) {
            ho.b("ExSplashStartReceiver", "removeStartListener err: %s", th.getClass().getSimpleName());
        }
    }

    public void b() {
        String str;
        try {
            c();
            if (!bz.b(this.d)) {
                ho.c("ExSplashStartReceiver", "not inner device, no need to register");
                return;
            }
            IntentFilter intentFilter = new IntentFilter(Constants.ACTION_EXSPLASH_BEGIN);
            Intent a2 = ao.a(this.d, null, intentFilter, Constants.PERMISSION_PPS_DOWNLOAD, null);
            if (a2 != null && a2.getAction() != null && a2.getAction().equals(Constants.ACTION_EXSPLASH_BEGIN)) {
                ho.b("ExSplashStartReceiver", "isExSplashStart");
                this.f7031a = true;
                this.d.removeStickyBroadcast(a2);
            }
            if (this.f == null) {
                this.f = new a();
            }
            ho.b("ExSplashStartReceiver", "register receiver");
            ao.a(this.d, this.f, intentFilter, Constants.PERMISSION_PPS_DOWNLOAD, null);
        } catch (IllegalStateException unused) {
            str = "registerReceiver IllegalStateException";
            ho.c("ExSplashStartReceiver", str);
        } catch (Throwable unused2) {
            str = "registerReceiver Exception";
            ho.c("ExSplashStartReceiver", str);
        }
    }

    public boolean a() {
        return this.f7031a;
    }

    public void a(boolean z) {
        this.f7031a = z;
    }

    public void a(b bVar) {
        if (bVar != null) {
            this.e.add(new WeakReference<>(bVar));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (this.e.isEmpty()) {
            return;
        }
        Iterator<WeakReference<b>> it = this.e.iterator();
        while (it.hasNext()) {
            WeakReference<b> next = it.next();
            if (next.get() != null) {
                next.get().a();
            }
        }
    }

    class a extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            ho.b("ExSplashStartReceiver", "onReceive");
            if (intent == null) {
                return;
            }
            try {
                if (Constants.ACTION_EXSPLASH_BEGIN.equals(intent.getAction())) {
                    c.this.f7031a = true;
                    c.this.d();
                    context.removeStickyBroadcast(intent);
                }
            } catch (Throwable th) {
                ho.c("ExSplashStartReceiver", "ExSplashBeginReceiver err: %s", th.getClass().getSimpleName());
            }
        }

        private a() {
        }
    }

    public static c a(Context context) {
        c cVar;
        synchronized (c) {
            if (b == null) {
                b = new c(context);
            }
            cVar = b;
        }
        return cVar;
    }

    private c(Context context) {
        this.d = context.getApplicationContext();
    }
}
