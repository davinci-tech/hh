package com.huawei.hianalytics.visual;

import android.app.Activity;
import java.lang.ref.WeakReference;

/* loaded from: classes4.dex */
public class z {

    /* renamed from: a, reason: collision with root package name */
    public final WeakReference<Activity> f3957a;
    public final String b;

    public z(Activity activity, String str) {
        this.f3957a = new WeakReference<>(activity);
        this.b = str;
    }

    public Activity a() {
        return this.f3957a.get();
    }
}
