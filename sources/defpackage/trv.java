package defpackage;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.text.TextUtils;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.wearengine.core.callback.ServiceConnectStateCallback;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public class trv {

    /* renamed from: a, reason: collision with root package name */
    private final String f17358a;
    private String c;
    private final String f;
    private final ExecutorService i = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS, new LinkedBlockingQueue());
    private final Object d = new Object();
    private final Object e = new Object();
    private ServiceConnectStateCallback g = null;
    private volatile IBinder b = null;
    private final ServiceConnection h = new ServiceConnection() { // from class: trv.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            tos.a("ServiceBindingHelper", "onServiceConnected success!");
            synchronized (trv.this.e) {
                trv.this.b = iBinder;
                tos.b("ServiceBindingHelper", "onServiceConnected BINDER_LOCK notifyAll");
                trv.this.e.notifyAll();
            }
            trv.this.ffi_(componentName, iBinder);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            tos.a("ServiceBindingHelper", "onServiceDisconnected success!");
            synchronized (trv.this.e) {
                trv.this.b = null;
                tos.b("ServiceBindingHelper", "onServiceDisconnected BINDER_LOCK notifyAll");
                trv.this.e.notifyAll();
            }
            trv.this.ffj_(componentName);
        }
    };

    public trv(String str, String str2) {
        this.f = str;
        this.f17358a = str2;
    }

    public void c(String str) {
        this.c = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ffi_(final ComponentName componentName, final IBinder iBinder) {
        if (this.g == null) {
            tos.d("ServiceBindingHelper", this.f17358a + ", mServiceConnectStateCallback is null");
            return;
        }
        this.i.execute(new Runnable() { // from class: trv.3
            @Override // java.lang.Runnable
            public void run() {
                trv.this.g.onConnectChanged(componentName, iBinder, true);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ffj_(final ComponentName componentName) {
        if (this.g == null) {
            tos.d("ServiceBindingHelper", this.f17358a + ", mServiceConnectStateCallback is null");
            return;
        }
        this.i.execute(new Runnable() { // from class: trv.5
            @Override // java.lang.Runnable
            public void run() {
                trv.this.g.onConnectChanged(componentName, null, false);
            }
        });
    }

    public void c(ServiceConnectStateCallback serviceConnectStateCallback) {
        tos.a("ServiceBindingHelper", "registerOnServiceConnectStateCallback:" + this.f17358a);
        this.g = serviceConnectStateCallback;
    }

    private void b() {
        tos.a("ServiceBindingHelper", "start bindService service : " + this.f17358a);
        synchronized (this.d) {
            tos.a("ServiceBindingHelper", "bindService locked");
            if (this.b != null) {
                tos.a("ServiceBindingHelper", "mBinder is not null");
                return;
            }
            if (TextUtils.isEmpty(this.f) || TextUtils.isEmpty(this.f17358a)) {
                tos.e("ServiceBindingHelper", "packageName or className is empty");
                throw new IllegalStateException(String.valueOf(12));
            }
            Intent intent = new Intent();
            intent.setPackage(this.f);
            intent.setClassName(this.f, this.f17358a);
            if (!TextUtils.isEmpty(this.c)) {
                intent.setAction(this.c);
            }
            Intent fcY_ = tot.fcY_(intent);
            if (fcY_ == null) {
                throw new IllegalStateException(String.valueOf(12));
            }
            synchronized (this.e) {
                if (!tot.a().bindService(fcY_, this.h, 37)) {
                    tos.e("ServiceBindingHelper", "bindService do not has permission");
                    throw new IllegalStateException(String.valueOf(15));
                }
                e();
            }
        }
    }

    private void e() {
        while (this.b == null) {
            try {
                tos.a("ServiceBindingHelper", "bindService BINDER_LOCK wait");
                this.e.wait(OpAnalyticsConstants.H5_LOADING_DELAY);
                tos.a("ServiceBindingHelper", "bindService BINDER_LOCK continue");
            } catch (InterruptedException unused) {
                tos.e("ServiceBindingHelper", "bindService wait error");
                return;
            }
        }
        tos.a("ServiceBindingHelper", "bindService finish");
    }

    public void c() {
        synchronized (this.d) {
            if (this.b != null) {
                tos.a("ServiceBindingHelper", "begin unBindService");
                try {
                    tot.a().unbindService(this.h);
                } catch (Exception unused) {
                    tos.e("ServiceBindingHelper", "unBindService has exception");
                }
                this.b = null;
            }
        }
        tos.a("ServiceBindingHelper", "unBindService success");
    }

    public IBinder ffk_() {
        return this.b;
    }

    public IBinder ffl_() {
        if (this.b == null) {
            b();
        }
        return this.b;
    }
}
