package com.huawei.healthcloud.plugintrack.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import com.huawei.healthcloud.plugintrack.ui.view.CanvasChangeView;

/* loaded from: classes4.dex */
public class CanvasChangeView extends FrameLayout {

    /* renamed from: a, reason: collision with root package name */
    private Canvas f3775a;
    private final Object e;

    public CanvasChangeView(Context context) {
        super(context);
        this.e = new Object();
        a();
    }

    public CanvasChangeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = new Object();
        a();
    }

    private void a() {
        setWillNotDraw(false);
        c();
    }

    private void c() {
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver != null) {
            viewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: hlo
                @Override // android.view.ViewTreeObserver.OnPreDrawListener
                public final boolean onPreDraw() {
                    return CanvasChangeView.this.b();
                }
            });
        }
    }

    public /* synthetic */ boolean b() {
        if (!isDirty()) {
            return true;
        }
        invalidate();
        return true;
    }

    public void setCanvas(Canvas canvas) {
        synchronized (this.e) {
            this.f3775a = canvas;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        synchronized (this.e) {
            Canvas canvas2 = this.f3775a;
            if (canvas2 != null) {
                super.dispatchDraw(canvas2);
            } else {
                super.dispatchDraw(canvas);
            }
        }
    }
}
