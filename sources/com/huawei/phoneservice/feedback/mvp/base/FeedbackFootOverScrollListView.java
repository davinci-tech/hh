package com.huawei.phoneservice.feedback.mvp.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.ListView;
import com.huawei.health.R;
import com.huawei.phoneservice.faq.base.util.FaqRefectUtils;

/* loaded from: classes9.dex */
public class FeedbackFootOverScrollListView extends ListView {

    /* renamed from: a, reason: collision with root package name */
    private int f8265a;
    private int b;
    private boolean c;
    private int d;
    private float e;
    private int f;
    private boolean g;
    private a i;

    /* loaded from: classes5.dex */
    public interface a {
        void a(int i, int i2, int i3, int i4);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.widget.ListView
    public void setOverscrollFooter(Drawable drawable) {
        super.setOverscrollFooter(drawable);
        if (drawable instanceof a) {
            a aVar = (a) drawable;
            this.i = aVar;
            aVar.a(getWidth(), getHeight(), getWidth(), getHeight());
        }
    }

    @Override // android.widget.AbsListView
    public void setOnScrollListener(AbsListView.OnScrollListener onScrollListener) {
        super.setOnScrollListener(onScrollListener);
    }

    @Override // android.view.View
    public void scrollTo(int i, int i2) {
        super.scrollTo(i, i2);
        if (this.c) {
            this.c = false;
        }
    }

    @Override // android.view.View
    protected boolean overScrollBy(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
        return super.overScrollBy(i, i2, i3, i4, i5, i6, i7, this.f8265a, z);
    }

    @Override // android.widget.AbsListView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int findPointerIndex;
        if (motionEvent.getAction() == 0) {
            int i = this.d;
            if (i != -1 && (findPointerIndex = motionEvent.findPointerIndex(i)) != -1) {
                int pointToPosition = pointToPosition((int) motionEvent.getX(findPointerIndex), (int) motionEvent.getY(findPointerIndex));
                this.f = pointToPosition;
                if (pointToPosition == -1) {
                    FaqRefectUtils.c(this, AbsListView.class, "mTouchMode", 5);
                }
            }
            return super.onTouchEvent(motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        a aVar = this.i;
        if (aVar != null) {
            aVar.a(i, i2, i3, i4);
        }
    }

    @Override // android.widget.AbsListView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(parcelable);
        this.c = true;
    }

    @Override // android.widget.AbsListView, android.view.View
    protected void onOverScrolled(int i, int i2, boolean z, boolean z2) {
        if (i2 < 0 && !this.g) {
            i2 = 0;
        }
        super.onOverScrolled(i, i2, z, z2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0014, code lost:
    
        if (r1 != 3) goto L22;
     */
    @Override // android.widget.AbsListView, android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            boolean r0 = super.onInterceptTouchEvent(r6)
            int r1 = r6.getAction()
            r1 = r1 & 255(0xff, float:3.57E-43)
            if (r1 == 0) goto L3c
            r2 = 1
            r3 = -1
            if (r1 == r2) goto L37
            r4 = 2
            if (r1 == r4) goto L17
            r2 = 3
            if (r1 == r2) goto L37
            goto L49
        L17:
            int r1 = r5.d
            if (r1 != r3) goto L1c
            goto L49
        L1c:
            int r1 = r6.findPointerIndex(r1)
            if (r1 != r3) goto L23
            goto L49
        L23:
            r5.d = r1
            float r1 = r6.getY(r1)
            int r1 = (int) r1
            float r3 = r5.e
            int r3 = (int) r3
            int r1 = r1 - r3
            int r1 = java.lang.Math.abs(r1)
            int r3 = r5.b
            if (r1 <= r3) goto L49
            return r2
        L37:
            r5.d = r3
            r5.f = r3
            goto L49
        L3c:
            float r1 = r6.getY()
            r5.e = r1
            r1 = 0
            int r1 = r6.getPointerId(r1)
            r5.d = r1
        L49:
            if (r0 != 0) goto L4e
            r5.onTouchEvent(r6)
        L4e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.phoneservice.feedback.mvp.base.FeedbackFootOverScrollListView.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    private void d(Context context) {
        this.b = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        this.f8265a = (int) (context.getResources().getDisplayMetrics().density * 140.0f);
        setOverScrollMode(0);
    }

    public FeedbackFootOverScrollListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = -1;
        this.c = false;
        this.g = true;
        this.f = -1;
        d(context);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100087_res_0x7f0601b7});
            this.g = obtainStyledAttributes.getBoolean(0, true);
            obtainStyledAttributes.recycle();
        }
    }

    public FeedbackFootOverScrollListView(Context context) {
        super(context);
        this.d = -1;
        this.c = false;
        this.g = true;
        this.f = -1;
        d(context);
    }
}
