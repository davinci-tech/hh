package com.huawei.watchface;

import android.os.Handler;
import android.os.Looper;
import com.huawei.watchface.utils.HwLog;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes7.dex */
public abstract class ez {

    /* renamed from: a, reason: collision with root package name */
    private final Set<String> f11024a = new HashSet();
    private Looper b = Looper.getMainLooper();

    public abstract void a();

    public abstract void a(String str);

    public boolean b(String str) {
        synchronized (this) {
            HwLog.d("PermissionsResultAction", "Permission not found: " + str);
        }
        return true;
    }

    protected final boolean a(String str, int i) {
        synchronized (this) {
            if (i == 0) {
                return a(str, ex.GRANTED);
            }
            return a(str, ex.DENIED);
        }
    }

    protected final boolean a(String str, ex exVar) {
        synchronized (this) {
            this.f11024a.remove(str);
            if (exVar == ex.GRANTED) {
                return b();
            }
            if (exVar == ex.DENIED) {
                return c(str);
            }
            if (exVar != ex.NOT_FOUND) {
                return false;
            }
            if (b(str)) {
                return b();
            }
            return c(str);
        }
    }

    private boolean b() {
        if (!this.f11024a.isEmpty()) {
            return false;
        }
        new Handler(this.b).post(new Runnable() { // from class: com.huawei.watchface.ez.1
            @Override // java.lang.Runnable
            public void run() {
                ez.this.a();
            }
        });
        return true;
    }

    private boolean c(final String str) {
        new Handler(this.b).post(new Runnable() { // from class: com.huawei.watchface.ez.2
            @Override // java.lang.Runnable
            public void run() {
                ez.this.a(str);
            }
        });
        return true;
    }
}
