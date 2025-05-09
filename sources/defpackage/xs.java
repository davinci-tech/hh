package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.huawei.android.appbundle.splitinstall.SplitSessionLoader;
import com.huawei.haf.bundle.InstallSessionState;
import com.huawei.haf.bundle.InstallStateListener;
import com.huawei.haf.common.security.SecurityConstant;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes8.dex */
final class xs {
    private static volatile xs d;
    private static final Object e = new Object();

    /* renamed from: a, reason: collision with root package name */
    private final SplitSessionLoader f17749a;
    private final Context b;
    private final IntentFilter c;
    private final xh f;
    private final d g;
    private final Handler i;
    private final Set<InstallStateListener> j;

    private xs(Context context) {
        this(context, xw.e());
    }

    private xs(Context context, SplitSessionLoader splitSessionLoader) {
        this.f = new xh("SplitListenerRegistry");
        this.j = Collections.newSetFromMap(new ConcurrentHashMap());
        this.c = new IntentFilter("com.huawei.android.appbundle.splitinstall.receiver.SplitInstallUpdateIntentService");
        this.g = new d();
        this.b = context.getApplicationContext();
        this.i = new Handler(Looper.getMainLooper());
        this.f17749a = splitSessionLoader;
    }

    static xs d(Context context) {
        if (d == null) {
            synchronized (e) {
                if (d == null) {
                    d = new xs(context);
                }
            }
        }
        return d;
    }

    void b(InstallStateListener installStateListener) {
        synchronized (this) {
            if (installStateListener == null) {
                this.f.a("registerListener listener == null", new Object[0]);
                return;
            }
            if (this.j.add(installStateListener) && this.j.size() == 1) {
                if (Build.VERSION.SDK_INT >= 33) {
                    this.b.registerReceiver(this.g, this.c, SecurityConstant.d, null, 4);
                } else {
                    this.b.registerReceiver(this.g, this.c, SecurityConstant.d, null);
                }
            }
            this.f.d("registerListener, size=%d", Integer.valueOf(this.j.size()));
        }
    }

    void a(InstallStateListener installStateListener) {
        synchronized (this) {
            if (installStateListener == null) {
                this.f.a("unregisterListener listener == null", new Object[0]);
                return;
            }
            if (this.j.remove(installStateListener) && this.j.isEmpty()) {
                try {
                    this.b.unregisterReceiver(this.g);
                } catch (Exception e2) {
                    this.f.e(e2, "unregisterListener", new Object[0]);
                }
            }
            this.f.d("unregisterListener, size=%d", Integer.valueOf(this.j.size()));
        }
    }

    void d(InstallSessionState installSessionState) {
        Iterator<InstallStateListener> it = this.j.iterator();
        while (it.hasNext()) {
            try {
                it.next().onStateUpdate(installSessionState);
            } catch (Exception e2) {
                this.f.e(e2, "notifyListeners State %s", String.valueOf(installSessionState));
            }
        }
    }

    void d(Runnable runnable) {
        this.i.post(runnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ek_(Intent intent) {
        SplitSessionLoader splitSessionLoader;
        Bundle bundleExtra = intent.getBundleExtra("session_state");
        if (bundleExtra == null) {
            this.f.a("onReceived, bundle == null", new Object[0]);
            return;
        }
        xx eo_ = xx.eo_(bundleExtra);
        if (eo_.status() != 2 || eo_.bytesDownloaded() == 0 || eo_.bytesDownloaded() >= eo_.totalBytesToDownload()) {
            this.f.d("onReceived: %s", eo_);
        }
        if (eo_.status() == 10 && (splitSessionLoader = this.f17749a) != null) {
            splitSessionLoader.load(eo_.d(), new yd(this, eo_));
        } else {
            d(eo_);
        }
    }

    class d extends BroadcastReceiver {
        private d() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            try {
                xs.this.ek_(intent);
            } catch (Exception e) {
                xs.this.f.e(e, "onReceive Intent %s", String.valueOf(intent));
            }
        }
    }
}
