package com.huawei.hianalytics.visual;

import android.view.View;
import android.view.ViewTreeObserver;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes4.dex */
public class y implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener, ViewTreeObserver.OnGlobalFocusChangeListener, ViewTreeObserver.OnDrawListener, ViewTreeObserver.OnWindowFocusChangeListener {
    public static volatile y b;

    /* renamed from: a, reason: collision with root package name */
    public final Set<x> f3956a = new HashSet();

    public static y a() {
        if (b == null) {
            synchronized (y.class) {
                if (b == null) {
                    b = new y();
                }
            }
        }
        return b;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b() {
        a(new a0("view_draw_changed"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c() {
        a(new a0("view_focus_changed"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d() {
        a(new a0("view_layout_changed"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void e() {
        a(new a0("view_scroll_changed"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f() {
        a(new a0("window_focus_changed"));
    }

    @Override // android.view.ViewTreeObserver.OnDrawListener
    public void onDraw() {
        m0.a().execute(new Runnable() { // from class: com.huawei.hianalytics.visual.y$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                y.this.b();
            }
        });
    }

    @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
    public void onGlobalFocusChanged(View view, View view2) {
        m0.a().execute(new Runnable() { // from class: com.huawei.hianalytics.visual.y$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                y.this.c();
            }
        });
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        m0.a().execute(new Runnable() { // from class: com.huawei.hianalytics.visual.y$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                y.this.d();
            }
        });
    }

    @Override // android.view.ViewTreeObserver.OnScrollChangedListener
    public void onScrollChanged() {
        m0.a().execute(new Runnable() { // from class: com.huawei.hianalytics.visual.y$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                y.this.e();
            }
        });
    }

    @Override // android.view.ViewTreeObserver.OnWindowFocusChangeListener
    public void onWindowFocusChanged(boolean z) {
        m0.a().execute(new Runnable() { // from class: com.huawei.hianalytics.visual.y$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                y.this.f();
            }
        });
    }

    public final void a(a0 a0Var) {
        HashSet hashSet;
        synchronized (this.f3956a) {
            hashSet = new HashSet(this.f3956a);
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            ((x) it.next()).a(a0Var);
        }
    }
}
