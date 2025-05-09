package defpackage;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.LruCache;
import com.huawei.skinner.facade.template.ILogger;
import com.huawei.skinner.internal.ISkinLoader;
import com.huawei.skinner.internal.ISkinUpdate;
import com.huawei.skinner.internal.OnRegisterSkinAttrsListener;
import com.huawei.skinner.loader.SkinPlugin;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/* loaded from: classes6.dex */
public class ncs implements ISkinLoader {

    /* renamed from: a, reason: collision with root package name */
    public static OnRegisterSkinAttrsListener f15255a;
    private static volatile ncs b;
    private SkinPlugin h;
    private Context m;
    private List<ISkinUpdate> r;
    private static final Object c = new Object();
    private static final ncn e = new ncn();
    public static ILogger d = new ndj();
    private LruCache<String, WeakReference<SkinPlugin>> n = new LruCache<>(5);
    private boolean g = true;
    private boolean f = false;
    private boolean i = false;
    private int o = 1;
    private ExecutorService k = Executors.newSingleThreadExecutor(a("HwSkinner SkinManager", false));
    private Handler l = new Handler(Looper.getMainLooper());
    private boolean j = false;
    private ncn s = e;
    private ncr t = ncr.e();

    @Override // com.huawei.skinner.internal.ISkinLoader
    public void notifySkinUpdate() {
    }

    private ncs(Context context) {
        Context applicationContext = context.getApplicationContext();
        if (applicationContext == null) {
            this.m = context;
        } else {
            this.m = ((Application) applicationContext).getBaseContext();
        }
        ncl.d(context);
    }

    public static ncs c(Context context) {
        return e(context);
    }

    public static ncs b() {
        if (b == null) {
            throw new ncw("HwSkinner::Init::Invoke init(context) first!");
        }
        return b;
    }

    public static ncs e(Context context) {
        if (b == null) {
            synchronized (c) {
                if (b == null) {
                    b = new ncs(context);
                }
            }
        }
        return b;
    }

    public boolean a() {
        return (this.g || this.h == null) ? false : true;
    }

    public boolean e() {
        return this.i;
    }

    private ThreadFactory a(final String str, final boolean z) {
        return new ThreadFactory() { // from class: ncs.3
            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable, str);
                thread.setDaemon(z);
                return thread;
            }
        };
    }

    public SkinPlugin d() {
        return this.h;
    }

    @Override // com.huawei.skinner.internal.ISkinLoader
    public void attach(ISkinUpdate iSkinUpdate) {
        if (this.r == null) {
            this.r = new ArrayList();
        }
        if (this.r.contains(iSkinUpdate)) {
            return;
        }
        this.r.add(iSkinUpdate);
    }

    @Override // com.huawei.skinner.internal.ISkinLoader
    public void detach(ISkinUpdate iSkinUpdate) {
        List<ISkinUpdate> list = this.r;
        if (list == null) {
            return;
        }
        list.remove(iSkinUpdate);
    }

    public boolean c() {
        return this.f;
    }
}
