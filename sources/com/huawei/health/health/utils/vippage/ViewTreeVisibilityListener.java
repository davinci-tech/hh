package com.huawei.health.health.utils.vippage;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import com.huawei.haf.handler.HandlerExecutor;
import defpackage.nsy;
import java.lang.ref.WeakReference;

/* loaded from: classes3.dex */
public class ViewTreeVisibilityListener implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
    private final WeakReference<View> d;
    private final WeakReference<ViewTreeListenee> e;

    public interface ViewTreeListenee {
        void biEvent();

        void checkVisibilityAndSetBiEvent();

        boolean hasSetBiEvent();
    }

    public ViewTreeVisibilityListener(View view, ViewTreeListenee viewTreeListenee) {
        this.e = new WeakReference<>(viewTreeListenee);
        this.d = new WeakReference<>(view);
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        ViewTreeListenee viewTreeListenee = this.e.get();
        View view = this.d.get();
        if (viewTreeListenee == null || view == null) {
            return;
        }
        if (viewTreeListenee.hasSetBiEvent()) {
            nsy.cMf_(view, this);
        } else {
            viewTreeListenee.checkVisibilityAndSetBiEvent();
        }
    }

    @Override // android.view.ViewTreeObserver.OnScrollChangedListener
    public void onScrollChanged() {
        ViewTreeListenee viewTreeListenee = this.e.get();
        View view = this.d.get();
        if (viewTreeListenee == null || view == null) {
            return;
        }
        if (viewTreeListenee.hasSetBiEvent()) {
            nsy.cMg_(view, this);
        } else {
            viewTreeListenee.checkVisibilityAndSetBiEvent();
        }
    }

    public static boolean Zx_(View view) {
        if (view == null || view.getVisibility() != 0) {
            return false;
        }
        return view.getLocalVisibleRect(new Rect());
    }

    public static void Zy_(View view, ViewTreeVisibilityListener viewTreeVisibilityListener) {
        Zz_(view, viewTreeVisibilityListener, 300L);
    }

    public static void Zz_(final View view, final ViewTreeVisibilityListener viewTreeVisibilityListener, long j) {
        HandlerExecutor.d(new Runnable() { // from class: com.huawei.health.health.utils.vippage.ViewTreeVisibilityListener.2
            @Override // java.lang.Runnable
            public void run() {
                nsy.cMa_(view, viewTreeVisibilityListener);
                nsy.cMb_(view, viewTreeVisibilityListener);
            }
        }, j);
    }
}
