package com.huawei.hianalytics.visual;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.view.Window;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.EventType;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes4.dex */
public class h implements w {
    public static h b;

    /* renamed from: a, reason: collision with root package name */
    public final Set<s> f3920a = new HashSet();

    public static h a() {
        if (b == null) {
            b = new h();
        }
        return b;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(Activity activity) {
        a(new z(activity, "activity_on_destroyed"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d(Activity activity) {
        a(new z(activity, "activity_on_resume"));
    }

    public void b(final Activity activity) {
        m0.a().execute(new Runnable() { // from class: com.huawei.hianalytics.visual.h$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                h.this.d(activity);
            }
        });
    }

    @Override // com.huawei.hianalytics.visual.w
    public void onActivityCreated(Activity activity, Bundle bundle) {
        if (activity.isChild()) {
            return;
        }
        u.a().f3952a = -1;
    }

    @Override // com.huawei.hianalytics.visual.w
    public void onActivityDestroyed(Activity activity) {
        j jVar = j.b;
        synchronized (jVar) {
            jVar.f3929a.remove(Integer.valueOf(activity.hashCode()));
        }
        a(activity);
        SparseArray<String> sparseArray = o0.g;
        if (sparseArray == null) {
            return;
        }
        sparseArray.clear();
    }

    @Override // com.huawei.hianalytics.visual.w
    public void onActivityPaused(Activity activity) {
        if (!activity.isChild()) {
            u.a().f3952a = -1;
        }
        Window window = activity.getWindow();
        View decorView = window != null ? window.getDecorView() : null;
        if (decorView == null) {
            return;
        }
        if (b.a().a(EventType.VIEW_EXPOSURE) || decorView.getTag(R.id.hianalytics_view_tree_observer_listener_tag) == null) {
            return;
        }
        decorView.getViewTreeObserver().removeOnDrawListener(y.a());
        decorView.getViewTreeObserver().removeOnGlobalFocusChangeListener(y.a());
        decorView.getViewTreeObserver().removeOnScrollChangedListener(y.a());
        decorView.getViewTreeObserver().removeOnGlobalLayoutListener(y.a());
        decorView.getViewTreeObserver().removeOnWindowFocusChangeListener(y.a());
        decorView.setTag(R.id.hianalytics_view_tree_observer_listener_tag, null);
    }

    @Override // com.huawei.hianalytics.visual.w
    public void onActivityResumed(Activity activity) {
        b(activity);
        Window window = activity.getWindow();
        View decorView = window != null ? window.getDecorView() : null;
        if (!activity.isChild() && decorView != null) {
            u.a().f3952a = decorView.hashCode();
        }
        if (decorView == null) {
            return;
        }
        if (!b.a().a(EventType.VIEW_EXPOSURE) && decorView.getTag(R.id.hianalytics_view_tree_observer_listener_tag) == null) {
            decorView.getViewTreeObserver().addOnDrawListener(y.a());
            decorView.getViewTreeObserver().addOnGlobalFocusChangeListener(y.a());
            decorView.getViewTreeObserver().addOnScrollChangedListener(y.a());
            decorView.getViewTreeObserver().addOnGlobalLayoutListener(y.a());
            decorView.getViewTreeObserver().addOnWindowFocusChangeListener(y.a());
            decorView.setTag(R.id.hianalytics_view_tree_observer_listener_tag, Boolean.TRUE);
        }
    }

    public void a(final Activity activity) {
        m0.a().execute(new Runnable() { // from class: com.huawei.hianalytics.visual.h$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                h.this.c(activity);
            }
        });
    }

    public final void a(z zVar) {
        HashSet hashSet;
        synchronized (this.f3920a) {
            hashSet = new HashSet(this.f3920a);
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            ((s) it.next()).a(zVar);
        }
    }
}
