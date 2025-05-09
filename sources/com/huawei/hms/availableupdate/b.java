package com.huawei.hms.availableupdate;

import android.app.Activity;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class b {
    public static final b b = new b();
    private static final Object c = new Object();

    /* renamed from: a, reason: collision with root package name */
    private final List<Activity> f4420a = new ArrayList(1);

    public void a(Activity activity) {
        synchronized (c) {
            for (Activity activity2 : this.f4420a) {
                if (activity2 != null && activity2 != activity && !activity2.isFinishing()) {
                    activity2.finish();
                }
            }
            this.f4420a.add(activity);
        }
    }

    public void b(Activity activity) {
        synchronized (c) {
            this.f4420a.remove(activity);
        }
    }
}
