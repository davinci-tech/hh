package com.huawei.ui.homehealth.device.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import androidx.core.view.NestedScrollingParent3;
import androidx.core.view.NestedScrollingParentHelper;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.homehealth.device.util.NestScrollHelper;
import com.huawei.ui.homehealth.device.view.NestedWebView;

/* loaded from: classes6.dex */
public class NestedDeviceScrollView extends ScrollView implements NestedScrollingParent3 {

    /* renamed from: a, reason: collision with root package name */
    private boolean f9413a;
    private ScrollViewListener b;
    private NestedScrollingParentHelper c;
    private boolean d;
    private final GestureDetector e;

    public interface ScrollViewListener {
        void onScrollChanged(ScrollView scrollView, int i, int i2, int i3, int i4);
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public boolean onStartNestedScroll(View view, View view2, int i, int i2) {
        return (i & 2) != 0;
    }

    public NestedDeviceScrollView(Context context) {
        this(context, null);
    }

    public NestedDeviceScrollView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NestedDeviceScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = false;
        this.f9413a = false;
        this.e = new GestureDetector(context, new GestureDetector.OnGestureListener() { // from class: com.huawei.ui.homehealth.device.view.NestedDeviceScrollView.2
            @Override // android.view.GestureDetector.OnGestureListener
            public boolean onDown(MotionEvent motionEvent) {
                return false;
            }

            @Override // android.view.GestureDetector.OnGestureListener
            public void onLongPress(MotionEvent motionEvent) {
            }

            @Override // android.view.GestureDetector.OnGestureListener
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                return false;
            }

            @Override // android.view.GestureDetector.OnGestureListener
            public void onShowPress(MotionEvent motionEvent) {
            }

            @Override // android.view.GestureDetector.OnGestureListener
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                return false;
            }

            @Override // android.view.GestureDetector.OnGestureListener
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                LogUtil.c("NestedDeviceScrollView", "GestureDetector onFling：", Float.valueOf(f2));
                if (!NestedDeviceScrollView.this.f9413a) {
                    return false;
                }
                NestedDeviceScrollView.this.a((int) (-f2));
                return false;
            }
        });
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x000f, code lost:
    
        if (r0 != 3) goto L13;
     */
    @Override // android.widget.ScrollView, android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r4) {
        /*
            r3 = this;
            int r0 = r4.getAction()
            r0 = r0 & 255(0xff, float:3.57E-43)
            if (r0 == 0) goto L15
            r1 = 1
            if (r0 == r1) goto L15
            r2 = 2
            if (r0 == r2) goto L12
            r1 = 3
            if (r0 == r1) goto L15
            goto L18
        L12:
            r3.d = r1
            goto L18
        L15:
            r0 = 0
            r3.d = r0
        L18:
            boolean r4 = super.onInterceptTouchEvent(r4)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.homehealth.device.view.NestedDeviceScrollView.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.View
    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        ScrollViewListener scrollViewListener = this.b;
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, i, i2, i3, i4);
        }
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.b = scrollViewListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        NestScrollHelper nestScrollHelper = new NestScrollHelper(getContext());
        double c = nestScrollHelper.c(i);
        final NestedWebView cZR_ = cZR_(this);
        if (cZR_ != null) {
            double height = (getHeight() - cZR_.getHeight()) - getScrollY();
            if (c <= height || i <= 0) {
                return;
            }
            double c2 = nestScrollHelper.c(i);
            final double d = nestScrollHelper.d(c - height);
            postDelayed(new Runnable() { // from class: ogo
                @Override // java.lang.Runnable
                public final void run() {
                    NestedWebView.this.a((int) Math.abs(d));
                }
            }, (int) (c2 - nestScrollHelper.c(d)));
        }
    }

    private NestedWebView cZR_(ViewGroup viewGroup) {
        NestedWebView cZR_;
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof NestedWebView) {
                return (NestedWebView) childAt;
            }
            if ((childAt instanceof ViewGroup) && (cZR_ = cZR_((ViewGroup) childAt)) != null) {
                return cZR_;
            }
        }
        return null;
    }

    @Override // android.widget.ScrollView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        GestureDetector gestureDetector = this.e;
        if (gestureDetector != null) {
            gestureDetector.onTouchEvent(motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }

    public NestedScrollingParentHelper getParentHelper() {
        if (this.c == null) {
            this.c = new NestedScrollingParentHelper(this);
        }
        return this.c;
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedPreScroll(View view, int i, int i2, int[] iArr, int i3) {
        if (i2 > 0) {
            c(i2, iArr);
        }
    }

    @Override // androidx.core.view.NestedScrollingParent3
    public void onNestedScroll(View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
        c(i4, null);
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedScroll(View view, int i, int i2, int i3, int i4, int i5) {
        c(i4, null);
    }

    private void c(int i, int[] iArr) {
        int scrollY = getScrollY();
        scrollBy(0, i);
        int scrollY2 = getScrollY();
        if (iArr != null) {
            iArr[1] = iArr[1] + (scrollY2 - scrollY);
        }
    }

    @Override // android.widget.ScrollView, android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public boolean onNestedFling(View view, float f, float f2, boolean z) {
        if (z) {
            return false;
        }
        dispatchNestedFling(0.0f, f2, true);
        fling((int) f2);
        return true;
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedScrollAccepted(View view, View view2, int i, int i2) {
        getParentHelper().onNestedScrollAccepted(view, view2, i, i2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public boolean onNestedPreFling(View view, float f, float f2) {
        return dispatchNestedPreFling(f, f2);
    }

    @Override // android.widget.ScrollView, android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public void onNestedScrollAccepted(View view, View view2, int i) {
        getParentHelper().onNestedScrollAccepted(view, view2, i);
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onStopNestedScroll(View view, int i) {
        getParentHelper().onStopNestedScroll(view, i);
    }

    @Override // android.widget.ScrollView, android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public void onStopNestedScroll(View view) {
        getParentHelper().onStopNestedScroll(view);
    }

    @Override // android.view.ViewGroup, androidx.core.view.NestedScrollingParent
    public int getNestedScrollAxes() {
        if (this.d) {
            return super.getNestedScrollAxes();
        }
        return getParentHelper().getNestedScrollAxes();
    }

    public void setHasNest(boolean z) {
        this.f9413a = z;
    }
}
