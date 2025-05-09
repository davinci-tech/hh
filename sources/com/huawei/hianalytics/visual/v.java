package com.huawei.hianalytics.visual;

import android.app.Activity;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class v {
    public static final v c = new v();

    /* renamed from: a, reason: collision with root package name */
    public final ArrayList<w> f3954a = new ArrayList<>();
    public volatile q b = null;

    public w[] a() {
        w[] wVarArr;
        synchronized (this.f3954a) {
            wVarArr = (w[]) this.f3954a.toArray(new w[0]);
        }
        return wVarArr;
    }

    public Activity b() {
        WeakReference<Activity> weakReference;
        if (this.b == null || (weakReference = this.b.c) == null) {
            return null;
        }
        return weakReference.get();
    }

    public Activity c() {
        WeakReference<Activity> weakReference;
        if (this.b == null || (weakReference = this.b.b) == null) {
            return null;
        }
        return weakReference.get();
    }

    public final void a(w wVar) {
        if (wVar == null) {
            return;
        }
        synchronized (this.f3954a) {
            this.f3954a.add(wVar);
        }
    }
}
