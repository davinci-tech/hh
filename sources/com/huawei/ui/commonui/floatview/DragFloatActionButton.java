package com.huawei.ui.commonui.floatview;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.animation.DecelerateInterpolator;
import com.huawei.haf.application.BaseApplication;
import health.compact.a.LanguageUtil;

/* loaded from: classes6.dex */
public class DragFloatActionButton extends HealthFloatingView {

    /* renamed from: a, reason: collision with root package name */
    private int f8829a;
    private int b;
    private int c;
    private int d;
    private boolean e;

    public DragFloatActionButton(Context context) {
        super(context);
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0018, code lost:
    
        if (r2 != 3) goto L29;
     */
    @Override // com.huawei.ui.commonui.floatview.FloatingSidingView, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r9) {
        /*
            r8 = this;
            float r0 = r9.getRawX()
            int r0 = (int) r0
            float r1 = r9.getRawY()
            int r1 = (int) r1
            int r2 = r9.getAction()
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L7b
            if (r2 == r4) goto L69
            r5 = 2
            if (r2 == r5) goto L1b
            r0 = 3
            if (r2 == r0) goto L69
            goto L76
        L1b:
            int r2 = r8.b
            if (r2 <= 0) goto L66
            int r2 = r8.d
            if (r2 > 0) goto L24
            goto L66
        L24:
            int r2 = r8.c
            int r2 = r0 - r2
            int r3 = r8.f8829a
            int r3 = r1 - r3
            android.content.Context r5 = r8.getContext()
            android.view.ViewConfiguration r5 = android.view.ViewConfiguration.get(r5)
            int r5 = r5.getScaledTouchSlop()
            int r6 = java.lang.Math.abs(r5)
            int r7 = java.lang.Math.abs(r2)
            if (r6 < r7) goto L4c
            int r5 = java.lang.Math.abs(r5)
            int r6 = java.lang.Math.abs(r3)
            if (r5 >= r6) goto L4e
        L4c:
            r8.e = r4
        L4e:
            boolean r5 = r8.e
            if (r5 == 0) goto L76
            float r9 = r8.getX()
            float r2 = (float) r2
            float r9 = r9 + r2
            float r2 = r8.getY()
            float r3 = (float) r3
            float r2 = r2 + r3
            r8.b(r9, r2)
            r8.c = r0
            r8.f8829a = r1
            return r4
        L66:
            r8.e = r3
            goto L76
        L69:
            boolean r0 = r8.h()
            if (r0 == 0) goto L76
            r8.setPressed(r3)
            r8.o()
            return r4
        L76:
            boolean r9 = super.onTouchEvent(r9)
            return r9
        L7b:
            r8.setPressed(r4)
            r8.e = r3
            android.view.ViewParent r9 = r8.getParent()
            r9.requestDisallowInterceptTouchEvent(r4)
            r8.c = r0
            r8.f8829a = r1
            android.view.ViewParent r9 = r8.getParent()
            if (r9 == 0) goto La3
            android.view.ViewParent r9 = r8.getParent()
            android.view.ViewGroup r9 = (android.view.ViewGroup) r9
            int r0 = r9.getHeight()
            r8.b = r0
            int r9 = r9.getWidth()
            r8.d = r9
        La3:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.commonui.floatview.DragFloatActionButton.onTouchEvent(android.view.MotionEvent):boolean");
    }

    private void b(float f, float f2) {
        float width = ((float) (this.d - getWidth())) < f ? this.d - getWidth() : f;
        float y = getY();
        float height = getHeight();
        int i = this.b;
        if (y + height > i) {
            f2 = i - getHeight();
        }
        if (0.0f > f) {
            width = 0.0f;
        }
        if (getY() < 0.0f) {
            f2 = 0.0f;
        }
        float f3 = 0.0f <= f2 ? f2 : 0.0f;
        float height2 = this.b - getHeight();
        if (height2 < f3) {
            f3 = height2;
        }
        setX(width);
        setY(f3);
    }

    private boolean h() {
        return this.e;
    }

    private boolean k() {
        return getX() == 0.0f;
    }

    private boolean m() {
        return getX() == ((float) (this.d - getWidth()));
    }

    private void o() {
        if (k() && m()) {
            return;
        }
        if (LanguageUtil.bc(BaseApplication.e())) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "x", getX(), 0.0f);
            ofFloat.setInterpolator(new DecelerateInterpolator());
            ofFloat.setDuration(500L);
            ofFloat.start();
            return;
        }
        animate().setInterpolator(new DecelerateInterpolator()).setDuration(500L).xBy((this.d - getWidth()) - getX()).start();
    }
}
