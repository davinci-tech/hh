package com.huawei.uikit.hwrecyclerview.layoutmanager;

import android.content.Context;
import android.graphics.Path;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import java.security.SecureRandom;

/* loaded from: classes7.dex */
public class HwFloatingBubbleLayout extends FrameLayout {

    /* renamed from: a, reason: collision with root package name */
    private Path f10689a;
    private boolean b;
    private int c;
    private Region d;
    private int e;
    private float h;
    private OnSelectedListener j;

    public interface OnSelectedListener {
        void onSelectedChanged(ViewGroup viewGroup, boolean z);
    }

    public HwFloatingBubbleLayout(Context context) {
        super(context);
        this.b = false;
        this.f10689a = new Path();
        this.h = 1.0f;
        a();
    }

    private void a() {
        this.h = (new SecureRandom().nextInt(10) / 100.0f) + 0.9f;
    }

    private void d() {
        this.d = new Region();
        this.f10689a.reset();
        float f = this.c / 2.0f;
        this.f10689a.addCircle(f, this.e / 2.0f, f, Path.Direction.CW);
        this.d.setPath(this.f10689a, new Region(0, 0, this.c, this.e));
    }

    public OnSelectedListener getOnSelectedListener() {
        return this.j;
    }

    public float getRandomFactor() {
        return this.h;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        eeA_(getParent());
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.c = i;
        this.e = i2;
        d();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent == null) {
            return false;
        }
        if (motionEvent.getAction() != 0) {
            return super.onTouchEvent(motionEvent);
        }
        if (this.d.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
            return super.onTouchEvent(motionEvent);
        }
        return false;
    }

    public void setOnSelectedListener(OnSelectedListener onSelectedListener) {
        this.j = onSelectedListener;
    }

    @Override // android.view.View
    public void setSelected(boolean z) {
        super.setSelected(z);
        if (z != this.b) {
            OnSelectedListener onSelectedListener = this.j;
            if (onSelectedListener != null) {
                onSelectedListener.onSelectedChanged(this, z);
            }
            this.b = z;
        }
    }

    private void eeA_(ViewParent viewParent) {
        if (viewParent != null && (viewParent instanceof ViewGroup)) {
            ViewGroup viewGroup = (ViewGroup) viewParent;
            viewGroup.setClipChildren(false);
            viewGroup.setClipToPadding(false);
        }
    }

    public HwFloatingBubbleLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = false;
        this.f10689a = new Path();
        this.h = 1.0f;
        a();
    }

    public HwFloatingBubbleLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = false;
        this.f10689a = new Path();
        this.h = 1.0f;
        a();
    }
}
