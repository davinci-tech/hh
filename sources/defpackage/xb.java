package defpackage;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.huawei.android.appbundle.remote.OnBinderDiedListener;
import com.huawei.android.appbundle.remote.RemoteCall;
import com.huawei.android.appbundle.remote.RemoteTask;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.hmf.tasks.TaskCompletionSource;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes8.dex */
public final class xb<T extends IInterface> {
    private static final HandlerCenter c = HandlerCenter.a((String) null);

    /* renamed from: a, reason: collision with root package name */
    private T f17741a;
    final xh b;
    private final Context e;
    private WeakReference<OnBinderDiedListener> f;
    private final RemoteCall<T> g;
    private final String h;
    private boolean j;
    private final Intent m;
    private ServiceConnection n;
    private final IBinder.DeathRecipient d = new xe(this);
    private final List<RemoteTask> i = new ArrayList();

    public xb(Context context, xh xhVar, String str, Intent intent, RemoteCall<T> remoteCall) {
        this.e = context;
        this.b = xhVar;
        this.h = str;
        this.m = intent;
        this.g = remoteCall;
    }

    public void d(OnBinderDiedListener onBinderDiedListener) {
        this.f = new WeakReference<>(onBinderDiedListener);
    }

    public T ee_() {
        return this.f17741a;
    }

    public void a(RemoteTask remoteTask) {
        b(new xd(this, remoteTask));
    }

    void e(RemoteTask remoteTask) {
        if (this.f17741a == null && !this.j) {
            this.b.d("Initiate binding to the service.", new Object[0]);
            this.i.add(remoteTask);
            xc xcVar = new xc(this);
            this.n = xcVar;
            this.j = true;
            if (this.e.bindService(this.m, xcVar, 1)) {
                return;
            }
            this.b.b("Failed to bind to the service.", new Object[0]);
            this.j = false;
            j();
            return;
        }
        if (this.j) {
            this.b.d("Waiting to bind to the service.", new Object[0]);
            this.i.add(remoteTask);
        } else {
            remoteTask.run();
        }
    }

    void ed_(IBinder iBinder) {
        this.f17741a = this.g.asInterface(iBinder);
        g();
        this.j = false;
        f();
    }

    void d() {
        i();
        this.f17741a = null;
        this.j = false;
    }

    public void e() {
        b(new xj(this));
    }

    void a() {
        if (this.f17741a != null) {
            this.e.unbindService(this.n);
            this.j = false;
            this.f17741a = null;
            this.n = null;
        }
    }

    private void g() {
        IBinder asBinder;
        this.b.e("linkToDeath", new Object[0]);
        T t = this.f17741a;
        if (t == null || (asBinder = t.asBinder()) == null) {
            return;
        }
        try {
            asBinder.linkToDeath(this.d, 0);
        } catch (RemoteException unused) {
            this.b.d("linkToDeath failed", new Object[0]);
        }
    }

    private void i() {
        IBinder asBinder;
        this.b.e("unlinkToDeath", new Object[0]);
        T t = this.f17741a;
        if (t == null || (asBinder = t.asBinder()) == null) {
            return;
        }
        asBinder.unlinkToDeath(this.d, 0);
    }

    private void f() {
        List<RemoteTask> list = this.i;
        Iterator<RemoteTask> it = list.iterator();
        while (it.hasNext()) {
            it.next().run();
        }
        list.clear();
    }

    private void j() {
        List<RemoteTask> list = this.i;
        if (list.isEmpty()) {
            return;
        }
        xf xfVar = new xf();
        Iterator<RemoteTask> it = list.iterator();
        while (it.hasNext()) {
            TaskCompletionSource task = it.next().getTask();
            if (task != null) {
                task.setException(xfVar);
            }
        }
        list.clear();
    }

    void b(RemoteTask remoteTask) {
        c.d(remoteTask, this.h);
    }

    void c() {
        OnBinderDiedListener onBinderDiedListener;
        this.b.d("reportBinderDeath", new Object[0]);
        WeakReference<OnBinderDiedListener> weakReference = this.f;
        if (weakReference == null || (onBinderDiedListener = weakReference.get()) == null) {
            return;
        }
        this.b.d("calling onBinderDied", new Object[0]);
        onBinderDiedListener.onBinderDied();
    }
}
