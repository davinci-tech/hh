package com.huawei.hms.availableupdate;

import android.app.Activity;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class a {
    public static final a c = new a();
    private static final Object d = new Object();

    /* renamed from: a, reason: collision with root package name */
    private final AtomicBoolean f4419a = new AtomicBoolean(false);
    private final List<Activity> b = new ArrayList(1);

    public void a(Activity activity) {
        synchronized (d) {
            for (Activity activity2 : this.b) {
                if (activity2 != null && activity2 != activity && !activity2.isFinishing()) {
                    activity2.finish();
                }
            }
            this.b.add(activity);
        }
    }

    public void b(Activity activity) {
        synchronized (d) {
            this.b.remove(activity);
        }
    }

    public void a(boolean z) {
        this.f4419a.set(z);
    }

    public AtomicBoolean a() {
        return this.f4419a;
    }
}
